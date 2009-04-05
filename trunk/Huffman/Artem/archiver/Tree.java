package archiver;

public class Tree {
	   public Tree child0;       // потомки "0" и "1
	   public Tree child1;
	   public boolean leaf;      // признак листового дерева
	   public int character;     // входной символ
	   public int weight;        // вес этого символа
	 
	   public Tree() {}
	 
	   public Tree(int character, int weight, boolean leaf) {
	     this.leaf = leaf;
	     this.character = character;
	     this.weight = weight;
	   }
	 
	/*  Обход дерева с генерацией кодов
	    1. "Распечатать" листовое дерево и записать код Хаффмана в массив
	    2. Рекурсивно обойти левое поддерево (с генерированием кода).
	    3. Рекурсивно обойти правое поддерево.
	*/
	   public void traverse(String code, Huffman h) {
	      if (leaf) {
	         //System.out.println((char)character +"  "+ weight +"  "+ code);
	         h.code[character] = code;
	      }
	      if ( child0 != null) child0.traverse(code + "0", h);
	      if ( child1 != null) child1.traverse(code + "1", h);
	   }

}
