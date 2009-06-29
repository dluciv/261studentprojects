#light

open System.IO;
open System.Collections;
open System.Collections.Generic;

// "Схлопывает" список из 256ти значений по алгоритму RLE
let collapse (lst : int list) = 
    List.fold (fun res num -> 
                 match res with
                 |((a, b) :: xs) when a = num -> (a, b + 1) :: xs
                 |l -> (num, 1) :: l
               ) [] lst
    
               
let readList (reader : BinaryReader) = 
    let mutable readedBytes : byte array = reader.ReadBytes(2)
    let mutable result = []
    let mutable readedCount = 0
    
    while(readedBytes.Length = 2) do
        print_any readedBytes
        result <- ((int)readedBytes.[0], (int)readedBytes.[1]) :: result
        readedCount <- readedCount + (int)readedBytes.[1]
        if readedCount < 256 
          then 
            readedBytes <- reader.ReadBytes(2)
          else
            readedBytes <- [||]
    
    match readedCount with 
    | c when c < 256 && readedBytes.Length < 2 -> failwith "error reading dictionary (incomplete length)"
    | c when c > 256 -> failwith "error reading dictionary (more then 256)"
    | c -> List.rev result;
    
      
let decompress (lst : (int * int)  list) = 
    let maxLengthCode = List.fold(fun mx (stage, num) -> if mx > stage then mx else stage) 0 lst
    
    let codesCount = List.fold(fun d (stage, num) -> if (stage <> 0) 
                                                       then 
                                                         CollectionUtil.addToDictionaryNum d (+) stage num 
                                                       else 
                                                         d ) (new Dictionary<int, int>()) lst
    let codesCountForStage key = if codesCount.ContainsKey key then codesCount.[key] else 0   
                                                               
    let collectStartCodeNums = List.rev [1..maxLengthCode - 1]
                               |> List.fold (fun ((x :: xs) as lst) stage -> ((x + codesCountForStage(stage + 1)) >>> 1):: lst ) (0 ::[]) 
                               
    
    let uncollapse = List.fold(fun l (stage, num) -> (List.replicate num stage) @ l) [] lst
                     |> List.rev
                     |> List.fold (fun l el -> ((byte)l.Length, el) :: l) [] 
    
    let constructAlphabetDictionary = 
      List.fold(fun d (letter, codeLength) -> if codeLength <> 0
                                                then CollectionUtil.addToDictionaryNum d (@) codeLength [letter]
                                                else d) (new Dictionary<int, byte list>()) uncollapse 
    
    let addCodes (d : Dictionary<int list, byte>) (stage : int) (letters : byte list) = 
      List.sort letters
      |> List.fold (fun ((dic, num) : (Dictionary<int list, byte>* int)) letr -> dic.Add (ListBinaryConversion.constructNumber (stage, num + collectStartCodeNums.[stage - 1]) [], letr)
                                                                                 (dic, num + 1)) (d, 0)
      |> LangUtils.first
    
    Seq.fold (fun d (el : KeyValuePair<int, byte list>) -> addCodes d el.Key el.Value) (new Dictionary<int list, byte>()) constructAlphabetDictionary
    
      
let uncollapse reader= 
  readList reader
  |> decompress
        
    
                        
    
    
    
    
    
