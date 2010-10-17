/*
 *  class for IncompatibleTypedException
 * Antonov Kirill(c), 2010
 */
package exception;

import lexer.Position;

public class IncompatibleTypedException extends ParserException {

    public IncompatibleTypedException(Position position) {
        super(position);
    }
}
