/*
 * общий класс для ошибок, возникающих при интерпритации
 * Savenko Maria(c)
 */
package savenko.interpreter;

import savenko.ast.Position;

public class InterpreterException extends Exception{


     private Position position = null;

     public InterpreterException(Position position){
          this.position = position;
     }

     public Position getPosition() {
          return position;
     }

}
