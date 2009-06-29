package haffman;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import tools.FileSt;
import tools.Tools;

public class Decoder {
	
	public static final int BUF = 4096;	
	public static final int addInfo = 259 * 4;

	OutputStream out;
	ByteOutputStream result;
	
	int pos;
	Node root;
	Node curNode;
	long buffer;
	int buf_size;	
	 
	
	void accumulate(int byt, int size) throws IOException {
		
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
				result.write(curNode.symbol);
				curNode = root;
			}
		}
		if (result.getCount() >  BUF) {
			out.write(result.getBytes(), 0, result.getCount());
			result.reset();
		}
	}
	
	public void decode(FileSt in, OutputStream out) throws IOException {
		if (in.available() < Decoder.addInfo) {
			byte[] buffer= new byte[in.available()];
			in.read(buffer);
			out.write(buffer);
			return;
		}
		result = new ByteOutputStream();
		int type = Tools.readForward(in);
		int size = Tools.readForward(in);
		int bit_size = Tools.readForward(in);
		this.out = out;
		
			
		int[] freq = new int[256];
		for (int i = 0; i < 256; i++) {
			freq[i] = Tools.readForward(in);
		}		
		
		pos = 0;
		buf_size = 0;
		buffer = 0;
		root = Node.buildTree(freq);
		curNode = root;
		
		byte[] buffer = new byte[BUF];
		int len = 0;
		int now = addInfo;
		while ((len = in.read(buffer)) > 0) {
			for (int i =0 ; i < len; i++) {
				accumulate(buffer[i] & 0xFF, 8);	
				if (now + i == in.available() - 1) {
					accumulate(buffer[i] & 0xFF, bit_size);	
				}
			}			
		}	
		out.write(result.getBytes(), 0, result.getCount());
		result.reset();		
	}
}
