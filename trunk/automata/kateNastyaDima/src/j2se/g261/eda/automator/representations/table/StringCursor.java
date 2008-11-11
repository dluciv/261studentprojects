/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.representations.table;

/**
 *
 * @author Katerina
 */
public class StringCursor {

    private int cursor = -1;
    private String s;

    public StringCursor(String s) {
        this.s = s;
    }

    public boolean hasNext() {
        return cursor + 1 < s.length();
    }

    public char next() {
        return s.charAt(++cursor);
    }
}

