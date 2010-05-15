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
    public Tree result;
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

    public Expression interpretNode(Tree node) {
        nodeNo++;

        if (lastNodeNo == -1 || nodeNo < lastNodeNo) {
            if (lastNodeNo == -1) {
                System.out.println(node.getClass().getSimpleName());
                System.out.println(nodeNo);
            }

            if (node instanceof ArOperand) {
                return (ArOperand) node;
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
                return (LogOperand) node;
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
            } else if (node instanceof ExFunction) {
                return (ExFunction) node;
            }
        }
        return null;
    }

    // арифметические узлы;
    private ArOperand interpret(ArAddition node) {
        ArOperand leftNumber = (ArOperand) interpretNode(node.getLeft());
        ArOperand rightNumber = (ArOperand) interpretNode(node.getRight());

        return new ArOperand(leftNumber.getValue() + rightNumber.getValue());
    }

    private ArOperand interpret(ArSubtraction node) {
        ArOperand leftNumber = (ArOperand) interpretNode(node.getLeft());
        ArOperand rightNumber = (ArOperand) interpretNode(node.getRight());

        return new ArOperand(leftNumber.getValue() - rightNumber.getValue());
    }

    private ArOperand interpret(ArMultiplication node) {
        ArOperand leftNumber = (ArOperand) interpretNode(node.getLeft());
        ArOperand rightNumber = (ArOperand) interpretNode(node.getRight());

        return new ArOperand(leftNumber.getValue() * rightNumber.getValue());
    }

    private ArOperand interpret(ArDivision node) {
        ArOperand leftNumber = (ArOperand) interpretNode(node.getLeft());
        ArOperand rightNumber = (ArOperand) interpretNode(node.getRight());
        int divider = rightNumber.getValue();

        if (divider != 0) {
            return new ArOperand(leftNumber.getValue() / divider);
        } else {
            fixError("devide by zero");
            return null;
        }
    }

    private ArOperand interpret(ArNegate node) { // !;
        ArOperand operand = (ArOperand) interpretNode(node.getOperand());

        return new ArOperand(-operand.getValue());
    }

    // логические узлы;
    private LogOperand interpret(LogAnd node) { // &&;
        LogOperand leftOperand = (LogOperand) interpretNode(node.getLeft());
        LogOperand rightOperand = (LogOperand) interpretNode(node.getRight());

        return new LogOperand(leftOperand.getValue() && rightOperand.getValue());
    }

    private LogOperand interpret(LogOr node) { // ||;
        LogOperand leftOperand = (LogOperand) interpretNode(node.getLeft());
        LogOperand rightOperand = (LogOperand) interpretNode(node.getRight());

        return new LogOperand(leftOperand.getValue() || rightOperand.getValue());
    }

    private LogOperand interpret(LogNot node) { // !;
        LogOperand operand = (LogOperand) interpretNode(node.getOperand());

        return new LogOperand(!operand.getValue());
    }

    private LogOperand interpret(LogEquality node) { // ==;
        ArOperand leftOperand = (ArOperand) interpretNode(node.getLeft());
        ArOperand rightOperand = (ArOperand) interpretNode(node.getRight());

        return new LogOperand(leftOperand.getValue() == rightOperand.getValue());
    }

    private LogOperand interpret(LogInequality node) { // !=;
        ArOperand leftOperand = (ArOperand) interpretNode(node.getLeft());
        ArOperand rightOperand = (ArOperand) interpretNode(node.getRight());

        return new LogOperand(leftOperand.getValue() != rightOperand.getValue());
    }

    private LogOperand interpret(LogGreater node) { // >;
        ArOperand leftOperand = (ArOperand) interpretNode(node.getLeft());
        ArOperand rightOperand = (ArOperand) interpretNode(node.getRight());

        return new LogOperand(leftOperand.getValue() > rightOperand.getValue());
    }

    private LogOperand interpret(LogLess node) { // <;
        ArOperand leftOperand = (ArOperand) interpretNode(node.getLeft());
        ArOperand rightOperand = (ArOperand) interpretNode(node.getRight());

        return new LogOperand(leftOperand.getValue() < rightOperand.getValue());
    }

    private LogOperand interpret(LogGE node) { // >=;
        ArOperand leftOperand = (ArOperand) interpretNode(node.getLeft());
        ArOperand rightOperand = (ArOperand) interpretNode(node.getRight());

        return new LogOperand(leftOperand.getValue() >= rightOperand.getValue());
    }

    private LogOperand interpret(LogLE node) { // <=;
        ArOperand leftOperand = (ArOperand) interpretNode(node.getLeft());
        ArOperand rightOperand = (ArOperand) interpretNode(node.getRight());

        return new LogOperand(leftOperand.getValue() <= rightOperand.getValue());
    }

    // узлы типа "выражение";
//    private Nothing interpret(ExSequence sequence) {
//        if (sequence.getLeft() != null) {
//            interpretNode(sequence.getLeft());
//        }
//        interpretNode(sequence.getRight());
//
//        return new Nothing();
//    }

    private ExSequence interpret(ExSequence sequence) {
        LinkedList<Expression> res = new LinkedList<Expression>();

        for (int i = 0; i < sequence.getList().size(); ++i) {
            res.add((Expression) interpretNode(sequence.getList().get(i)));
        }
        return new ExSequence(res);
    }

    private Expression interpret(ExBinding node) { // binding;
        Expression letExpression = interpretNode(node.getLetExpression());
        EnvironmentCell environmentCell;

        if (letExpression instanceof ArOperand) {
            ArOperand arLetExpession = (ArOperand) letExpression;
            environmentCell = new EnvironmentCell(node.getId(), arLetExpession);
        } else if (letExpression instanceof LogOperand) {
            LogOperand logLetExpession = (LogOperand) letExpression;
            environmentCell = new EnvironmentCell(node.getId(), logLetExpession);
        } else if (letExpression instanceof ExFunction) {
            ExFunction funLetExpression = (ExFunction) letExpression;
            environmentCell = new EnvironmentCell(node.getId(), funLetExpression);
        } else {
            fixError("smth strange, code: 0");
            return null;
        }

        environment.add(environmentCell);
        Expression inExpression = interpretNode(node.getInExpression());
        environment.remove(environmentCell);

        if (inExpression instanceof ArOperand) {
            ArOperand arOperand = (ArOperand) inExpression;
            return new ArOperand(arOperand.getValue());
        } else if (inExpression instanceof LogOperand) { // приделать чё надо;
            LogOperand logOperand = (LogOperand) inExpression;
            return new LogOperand(logOperand.getValue());
        } else {
            return new Nothing();
        }
    }

    private Expression interpret(Variable node) {
        return findVar(node.getId()).getValue();
    }

    private Nothing interpret(ExPrint node) {
        Expression exToPrint = interpretNode(node.getExToPrint());

        if (exToPrint instanceof ArOperand) { // peredelat'
            ArOperand arExToPrint = (ArOperand) exToPrint;
            output += arExToPrint.getValue().toString() + "\n";
        } else if (exToPrint instanceof LogOperand) { // peredelat'
            LogOperand logExToPrint = (LogOperand) exToPrint;
            output += logExToPrint.getValue().toString() + "\n";
        } else {
            //ExFunction funExToPrint = (ExFunction) exToPrint;
            output += "there is attempt to print function\n";
        }

        //       System.out.println(exToPrint.getValue());

        return new Nothing();
    }

    private Expression interpret(ExConditional node) {
        LogOperand cnd = (LogOperand) interpretNode(node.getLogExpression());

        if (cnd.getValue() == true) {
            return interpretNode(node.getThenExpression());
        } else {
            return interpretNode(node.getElsePart());
        }
    }

    // вспомогательные функции;
    private EnvironmentCell findVar(int id) {
        for (int i = 0; i < environment.size(); ++i) {
            if (environment.get(i).getId() == id) {
                return environment.get(i);
            }
        }

        //output += "id " + id + " undefined\n";
        //System.exit(0);
        fixError("no such id");
        return new EnvironmentCell(environment.size(), new ArOperand(0)); // позволяет завершить работу ин-
        // терпретатора, но с ошибкой;
    }

    private void fixError(String message) {
        errorCounter++;
        interpreterErrorLog += "interpreter error: " + message + ";\n";
    }
}
