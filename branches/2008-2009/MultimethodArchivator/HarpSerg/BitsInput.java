package archiver;


import java.io.IOException;

/**
 *
 * @author HarpSerg
 */
public class BitsInput {
	private InputFile _stream = null;
	private boolean[] _buf = new boolean[8];
	private int _cur = 8;
	
	public BitsInput(InputFile stream) {
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
	
	public InputFile stream() {
		return _stream;
	}
	
	private void readbyte() throws IOException {
		int b = _stream.read();

        if (b == -1) {
			throw new IOException();
		}
		
		for (int i = 0; i < 8; i++) {
			_buf[i] = b >> (7 - i) == 1;
			if (_buf[i]) {
				b -= 1 << (7 - i);
			}
		}
		_cur = 0;
	}
}