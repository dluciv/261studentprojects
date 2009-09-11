package archivator;



/**
 *
 * @author Кирилл
 */
import java.util.*;
import java.io.*;

public class HuffmanDearchivator extends HuffmanArchivator {

    HashMap<Integer, ArrayList<Integer>> codeTable = new HashMap<Integer, ArrayList<Integer>>();
    int[] codeLength = new int[256];
    InputBits input;

    HuffmanDearchivator(String oldFIle, String newFile) throws IOException{
        super(oldFIle, newFile);
        input = new InputBits(reader);
        for(int i = 0; i < 256; ++i)
            alphabet[i] = new Tree( i, 0);
        hEncoder = new Encoder(alphabet);
    }

    public void dearchivateH() throws IOException {
        parseTable(reader);
        hEncoder.setCodeLengths(codeLength);
        hEncoder.encodeWithLengths(getMinMaxLengths()[0], getMinMaxLengths()[1]);
        unpack();
    }

    //восстанавливает длины кодов символов из записанной таблицы
    private void parseTable(DataInputStream reader) throws IOException {
        int length, symbolsNum, symbol = 0;
        while (symbol != 256) {
            length = reader.read();
            symbolsNum = reader.read() + 1;
            for (int i = 0; i < symbolsNum; ++i) {
                codeLength[symbol] = length;
                ++symbol;
            }
        }
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
    private int offs(int lvl) {
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

    private void unpack() throws IOException {
        int currSymbol, code, length, base, firstSymbol;
        HashMap<Integer, Integer> firstLeafNums = hEncoder.getFirstLeafsNums(getMinMaxLengths()[0], getMinMaxLengths()[1]);
        ArrayList<Integer> sortedSymbols = getSymbs();//символы отсортированные по длине кода и алфавиту

        while (true) {
            code = input.readBit();
            if(code == -1)
                break;
            length = 1;
            while(code < firstLeafNums.get(length)){
                code <<= 1;
                code += input.readBit();
                length++;
            }
            base = firstLeafNums.get(length);
            firstSymbol = offs(length);
            currSymbol = sortedSymbols.get(firstSymbol + code - base);
            writer.write(currSymbol);
        }
        writer.close();
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

