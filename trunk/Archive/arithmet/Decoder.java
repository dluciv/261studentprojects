package arithmet;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import tools.FileSt;
import tools.Tools;

public class Decoder {
	
	static final int addInfo = 259 * 4;
	public static final int BUF = 4096;
	
	ByteOutputStream result;
	
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
	public void decode(FileSt in, OutputStream out) throws IOException {
		int available = in.available();
		if (available < Decoder.addInfo) {
			byte[] buffer= new byte[available];
			in.read(buffer);
			out.write(buffer);
			return;
		}
		int type = Tools.readForward(in);
		int size = Tools.readForward(in);
		result = new ByteOutputStream();
		
		int shift = (available - addInfo) * 8 - Tools.readForward(in);
		A = new int[256];
		for (int i = 0; i < 256; i++) {
			A[i] = Tools.readForward(in);
		}
		Q = Tools.GetQ(A);
		k = Tools.GetK(A);
		
		int curPos = 0;
		int curByte = addInfo;
		int resPos = 0;
		
		int len = 0;
		byte[] buffer = new byte[BUF];
		
		while ((len = in.read(buffer)) > 0 && shift != (available - addInfo) * 8) {
			for (int i = 0; i < len; i++) {
				while (shift > curPos) {
					curPos += 8;
					accumulate(buffer[i]);			
				}
				while (shift <= curPos) {
					byte ans = decode(curPos - shift);
					result.write(ans);
					shift += k[ans & 0xFF];
				}
			}
			if (result.getCount() > BUF) {
				out.write(result.getBytes(),0,result.getCount());
				result.reset();
			}
		}		
		out.write(result.getBytes(),0,result.getCount());
		result.reset();
	}
}
