/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.table;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author Katerina
 */
public class TableRecord {

    private HashMap<Character, Vector<Integer>> storage;
    public static final char SYMBOL_END = '\t';

    public TableRecord() {
        storage = new HashMap<Character, Vector<Integer>>();
    }

    public void add(char c, Vector<Integer> v) {
        if (!storage.containsKey(c)) {
            storage.put(c, v);
        }
    }

    public void add(char c, int i) {
        if (storage.containsKey(c)) {
            Vector<Integer> v = storage.get(c);
            if (!v.contains(i)) {
                v.add(i);
            }
        } else {
            Vector<Integer> v = new Vector<Integer>();
            v.add(i);
            storage.put(c, v);
        }
    }

    public Set<Character> keySet() {
        return storage.keySet();
    }
}
