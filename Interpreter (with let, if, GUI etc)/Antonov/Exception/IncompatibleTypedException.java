/*
 *  class for IncompatibleTypedException
 * Antonov Kirill(c), 2010
 */
package name.kirill.ml.exception;

import name.kirill.ml.lexer.Position;

public class IncompatibleTypedException extends ParserException {

    public IncompatibleTypedException(Position position) {
        super(position);
    }
}
