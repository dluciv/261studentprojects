/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package archiver;

/**
 *
 * @author Admin
 */
public class BitDecoder {
	private String bits = "";
	private boolean empty;
	Huffman h;

	public BitDecoder(Huffman huf){
		empty = false;
		h = huf;
		//h.printIws();
	}
	
	public void addBits(String str){
		bits += str;
	}

	public String decodeBits(){
		String str = "";

		TwoString ts = h.decodeBits(bits);
		bits = ts.str1;
		str = ts.str2;

		return str;
	}

	public boolean isAllDecoded(){
		if(bits.length()==0 && !empty)
			return true;
		return false;
	}
}
