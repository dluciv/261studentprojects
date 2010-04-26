package name.stepa.turing;

import java.util.HashMap;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class InfiniteTape {

    public final static char TAPE_UNKNOWN_VALUE = '*';

    private HashMap<Integer, Character> map = new HashMap();

    private int position = 0;

    public InfiniteTape(String initialData) {
        char[] data = initialData.toCharArray();
        for (int i = 0; i < data.length; i++) {
            writeValue(data[i], i);
        }
    }

    public int getPosition() {
        return position;
    }

    public void moveRight() {
        position++;
    }

    public void moveLeft() {
        position--;
    }

    public void writeValue(Character value) {
        if (!map.containsKey(position))
            map.remove(position);
        map.put(position, value);
    }

    public Character readValue() {
        return readValue(position);
    }

    private Character readValue(int pos) {
        if (!map.containsKey(pos))
            return TAPE_UNKNOWN_VALUE;
        else
            return map.get(pos);
    }

    private void writeValue(Character value, int pos) {
        if (!map.containsKey(pos))
            map.remove(pos);
        map.put(pos, value);
    }


    @Override
    public String toString() {
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
            res.append(readValue(i));
            if (i == position)
                res.append('^');
        }

        return res.toString();
    }


}
