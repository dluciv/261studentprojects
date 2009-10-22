/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package transport;

/**
 *
 * @author Artem
 */
public class Cell {
    int price;
    int maxallowed;
    int value;
    int col;
    int arr;
    boolean base;

    public Cell(int pr, int max,int val,int c,int ar) {
        base = false;
        price = pr;
        maxallowed = max;
        value = val;
        col = c;
        arr = ar;

    }

    public boolean isEmpty() {
        if (value == 0)
            return true;
        return false;
    }

    public void setValue(int val) {
        value = val;
    }

    public void setBase() {
        base = true;
    }

    public void unsetBase() {
        base = false;
    }

    public int getValue() {
        return value;
    }

    public int getPrice() {
        return price;
    }

    public int getMaxAllowed() {
        return maxallowed;
    }

    public int getCol() {
        return col;
    }

    public int getArr() {
        return arr;
    }
}
