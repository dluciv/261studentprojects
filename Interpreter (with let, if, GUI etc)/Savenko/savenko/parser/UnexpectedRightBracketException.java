/*
 * Savenko Maria(c)
 */

package savenko.parser;

import savenko.ast.Position;

public class UnexpectedRightBracketException extends ParserException{

     public UnexpectedRightBracketException(Position position){
          super(position);
     }

}
