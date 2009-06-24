package mydbse;

/**
 *
 * @author Kirill Kuznetsov
 */
import java.io.*;
import java.util.ArrayList;
import java.io.RandomAccessFile;

public class Searcher {

    private RandomAccessFile reader;
    private BTree index = new BTree();
    private int keyType = -1;
    private static int LINE_SIZE = 27;

    Searcher() {
    }

    Searcher(String fileName) throws IOException {
        reader = new RandomAccessFile(fileName, "r");
    }

    public void makeIndex() throws IOException {
        int i = 1;
        long left = reader.length();
        String key;

        index = new BTree();
        reader.seek(0);
        while (left > 0 ) {
            key = reader.readLine();
            left -= LINE_SIZE;
            Record newKey = new Record(key, i, keyType);
            index.addKey(newKey);
            ++i;
//            index.getRoot().printTree(0);
//            System.out.println();
        }
        index = index.getRoot();
        
    }

    public ArrayList<Integer> search(String from, String to) throws IOException {
        ArrayList<Integer> lines;
        Record rFrom = new Record(from),
                rTo = new Record(to);
        long begin = System.nanoTime();
        lines = index.find(rFrom, rTo);
        System.out.println(lines.size() + " results found in " + (System.nanoTime() - begin));
        return lines;
    }

    public void showNthLine(int n) throws IOException {
        reader.seek((n-1) * LINE_SIZE);
        System.out.println(reader.readLine());
    }

    public void setKeyType(int newKeyType) {
        keyType = newKeyType;
    }
}
