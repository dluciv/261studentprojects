package Regular;

/**
 *
 * @author Кирилл
 */

public class ParserException extends Exception{
    static int MISSED_CLOSING_BRACKET = 0;
    int type;

    ParserException(int type) {
        this.type = type;
    }
}

