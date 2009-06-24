package archiver;

/**
 *
 * @author Artem Mironov
 * @copyright 2009 Artem Mironov
 * @license GNU/GPL v2
 */

public class BitDecoder {
	private String bits = "";
	private boolean empty;
	HuffmanCoder h;

	public BitDecoder(HuffmanCoder huf){
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
