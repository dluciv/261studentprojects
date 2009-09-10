open System.Collections.Generic
open System

// "Интервал" для арифметического сжатия. То есть можно оперирвоать не с двумя занчениями (верхним и нижним), а с 
// интервалом

let ZERO = 0u
let BIT_COUNT = 32

let TOP_VALUE = uint32((1L <<< BIT_COUNT) - 1L)
let I_FIRST_QUATR = TOP_VALUE / 4u + 1u
let I_HALF = 2u * I_FIRST_QUATR 
let I_THIRD_QUATR = 3u * I_FIRST_QUATR

type 'a Interval = Interval of 'a * 'a

let low<'a> (Interval(l, t) : 'a Interval) = l    
let top<'a> (Interval(l, t) : 'a Interval) = t    
let length (Interval(l, t) : uint32 Interval) = double(t - l) + 1.0
let initialInterval = Interval(ZERO, TOP_VALUE)

let contract (Interval(low1, top1) as i: uint32 Interval) (Interval(low2, top2) as i1 : double Interval) =
    Interval(low1 + (uint32)(length i * low2), low1 + (uint32)(length i * top2 - 1.0))
    
let doubling (Interval(low, top) : uint32 Interval) = 
    Interval(2u * low, 2u * top + 1u)
    
let mult (value : double)(Interval(low, top) as i : uint32 Interval) =
    Interval(value * (double low), value * (double top))  
let div (value : double)(Interval(low, top) as i : uint32 Interval) =
    Interval( value / (double low) ,value / (double top))  

let inInteval (value : uint32) (Interval(low, top) : uint32 Interval) = 
    low <= value && top > value


let makeIntervalDictionary  (d : Dictionary<byte, uint32>)= 
  let countSymbolWeight (bt : byte) = 
    Seq.fold (fun result (pair : KeyValuePair<byte, uint32>) -> 
                  if pair.Key < bt 
                    then result + pair.Value
                    else result) 0u   
                    
  let countSummWeight = 
    Seq.fold (fun result (pair : KeyValuePair<byte, uint32>) -> result + pair.Value) 0u   
                    
  let countCompressiveInterval (bt : byte) (dic : Dictionary<byte, uint32>) = 
     let makeInterval (weight : uint32) = Interval((double)weight / (double)(countSummWeight dic), double(weight + dic.[bt]) / (double)(countSummWeight dic))
     countSymbolWeight bt dic
     |> makeInterval

  let convert (newDict : Dictionary<byte, double Interval>) (dict : Dictionary<byte, uint32>) = 
    Seq.fold (fun (a : Dictionary<byte, double Interval>)  (pair : KeyValuePair<byte, uint32>) -> 
                a.Add(pair.Key, countCompressiveInterval pair.Key dict)
                a) newDict  dict   

  convert (new Dictionary<byte, double Interval>()) d