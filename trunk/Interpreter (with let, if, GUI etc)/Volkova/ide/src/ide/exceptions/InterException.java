/*
 * class InterException - common class for all types of errors which appear 
 * in the issue of interpreting: NoValueException
 * InterException extends class Exception
 *
 * (c) Volkova Ekatetina
 */
package ide.exceptions;

import ide.ide.*;

public class InterException extends Exception {

    private Position position = null;

    public InterException(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
