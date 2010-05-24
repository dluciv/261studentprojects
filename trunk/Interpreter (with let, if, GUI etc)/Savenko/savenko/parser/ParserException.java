/*
 * common class for all parser exceptions
 * Savenko Maria(c)
 */
package savenko.parser;

import savenko.interpreter.InterpreterException;
import savenko.ast.Position;

public class ParserException extends InterpreterException{

     public ParserException(Position position){
          super(position);
     }

}
