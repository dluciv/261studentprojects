/**
 * @copyright 2009 HarpSerg
 * @author HarpSerg
 */
public class Entry {   
    static public final int SURNAME = 0,  TEL = 1;
    private String sn;
    private String tel;

    public Entry(String sn, String tel) {
        this.sn = sn;
        this.tel = tel;
    }

    public Entry(String line, int num) {
        int i = line.indexOf(' ');
        sn = line.substring(0, i);
        tel = line.substring(15);        
    }
    public Entry(String line){
        String[] args = line.split(":");
        if(args.length == 1){
            this.sn = args[0];
            this.tel = args[0];
        } else {
            this.sn = args[0];
            this.tel = args[1];
        }
    }    

    public String getTel() {
        return tel;
    }

    public String getSN() {
        return sn;
    }

   
}
