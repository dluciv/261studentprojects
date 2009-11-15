/**
 * @copyright 2009 HarpSerg
 * @author HarpSerg
 */
import java.util.Comparator;

public class KeyComparator implements Comparator<Entry>{
    private Keys key;
    public KeyComparator(Keys key){
        this.key = key;
    }
    public int compare(Entry ent1, Entry ent2) {
        return ent1.get(key).compareTo(ent2.get(key));
    }
}
