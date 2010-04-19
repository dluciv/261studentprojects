package savenko;

import savenko.ast.Position;

public class NullIDException extends InterpreterException{

     public NullIDException(Position position){
          super(position);
     }

}
