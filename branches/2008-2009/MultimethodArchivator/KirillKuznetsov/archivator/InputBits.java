package archivator;

/**
 *
 * @author Kirill Kuznetsov
 */

import java.io.*;

public class InputBits {

    private int buffer;
    private DataInputStream reader;
    private int bitsUsed = 0;

    InputBits(DataInputStream reader) {
        this.reader = reader;
    }

    public int readBit() throws IOException {
        int bit, meaningBits;
        if (bitsUsed == 0) {
            if(reader.available() == 0)
                return -1;
            buffer = reader.read();
            bitsUsed = 8;
            if(reader.available() == 1){
                meaningBits = reader.read();
                buffer <<= 8 - meaningBits;
                bitsUsed = meaningBits;
            }

        }
        bit = buffer>>7;
        buffer <<= 1;
        buffer -= bit<<8;
        bitsUsed--;
        return bit;

    }
}
