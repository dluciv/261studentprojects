package ide.exceptions;

import ide.ide.*;

public class RightBrException extends ParserException {

    public RightBrException(Position position) {
        super(position);
    }
}
