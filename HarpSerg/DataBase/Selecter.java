

/**
 *
 * @author HarpSerg
 */
import java.io.*;
import java.util.ArrayList;
import java.io.RandomAccessFile;
import java.util.ListIterator;
import java.util.Comparator;

public class Selecter {

    private RandomAccessFile reader;
    private BTree index;
    private static int LINE_SIZE = 23;

    Selecter() {
    }

    Selecter(String fileName) throws IOException {
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

    public void search(String from, String to, int number) throws IOException {
        ArrayList<Integer> lines;
        Entry rFrom = new Entry(from),
                rTo = new Entry(to);
        long begin = System.nanoTime();
        lines = index.find(rFrom, rTo);
        System.out.println(lines.size() + " results found in " + (System.nanoTime() - begin) + " ns\n" +
                "Press Enter to watch results.\nType \"q\" to stop watching.");
        showRecords(lines, number);
    }
    private void showRecords(ArrayList<Integer> lineNums, int n) throws IOException {
        BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
        ListIterator<Integer> iter = lineNums.listIterator();
        if (n == Main.ALL) {
            n = lineNums.size();
        }
        while (!inReader.readLine().equals("q")) {
            for (int i = 0; i < n && iter.hasNext(); ++i) {
                showNthLine(iter.next());
            }
        }
    }

    public void showNthLine(int n) throws IOException {
        reader.seek((n - 1) * LINE_SIZE);
        System.out.println(reader.readLine());
    }
}
