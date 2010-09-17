package ide.exceptions;

import ide.*;

public class UnknownSymbolException extends ParserException {

    public UnknownSymbolException(Position position) {
        super(position);
    }
}
