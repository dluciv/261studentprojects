package name.stepa.ml.model.interpreter;

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
            //println("Processing: " + lines[nextLine]);
            calcExpression(lines[nextLine]);
            notifyStateChanging();
        } else
            println("Reached end of document.");
    }

    private void calcExpression(String expression) throws Exception {
        Lexeme[] lexeems = new Lexer().parse(expression);
        //println("lexemes: " + Arrays.toString(lexeems));
        SyntaxTreeNode syntax = new SyntaxProcessor().process(lexeems);
        //println("syntax: " + syntax.toString());
        if (syntax instanceof AssignNode) {
            double value = calcAlgebraic(syntax.left);
            String name = ((AssignNode) syntax).variable;
            context.put(name, value);
            println("set value " + value + " -> " + name);
        } else if (syntax instanceof FunctionTreeNode) {
            String functionName = ((FunctionTreeNode) syntax).function;
            double argument = calcAlgebraic(syntax.left);

            if (functionName.equals("print")) {
                println(Double.toString(argument));
            }
        }
    }

    private double calcAlgebraic(SyntaxTreeNode node) throws Exception {
        if (node instanceof ValueTreeNode)
            return ((ValueTreeNode) node).value;
        if (node instanceof VariableTreeNode)
            return context.get(((VariableTreeNode) node).variable);
        else {
            BinaryOperationTreeNode op = (BinaryOperationTreeNode) node;
            double left = calcAlgebraic(node.left);
            double right = calcAlgebraic(node.right);
            if (op.operation == '+')
                return left + right;
            else if (op.operation == '-')
                return left - right;
            else if (op.operation == '*')
                return left * right;
            else if (op.operation == '/')
                return left / right;
        }

        throw new Exception("Calculation error!");
    }
}
