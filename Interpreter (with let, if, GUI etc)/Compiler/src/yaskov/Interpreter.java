/*
 * 
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package yaskov;

import ast.*;
import lebedev.TableCell;
import java.util.LinkedList;

public class Interpreter {
    public Tree input;
    public Tree result;
    private String interpreterErrorLog = "";
    private String output = "";
    public LinkedList<StackCell> stack = new LinkedList<StackCell>();
    public LinkedList<TableCell> varTable = new LinkedList<TableCell>();
    public LinkedList<EnvironmentCell> environment = new LinkedList<EnvironmentCell>();
    private int errorCounter = 0;

    public Interpreter(Tree parserOutput, LinkedList<TableCell> varTable, int parseErrorQnt) {
        if (parseErrorQnt == 0) {
            input = parserOutput;
            this.varTable = varTable;
            //interpretSequence(parserOutput);
            System.out.println("--------------\n");
        }
        else {
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
        if (node instanceof ArOperand) {
            System.out.println(node.getClass().getSimpleName());
            return (ArOperand) node;
        }
        else if (node instanceof ArAddition) {
            System.out.println(node.getClass().getSimpleName());
            return interpret((ArAddition) node);
        }
        else if (node instanceof ArSubtraction) {
            System.out.println(node.getClass().getSimpleName());
            return interpret((ArSubtraction) node);
        }
        else if (node instanceof ArMultiplication) {
            System.out.println(node.getClass().getSimpleName());
            return interpret((ArMultiplication) node);
        }
        else if (node instanceof ArDivision) {
            System.out.println(node.getClass().getSimpleName());
            return interpret((ArDivision) node);
        }
        else if (node instanceof ArNegate) {
            System.out.println(node.getClass().getSimpleName());
            return interpret((ArNegate) node);
        }
        else if (node instanceof LogOperand) {
            //LogOperand nd = (LogOperand) node;
            System.out.println(node.getClass().getSimpleName());
            return (LogOperand) node;
        }
        else if (node instanceof LogAnd) {
            System.out.println(node.getClass().getSimpleName());
            return interpret((LogAnd) node);
        }
        else if (node instanceof LogOr) {
            System.out.println(node.getClass().getSimpleName());
            return interpret((LogOr) node);
        }
        else if (node instanceof LogNot) {
            System.out.println(node.getClass().getSimpleName());
            return interpret((LogNot) node);
        }
        else if (node instanceof LogEquality) {
            System.out.println(node.getClass().getSimpleName());
            return interpret((LogEquality) node);
        }
        else if (node instanceof LogInequality) {
            System.out.println(node.getClass().getSimpleName());
            return interpret((LogInequality) node);
        }
        else if (node instanceof LogGreater) {
            System.out.println(node.getClass().getSimpleName());
            return interpret((LogGreater) node);
        }
        else if (node instanceof LogLess) {
            System.out.println(node.getClass().getSimpleName());
            return interpret((LogLess) node);
        }
        else if (node instanceof LogGE) {
            System.out.println(node.getClass().getSimpleName());
            return interpret((LogGE) node);
        }
        else if (node instanceof LogLE) {
            System.out.println(node.getClass().getSimpleName());
            return interpret((LogLE) node);
        }
        else if (node instanceof Variable) {
            System.out.println(node.getClass().getSimpleName());
            return interpret((Variable) node);
        }
        else if (node instanceof ExBinding) {
            System.out.println(node.getClass().getSimpleName());
            return interpret((ExBinding) node);
        }
        else if (node instanceof ExSequence) {
            System.out.println(node.getClass().getSimpleName());
            return interpret((ExSequence) node);
        }
        else if (node instanceof ExPrint) {
            System.out.println(node.getClass().getSimpleName());
            return interpret((ExPrint) node);
        }
        else if (node instanceof ExConditional) {
            System.out.println(node.getClass().getSimpleName());
            return interpret((ExConditional) node);
        }
        return null;
    }

//    void interpret(ExSequence node) {
//        interpretNode(node.getLeft());
//        interpretNode(node.getRight());
//    }

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
        }
        else {
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

    private Nothing interpret(ExSequence sequence) {
        //LinkedList<Expression> res = new LinkedList<Expression>();
        if (sequence.getLeft() != null) {
            interpretNode(sequence.getLeft());
        }
        interpretNode(sequence.getRight());

//        for (int i = 0; i < sequence.getList().size(); ++i) {
//            res.add((Expression) interpretNode(sequence.getList().get(i)));
//        }
        return new Nothing();
    }
    
    private Expression interpret(ExBinding node) { // binding;
        ArOperand letExpession = (ArOperand) interpretNode(node.getLetExpression());
        EnvironmentCell environmentCell = new EnvironmentCell(node.getId(), letExpession.getValue());

        environment.add(environmentCell);
        Expression inExpression = interpretNode(node.getInExpression());
        environment.remove(environmentCell);

        if (inExpression instanceof ArOperand) {
            ArOperand arOperand = (ArOperand) inExpression;
            return new ArOperand(arOperand.getValue());
        }
        else if (inExpression instanceof LogOperand) { // приделать чё надо;
            LogOperand logOperand = (LogOperand) inExpression;
            return new LogOperand(logOperand.getValue());
        }
        else {
            return new Nothing();
        }
    }

    private Expression interpret(Variable node) {
        Object varValue = findVar(node.getId()).getValue();
        
        if (varValue instanceof Boolean) {
            return new LogOperand((Boolean) varValue);
        }
        else if (varValue instanceof Integer) {
            return new ArOperand((Integer) varValue);
        }
        else {
            fixError("smth strange");
            return null;
        }
    }

    private Nothing interpret(ExPrint node) {
        Expression exToPrint = interpretNode(node.getExToPrint());

        if (exToPrint instanceof ArOperand) { // peredelat'
            ArOperand arExToPrint = (ArOperand) exToPrint;
            output += arExToPrint.getValue().toString() + "\n";
        }
        else { // peredelat'
            LogOperand logExToPrint = (LogOperand) exToPrint;
            output += logExToPrint.getValue().toString() + "\n";
        }
        
 //       System.out.println(exToPrint.getValue());

        return new Nothing();
    }

    private Expression interpret(ExConditional node) {
        LogOperand cnd = (LogOperand)interpretNode(node.getLogExpression());
        Expression res;

        if (cnd.getValue() == true) {
            res = interpretNode(node.getThenExpression());
        }
        else {
            res = interpretNode(node.getElsePart());
        }

        return res;
    }

    // вспомогательные функции;
    
    private EnvironmentCell findVar(int id) {
        for (int i = 0; i < environment.size(); ++i) {
            if (environment.get(i).getId() == id) {
                return environment.get(i);
            }
        }

        fixError("no such id");
        return null;
    }

    private void fixError(String message) {
        errorCounter++;
        interpreterErrorLog += "interpreter error: " + message + ";\n";
    }
}
