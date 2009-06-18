#light

open System.IO
open System.Collections
open System.Collections.Generic


let READER_BUFFER_SIZE = 10
let LISTBUFFER_SIZE = 1



    
// Для отладки    
let printTemp (d : 'a) = 
    print_any d
    printf "\n"
    d

// Функция с очень оригинальным названием на самом деле делает второй проход считывания по файлу и
// затем записывает в буфер и закрывает его сначала словарь, а затем все содержимое файла на основе словаря.
let writeToFile (filename : string) (resultName : string) (dic : Dictionary<byte, (int * int)>)  = 
    
    // Высчитывает максимальный уровень в дереве
    let countMaxStage = 
        let getFirstValue (p : (int * int)) = 
            match p with 
            | (a, b) -> a
        let max a b = if a > b then a else b
        Seq.fold (fun m (p : KeyValuePair<byte, (int * int)>) -> max (getFirstValue p.Value) m) 0 dic 

    // Производит одновременное считывание и записывание результатов в новый файл. По сути выполняет основную рвботу,
    // лежащую на головной функции
    let rec readWrite (streamReader  : BinaryReader) (buffer : ListBuffer.ListBuffer) (fileStream : FileStream)= 
        using (fileStream) (fun writer ->
            using (streamReader) (fun reader ->
                let mutable currentBuffer = ListBuffer.writeDictionary dic buffer writer
                let mutable currentBytes = reader.ReadBytes(READER_BUFFER_SIZE)
                while(currentBytes.Length > 0) do 
                  for bt in currentBytes do
                    currentBuffer <- ListBuffer.insert currentBuffer dic.[bt] writer
                  currentBytes <- reader.ReadBytes(READER_BUFFER_SIZE)
                currentBuffer
            )
            |>ListBuffer.fillBufferToEnd countMaxStage
            |>ListBuffer.closeBuffer  writer
        )
    readWrite (new BinaryReader(File.Open(filename, FileMode.Open))) (ListBuffer.constructEmptyBuffer LISTBUFFER_SIZE) (new FileStream(resultName, FileMode.Create))


let encode (filename : string) (resultName : string) = 
   HuffmanTree.makeHuffmanTree 
   >> CanonicalHuffmanTreeConverter.makeCamonicalRepresentation
   >> writeToFile filename resultName

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
     


// Собственно тест.
readAndBuildTree("e:\\file.txt")  ("e:\\result.txt") (new Dictionary<byte HuffmanTree.Tree, int>())



