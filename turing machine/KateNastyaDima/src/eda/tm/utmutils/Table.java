/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eda.tm.utmutils;

import eda.tm.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 *
 * @author nastya
 */
public class Table {

    private HashMap<Integer, TableRecord> storage;


    public Table(int size) {
        storage = new HashMap<Integer, TableRecord>();
        for (int i = 1; i <= size; i++) {
            storage.put(i, new TableRecord());            
        }
    }

    public void add(int stateNumber, char symbol, StateSymbolMove ssm) {
//        if (!storage.containsKey(stateNumber)) {
//            storage.put(stateNumber, new TableRecord());
//        }
        TableRecord t = storage.get(stateNumber);
        t.add(symbol, ssm);
    }

    public void add(int i, TableRecord j) {
        if (!storage.containsKey(i)) {
            storage.put(i, j);
        }
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

    public void fillTable() {
        HashSet<Character> set = collectCaracterKeys();

        Iterator<java.util.Map.Entry<Integer, TableRecord>> a = storage.entrySet().iterator();
        while (a.hasNext()) {
            TableRecord t = a.next().getValue();

            Iterator<Character> b = set.iterator();
            while (b.hasNext()) {
                t.add(b.next(), null);
            }
        }

    }

    @Override
    public String toString() {
        String s = "ccC";

        ConcurrentSkipListSet<Entry<Integer, TableRecord>> list = listOfRecords();
        System.out.println("|||||||||||||");
        System.out.println(list.size());
        for (Table.Entry<Integer, TableRecord> e : list) {
            s += e.getValue() + "cc";
        }

        return s;
    }

    private ConcurrentSkipListSet<Entry<Integer, TableRecord>> listOfRecords() {
        ConcurrentSkipListSet<Entry<Integer, TableRecord>> set = new ConcurrentSkipListSet<Entry<Integer, TableRecord>>();
        Iterator<java.util.Map.Entry<Integer, TableRecord>> i = storage.entrySet().iterator();
        while (i.hasNext()) {
            java.util.Map.Entry<Integer, TableRecord> e = i.next();
            set.add(new Entry<Integer, TableRecord>(e.getKey(), e.getValue()));
        }
        return set;
    }

    private class Entry<K extends Comparable, V> implements Comparable<Entry> {

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