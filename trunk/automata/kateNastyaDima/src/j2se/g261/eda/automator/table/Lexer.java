/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.table;

/**
 *
 * @author Katerina
 */
public class Lexer {

    private int cursor = -1;
    private String s;

    public Lexer(String s) {
        this.s = s;
    }

    public boolean hasNext() {
        return cursor + 1 < s.length();
    }

    public char next() {
        return s.charAt(++cursor);
    }
}

