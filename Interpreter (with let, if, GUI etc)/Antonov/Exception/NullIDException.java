/*
 * Means - there is no value(or it is NULL) for the asked id in the environment
 * Antonov Kirill(c), 2010
 */
package Exception;

import Lexer.Position;

public class NullIDException extends InterpreterException {

    public NullIDException(Position position) {
        super(position);
    }
}
