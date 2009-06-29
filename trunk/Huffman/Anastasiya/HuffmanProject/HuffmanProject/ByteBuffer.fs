#light

open System.IO


type ByteBuffer = ByteBuf of byte list * int

let createEmpty size = ByteBuf([], size)

let add (ByteBuf(lst, size)  : ByteBuffer) (l : int list) =  ByteBuf (List.append lst (LangUtils.intListToByteList l), size)                                                              

let addByte (ByteBuf(lst, size) : ByteBuffer) (b : byte) = ByteBuf(lst @ [b], size)

let flush (ByteBuf(lst, size) as bf : ByteBuffer) (writer : FileStream)= 
    match lst.Length with
    | x when x > size -> LangUtils.write writer lst
                         ByteBuf([], size)
    | _ -> bf

let close (ByteBuf(lst, size) : ByteBuffer) (writer : FileStream) = 
    printf "dump all"
    print_any lst
    LangUtils.write writer lst
