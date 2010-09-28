/*
 * UnexpectedRightBracketException
 * Antonov Kirill(c), 2010
 */
package name.kirill.ml.exception;

import name.kirill.ml.lexer.Position;

public class UnexpectedRightBracketException extends ParserException{

     public UnexpectedRightBracketException(Position position){
          super(position);
     }

}

