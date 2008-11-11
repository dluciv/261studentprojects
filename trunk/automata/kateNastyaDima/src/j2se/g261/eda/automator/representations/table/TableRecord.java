/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.representations.table;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author Katerina
 * @author Dmitry
 */
public class TableRecord {

    private HashMap<Character, Vector<Integer>> storage;
    public static final char SYMBOL_END = '!';
    public static final char SYMBOL_START = '.';

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

    public Vector<Integer> getVectorByChar(Character c){
        return storage.get(c);
    }
    
    Set<Character> keySet() {
        return storage.keySet();
    }
    
    @Override
    public String toString(){
        String s = "";
        
        Iterator<Entry<Character, Vector<Integer>>> i = storage.entrySet().iterator();
        
        while(i.hasNext()){
            Entry<Character, Vector<Integer>> e = i.next();
            s += "|" + e.getKey().toString() + "| " + e.getValue().toString() + "\n";
        }
        
        return s;
    }
    
    public Vector<Integer> getStateSet(char key){
            return storage.get(key);
    }
}
