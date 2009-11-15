/**
 * @copyright 2009 HarpSerg
 * @author HarpSerg
 */
public class Entry {     
    private String sn;
    private String tel;

    public Entry(String sn, String tel) {
        this.sn = sn;
        this.tel = tel;
    }

    public String get(Keys key) {
        if (key.equals(Keys.SURNAME)) {
            return sn;
        } else if (key.equals(Keys.TEL)) {
            return tel;
        } else {
            return null;
        }
    }

    public String getTel() {
        return tel;
    }

    public String getSN() {
        return sn;
    }
}
