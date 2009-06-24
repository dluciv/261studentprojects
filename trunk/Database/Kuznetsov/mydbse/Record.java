package mydbse;

/**
 *
 * @author Kirill Kuznetsov
 */
import java.util.ArrayList;
public class Record implements Comparable<Record> {

    private String key;
    private ArrayList<Integer> line_num = new ArrayList<Integer>();
    static public int SURNAME = 0, TEL = 1;

    Record() {
    }

    Record(String record, int num, int field) {
        int i = record.indexOf(' ');
        if (field == SURNAME) {
            key = record.substring(0, i);
        } else {
            key = record.substring(19);
        }
        line_num.add(num);
    }

    Record(String key){
        this.key = key;
    }

    public int compareTo(Record k) {
        return this.key.compareTo(k.key);
    }

    public ArrayList<Integer> getLineNums(){
        return line_num;
    }

    public String getKey(){
        return key;
    }

    public void addToLineNums( ArrayList<Integer> newNums){
        line_num.addAll(newNums);
    }
}
