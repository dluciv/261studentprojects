#light

open System.Collections.Generic


let addToDictionary (d : Dictionary<'a, int>)(key : 'a) = 
    if d.ContainsKey key
        then 
            d.[key] <- d.[key] + 1
        else
            d.Add (key, 1)
            
let addToDictionaryNum (d : Dictionary<'a, 'b>) (addFun : ('b -> 'b -> 'b))(key : 'a) (num : 'b)= 
    if d.ContainsKey key
        then 
            d.[key] <- addFun d.[key] num
        else
            d.Add (key, num)
    d
            
let addToStage (d : Dictionary< int, (int* 'a list)>)(value : 'a) (stage : int) = 
    if d.ContainsKey stage 
        then 
            let (old_node, old_list) = d.[stage]
            let res = d.Remove stage
            d.Add (stage, (old_node,value :: old_list))
            d
        else
            d.Add (stage,(0, [value]))           
            d
let addNodeToStage (d : Dictionary< int, (int* 'a list)>)(stage : int) = 
    if d.ContainsKey stage 
        then 
            let (old_node, old_list) = d.[stage]
            let res = d.Remove stage
            d.Add (stage, (old_node + 1,old_list))
            d
        else
            d.Add (stage,(1, []))           
            d
            
            
let addCollection (d : Dictionary <'a, 'b>) (collection : seq<'a* 'b>) = 
    Seq.fold (fun (d :Dictionary<'a , 'b>) ((val1, val2) : ('a* 'b)) -> d.Add(val1, val2) 
                                                                        d) d collection            
                                                                        
                                                                        
// Аналог инструкции take. Почему-то я ее не нашла в классе List    
let takeFromList (num : int) (l : 'a list) = 
    LangUtils.take num l

//    |> Seq.fold (fun res el -> el :: res) []       


// Возвращает список без num первых элементов        
let rec removeFirstElements (num : int) (lst : 'a list) = 
    match num, lst with
    | n, l when n <= 0 -> l
    | n, (x :: xs) -> removeFirstElements(n - 1) xs
    | n, [] -> []

                                               
// Определяет верхнюю границу самого "верхнего" интервала в словаре. 
let dicLength (d : Dictionary<byte, uint32 Interval.Interval>) = 
    Seq.sortBy (fun (pair : KeyValuePair<byte, uint32 Interval.Interval>) -> pair.Key) d
    |> Seq.to_list
    |> LangUtils.takeLast
    |> (fun (pair : KeyValuePair<byte, uint32 Interval.Interval>) -> 
         pair.Value
         |> Interval.top<uint32>)
                                               
// Находит в словаре символ, которому соответствует интервал, в который попадает указанное число                                           
let find  (dict : Dictionary<byte, uint32 Interval.Interval>) (value : uint32)  = 
    printf "find :"
    print_any value
    Seq.fold (
        fun result (pair : KeyValuePair<byte, uint32 Interval.Interval>) -> 
        if Interval.inInteval value pair.Value
          then pair.Key
          else result) 0uy dict                                               
          
// Превращаетсловарь частот в словарь "интервалов частот".          
let convertWeights (newDic : Dictionary<byte, uint32 Interval.Interval>) (dic : Dictionary<byte, uint32>)  =
    Seq.sortBy (fun (pair : KeyValuePair<byte, uint32>) -> pair.Key) dic
    |> Seq.fold (fun res (pair : KeyValuePair<byte, uint32>) -> newDic.Add(pair.Key, Interval.Interval(res, res + pair.Value))
                                                                res + pair.Value) 0u
    newDic
          