#light

open System.IO
open System.Collections
open System.Collections.Generic


let READER_BUFFER_SIZE = 2
let LISTBUFFER_SIZE = 1



    
// Функция с очень оригинальным названием на самом деле делает второй проход считывания по файлу и
// затем записывает в буфер и закрывает его сначала словарь, а затем все содержимое файла на основе словаря.
let writeToFile (filename : string) (resultName : string) (hTree : Dictionary<byte HuffmanTree.Tree, int>) (dic : Dictionary<byte, (int * int)>)  = 
    
    // Производит одновременное считывание и записывание результатов в новый файл. По сути выполняет основную рвботу,
    // лежащую на головной функции
    let rec readWrite (streamReader  : BinaryReader) (buffer : ListBuffer.ListBuffer) (fileStream : FileStream)= 
        using (fileStream) (fun writer ->
            using (streamReader) (fun reader ->
                // Записываем количество используемых битов в последнем байте в начало файла
                let mutable currentBuffer = ListBuffer.writeDictionary dic buffer writer     
                                            |> ListBuffer.writeLastByteSize hTree dic writer
                // Начинаем чтение        
                let mutable currentBytes = reader.ReadBytes(READER_BUFFER_SIZE)
                while(currentBytes.Length > 0) do 
                  for bt in currentBytes do
                    currentBuffer <- ListBuffer.insert currentBuffer dic.[bt] writer
                  currentBytes <- reader.ReadBytes(READER_BUFFER_SIZE)
                currentBuffer
            )
            |>ListBuffer.fillBufferToEnd 
            |>ListBuffer.closeBuffer writer
        )
    readWrite (new BinaryReader(File.Open(filename, FileMode.Open))) (ListBuffer.constructEmptyBuffer LISTBUFFER_SIZE) (new FileStream(resultName, FileMode.Create))


let encode (filename : string) (resultName : string) (d : Dictionary<byte HuffmanTree.Tree, int>) = 
   HuffmanTree.makeHuffmanTree d
   |> CanonicalHuffmanAlgorithm.makeCamonicalRepresentation 
   |> writeToFile filename resultName d 
   
// Делает первй проход считывания по файлу, строит словарь и затем строит на основе него дерево, совмещая 
// предыдущие функции создания и конвертирования для дерева хаффмана.
let readAndBuildTree (filename : string) (resultName : string) (d : Dictionary<byte HuffmanTree.Tree, int>)= 
    let rec read_ (streamReader  : BinaryReader) = 
        using (streamReader) (fun reader ->
          let mutable currentBytes = reader.ReadBytes(READER_BUFFER_SIZE)
          while (currentBytes.Length > 0) do
            for bt in currentBytes do
              CollectionUtil.addToDictionary d (HuffmanTree.makeLeaf bt)
            currentBytes <- reader.ReadBytes(READER_BUFFER_SIZE)
        ) 
    read_ (new BinaryReader(File.Open(filename, FileMode.Open)))
    encode filename resultName d
     


let readRLE (reader : BinaryReader) =
    RLE.uncollapse reader
    
let readLastByteSize (reader : BinaryReader) = 
    (int) (reader.ReadByte())

let writeDecodedByte (toWrite : byte list)(writer : FileStream) = 
    List.fold (fun i bt -> writer.WriteByte bt
                           i) () toWrite
                
    

let decodeFile (reader : BinaryReader) (writer : FileStream) (d : Dictionary<int list, byte>) = 
    let maxCode = Seq.fold (fun max (el : KeyValuePair<int list, byte>) -> if max < el.Key.Length 
                                                                             then el.Key.Length
                                                                             else max ) 0 d

    let lastByte = 
      match (readLastByteSize reader) with
        | 8 -> LangUtils.BYTE_SIZE
        | x -> LangUtils.BYTE_SIZE - x
      
    let mutable readedBytes = reader.ReadBytes(READER_BUFFER_SIZE)
    let mutable byteSequence = [];
    let mutable currentSequence = [];
    let mutable decodedBytes = [];
    while (readedBytes.Length > 0) do
      for bt in readedBytes do
        byteSequence <- byteSequence @ ListBinaryConversion.constructNumber (LangUtils.BYTE_SIZE, (int)bt) []
      for bit in (LangUtils.take (byteSequence.Length - (int)lastByte) byteSequence) do
        currentSequence <- currentSequence @ [bit]
        if d.ContainsKey currentSequence 
          then decodedBytes <- decodedBytes @ [d.[currentSequence]]
               currentSequence <- [] 
               if decodedBytes.Length > 4 
                 then writeDecodedByte decodedBytes writer
                      decodedBytes <- []
                 else ()
          else if currentSequence.Length > maxCode
                 then writeDecodedByte decodedBytes writer
                      writer.Flush()
                      exit 0
                 else ()    
      byteSequence <- LangUtils.takeFromNth (byteSequence.Length - (int)lastByte) byteSequence
      readedBytes <- reader.ReadBytes(READER_BUFFER_SIZE)
    writeDecodedByte decodedBytes writer
    writer.Flush()
        
              
      
              
      
        
    
let decodeArchive (archiveName : string) (resultName : string)= 
    using(new BinaryReader(File.Open(archiveName, FileMode.Open)))
      (fun reader -> using (new FileStream(resultName, FileMode.Create)) 
                       (fun writer ->readRLE reader
                                     |> decodeFile reader  writer))
       
let testEquals (reader1 : BinaryReader) (reader2 : BinaryReader) = 
    let mutable readedBytes1 = reader1.ReadBytes(READER_BUFFER_SIZE)
    let mutable readedBytes2 = reader2.ReadBytes(READER_BUFFER_SIZE)
    let mutable equals = true;
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
        else equals <- false
    equals && readedBytes2.Length = readedBytes1.Length

let fileEquals  (filename : string) (decodedName : string) = 
    using(new BinaryReader(File.Open(filename, FileMode.Open)))
        (fun reader -> using(new BinaryReader(File.Open(decodedName, FileMode.Open)))
                          (fun reader2 -> testEquals reader reader2))

       
let makeArchive input output = 
    readAndBuildTree input output (new Dictionary<byte HuffmanTree.Tree, int>())