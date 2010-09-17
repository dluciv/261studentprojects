/*
 *
 *
 */

package ide.exceptions;

import ide.ide.*;

public class UnknownSymbolException extends ParserException {

    public UnknownSymbolException(Position position) {
        super(position);
    }
}
