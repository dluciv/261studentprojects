
package LexerAndParser;

public class Interpret {

   Environment environment;

     public Interpret() {
          environment = new Environment();
     }

     public Value interpret(Sequence sequence) throws Exception{
          Value val = null;

          for (Expression statement : sequence.returnStatement()) {
               val = interpret(statement);
          }
          environment.removeEnvironment();
          return val;
     }

     private Value interpret(Binding binding) throws Exception{
          Value old_value = null, val;

          if (environment.ifIdentificatorExist(binding.getIdentifier())) {
               old_value = interpret(binding.getExpression());
               val = interpret(binding.getValue());
               environment.removeIdentificator(binding.getIdentifier());
               environment.addToEnvironment(binding.getIdentifier(), old_value);
          } else {
               if (binding.getValue() != null) {
                    environment.addToEnvironment(binding.getIdentifier(), interpret(binding.getExpression()));
                    //environment.addToEnvironment(binding.getIdentifier(), interpret(binding.getValue()));

                    val = interpret(binding.getValue());
                    //environment.removeIdentificator(binding.getIdentifier());
               } else {
                    val = interpret(binding.getExpression());
               }
          }

          return val;
     }

     private Value interpret(Tree node) throws Exception {
          if (node instanceof Sequence) {
               return interpret((Sequence) node);
          } else if (node instanceof Number) {
               return interpret((Number) node);
          } else if (node instanceof Binding) {
               return interpret((Binding) node);
          } else if (node instanceof Plus) {
               return interpret((Plus) node);
          } else if (node instanceof Minus) {
               return interpret((Minus) node);
          } else if (node instanceof Div) {
               return interpret((Div) node);
          } else if (node instanceof Mult) {
               return interpret((Mult) node);
          } else if (node instanceof Or) {
              return interpret((Or) node);
          }else if (node instanceof And) {
              return interpret ((And) node);
          }else if (node instanceof equality) {
              return interpret ((equality) node);
          }else if (node instanceof unequality){
              return interpret ((unequality) node);
          }else if (node instanceof Identificator) {
               return interpret((Identificator) node);
          }else if (node instanceof If){
              return interpret((If) node);
          }else if (node instanceof LE) {
               return interpret((LE) node);
          }else if (node instanceof GE) {
               return interpret((GE) node);
          }else if (node instanceof Less) {
               return interpret((Less) node);
          }else if (node instanceof Greater) {
               return interpret((Greater) node);
          }else if (node instanceof Negate) {
               return interpret((Negate) node);
          }else if (node instanceof And) {
               return interpret((And) node);
          }else if (node instanceof Or) {
               return interpret((Or) node);
          }else if (node instanceof BooleanOp) {
               return interpret((BooleanOp) node);
          }else if (node instanceof Begin) {
               return interpret((Begin) node);
          }else if (node instanceof Function){
              return interpret((Function) node);
          }else if (node instanceof Print) {
               return interpret((Print) node);
          } else {
               throw new Exception();
          }
     }

     private Value interpret(Print id) throws Exception{
         if (interpret(id.getExpression()) instanceof IntValue){
            System.out.println(((IntValue)interpret(id.getExpression())).getValue());
         }else if(interpret(id.getExpression()) instanceof BoolValue){
             System.out.println(((BoolValue)interpret(id.getExpression())).getValue());
         }
         return new Value();
     }

     private Value interpret(Number number) throws Exception {
         return new IntValue(number.Value());
     }
     //Арифметические узлы
     private Value interpret(Plus plus) throws Exception {
          IntValue left = (IntValue) interpret(plus.LeftNode());
          IntValue right = (IntValue) interpret(plus.RightNode());
          return new IntValue(left.getValue() + right.getValue());
     }
     private Value interpret(Mult mult) throws Exception {
          IntValue left = (IntValue) interpret(mult.LeftNode());
          IntValue right = (IntValue) interpret(mult.RightNode());
          return new IntValue(left.getValue() * right.getValue());
     }
     private Value interpret(Minus minus) throws Exception {
          IntValue left = (IntValue) interpret(minus.LeftNode());
          IntValue right = (IntValue) interpret(minus.RightNode());
          return new IntValue(left.getValue() - right.getValue());
     }
     private Value interpret(Div div) throws Exception {
          IntValue left = (IntValue) interpret(div.LeftNode());
          IntValue right = (IntValue) interpret(div.RightNode());
          Integer divider = (Integer) right.getValue();
          if (divider == 0){
              throw new Exception("We can't divide by zero");
             
          } else {
               return new IntValue(left.getValue() / right.getValue());
          }
          
     }
    //логические узлы
    private Value interpret(equality id) throws Exception{
         if (((IntValue)interpret(id.LeftNode())).getValue() == ((IntValue)interpret(id.RightNode())).getValue())
               return new BoolValue(true);
          else return new BoolValue(false);

    }

    private Value interpret(unequality id) throws Exception{
         if (((IntValue)interpret(id.LeftNode())).getValue() != ((IntValue)interpret(id.RightNode())).getValue())
               return new BoolValue(true);
          else return new BoolValue(false);
    }
         private Value interpret(LE id) throws Exception {
          if (((IntValue)interpret(id.LeftNode())).getValue() <= ((IntValue)interpret(id.RightNode())).getValue())
               return new BoolValue(true);
          else return new BoolValue(false);
     }

     private Value interpret(GE id) throws Exception {
          if (((IntValue)interpret(id.LeftNode())).getValue() >= ((IntValue)interpret(id.RightNode())).getValue())
               return new BoolValue(true);
          else return new BoolValue(false);
     }

     private Value interpret(Greater id) throws Exception {
          if (((IntValue)interpret(id.LeftNode())).getValue() > ((IntValue)interpret(id.RightNode())).getValue())
               return new BoolValue(true);
          else return new BoolValue(false);
     }

     private Value interpret(Less id) throws Exception {
          if (((IntValue)interpret(id.LeftNode())).getValue() < ((IntValue)interpret(id.RightNode())).getValue())
               return new BoolValue(true);
          else return new BoolValue(false);
     }

     private Value interpret(Negate id) throws Exception {
          if (((BoolValue)interpret(id)).getValue())
               return new BoolValue(true);
          else return new BoolValue(false);
     }

     private Value interpret(Or id) throws Exception {
         return new BoolValue(((BoolValue)interpret(id.LeftNode())).getValue()||((BoolValue)interpret(id.RightNode())).getValue());
    }

     private Value interpret(And id) throws Exception {
         return new BoolValue(((BoolValue)interpret(id.LeftNode())).getValue()&&((BoolValue)interpret(id.RightNode())).getValue());
    }

    private Value interpret(BooleanOp id) throws Exception {
         return new BoolValue(((BooleanOp)id).getBool());
    }


     
    private Value interpret(Identificator id) throws Exception {
          Value val = environment.getValue(id);
          if (val != null) {
               return val;
          } else {
               throw new Exception();
          }
     }

    private Value interpret(If id) throws Exception {
          Value val = interpret(id.getExpression());
          if (((BoolValue) val).getValue()) {
               return interpret(id.getIfExpression());
          } else{
               return interpret(id.getElseExpression());
          }

          
     }
    private Value interpret(Function id) throws Exception {
          Value old_value = null, val;

          if (environment.ifIdentificatorExist(id.getIdentifier())) {
               old_value = environment.addToEnvironment(id.getIdentifier(), interpret(id.getExpression()));
               val = interpret(id.getExpression());
               environment.addToEnvironment(id.getIdentifier(), old_value);
          } else {
               environment.addToEnvironment(id.getIdentifier(), interpret(id.getExpression()));
               val = interpret(id.getExpression());
               environment.removeIdentificator(id.getIdentifier());
          }

          return val;
     }

     private Value interpret(Begin id) throws Exception {
          return interpret(id.getSequence());
     }

}
