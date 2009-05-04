package huffman;

/**
 *
 * @author Кирилл
 */
import java.io.*;
import java.util.*;

public class HuffmanArchivator {

    private Tree[] alphabet = new Tree[256];
    public static char EOT = '$';//end-of-table symbol

    public void archivateH(String oldFile, String newFile) throws IOException {
        File compressed = new File(newFile);

        getAlphabet(oldFile);
        growTree();
        Encoder hEncoder = new Encoder(alphabet);
        hEncoder.encode();
        alphabet = hEncoder.getAlphabet();
        hEncoder.printAllCodes();
        compressed.createNewFile();
        compress(oldFile, compressed, hEncoder.getCodeLengths());
    }

    private void getAlphabet(String oldFile) {
        try {
            DataInputStream rStream = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(new File(oldFile))));

            for (short i = 0; i <= 255; ++i) {
                alphabet[i] = new Tree(i, 0);
            }
            while (rStream.available() != 0) {
                ++(alphabet[rStream.read()].freq);
            }
            rStream.close();
        } catch (IOException ie) {
        }
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

    private void compress(String oldFile, File newFile, int[] codeLengths) throws IOException {
        int crntByte = 0;
        int crntBit;

        List<Integer> code = new ArrayList<Integer>();
        List<Integer> tail = new ArrayList<Integer>();

        DataOutputStream writer = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(newFile)));
        DataInputStream reader = new DataInputStream(
                new BufferedInputStream(new FileInputStream(new File(oldFile))));

        writeCodeTable(writer, codeLengths);
        while (reader.available() != 0 || tail.size() > 8) {
            if (tail.size() != 0) {
                code.addAll(tail);
                tail.clear();
            }
            if (code.size() < 8) {
                code.addAll(alphabet[reader.read()].code);
            }
            if (code.size() >= 8) {
                for (byte i = 0; i <= 7; ++i) {
                    crntBit = code.get(i);
                    crntByte = crntByte * 2 + crntBit;
                }
                writer.write(crntByte);
                crntByte = 0;
                tail.addAll(code.subList(8, code.size()));
            } else {
                tail.addAll(code);
            }
            code.clear();
        }

        for (byte i = 0; i < tail.size(); ++i) {
            crntByte = crntByte * 2 + tail.get(i);
        }
        if (tail.isEmpty()) {
            writer.write(8);//последний байт - число "значящих битов " в предпоследнем байте
        } else {
            writer.write(crntByte);
            writer.write(tail.size());//последний байт - число "значящих битов " в предпоследнем байте
        }
        reader.close();
        writer.close();
    }

    //записывает в файл таблицу кодов ( RLA )
    private void writeCodeTable(DataOutputStream write, int[] codeLength) throws IOException {
        int currLength = codeLength[0];
        int counter = 1;
        for (int i = 1; i < 256; ++i) {
            if (codeLength[i] != currLength) {
                write.writeInt(currLength);
                write.writeChar('-');
                System.out.print(currLength + "-" + counter + ";");
                write.writeInt(counter);
                write.writeChar(';');
                currLength = codeLength[i];
                counter = 1;
            } else {
                counter++;
            }
        }
        write.writeInt(currLength);
        write.writeChar('-');
        write.writeInt(counter);
        write.writeChar(EOT);
        System.out.print(currLength + "-" + counter + ";");

    }
}
