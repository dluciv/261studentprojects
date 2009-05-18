package huffman;

/**
 *
 * @author Cyrill
 */
import java.io.*;

public class Archivator {

    static public void archivate(String file, String newFile) throws IOException {
        File oldFileTest = new File(file);
        if (oldFileTest.exists()) {
            DataInputStream rStream = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(oldFileTest)));
            if (rStream.available() == 0) {
                File archFile = oldFileTest;
                archFile.createNewFile();
            } else {
                HuffmanArchivator packman = new HuffmanArchivator( file, newFile);
                packman.archivateH();
            }
        } else {
            throw new FileNotFoundException();
        }

    }

    public static void dearchivate(String file, String newFile) throws IOException {
        File oldFile = new File(file);
        if (oldFile.exists()) {
            DataInputStream reader = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(new File(file))));
            if (reader.available() == 0) {
                File archFile = new File(newFile);
            } else {
               HuffmanDearchivator unpackman = new HuffmanDearchivator(file, newFile);
               unpackman.dearchivateH();
            }
        } else {
            throw new FileNotFoundException();
        }
    }
}


