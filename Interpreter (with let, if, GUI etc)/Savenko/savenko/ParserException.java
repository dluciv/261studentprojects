package savenko;

import savenko.ast.Position;

public class ParserException extends InterpreterException{

     public ParserException(Position position){
          super(position);
     }

}
