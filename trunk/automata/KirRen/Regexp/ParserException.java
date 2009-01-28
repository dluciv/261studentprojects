package Regexp;

/**
 * @author Renat Akhmedyanov
 */

public class ParserException extends Exception{
    String message;

    ParserException(String message) {
        this.message = message;
    }
}
