package archivator;

/**
 *
 * @author Кирилл
 */
import java.io.*;
import java.util.*;

public class HuffmanArchivator {

    protected Tree[] alphabet = new Tree[256];
    protected static char EOT = '$';//end-of-table symbol
    protected DataInputStream reader;
    protected DataOutputStream writer;
    protected File newFile, oldFile;
    protected Encoder hEncoder ;
    protected OutputBits  output;

    HuffmanArchivator (String oldFile, String newFile) throws IOException{
        this.newFile = new File(newFile);
        this.oldFile = new File(oldFile);
        reader = new DataInputStream(new BufferedInputStream(new FileInputStream(this.oldFile)));
        writer = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.newFile)));
        output = new OutputBits(writer);
    }

    public void archivateH() throws IOException {
        getAlphabet();
        growTree();
        hEncoder = new Encoder(alphabet);
        hEncoder.encode();
        alphabet = hEncoder.getAlphabet();
        //hEncoder.printAllCodes();
        newFile.createNewFile();
        compress(hEncoder.getCodeLengths());
    }
    private void resetReader() throws IOException {
        reader.close();
        reader = new DataInputStream(new BufferedInputStream(new FileInputStream(this.oldFile)));
    }

    private void getAlphabet() throws IOException {
            for (short i = 0; i <= 255; ++i) {
                alphabet[i] = new Tree(i, 0);
            }
            while (reader.available() != 0) {
                ++(alphabet[reader.read()].freq);
            }
            resetReader();
    }

    private void growTree() {
        TreeSet<Tree> sortedAlphabet = new TreeSet<Tree>();

        for (short i = 0; i <= 255; ++i) {
            if (alphabet[i].freq != 0) {
                sortedAlphabet.add(alphabet[i]);
            }
        }
        if (sortedAlphabet.size() != 1) {
            while (sortedAlphabet.size() != 1) {
                Tree min1 = sortedAlphabet.first();
                sortedAlphabet.remove(min1);
                Tree min2 = sortedAlphabet.first();
                sortedAlphabet.remove(min2);
                sortedAlphabet.add(new Tree(min1, min2));
            }
        } else {
            sortedAlphabet.add(new Tree(sortedAlphabet.first(), new Tree()));
        }
    }

    private void compress( int[] codeLengths) throws IOException {
        int crntByte = 0, codeLength;
        writeCodeTable(writer, codeLengths);
        while (reader.available() != 0) {
            crntByte = reader.read();
            codeLength = alphabet[crntByte].code.size();
            for(int i = 0;i < codeLength; ++i){
                output.writeBit(alphabet[crntByte].code.get(i));
            }
        }
        output.lastBits();
        reader.close();
        writer.close();
    }

    /*
     * записывает в файл таблицу кодов ( RLE )
     * */
    private void writeCodeTable(DataOutputStream write, int[] codeLength) throws IOException {
        int currLength = codeLength[0];
        int counter = 1;
        for (int i = 1; i < 256; ++i) {
            if (codeLength[i] != currLength) {
                write.write(currLength);
                write.write(counter - 1);
                currLength = codeLength[i];
                counter = 1;
            } else {
                counter++;
            }
        }
        write.write(currLength);
        write.write(counter - 1);

    }
}
