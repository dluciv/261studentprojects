package arithmet;

import java.util.ArrayList;
import java.util.Collections;

import tools.Tools;


public class Coder {
	
	int position = 0;
	long curData;
	ArrayList<Byte> outData;
	int[] A;
	
	public Coder() {		
		position = 0;
		curData = 0;
		outData = new ArrayList<Byte>(); 
	}
	
	void accumulate(int data, int shift) {
		while (shift > position + 8) {
			outData.add((byte)(curData & 0xFF));
			curData >>= 8;
			position += 8;			
		}
		curData += data << (shift - position);
	}
	void finish() {
		while (curData != 0) {
			outData.add((byte)(curData & 0xFF));
			curData >>= 8;
			position += 8;
		}
	}
	
	
	// shift | freqTable | data
	public byte[] code(byte[] in) {
		if (in.length < Decoder.sizeAdvInfo) {
			return in;
		}
		A = Tools.counter(in);
		int[] Q = Tools.GetQ(A);
		int[] k = Tools.GetK(A);
		int shift = 0;
		for (int i = in.length - 1; i >= 0; i--) {
			shift += k[in[i] & 0xFF];
			accumulate(Q[in[i] & 0xFF], shift);
		}
		finish();
		for (int i = 255; i >= 0; i--) {
			Tools.write(A[i], outData);
		}
		Tools.write(shift, outData);
		Tools.write(in.length, outData);
		Tools.write(Tools.ARIFMET, outData);
		Collections.reverse(outData);
		byte[] res = new byte[outData.size()];
		for(int i = 0; i < res.length; i++) {
			res[i] = outData.get(i);
		}
		return res;
	}
	
}
