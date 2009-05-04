package huffman;

/**
 *
 * @author Cyrill
 */
import java.io.*;

public class Archivator {

    static public void archivate(String file, String newFile) throws IOException {
        File oldFile = new File(file);
        if (oldFile.exists()) {
            DataInputStream rStream = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(new File(file))));
            if (rStream.available() == 0) {
                File archFile = new File(newFile);
                archFile.createNewFile();
            } else {
                HuffmanArchivator packman = new HuffmanArchivator();
                packman.archivateH(file, newFile);
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
                reader.close();
                HuffmanDearchivator unpackman = new HuffmanDearchivator();
                unpackman.dearchivateH(file, newFile);
            }
        } else {
            throw new FileNotFoundException();
        }
    }
}


