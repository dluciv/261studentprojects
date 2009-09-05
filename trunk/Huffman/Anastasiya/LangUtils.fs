#light
open System.Collections
open System.Collections.Generic
open System.IO

let BYTE_SIZE = 8

let first (tuple : ('a* 'b)) = 
  match tuple with
  |(a, b) -> a
  
let second (tuple : ('a* 'b)) = 
  match tuple with
  |(a, b) -> b
  
  
let take (n : int) (lst : 'a list)= 
  let rec take_ (l : 'b list) (num : int) (result : 'b list) = 
    match num, l with
    | 0, _ -> result
    | x, (y :: ys) when x > 0 -> take_ ys (x - 1) (result @ [y])
    | _, _ -> failwith "internal error <take>"
    
  take_ lst n []

let takeFromNth (n : int) (lst : 'a list) = 
  let rec take_ (l : 'b list) (num : int) = 
    match num, l with
    |0, ys -> ys
    |x, (y :: ys) when x > 0 -> take_ ys (x - 1) 
    |_, _ -> failwith "internal error <takeNth>"
    
  take_ lst n
  
let rec tenPower (pow : int) = 
    match pow with
    |0 -> 1L
    |x ->  10L * tenPower(x - 1)

let write (writer : FileStream) (l : byte list) = 
      writer.Write(List.to_array l, 0, l.Length)
  
let intListToByteList (l : int list) = 
      List.fold (fun lst el -> ((byte)el ) :: lst) [] l
      |> List.rev
  
//let print (lst : 'a list) = 
//  printf "\nLIST: \n"
//  List.fold (fun r el -> print_any el
//                         printf "\t"
//                         0) 0 lst
//  printf "\n"
//  lst
//  
  
  
//let print_all an = 
//    printf "\n"
//    print_any an
//    printf "\n"
//    an 
//let print1 (lst : 'a list) = 
//  List.fold (fun r el -> print_any el
//                         printf " "
//                         0) 0 lst
//  lst
//  
//let printSeq (s : 'a seq) = 
//  Seq.fold (fun r el ->  print_any el
//                         printf "\t"
//                         0) 0 s
//  printf "\n"
//  s  
//  
//  
  
//let printSeqOfList (s : ('b* 'a list) seq) = 
//  Seq.fold (fun r el ->  printf "("
//                         print_any (first el)
//                         printf ", "
//                         print_any (second el)
//                         printf ")\t"
//                         0) 0 s
//  printf "\n"
//  s    
//  
//  
//let printDicOfList (s : Dictionary<'b, 'a list>) = 
//  Seq.fold (fun r (el : KeyValuePair<'b, 'a list>) ->  printf "("
//                                                       print_any el.Key
//                                                       printf ", "
//                                                       print_any el.Value
//                                                       printf ")\t"
//                                                       0) 0 s
//  printf "\n"
//  s      
//  
//let printDicOfList1 (s : Dictionary<'b list, 'a>) = 
//  Seq.fold (fun r (el : KeyValuePair<'b list, 'a>) ->  printf "("
//                                                       print_any el.Key
//                                                       printf ", "
//                                                       print_any el.Value
//                                                       printf ")\t"
//                                                       0) 0 s
//  printf "\n"
//  s        
//  
//  
//  

//let printLst (lst : 'a list) = 
//    List.fold(fun a b -> print_any b
//                         printf "::"
//                         a) () lst
//
//let printPair ((count,  lst) : (int * 'a list)) = 
//    print_any count
//    printf ", "
//    printLst lst
//  
//let printDic (d : Dictionary<int, (int * 'a list)>) = 
//    printf "\start printing dict: "  
//    Seq.fold (fun a (kv : KeyValuePair<int, (int * 'a list)>) -> print_any kv.Key
//                                                                 printf ": "
//                                                                 printPair kv.Value
//                                                                 printf "; " 
//                                                                 a) () d
//    