/*
 * интерпретирует программу, выводит на печать последнее значение
 * Antonov Kirill(c)
 */
package interpreter;

import environment.Environment;
import environment.BoolValue;
import environment.Value;
import environment.UnitValue;
import environment.IntValue;
import ast.And;
import ast.Application;
import ast.Begin;
import ast.Binding;
import ast.BooleanOperation;
import ast.Div;
import ast.Expression;
import ast.Function;
import ast.GE;
import ast.Greater;
import ast.Identifier;
import ast.Number;
import ast.If;
import ast.LE;
import ast.Less;
import ast.Minus;
import ast.Mult;
import ast.Negate;
import ast.Or;
import ast.Plus;
import ast.Print;
import ast.Sequence;
import ast.Tree;
import ast.Equality;
import ast.Unequality;
import exception.InterpreterException;
import exception.NullIDException;

public class Interpret {

    Environment environment;

    public Interpret() {
        environment = new Environment();

    }

    public Value interpret(Sequence sequence) throws InterpreterException {
        Value val = null;

        for (Expression statement : sequence.returnStatement()) {

            val = interpret(statement);
        }
        environment.removeEnvironment();
        return val;
    }

    private Value interpret(Binding binding) throws InterpreterException {
        Value old_value = null, val;

        if (environment.ifIdentificatorExist(binding.getIdentifier())) {

            old_value = interpret(binding.getExpression());
            val = interpret(binding.getValue());
            environment.removeIdentificator(binding.getIdentifier());

            environment.addToEnvironment(binding.getIdentifier(), old_value);

        } else {
            if (binding.getValue() != null) {
                Value value = interpret(binding.getExpression());

                if (value instanceof Closure) {
                    environment.addToEnvironment(binding.getIdentifier(), value);
                } else {
                    environment.addToEnvironment(binding.getIdentifier(), value);
                }

                val = interpret(binding.getValue());

            } else {
                val = interpret(binding.getExpression());
            }
        }

        return val;
    }

    private Value interpret(Tree node) throws InterpreterException {

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
        } else if (node instanceof And) {
            return interpret((And) node);
        } else if (node instanceof Equality) {
            return interpret((Equality) node);
        } else if (node instanceof Unequality) {
            return interpret((Unequality) node);
        } else if (node instanceof Identifier) {
            return interpret((Identifier) node);
        } else if (node instanceof If) {
            return interpret((If) node);
        } else if (node instanceof LE) {
            return interpret((LE) node);
        } else if (node instanceof GE) {
            return interpret((GE) node);
        } else if (node instanceof Less) {
            return interpret((Less) node);
        } else if (node instanceof Greater) {
            return interpret((Greater) node);
        } else if (node instanceof Negate) {
            return interpret((Negate) node);
        } else if (node instanceof And) {
            return interpret((And) node);
        } else if (node instanceof Or) {
            return interpret((Or) node);
        } else if (node instanceof BooleanOperation) {
            return interpret((BooleanOperation) node);
        } else if (node instanceof Begin) {
            return interpret((Begin) node);
        } else if (node instanceof Function) {
            return interpret((Function) node);
        } else if (node instanceof Print) {
            return interpret((Print) node);
        } else if (node instanceof Application) {
            return interpret((Application) node);
        } else {
            throw new InterpreterException(null);
        }
    }

    private Value interpret(Print id) throws InterpreterException {
        if (interpret(id.getExpression()) instanceof IntValue) {
            System.out.println(((IntValue) interpret(id.getExpression())).getValue());
        } else if (interpret(id.getExpression()) instanceof BoolValue) {
            System.out.println(((BoolValue) interpret(id.getExpression())).getValue());
        }

        return new UnitValue();
    }

    private Value interpret(Number number) throws InterpreterException {
        return new IntValue(number.Value());
    }
    //Арифметические узлы

    private Value interpret(Plus plus) throws InterpreterException {
        IntValue left = (IntValue) interpret(plus.LeftNode());
        IntValue right = (IntValue) interpret(plus.RightNode());
        return new IntValue(left.getValue() + right.getValue());
    }

    private Value interpret(Mult mult) throws InterpreterException {
        IntValue left = (IntValue) interpret(mult.LeftNode());
        IntValue right = (IntValue) interpret(mult.RightNode());
        return new IntValue(left.getValue() * right.getValue());
    }

    private Value interpret(Minus minus) throws InterpreterException {
        IntValue left = (IntValue) interpret(minus.LeftNode());
        IntValue right = (IntValue) interpret(minus.RightNode());
        return new IntValue(left.getValue() - right.getValue());
    }

    private Value interpret(Div div) throws InterpreterException {
        IntValue left = (IntValue) interpret(div.LeftNode());
        IntValue right = (IntValue) interpret(div.RightNode());

        return new IntValue(left.getValue() / right.getValue());
    }

    //логические узлы
    private Value interpret(Equality id) throws InterpreterException {
        if (((IntValue) interpret(id.LeftNode())).getValue() == ((IntValue) interpret(id.RightNode())).getValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }

    }

    private Value interpret(Unequality id) throws InterpreterException {
        if (((IntValue) interpret(id.LeftNode())).getValue() != ((IntValue) interpret(id.RightNode())).getValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }
    }

    private Value interpret(LE id) throws InterpreterException {
        if (((IntValue) interpret(id.LeftNode())).getValue() <= ((IntValue) interpret(id.RightNode())).getValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }
    }

    private Value interpret(GE id) throws InterpreterException {
        if (((IntValue) interpret(id.LeftNode())).getValue() >= ((IntValue) interpret(id.RightNode())).getValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }
    }

    private Value interpret(Greater id) throws InterpreterException {
        if (((IntValue) interpret(id.LeftNode())).getValue() > ((IntValue) interpret(id.RightNode())).getValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }
    }

    private Value interpret(Less id) throws InterpreterException {
        if (((IntValue) interpret(id.LeftNode())).getValue() < ((IntValue) interpret(id.RightNode())).getValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }
    }

    private Value interpret(Negate id) throws InterpreterException {
        if (((BoolValue) interpret(id)).getValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }
    }

    private Value interpret(Or id) throws InterpreterException {
        return new BoolValue(((BoolValue) interpret(id.LeftNode())).getValue() || ((BoolValue) interpret(id.RightNode())).getValue());
    }

    private Value interpret(And id) throws InterpreterException {
        return new BoolValue(((BoolValue) interpret(id.LeftNode())).getValue() && ((BoolValue) interpret(id.RightNode())).getValue());
    }

    private Value interpret(BooleanOperation id) throws InterpreterException {
        return new BoolValue(((BooleanOperation) id).getBool());
    }

    private Value interpret(Identifier id) throws NullIDException {
        Value val = environment.getValue(id);
        if (val != null) {
            return val;
        } else {
            throw new NullIDException(null);
        }
    }

    private Value interpret(If id) throws InterpreterException {
        Value val = interpret(id.getExpression());
        Value ifVal = interpret(id.getIfExpression());
        Value elseVal = interpret(id.getElseExpression());

        if (((BoolValue) val).getValue()) {

            return ifVal;
        } else {

            return elseVal;
        }
    }

    private Value interpret(Function id) throws InterpreterException {

        return new Closure(id, environment.clone());

    }

    private Value interpret(Application id) throws InterpreterException {
        Value old_value = null, val;
        Function fun = ((Closure) interpret(id.getFunction())).getFunction();

        if (environment.ifIdentificatorExist(fun.getIdentifier())) {
            old_value = environment.addToEnvironment(fun.getIdentifier(), interpret(id.getExpression()));
            val = interpret(fun.getExpression());
            environment.addToEnvironment(fun.getIdentifier(), old_value);
        } else {
            if (id.getExpression() != null) {
                environment.addToEnvironment(fun.getIdentifier(),
                        interpret(id.getExpression()));
                val = interpret(fun.getExpression());
                environment.removeIdentificator(fun.getIdentifier());
            } else {
                val = interpret(fun.getExpression());
            }
        }


        return val;
    }

    private Value interpret(Begin id) throws InterpreterException {
        return interpret(id.getSequence());
    }
}
