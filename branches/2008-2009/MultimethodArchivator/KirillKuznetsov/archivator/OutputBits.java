
package archivator;

/**
 *
 * @author Kirill Kuznetsov
 */
import java.io.*;
public class OutputBits {
    private DataOutputStream writer;
    private int buffer;
    private int freeBits = 8;

    OutputBits(DataOutputStream writer) {
        this.writer = writer;
    }

    public void writeBit(boolean bit) throws IOException{
        if(freeBits == 0){
            writer.write(buffer);
            buffer = 0;
            freeBits = 8;
        }
        buffer <<= 1;
        if (bit) 
            buffer |= 1;
        freeBits--;
        
    }
    public void lastBits()throws IOException{
        writer.write(buffer);
        writer.write(8 - freeBits);
    }
}
