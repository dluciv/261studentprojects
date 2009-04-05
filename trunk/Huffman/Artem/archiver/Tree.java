package archiver;

public class Tree {
	   public Tree child0;       // ������� "0" � "1
	   public Tree child1;
	   public boolean leaf;      // ������� ��������� ������
	   public int character;     // ������� ������
	   public int weight;        // ��� ����� �������
	 
	   public Tree() {}
	 
	   public Tree(int character, int weight, boolean leaf) {
	     this.leaf = leaf;
	     this.character = character;
	     this.weight = weight;
	   }
	 
	/*  ����� ������ � ���������� �����
	    1. "�����������" �������� ������ � �������� ��� �������� � ������
	    2. ���������� ������ ����� ��������� (� �������������� ����).
	    3. ���������� ������ ������ ���������.
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
