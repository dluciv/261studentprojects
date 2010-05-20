/*
 * 
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */
package yaskov;

import ast.*;
import java.util.LinkedList;

public class Interpreter {

    public Tree input;
    public Value result;
    private String interpreterErrorLog = "";
    private String output = "";
    private LinkedList<EnvironmentCell> environment = new LinkedList<EnvironmentCell>();
    private int errorCounter = 0;
    private int nodeNo = 0;
    private int lastNodeNo;

    public Interpreter(Tree parserOutput, int parseErrorQnt, int lastNodeNo) {
        if (parseErrorQnt == 0) {
            input = parserOutput;
            this.lastNodeNo = lastNodeNo;
            //interpretSequence(parserOutput);
            //System.out.println("--------------\n");
        } else {
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

    public void interpretProgram() {
        result = interpretNode(input);
    }

    public Value interpretNode(Tree node) {
        nodeNo++;

        if (lastNodeNo == -1 || nodeNo < lastNodeNo) {
            if (lastNodeNo == -1) {
                System.out.println(node.getClass().getSimpleName());
                System.out.println(nodeNo);
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

    private Value interpret(ArNegate node) { // !;
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
        int i;

        for (i = 0; i < sequence.getList().size() - 1; ++i) {
            interpretNode(sequence.getList().get(i));
        }
        
        return (Value) interpretNode(sequence.getList().get(i));
    }

    private Value interpret(ExBinding node) { // binding;
        Value letExpressionValue = interpretNode(node.getLetExpression());
        EnvironmentCell environmentCell;

        environmentCell = new EnvironmentCell(node.getId(), letExpressionValue);

        environment.add(environmentCell);
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

    private Value interpret(ExPrint node) {
        Value exToPrintValue = interpretNode(node.getExToPrint());

        if (exToPrintValue instanceof ValFun) {
            fixError("\"print(expr)\" can not print function");
        }
        else {
            output += exToPrintValue.getValue().toString() + "\n";
        }

        return new ValUnit();
    }

    private Value interpret(ExConditional node) {
        ValBoolean cnd = (ValBoolean) interpretNode(node.getLogExpression());

        if ((Boolean) cnd.getValue() == true) {
            return interpretNode(node.getThenExpression());
        } else {
            return interpretNode(node.getElsePart());
        }
    }

    private Value interpret(ExApplication node) {

        /*Expression arg = interpretNode(node.getArgument());
        EnvironmentCell environmentCell;

        if (arg instanceof ArOperand) {
            ArOperand arOperand = (ArOperand) arg;
            environmentCell = new EnvironmentCell(node., arg)
        }*/
        return new ValUnit();
    }

    private Value interpret(ExFunction node) {
        return new ValFun(node.getFunctionBody(), environment);
    }

    // вспомогательные функции;
    private EnvironmentCell findVar(int id) {
        for (EnvironmentCell cell: environment) {
            if (cell.getId() == id) {
                return cell;
            }
        }

        //output += "id " + id + " undefined\n";
        //System.exit(0);
        fixError("no such id");
        return new EnvironmentCell(environment.size(), new ValInt(0)); // позволяет завершить работу ин-
        // терпретатора, но с ошибкой;
    }

    private void fixError(String message) {
        errorCounter++;
        interpreterErrorLog += "interpreter error: " + message + ";\n";
    }
}
