package archiver;

public class HuffmanTree {
	   public HuffmanTree child0;       // потомки "0" и "1
	   public HuffmanTree child1;
	   public boolean leaf = false;      // признак листового дерева
	   public int character;     // входной символ
	   public int weight;        // вес этого символа

	   public HuffmanTree() {}

	   public HuffmanTree(int character, int weight, boolean leaf) {
	     this.leaf = leaf;
	     this.character = character;
	     this.weight = weight;
	   }

}
