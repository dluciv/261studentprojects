/**
 *
 * Lapin Sergey 261 group mat-mex
 * Regular expression analysis
 * 19.01.2009
 */

package parser;

public class Lexer {

    String string;
    int cursor = -1;

    public Lexer(String string) {
        this.string = string;
    }
    
    public boolean isEndOfLine(){
        return cursor + 1 >= string.length();
    }
    
    public int Length(){
        return string.length();
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