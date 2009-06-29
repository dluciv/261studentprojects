#light

open System.Collections.Generic

// Создает из списка 0 и 1 байт. Если список больше, чем из 8ми элементов, верхние элементы "обрезаются"        
let constructByte (l : int list) = 
    (byte) (List.fold (fun a b -> (a <<< 1) + b) 0 l)
    
// Переводит число в список, представляющий бинарное значение числа.
let rec convertToBinary (numValue : int) (binaryValue : int list) =
    match numValue with
    | 0 -> binaryValue              
    | x -> convertToBinary (numValue >>> 1) ((int)((byte)numValue <<< 7 >>> 7) :: binaryValue)

// Конструирует бинарное списочное представление необходимой длины числа (кода)
let constructNumber ((numLength, numValue): (int * int)) (lst : int list)= 
    convertToBinary numValue []
    |> List.append (List.replicate numLength 0) 
    |> List.append lst   
    
let countSizeOfLastByte (countAlphabet : Dictionary<byte HuffmanTree.Tree, int>) (codes : Dictionary<byte, (int * int)>) = 
  (Seq.fold (fun all (p : KeyValuePair<byte HuffmanTree.Tree, int>) -> all + (p.Value) * LangUtils.first codes.[HuffmanTree.data p.Key]) 0 countAlphabet) % 8
  