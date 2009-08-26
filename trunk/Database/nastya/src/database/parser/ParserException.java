package database.parser;

/**
 * Created by IntelliJ IDEA.
 * User: nastya
 * Date: 21.08.2009
 * Time: 1:29:21
 * To change this template use File | Settings | File Templates.
 */
public class ParserException extends Exception {

    public ParserException(String message) {
        super(message);
    }

    public ParserException(Throwable cause) {
        super(cause);
    }

    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
