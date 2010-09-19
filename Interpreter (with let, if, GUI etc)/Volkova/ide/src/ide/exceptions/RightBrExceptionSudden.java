/*
 * class RightBrExceptionSudden - class of error when unnecessary right bracket appears suddenly.
 * RightBrException extends ParserException
 *
 * (c) Volkova Ekatetina
 */
package ide.exceptions;

import ide.ide.*;

public class RightBrExceptionSudden extends ParserException {

    public RightBrExceptionSudden(Position position) {
        super(position);
    }
}
