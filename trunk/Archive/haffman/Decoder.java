package haffman;

import tools.Tools;

public class Decoder {
	
	static final int addInfo = 259 * 4;
	static final int positionOfName = 0;
	static final int positionOfSize = 1;
	static final int positionOfBitSize = 2;
	static final int positionOfTable = 3;
	
	byte[] result;
	int pos;
	Node root;
	Node curNode;
	long buffer;
	int buf_size;	
	 
	
	void accumulate(int byt, int size) {
		
		buffer <<= size;
		buffer += byt >> (8 - size);
		buf_size += size;
		for (;buf_size != 0; buf_size--) {
			int val = (int)(buffer >> (buf_size - 1));
			buffer &= (1 << (buf_size)) - 1;
			buf_size--;
			if (val == 0) {
				curNode = curNode.left;
			} else {
				curNode = curNode.right;
			}
			if (curNode.isLeaf()) {
				result[pos++] = curNode.symbol;
				curNode = root;
			}
		}
	}
	
	public byte[] decode(byte[] in) {
		if (in.length < addInfo) {
			return in;
		}
		int size = Tools.readForward(positionOfSize, in);
		int bit_size = Tools.readForward(positionOfBitSize, in);
		
		result = new byte[size];		
		int[] freq = new int[256];
		for (int i = 0; i < 256; i++) {
			freq[i] = Tools.readForward(i + positionOfTable, in);
		}		
		pos = 0;
		buf_size = 0;
		buffer = 0;
		root = Node.buildTree(freq);
		curNode = root;
		
		for (int i =addInfo ; i < in.length - 1; i++) {
			accumulate(in[i] & 0xFF, 8);			
		}
		accumulate(in[in.length - 1] & 0xFF, bit_size);
		
		return result;
	}
}
