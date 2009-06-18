#light

open System.IO
open System.Collections
open System.Collections.Generic


// Тип буффер. Состоит из списк, хранящего биты и максимальной длины, после которой ближайшая непринудительная 
// инструкция слить в файл содержимое буффера действительно запишет в файл количество байтов, равное максимальной длине
type ListBuffer = LBuffer of int list * int

// Создает буффер
let constructBuffer (max_size : int) (lst: int list) = 
    LBuffer(lst, max_size)
    
// Создает пустой буффер    
let constructEmptyBuffer (max_size : int) = constructBuffer max_size []
    
// Записывает список из 0 и 1 в виде байтов в файл. Если длина списка не кратна 8ми, то "остаток" отрезается.
// Записывает по одному байту ввиду того, что FileStream почему-то не дает по-другому. Будем думать.
let rec writeBytes (lst : int list) (s : FileStream) = 
    match lst with
    | l when Seq.length l < 8 -> ()
    | l -> CollectionUtil.takeFromList 8 l
           |> ConvertingUtils.constructByte 
           |> s.WriteByte
           writeBytes (CollectionUtil.removeFirstElements 8 l) s

// Запись в файл (непринудительная, то есть непрямая. Если буффер не заполнен, запись не произведется)
let flushBuffer (LBuffer(lst, max_lngth) as bf : ListBuffer) (fileStream : FileStream) = 
    match lst with
    | l when l.Length < max_lngth * 8 -> bf
    | l -> writeBytes (CollectionUtil.takeFromList (max_lngth * 8) l) fileStream
           constructBuffer max_lngth (CollectionUtil.removeFirstElements (max_lngth * 8) l)

// "Закрытие" буффера. Принудительная (прямая) запись всего, что есть в буффере в файл. Опять же, необходимо 
// позаботиться о кратности длины списка 8ми.    
let closeBuffer (fileStream : FileStream) (LBuffer(lst, max_lngth): ListBuffer) = 
    writeBytes lst fileStream
    
// Функция, позволяющая "дозаполнить" буффер до кратного 8ми числа битов, ориентируясь на максимальный уровень
// в дереве. Таким образом, если максимальный уровень дерева будет A, а количество "недозаполненных" байтов B,
// то необходимо заполнить уровень B + 8 * K нулями так, что бы B + 8 * K > A. Таким образом берем 
// K = [(A - B) / 8] (целая часть). Количество необходимых нулей таким образом будет ([(A - B) / 8] + 1) * 8 + B
let fillBufferToEnd (firstEmptyStage : int) (LBuffer(lst, max_lngth) as bf : ListBuffer) = 
    match lst.Length % 8 with
    | 0 -> bf
    | x when x > firstEmptyStage -> constructBuffer max_lngth (ConvertingUtils.appendNullsToEnd x lst)  
    | x -> constructBuffer max_lngth (ConvertingUtils.appendNullsToEnd (((firstEmptyStage - x) / 8 + 1) * 8 + x) lst)    

    
 // Производит запись нового кода, соответствующего текущему сисмволу, в буффер и попытку непринудительно
// записать содержимое буффера в файл. 
let insert (LBuffer(lst, max_lngth) : ListBuffer) (stageNum : (int * int)) = 
    ConvertingUtils.constructNumber stageNum lst
    |> constructBuffer max_lngth 
    |> flushBuffer
    
// Записывает через буфер в файл словарь кодирования методом RLE    
let writeDictionary (dic : Dictionary<byte, (int * int)>) (buffer : ListBuffer) (fileStream : FileStream) = 
    // Берет первое значение из пары
    let firstInPair (p : (int * int)) = 
        match  p with
        |(a, b) -> a
    // Получаем список из 256ти значений на основе запроса в словаре длин кодов каждого и 256ти возможных значений
    // байта. Не самый, наверное, оптимальный, но самый интуитивно понятный способ.                        
    List.fold (fun res num -> if dic.ContainsKey num 
                                then 
                                   (firstInPair(dic.[(byte)num])) :: res 
                                else 
                                  0 :: res) [] [(byte)0..(byte)255]
    |> RLE.collapse
    // Записываем полученные значения в буффер. Значение 8 + 1 выплывает из-за нумерации с 1 уровней в дереве.
    |> List.fold (fun r (a, b) -> insert (insert r (9, a) fileStream) (9, b) fileStream) buffer                                      


    