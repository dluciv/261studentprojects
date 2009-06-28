package mydbse;

/**
 *
 * @author Kirill Kuznetsov
 */
import java.util.ArrayList;

public class Record implements Comparable<Record> {

    private cKey key;
    private ArrayList<Integer> line_num = new ArrayList<Integer>();
    static public int SURNAME = 0, TEL = 1;

    Record() {
    }

    Record(String record, int num, int field) {
        if (field == SURNAME) {
        int i = record.indexOf(' ');
            key = new Surname(record.substring(0, i));
        } else {
            key = new Tel( record.substring(19));
        }
        line_num.add(num);
    }
    Record(String record, int field) {
        if (field == SURNAME) {
            key = new Surname(record);
        } else {
            key = new Tel(record);
        }
    }

//    Record(String key){
//        this.key = key;
//    }

    public int compareTo(Record k) {
        return this.key.compareTo(k.key);
         
    }

    public ArrayList<Integer> getLineNums(){
        return line_num;
    }

    public cKey getKey(){
        return key;
    }

    public void addToLineNums( ArrayList<Integer> newNums){
        line_num.addAll(newNums);
    }
}
