/*
 * common class for all interpret exceptions
 * Antonov Kirill(c), 2010
 */
package Exception;

import Lexer.Position;

public class InterpreterException extends Exception {

    private Position position = null;

    public InterpreterException(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
