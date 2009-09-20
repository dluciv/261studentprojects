open System.IO
open System.Collections.Generic

let BIT_BUFFER_SIZE = 16


type BitBuffer = Buffer of int * byte list

// Это надо для "отсечения" байтов на запись от буффера
let convertBufferToByteList (Buffer(lngth, lst) as buf : BitBuffer) = 
    let cutByte = LangUtils.take 8
    let cutNonByte = LangUtils.takeFromNth 8
    let constructNumber = List.fold (fun a nxt -> (a <<< 1) + nxt) 0uy

    let rec convert_ (source : byte list) (result : byte list) = 
        match source with
        |[] -> result
        |xs when List.length xs >= 8 -> cutByte xs
                                        |> constructNumber
                                        |> LangUtils.toList
                                        |> (@) result
                                        |> convert_ (cutNonByte xs)
        |xs -> List.replicate (8 - List.length xs) 0uy
               |> List.append xs 
               |> constructNumber
               |> LangUtils.toList               
               |> (@) result               
                                                
                                        
    convert_ lst []

let flush (filestream : FileStream) (Buffer(lngth, lst) as buf : BitBuffer) =     
    let toWrite = buf |> convertBufferToByteList |> List.to_array
    match lst.Length with
    | x when x >= lngth -> filestream.Write(toWrite, 0, Array.length toWrite)
                           Buffer(lngth, [])   
    | _ -> buf
    
let close (filestream : FileStream) (Buffer(lngth, lst) as buf : BitBuffer) =     
    let toWrite = buf |> convertBufferToByteList |> List.to_array
    filestream.Write(toWrite, 0, Array.length toWrite)

let rec insert (filestream : FileStream) someBit  (Buffer(lngth, lst) as buf : BitBuffer) = 
    match List.length lst with
    | x when x >= lngth -> flush filestream buf
                           |> insert filestream someBit
    | x -> Buffer(lngth, lst @ [someBit])
    
    
// Сброс известных битов в файл и дозапись "следующих инфертированных"    
let rec dumpFollowed (dumpBit : (byte -> BitBuffer -> BitBuffer)) bit followed b =
  match followed, bit with
  | 0, _ -> b
  | x, 0uy -> dumpBit 1uy b
              |> dumpFollowed dumpBit 0uy (x - 1)
  | x, 1uy -> dumpBit 0uy b
              |> dumpFollowed dumpBit 1uy (x - 1)
  | _, _ -> b
      
// Процесс сужения интервала (при декодировании)    
let rec dump (Interval.Interval(low, top) as interv : uint32 Interval.Interval) (followed : int) 
  (dumpBit : (byte -> BitBuffer -> BitBuffer)) (buf : BitBuffer) = 
    match low, top with
    |l, t when t < Interval.I_HALF ->  dumpBit 0uy buf
                                       |> dumpFollowed dumpBit 0uy followed
                                       |> dump (Interval.doubling interv) 0 dumpBit
    |l, t when l >= Interval.I_HALF -> buf
                                       |> dumpBit 1uy 
                                       |> dumpFollowed dumpBit 1uy followed
                                       |> dump (Interval.doubling (Interval.Interval(low - Interval.I_HALF, top - Interval.I_HALF))) 0 dumpBit
    |l, t when l >= Interval.I_FIRST_QUATR && t < Interval.I_THIRD_QUATR -> 
        buf 
        |> dump (Interval.doubling (Interval.Interval(low - Interval.I_FIRST_QUATR, top - Interval.I_FIRST_QUATR))) (followed + 1) dumpBit
    |_, _ -> (interv, buf, followed)                                                          
                                                              
                                     