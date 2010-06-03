/*
 * 
 * "Отлаживающий интерпретатор языка "Student ML"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package interpreter;

import ast.*;
import gui.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lexerandparser.Position;
import tools.Tool;

public class Interpreter extends Thread {
    private final int SLEEP_TIME_MS = 25;
    private Expression input;
    private LinkedList<EnvironmentCell> environment = new LinkedList<EnvironmentCell>();
    private iController controller;
    private Value result;
    private boolean isDebugging;
    private static boolean isBlocked;
    private static boolean isStoped;
    private static Position curPos;

    public Interpreter(Expression parserOutput, boolean isDebugging, Controller controller) {
        input = parserOutput;
        this.isDebugging = isDebugging;
        isBlocked = true;
        this.controller = controller;
    }

    public static void unlockInterpreter() {
        isBlocked = false;
    }

    public static void stopInterpreter() {
        isStoped = true;
    }

    public static Position getCurPos() {
        return curPos;
    }

    public Value getResult() {
        return result;
    }

    @Override
    public void run() {
        try {
            isStoped = false;
            printDebugInfo(input);
            result = interpretNode(input);
            if (isDebugging) {
                controller.printInOutputPane("end of source program;\n");
            }
        } catch (InterpreterStopedException e) { }
        controller.setInterpreterStateFalse();
    }

    private Value interpretNode(Expression node) throws InterpreterStopedException {
        curPos = node.getPosition();

        if (isStoped) {
            throw new InterpreterStopedException();
        }

        if (node instanceof ArOperand) {
            return interpret((ArOperand) node);
        } else if (node instanceof ArAddition) {
            return interpret((ArAddition) node);
        } else if (node instanceof ArSubtraction) {
            return interpret((ArSubtraction) node);
        } else if (node instanceof ArMultiplication) {
            return interpret((ArMultiplication) node);
        } else if (node instanceof ArDivision) {
            return interpret((ArDivision) node);
        } else if (node instanceof ArNegate) {
            return interpret((ArNegate) node);
        } else if (node instanceof LogOperand) {
            return interpret((LogOperand) node);
        } else if (node instanceof LogAnd) {
            return interpret((LogAnd) node);
        } else if (node instanceof LogOr) {
            return interpret((LogOr) node);
        } else if (node instanceof LogNot) {
            return interpret((LogNot) node);
        } else if (node instanceof LogEquality) {
            return interpret((LogEquality) node);
        } else if (node instanceof LogInequality) {
            return interpret((LogInequality) node);
        } else if (node instanceof LogGreater) {
            return interpret((LogGreater) node);
        } else if (node instanceof LogLess) {
            return interpret((LogLess) node);
        } else if (node instanceof LogGE) {
            return interpret((LogGE) node);
        } else if (node instanceof LogLE) {
            return interpret((LogLE) node);
        } else if (node instanceof Variable) {
            return interpret((Variable) node);
        } else if (node instanceof ExBinding) {
            return interpret((ExBinding) node);
        } else if (node instanceof ExSequence) {
            return interpret((ExSequence) node);
        } else if (node instanceof ExPrint) {
            return interpret((ExPrint) node);
        } else if (node instanceof ExConditional) {
            return interpret((ExConditional) node);
        } else if (node instanceof ExApplication) {
            return interpret((ExApplication) node);
        } else if (node instanceof ExFunction) {
            return interpret((ExFunction) node);
        }
        return null;
    }

    // арифметические узлы;
    private Value interpret(ArOperand node) {
        return new ValInt(node.getValue());
    }

    private Value interpret(ArAddition node) throws InterpreterStopedException {
        Integer leftNumber = interpretNode(node.getLeft()).asInteger();
        Integer rightNumber = interpretNode(node.getRight()).asInteger();

        return new ValInt(leftNumber + rightNumber);
    }

    private Value interpret(ArSubtraction node) throws InterpreterStopedException {
        Integer leftNumber = interpretNode(node.getLeft()).asInteger();
        Integer rightNumber = interpretNode(node.getRight()).asInteger();

        return new ValInt(leftNumber - rightNumber);
    }

    private Value interpret(ArMultiplication node) throws InterpreterStopedException {
        Integer leftNumber = interpretNode(node.getLeft()).asInteger();
        Integer rightNumber = interpretNode(node.getRight()).asInteger();

        return new ValInt(leftNumber * rightNumber);
    }

    private Value interpret(ArDivision node) throws InterpreterStopedException {
        Integer leftNumber = interpretNode(node.getLeft()).asInteger();
        Integer rightNumber = interpretNode(node.getRight()).asInteger();

        if (rightNumber != 0) {
            return new ValInt(leftNumber / rightNumber);
        } else {
            Tool.fixError("devide by zero", curPos);
            return null;
        }
    }

    private Value interpret(ArNegate node) throws InterpreterStopedException { // -;
        Integer operand = interpretNode(node.getOperand()).asInteger();

        return new ValInt(-operand);
    }

    // логические узлы;
    private Value interpret(LogOperand node) {
        return new ValBoolean(node.getValue());
    }

    private Value interpret(LogAnd node) throws InterpreterStopedException { // &&;
        Boolean leftOperand = interpretNode(node.getLeft()).asBolean();
        Boolean rightOperand = interpretNode(node.getRight()).asBolean();

        return new ValBoolean(leftOperand && rightOperand);
    }

    private Value interpret(LogOr node) throws InterpreterStopedException { // ||;
        Boolean leftOperand = interpretNode(node.getLeft()).asBolean();
        Boolean rightOperand = interpretNode(node.getRight()).asBolean();

        return new ValBoolean(leftOperand || rightOperand);
    }

    private Value interpret(LogNot node) throws InterpreterStopedException { // !;
        Boolean operand = interpretNode(node.getOperand()).asBolean();

        return new ValBoolean(!operand);
    }

    private Value interpret(LogEquality node) throws InterpreterStopedException { // ==;
        Object leftOperand = interpretNode(node.getLeft()).getValue();
        Object rightOperand = interpretNode(node.getRight()).getValue();

        compareTypesEquality(rightOperand, leftOperand);

        return new ValBoolean(leftOperand == rightOperand);
    }

    private Value interpret(LogInequality node) throws InterpreterStopedException { // !=;
        Object leftOperand = interpretNode(node.getLeft()).getValue();
        Object rightOperand = interpretNode(node.getRight()).getValue();

        compareTypesEquality(rightOperand, leftOperand);

        return new ValBoolean(leftOperand != rightOperand);
    }

    private Value interpret(LogGreater node) throws InterpreterStopedException { // >;
        Integer leftOperand = interpretNode(node.getLeft()).asInteger();
        Integer rightOperand = interpretNode(node.getRight()).asInteger();

        return new ValBoolean(leftOperand > rightOperand);
    }

    private Value interpret(LogLess node) throws InterpreterStopedException { // <;
        Integer leftOperand = interpretNode(node.getLeft()).asInteger();
        Integer rightOperand = interpretNode(node.getRight()).asInteger();

        return new ValBoolean(leftOperand < rightOperand);
    }

    private Value interpret(LogGE node) throws InterpreterStopedException { // >=;
        Integer leftOperand = interpretNode(node.getLeft()).asInteger();
        Integer rightOperand = interpretNode(node.getRight()).asInteger();

        return new ValBoolean(leftOperand >= rightOperand);
    }

    private Value interpret(LogLE node) throws InterpreterStopedException { // <=;
        Integer leftOperand = interpretNode(node.getLeft()).asInteger();
        Integer rightOperand = interpretNode(node.getRight()).asInteger();

        return new ValBoolean(leftOperand <= rightOperand);
    }

    // узлы типа "выражение";
    private Value interpret(ExSequence sequence) throws InterpreterStopedException {
        Value res = null;

        for (Expression statement : sequence.getList()) {
            printDebugInfo(statement);
            res = interpretNode(statement);
        }

        return res;
    }

    private Value interpret(ExBinding node) throws InterpreterStopedException { // binding;
        printDebugInfo(node.getLetExpression());
        Value letExpressionValue = interpretNode(node.getLetExpression());
        EnvironmentCell environmentCell;

        environmentCell = new EnvironmentCell(node.getId(), letExpressionValue);

        environment.add(environmentCell);
        printDebugInfo(node.getInExpression());
        Value inExpressionValue = interpretNode(node.getInExpression());
        environment.remove(environmentCell);

        if (inExpressionValue instanceof ValInt) {
            ValInt arVal = (ValInt) inExpressionValue;
            return new ValInt((Integer) arVal.getValue());
        } else if (inExpressionValue instanceof ValBoolean) {
            ValBoolean logVal = (ValBoolean) inExpressionValue;
            return new ValBoolean((Boolean) logVal.getValue());
        } else {
            return new ValUnit();
        }
    }

    private Value interpret(Variable node) {
        return findVar(node.getId()).getValue();
    }

    private Value interpret(ExPrint node) throws InterpreterStopedException { // print
        Value exToPrintValue = interpretNode(node.getExToPrint());

        if (exToPrintValue instanceof ValClosing) {
            Tool.fixError("\"print(expr)\" can not print function", curPos);
        } else if (Tool.getErrorQnt() == 0) {
            controller.printInOutputPane(exToPrintValue.getValue().toString() + "\n");
        }

        return new ValUnit();
    }

    private Value interpret(ExConditional node) throws InterpreterStopedException {
        ValBoolean cnd = (ValBoolean) interpretNode(node.getLogExpression());

        if ((Boolean) cnd.getValue() == true) {
            printDebugInfo(node.getThenExpression());
            return interpretNode(node.getThenExpression());
        } else {
            printDebugInfo(node.getElseExpression());
            return interpretNode(node.getElseExpression());
        }
    }

    private Value interpret(ExApplication node) throws InterpreterStopedException {
        ValClosing function = new ValClosing(0, new ArOperand(0, Interpreter.getCurPos()), environment);
        try {
            function = (ValClosing) interpretNode(node.getFunction());
        }
        catch (ClassCastException e) {
            Tool.fixError("cast to function error", curPos);
        }
        Value arg = interpretNode(node.getArgument());

        LinkedList<EnvironmentCell> tempEnvironment = environment;
        environment = function.getEnv();
        environment.add(new EnvironmentCell(function.getId(), arg));
        Value res = interpretNode(function.getFunctionBody());
        environment = tempEnvironment;

        return res;
    }

    private Value interpret(ExFunction node) {
        printDebugInfo(node.getFunctionBody());
        return new ValClosing(node.getId(), node.getFunctionBody(), (LinkedList<EnvironmentCell>) environment.clone());
    }

    // вспомогательные функции;
    private void compareTypesEquality(Object firstOperand, Object secondOperand) {
        if (firstOperand.getClass() != secondOperand.getClass()) {
            Tool.fixError("type mismatch", curPos);
        }
    }

    private void printDebugInfo(Expression node) {
        if (isDebugging && !isStoped) {
            controller.printInOutputPane(node.getClass().getSimpleName() + "\n");
            controller.selectDebugLine(node.getPosition().getLine() + 1, node.getPosition().getColumn() + 1);
            waitKeypress();
        }
    }

    private void waitKeypress() {
        while (isBlocked && !isStoped) {
            try {
                sleep(SLEEP_TIME_MS);
            } catch (InterruptedException ex) {
                Logger.getLogger(Interpreter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        isBlocked = true;
    }

    private EnvironmentCell findVar(int id) {
        for (EnvironmentCell cell : environment) {
            if (cell.getId() == id) {
                return cell;
            }
        }
        Tool.fixError("no such id", curPos);
        return new EnvironmentCell(environment.size(), new ValInt(0));
    }
}
