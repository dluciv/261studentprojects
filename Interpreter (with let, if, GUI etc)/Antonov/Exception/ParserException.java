/*
 * common class for all parser exceptions
 * Antonov Kirill(c), 2010
 */
package Exception;

import Lexer.Position;

public class ParserException extends InterpreterException {

    public ParserException(Position position) {
        super(position);
    }
}
