package savenko;

import savenko.ast.Position;

public class InterpreterException extends Exception{


     private Position position;

     public InterpreterException(Position position){
          this.position = position;
     }

     public Position getPosition() {
          return position;
     }

}
