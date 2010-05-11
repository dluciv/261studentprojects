package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.lexer.*;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class SyntaxProcessor {

    private int pointer;

    public SyntaxTreeNode process(Lexeme[] data) throws Exception {
        pointer = 0;
        return processExpr(data);
    }


    private SyntaxTreeNode processExpr(Lexeme[] data) throws Exception {
        if (data[0] instanceof LetLexeme) {
            String variable = ((IdentifierLexeme) data[1]).value;
            pointer += 3;
            return new AssignNode(variable, processLogic(data));
        } else if (data[0] instanceof IdentifierLexeme) {
            String variable = ((IdentifierLexeme) data[0]).value;
            pointer++;
            return new FunctionTreeNode(variable, processLogic(data));
        } else
            throw new Exception("Wrong expression!");
    }

    private SyntaxTreeNode processLogic(Lexeme[] data) throws Exception {
        if (data[pointer] instanceof NotLexeme) {
            pointer++;
            return new UnaryOperationTreeNode(UnaryOperationTreeNode.NOT, processLogic(data));
        }
        SyntaxTreeNode left = processComparison(data);
        if (!(data[pointer] instanceof OperationLexeme)) {
            return left;
        } else {
            char op = ((OperationLexeme) data[pointer]).operation;
            if (op == '&') {
                pointer++;
                return new BinaryOperationTreeNode(left, processLogic(data), op);
            }
        }

        throw new Exception("Syntax error!");
    }

    private SyntaxTreeNode processComparison(Lexeme[] data) throws Exception {
        SyntaxTreeNode left = processAlgebraic(data);
        if (!(data[pointer] instanceof ComparisonLexeme)) {
            return left;
        }
        ComparisonLexeme lex = (ComparisonLexeme) data[pointer];
        pointer++;
        return new CaparisonTreeNode(lex.type, left, processAlgebraic(data));
    }

    private SyntaxTreeNode processAlgebraic(Lexeme[] data) throws Exception {
        return processAdditive(data);
    }


    private SyntaxTreeNode processMult(Lexeme[] data) throws Exception {
        SyntaxTreeNode left = processValue(data);
        if (!(data[pointer] instanceof OperationLexeme)) {
            return left;
        }
        OperationLexeme lex = (OperationLexeme) data[pointer];
        if ((lex.operation == '*') || (lex.operation == '/')) {
            pointer++;
            return new BinaryOperationTreeNode(left, processMult(data), lex.operation);
        } else
            return left;
    }

    private SyntaxTreeNode processAdditive(Lexeme[] data) throws Exception {
        if ((data[pointer] instanceof OperationLexeme) && (((OperationLexeme) data[pointer]).operation == '-')) {
            pointer++;
            return new UnaryOperationTreeNode(UnaryOperationTreeNode.MINUS, processAdditive(data));
        }

        SyntaxTreeNode left = processMult(data);
        if (!(data[pointer] instanceof OperationLexeme)) {
            return left;
        }
        OperationLexeme lex = (OperationLexeme) data[pointer];
        if ((lex.operation == '+') || (lex.operation == '-')) {
            pointer++;
            return new BinaryOperationTreeNode(left, processAdditive(data), lex.operation);
        } else
            return left;
    }

    private SyntaxTreeNode processValue(Lexeme[] data) throws Exception {
        if (data[pointer] instanceof ValueLexeme) {

            ValueTreeNode res = new ValueTreeNode(((ValueLexeme) data[pointer]).value);
            pointer++;
            return res;
        }
        if (data[pointer] instanceof IdentifierLexeme) {

            VariableTreeNode res = new VariableTreeNode(((IdentifierLexeme) data[pointer]).value);
            pointer++;
            return res;
        }
        if (data[pointer] instanceof OpenBracketLexeme) {
            return processBracket(data);
        }
        throw new Exception("Syntax error!");
    }

    private SyntaxTreeNode processBracket(Lexeme[] data) throws Exception {
        pointer++;
        SyntaxTreeNode res = processComparison(data);
        if (!(data[pointer] instanceof CloseBracketLexeme))
            throw new Exception("Syntax error!");
        pointer++;
        return res;
    }
}