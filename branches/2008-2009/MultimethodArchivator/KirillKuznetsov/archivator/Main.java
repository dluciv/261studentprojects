package archivator;

/**
 *
 * @author Cyrill
 */
import java.io.*;
//import java.io.

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        if (args.length == 4) {
            if (args[0].equals("c")) {
                if (args[1].equals("huf")) {
                    Archivator.archivate("huf", args[2], args[3]);
                } else if (args[1].equals("arc")) {
                    Archivator.archivate("arc", args[2], args[3]);

                } else {
                    printHelp();
                    throw new IOException("wrong method");
                }
            } else if (args[0].equals("x")) {
                if (args[1].equals("huf")) {
                    Archivator.dearchivate("huf", args[2], args[3]);
                } else if (args[1].equals("arc")) {
                    Archivator.dearchivate("arc", args[2], args[3]);
                } else {
                    printHelp();
                    throw new IOException("wrong method");
                }
            } else {
                printHelp();
            }
        } else {
            printHelp();
        }
    }

    public static void printHelp() {
        System.out.println("MyArchivator Copyright (c)2009 Kirill Kuznetsov");
        System.out.println("How to use: java -jar [path to Archivator.jar] <command> <compression method> <input file> <output file>");//выполнение через java компилятор
        System.out.println("commands:\n c - to compress input file to output file\n x - to extract input file to output file");
        System.out.println("coding methods:\n huf - Huffman coding method\n arc - Arithmetic coding method ");

    }
}
