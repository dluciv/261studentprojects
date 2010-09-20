/*
 * UnexpectedRightBracketException
 * Antonov Kirill(c), 2010
 */
package Exception;

import Lexer.Position;

public class UnexpectedRightBracketException extends ParserException{

     public UnexpectedRightBracketException(Position position){
          super(position);
     }

}

