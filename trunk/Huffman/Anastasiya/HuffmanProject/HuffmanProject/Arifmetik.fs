#light
open System.IO
open System.Collections.Generic


let REDING_BUFFER_SIZE =  2

let printInterval (ArithmeticalAlgorithm.Interv(i1, i2) : BigFloat.BigFloat ArithmeticalAlgorithm.Interval) = 
    printf "["
    BigFloat.printFloat i1
    printf ", "
    BigFloat.printFloat i2
    printf ")"

let printIntervalDictionary (dic : Dictionary<byte, BigFloat.BigFloat ArithmeticalAlgorithm.Interval>) = 
    Seq.fold (fun s (kv : KeyValuePair<byte, BigFloat.BigFloat ArithmeticalAlgorithm.Interval>) -> print_any kv.Key 
                                                                                                   printf "\t" 
                                                                                                   printInterval kv.Value
                                                                                                   printf "\n"
                                                                                                   s) 0 dic
    dic


let readAndMakeDictionary (reader : BinaryReader) = 
    let mutable readedBytes = reader.ReadBytes(REDING_BUFFER_SIZE)
    let mutable dic = new Dictionary<byte, int>()
    
    while (readedBytes.Length > 0) do
      for bt in readedBytes do
        if dic.ContainsKey bt 
          then
            dic.[bt] <- dic.[bt] + 1
          else
            dic.Add(bt, 1)
      readedBytes <- reader.ReadBytes(REDING_BUFFER_SIZE)
    dic
    
    
   

         
let printFloatInterval (ArithmeticalAlgorithm.Interv(i1, i2) : BigFloat.BigFloat ArithmeticalAlgorithm.Interval) = 
    printf "\n["
    BigFloat.printFloat i1         
    printf ", "
    BigFloat.printFloat i2         
    printf ")\n"
    
                                                                            
let readAndEncode (reader : BinaryReader) (writer : FileStream) (dic : Dictionary<byte, BigFloat.BigFloat ArithmeticalAlgorithm.Interval>) = 
    let mutable readedBytes = reader.ReadBytes(REDING_BUFFER_SIZE)
    let mutable currentInterval = ArithmeticalAlgorithm.UNIT_INTERVAL
    let mutable buffer = ByteBuffer.createEmpty 2
    while (readedBytes.Length > 0) do
      for bt in readedBytes do
        print_any bt
        let res = (ArithmeticalAlgorithm.contract dic.[bt] currentInterval
                  |> ArithmeticalAlgorithm.dumpConstantPart) buffer writer
        buffer <- LangUtils.first res
        currentInterval <- LangUtils.second res
        printFloatInterval currentInterval
      readedBytes <- reader.ReadBytes(REDING_BUFFER_SIZE)  
    (ArithmeticalAlgorithm.dumpInterval currentInterval buffer
    |>ByteBuffer.close) writer                                                                                      
        


let encodeToArchive (source : string) (dest : string) = 
    let dict = using (new BinaryReader(File.Open(source, FileMode.Open))) (fun reader -> readAndMakeDictionary reader
                                                                                         |> ArithmeticalAlgorithm.makeIntervalDictionary)
                                                                                         
    using (new BinaryReader(File.Open(source, FileMode.Open))) (fun reader -> 
        using (new FileStream(dest, FileMode.Create)) (fun writer -> readAndEncode reader writer dict))
                                                                     
                                                                     
encodeToArchive ("e:\\file.txt") ("e:\\result.txt")      


