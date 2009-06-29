package haffman;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import tools.FileSt;
import tools.Tools;

public class Coder {
	
	public static final int BUF = 4096;
	
	OutputStream outer;
	long buffer;
	int buf_size;
	int bit_size = 0;
	
	ByteOutputStream outData;
	private OutputStream out; 
	
	int sc = 0;
	void accumulate(Symbol s) throws IOException {
		buffer <<= s.length;
		buffer += s.code;
		buf_size += s.length;
			
		while (buf_size > 8) {
			outData.write((byte)(buffer >> (buf_size - 8)));
			sc++;
			buffer &= (1 << (buf_size - 8)) - 1;
			buf_size -= 8;
		}
		if (outData.getCount() > BUF) {
			out.write(outData.getBytes(), 0, outData.getCount());
			outData.reset();			
		}
	}
	void finish() throws IOException {
		bit_size = buf_size;
		outData.write((byte)buffer);
		
		out.write(outData.getBytes(),0, outData.getCount());
		outData.reset();
	}
	
	
	public void code(FileSt in, OutputStream out) throws IOException {
		
		if (in.available() < Decoder.addInfo) {
			byte[] buffer= new byte[in.available()];
			in.read(buffer);
			out.write(buffer);
			return;
		}
		this.out = out;
		int[] freq = Tools.counter(in);
		outData = new ByteOutputStream();
		Node root = Node.buildTree(freq);
		Symbol[] codeTable = Node.fillTable(root);
		bit_size = 0;
		for (int i = 0; i < 256; i++) {
			bit_size += codeTable[i].length * freq[i];
			bit_size %= 8;
		}
		bit_size = bit_size == 0 ? 8 : bit_size;
		Tools.write(Tools.HAFFMAN, outData);
		Tools.write(in.available(), outData);
		Tools.write(bit_size, outData);		
		for (int i = 0; i < 256; i++) {
			Tools.write(freq[i], outData);
		}
		//System.out.println(outData.getBytes().length);
		out.write(outData.getBytes(),0, outData.getCount());
		outData.reset();
		byte[] buffer = new byte[BUF];
		int len =0;
		while ((len = in.read(buffer)) >= 0) {
			for (int i = 0; i < len; i++) {
				accumulate(codeTable[buffer[i] & 0xFF]);
			}
		}
		
		finish();
		
		
		
	}
}
