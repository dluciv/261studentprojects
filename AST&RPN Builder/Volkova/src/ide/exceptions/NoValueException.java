package ide.exceptions;

import ide.ide.*;

public class NoValueException extends InterException {

    public NoValueException(Position position) {
        super(position);
    }
}
