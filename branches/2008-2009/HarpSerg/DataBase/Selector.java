/**
 * @copyright 2009 HarpSerg
 * @author HarpSerg
 */
import java.io.*;
import java.util.ArrayList;
import java.io.RandomAccessFile;
import java.util.Comparator;

public class Selector {

    private RandomAccessFile reader;
    private BTree index;
    private static int LINE_SIZE = 23;
    
    public Selector(String fileName) throws IOException {
        reader = new RandomAccessFile(fileName, "r");
    }

    public void makeIndex(Comparator c) throws IOException {
        long left = reader.length();
        String key;
        index = new BTree(c);
        reader.seek(0);
        while (left > 0) {
            key = reader.readLine();
            left -= LINE_SIZE;
            Entry newKey = new Entry(key.substring(0, key.indexOf(' ')), key.substring(15));
            index.addKey(newKey);            
        }
        index = index.getRoot();
    }    

    public ArrayList<Entry> search(String from, String to, int number) throws IOException {
        return index.find(new Entry(from, from), new Entry(to, to));
    }    
}
