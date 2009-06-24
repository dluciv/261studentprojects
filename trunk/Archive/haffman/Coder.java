package haffman;

import java.util.ArrayList;

import tools.Tools;

public class Coder {
	
	
	ArrayList<Byte> outData;
	long buffer;
	int buf_size;
	int bit_size = 0;
	
	void accumulate(Symbol s) {
		buffer <<= s.length;
		buffer += s.code;
		buf_size += s.length;
			
		while (buf_size > 8) {
			outData.add((byte)(buffer >> (buf_size - 8)));
			buffer &= (1 << (buf_size - 8)) - 1;
			buf_size -= 8;
		}
	}
	void finish() {
		bit_size = buf_size;
		outData.add((byte)buffer);		
	}
	
	
	public byte[] code(byte[] in) {
		if (in.length < Decoder.addInfo) {
			return in;
		}
		int[] freq = Tools.counter(in);
		outData = new ArrayList<Byte>();
		Node root = Node.buildTree(freq);
		Symbol[] codeTable = Node.fillTable(root);
		Tools.write(Tools.HAFFMAN, outData);
		Tools.write(in.length, outData);
		Tools.write(buf_size, outData);		
		for (int i = 0; i < 256; i++) {
			Tools.write(freq[i], outData);
		}
		for (int i = 0; i < in.length; i++) {
			accumulate(codeTable[in[i] & 0xFF]);
		}
		finish();		
		byte[] result = new byte[outData.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = outData.get(i);
		}		
		return result; // todo
	}
}
