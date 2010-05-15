/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lebedev;

public class Position {
    private int line;
    private int column;

    public Position(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
}
