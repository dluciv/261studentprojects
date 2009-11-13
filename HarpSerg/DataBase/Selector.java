/**
 * @copyright 2009 HarpSerg
 * @author HarpSerg
 */
import java.io.*;
import java.util.ArrayList;
import java.io.RandomAccessFile;
import java.util.ListIterator;
import java.util.Comparator;

public class Selector {

    private RandomAccessFile reader;
    private BTree index;
    private static int LINE_SIZE = 23;
    
    public Selector(String fileName) throws IOException {
        reader = new RandomAccessFile(fileName, "r");
    }

    public void makeIndex(Comparator c) throws IOException {
        int i = 1;
        long left = reader.length();
        String key;

        index = new BTree(c);
        reader.seek(0);
        while (left > 0) {
            key = reader.readLine();
            left -= LINE_SIZE;
            Entry newKey = new Entry(key, i);
            index.addKey(newKey);
            ++i;
        }
        index = index.getRoot();
    }    

    public ArrayList<Entry> search(String from, String to, int number) throws IOException {
        return index.find(new Entry(from), new Entry(to));
    }

    public void showRecords(ArrayList<Entry> entryestoshow, int n) throws IOException {
        BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
        ListIterator<Entry> iter = entryestoshow.listIterator();
        if (n == Main.ALL) {
            n = entryestoshow.size();
        }
        if (entryestoshow.size() != 0) {
            while (!inReader.readLine().equals("q")) {
                for (int i = 0; i < n && iter.hasNext(); ++i) {
                    showNthLine(iter.next());
                }
            }
        }
    }

    public void showNthLine(Entry n) throws IOException {        
        System.out.println(n.getSN() + " " + n.getTel());
    }
}
