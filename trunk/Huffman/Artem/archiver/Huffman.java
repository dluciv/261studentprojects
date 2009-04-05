package archiver;

public class Huffman {
	public static final int ALPHABETSIZE = 256;
	Tree[] tree= new Tree[ALPHABETSIZE]; // ������� ������ ��������
	int weights[] = new int[ALPHABETSIZE];  // ���� ��������
	public String[] code = new String[ALPHABETSIZE]; // ���� ��������
	 
	private int getLowestTree(int used) { // ���� ����� "������" ������
		int min=0;
		for (int i=1; i<used; i++)
			if (tree[i].weight < tree[min].weight ) 
				min = i;
			return min;
	}
	public void setWeights( byte[] data ){
		for (int i=0; i<data.length; i++) 		// ������� ���� ��������
		{
			weights[data[i]]++;
		}
		/*
		for (int i=0; i<data.length; i++)
		{
			System.out.println(data[i]+" - "+weights[data[i]]);
		}
		*/
	}
	public int[] getWeights( ){
			return weights;
	}
	public void growTree( byte[] data ) { 		// ������ ������
												//��������� ������ �� "��������" ��������
		int used = 0;							//� ��������������� ���������
		for (int c=0; c < ALPHABETSIZE; c++) {
			int w = weights[c];
			if (w != 0) tree[used++] = new Tree(c, w, true);
		}
		while (used > 1) {						// ������ ������� ������ ����� 
			int min = getLowestTree( used );	// ���� 1 �����
			int weight0 = tree[min].weight;
			Tree temp = new Tree();				// ������� ����� ������
			temp.child0 = tree[min];			// � ��������� 1 �����
			tree[min] = tree[--used]; 			// �� ����� 1 ����� ������
												// ��������� ������ � ������
			min = getLowestTree( used );		// ���� 2 ����� �
			temp.child1 = tree[min]; 			// ��������� �� � ���.���.
			temp.weight = weight0 + tree[min].weight;		// ������� ��� ���.���.
			tree[min] = temp;					// ���.���. ������ �� ����� 2 �����
		} 										// ���! �������� 1 ������ ��������
	}
	
	public void makeCode() {					// ��������� ���������� ����� ��������
		tree[0].traverse( "", this);
	}
	 
	public String coder( byte[] data ) { 		// �������� ������ ������� �� 1 � 0
		String str = "";
		for (int i=0; i<data.length; i++) 
			str += code[data[i]];
		return str;
	}
	 
	public String decoder(String data) {
		String str="";								// ��������� � ����� ������ �� ���������
		while(data.length() > 0){
			for (int c=0; c < ALPHABETSIZE; c++) {
				if (data.startsWith(code[c])){
					data = data.substring(code[c].length(), data.length());
					str += (char)c;
				}
			}
		}
		return str;
	}
}
