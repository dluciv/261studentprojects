package Arithm;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Renat Akhmedyanov
 */
public class BitOutputStream {
	private FileOutputStream _stream = null;
	private boolean[] _buf = new boolean[8];
	private int _cur = 0;
	
	public BitOutputStream(FileOutputStream stream) {
		_stream = stream;
	}
	
	public void write(boolean bit) throws IOException {
		_buf[_cur] = bit;
		if (++_cur == 8) {
			flush();
		}
	}
	
	public void write(int c) throws IOException {
		_stream.write(c);
	}
	
	public void close() throws IOException {
		flush();
		_stream.close();
	}
	
	public void flush() throws IOException {
		if (_cur > 0) {
			_stream.write(bitsToInt(_buf, _cur));
			_stream.flush();
			_cur = 0;
		}
	}
	
	public FileOutputStream stream() {
		return _stream;
	}
	
	private int bitsToInt(boolean[] bits) {
		return bitsToInt(bits, 8);
	}
	
	private int bitsToInt(boolean[] bits, int count) {
		int out = 0;
		for (int i=0; i<count; i++) {
			if (bits[i]) {
				out += 1 << (bits.length - i - 1);
			}
		}
		return out;
	}
}
