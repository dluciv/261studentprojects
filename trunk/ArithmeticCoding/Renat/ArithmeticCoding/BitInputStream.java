package ArithmeticCoding;

import java.io.FileInputStream;
import java.io.IOException;

public class BitInputStream {
	public static final int BUF_LENGTH = 0xFF; // 0xF, 0xFF, 0xFFF...
	private FileInputStream _stream;
	private boolean[] _bits = new boolean[BUF_LENGTH];
	private int _length = 0;
	private int _cursor = 0;
	
	BitInputStream(FileInputStream inp) throws IOException {
		_stream = inp;
	}
	
	// сбросить буфер и прочитать один байт
	public int readByte() throws IOException {
		_length = 0;
		_cursor = 0;
		return _stream.read();
	}
	
	// прочитать один бит
	public boolean read() throws BufferOverflow, IOException {
		if (_length == 0) {
			readByteIntoBuffer();
		}
		if (_length == 0) {
			throw new IOException();
		}
		boolean bit = _bits[_cursor];
		_length--;
		_cursor++;
		_cursor &= BUF_LENGTH;
		return bit;
	}
	
	private void readByteIntoBuffer() throws BufferOverflow, IOException {
		int b = _stream.read();
		if (b == -1) {
			return;
		}
		
		int index;
		boolean bit;
		for (int i=0; i<8; i++) {
			bit = b >> (7-i) == 1;
			b -= bit ? 1 << (7-i) : 0;
			index = (_cursor + i) & BUF_LENGTH;
			_bits[index] = bit;
			_length++;
		}
		if (_length > BUF_LENGTH) {
			throw new BufferOverflow();
		}
	}
}

class BufferOverflow extends Exception {}