open System.IO

let READER_BUFFER = 8

type ArchiveType = Huffman | Alrifmetic
type FileName = string
type ActionType = Encode | Decode | Test
type ShowStats = Show | Hide
type Parameters = Params of ArchiveType * FileName * FileName * ActionType * ShowStats
    with 
        member x.archiveType = match x with Params(x, _, _, _, _) -> x
        member x.inputFile = match x with Params(_, x, _, _, _) -> x
        member x.outputFile = match x with Params(_, _, x, _, _) -> x
        member x.actionType = match x with Params(_, _, _, x, _) -> x
        member x.showStat = match x with Params(_, _, _, _, x) -> x

let setArchiveType (Params(_, fn1, fn2, at, ss) : Parameters) (x : ArchiveType) = Params(x, fn1, fn2,at,ss)
let setInputFileName (Params(arc, _, fn2, at, ss) : Parameters) (x : FileName) = Params(arc, x, fn2,at,ss)
let setOutputFileName (Params(arc, fn1, _, at, ss) : Parameters) (x : FileName) = Params(arc, fn1, x,at,ss)
let setActionType (Params(arc, fn1, fn2, _, ss) : Parameters) (x : ActionType) = Params(arc, fn1, fn2,x,ss)
let setShowStats (Params(arc, fn1, fn2, at, _) : Parameters) (x : ShowStats) = Params(arc, fn1, fn2,at,x)


let defaultParameters = Params(Huffman, "input.txt", "output.txt", Encode, Hide)

let printWarnings (haveIn, haveOut) = 
    match haveIn, haveOut with
    | true, true -> printf "\n"
    | false, false -> Messages.warningNoInputFile()
                      |> Messages.warningNoOutputFile
    | true, false -> Messages.warningNoOutputFile()
    | false, true -> Messages.warningNoInputFile()

let printFile (reader : BinaryReader) = 
    let mutable readedBytes = reader.ReadChars(READER_BUFFER)
    while(readedBytes.Length > 0) do
        for c in readedBytes do
            printf "%c" c
        readedBytes <- reader.ReadChars(READER_BUFFER)

let printHelp readme = 
    using(new BinaryReader(File.Open(readme, FileMode.Open)))
      (fun reader -> printFile reader)


let parse (arguments : string list) = 
  let parseArchiveType (t : string) = 
      match t with
      | "hf" -> Huffman
      | "ar" -> Alrifmetic
      | _ -> failwith Messages.wrongAlgorithm
  let parseActionType (t : string) = 
      match t with
      | "encode" -> Encode
      | "decode" -> Decode
      | "test" -> Test
      | _ -> failwith Messages.wrongActionType
  let rec parse_ (args : string list) (haveIn, haveOut) (param : Parameters)  =
      match args with
      | x :: xs  when x = "-help" -> printHelp "readme.txt"
                                     exit(0)
      | x :: y :: xs when x = "-algorithm" -> parseArchiveType y
                                              |> setArchiveType param
                                              |> parse_ xs (haveIn, haveOut)
      | x :: y :: xs when x = "-in" -> setInputFileName param y
                                       |> parse_ xs (true, haveOut)
      | x :: y :: xs when x = "-out" -> setOutputFileName param y
                                        |> parse_ xs (haveIn, true)
      | x :: y :: xs when x = "-action" -> parseActionType y
                                           |> setActionType param
                                           |> parse_ xs (haveIn, haveOut)
      | x :: xs when x = "-stat" -> setShowStats param Show
                                    |> parse_ xs (haveIn, haveOut)                                         
      | [] -> printWarnings (haveIn, haveOut)
              param
      | _ -> failwith Messages.wrongCMDArgs
      
  parse_ (List.tl  arguments) (false, false) defaultParameters                                        
                                           
                                              
                                              