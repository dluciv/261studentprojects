package huffman;

/**
 *
 * @author Кирилл
 */
import java.io.*;
import java.util.*;

public class Tester {

    private static String TEST_DIR_PATH = "D:\\Test\\";

    private static void makeAllSymbolsFile() throws IOException {
        File allSymbolsFile = new File(TEST_DIR_PATH + "allSymbols");
        allSymbolsFile.createNewFile();
        DataOutputStream writer = new DataOutputStream(new BufferedOutputStream(
                new FileOutputStream(allSymbolsFile)));
        byte symbol = -128;
        for (int i = 0; i < 256; ++i, ++symbol) {
            writer.write(symbol);
        }
        writer.close();
    }

    private static void makeRandomFile() throws IOException {
        File allSymbolsFile = new File(TEST_DIR_PATH + "random");
        allSymbolsFile.createNewFile();
        DataOutputStream writer = new DataOutputStream(new BufferedOutputStream(
                new FileOutputStream(allSymbolsFile)));
        Random generator = new Random();
        for (int j = 0; j <= generator.nextInt(2000000000); ++j) {
            writer.write(generator.nextInt(255));
        }
        writer.close();
    }

    public static void makeLeftTreeFile() throws IOException {
        File allSymbolsFile = new File(TEST_DIR_PATH + "leftTree");
        allSymbolsFile.createNewFile();
        DataOutputStream writer = new DataOutputStream(new BufferedOutputStream(
                new FileOutputStream(allSymbolsFile)));
        byte symbol = 0;
        int fib1 = 1, fib2 = 1, fib3 = fib1 + fib2;
        for (int i = 0; i < 20; ++i, ++symbol) {
            for (int j = 0; j < fib1; ++j) {
                writer.write(symbol);
            }
            fib1 = fib2;
            fib2 = fib3;
            fib3 = fib1 + fib2;
        }
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        //makeAllSymbolsFile();
        //makeLeftTreeFile();
        //makeRandomFile();
        //Archivator.archivate(TEST_DIR_PATH+"random", TEST_DIR_PATH+"random.huf");
        //Archivator.archivate(TEST_DIR_PATH+"bigOne.avi", TEST_DIR_PATH+"bigOne.avi.huf");
        //Archivator.archivate(TEST_DIR_PATH+"oneSymbol.txt", TEST_DIR_PATH+"oneSymbol.txt.huf");
        //Archivator.archivate(TEST_DIR_PATH + "allSymbols", TEST_DIR_PATH + "allSymbols.huf");
        //Archivator.archivate(TEST_DIR_PATH + "leftTree", TEST_DIR_PATH + "leftTree.huf");
        //Archivator.archivate(TEST_DIR_PATH + "empty.txt", TEST_DIR_PATH + "empty.txt.huf");
        //Archivator.archivate(TEST_DIR_PATH + "Text.txt", TEST_DIR_PATH +"Text.txt.huf");
        //Archivator.dearchivate(TEST_DIR_PATH + "empty.txt.huf", TEST_DIR_PATH + "empty.huf.txt");
        //Archivator.dearchivate(TEST_DIR_PATH + "oneSymbol.txt.huf", TEST_DIR_PATH + "oneSymbol.huf.txt");
        //Archivator.dearchivate(TEST_DIR_PATH + "random.huf", TEST_DIR_PATH + "random_");
        //Archivator.dearchivate(TEST_DIR_PATH + "allSymbols.huf", TEST_DIR_PATH + "allSymbols_");
        //Archivator.dearchivate(TEST_DIR_PATH + "leftTree.huf", TEST_DIR_PATH + "leftTree_");
        //Archivator.dearchivate(TEST_DIR_PATH + "Text.txt.huf", TEST_DIR_PATH+"Text.huf.txt");
        //Archivator.archivate(TEST_DIR_PATH + "random.txt", TEST_DIR_PATH+"random.txt.arc");
        //Archivator.archivate(TEST_DIR_PATH + "bigOne.avi", TEST_DIR_PATH+"bigOne.avi.arc");
        //Archivator.archivate(TEST_DIR_PATH + "oneSymbol.txt", TEST_DIR_PATH+"oneSymbol.txt.arc");
        //Archivator.archivate(TEST_DIR_PATH + "allSymbols", TEST_DIR_PATH + "allSymbols.arc");
        //Archivator.archivate(TEST_DIR_PATH + "leftTree", TEST_DIR_PATH + "leftTree.arc");
        //Archivator.archivate(TEST_DIR_PATH + "empty.txt", TEST_DIR_PATH + "empty.txt.arc");
        //Archivator.archivate(TEST_DIR_PATH + "Text.txt", TEST_DIR_PATH +"Text.txt.arc");
        //Archivator.dearchivate(TEST_DIR_PATH + "empty.txt.arc", TEST_DIR_PATH + "empty.arc.txt");
        //Archivator.dearchivate(TEST_DIR_PATH + "oneSymbol.txt.arc", TEST_DIR_PATH + "oneSymbol.arc.txt");
        //Archivator.dearchivate(TEST_DIR_PATH + "random.txt.arc", TEST_DIR_PATH + "random.arc.txt");
        //Archivator.dearchivate(TEST_DIR_PATH + "allSymbols.arc", TEST_DIR_PATH + "allSymbols.arc.");
        //Archivator.dearchivate(TEST_DIR_PATH + "leftTree.arc", TEST_DIR_PATH + "leftTree.arc.");
        //Archivator.dearchivate(TEST_DIR_PATH + "Text.txt.arc", TEST_DIR_PATH+"Text.arc.txt");
    }
}
