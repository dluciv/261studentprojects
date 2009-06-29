package arithmet;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import tools.FileSt;
import tools.Tools;


public class Coder {
	
	static final int BUF = 4096;
	
	int position = 0;
	long curData;
	int[] A;
	private OutputStream out;
	ByteOutputStream outData;
	
	public Coder() {		
		position = 0;
		curData = 0;
		outData = new ByteOutputStream(); 
	}
	
	void accumulate(int data, int shift) throws IOException {
		while (shift > position + 8) {
			outData.write((byte)(curData & 0xFF));
			curData >>= 8;
			position += 8;			
		}		
		curData += data << (shift - position);
		if (outData.getCount() > BUF) {
			out.write(outData.getBytes(), 0, outData.getCount());
			outData.reset();
		}
	}
	void finish() throws IOException {
		while (curData != 0) {
			outData.write((byte)(curData & 0xFF));
			curData >>= 8;
			position += 8;
		}
		out.write(outData.getBytes(),0,outData.getCount());
		outData.reset();
	}
	
	
	// shift | freqTable | data
	public void code(FileSt in, OutputStream out) throws IOException {
		int available =in.available(); 
		if (available < Decoder.addInfo) {
			byte[] buffer= new byte[available];
			in.read(buffer);
			out.write(buffer);
			return;
		}
		this.out = out;
		A = Tools.counter(in);
		int[] Q = Tools.GetQ(A);
		int[] k = Tools.GetK(A);
		int shift = 0;
		byte[] buffer = new byte[BUF];
		int len;
		while ((len = in.read(buffer))>0) {
			for (int i = 0; i < len; i++) {
				shift += k[buffer[i] & 0xFF];
				accumulate(Q[buffer[i] & 0xFF], shift);
			}
		}
		finish();

		for (int i = 255; i >= 0; i--) {
			Tools.write(A[i], outData);
		}
		Tools.write(shift, outData);
		Tools.write(available, outData);
		Tools.write(Tools.ARIFMET, outData);
		out.write(outData.getBytes(),0,outData.getCount());
		outData.reset();

	}
	
}
