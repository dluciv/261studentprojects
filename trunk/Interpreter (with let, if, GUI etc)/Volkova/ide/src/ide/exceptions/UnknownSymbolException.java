/*
 * class UnknownSymbolException - class of error when we
 * came upon the unknown symbol in the prodram in the issue of parsing
 *.UnknownSymbolException extends class parserExseption
 *
 * (c) Volkova Ekatetina
 */
package ide.exceptions;

import ide.ide.*;

public class UnknownSymbolException extends ParserException {

    public UnknownSymbolException(Position position) {
        super(position);
    }
}
