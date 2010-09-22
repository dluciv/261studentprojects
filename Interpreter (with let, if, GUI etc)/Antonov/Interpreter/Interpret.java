/*
 * интерпретирует программу, выводит на печать последнее значение
 * Antonov Kirill(c)
 */
package Interpreter;

import AST.And;
import AST.Application;
import AST.Begin;
import AST.Binding;
import AST.BooleanOp;
import AST.Div;
import AST.Expression;
import AST.Function;
import AST.GE;
import AST.Greater;
import AST.Identificator;
import AST.Number;
import AST.If;
import AST.LE;
import AST.Less;
import AST.Minus;
import AST.Mult;
import AST.Negate;
import AST.Or;
import AST.Plus;
import AST.Print;
import AST.Sequence;
import AST.Tree;
import AST.Types;

import AST.equality;
import AST.unequality;
import Exception.IncompatibleTypedException;
import Exception.InterpreterException;
import Exception.NullIDException;

public class Interpret {

    //private boolean isDebugging;
    //private static boolean isBlocked;
    //private static boolean isStoped;
    Environment environment;
    //private Sequence input;
    //private iControler controler;
    //private Value result;
    //private final int SLEEP_TIME_MS = 25;
    //private static Position curPos;

    public Interpret(/*Sequence sequence, boolean isDebugging, Controler controler*/) {
        environment = new Environment();
        //input = sequence;
        //this.isDebugging = isDebugging;
        //isBlocked = true;
        //this.controler = controler;
    }

    public Value interpret(Sequence sequence) throws InterpreterException {
        Value val = null;

        for (Expression statement : sequence.returnStatement()) {
            //printDebugInfo(statement);
            val = interpret(statement);
        }
        environment.removeEnvironment();
        return val;
    }

    /*   public static void unlockInterpreter() {
    isBlocked = false;
    }

    public static void stopInterpreter() {
    isStoped = true;
    }

    public Value getResult() {
    return result;
    }
    public static Position getCurPos() {
    return curPos;
    }

    @Override
    public void run() {
    try {
    isStoped = false;
    printDebugInfo(input);
    result = interpret(input);
    if (isDebugging) {
    controler.printInOutputPane("end of source program;\n");
    }
    } catch (InterpreterException e) { }
    controler.setInterpreterStateFalse();
    }*/
    private boolean EqualTypes(Types type, Value value) {
        switch (type) {
            case Bool:
                return (value instanceof BoolValue);
            case Int:
                return (value instanceof IntValue);
            case Unit:
                return (value instanceof UnitValue);
            default:
                return false;
        }
    }

    private Value interpret(Binding binding) throws InterpreterException {
        Value old_value = null, val;

        if (environment.ifIdentificatorExist(binding.getIdentifier())) {
            //printDebugInfo(binding.getExpression());
            old_value = interpret(binding.getExpression());
            val = interpret(binding.getValue());
            environment.removeIdentificator(binding.getIdentifier());

            if (EqualTypes(binding.getIdentifier().GetType().GetType(), old_value)) {
                environment.addToEnvironment(binding.getIdentifier(), old_value);
            } else {
                throw new IncompatibleTypedException(null);
            }

        } else {
            if (binding.getValue() != null) {
                Value value = interpret(binding.getExpression());

                if (value instanceof Closure) {
                    if (((Closure) value).getFunction().getIdentifier().GetType().GetType()
                            == binding.getIdentifier().GetType().GetType()) {
                        environment.addToEnvironment(binding.getIdentifier(), value);
                    } else {
                        throw new IncompatibleTypedException(null);
                    }
                } else {
                    if (EqualTypes(binding.getIdentifier().GetType().GetType(), value)) {
                        environment.addToEnvironment(binding.getIdentifier(), value);
                    } else {
                        throw new IncompatibleTypedException(null);
                    }
                }

                //environment.addToEnvironment(binding.getIdentifier(), interpret(binding.getValue()));
                //printDebugInfo(binding.getValue());
                val = interpret(binding.getValue());
                //environment.removeIdentificator(binding.getIdentifier());
            } else {
                val = interpret(binding.getExpression());
            }
        }

        return val;
    }

    private Value interpret(Tree node) throws InterpreterException {
        //curPos = node.getPosition();
        //if (isStoped) {
        //  throw new InterpreterException(null);
        //}
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
        } else if (node instanceof equality) {
            return interpret((equality) node);
        } else if (node instanceof unequality) {
            return interpret((unequality) node);
        } else if (node instanceof Identificator) {
            return interpret((Identificator) node);
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
        } else if (node instanceof BooleanOp) {
            return interpret((BooleanOp) node);
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
        return new Value();
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
        //Integer divider = (Integer) right.getValue();
        //if (divider == 0){
        //    throw new InterpreterException(null);

        //} else {
        return new IntValue(left.getValue() / right.getValue());
    }

    //логические узлы
    private Value interpret(equality id) throws InterpreterException {
        if (((IntValue) interpret(id.LeftNode())).getValue() == ((IntValue) interpret(id.RightNode())).getValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }

    }

    private Value interpret(unequality id) throws InterpreterException {
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

    private Value interpret(BooleanOp id) throws InterpreterException {
        return new BoolValue(((BooleanOp) id).getBool());
    }

    private Value interpret(Identificator id) throws NullIDException {
        Value val = environment.getValue(id);
        if (val != null) {
            return val;
        } else {
            throw new NullIDException(null);
        }
    }

    private Value interpret(If id) throws InterpreterException {
        Value val = interpret(id.getExpression());
        if (((BoolValue) val).getValue()) {
            //printDebugInfo(id.getIfExpression());
            return interpret(id.getIfExpression());
        } else {
            //rintDebugInfo(id.getElseExpression());
            return interpret(id.getElseExpression());
        }


    }

    private Value interpret(Function id) throws InterpreterException {
        //Value old_value = null, val;
        //printDebugInfo(id.getExpression());
        return new Closure(id, environment.clone());

        /* if (environment.ifIdentificatorExist(id.getIdentifier())) {
        old_value = environment.addToEnvironment(id.getIdentifier(), interpret(id.getExpression()));
        val = interpret(id.getExpression());
        environment.addToEnvironment(id.getIdentifier(), old_value);
        } else {
        environment.addToEnvironment(id.getIdentifier(), interpret(id.getExpression()));
        val = interpret(id.getExpression());
        environment.removeIdentificator(id.getIdentifier());
        }

        return val;*/
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

//        if ((val instanceof BoolValue) && (fun.getIdentifier().GetType().RightNode() == Types.Bool)){
//
//        }

        return val;
    }

    private Value interpret(Begin id) throws InterpreterException {
        return interpret(id.getSequence());
    }

    /* private void printDebugInfo(Tree id) {
    if (isDebugging && !isStoped) {
    controler.printInOutputPane(id.getClass().getSimpleName() + "\n");
    controler.selectDebugLine(id.getPosition().getRowNum() + 1, id.getPosition().getCurrInd() + 1);
    waitKeypress();
    }
    }

    private void waitKeypress() {
    while (isBlocked && !isStoped) {
    try {
    sleep(SLEEP_TIME_MS);
    } catch (InterruptedException ex) {
    Logger.getLogger(Interpret.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    isBlocked = true;
    }
     */
}
