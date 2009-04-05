package archiver;

public class Huffman {
	public static final int ALPHABETSIZE = 256;
	Tree[] tree= new Tree[ALPHABETSIZE]; // рабочий массив деревьев
	int weights[] = new int[ALPHABETSIZE];  // веса символов
	public String[] code = new String[ALPHABETSIZE]; // коды Хаффмана
	 
	private int getLowestTree(int used) { // ищем самое "легкое" дерево
		int min=0;
		for (int i=1; i<used; i++)
			if (tree[i].weight < tree[min].weight ) 
				min = i;
			return min;
	}
	public void setWeights( byte[] data ){
		for (int i=0; i<data.length; i++) 		// считаем веса символов
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
	public void growTree( byte[] data ) { 		// растим дерево
												//заполняем массив из "листовых" деревьев
		int used = 0;							//с использованными символами
		for (int c=0; c < ALPHABETSIZE; c++) {
			int w = weights[c];
			if (w != 0) tree[used++] = new Tree(c, w, true);
		}
		while (used > 1) {						// парами сливаем легкие ветки 
			int min = getLowestTree( used );	// ищем 1 ветку
			int weight0 = tree[min].weight;
			Tree temp = new Tree();				// создаем новое дерево
			temp.child0 = tree[min];			// и прививаем 1 ветку
			tree[min] = tree[--used]; 			// на место 1 ветки кладем
												// последнее дерево в списке
			min = getLowestTree( used );		// ищем 2 ветку и
			temp.child1 = tree[min]; 			// прививаем ее к нов.дер.
			temp.weight = weight0 + tree[min].weight;		// считаем вес нов.дер.
			tree[min] = temp;					// нов.дер. кладем на место 2 ветки
		} 										// все! осталось 1 дерево Хаффмана
	}
	
	public void makeCode() {					// запускаем вычисление кодов Хаффмана
		tree[0].traverse( "", this);
	}
	 
	public String coder( byte[] data ) { 		// кодирует данные строкой из 1 и 0
		String str = "";
		for (int i=0; i<data.length; i++) 
			str += code[data[i]];
		return str;
	}
	 
	public String decoder(String data) {
		String str="";								// проверяем в цикле данные на вхождение
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
