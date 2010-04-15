package name.stepa.turing;

import java.util.HashMap;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class InfiniteTape {
    private HashMap<Integer, Character> map = new HashMap();

    public void set(Integer index, Character value) {
        if (!map.containsKey(index))
            map.remove(index);
        map.put(index, value);
    }

    public Character get(Integer index) {
        if (!map.containsKey(index))
            return '\0';
        else
            return map.get(index);
    }
}
