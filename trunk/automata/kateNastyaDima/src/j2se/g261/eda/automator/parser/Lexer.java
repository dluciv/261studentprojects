/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.parser;

/**
 *
 * @author nastya
 */
public class Lexer {

    String string;
    int cursor = -1;

    public Lexer(String string) {
        this.string = string;
    }
    
    public boolean isEndOfLine(){
        return cursor + 1 >= string.length();
    }
    
    public char nextChar(){
        cursor++;
//        System.out.println("inc " + cursor);
        return string.charAt(cursor);
    }
    
    public void decrementCursor(){
        cursor--;
//        System.out.println("dec " + cursor);
    }
}
