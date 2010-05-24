/*
 * reached unknown to lexer symbol in the string
 * Savenko Maria(c)
 */
package savenko.parser;

import savenko.ast.Position;

public class UnknownSymbolException extends ParserException{

     public UnknownSymbolException(Position position){
          super(position);
     }

}
