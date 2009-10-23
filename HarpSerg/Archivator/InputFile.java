package archiver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author HarpSerg
 */
public class InputFile {

    private InputStream fileStream;
    private boolean[] _buf = new boolean[8];
    private int _cur = 8;

    public InputFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            fileStream = new FileInputStream(file);
        } else {
            throw new IOException();
        }
    }

    public byte[] read(int len) throws IOException {
        if (len > fileStream.available()) {
            len = fileStream.available();
        }
        byte[] buf = new byte[len];
        fileStream.read(buf, 0, len);
        return buf;
    }

    public int read() throws IOException {
        int buf;
        buf = fileStream.read();
        return buf;
    }

    public void clean() throws IOException {
        this.fileStream.close();
    }

    public int lenLeft() throws IOException {
        return fileStream.available();
    }

    public void close() throws IOException {
        fileStream.close();
    }

    public byte readOneByte() throws IOException {
        byte res = read(1)[0];
        return res;

    }

    public int read(byte[] buf) throws IOException {
        return fileStream.read(buf);
    }
}
