/*
 * semicolon exception
 * Antonov Kirill(c), 2010
 */
package exception;

import lexer.Position;

public class SemicolonException extends ParserException {

    public SemicolonException(Position position) {
        super(position);
    }
}
