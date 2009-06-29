#light

type BigFloat = Fl of int list

let make (l : int list) = Fl(l)

let ZERO = Fl([0])
let ONE = Fl([1])

let clear (Fl(l1) : BigFloat) = 
    let clearNulls (lst : int list) (el : int) = 
        match lst, el with
        | [], 0 -> []
        | xs, 0 -> xs @ [0]
        | xs, n -> xs @ [n]
    let checkNotNull (lst : int list) = 
        match lst with
        |[] -> [0]
        |xs -> xs
    List.rev l1
    |> List.fold clearNulls [] 
    |> List.rev
    |> checkNotNull
    |> make

let levelOff (l : int list) = 
    match l with
    | [] -> []
    |(x :: xs) as xss -> if x >= 10 then (x / 10) :: (x % 10) :: xs else xss

let printFloat (Fl(l) as f : BigFloat) = 
    match l with
    | [] -> printf "_\n"
    |(x :: []) -> print_any x
                  printf ".0 "
    |(x :: xs) -> print_any x
                  printf "."
                  List.fold (fun _ el -> print_any el) () xs
                  printf " "
    f

let printFloatList (fl : BigFloat list) = 
    List.fold (fun r el -> printFloat el
                           printf ", ") () fl
    fl                           
    
let plus_list (f1 : int list) (f2 : int list) = 
    let lengthSub = f1.Length - f2.Length
    
    let rec conc (l1 : 'a list) (l2 : 'a list) (res : ('a * 'a) list) =
        match l1, l2 with
        | [], [] -> res
        | (x :: xs), (y :: ys) -> conc xs ys ((x, y) :: res)
        |_, _ -> failwith "error : big float plus 1"
        
    let summ (res : int list) ((i1, i2) : (int * int)) = 
        match res with 
        | x :: xs -> (i1 + i2 + x / 10) :: (x % 10) :: xs
        | [] -> (i1 + i2) :: []
        
    
    let pl (l1 : int list) (l2 :  int list) = 
        List.fold summ [] (conc l1 l2 [])
//        |> levelOff
        
    
    match lengthSub with
    |x when x = 0 -> pl f1 f2
    |x when x > 0 -> pl f1 (f2 @ (List.replicate x 0))
    |x when x < 0 -> pl (f1 @ (List.replicate (-x) 0)) f2


let minus_list (f1 : int list) (f2 : int list) = 
    let lengthSub = f1.Length - f2.Length
    
    let rec conc (l1 : 'a list) (l2 : 'a list) (res : ('a * 'a) list) =
        match l1, l2 with
        | [], [] -> res
        | (x :: xs), (y :: ys) -> conc xs ys ((x, y) :: res)
        |_, _ -> failwith "error : big float plus 1"
        
    let minus (res : int list) ((i1, i2) : (int * int)) = 
        match res with 
        | x :: xs when x < 0 -> (i1 - i2 - 1) :: (x  + 10) :: xs
        | x :: xs -> (i1 - i2) :: x :: xs
        | [] -> (i1 - i2) :: []
        
    
    let pl (l1 : int list) (l2 :  int list) = 
        List.fold minus [] (conc l1 l2 [])
        
    
    match lengthSub with
    |x when x = 0 -> pl f1 f2
    |x when x > 0 -> pl f1 (f2 @ (List.replicate x 0))
    |x when x < 0 -> pl (f1 @ (List.replicate (-x) 0)) f2






let plus (Fl(f1) : BigFloat) (Fl(f2) : BigFloat) =
    plus_list f1 f2
    |> make
    |> clear
    
let minus (Fl(f1) : BigFloat) (Fl(f2) : BigFloat) =
    minus_list f1 f2
    |> make
    |> clear
    
    
let multInt (Fl(l) : BigFloat) (i : int) = 
    let umn (lst : int list) (el : int) = 
        match lst with
        |[] -> (el * i) :: []
        |(x :: xs) -> (el * i + x / 10) :: (x % 10) :: xs

    List.rev l
    |> List.fold (umn) []
    
    
let mult (fl1 : BigFloat) (Fl(l2) : BigFloat) = 
    List.rev l2
    |> List.fold (fun res el -> (multInt fl1 el) :: res) [] 
    |> List.fold (fun res el -> make((List.replicate (res.Length) 0 ) @ el ) :: res) []
    |> List.fold (fun res el -> plus res el) ZERO
    |> clear


        
let rec equalParts (Fl(l1) : BigFloat) (Fl(l2) : BigFloat) = 
    let rec eq lst1 lst2 res= 
        match lst1, lst2 with
        |(x :: xs), (y :: ys) when x = y -> eq xs ys (x :: res)
        |_ -> List.rev res        
        
    let getEquParts = 
        match eq l1 l2 [] with
        | 0 :: xs  -> xs
        | xs -> xs
        
    match l1, l2 with
    |[0], xs when xs.Length > 1 -> equalParts (make (List.replicate xs.Length 0)) (make xs)
    |xs, [0] when xs.Length > 1-> equalParts (make (List.replicate xs.Length 0)) (make xs)
    |xs, ys -> getEquParts 
    
    
let startFromNth (num : int) (Fl(l) : BigFloat) = 
    let checkNotNull (lst : int list) = 
        match lst, num with
        | xs, 0 -> xs
        | [], _ -> [0];
        | xs, _ -> 0 :: xs
    let get lst = LangUtils.takeFromNth num lst

    printf "\n++++\n"
    print_any num
    printf ", "
    print_any l
    printf "\n++++\n"
    match l with
    | 0 :: xs -> 0 :: (get xs)
    | xs -> get xs
    |> make    
    
    

let divideInts (i1 : int) (i2 : int) (prec : int) = 
    let rec div (toDiv : int) (divBy : int) (res : int list)= 
        match res.Length, toDiv with
        |lngth, _ when lngth >= prec -> res
        |_, x when x < divBy -> div (toDiv * 10) divBy (0 :: res)
        |_, x when x = divBy -> 1 :: res
        |_, x when x > divBy -> div ((toDiv % divBy) * 10) divBy ((toDiv / divBy) :: res)
    match i2 with
    |0 -> failwith "error : dividing by ZERO"
    |x -> div i1 x []
    
    |> List.rev
    |> make
        
let dump (Fl(l1) : BigFloat) = 
    match l1 with 
    |0 :: xs -> xs
    |xs -> xs                      