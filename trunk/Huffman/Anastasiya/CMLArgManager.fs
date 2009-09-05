
type ArchiveType = Huffman | Alrifmetic
type FileName = string
type ActionType = Encode | Decode | Test
type ShowStats = Show | Hide
type Parameters = Params of ArchiveType * FileName * FileName * ActionType * ShowStats

let getArchiveType (Params(aT, _, _, _, _) : Parameters) = aT
let getInputFileName (Params(_, x, _, _, _) : Parameters) = x
let getOutputFileName (Params(_, _, x, _, _) : Parameters) = x
let getActionType (Params(_, _, _, x, _) : Parameters) = x
let isShowStats (Params(_, _, _, _, x) : Parameters) = x

let setArchiveType (Params(_, fn1, fn2, at, ss) : Parameters) (x : ArchiveType) = Params(x, fn1, fn2,at,ss)
let setInputFileName (Params(arc, _, fn2, at, ss) : Parameters) (x : FileName) = Params(arc, x, fn2,at,ss)
let setOutputFileName (Params(arc, fn1, _, at, ss) : Parameters) (x : FileName) = Params(arc, fn1, x,at,ss)
let setActionType (Params(arc, fn1, fn2, _, ss) : Parameters) (x : ActionType) = Params(arc, fn1, fn2,x,ss)
let setShowStats (Params(arc, fn1, fn2, at, _) : Parameters) (x : ShowStats) = Params(arc, fn1, fn2,at,x)


let defaultParameters = Params(Huffman, "input.txt", "output.txt", Encode, Hide)


let parse (arguments : string list) = 
  let parseArchiveType (t : string) = 
      match t with
      | "hf" -> Huffman
      | "ar" -> Alrifmetic
      | _ -> failwith "cmd error: no such algorithm type"
  let parseActionType (t : string) = 
      match t with
      | "encode" -> Encode
      | "decode" -> Decode
      | "test" -> Test
      | _ -> failwith "cmd error: no such action type"
  let rec parse_ (args : string list) (param : Parameters) =
      match args with
      | x :: y :: xs when x = "-algorithm" -> parseArchiveType y
                                              |> setArchiveType param
                                              |> parse_ xs 
      | x :: y :: xs when x = "-in" -> setInputFileName param y
                                       |> parse_ xs 
      | x :: y :: xs when x = "-out" -> setOutputFileName param y
                                        |> parse_ xs 
      | x :: y :: xs when x = "-action" -> parseActionType y
                                           |> setActionType param
                                           |> parse_ xs 
      | x :: xs when x = "-stat" -> setShowStats param Show
                                    |> parse_ xs                                          
      | [] -> param
      | _ -> failwith "cmd error : wrong command line arguments"
      
  parse_ (List.tl  arguments) defaultParameters                                        
                                           
                                              
                                              