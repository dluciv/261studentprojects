/*
 * class NoValueException - class of error which appear when identifier haven't
 * value (look on to class Enviroment) or identifier's value is null
 * NoValueException extends class InterException.
 *
 * (c) Volkova Ekatetina
 */
package ide.exceptions;

import ide.ide.*;

public class NoValueException extends InterException {

    public NoValueException(Position position) {
        super(position);
    }
}
