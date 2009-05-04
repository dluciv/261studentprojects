package huffman;



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
        if (args.length == 3) {
            if (args[0].equals("/a")) {
                Archivator.archivate(args[1], args[2]);
            } else if (args[0].equals("/d")) {
                Archivator.dearchivate(args[1], args[2]);
            } else {
                printHelp();
            }
        } else {
            printHelp();
        }
    }

    public static void printHelp() {
        System.out.println("to archivate: \"/a file_to_compress name_for_compressed_file\"");
        System.out.println("to dearchivate: \"/d file_to_unpack name_for_unpacked_file\"");
    }
}
