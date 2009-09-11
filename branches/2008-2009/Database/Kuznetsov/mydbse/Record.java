package mydbse;

/**
 *
 * @author Kirill Kuznetsov
 */
import java.util.ArrayList;

public class Record {
    private ArrayList<Integer> line_num = new ArrayList<Integer>();
    static public final int SURNAME = 0,  TEL = 1;
    private String sn;
    private String tel;

    Record(String sn, String tel) {
        this.sn = sn;
        this.tel = tel;
    }

    Record(String line, int num) {
        int i = line.indexOf(' ');
        sn = line.substring(0, i);
        tel = line.substring(15);
        line_num.add(num);
    }
    Record(String line){
        String[] args = line.split(":");
        if(args.length == 1){
            this.sn = args[0];
            this.tel = args[0];
        } else {
            this.sn = args[0];
            this.tel = args[1];
        }
    }

    public ArrayList<Integer> getLineNums() {
        return line_num;
    }

    public void addToLineNums(ArrayList<Integer> newNums) {
        line_num.addAll(newNums);
    }

    public String getTel() {
        return tel;
    }

    public String getSN() {
        return sn;
    }

    public void setTel(String tel){
        this.tel = tel;
    }

    public void setSN(String sn){
        this.sn = sn;
    }
}
