#light

open System.Collections.Generic

 
// Строит словарь распределения листьев и узлов на каждом уровне на основе дерева Хаффмана.
// Структура словаря: ключ - номер уровня (корень - уровень 1), значение - пара из количества 
// узлов в уровне и списка элементов, находящихся в листьях.

//TODO начать нумерацию с 0
let expandByStage (tree : 'a HuffmanTree.Tree)= 
    let rec expand_ (d : Dictionary<int, (int* 'a list)>) (stage : int) (tree_ : 'a HuffmanTree.Tree) = 
        match tree_ with 
        |HuffmanTree.NullTree -> d
        |HuffmanTree.TreeLeaf x ->  CollectionUtil.addToStage d x stage
        |HuffmanTree.TreeNode (x, y) -> expand_ (expand_ (CollectionUtil.addNodeToStage d stage) (stage + 1) y) (stage + 1) x
                            
    expand_  (new Dictionary<int, (int *'a list)>()) 0 tree 
    
    
// "Нумерует" "дерево", учитывая то, что оно каноническое. По сути, берет словарь с распределением
// элементов по уровням и, проверяя каждый уровень и сортируя список элементов по возрастанию, 
// высчитывает длину кода и десятичное значение кода каждого из элементов на нем с учетом количества узлов. 
// Например, если запись в словаре (вместе с ключем) будет иметь вид
// <4, (1, b :: g :: h ::[])>,  то для символов b, g и h будем иметь записи
// <b, (4,1)>, <g, (4,2)> и <h, (4,3)> соответственно.
// На самом деле, из-за того, что нумерация уровней идет с 1, первое число в паре на 1 больше, чем длина 
// кода числа.
let numerate = 
    
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
        (a, Seq.sort lst)
        
        
    Seq.fold (fun d (p : KeyValuePair<int, (int * byte list)>) -> sortValue p.Value
                                                                  |> insertIntoDictionary d p.Key ) (new Dictionary<byte, (int * int)>())
    


let makeCamonicalRepresentation d = 
    let tmp = expandByStage d
    numerate tmp
        