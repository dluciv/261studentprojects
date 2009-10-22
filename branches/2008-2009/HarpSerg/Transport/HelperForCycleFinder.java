/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package transport;

/**
 *
 * @author Artem
 */
public class HelperForCycleFinder {
    boolean[][] table;
    HelperForCycleFinder(int col, int arr) {
        table = new boolean[col][arr];
    }

    public void setVisited(int col, int arr) {
        table[col][arr] = true;
    }

    public void unsetVisited(int col, int arr) {
        table[col][arr] = false;
    }

    public boolean getVisited(int col, int arr) {
        return table[col][arr];
    }
}
