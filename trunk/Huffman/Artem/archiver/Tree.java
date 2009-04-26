package archiver;

public class Tree {
	   public Tree child0;       // потомки "0" и "1
	   public Tree child1;
	   public boolean leaf = false;      // признак листового дерева
	   public int character;     // входной символ
	   public int weight;        // вес этого символа

	   public Tree() {}

	   public Tree(int character, int weight, boolean leaf) {
	     this.leaf = leaf;
	     this.character = character;
	     this.weight = weight;
	   }

}
