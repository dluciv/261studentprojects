package Arithm;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Renat Akhmedyanov
 */
public class BitInputStream {
	private FileInputStream _stream = null;
	private boolean[] _buf = new boolean[8];
	private int _cur = 8;
	
	public BitInputStream(FileInputStream stream) {
		_stream = stream;
	}
	
	public boolean read() throws IOException {
		if (_cur == 8) {
			readbyte();
		}
		return _buf[_cur++];
	}
	
	public void skipbyte() {
		_cur = 8;
	}
	
	public void close() throws IOException {
		_stream.close();
	}
	
	public FileInputStream stream() {
		return _stream;
	}
	
	private void readbyte() throws IOException {
		int b = _stream.read();
		
		if (b == -1) {
			throw new IOException();
		}
		
		for (int i=0; i<8; i++) {
			_buf[i] = b >> (7 - i) == 1;
			if (_buf[i]) {
				b -= 1 << (7 - i);
			}
		}
		_cur = 0;
	}
}