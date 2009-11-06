/**
 * @copyright 2009 HarpSerg
 * @author HarpSerg
 */
import java.util.Comparator;
/**
 * @copyright 2009 HarpSerg
 * @author HarpSerg
 */
public class SingleKeyComp implements Comparator<Entry>{
    private int field;
    SingleKeyComp(int field){
        this.field = field;
    }
    public int compare(Entry ent1, Entry ent2){
        if(field == Entry.SURNAME)
            return ent1.getSN().compareTo(ent2.getSN());
        else
            return ent1.getTel().compareTo(ent2.getTel());
    }
}
