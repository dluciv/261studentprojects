#light
// В случае с записью словаря в арифметическом кодировании: каждый код можент принимать значение uint32, то
// есть в диапазоне до 2^31 - 1, таким образом на каждый код отводится 4 байта. Весь словарь в общей сложности 
// должен иметь длину 256 * 4 = 1024 байта. Далее мы хотим сжать его по RLE и переводим в отдельные байты, 
// которых, собственно, есть 1024. Таким образом, в вырожденном случае, если словарь полностью состоит из 0ей
// у нас должна быть запись типа 0 1024. Очевидно, что первое число записывается как байт, а вот на второе число
// надо отвести не меньше 10 битов. Таким образом берем int16 для кодирования второго числа.

open System.IO;
open System.Collections;
open System.Collections.Generic;

let BYTE_SIZE = 1
let INT16_SIZE = 2

let STANDART_RLE_SEGMENT = 2 * BYTE_SIZE
let RLE32_SEGMENT = BYTE_SIZE + INT16_SIZE
let STANDART_RLE_LENGTH = 256
let RLE32_LENGTH = 1024s

// "Схлопывает" список из 256ти значений по алгоритму RLE
let collapse (lst : 'a list) = 
    List.fold (fun res num -> 
                 match res with
                 |((a, b) :: xs) when a = num -> (a, b + 1) :: xs
                 |l -> (num, 1) :: l
               ) [] lst
    
let collapse32 (lst : uint32 list) = 
  let convertToByteList (value : uint32) =
      LangUtils.uintToBytes value 0 []
  List.fold (fun res nxt -> res @ (convertToByteList nxt)) [] lst
  |>List.fold (fun res num -> 
                 match res with
                 |((a, b) :: xs) when a = num -> (a, b + 1s) :: xs
                 |l -> (num, 1s) :: l
               ) []
          
let readList (reader : BinaryReader) = 
    let mutable readedBytes : byte array = reader.ReadBytes(STANDART_RLE_SEGMENT)
    let mutable result = []
    let mutable readedCount = 0
    
    while(readedBytes.Length = STANDART_RLE_SEGMENT) do
        result <- ((int)readedBytes.[0], (int)readedBytes.[1]) :: result
        readedCount <- readedCount + (int)readedBytes.[1]
        if readedCount < STANDART_RLE_LENGTH 
          then 
            readedBytes <- reader.ReadBytes(STANDART_RLE_SEGMENT)
          else
            readedBytes <- [||]
    
    match readedCount with 
    | c when c < STANDART_RLE_LENGTH && readedBytes.Length < STANDART_RLE_SEGMENT -> failwith Messages.errorReadingDictionary
    | c when c > STANDART_RLE_LENGTH -> failwith Messages.errorReadingDictionary
    | c -> List.rev result;
    
let readList32 (reader : BinaryReader) = 
    let mutable readedBytes : byte array = reader.ReadBytes(RLE32_SEGMENT)
    let mutable result = []
    let mutable readedCount = 0s
    
    let constructInt16 a = 
        List.of_array a
        |> LangUtils.takeFromNth 1
        |> LangUtils.constructInt16
    
    while(readedBytes.Length = RLE32_SEGMENT) do
        result <- ((byte)readedBytes.[0], constructInt16 readedBytes) :: result
        readedCount <- readedCount + (constructInt16 readedBytes)
        if readedCount < RLE32_LENGTH 
          then 
            readedBytes <- reader.ReadBytes(RLE32_SEGMENT)
          else
            readedBytes <- [||]
    
    match readedCount with 
    | c when c < RLE32_LENGTH && readedBytes.Length < RLE32_SEGMENT -> failwith Messages.errorReadingDictionary
    | c when c > RLE32_LENGTH -> failwith Messages.errorReadingDictionary
    | c -> List.rev result;
    
            
let decompress32 (lst : (byte * int16) list) = 
    let uncollapse_ = 
        List.fold (fun res ((nxtByte, nxtInt) : (byte * int16)) -> res @ (List.replicate (int32(nxtInt)) nxtByte)) []
    
    
    let rec makeInt32s lst (res : uint32 list) = 
      match lst with
      | [] -> res
      | xs  -> LangUtils.take 4 xs                  
               |> LangUtils.constructUint32
               |> LangUtils.toList
               |> (@) res
               |> makeInt32s (LangUtils.takeFromNth 4 lst)
    
    let dic = new Dictionary<byte, uint32>()
    makeInt32s (uncollapse_ lst) []
    |> List.fold (fun res nxt ->if nxt > 0u 
                                  then dic.Add(res, nxt) 
                                  else ()
                                res + 1uy) 0uy 
    dic         
            
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
        
let uncollapse32 reader= 
  readList32 reader
  |> decompress32
        
let writeRLE32 (lst : (byte * int16) list) (writer : FileStream)= 
  let rec int16ToBytes (v : int16) num  res= 
    match num with
    | 2 -> res
    | x -> (byte)(v >>> (8 * x)) :: res
            |> int16ToBytes v (x + 1)
  let writePair ((bt, value) : (byte * int16)) =
    writer.WriteByte bt
    writer.Write(List.to_array (int16ToBytes value 0 []), 0, 2)                 

  List.fold (fun _ nxt -> writePair nxt) () lst
               
    
                        
    
    
    
    
    
