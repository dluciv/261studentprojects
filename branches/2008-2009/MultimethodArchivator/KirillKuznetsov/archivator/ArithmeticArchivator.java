package archivator;

/**
 *
 * @author Кирилл
 */
import java.io.*;

public class ArithmeticArchivator {

    protected DataInputStream reader;
    protected DataOutputStream writer;
    protected File compressed,  oldFile;
    protected int[] freq = new int[258];
    protected long[] comFreq = new long[258];
    protected long left = 0;
    protected long right = MAX_VALUE;
    private OutputBits output;
    protected int bitsToFollow = 0;
    protected static int CODE_BITS = 31;
    protected static int EOF = -1;
    protected static int MAX_VALUE = (1 << CODE_BITS) - 1;
    protected static int FIRST_QTR = MAX_VALUE / 4 + 1;
    protected static int HALF = FIRST_QTR * 2;
    protected static int THIRD_QTR = FIRST_QTR * 3;
    protected static int NO_OF_CHARS = 256;
    protected static int NO_OF_SYMBOLS = NO_OF_CHARS + 1;
    protected static int EOF_SYMBOL = NO_OF_SYMBOLS;
    protected static int MAX_FREQ = (1 << 23) - 1;

    ArithmeticArchivator() {
    }

    ArithmeticArchivator(String oldFileName, String newFileName) throws FileNotFoundException {
        compressed = new File(newFileName);
        oldFile = new File(oldFileName);
        writer = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(compressed)));
        reader = new DataInputStream(
                new BufferedInputStream(new FileInputStream(oldFile)));
        output = new OutputBits(writer);
    }

    public void archivateA() throws IOException {
        getFreq();
        writeFreq();
        setComFreq();
        compress();
        reader.close();
        writer.close();
    }

    private void getFreq() throws IOException {
        int j;
        for (short i = 1; i <= 257; ++i) {
            freq[i] = 1;
        }
        while (reader.available() != 0) {
            j = reader.read();
            if(freq[j + 1] < MAX_FREQ)
            ++(freq[j + 1]);
        }
//        for(int i = 0; i < 258; ++i)
//            System.out.println(freq[i]);
        resetReader();
    }

    private void compress() throws IOException {
        while (reader.available() != 0) {
            encodeSymbol(reader.read() + 1);
        }
        finishEncoding();
    }

    private void encodeSymbol(int symbol) throws IOException {
        long currRange = right - left + 1;
        right = left + (currRange * comFreq[symbol - 1]) / comFreq[0] - 1;
        left += (currRange * comFreq[symbol]) / comFreq[0];
        //System.out.println(left + " - " + right);

        while (true) {
            if (right < HALF) {
                bitPlusFollow(false);
            } else if (left >= HALF) {
                bitPlusFollow(true);
                left -= HALF;
                right -= HALF;
            } else if (left >= FIRST_QTR && right < THIRD_QTR) {
                bitsToFollow++;
                left -= FIRST_QTR;
                right -= FIRST_QTR;
            } else {
                break;
            }
            left *= 2;
            right = right * 2 + 1;

        }
    }

    protected void setComFreq() {
        comFreq[257] = 0;
        for (int i = 257; i > 0; i--) {
            comFreq[i - 1] = comFreq[i] + freq[i];
        }
    }

    private void writeFreq() throws IOException {
        for (int i = 0; i < 258; ++i) {
            writer.writeInt(freq[i]);
        }
    }

    private void finishEncoding() throws IOException {
        encodeSymbol(EOF_SYMBOL);
        bitsToFollow++;
        if (left < FIRST_QTR) {
            bitPlusFollow(true);
        } else {
            bitPlusFollow(false);
        }
        output.lastBits();
    }

    private void bitPlusFollow(boolean bit) throws IOException {
        output.writeBit(bit);
        while (bitsToFollow > 0) {
            output.writeBit(!bit);
            bitsToFollow--;
        }
    }

    private void resetReader() throws IOException {
        reader.close();
        reader = new DataInputStream(new BufferedInputStream(new FileInputStream(this.oldFile)));
    }
}
