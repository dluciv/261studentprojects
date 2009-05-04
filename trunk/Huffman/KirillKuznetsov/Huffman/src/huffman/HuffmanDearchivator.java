package huffman;

/**
 *
 * @author Кирилл
 */
import java.util.*;
import java.io.*;

public class HuffmanDearchivator {

    HashMap<Integer, ArrayList<Integer>> codeTable = new HashMap<Integer, ArrayList<Integer>>();
    int[] codeLength = new int[256];
    Tree[] alphabet = new Tree[256];

    HuffmanDearchivator() {
        for (int i = 0; i < 256; ++i) {
            alphabet[i] = new Tree(i, 0);
        }
    }

    public void dearchivateH(String oldFile, String newFile) throws IOException {
        File unpacked = new File(newFile);
        unpacked.createNewFile();
        DataInputStream reader = new DataInputStream(new BufferedInputStream(new FileInputStream(new File(oldFile))));
        parseTable(reader);
        Encoder hEncoder = new Encoder(alphabet);
        hEncoder.setCodeLengths(codeLength);
        hEncoder.encodeWithLengths(getMinMaxLengths()[0], getMinMaxLengths()[1]);
        hEncoder.printAllCodes();
        unpack(reader, newFile, hEncoder);
    //upack(oldFile, compressed);
    }

    //восстанавливает длины кодов символов из записанной таблицы
    private void parseTable(DataInputStream reader) throws IOException {
        char next = ' ';
        int length;
        int symbolsNum;
        int symbol = 0;
        while (next != HuffmanArchivator.EOT) {
            length = reader.readInt();
            reader.readChar();
            symbolsNum = reader.readInt();
            for (int i = 0; i < symbolsNum; ++i) {
                codeLength[symbol] = length;
                ++symbol;
            }
            next = reader.readChar();
        }
        printCodeLengthsTable();
    }

    private void printCodeLengthsTable() {
        for (int i = 0; i < 256; ++i) {
            System.out.print(i + "-" + codeLength[i] + ";");
        }
        System.out.print('\n');
    }

    private int[] getMinMaxLengths() {
        int minLength = 0, maxLength = 0;
        int[] minMaxLengths = new int[2];
        for (int i = 0; i < 256; ++i) {
            if (minLength == 0 || (codeLength[i] != 0 && codeLength[i] < minLength)) {
                minLength = codeLength[i];
            }
            if (codeLength[i] > maxLength) {
                maxLength = codeLength[i];
            }
        }
        minMaxLengths[0] = minLength;
        minMaxLengths[1] = maxLength;
        return minMaxLengths;
    }

//    private void unpack(DataInputStream reader, String newFile, Encoder hEncoder) throws IOException {
//        DataOutputStream writer = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(newFile)));
//
//        int currSymbol, code, readed, length, base, firstSymbol, lastByte;
//
//        HashMap<Integer, Integer> firstLeafNums = hEncoder.getFirstLeafsNums(getMinMaxLengths()[0], getMinMaxLengths()[1]);
//        readed = reader.read();
//        ArrayList<Integer> buffer = hEncoder.toBin(readed, 8);
//
//        ArrayList<Integer> sortedSymbols = getSymbs();//символы отсортированные по длине кода и алфавиту
//        while (reader.available() > 0 || !buffer.isEmpty()) {
//            if (buffer.isEmpty()) {
//                readed = reader.read();
//                if (reader.available() == 1) {
//                    lastByte = reader.read();
//                    if (lastByte == 0) {
//                        buffer = hEncoder.toBin(readed, 8);
//                    } else {
//                        buffer = hEncoder.toBin(readed, lastByte);
//                    }
//                } else {
//                    buffer = hEncoder.toBin(readed, 8);
//                }
//            }
//            code = buffer.get(0);
//            buffer.remove(0);
//            length = 1;
//            while (code < firstLeafNums.get(length)) {
//                if (buffer.isEmpty()) {
//                    readed = reader.read();
//                    if (reader.available() == 1) {
//                        lastByte = reader.read();
//                        if (lastByte == 0) {
//                            buffer = hEncoder.toBin(readed, 8);
//                        } else {
//                            buffer = hEncoder.toBin(readed, lastByte);
//                        }
//                    } else {
//                        buffer = hEncoder.toBin(readed, 8);
//                    }
//                }
//                code = code * 2 + buffer.get(0);
//                length += 1;
//                buffer.remove(0);
//            }
//            base = firstLeafNums.get(length);
//            firstSymbol = offs(length, hEncoder);
//            currSymbol = sortedSymbols.get(firstSymbol + code - base);
//            writer.write(currSymbol);
//        }
//        writer.close();
//    }
    private int offs(int lvl, Encoder hEncoder) {
        int a = symbols();
        for (int i = lvl; i > 0; --i) {
            a -= hEncoder.symbolsWithCodeLength(i);
        }
        return a;
    }

    private int symbols() {
        int symbols = 0;
        for (int i = 0; i < 256; ++i) {
            if (codeLength[i] != 0) {
                ++symbols;
            }
        }
        return symbols;

    }

    private ArrayList<Integer> getSymbs() {
        TreeSet<Symbol> symbs = new TreeSet<Symbol>();//символы отсортированные по длине кода и алфавиту
        for (int i = 0; i < 256; ++i) {
            if (codeLength[i] != 0) {
                symbs.add(new Symbol(codeLength[i], i));
            }
        }

        ArrayList<Integer> sortedSymbols = new ArrayList<Integer>();
        for (Symbol symb : symbs) {
            sortedSymbols.add(symb.symb);
        }
        return sortedSymbols;
    }

    private void unpack(DataInputStream reader, String newFile, Encoder hEncoder) throws IOException {
        DataOutputStream writer = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(newFile)));
        int readed,lastByte,currSymbol, code, length, base, firstSymbol;
        ArrayList<Integer> buffer  = new ArrayList<Integer>();
        HashMap<Integer, Integer> firstLeafNums = hEncoder.getFirstLeafsNums(getMinMaxLengths()[0], getMinMaxLengths()[1]);
        ArrayList<Integer> sortedSymbols = getSymbs();//символы отсортированные по длине кода и алфавиту

        readed = reader.read();
        if (reader.available() == 1) {
            lastByte = reader.read();
            buffer =hEncoder.toBin(readed, lastByte);
            
        } else {
            buffer = hEncoder.toBin(readed, 8);
        }
        while (reader.available() > 0 || !buffer.isEmpty()) {
        if (buffer.isEmpty()) {
                buffer = fillBuffer(hEncoder, reader);
            }
            code = buffer.get(0);
            buffer.remove(0);
            length = 1;
            while (code < firstLeafNums.get(length)) {
                if (buffer.isEmpty()) {
                    buffer = fillBuffer(hEncoder, reader);
                }
                code = code * 2 + buffer.get(0);
                length += 1;
                buffer.remove(0);
            }
            base = firstLeafNums.get(length);
            firstSymbol = offs(length, hEncoder);
            currSymbol = sortedSymbols.get(firstSymbol + code - base);
            writer.write(currSymbol);
        }
        writer.close();
    }


    private ArrayList<Integer> fillBuffer(Encoder hEncoder, DataInputStream reader) throws IOException {
        int readed, lastByte;
        readed = reader.available();
        readed = reader.read();
        if (reader.available() == 1) {
            lastByte = reader.read();
            return hEncoder.toBin(readed, lastByte);
        } else {
            return hEncoder.toBin(readed, 8);
        }
    }
}

class Symbol implements Comparable<Symbol> {

    int symb;
    int length;

    Symbol(int length, int symb) {
        this.length = length;
        this.symb = symb;

    }

    public int compareTo(Symbol s) {
        if (length > s.length) {
            return -1;
        } else if (length < s.length) {
            return 1;
        } else if (symb > s.symb) {
            return 1;
        } else if (symb < s.symb) {
            return -1;
        } else {
            return 0;
        }

    }
}

