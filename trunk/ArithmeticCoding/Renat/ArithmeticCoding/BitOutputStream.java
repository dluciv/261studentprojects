package ArithmeticCoding;

import java.io.FileOutputStream;
import java.io.IOException;

public class BitOutputStream
{
	public static final int BUF_LENGTH = 0xFF; // 0xF, 0xFF, 0xFFF...
	private FileOutputStream _out;
	private boolean[] _bits = new boolean[BUF_LENGTH];
	private int _length = 0;
	private int _cursor = 0;
	
	BitOutputStream(FileOutputStream out) throws IOException {
		_out = out;
	}
	
	public void writeBit(boolean bit) throws IOException {
		_bits[_cursor] = bit;
		_cursor++;
		_length++;
		
		if (_cursor == BUF_LENGTH) {
			_cursor = 0;
		}
		if (_length >= 8) {
			_length -= 8;
			int j=7;
			int b = 0;
			for (int i=_cursor-8; i<_cursor; i++) {
				while (i >= BUF_LENGTH) {
					i -= BUF_LENGTH;
				}
				while (i < 0) {
					i += BUF_LENGTH;
				}
				b += _bits[i] ? 1 << j : 0;
				j--;
			}
			_out.write(b);
		}
	}
	
	public void flush() throws IOException {
		int j=7;
		int b = 0;
		for (int i=_cursor-_length; i<_cursor; i++) {
			i &= BUF_LENGTH;
			b += _bits[i] ? 1 << j : 0;
			j--;
		}
		_out.write(b);
	}
	
	public void close() throws IOException {
		flush();
		_out.flush();
		_out.close();
	}
}