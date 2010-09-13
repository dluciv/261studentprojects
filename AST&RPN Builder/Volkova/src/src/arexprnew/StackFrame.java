/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arexprnew;

/**
 *
 * @author kate
 */
public class StackFrame {
   private Expression expr;
   private Enviroment env;

   public StackFrame(Expression expr, Enviroment env){
       this.expr = expr;
       this.env = env;
   }

   public Expression getExpression(){
       return expr;
   }

   public Enviroment getEnviroment(){
       return env;
   }

}
