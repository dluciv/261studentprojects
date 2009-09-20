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
    | l when Seq.length l < LangUtils.BYTE_SIZE -> ()
    | l -> CollectionUtil.takeFromList LangUtils.BYTE_SIZE l
           |> ListBinaryConversion.constructByte 
           |> s.WriteByte
           writeBytes (CollectionUtil.removeFirstElements LangUtils.BYTE_SIZE l) s

// Запись в файл (непринудительная, то есть непрямая. Если буффер не заполнен, запись не произведется)
let flushBuffer (LBuffer(lst, max_lngth) as bf : ListBuffer) (fileStream : FileStream) = 
    match lst with
    | l when l.Length < max_lngth * LangUtils.BYTE_SIZE -> bf
    | l -> writeBytes (CollectionUtil.takeFromList (max_lngth * LangUtils.BYTE_SIZE) l) fileStream
           constructBuffer max_lngth (CollectionUtil.removeFirstElements (max_lngth * LangUtils.BYTE_SIZE) l)

// "Закрытие" буффера. Принудительная (прямая) запись всего, что есть в буффере в файл. Опять же, необходимо 
// позаботиться о кратности длины списка 8ми.    
let closeBuffer (fileStream : FileStream) (LBuffer(lst, max_lngth): ListBuffer) = 
    writeBytes lst fileStream
    
// Функция дополняет буффер до конца, то есть до кратного 8ми по размеру списка.
let fillBufferToEnd (LBuffer(lst, max_lngth) as bf : ListBuffer) = 
    constructBuffer max_lngth (lst @ (List.replicate (LangUtils.BYTE_SIZE - lst.Length % LangUtils.BYTE_SIZE) 0))

    
 // Производит запись нового кода, соответствующего текущему сисмволу, в буффер и попытку непринудительно
// записать содержимое буффера в файл. 
let insert (LBuffer(lst, max_lngth) : ListBuffer) (stageNum : (int * int)) = 
    ListBinaryConversion.constructNumber stageNum lst
    |> constructBuffer max_lngth 
    |> flushBuffer

// Производит запись нового бита в буффер и попытку непринудительно
// записать содержимое буффера в файл. 
let insertBit (LBuffer(lst, max_lngth) : ListBuffer) (i : int) = 
    constructBuffer max_lngth (i :: lst)
    |> flushBuffer
    

    
let writeLastByteSize (countAlphabet : Dictionary<byte HuffmanTree.Tree, int>) 
  (codes : Dictionary<byte, (int * int)>) (writer : FileStream)(lb : ListBuffer) =
        insert lb (LangUtils.BYTE_SIZE, ListBinaryConversion.countSizeOfLastByte countAlphabet codes) writer
    
// Записывает через буфер в файл словарь кодирования методом RLE    
let writeDictionary (dic : Dictionary<byte, (int * int)>) (buffer : ListBuffer) (fileStream : FileStream) = 
    // Получаем список из 256ти значений на основе запроса в словаре длин кодов каждого и 256ти возможных значений
    // байта. Не самый, наверное, оптимальный, но самый интуитивно понятный способ.                        
    List.fold (fun res num -> if dic.ContainsKey num 
                                then 
                                   (LangUtils.first(dic.[(byte)num])) :: res 
                                else 
                                  0 :: res) [] [(byte)0..(byte)255]
    |> RLE.collapse
    |> List.fold (fun r (a, b) -> insert (insert r (LangUtils.BYTE_SIZE, a) fileStream) (LangUtils.BYTE_SIZE, b) fileStream) buffer                                      



let levelOff (LBuffer(lst, max_length) : ListBuffer) = 
    constructBuffer max_length ((List.replicate (LangUtils.BYTE_SIZE - lst.Length %LangUtils.BYTE_SIZE) 0) @ lst)