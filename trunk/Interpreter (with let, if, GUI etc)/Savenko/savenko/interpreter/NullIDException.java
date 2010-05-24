/*
 * Means - there is no value(or it is NULL) for the asked id in the environment
 * Savenko Maria(c)
 */
package savenko.interpreter;

import savenko.ast.Position;

public class NullIDException extends InterpreterException{

     public NullIDException(Position position){
          super(position);
     }

}
