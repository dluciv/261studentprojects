/*
 * semicolon exception
 * Antonov Kirill(c), 2010
 */
package name.kirill.ml.exception;

import name.kirill.ml.lexer.Position;

public class SemicolonException extends ParserException {

    public SemicolonException(Position position) {
        super(position);
    }
}
