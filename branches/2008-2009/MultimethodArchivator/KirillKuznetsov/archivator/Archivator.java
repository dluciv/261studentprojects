package archivator;

/**
 *
 * @author Cyrill
 */
import java.io.*;

public class Archivator {

    static public void archivate(String method, String file, String newFile) throws IOException {
        File oldFileTest = new File(file);
        if (oldFileTest.exists()) {
            DataInputStream rStream = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(oldFileTest)));
            if (rStream.available() == 0) {
                File archFile = oldFileTest;
                archFile.createNewFile();
            } else {
                if (method.equals("huf")) {
                    HuffmanArchivator packman = new HuffmanArchivator(file, newFile);
                    packman.archivateH();
                } else {
                    ArithmeticArchivator packman = new ArithmeticArchivator(file, newFile);
                    packman.archivateA();
                }
            }
        } else {
            throw new FileNotFoundException();
        }

    }

    public static void dearchivate(String method, String file, String newFile) throws IOException {
        File oldFile = new File(file);
        if (oldFile.exists()) {
            DataInputStream reader = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(new File(file))));
            if (reader.available() == 0) {
                File archFile = new File(newFile);
            } else {
                if (method.equals("huf")) {
                    HuffmanDearchivator unpackman = new HuffmanDearchivator(file, newFile);
                    unpackman.dearchivateH();
                } else {
                    ArithmeticDearchivator unpackman = new ArithmeticDearchivator(file, newFile);
                    unpackman.unpack();
                }
            }
        } else {
            throw new FileNotFoundException();
        }
    }
}


