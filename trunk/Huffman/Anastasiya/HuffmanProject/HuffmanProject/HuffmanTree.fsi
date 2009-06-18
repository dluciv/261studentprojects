open System.Collections.Generic

type 'a Tree =NullTree | TreeLeaf of 'a | TreeNode of 'a Tree * 'a Tree

val makeHuffmanTree<'a> : (seq<KeyValuePair<'a Tree, int>> -> 'a Tree)

val makeLeaf<'a> : 'a -> 'a Tree