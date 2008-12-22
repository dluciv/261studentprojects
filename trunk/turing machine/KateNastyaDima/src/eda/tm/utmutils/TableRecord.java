/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eda.tm.utmutils;

import eda.tm.*;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author nastya
 */
public class TableRecord {

    private HashMap<Character, StateSymbolMove> storage;
   

    public TableRecord() {
        storage = new HashMap<Character, StateSymbolMove>();
    }

    public void add(char c, StateSymbolMove v) {
        if (!storage.containsKey(c)) {
            storage.put(c, v);
        }
    }  
    
    
    Set<Character> keySet() {
        return storage.keySet();
    }
    
    private String toUnary(int i){
        String s = "";
        for (int j = 0; j < i; j++) {
            s+="1";
        }
        return s;
    }
    
    private String toUMTssm(StateSymbolMove ssm){
        if(ssm == null) return "0";
        String s = "";
        s += toUnary(Integer.parseInt(ssm.getState()));
        s += ssm.getMove() == Moving.LEFT ? "L" : "R";
        s += ssm.getSymbol();
        return s;
    }
    
    @Override
    public String toString(){
        String s = "";
        s += toUMTssm(storage.get('b'));
        s += "c";
        s += toUMTssm(storage.get('0'));
        s += "c";
        s += toUMTssm(storage.get('1'));
        return s;
    }
}