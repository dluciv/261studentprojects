package ru.hotheart.interpreter.core;

import ru.hotheart.interpreter.lexer.Lexeme;
import ru.hotheart.interpreter.lexer.Lexer;
import ru.hotheart.interpreter.syntax.*;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class Interpreter {
    public Context context = new Context();

    public void process(String code) throws Exception {
        String[] lines = code.split(";\n");
        for(String i : lines)
        {
            processLine(i);
        }
    }

    private void processLine(String line) throws Exception {
        if (line.startsWith("print")) {
            String params = line.substring("print(".length(), line.length() - 1);

            //System.out.println("Calculating: " + params);
            System.out.println(calcExpression(params));
        } else {
            String[] data = line.split("=");
            double val = calcExpression(data[1]);
            if (context.containsKey(data[0]))
                context.remove(data[0]);
            context.put(data[0], val);
        }
    }

    private double calcExpression(String expression) throws Exception {
        Lexeme[] lexems = new Lexer().parse(expression);
        SyntaxTreeNode syntax = new SyntaxProcessor().process(lexems);
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
