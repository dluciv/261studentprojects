/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package savenko;

import savenko.ast.Position;

public class RightBracketExpectedException extends ParserException{

     public RightBracketExpectedException(Position position){
          super(position);
     }
/*
     public RightBracketExpectedException(){
          super(null);
     }*/

}
