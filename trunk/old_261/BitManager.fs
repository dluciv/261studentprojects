open System.IO
// Тут описана структура, позволяющая получать по одному биту из потока. Если поток кончается, то получаем нули

let BUFFER_SIZE = 2

type BitManager = Manager of int list * BinaryReader

let empty reader = Manager([], reader)

let readNext (reader : BinaryReader) = 
    let read = reader.ReadBytes(BUFFER_SIZE)
               |> List.of_array
               |> List.rev
               |> LangUtils.convertToBits

    Manager(read, reader)

let rec nextBit (Manager(lst, reader) : BitManager) = 
    let notEmpty (Manager(l, _) as m :BitManager) = 
        match l with
        | [] -> Manager(List.replicate 8 0, reader)
        | _ -> m
    match lst with
    | [] -> readNext reader
            |> notEmpty
            |> nextBit
    | (x :: xs) ->(x, Manager(xs, reader))
    
let construct reader lst = Manager(lst, reader)    