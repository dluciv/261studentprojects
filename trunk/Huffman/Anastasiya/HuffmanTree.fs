#light

open System.Collections.Generic



type 'a Tree =NullTree | TreeLeaf of 'a | TreeNode of 'a Tree * 'a Tree

let makeLeaf = TreeLeaf

// Объединяет два дерева в одно, при этом создается "узел", ветками которого являются. 
// исходные два дерева. Возвращает полученное дерево. 
let concatTree (t1 : 'a Tree) (t2 : 'a Tree) = 
    match t1, t2 with
    |NullTree, t -> t
    |t, NullTree -> t
    |_, _ -> TreeNode (t1, t2)
    
//// Печатает дерево в удобочитаемом виде. Возвращает исходное дерево.    
//let rec printTree (tree : 'a Tree) = 
//    let rec printNode t n = 
//        match t with
//        | NullTree -> printf("EMPTY\n")
//        | TreeNode (x, y) ->  print_any(n)
//                              printf(") ")
//                              print_any("Node")
//                              printf("\n")
//                              printNode x (n + 1)
//                              printNode y (n + 1)
//        | TreeLeaf x -> print_any(n)
//                        printf(") ")
//                        print_any(x)
//                        printf("\n")
//    printNode tree 0 
//    tree

// Вставляет пару дерево-количество вхождений в аналогичный список с учетом сортировки а порядке 
// увеличения количеств вхождений. 
let rec insertWithSorting<'a> (l : ('a Tree* int) list) (n : ('a Tree * int)) = 
    match l, n with
    | [], _ -> [n]
    | ((_, v) :: xs), (_, nV) when v >= nV -> n :: l
    | (((_, v) as x) :: xs), (_, nV) when v < nV -> x :: insertWithSorting xs n
    |  _ -> [n]


// Конвертиует словарь с листьями-количеством вхождений  в список листьев, отсортированный в порядке 
// убывания количества вхождений.
let convertToSortedList<'a> (d : seq<KeyValuePair<'a Tree, int>>) =
    Seq.fold (fun l (n : KeyValuePair<'a Tree, int>) -> insertWithSorting<'a> l (n.Key, n.Value)) [] d
    
// Рекурсивно строит дерево из списка деревьев(изначально листьев). Механизм: берет два "головных"
// элемента и объединяет их в новый узел, вставляя его в список с учетом сортировки. Затем снова берется 
// два головных элемента и объединяются в дерево и т. д. В результате остается одно дерево, которое и возвращается
let rec buildTree<'a> (l : ('a Tree* int) list) = 
    match l with
    |[] -> NullTree
    |(t, _) :: [] -> t
    |(NullTree, _) :: xs -> buildTree xs
    | x :: (NullTree, _) :: xs -> buildTree (x :: xs)
    | (xT, xI) :: (yT, yI) :: xs -> buildTree (insertWithSorting xs ((concatTree xT yT), xI + yI))


let data (tree : 'a Tree) = 
  match tree with
  | TreeLeaf(d) -> d
  | _ -> failwith "tree error"
    


// Полный процесс построения дерева Хаффмана (обычного) на основе словаря, полученного после прочтения
// исходного файла.
let makeHuffmanTree<'a> (d : seq<KeyValuePair<'a Tree, int>>)= 
    buildTree<'a> (convertToSortedList d)
