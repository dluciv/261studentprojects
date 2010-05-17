package savenko;

import savenko.ast.And;
import savenko.ast.Begin;
import savenko.ast.BoolValue;
import savenko.ast.BooleanOp;
import savenko.ast.GE;
import savenko.ast.Greater;
import savenko.ast.LE;
import savenko.ast.Less;
import savenko.ast.Negate;
import savenko.ast.Sequence;
import savenko.ast.If;
import savenko.ast.Unequal;
import savenko.ast.Value;
import savenko.ast.Print;
import savenko.ast.Multiply;
import savenko.ast.Number;
import savenko.ast.Identifier;
import savenko.ast.Expression;
import savenko.ast.Binding;
import savenko.ast.Plus;
import savenko.ast.Minus;
import savenko.ast.Divide;
import savenko.ast.Equal;
import savenko.ast.Function;
import savenko.ast.IntValue;
import savenko.ast.Or;
import savenko.ast.Position;
import savenko.ast.Tree;

public class Interpreter {

     Environment environment;

     public Interpreter() {
          environment = new Environment();
     }

     public Value interpret(Sequence sequence) throws InterpreterException {
          Value val = null;

          for (Expression statement : sequence.returnStatement()) {
               val = interpret(statement);
          }

          return val;
     }

     private Value interpret(Binding binding) throws InterpreterException {
          Value old_value = null, val;

          if (environment.ifIdentifierExist(binding.getIdentifier())) {
               old_value = environment.addToEnvironment(binding.getIdentifier(), interpret(binding.getExpression()));
               val = interpret(binding.getValue());
               environment.addToEnvironment(binding.getIdentifier(), old_value);
          } else {
               if (binding.getValue() != null) {
                    environment.addToEnvironment(binding.getIdentifier(), interpret(binding.getExpression()));
                    val = interpret(binding.getValue());
                    environment.removeIdentifier(binding.getIdentifier());
               } else {
                    val = interpret(binding.getExpression());
               }
          }

          return val;
     }

     private Value interpret(Plus plus) throws InterpreterException {
          IntValue left = (IntValue) interpret(plus.LeftNode());
          IntValue right = (IntValue) interpret(plus.RightNode());
          return new IntValue(left.getValue() + right.getValue());
     }

     private Value interpret(Minus minus) throws InterpreterException {
          IntValue left = (IntValue) interpret(minus.LeftNode());
          IntValue right = (IntValue) interpret(minus.RightNode());
          return new IntValue(left.getValue() - right.getValue());
     }

     private Value interpret(Divide divide) throws InterpreterException {
          IntValue left = (IntValue) interpret(divide.LeftNode());
          IntValue right = (IntValue) interpret(divide.RightNode());
          return new IntValue(left.getValue() / right.getValue());
     }

     private Value interpret(Multiply multiply) throws InterpreterException {
          IntValue left = (IntValue) interpret(multiply.LeftNode());
          IntValue right = (IntValue) interpret(multiply.RightNode());
          return new IntValue(left.getValue() * right.getValue());
     }

     private Value interpret(Number number) throws InterpreterException {
          IntValue val = new IntValue(number.calculate());
          return val;
     }

     private Value interpret(Identifier id) throws NullIDException {
          Value val = environment.getValue(id);
          if (val != null) {
               return val;
          } else {
               throw new NullIDException(null); //-------pos------
          }
     }

     private Value interpret(Print id) throws InterpreterException {
          Value val = interpret(id.getExpression());
          //System.out.print(val.getIntValue()); //?????????
          return val;
     }

     private Value interpret(If id) throws InterpreterException {
          Value val = interpret(id.getClause());
          if (((BoolValue) val).getValue()) {
               val = interpret(id.getIfExpression());
          } else if (id.getElseExpression() != null) {
               val = interpret(id.getElseExpression());
          }

          return val;
     }

     private Value interpret(Function id) throws InterpreterException {
          Value old_value = null, val;

          if (environment.ifIdentifierExist(id.getIdentifier())) {
               old_value = environment.addToEnvironment(id.getIdentifier(), interpret(id.getExpression()));
               val = interpret(id.getExpression());
               environment.addToEnvironment(id.getIdentifier(), old_value);
          } else {
               environment.addToEnvironment(id.getIdentifier(), interpret(id.getExpression()));
               val = interpret(id.getExpression());
               environment.removeIdentifier(id.getIdentifier());
          }

          return val;
     }

     private Value interpret(LE id) throws InterpreterException {
          if (((IntValue)interpret(id.LeftNode())).getValue() <= ((IntValue)interpret(id.RightNode())).getValue())
               return new BoolValue(true);
          else return new BoolValue(false);
     }

     private Value interpret(Equal id) throws InterpreterException {
          if (((IntValue)interpret(id.LeftNode())).getValue() == ((IntValue)interpret(id.RightNode())).getValue())
               return new BoolValue(true);
          else return new BoolValue(false);
     }

     private Value interpret(GE id) throws InterpreterException {
          if (((IntValue)interpret(id.LeftNode())).getValue() >= ((IntValue)interpret(id.RightNode())).getValue())
               return new BoolValue(true);
          else return new BoolValue(false);
     }

     private Value interpret(Greater id) throws InterpreterException {
          if (((IntValue)interpret(id.LeftNode())).getValue() > ((IntValue)interpret(id.RightNode())).getValue())
               return new BoolValue(true);
          else return new BoolValue(false);
     }

     private Value interpret(Unequal id) throws InterpreterException {
          if (((IntValue)interpret(id.LeftNode())).getValue() != ((IntValue)interpret(id.RightNode())).getValue())
               return new BoolValue(true);
          else return new BoolValue(false);
     }

     private Value interpret(Less id) throws InterpreterException {
          if (((IntValue)interpret(id.LeftNode())).getValue() < ((IntValue)interpret(id.RightNode())).getValue())
               return new BoolValue(true);
          else return new BoolValue(false);
     }

     private Value interpret(Negate id) throws InterpreterException {
          if (((BoolValue)interpret(id)).getValue())
               return new BoolValue(true);
          else return new BoolValue(false);
     }

     private Value interpret(Or id) throws InterpreterException {
         return new BoolValue(((BoolValue)interpret(id.LeftNode())).getValue()||((BoolValue)interpret(id.RightNode())).getValue());
    }

     private Value interpret(And id) throws InterpreterException {
         return new BoolValue(((BoolValue)interpret(id.LeftNode())).getValue()&&((BoolValue)interpret(id.RightNode())).getValue());
    }

     private Value interpret(Begin id) throws InterpreterException {
          return interpret(id.getSequence());
     }
     
     private Value interpret(BooleanOp id) throws InterpreterException {
         return new BoolValue(((BooleanOp)id).getBool());
    }

     private Value interpret(Tree node) throws InterpreterException {
          if (node instanceof Sequence) {
               return interpret((Sequence) node);
          } else if (node instanceof Number) {
               return interpret((Number) node);
          } else if (node instanceof Binding) {
               return interpret((Binding) node);
          } else if (node instanceof Or) {
               return interpret((Or) node);
          } else if (node instanceof And) {
               return interpret((And) node);
          } else if (node instanceof Plus) {
               return interpret((Plus) node);
          } else if (node instanceof Minus) {
               return interpret((Minus) node);
          } else if (node instanceof Divide) {
               return interpret((Divide) node);
          } else if (node instanceof Multiply) {
               return interpret((Multiply) node);
          } else if (node instanceof Identifier) {
               return interpret((Identifier) node);
          } else if (node instanceof Print) {
               return interpret((Print) node);
          } else if (node instanceof Function) {
               return interpret((Function) node);
          } else if (node instanceof If) {
               return interpret((If) node);
          } else if (node instanceof BooleanOp) {
               return interpret((BooleanOp) node);
          } else if (node instanceof Begin) {
               return interpret((Begin) node);
          } else if (node instanceof LE) {
               return interpret((LE) node);
          } else if (node instanceof GE) {
               return interpret((GE) node);
          } else if (node instanceof Greater) {
               return interpret((Greater) node);
          } else if (node instanceof Less) {
               return interpret((Less) node);
          } else if (node instanceof Unequal) {
               return interpret((Unequal) node);
          } else if (node instanceof Negate) {
               return interpret((Negate) node);
          } else if (node instanceof Equal) {
               return interpret((Equal) node);
          } else {
               throw new InterpreterException(null); //-------pos------
          }
     }
}
