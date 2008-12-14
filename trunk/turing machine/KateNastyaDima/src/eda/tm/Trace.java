/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eda.tm;

import java.util.Vector;

/**
 *
 * @author nastya
 */
public class Trace {

    private Vector<TraceTape> trace;

    public Trace() {
        trace = new Vector<TraceTape>();
    }

    public void add(TraceTape tape) {
        trace.add(tape);
    }

    public void balance() {
        Vector<Integer> v = new Vector<Integer>();
        if(v.size() == 0) return;
        int last = trace.lastElement().getLastPosition();
        v.add(trace.lastElement().getCurrentPosition());
        for (int i = trace.size() - 2; i >= 0; i++) {
            last = trace.get(i).getLastPosition() - trace.get(i).getCurrentPosition() + last;
            v.add(trace.get(i).getCurrentPosition() - trace.get(i).getCurrentPosition() + last);
        }
        System.out.println(v);
    }

    public TraceTape get(int i) {
        return trace.get(i);
    }

    public int getLength() {
        if (trace.size() == 0) {
            return 1;
        }
        return trace.lastElement().size();
    }

    public int size() {
        return trace.size();
    }
    
    @Override
    public String toString(){
        String s = "";
        for (TraceTape traceTape : trace) {
            s += traceTape + "\n";
        }
        
        return s;
    }
}
