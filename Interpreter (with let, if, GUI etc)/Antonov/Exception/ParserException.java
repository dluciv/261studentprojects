/*
 * common class for all parser exceptions
 * Antonov Kirill(c), 2010
 */
package name.kirill.ml.exception;

import name.kirill.ml.lexer.Position;

public class ParserException extends InterpreterException {

    public ParserException(Position position) {
        super(position);
    }
}
