package ide.exceptions;

import ide.ide.*;

public class ParserException extends InterException {

    public ParserException(Position position) {
        super(position);
    }
}
