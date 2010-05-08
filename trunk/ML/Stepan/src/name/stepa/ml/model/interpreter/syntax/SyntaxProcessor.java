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
        if (data[0] instanceof IdentifierLexeme) {
            if (((IdentifierLexeme) data[0]).value.equals("let")) {
                String variable = ((IdentifierLexeme) data[1]).value;
                pointer += 3;
                return new AssignNode(variable, processAlgebraic(data));
            } else {
                String variable = ((IdentifierLexeme) data[0]).value;
                pointer++;
                return new FunctionTreeNode(variable, processAlgebraic(data));
            }
        } else
            throw new Exception("Wrong expression!");
    }

    private SyntaxTreeNode processAlgebraic(Lexeme[] data) throws Exception {
        return processAdditive(data);
    }


    private SyntaxTreeNode processMult(Lexeme[] data) throws Exception {
        SyntaxTreeNode left = processValue(data);
        if (!(data[pointer] instanceof BinaryOperationLexeme)) {
            return left;
        }
        BinaryOperationLexeme lex = (BinaryOperationLexeme) data[pointer];
        if ((lex.operation == '*') || (lex.operation == '/')) {
            pointer++;
            return new BinaryOperationTreeNode(left, processMult(data), lex.operation);
        } else
            return left;
    }

    private SyntaxTreeNode processAdditive(Lexeme[] data) throws Exception {
        SyntaxTreeNode left = processMult(data);
        if (!(data[pointer] instanceof BinaryOperationLexeme)) {
            return left;
        }
        BinaryOperationLexeme lex = (BinaryOperationLexeme) data[pointer];
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
        SyntaxTreeNode res = processAlgebraic(data);
        if (!(data[pointer] instanceof CloseBracketLexeme))
            throw new Exception("Syntax error!");
        pointer++;
        return res;
    }
}