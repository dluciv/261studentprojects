/*
 * class SemiException - class of error when semicolon is default
 * in sequence of expressions.
 * SemiException extends class ParserException
 *
 * (c) Volkova Ekatetina
 */
package ide.exceptions;

import ide.ide.*;

public class SemiException extends ParserException {

    public SemiException(Position position) {
        super(position);
    }
}
