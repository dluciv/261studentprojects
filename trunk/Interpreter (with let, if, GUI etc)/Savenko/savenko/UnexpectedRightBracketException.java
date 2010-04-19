/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package savenko;

import savenko.ast.Position;

public class UnexpectedRightBracketException extends ParserException{

     public UnexpectedRightBracketException(Position position){
          super(position);
     }

}
