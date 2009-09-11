#light
open System.IO
open System.Collections.Generic


let BUFFER_SIZE = 4

// Кодирует бит, сжимает интервал и начинает процесс "сброса" стационарных символов в файл и махинаций с интервалом
let encode (bt : byte) (interval : uint32 Interval.Interval) commonWeight (dic : Dictionary<byte, uint32 Interval.Interval>) = 
    dic.[bt]
    |> Interval.contract commonWeight interval 
    |> BitBuffer.dump 
    
    
    
// Процесс чтения и кодирования
let read (reader : BinaryReader) (filestream : FileStream) (dic : Dictionary<byte, uint32 Interval.Interval>) =
  let mutable readedbutes = reader.ReadBytes(BUFFER_SIZE)
  let mutable buf = BitBuffer.Buffer(BitBuffer.BIT_BUFFER_SIZE, [])
  let mutable currentInterval = Interval.initialInterval
  let mutable followedBits = 0
  let commonWeight = CollectionUtil.commonWeight dic
  
  
  let encodeBit bit interval dict followed buffer = 
     encode bit interval commonWeight dict followed (BitBuffer.insert filestream) buffer
     
  let endEncoding (Interval.Interval(low, top) as i : uint32 Interval.Interval) followed (dumpBit : (byte -> BitBuffer.BitBuffer -> BitBuffer.BitBuffer)) buffer = 
     match low with
     | x when x < Interval.I_FIRST_QUATR -> dumpBit 0uy buffer
                                            |> BitBuffer.dumpFollowed dumpBit 0uy (followed + 1)
     | _ -> dumpBit 1uy buffer
            |> BitBuffer.dumpFollowed dumpBit 1uy followed

  while(readedbutes.Length > 0) do
    for bt in readedbutes do
      let res = encodeBit bt currentInterval dic followedBits buf
      currentInterval <- (fun ((a, b, c) : (uint32 Interval.Interval * BitBuffer.BitBuffer* int)) -> a) res
      buf <- (fun ((a, b, c) : (uint32 Interval.Interval * BitBuffer.BitBuffer* int)) -> b) res
      followedBits <- (fun ((a, b, c) : (uint32 Interval.Interval * BitBuffer.BitBuffer* int)) -> c) res
    readedbutes <- reader.ReadBytes(BUFFER_SIZE)

  buf <- endEncoding currentInterval (followedBits + 1) (BitBuffer.insert filestream) buf
  BitBuffer.close filestream buf
  filestream.Flush()
      

// Процесс считывания словаря из файла      
let readInitialDictionary (reader : BinaryReader) = 
  let mutable readedbutes = reader.ReadBytes(BUFFER_SIZE)
  let dic = Dictionary<byte, uint32>()
  while(readedbutes.Length > 0) do
    for bt in readedbutes do
      CollectionUtil.addToDictionaryNum dic (fun a b -> a + b) bt 1u
    readedbutes <- reader.ReadBytes(BUFFER_SIZE)
  dic    
      
      


// Конкретно разбирает способы расширения(при декодировании) в зависимости от того, какой интервал на данную секунду         
let rec dumpBits (manager : BitManager.BitManager) (value : uint32) (Interval.Interval(low, top) as interv : uint32 Interval.Interval) = 
    match low, top with
    | _, x when x < Interval.I_HALF ->
        let res = BitManager.nextBit manager
        interv 
        |> Interval.doubling
        |> dumpBits ((fun (a, b) -> b) res) (2u * value + uint32((fun (a, b) -> a) res))
    | x, _ when x >= Interval.I_HALF -> 
        let res = BitManager.nextBit manager
        Interval.Interval(low - Interval.I_HALF, top - Interval.I_HALF)
        |> Interval.doubling
        |> dumpBits ((fun (a, b) -> b) res) (2u * (value - Interval.I_HALF) + uint32((fun (a, b) -> a) res))
    | x, y when x >= Interval.I_FIRST_QUATR && y < Interval.I_THIRD_QUATR -> 
        let res = BitManager.nextBit manager
        Interval.Interval(low - Interval.I_FIRST_QUATR, top - Interval.I_FIRST_QUATR)
        |> Interval.doubling
        |> dumpBits ((fun (a, b) -> b) res) (2u * (value - Interval.I_FIRST_QUATR) + uint32((fun (a, b) -> a) res))
    | _, _ -> (value, interv, manager)

// Читает файл и декодирует      
let decode reader (writer : FileStream)(readDict : (BinaryReader -> Dictionary<byte, uint32>))   =

    let startDict = readDict reader
    let dic = CollectionUtil.convertWeights (new Dictionary<byte, uint32 Interval.Interval>()) startDict
    let dicLength = CollectionUtil.dicLength dic
     
    // Расширение интервала          
    let resize  ((value, interv, manager) as pair  : (uint32 * uint32 Interval.Interval * BitManager.BitManager)) (decoded : byte) = 
        dic.[decoded]
        |> Interval.div dicLength interv
        |> dumpBits manager value
     
    // Находим символ и расширяем интервал              
    let decodeSymbol ((value, interv, manager) as pair  : (uint32 * uint32 Interval.Interval* BitManager.BitManager)) (w : byte -> byte) = 
      uint32(((int64((value - Interval.low<uint32> interv)) + 1L) * int64(dicLength) - 1L )/ Interval.length interv)
      |> CollectionUtil.find dic
      |> w
      |> resize pair
    
    let mutable cutrrentInterval = Interval.initialInterval
    let readedBytes = reader.ReadBytes((Interval.BIT_COUNT + 1) / 8)
                      |> List.of_array
                      |> LangUtils.addNullBytes
                      |> LangUtils.constructUint32
    let mutable lengthCounter = 0u
    let writing bt = writer.WriteByte(bt)
                     bt
    let mutable manager = BitManager.empty reader
    // Собственно сам алгоритм    
    let mutable res = decodeSymbol (readedBytes, cutrrentInterval, manager) writing
    lengthCounter <- lengthCounter + 1u
    while(lengthCounter < dicLength) do
      res <- decodeSymbol res writing 
      lengthCounter <- lengthCounter + 1u
    writer.Flush()  
 
// Декодирует архив      
let decodeArchive input output = 
    using(new BinaryReader(File.Open(input, FileMode.Open)))
      (fun reader -> using (new FileStream(output, FileMode.Create)) 
                       (fun writer -> decode reader writer RLE.uncollapse32))

// Кодирует файл в архив
let encodeArchive input output = 
    let dic = using(new BinaryReader(File.Open(input, FileMode.Open))) (fun reader -> readInitialDictionary reader)
    let writeDictionary = 
      List.fold (fun res num -> if dic.ContainsKey num 
                                  then 
                                    (dic.[(byte)num]) :: res 
                                  else 
                                    0u :: res) [] [(byte)0..(byte)255]
      |> RLE.collapse32
      |> RLE.writeRLE32
      
    using(new BinaryReader(File.Open(input, FileMode.Open)))
      (fun reader -> using (new FileStream(output, FileMode.Create)) 
                       (fun writer -> writeDictionary writer
                                      read reader writer (Interval.makeIntervalDictionary dic)))
