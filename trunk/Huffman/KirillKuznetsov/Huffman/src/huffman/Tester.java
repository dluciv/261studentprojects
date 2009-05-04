
package huffman;

/**
 *
 * @author Кирилл
 */
import java.io.*;
public class Tester {
    private static String TEST_DIR_PATH = "D:\\java\\";
    public static void makeAllSymbolsFile() throws IOException {
        File allSymbolsFile = new File(TEST_DIR_PATH + "allSymbols");
        allSymbolsFile.createNewFile();
        DataOutputStream writer = new DataOutputStream(new BufferedOutputStream(
                new FileOutputStream (allSymbolsFile)));
        byte symbol = -128;
        for(int i = 0 ; i < 256; ++i, ++symbol)
            writer.write(symbol);
        writer.close();
    }
    public static void makeLeftTreeFile() throws IOException {
        File allSymbolsFile = new File(TEST_DIR_PATH + "leftTree");
        allSymbolsFile.createNewFile();
        DataOutputStream writer = new DataOutputStream(new BufferedOutputStream(
                new FileOutputStream (allSymbolsFile)));
        byte symbol = 0;
        int fib1 = 1, fib2 = 1, fib3 = fib1 + fib2;
        for(int i = 0 ; i < 20; ++i, ++symbol){
            for(int j = 0; j < fib1; ++j)
                writer.write(symbol);
            fib1 = fib2;
            fib2 = fib3;
            fib3 = fib1 + fib2;
        }
        writer.close();
    }
    public static void main(String[] args) throws IOException{
        //makeAllSymbolsFile();
        //makeLeftTreeFile();
        //Archivator.archivate(TEST_DIR_PATH+"qwe.txt", TEST_DIR_PATH+"qwe.huf");
        //Archivator.archivate(TEST_DIR_PATH+"34.txt", TEST_DIR_PATH+"34.huf");
        //Archivator.archivate(TEST_DIR_PATH+"aaa.txt", TEST_DIR_PATH+"aaa.huf");
        //Archivator.archivate(TEST_DIR_PATH + "allSymbols", TEST_DIR_PATH + "allSymbols.huf");
        //Archivator.archivate(TEST_DIR_PATH + "leftTree", TEST_DIR_PATH + "leftTree.huf");
        //Archivator.archivate(TEST_DIR_PATH + "empty.txt", TEST_DIR_PATH + "empty.huf");
        //Archivator.archivate(TEST_DIR_PATH+"7.txt", TEST_DIR_PATH+"7.huf");
        //Archivator.dearchivate(TEST_DIR_PATH + "empty.txt", TEST_DIR_PATH + "empty.d.huf");
        //Archivator.dearchivate(TEST_DIR_PATH + "aaa.huf", TEST_DIR_PATH + "aaa.huf.txt");
        //Archivator.dearchivate(TEST_DIR_PATH + "qwe.huf", TEST_DIR_PATH + "qwe.huf.txt");
        //Archivator.dearchivate(TEST_DIR_PATH + "allSymbols.huf", TEST_DIR_PATH + "allSymbols.d");
        //Archivator.dearchivate(TEST_DIR_PATH + "leftTree.huf", TEST_DIR_PATH + "leftTree.d");
        //Archivator.dearchivate(TEST_DIR_PATH+"34.huf", TEST_DIR_PATH+"34.d.huf");
        //Archivator.dearchivate(TEST_DIR_PATH+"7.huf", TEST_DIR_PATH+"7.huf.txt");
    }

}
