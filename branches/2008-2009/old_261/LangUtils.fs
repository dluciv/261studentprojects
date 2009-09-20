#light
open System.Collections
open System.Collections.Generic
open System.IO

let BYTE_SIZE = 8
let READER_BUFFER_SIZE = 2

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
    | _, _ -> Messages.internalError "<take>"
              |> failwith
    
  take_ lst n []

let takeFromNth (n : int) (lst : 'a list) = 
  let rec take_ (l : 'b list) (num : int) = 
    match num, l with
    |0, ys -> ys
    |x, (y :: ys) when x > 0 -> take_ ys (x - 1) 
    |_, _ -> Messages.internalError "<takeFromNth>"
             |> failwith
    
  take_ lst n
  
let takeLast (lst : 'a list) = 
  takeFromNth (List.length lst - 1) lst
  |> (fun ((x :: xs) : 'a list) -> x) 
  
let toList a = a :: []  
  
let rec tenPower (pow : int) = 
    match pow with
    |0 -> 1L
    |x ->  10L * tenPower(x - 1)

let write (writer : FileStream) (l : byte list) = 
      writer.Write(List.to_array l, 0, l.Length)
  
let intListToByteList (l : int list) = 
      List.fold (fun lst el -> ((byte)el ) :: lst) [] l
      |> List.rev
  

let rec uintToBytes (v : uint32) num  res= 
  match num with
  | 4 -> res
  | x -> res @ [((byte)(v >>> (8 * x)))]
         |> uintToBytes v (x + 1)
         

         
         
let constructInt (l : byte list)= 
  List.fold (fun res nxt -> (res <<< 8) + (int)nxt) 0 l

let constructUint32 (l : byte list)= 
  List.fold (fun res nxt -> (res <<< 8) + (uint32)nxt) 0u l

let constructInt16 (l : byte list)= 
  List.fold (fun res nxt -> (res <<< 8) + (int16)nxt) 0s l
        

let convertToBits (lst : byte list) = 
    let rec byteToBits (bt : byte) num (res : int list) = 
        match num with
        | 0 -> res
        | x ->byteToBits (bt >>> 1) (x - 1) (int(bt % 2uy) :: res)
    let rec convert (l : byte list) res = 
        match l with
        | [] -> res
        | (x :: xs) -> byteToBits x 8 []
                       |> (@) (convert xs res)
    convert lst []
    
let rec addNullBytes lst = 
    match List.length lst with
    | x when x < 4 -> lst @ [0uy]
                      |> addNullBytes 
    | _ -> lst
    
// Проверяет совпадение байтов из потоков
let testEquals (reader1 : BinaryReader) (reader2 : BinaryReader) = 
    let mutable readedBytes1 = reader1.ReadBytes(READER_BUFFER_SIZE)
    let mutable readedBytes2 = reader2.ReadBytes(READER_BUFFER_SIZE)
    let mutable equals = true;
    let mutable readedCount = 0;
    let rec listEquals (list1 : byte list) (list2 : byte list) =
        match list1, list2 with
        | x::xs, y::ys when x = y -> listEquals xs ys
        | [], [] -> true
        | _, _ -> false
      
    let rec arrayEquals (array1 : byte []) (array2 : byte []) = 
        listEquals (List.of_array array1) (List.of_array  array2)
    
    while(equals && readedBytes1.Length > 0 && readedBytes2.Length = readedBytes1.Length) do
      if arrayEquals readedBytes1 readedBytes2
        then readedBytes1 <- reader1.ReadBytes(READER_BUFFER_SIZE)
             readedBytes2 <- reader2.ReadBytes(READER_BUFFER_SIZE)
             readedCount <- readedCount + readedBytes1.Length
        else readedCount
             |> Messages.notEqualsAt 
             equals <- false
    equals && readedBytes2.Length = readedBytes1.Length

// Проверяет совпадение файлов   
let fileEquals  (filename : string) (decodedName : string) = 
    using(new BinaryReader(File.Open(filename, FileMode.Open)))
        (fun reader -> using(new BinaryReader(File.Open(decodedName, FileMode.Open)))
                          (fun reader2 -> testEquals reader reader2))
    