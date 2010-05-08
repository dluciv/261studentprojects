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
        if (stateListener != null)
            stateListener.onLineChanged(nextLine);
    }

    public void interpret() throws Exception {
        if ((lines != null) && (++nextLine < lines.length)) {
            calcLine(lines[nextLine]);
            notifyStateChanging();
        } else
            println("Reached end of document.");
    }

    private void calcLine(String expression) throws Exception {
        println("line: " + lines[nextLine]);
        Lexeme[] lexeems = new Lexer().parse(expression);
        println("lexemes: " + Arrays.toString(lexeems));
        SyntaxTreeNode syntax = new SyntaxProcessor().process(lexeems);
        println("syntax: " + syntax.toString());
        if (syntax instanceof AssignNode) {
            Object value = interpret(syntax.left);
            String name = ((AssignNode) syntax).variable;
            context.put(name, value);
            println("set value " + value + " -> " + name);
        } else if (syntax instanceof FunctionTreeNode) {
            String functionName = ((FunctionTreeNode) syntax).function;
            Object argument = interpret(syntax.left);

            if (functionName.equals("print")) {
                println(argument.toString());
            }
        }
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

        throw new Exception("Unsupported syntax tree item: " + node.getClass().getSimpleName());
    }


    private Object interpret(CaparisonTreeNode node) throws Exception {
        Double left = (Double) interpret(node.left);
        Double right = (Double) interpret(node.right);
        switch (node.operation) {
            case ComparisonLexeme.EQUALITY:
                return left == right;
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

        throw new Exception("Invalid binary operation: " + node.operation);
    }

    private Double getAlgebraicValue(Object value) throws Exception {
        if (value instanceof Double)
            return (Double) value;
        if (value instanceof Boolean)
            return ((Boolean) value) ? 1.0 : 0;

        throw new Exception("Unsupported value: " + value.getClass().getSimpleName());
    }
}
