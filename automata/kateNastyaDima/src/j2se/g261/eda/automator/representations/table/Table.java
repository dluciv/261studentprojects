/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.representations.table;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 *
 * @author Katerina
 */
public class Table {

    private HashMap<Integer, TableRecord> storage;

    public Table() {
        storage = new HashMap<Integer, TableRecord>();
    }

    public void add(int i, TableRecord j) {
        if (!storage.containsKey(i)) {
            storage.put(i, j);
        }
    }

    public void fillTable() {
        HashSet<Character> set = collectCaracterKeys();

        Iterator<java.util.Map.Entry<Integer, TableRecord>> a = storage.entrySet().iterator();
        while (a.hasNext()) {
            TableRecord t = a.next().getValue();

            Iterator<Character> b = set.iterator();
            while (b.hasNext()) {
                t.add(b.next(), new Vector<Integer>());
            }
        }

    }

    public void clear() {
        storage.clear();
    }

    public HashSet<Character> collectCaracterKeys() {
        HashSet<Character> set = new HashSet<Character>();
        Iterator<java.util.Map.Entry<Integer, TableRecord>> a = storage.entrySet().iterator();
        while (a.hasNext()) {
            TableRecord t = a.next().getValue();
            set.addAll(t.keySet());
        }
        return set;

    }

    @Override
    public String toString() {
        String s = "";
        Iterator<java.util.Map.Entry<Integer, TableRecord>> i = storage.entrySet().iterator();

        while (i.hasNext()) {
            java.util.Map.Entry<Integer, TableRecord> e = i.next();
            s += e.getKey().toString() + "\n";
            s += e.getValue().toString() + "\n";
        }
        return s;
    }

    public ConcurrentSkipListSet<Entry<Integer, TableRecord>> listOfRecords() {
        ConcurrentSkipListSet<Entry<Integer, TableRecord>> set = new ConcurrentSkipListSet<Entry<Integer, TableRecord>>();
        Iterator<java.util.Map.Entry<Integer, TableRecord>> i = storage.entrySet().iterator();
        while (i.hasNext()) {
            java.util.Map.Entry<Integer, TableRecord> e = i.next();
            set.add(new Entry<Integer, TableRecord>(e.getKey(), e.getValue()));
        }
        return set;
    }

    public Vector<Integer> getStateSet(int key, char key2) {
        return storage.get(key).getStateSet(key2);
    }

    public class Entry<K extends Comparable, V> implements Comparable<Entry> {

        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public int compareTo(Entry o) {
            return key.compareTo(o.getKey());
        }
    }
}
