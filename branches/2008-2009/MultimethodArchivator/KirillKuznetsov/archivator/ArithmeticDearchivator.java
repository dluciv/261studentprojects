package archivator;

/**
 *
 * @author Kirill Kuznetsov
 */
import java.io.*;

public class ArithmeticDearchivator extends ArithmeticArchivator {

    private int code;
    private InputBits input;

    ArithmeticDearchivator() {
    }

    ArithmeticDearchivator(String oldFileName, String newFileName) throws FileNotFoundException {
        super(oldFileName, newFileName);
        input = new InputBits(reader);
    }

    public void unpack() throws IOException {
        int symbol;
        parseFreq();
        setComFreq();
        startDecoding();
        while(true){
           symbol = decodeSymbol();
           if(symbol == EOF_SYMBOL){
               writer.close();
               break;
           }else
               writer.writeByte(--symbol);
        }
    }

    private void parseFreq() throws IOException {
        for (int i = 0; i < 258; ++i) {
            freq[i] = reader.readInt();
            //System.out.println(freq[i]);
        }

    }

    private void startDecoding() throws IOException {
        code = 0;
        for(int i =1; i <= CODE_BITS; ++i)
            code = code*2 + input.readBit();
        left = 0;
        right = MAX_VALUE;
    }

    private int decodeSymbol() throws IOException {
        long range,cum;
        int symbol, bit;
        range = (right - left) + 1;
        cum = (((code - left) + 1) * comFreq[0] - 1) / range;
        for (symbol = 1; comFreq[symbol] > cum; symbol++);
        right = left + (range * comFreq[symbol - 1]) / comFreq[0] - 1;
        left = left + (range * comFreq[symbol]) / comFreq[0];
        //System.out.println( left + " - " + right);
        while(symbol != 257) {
            if (right < HALF) {
            } else if (left >= HALF) {
                code -= HALF;
                left -= HALF;
                right -= HALF;
            } else if (left >= FIRST_QTR && right < THIRD_QTR) {
                code -= FIRST_QTR;
                left -= FIRST_QTR;
                right -= FIRST_QTR;
            } else {
                break;
            }
            left = 2 * left;
            right = 2 * right + 1;
            bit = input.readBit();
            code = 2 * code + bit;
        }
        return symbol;
    }
}
