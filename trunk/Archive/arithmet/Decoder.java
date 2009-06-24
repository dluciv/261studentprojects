package arithmet;

import tools.Tools;

public class Decoder {
	
	static final int sizeAdvInfo = 259 * 4;
	static final int positionOfName = 0;
	static final int positionOfSize = 1;
	static final int positionOfShift = 2;
	static final int positionOfTable = 3;
	
	
	
	int[] A;
	int[] Q;
	int[] k;
	long buffer;
	
	public Decoder() {
		buffer = 0;
	}
	
	void accumulate(byte x) {
		buffer <<= 8;
		buffer += x & 0xFF;
	}
	
	byte decode(int sh) {		
		for (int i = 0; i < 256; i++) {
			if ((Q[i] << sh) <= buffer && buffer < (Q[i+1] << sh)) {
				buffer -= Q[i] << sh;
				return (byte)i; 
			}
		}
		return 0;
	}
	public byte[] decode(byte[] in) {
		if (in.length < Decoder.sizeAdvInfo) {
			return in;
		}
		int size = Tools.readBack(positionOfSize, in);
		byte[] result = new byte[size];
		
		int shift = (in.length - sizeAdvInfo) * 8 - Tools.readBack(positionOfShift, in);
		A = new int[256];
		for (int i = 0; i < 256; i++) {
			A[i] = Tools.readBack(i + positionOfTable, in);
		}
		Q = Tools.GetQ(A);
		k = Tools.GetK(A);
		
		int curPos = 0;
		int curByte = sizeAdvInfo;
		int resPos = 0;
		
		while (shift != (in.length - sizeAdvInfo) * 8) {
			while (shift > curPos) {
				curPos += 8;
				accumulate(in[curByte++]);			
			}
			byte ans = decode(curPos - shift);
			result[resPos++] = ans;
			shift += k[ans & 0xFF];
		}
		return result;
	}
}
