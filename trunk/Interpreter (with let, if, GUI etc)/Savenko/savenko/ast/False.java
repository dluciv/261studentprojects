/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package savenko.ast;

public class False implements Expression{

     private Expression expr1 = null;
     private Expression expr2 = null;
     private Lexer.lexems sign = null;

     public False(Expression expr1,Lexer.lexems sign,Expression expr2) {
          this.expr1 = expr1;
          this.expr2 = expr2;
          this.sign = sign;
     }

}
