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
public class TraceTape {
    private Vector<TraceItem> trace;
    private int currentPosition;
    private int lastPosition;
    
    public TraceTape(Tape tape, Moving lastMove){
        System.out.println(lastMove);
        trace = new Vector<TraceItem>();

        Vector<Character> v = tape.trace();
        currentPosition = tape.getPosition();
        lastPosition = 0;
        switch(lastMove){
            case LEFT:
                lastPosition = currentPosition + 1;
            case RIGHT:
                lastPosition = currentPosition - 1;
            case STEND:
                lastPosition = currentPosition;
        }
        
        for (int i = 0; i < v.size(); i++) {
                trace.add(new TraceItem(
                        v.get(i) == Tape.EMPTY ? '*' : v.get(i),
                        i == currentPosition, i == lastPosition));
        }
    }
    
    public int size(){
        return trace.size();
    }
    
    public TraceItem get(int index){
        if(index >= trace.size() ) return null;
        return trace.get(index);
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public int getLastPosition() {
        return lastPosition;
    }
    
    @Override
    public String toString(){
        String s = "| ";
        
        for (TraceItem traceItem : trace) {
            s += traceItem.toString() + " | ";
        }
        
        return s;
    }
   
}
