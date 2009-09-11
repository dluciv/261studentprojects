#light

open System.Collections.Generic

// Создает из списка 0 и 1 байт. Если список больше, чем из 8ми элементов, верхние элементы "обрезаются"        
let constructByte (l : int list) = 
    (byte) (List.fold (fun a b -> (a <<< 1) + b) 0 l)
    
// HACK странный способ взять "следующее"    
// Переводит число в список, представляющий бинарное значение числа.
let rec convertToBinary (numValue : int) (binaryValue : int list) =
    match numValue with
    | 0 -> binaryValue              
    | x -> convertToBinary (numValue >>> 1) ((int)((byte)numValue <<< 7 >>> 7) :: binaryValue)

// Конструирует бинарное списочное представление необходимой длины числа (кода)
let constructNumber ((numLength, numValue): (int * int)) (lst : int list)= 
    let l = convertToBinary numValue []
    List.append (List.replicate (numLength - l.Length) 0) l 
    |> List.append lst   
    
let countSizeOfLastByte (alphabetToCount : Dictionary<byte HuffmanTree.Tree, int>) (codes : Dictionary<byte, (int * int)>) = 
  let count all (p : KeyValuePair<byte HuffmanTree.Tree, int>) =
    all + (p.Value) * LangUtils.first codes.[HuffmanTree.data p.Key]
  match Seq.fold count 0 alphabetToCount with
  | 8 -> 8
  | x -> x % LangUtils.BYTE_SIZE
  