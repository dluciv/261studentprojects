/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eda.tm;

/**
 *
 * @author nastya
 */
public class TraceItem {
    private char symbol;
    private boolean currentPoint;
    private boolean lastPoint;
    
    
    public TraceItem(char symbol, boolean currentPoint, boolean lastPoint) {
        this.symbol = symbol;
        this.currentPoint = currentPoint;
        this.lastPoint = lastPoint;
    }
    
    
    public TraceItem(char symbol){
        this(symbol, false, false);
    }

    public boolean isCurrentPoint() {
        return currentPoint;
    }

    public boolean isLastPoint() {
        return lastPoint;
    }

    public char getSymbol() {
        return symbol;
    }
    
    @Override
    public String toString(){
        return symbol + (currentPoint ? "1": "0") + (lastPoint ? "1": "0");
    }
}
