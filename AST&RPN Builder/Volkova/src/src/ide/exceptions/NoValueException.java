package ide.exceptions;

import ide.*;

public class NoValueException extends InterException {

    public NoValueException(Position position) {
        super(position);
    }
}
