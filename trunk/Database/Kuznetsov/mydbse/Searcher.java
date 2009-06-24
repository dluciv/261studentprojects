package mydbse;

/**
 *
 * @author Kirill Kuznetsov
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Searcher {

    private BufferedReader reader;
//    private BufferedWriter writer;
    private BTree index = new BTree();
    private String baseFile;
    private int keyType = -1;
    private static String FOLDER = "D:\\DB\\";

    Searcher() {
    }

    Searcher(String fileName) throws IOException {
        baseFile = fileName;
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(FOLDER + fileName))));
//        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(index)));
    }

    public void makeIndex() throws IOException {
        int i = 1;
        String key;

        index = new BTree();
        refreshReader();
        while (reader.ready()) {
            key = reader.readLine();
            Record newKey = new Record(key, i, keyType);
            index.addKey(newKey);
            ++i;
        }
        reader.close();
//        System.out.println(i);
        index = index.getRoot();
    }

    public ArrayList<Integer> search(String from, String to, int n) throws IOException{
        ArrayList<Integer> lines;
        Record rFrom = new Record(from),
               rTo = new Record(to);

        lines = index.find(rFrom,rTo);
        return lines;
    }

    public void showNthLine(int  n) throws IOException {
            refreshReader();
            for (int i = 1; i < n; ++i) {
                reader.readLine();
            }
            System.out.println(reader.readLine());
    }

    private void refreshReader() throws FileNotFoundException{
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(FOLDER + baseFile))));
    }

   public void setKeyType(int newKeyType){
        keyType = newKeyType;
    }

//    public void searchKey(String key) throws IOException{
//        TreeSet<Integer> lines = index.findRecord(key);
//        showRecords(lines);
//    }

}
