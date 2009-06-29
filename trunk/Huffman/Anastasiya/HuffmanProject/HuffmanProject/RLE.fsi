#light
open System.IO
open System.Collections.Generic

val collapse : int list -> (int * int) list
val uncollapse : BinaryReader -> Dictionary<int list, byte>
//val uncollapse : BinaryReader -> seq<(int list* byte)>
