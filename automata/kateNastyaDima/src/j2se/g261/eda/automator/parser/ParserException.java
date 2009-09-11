/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.parser;

/**
 *
 * @author nastya
 */
public class ParserException extends Exception {

    public static final int WRONG_STAPLERS = 0;
    public static final int WRONG_USING_OPERATORS = 1;
    int type;

    public ParserException(int type) {
        super("Parser exception");
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
