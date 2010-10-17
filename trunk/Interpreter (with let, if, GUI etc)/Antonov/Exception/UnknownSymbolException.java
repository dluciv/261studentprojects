/*
 * reached unknown to lexer symbol in the string
 * Antonov Kirill(c), 2010
 */
package exception;

import lexer.Position;

public class UnknownSymbolException extends ParserException{

     public UnknownSymbolException(Position position){
          super(position);
     }

}