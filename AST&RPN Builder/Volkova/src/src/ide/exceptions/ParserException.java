package ide.exceptions;

import ide.*;

public class ParserException extends InterException {

    public ParserException(Position position) {
        super(position);
    }
}
