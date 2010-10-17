/*
 * UnexpectedRightBracketException
 * Antonov Kirill(c), 2010
 */
package exception;

import lexer.Position;

public class UnexpectedRightBracketException extends ParserException{

     public UnexpectedRightBracketException(Position position){
          super(position);
     }

}

