#light

open System.IO
open System.Collections.Generic


let EXACTNESS = 4

    
type 'a Interval = Interv of 'a * 'a
let UNIT_INTERVAL = Interv(BigFloat.ZERO, BigFloat.ONE)

let firstPartsEquals (Interv(i1, i2) : BigFloat.BigFloat Interval) = 
    (BigFloat.equalParts i1 i2).Length > 0
    
    
    
let firstPart (Interv(i1, i2) : BigFloat.BigFloat Interval) = 
     BigFloat.equalParts i1 i2
    
//let dumpFirstPart (Interv(i1, i2) : float Interval) = 
//    Interv(i1 * 10.0 - round i1, i2 * 10.0 - round i2)    
// 
 
let writeToFile (bf : ByteBuffer.ByteBuffer)(writer : FileStream) (l : int list) = 
    ByteBuffer.flush (ByteBuffer.add bf l)  writer

    
let dumpConstantPart (Interv(i1, i2) : BigFloat.BigFloat Interval) (bf : ByteBuffer.ByteBuffer) (writer : FileStream) = 
  let constPart = BigFloat.equalParts i1 i2
  (writeToFile bf writer constPart,
  Interv(BigFloat.startFromNth (constPart.Length) i1, BigFloat.startFromNth (constPart.Length) i2))
//  let rec dump_ ((l, interv) : byte list * float Interval) = 
//     if firstPartsEquals interv
//       then 
//         dump_ ((firstPart interv) :: l, dumpFirstPart interv)
//       else 
//         (l, interv)
//         
//  dump_ ([], i)
//  |> writeToFile bf writer
         
let contract (Interv(by1, by2) : BigFloat.BigFloat Interval) (Interv(i1, i2) : BigFloat.BigFloat Interval) = 
    let first = BigFloat.minus i2 i1
                |> BigFloat.printFloat
                |> BigFloat.mult by1
                |> BigFloat.printFloat
                |> BigFloat.plus i1
                |> BigFloat.printFloat
    let second = BigFloat.minus i2 i1
                |> BigFloat.printFloat
                |> BigFloat.mult by2
                |> BigFloat.printFloat
                |> BigFloat.plus i1
                |> BigFloat.printFloat
    printf "\n-----\n"
    printf "START : ["
    BigFloat.printFloat i1
    printf ", "
    BigFloat.printFloat i2
    printf ") \n"
    printf "START (by): ["
    BigFloat.printFloat by1
    printf ", "
    BigFloat.printFloat by2
    printf ") \n"
    printf "END: ["
    BigFloat.printFloat first
    printf ", "
    BigFloat.printFloat second
    printf ") \n"
    printf "\n-----\n"
    Interv(first, second)


let makeIntervalByDistribution (Interv(prev1, prev2) : BigFloat.BigFloat Interval) (distr : int) (allCount : int) = 
    let second = BigFloat.divideInts distr allCount EXACTNESS
                 |> BigFloat.plus prev2
    Interv(prev2, second)


let printInterval (Interv(i1, i2) : 'a Interval) = 
    printf "["
    print_any i1
    printf ", "
    print_any i2
    printf ")"

let printIntervalSeq (d : Dictionary<byte, BigFloat.BigFloat Interval>)=
    Seq.fold(fun _ (kv : KeyValuePair<byte, BigFloat.BigFloat Interval>) -> print_any kv.Key
                                                                            printf "\t"
                                                                            printInterval kv.Value) () d
    d                                                                        

let makeIntervalDictionary (dic : Dictionary<byte, int>) = 
    let count = Seq.fold (fun num (kv : KeyValuePair<byte, int>) -> num + kv.Value) 0 dic
    print_any dic
    
    Seq.sortBy (fun (kv : KeyValuePair<byte, int>) -> kv.Key) dic
    |> Seq.fold (fun (((bt, interv) as x) :: xs) (kv : KeyValuePair<byte, int>) -> (kv.Key, (makeIntervalByDistribution interv kv.Value count)) :: x :: xs) ((0uy, Interv(BigFloat.ZERO, BigFloat.ZERO)) :: [] )
    |> List.rev
    |> LangUtils.takeFromNth 1
    |> Seq.fold (fun (d : Dictionary<byte, BigFloat.BigFloat Interval>) (bt, interv) -> d.Add(bt, interv)
                                                                                        d) (new Dictionary<byte, BigFloat.BigFloat Interval>())
    |> printIntervalSeq                                                                                    
                                                                            
         
         
let dumpInterval(Interv(i1, i2) as i : BigFloat.BigFloat Interval) (bf : ByteBuffer.ByteBuffer)= 
    ByteBuffer.add bf (BigFloat.dump i1)