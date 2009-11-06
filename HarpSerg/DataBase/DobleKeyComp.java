/**
 * @copyright 2009 HarpSerg
 * @author HarpSerg
 */
import java.util.Comparator;
/**
 * @copyright 2009 HarpSerg
 * @author HarpSerg
 */
public class DobleKeyComp implements Comparator<Entry>{
    private int first;
    private int second;
    DobleKeyComp(int first, int second){
        this.first = first;
        this.second = second;
    }
    public int compare(Entry ent1, Entry ent2){
        SingleKeyComp c1 = new SingleKeyComp(first),
                     c2 = new SingleKeyComp(second);
        if(c1.compare(ent1, ent2) == 0)
            return c2.compare(ent1, ent2);
        else
            return c1.compare(ent1, ent2);
    }

}
