package database.parser;

/**
 * Какая-либо ошибка при разборе базы 
 * @author nastya
 * Date: 21.08.2009
 * Time: 1:29:21
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
