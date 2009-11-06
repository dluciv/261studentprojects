/**
 * @copyright 2009 HarpSerg
 * @author HarpSerg
 */
package archiver;

import java.io.IOException;

/**
 * @copyright 2009 HarpSerg
 * @author HarpSerg
 */
public class BitsOutput {

    private OutputFile _stream = null;
    private boolean[] _buf = new boolean[8];
    private int _cur = 0;

    public BitsOutput(OutputFile stream) {
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
            _stream.write(Bits.bitsToInt(_buf, _cur));
            _cur = 0;
        }
    }

    public OutputFile stream() {
        return _stream;
    }
}
