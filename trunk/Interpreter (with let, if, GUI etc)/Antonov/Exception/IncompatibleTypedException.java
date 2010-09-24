/*
 *  class for IncompatibleTypedException
 * Antonov Kirill(c), 2010
 */
package Exception;

import Lexer.Position;

public class IncompatibleTypedException extends InterpreterException {

    public IncompatibleTypedException(Position position) {
        super(position);
    }
}
