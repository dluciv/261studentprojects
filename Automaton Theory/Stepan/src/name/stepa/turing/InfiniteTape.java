package name.stepa.turing;

import java.util.HashMap;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class InfiniteTape {

    public final static char TAPE_UNKNOWN_VALUE = '*';

    private HashMap<Integer, Character> map = new HashMap();

    public synchronized void set(Integer index, Character value) {
        if (!map.containsKey(index))
            map.remove(index);
        map.put(index, value);
    }

    public synchronized Character get(Integer index) {
        if (!map.containsKey(index))
            return TAPE_UNKNOWN_VALUE;
        else
            return map.get(index);
    }

    public String getStateString() {
        return getStateString(-1);
    }

    public synchronized String getStateString(int pos) {
        StringBuilder res = new StringBuilder();

        int maxIndex = 0;
        int minIndex = 0;
        for (Integer i : map.keySet()) {
            if (maxIndex < i)
                maxIndex = i;
            if (minIndex > i)
                minIndex = i;
        }

        for (int i = minIndex - 3; i <= maxIndex + 3; i++) {
            res.append(get(i));
        }

        if (pos != -1) {
            res.append('\n');
            for (int i = minIndex - 3; i < pos; i++)
                res.append(' ');
            res.append('^');
        }

        return res.toString();
    }

}
