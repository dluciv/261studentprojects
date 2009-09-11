
package archivator;

/**
 *
 * @author Кирилл
 */
import java.util.*;

public class Encoder {
    private Tree[] alphabet = new Tree[256];
    private int[] codeLength = new int[256];


    Encoder( Tree[] alphabet ){
            this.alphabet = alphabet;
    }
    //ставит символам в соответствие канонические коды Хаффмена
    public void encode(){
        int[] minMaxLengths = getMinMaxLengths();
        encodeWithLengths(minMaxLengths[0], minMaxLengths[1]);
    }

    public Tree[] getAlphabet(){
        return alphabet;
    }

    public int[] getCodeLengths(){
        return codeLength;
    }

    public void setCodeLengths( int[] codeLengths){
        this.codeLength = codeLengths;
    }

    public void encodeWithLengths(int minLength,int maxLength) {
        HashMap<Integer, Integer> firstLeafs = getFirstLeafsNums(minLength, maxLength);
        ArrayList<Boolean> currCode = new ArrayList<Boolean>();
        for (int i = minLength; i <= maxLength; ++i) {
            TreeSet<Short> symbols = getSymbolsWithCodeLength(i);
            if (symbols.size() != 0) {
                int currLeafNum = firstLeafs.get(i);
                currCode = toBin(firstLeafs.get(i), i);
                alphabet[symbols.first()].code = currCode;
                symbols.remove(symbols.first());
                while (symbols.size() != 0) {
                    alphabet[symbols.first()].code = toBin(++currLeafNum, i);
                    symbols.remove(symbols.first());
                }
            }
        }
    }

    private int getCodeLength(int symbol) {
        Tree curr = alphabet[symbol];
        int length = 0;
        while (curr.parent != null) {
            curr = curr.parent;
            length++;
        }
        return length;
    }


    //возвращает число символов с заданной длиной кода( число узлов-листьев на заданном уровне дерева)
    public int symbolsWithCodeLength(int length) {
        int counter = 0;
        for (short i = 0; i < 256; ++i) {
            if (codeLength[i] == length) {
                ++counter;
            }
        }
        return counter;
    }

    //метод возвращает список листов, т.е. узлов с символами, на заданном уровне дерева
    private TreeSet<Short> getSymbolsWithCodeLength(int length) {
        TreeSet<Short> symbols = new TreeSet<Short>();
        for (short i = 0; i < 256; ++i) {
            if (codeLength[i] == length) {
                symbols.add(i);
            }
        }
        return symbols;
    }

    //перевод числа в двоичное
    public ArrayList<Boolean> toBin(int num, int length) {
        ArrayList<Boolean> binCode = new ArrayList<Boolean>();
        while (num != 0) {
            binCode.add(0, num % 2 == 1);
            num = num / 2;
        }
        if (binCode.size() < length) {
            for (int i = binCode.size(); i < length; i++) {
                binCode.add(0, false);
            }
        }

        return binCode;
    }
    // возвращает HAshMap< уровень дерева, порядковый номер первого узла-листа на уровне>
    public HashMap<Integer, Integer> getFirstLeafsNums(int minLength, int maxLength) {
        HashMap<Integer, Integer> firstLeafs = new HashMap<Integer, Integer>();
        int prevFirstNum = 0;
        firstLeafs.put(maxLength, 0);
        for (int i = maxLength + 1; i > 0; --i) {
            prevFirstNum = (prevFirstNum + symbolsWithCodeLength(i + 1)) / 2;
            firstLeafs.put(i, prevFirstNum);

        }
        return firstLeafs;
    }
    // возвращает минимальную и максимальную длины кодов
    private int[] getMinMaxLengths(){
        int[] lengths =new int[2];
        int minLength = 0, maxLength = 0;

        for (int i = 0; i < 256; ++i) {
            if (alphabet[i].freq != 0) {
                codeLength[i] = getCodeLength(i);
                if (alphabet[i].isLeaf()) {
                    if (codeLength[i] > maxLength) {
                        maxLength = codeLength[i];
                    }
                }
                if (codeLength[i] < minLength || minLength == 0) {
                    minLength = codeLength[i];
                }
            }
        }
        lengths[0] = minLength;
        lengths[1] = maxLength;
        return lengths;
    }
    public void printAllCodes() {
        for (int i = 0; i < 256; ++i) {
            if (!alphabet[i].code.isEmpty()) {
                alphabet[i].printCode();
            }
        }
    }

}
