package name.stepa.ml.model.interpreter;

import name.stepa.ml.model.interpreter.lexer.ComparisonLexeme;
import name.stepa.ml.model.interpreter.lexer.Lexeme;
import name.stepa.ml.model.interpreter.lexer.Lexer;
import name.stepa.ml.model.interpreter.syntax.*;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 08.05.2010
 * Time: 18:24:20
 * To change this template use File | Settings | File Templates.
 */
public class Interpreter {

    String[] lines;
    Context context;
    int nextLine;
    IOutput output = null;
    IInterpreterStateListener stateListener = null;

    public void setProgram(String program) {
        if (program == null) {
            lines = null;
        } else {
            lines = program.split(";\n");
            if (lines[lines.length - 1].endsWith(";"))
                lines[lines.length - 1] = lines[lines.length - 1].substring(0, lines[lines.length - 1].length() - 1);
            context = new Context();
            nextLine = -1;
            notifyStateChanging();
        }
    }

    public void setStateListener(IInterpreterStateListener stateListener) {
        this.stateListener = stateListener;
    }

    public void setOutput(IOutput output) {
        this.output = output;
    }

    private void println(String s) {
        if (output != null)
            output.println(s);
    }

    private void notifyStateChanging() {
        if (stateListener != null) {
            if ((lines.length > nextLine) && (nextLine >= 0)) {
                int chPos = 0;
                for (int i = 0; i < nextLine; i++) {
                    chPos += 2 + lines[i].length();
                }

                stateListener.onLineChanged(nextLine, chPos, chPos + lines[nextLine].length());
            }
        }
    }

    public void interpret() throws Exception {
        if (lines != null) {
            while ((++nextLine < lines.length) && (lines[nextLine].trim().equals(""))) ;
            if (lines.length <= nextLine)
                println("Reached end of document.");
            else {
                interpret(lines[nextLine]);
                notifyStateChanging();
            }
        } else
            println("Reached end of document.");
    }

    private void interpret(String expression) throws Exception {
        println("line: " + lines[nextLine]);
        Lexeme[] lexemes = new Lexer().parse(expression);
        println("lexemes: " + Arrays.toString(lexemes));
        SyntaxTreeNode syntax = new SyntaxProcessor().process(lexemes);
        println("syntax: " + syntax.toString());
        println("result: " + interpret(syntax).toString());
    }

    private Object interpret(SyntaxTreeNode node) throws Exception {
        if (node instanceof CaparisonTreeNode)
            return interpret((CaparisonTreeNode) node);
        if (node instanceof ValueTreeNode)
            return interpret((ValueTreeNode) node);
        if (node instanceof VariableTreeNode)
            return interpret((VariableTreeNode) node);
        if (node instanceof BinaryOperationTreeNode)
            return interpret((BinaryOperationTreeNode) node);
        if (node instanceof UnaryOperationTreeNode)
            return interpret((UnaryOperationTreeNode) node);
        if (node instanceof AssignNode)
            return interpret((AssignNode) node);
        if (node instanceof FunctionTreeNode)
            return interpret((FunctionTreeNode) node);
        if (node instanceof InTreeNode)
            return interpret((InTreeNode) node);

        throw new Exception("Unsupported syntax tree item: " + node.getClass().getSimpleName());
    }

    private Object interpret(InTreeNode node) throws Exception {
        Object old = null;
        if (context.containsKey(node.assignment.variable))
            old = context.get(node.assignment.variable);


        interpret(node.assignment);
        Object res = interpret(node.expression);

        if (old != null)
            context.put(node.assignment.variable, old);

        return res;
    }

    private Object interpret(AssignNode node) throws Exception {
        Object value = interpret(node.assignExpression);
        String name = node.variable;
        context.put(name, value);
        println("set value " + value + " -> " + name);
        return value;
    }

    private Object interpret(FunctionTreeNode node) throws Exception {
        String functionName = node.function;
        Object argument = interpret(node.argument);
        if (functionName.equals("print")) {
            println(argument.toString());
            return "void";
        }
        throw new Exception("Wrong function name: " + functionName);
    }


    private Object interpret(UnaryOperationTreeNode node) throws Exception {
        if (node.operation == UnaryOperationTreeNode.MINUS) {
            return -getAlgebraicValue(interpret(node.argument));
        } else if (node.operation == UnaryOperationTreeNode.NOT) {
            return !getLogicValue(interpret(node.argument));
        }

        throw new Exception("Unsupported unary operation: " + node.operation);
    }

    private Object interpret(CaparisonTreeNode node) throws Exception {
        Double left = (Double) interpret(node.left);
        Double right = (Double) interpret(node.right);
        switch (node.operation) {
            case ComparisonLexeme.E:
                return left == right;
            case ComparisonLexeme.NE:
                return left != right;
            case ComparisonLexeme.G:
                return left > right;
            case ComparisonLexeme.GE:
                return left >= right;
            case ComparisonLexeme.L:
                return left < right;
            case ComparisonLexeme.LE:
                return left <= right;
        }

        throw new Exception("Invalid comparison operation: " + node.operation);
    }

    private Object interpret(ValueTreeNode node) {
        return ((ValueTreeNode) node).value;
    }

    private Object interpret(VariableTreeNode node) {
        return context.get(((VariableTreeNode) node).variable);
    }

    private Object interpret(BinaryOperationTreeNode node) throws Exception {
        if (isAlgebraicOperation(node.operation)) {
            Double left = getAlgebraicValue(interpret(node.left));
            Double right = getAlgebraicValue(interpret(node.right));
            if (node.operation == '+')
                return left + right;
            else if (node.operation == '-')
                return left - right;
            else if (node.operation == '*')
                return left * right;
            else if (node.operation == '/')
                return left / right;
        } else {
            Boolean left = getLogicValue(interpret(node.left));
            Boolean right = getLogicValue(interpret(node.right));
            if (node.operation == '&')
                return left & right;
            else if (node.operation == '|')
                return left | right;
            else if (node.operation == '^')
                return left ^ right;
        }

        throw new Exception("Invalid binary operation: " + node.operation);
    }

    private boolean isAlgebraicOperation(char c) {
        if (c == '+')
            return true;
        else if (c == '-')
            return true;
        else if (c == '*')
            return true;
        else if (c == '/')
            return true;
        else
            return false;
    }

    private Double getAlgebraicValue(Object value) throws Exception {
        if (value instanceof Double)
            return (Double) value;

        throw new Exception("Type mismatch! Expected Double, got " + value.getClass().getSimpleName());
    }

    private Boolean getLogicValue(Object value) throws Exception {
        if (value instanceof Boolean)
            return (Boolean) value;

        throw new Exception("Type mismatch! Expected Boolean, got " + value.getClass().getSimpleName());
    }
}
