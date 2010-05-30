/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lebedev;

public class Position {

    private int abs;
    private int line;
    private int column;
    private int lenght;

    public Position(int abs, int line, int column, int lenght) {
        this.abs = abs;
        this.line = line;
        this.column = column;
        this.lenght = lenght;
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

    public int getLenght() {
        return lenght;
    }
}
