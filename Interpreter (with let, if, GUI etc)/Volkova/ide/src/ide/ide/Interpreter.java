/*
 *
 *
 * (c) Volkova Ekatetina
 */
package ide.ide;

import ide.value.Value;
import ide.value.BoolValue;
import ide.value.IntValue;
import ide.exceptions.InterException;
import ide.exceptions.NoValueException;
import ide.operations.*;
import ide.operators.*;
import ide.operators.Number;
import java.util.Stack;

public class Interpreter {

    //Enviroment env;
    Stack<StackFrame> stack;

    public Interpreter() {
        stack = new Stack<StackFrame>();

    }

    public Value beginInterpretation(Sq sq) throws InterException {
        Enviroment env = new Enviroment();
        Value val = null;

        for (Expression op : sq.returnOps()) {
            val = interpret(op, env);
        }

        return val;
    }

    public Value interpret(Sq sq, Enviroment env) throws InterException {
        Value val = null;

        for (Expression op : sq.returnOps()) {
            val = interpret(op, env);
        }

        return val;
    }

    private Value interpret(Binding binding, Enviroment env) throws InterException {
        if (env.containsId(binding.getId())) {
            if (binding.getExpression() instanceof Function) {
                    stack.push(new StackFrame(binding.getId(), ((Function) binding.getExpression()).getArgument()));
                }

                env.addToEnv(binding.getId(), interpret(binding.getExpression(), env));
                return interpret(binding.getValue(), env);
        } else {
            if (binding.getValue() != null) {
                if (binding.getExpression() instanceof Function) {
                    stack.push(new StackFrame(binding.getId(), ((Function) binding.getExpression()).getArgument()));
                }

                env.addToEnv(binding.getId(), interpret(binding.getExpression(), env));
                return interpret(binding.getValue(), env);
            } else {
                if (binding.getExpression() instanceof Function) {
                    stack.push(new StackFrame(binding.getId(), ((Function) binding.getExpression()).getArgument()));
                }

                return interpret(binding.getExpression(), env);
            }
        }
    }

    private Value interpret(Plus plus, Enviroment env) throws InterException {
        return new IntValue(((IntValue) interpret(plus.getLeftNode(), env)).getValue() + ((IntValue) interpret(plus.getRightNode(), env)).getValue());
    }

    private Value interpret(Minus minus, Enviroment env) throws InterException {
        return new IntValue(((IntValue) interpret(minus.getLeftNode(), env)).getValue() -
                ((IntValue) interpret(minus.getRightNode(), env)).getValue());
    }

    private Value interpret(Div div, Enviroment env) throws InterException {
        IntValue right = (IntValue) interpret(div.getRightNode(), env);
        if (right.getValue() == 0) {
            throw new NoValueException(null);
        } else {
            return new IntValue(((IntValue) interpret(div.getLeftNode(), env)).getValue() / right.getValue());
        }
    }

    private Value interpret(Mult mult, Enviroment env) throws InterException {
        return new IntValue(((IntValue) interpret(mult.getLeftNode(), env)).getValue() *
                ((IntValue) interpret(mult.getRightNode(), env)).getValue());
    }

    private Value interpret(Number number, Enviroment env) throws InterException {
        return new IntValue(number.getNumber());
    }

    private Value interpret(Print id, Enviroment env) throws InterException {
        return interpret(id.getExpression(), env);
    }

    private Value interpret(IfThenElse id, Enviroment env) throws InterException {
        if (((BoolValue) interpret(id.getCondition(), env)).getBoolValue()) {
            return interpret(id.getExpression1(), env);
        } else {
            return interpret(id.getExpression2(), env);
        }
    }

    private Value interpret(Id id, Enviroment env) throws NoValueException, InterException {
        if (env.getValue(id) == null) {
            throw new NoValueException(null);
        }

        return env.getValue(id);
    }

    private Value interpret(Function id, Enviroment env) throws InterException {
        return new Closure(id, env);
    }
    //let rec f = fun x -> if x<2 then f x+1 else 1 in f 0

    private Value interpret(Application id, Enviroment env) throws InterException {

        Function function = ((Closure) interpret(id.getFunction(), env)).getFunction();//////////////////////////////
        if (env.containsId(function.getArgument())) {
            stack.push(new StackFrame(stack.pop().getNamefun(), id.getArg()));
            env.addToEnv(function.getArgument(), interpret(stack.peek().getArgument(), env));
            return interpret(function.getExpression(), env);
        } else {
            if (id.getArg() != null) {
                env.addToEnv(function.getArgument(), interpret(id.getArg(), env));
                return interpret(function.getExpression(), env);
            } else {
                return interpret(function.getExpression(), env);
            }
        }
    }

    private Value interpret(LessOrEq id, Enviroment env) throws InterException {
        if (((IntValue) interpret(id.getLeftNode(), env)).getValue() <= ((IntValue) interpret(id.getRightNode(), env)).getValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }
    }

    private Value interpret(Equal id, Enviroment env) throws InterException {
        if (((IntValue) interpret(id.getLeftNode(), env)).getValue() == ((IntValue) interpret(id.getRightNode(), env)).getValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }
    }

    private Value interpret(GreaterOrEq id, Enviroment env) throws InterException {
        if (((IntValue) interpret(id.getLeftNode(), env)).getValue() >= ((IntValue) interpret(id.getRightNode(), env)).getValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }
    }

    private Value interpret(Greater id, Enviroment env) throws InterException {
        if (((IntValue) interpret(id.getLeftNode(), env)).getValue() > ((IntValue) interpret(id.getRightNode(), env)).getValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }
    }

    private Value interpret(UnEq id, Enviroment env) throws InterException {
        if (((IntValue) interpret(id.getLeftNode(), env)).getValue() != ((IntValue) interpret(id.getRightNode(), env)).getValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }
    }

    private Value interpret(Less id, Enviroment env) throws InterException {
        if (((IntValue) interpret(id.getLeftNode(), env)).getValue() < ((IntValue) interpret(id.getRightNode(), env)).getValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }
    }

    private Value interpret(Not id, Enviroment env) throws InterException {
        if (((BoolValue) interpret(id, env)).getBoolValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }
    }

    private Value interpret(Or id, Enviroment env) throws InterException {
        return new BoolValue(((BoolValue) interpret(id.getLeftNode(), env)).getBoolValue() ||
                ((BoolValue) interpret(id.getRightNode(), env)).getBoolValue());
    }

    private Value interpret(And id, Enviroment env) throws InterException {
        return new BoolValue(((BoolValue) interpret(id.getLeftNode(), env)).getBoolValue() &&
                ((BoolValue) interpret(id.getRightNode(), env)).getBoolValue());
    }

    private Value interpret(BeginEnd id, Enviroment env) throws InterException {
        return interpret(id.getSq(), env);
    }

    private Value interpret(BoolOp id, Enviroment env) throws InterException {
        return new BoolValue(((BoolOp) id).getBool());
    }

    private Value interpret(Tree node, Enviroment env) throws InterException {
        if (node instanceof Sq) {
            return interpret((Sq) node, env);
        } else if (node instanceof Number) {
            return interpret((Number) node, env);
        } else if (node instanceof Binding) {
            return interpret((Binding) node, env);
        } else if (node instanceof Or) {
            return interpret((Or) node, env);
        } else if (node instanceof And) {
            return interpret((And) node, env);
        } else if (node instanceof Plus) {
            return interpret((Plus) node, env);
        } else if (node instanceof Minus) {
            return interpret((Minus) node, env);
        } else if (node instanceof Div) {
            return interpret((Div) node, env);
        } else if (node instanceof Mult) {
            return interpret((Mult) node, env);
        } else if (node instanceof Id) {
            return interpret((Id) node, env);
        } else if (node instanceof Print) {
            return interpret((Print) node, env);
        } else if (node instanceof Application) {
            return interpret((Application) node, env);
        } else if (node instanceof Function) {
            return interpret((Function) node, env);
        //} else if (node instanceof RecFuncton) {
        //    return interpret((RecFuncton) node, env);
        } else if (node instanceof IfThenElse) {
            return interpret((IfThenElse) node, env);
        } else if (node instanceof BoolOp) {
            return interpret((BoolOp) node, env);
        } else if (node instanceof BeginEnd) {
            return interpret((BeginEnd) node, env);
        } else if (node instanceof LessOrEq) {
            return interpret((LessOrEq) node, env);
        } else if (node instanceof GreaterOrEq) {
            return interpret((GreaterOrEq) node, env);
        } else if (node instanceof Greater) {
            return interpret((Greater) node, env);
        } else if (node instanceof Less) {
            return interpret((Less) node, env);
        } else if (node instanceof UnEq) {
            return interpret((UnEq) node, env);
        } else if (node instanceof Not) {
            return interpret((Not) node, env);
        } else if (node instanceof Equal) {
            return interpret((Equal) node, env);
        } else {
            throw new InterException(null);
        }
    }
}
