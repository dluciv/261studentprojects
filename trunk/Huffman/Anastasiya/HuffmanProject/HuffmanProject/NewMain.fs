#light

open System.IO
open System.Collections
open System.Collections.Generic
open Microsoft.FSharp.Control


let READER_BUFFER = 10
let WRITER_BUFFER = 1


type 'a Tree =NullTree | TreeLeaf of 'a | TreeNode of 'a Tree * 'a Tree

// Объединяет два дерева в одно, при этом создается "узел", ветками которого являются. 
// исходные два дерева. Возвращает полученное дерево. 
let concatTree (t1 : 'a Tree) (t2 : 'a Tree) = 
    match t1, t2 with
    |NullTree, t -> t
    |t, NullTree -> t
    |_, _ -> TreeNode (t1, t2)
    
// Печатает дерево в удобочитаемом виде. Возвращает исходное дерево.    
let rec printTree (tree : 'a Tree) = 
    let rec printNode t n = 
        match t with
        | NullTree -> printf("EMPTY\n")
        | TreeNode (x, y) ->  print_any(n)
                              printf(") ")
                              print_any("Node")
                              printf("\n")
                              printNode x (n + 1)
                              printNode y (n + 1)
        | TreeLeaf x -> print_any(n)
                        printf(") ")
                        print_any(x)
                        printf("\n")
    printNode tree 0 
    tree

// Вставляет пару дерево-количество вхождений в аналогичный список с учетом сортировки а порядке 
// увеличения количеств вхождений. 
let rec insertWithSorting (l : ('a Tree* int) list) (n : ('a Tree * int)) = 
    match l, n with
    | [], _ -> [n]
    | ((_, v) :: xs), (_, nV) when v >= nV -> n :: l
    | (((_, v) as x) :: xs), (_, nV) when v < nV -> x :: insertWithSorting xs n
    |  _ -> [n]


// Конвертиует словарь с листьями-количеством вхождений  в список листьев, отсортированный в порядке 
// убывания количества вхождений.
let convertToSortedList (d : Dictionary<'a Tree, int>) =
    Seq.fold (fun l (n : KeyValuePair<'a Tree, int>) -> insertWithSorting l (n.Key, n.Value) ) [] d
    
// Рекурсивно строит дерево из списка деревьев(изначально листьев). Механизм: берет два "головных"
// элемента и объединяет их в новый узел, вставляя его в список с учетом сортировки. Затем снова берется 
// два головных элемента и объединяются в дерево и т. д. В результате остается одно дерево, которое и возвращается
let rec buildTree (l : ('a Tree* int) list) = 
    match l with
    |[] -> NullTree
    |(t, _) :: [] -> t
    |(NullTree, _) :: xs -> buildTree xs
    | x :: (NullTree, _) :: xs -> buildTree (x :: xs)
    | (xT, xI) :: (yT, yI) :: xs -> buildTree (insertWithSorting xs ((concatTree xT yT), xI + yI))


// Полный процесс построения дерева Хаффмана (обычного) на основе словаря, полученного после прочтения
// исходного файла.
let makeHuffmanTree (d : Dictionary<byte Tree, int>) = 
    d
    |> convertToSortedList
    |> buildTree

 
// Строит словарь распределения листьев и узлов на каждом уровне на основе дерева Хаффмана.
// Структура словаря: ключ - номер уровня (корень - уровень 1), значение - пара из количества 
// узлов в уровне и списка элементов, находящихся в листьях.

//TODO начать нумерацию с 0
let expandByStage (tree : 'a Tree)= 
    let rec expand_ (d : Dictionary<int, (int* 'a list)>) (stage : int) (tree_ : 'a Tree) = 
        match tree_ with 
        |NullTree -> d
        |TreeLeaf x ->  CollectionUtil.addToStage d x stage
        |TreeNode (x, y) -> expand_ (expand_ (CollectionUtil.addNodeToStage d stage) (stage + 1) y) (stage + 1) x
                            
    expand_  (new Dictionary<int, (int *'a list)>()) 1 tree   
    
// Для отладки    
let printTemp (d : 'a) = 
    print_any d
    printf "\n"
    d

// "Нумерует" "дерево", учитывая то, что оно каноническое. По сути, берет словарь с распределением
// элементов по уровням и, проверяя каждый уровень и сортируя список элементов по возрастанию, 
// высчитывает длину кода и десятичное значение кода каждого из элементов на нем с учетом количества узлов. 
// Например, если запись в словаре (вместе с ключем) будет иметь вид
// <4, (1, b :: g :: h ::[])>,  то для символов b, g и h будем иметь записи
// <b, (4,1)>, <g, (4,2)> и <h, (4,3)> соответственно.
// На самом деле, из-за того, что нумерация уровней идет с 1, первое число в паре на 1 больше, чем длина 
// кода числа.
let numerate (dic : Dictionary<int, (int * byte list)>) = 
    
    // Функция, вставляющая в словарь записи о всех символах, находящихся на уровне, согласно описанному выше
    // механизму
    let insertIntoDictionary (d : Dictionary<byte, (int* int)>) (stage : int) 
        ((nodes, letters) : (int *seq<byte>)) =  
        match Seq.fold (fun ((dict , num) : (Dictionary<byte, (int * int)>* int)) (l : byte) -> dict.Add(l, (stage, nodes + num))
                                                                                                (dict, num + 1)) (d, 0) letters with
        | (a, b) -> a
        

    // Сортирует элементы списка по возрастанию и возвращает исходную пару с сортированным списком.
    // Избыточна и будет удалена.
    let sortValue ((a, lst) : (int * byte list)) = 
        (a, Seq.sortBy (fun int_val -> ((int)int_val)) lst)
        
        
    Seq.fold (fun d (p : KeyValuePair<int, (int * byte list)>) -> insertIntoDictionary d p.Key (sortValue p.Value)) (new Dictionary<byte, (int * int)>()) dic
    
    
    

// Большинство следующих далее функций на самом деле каким-либо образом связаны с буффером и записью в файл.
// Работа с буффером и работа со списками будут вынесены в отдельные файлы.


// Тип буффер. Состоит из списк, хранящего биты и максимальной длины, после которой ближайшая непринудительная 
// инструкция слить в файл содержимое буффера действительно запишет в файл количество байтов, равное максимальной длине
type ListBuffer = LBuffer of int list * int

// Создает буффер
let constructBuffer (max_size : int) (lst: int list) = 
    LBuffer(lst, max_size)
    
// Создает пустой буффер    
let constructEmptyBuffer (max_size : int) = constructBuffer max_size []
    
// Аналог инструкции take. Почему-то я ее не нашла в классе List    
let takeFromList (num : int) (l : 'a list) = 
    Seq.take num l
    |> Seq.fold (fun res el -> el :: res) []       

// Возвращает список без num первых элементов        
let rec removeFirstElements (num : int) (lst : 'a list) = 
    match num, lst with
    | n, l when n <= 0 -> l
    | n, (x :: xs) -> removeFirstElements(n - 1) xs
    | n, [] -> []

// Создает из списка 0 и 1 байт. Если список больше, чем из 8ми элементов, верхние элементы "обрезаются"        
let constructByte (l : int list) = 
    (byte) (List.fold (fun a b -> (a <<< 1) + b) 0 l)

// Записывает список из 0 и 1 в виде байтов в файл. Если длина списка не кратна 8ми, то "остаток" отрезается.
// Записывает по одному байту ввиду того, что FileStream почему-то не дает по-другому. Будем думать.
let rec writeBytes (lst : int list) (s : FileStream) = 
    match lst with
    | l when Seq.length l < 8 -> ()
    | l -> takeFromList 8 l
           |> constructByte 
           |> s.WriteByte
           writeBytes (removeFirstElements 8 l) s


// Запись в файл (непринудительная, то есть непрямая. Если буффер не заполнен, запись не произведется)
let flushBuffer (LBuffer(lst, max_lngth) as bf : ListBuffer) (fileStream : FileStream) = 
    match lst with
    | l when l.Length < max_lngth * 8 -> bf
    | l -> writeBytes (takeFromList (max_lngth * 8) l) fileStream
           constructBuffer max_lngth (removeFirstElements (max_lngth * 8) l)

// "Закрытие" буффера. Принудительная (прямая) запись всего, что есть в буффере в файл. Опять же, необходимо 
// позаботиться о кратности длины списка 8ми.    
let closeBuffer (fileStream : FileStream) (LBuffer(lst, max_lngth): ListBuffer) = 
    writeBytes lst fileStream
    
// Вспомогательная функция, которая позволяет добавить определенное количество нулей в конец списка.    
let appendNullsToEnd (count : int) (lst : int list) = 
    let rec nulls (num : int) (result : int list) = 
        match num with
        | x when x <= 0 -> result
        | x -> nulls (x - 1) (0 :: result)
    lst @ (nulls count [])        
             
// Функция, позволяющая "дозаполнить" буффер до кратного 8ми числа битов, ориентируясь на максимальный уровень
// в дереве. Таким образом, если максимальный уровень дерева будет A, а количество "недозаполненных" байтов B,
// то необходимо заполнить уровень B + 8 * K нулями так, что бы B + 8 * K > A. Таким образом берем 
// K = [(A - B) / 8] (целая часть). Количество необходимых нулей таким образом будет ([(A - B) / 8] + 1) * 8 + B
let fillBufferToEnd (firstEmptyStage : int) (LBuffer(lst, max_lngth) as bf : ListBuffer) = 
    match lst.Length % 8 with
    | 0 -> bf
    | x when x > firstEmptyStage -> constructBuffer max_lngth (appendNullsToEnd x lst)  
    | x -> constructBuffer max_lngth (appendNullsToEnd (((firstEmptyStage - x) / 8 + 1) * 8 + x) lst)    

// Производит запись нового кода, соответствующего текущему сисмволу, в буффер и попытку непринудительно
// записать содержимое буффера в файл. 
let insert (LBuffer(lst, max_lngth) : ListBuffer) (stageNum : (int * int)) = 
    
    // Дозаполняет список из 0 и 1 нулями спереди для того, что бы "длина" кода была равна заявленной.
    let rec appendNulls (allLength : int) (binaryValue : int list)  = 
        match binaryValue with
        | bv when bv.Length >= allLength -> binaryValue
        | bv -> appendNulls allLength (0 :: binaryValue) 
        
    // Переводит число в список, представляющий бинарное значение числа.
    let rec convertToBinary (numValue : int) (binaryValue : int list) =
        match numValue with
        | 0 -> binaryValue              
        | x -> convertToBinary (numValue >>> 1) ((int)((byte)numValue <<< 7 >>> 7) :: binaryValue)

    // Конструирует бинарное списочное представление необходимой длины числа (кода)
    let constructNumber ((numLength, numValue): (int * int)) = 
        convertToBinary numValue []
        |> appendNulls (numLength - 1)
        |> List.append lst
        
    constructNumber stageNum
    |> constructBuffer max_lngth
    |> flushBuffer
       
// Записывает через буфер в файл словарь кодирования методом RLE    
let writeDictionary (dic : Dictionary<byte, (int * int)>) (buffer : ListBuffer) (fileStream : FileStream) = 
    // Берет первое значение из пары
    let firstInPair (p : (int * int)) = 
        match  p with
        |(a, b) -> a
    // "Схлопывает" список из 256ти значений по алгоритму RLE
    let collapse (lst : int list) = 
        List.fold (fun res num -> 
                        match res with
                        |((a, b) :: xs) when a = num -> (a, b + 1) :: xs
                        |l -> (num, 1) :: l
                        ) [] lst
    // Получаем список из 256ти значений на основе запроса в словаре длин кодов каждого и 256ти возможных значений
    // байта. Не самый, наверное, оптимальный, но самый интуитивно понятный способ.                        
    List.fold (fun res num -> if dic.ContainsKey num 
                                then 
                                   (firstInPair(dic.[(byte)num])) :: res 
                                else 
                                  0 :: res) [] [(byte)0..(byte)255]
    |> collapse
    // Записываем полученные значения в буффер. Значение 8 + 1 выплывает из-за нумерации с 1 уровней в дереве.
    |> List.fold (fun r (a, b) -> insert (insert r (9, a) fileStream) (9, b) fileStream) buffer                                      


// Функция с очень оригинальным названием на самом деле делает второй проход считывания по файлу и
// затем записывает в буфер и закрывает его сначала словарь, а затем все содержимое файла на основе словаря.
let writeToFile (filename : string) (resultName : string) (dic : Dictionary<byte, (int * int)>)  = 
    
    // Высчитывает максимальный уровень в дереве
    let countMaxStage = 
        let getFirstValue (p : (int * int)) = 
            match p with 
            | (a, b) -> a
        let max a b = if a > b then a else b
        Seq.fold (fun m (p : KeyValuePair<byte, (int * int)>) -> max (getFirstValue p.Value) m) 0 dic 

    // Производит одновременное считывание и записывание результатов в новый файл. По сути выполняет основную рвботу,
    // лежащую на головной функции
    let rec readWrite (streamReader  : BinaryReader) (buffer : ListBuffer) (fileStream : FileStream)= 
        using (fileStream) (fun writer ->
            using (streamReader) (fun reader ->
                let mutable currentBuffer = writeDictionary dic buffer writer
                let mutable currentBytes = reader.ReadBytes(READER_BUFFER)
                while(currentBytes.Length > 0) do 
                  for bt in currentBytes do
                    currentBuffer <- insert currentBuffer dic.[bt] writer
                  currentBytes <- reader.ReadBytes(READER_BUFFER)
                currentBuffer
            )
            |>fillBufferToEnd countMaxStage
            |>closeBuffer  writer
        )
                                            
    readWrite (new BinaryReader(File.Open(filename, FileMode.Open))) (constructEmptyBuffer 1) (new FileStream(resultName, FileMode.Create))

// Делает первй проход считывания по файлу, строит словарь и затем строит на основе него дерево, совмещая 
// предыдущие функции создания и конвертирования для дерева хаффмана.
let readAndBuildTree (filename : string) (resultName : string) (d : Dictionary<byte Tree, int>)= 
    let rec read_ (streamReader  : BinaryReader) = 
        using (streamReader) (fun reader ->
          let mutable currentBytes = reader.ReadBytes(READER_BUFFER)
          while (currentBytes.Length > 0) do
            for bt in currentBytes do
              CollectionUtil.addToDictionary d (TreeLeaf bt)
            currentBytes <- reader.ReadBytes(READER_BUFFER)
        ) 
    
    read_ (new BinaryReader(File.Open(filename, FileMode.Open)))
    makeHuffmanTree d
    |> expandByStage
    |> numerate
    |> writeToFile filename resultName


// Собственно тест.
readAndBuildTree("e:\\file.txt")  ("e:\\result.txt") (new Dictionary<byte Tree, int>())



