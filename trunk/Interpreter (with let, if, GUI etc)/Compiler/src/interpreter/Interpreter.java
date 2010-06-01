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

public class Interpreter extends Thread {

    private final int SLEEP_TIME_MS = 200;
    private Expression input;
    private Value result;
    private String interpreterErrorLog = "";
    private String output = "";
    private LinkedList<EnvironmentCell> environment = new LinkedList<EnvironmentCell>();
    Controller controller;
    private int errorCounter = 0;
    private boolean isUnderDebug;
    private static boolean isBlocked;

    public Interpreter(Expression parserOutput, int parseErrorQnt, boolean isUnderDebug, Controller controller) {
        if (parseErrorQnt == 0) {
            input = parserOutput;
            this.isUnderDebug = isUnderDebug;
            isBlocked = true;
            this.controller = controller;
        } else {
            errorCounter = parseErrorQnt;
            fixError("there are parse or/and lexical errors");
        }
    }

    public int getErrorQnt() {
        return errorCounter;
    }

    public String getErrorLog() {
        return interpreterErrorLog;
    }

    public String getOutput() {
        return output;
    }

    public static void unlockInterpreter() {
        isBlocked = false;
    }

    @Override
    public void run() {
        if (errorCounter != 0) {
            return;
        }

        printDebugInfo(input);
        result = interpretNode(input);
        if (isUnderDebug) {
            controller.printInOutputPane("end of source program;\n");
        }
    }

    private Value interpretNode(Tree node) {
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

    private Value interpret(ArAddition node) {
        ValInt leftNumber = (ValInt) interpretNode(node.getLeft());
        ValInt rightNumber = (ValInt) interpretNode(node.getRight());

        return new ValInt((Integer) leftNumber.getValue() + (Integer) rightNumber.getValue());
    }

    private Value interpret(ArSubtraction node) {
        ValInt leftNumber = (ValInt) interpretNode(node.getLeft());
        ValInt rightNumber = (ValInt) interpretNode(node.getRight());

        return new ValInt((Integer) leftNumber.getValue() - (Integer) rightNumber.getValue());
    }

    private Value interpret(ArMultiplication node) {
        ValInt leftNumber = (ValInt) interpretNode(node.getLeft());
        ValInt rightNumber = (ValInt) interpretNode(node.getRight());

        return new ValInt((Integer) leftNumber.getValue() * (Integer) rightNumber.getValue());
    }

    private Value interpret(ArDivision node) {
        ValInt leftNumber = (ValInt) interpretNode(node.getLeft());
        ValInt rightNumber = (ValInt) interpretNode(node.getRight());
        Integer divider = (Integer) rightNumber.getValue();

        if (divider != 0) {
            return new ValInt((Integer) leftNumber.getValue() / (Integer) rightNumber.getValue());
        } else {
            fixError("devide by zero");
            return null;
        }
    }

    private Value interpret(ArNegate node) { // -;
        ValInt operand = (ValInt) interpretNode(node.getOperand());

        return new ValInt(-(Integer) operand.getValue());
    }

    // логические узлы;
    private Value interpret(LogOperand node) {
        return new ValBoolean(node.getValue());
    }

    private Value interpret(LogAnd node) { // &&;
        ValBoolean leftOperand = (ValBoolean) interpretNode(node.getLeft());
        ValBoolean rightOperand = (ValBoolean) interpretNode(node.getRight());

        return new ValBoolean((Boolean) leftOperand.getValue() && (Boolean) rightOperand.getValue());
    }

    private Value interpret(LogOr node) { // ||;
        ValBoolean leftOperand = (ValBoolean) interpretNode(node.getLeft());
        ValBoolean rightOperand = (ValBoolean) interpretNode(node.getRight());

        return new ValBoolean((Boolean) leftOperand.getValue() || (Boolean) rightOperand.getValue());
    }

    private Value interpret(LogNot node) { // !;
        ValBoolean operand = (ValBoolean) interpretNode(node.getOperand());

        return new ValBoolean(!(Boolean) operand.getValue());
    }

    private Value interpret(LogEquality node) { // ==;
        Value leftOperand = (Value) interpretNode(node.getLeft());
        Value rightOperand = (Value) interpretNode(node.getRight());

        return new ValBoolean(leftOperand.getValue() == rightOperand.getValue());
    }

    private Value interpret(LogInequality node) { // !=;
        Value leftOperand = (Value) interpretNode(node.getLeft());
        Value rightOperand = (Value) interpretNode(node.getRight());

        return new ValBoolean(leftOperand.getValue() == rightOperand.getValue());
    }

    private Value interpret(LogGreater node) { // >;
        ValInt leftOperand = (ValInt) interpretNode(node.getLeft());
        ValInt rightOperand = (ValInt) interpretNode(node.getRight());

        return new ValBoolean((Integer) leftOperand.getValue() > (Integer) rightOperand.getValue());
    }

    private Value interpret(LogLess node) { // <;
        ValInt leftOperand = (ValInt) interpretNode(node.getLeft());
        ValInt rightOperand = (ValInt) interpretNode(node.getRight());

        return new ValBoolean((Integer) leftOperand.getValue() < (Integer) rightOperand.getValue());
    }

    private Value interpret(LogGE node) { // >=;
        ValInt leftOperand = (ValInt) interpretNode(node.getLeft());
        ValInt rightOperand = (ValInt) interpretNode(node.getRight());

        return new ValBoolean((Integer) leftOperand.getValue() >= (Integer) rightOperand.getValue());
    }

    private Value interpret(LogLE node) { // <=;
        ValInt leftOperand = (ValInt) interpretNode(node.getLeft());
        ValInt rightOperand = (ValInt) interpretNode(node.getRight());

        return new ValBoolean((Integer) leftOperand.getValue() <= (Integer) rightOperand.getValue());
    }

    // узлы типа "выражение";
    private Value interpret(ExSequence sequence) {
        Value res = null;

        for (Expression statement : sequence.getList()) {
            printDebugInfo(statement);
            res = interpretNode(statement);
        }

        return res;
    }

    private Value interpret(ExBinding node) { // binding;
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

    private Value interpret(ExPrint node) { // print
        Value exToPrintValue = interpretNode(node.getExToPrint());

        if (exToPrintValue instanceof ValClosing) {
            fixError("\"print(expr)\" can not print function");
        } else {
            controller.printInOutputPane(exToPrintValue.getValue().toString() + "\n");
        }

        return new ValUnit();
    }

    private Value interpret(ExConditional node) {
        ValBoolean cnd = (ValBoolean) interpretNode(node.getLogExpression());

        if ((Boolean) cnd.getValue() == true) {
            printDebugInfo(node.getThenExpression());
            return interpretNode(node.getThenExpression());
        } else {
            printDebugInfo(node.getElseExpression());
            return interpretNode(node.getElseExpression());
        }
    }

    private Value interpret(ExApplication node) {
        ValClosing function = (ValClosing) interpretNode(node.getFunction());
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
    private void printDebugInfo(Expression node) {
        if (isUnderDebug) {
            controller.printInOutputPane(node.getClass().getSimpleName() + "\n");
            controller.selectDebugLine(node.getPosition().getLine() + 1, node.getPosition().getColumn() + 1);
            //System.out.println(node.getPosition().getLine() + " - " + node.getPosition().getColumn());
            waitKeypress();
        }
    }

    private void waitKeypress() {
        while (isBlocked) {
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
        fixError("no such id");
        return new EnvironmentCell(environment.size(), new ValInt(0)); // позволяет завершить работу ин-
        // терпретатора, но с ошибкой;
    }

    private void fixError(String message) {
        errorCounter++;
        interpreterErrorLog += "interpreter error: " + message + ";\n";
    }
}
