/*
 * There was Left Bracket but we reached the EOL||then||in||end without Right Bracket
 * Savenko Maria(c)
 */

package savenko.parser;

import savenko.ast.Position;

public class RightBracketExpectedException extends ParserException{

     public RightBracketExpectedException(Position position){
          super(position);
     }

}
