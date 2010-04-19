package savenko;

import savenko.ast.Position;

public class UnknownSymbolException extends ParserException{

     public UnknownSymbolException(Position position){
          super(position);
     }

}
