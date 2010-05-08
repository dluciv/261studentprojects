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

    public void setProgram(String program) {
        if (program == null) {
            lines = null;
        } else {
            lines = program.split(";\n");
            context = new Context();
            nextLine = 0;
        }
    }

    public void setOutput(IOutput output) {
        this.output = output;
    }

    public void interpret() throws Exception {
        if ((lines != null) && (nextLine < lines.length)) {
            processLine(lines[nextLine]);
            nextLine++;
        } else
            println("Reached end of document.");
    }

    private void println(String s) {
        if (output != null)
            output.println(s);
    }

    private void processLine(String line) throws Exception {
        if (line.startsWith("print")) {
            String params = line.substring("print(".length(), line.length() - 1);
            println(Double.toString(calcExpression(params)));
        } else if (line.startsWith("let")) {
            String[] data = line.substring(4).split("=");
            double val = calcExpression(data[1]);
            if (context.containsKey(data[0]))
                context.remove(data[0]);
            context.put(data[0], val);
            println("set value " + val + " -> " + data[0]);
        } else if (!line.isEmpty()) {
            throw new Exception("Wrong expression!");
        }
    }

    private double calcExpression(String expression) throws Exception {
        Lexeme[] lexems = new Lexer().parse(expression);
        println("lexemes: " + Arrays.toString(lexems));
        SyntaxTreeNode syntax = new SyntaxProcessor().process(lexems);
        println("syntax: " + syntax.toString());
        return calcExpression(syntax);
    }

    private double calcExpression(SyntaxTreeNode node) throws Exception {
        if (node instanceof ValueTreeNode)
            return ((ValueTreeNode) node).value;
        if (node instanceof VariableTreeNode)
            return context.get(((VariableTreeNode) node).variable);
        else {
            BinaryOperationTreeNode op = (BinaryOperationTreeNode) node;
            double left = calcExpression(node.left);
            double right = calcExpression(node.right);
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
