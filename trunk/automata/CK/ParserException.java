/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Regular;

/**
 *
 * @author Кирилл
 */

public class ParserException extends Exception{
    static int MISSED_CLOSING_BRACKET = 0;
    int type;

    public ParserException(int type) {
        this.type = type;
    }
}

