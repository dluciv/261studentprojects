/*
 * semicolon exception
 * Antonov Kirill(c), 2010
 */
package Exception;

import Lexer.Position;

public class SemicolonException extends ParserException {

    public SemicolonException(Position position) {
        super(position);
    }
}
