/*
 * class ParserException - common class for all types of errors, which appear
 * in the issue of parsing: RightBrException, RightBrExceptionSudden, SemiException,
 * UnknownSymbolException
 * ParserException extends InterException
 *
 * (c) Volkova Ekatetina
 */
package ide.exceptions;

import ide.ide.*;

public class ParserException extends InterException {

    public ParserException(Position position) {
        super(position);
    }
}
