#light

// Создает из списка 0 и 1 байт. Если список больше, чем из 8ми элементов, верхние элементы "обрезаются"        
let constructByte (l : int list) = 
    (byte) (List.fold (fun a b -> (a <<< 1) + b) 0 l)

// Вспомогательная функция, которая позволяет добавить определенное количество нулей в конец списка.    
let appendNullsToEnd (count : int) (lst : int list) = 
    let rec nulls (num : int) (result : int list) = 
        match num with
        | x when x <= 0 -> result
        | x -> nulls (x - 1) (0 :: result)
    lst @ (nulls count [])        
  
  
// Дозаполняет список из 0 и 1 нулями спереди для того, что бы "длина" кода была равна заявленной.
let rec appendNulls (allLength : int) (binaryValue : int list)  = 
    match binaryValue with
    | bv when bv.Length >= allLength -> binaryValue
    | bv -> appendNulls allLength (0 :: binaryValue) 
        
// Переводит число в список, представляющий бинарное значение числа.
let rec convertToBinary (numValue : int) (binaryValue : int list) =
    match numValue with
    | 0 -> binaryValue              
    | x -> convertToBinary (numValue >>> 1) ((int)((byte)numValue <<< 7 >>> 7) :: binaryValue)

// Конструирует бинарное списочное представление необходимой длины числа (кода)
let constructNumber ((numLength, numValue): (int * int)) = 
    convertToBinary numValue []
    |> appendNulls (numLength - 1)
    |> List.append   