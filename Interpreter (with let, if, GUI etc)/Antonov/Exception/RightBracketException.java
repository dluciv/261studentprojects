/*
 * There was Left Bracket but we reached the EOL||then||in||end without Right Bracket
 * Antonov Kirill(c), 2010
 */
package exception;

import lexer.Position;

public class RightBracketException extends ParserException {

    public RightBracketException(Position position) {
        super(position);
    }
}
