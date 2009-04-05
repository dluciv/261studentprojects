package archiver;

public class HuffmanTest {
	public static void main(String[] args) {
		Huffman h = new Huffman();


		byte[] data = {20,20,20,20,50,116,118,46,20,20,20,20,114,117,20,20,20};
		 
		h.setWeights( data );		
		h.growTree( data );                       // растим дерево
		h.makeCode();                             // находим коды
		
		String str = h.coder(data);

		System.out.println(str);
		System.out.println(h.decoder(str));
	}

}
