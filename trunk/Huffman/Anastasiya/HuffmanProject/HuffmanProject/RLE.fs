#light

// "Схлопывает" список из 256ти значений по алгоритму RLE
let collapse (lst : int list) = 
    List.fold (fun res num -> 
                 match res with
                 |((a, b) :: xs) when a = num -> (a, b + 1) :: xs
                 |l -> (num, 1) :: l
               ) [] lst
