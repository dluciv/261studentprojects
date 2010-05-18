/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lebedev;

public class Position {
    private int line;
    private int column;
    private int abs;

    public Position(int abs, int line, int column) {
        this.abs = abs;
    	this.line = line;
        this.column = column;
    }

    public int getAbs() {
    	return abs;
    }
    
    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
}
