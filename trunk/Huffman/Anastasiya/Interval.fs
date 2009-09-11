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
let length (Interval(l, t) : uint32 Interval) = int64(t - l) + 1L
let initialInterval = Interval(ZERO, TOP_VALUE)

let contract (coomonWeight : uint32 ) (Interval(low1, top1) as i: uint32 Interval) (Interval(low2, top2) as i1 : uint32 Interval) =
    Interval(low1 + uint32(length i * int64(low2)/ int64(coomonWeight)), low1 - 1u + uint32(length i * int64(top2)/int64(coomonWeight)))
    
let doubling (Interval(low, top) : uint32 Interval) = 
    Interval(2u * low, 2u * top + 1u)
    

let div (commonWeight : uint32)(Interval(lc, tc) as current : uint32 Interval) (Interval(low, top) as symbol : uint32 Interval) =
    Interval(uint32(int64(lc) + int64(low) * (length current)/ int64(commonWeight)),uint32(int64(lc) + int64(top) * (length current)/ int64(commonWeight))-1u)  

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
     let makeInterval (weight : uint32) = Interval(weight, weight + dic.[bt])
     countSymbolWeight bt dic
     |> makeInterval

  let convert (newDict : Dictionary<byte, uint32 Interval>) (dict : Dictionary<byte, uint32>) = 
    Seq.fold (fun (a : Dictionary<byte, uint32 Interval>)  (pair : KeyValuePair<byte, uint32>) -> 
                a.Add(pair.Key, countCompressiveInterval pair.Key dict)
                a) newDict  dict   

  convert (new Dictionary<byte, uint32 Interval>()) d
  