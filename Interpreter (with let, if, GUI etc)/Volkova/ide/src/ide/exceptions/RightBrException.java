/*
 * class RightBrException - class error when no right bracket, but it necessary
 * RightBracketException extends class ParserException
 *
 * (c) Volkova Ekatetina
 */
package ide.exceptions;

import ide.ide.*;

public class RightBrException extends ParserException {

    public RightBrException(Position position) {
        super(position);
    }
}
