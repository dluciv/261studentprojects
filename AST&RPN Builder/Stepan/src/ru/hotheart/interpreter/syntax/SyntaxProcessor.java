package ru.hotheart.interpreter.syntax;

import ru.hotheart.interpreter.lexer.*;

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
        return processAdditive(data);
    }

    private SyntaxTreeNode processMult(Lexeme[] data) throws Exception {
        SyntaxTreeNode left = processValue(data);
        if (!(data[pointer] instanceof BinaryOperationLexeme)) {
            return left;
        }
        BinaryOperationLexeme lex = (BinaryOperationLexeme) data[pointer];
        if ((lex.operation == '*') || (lex.operation == '-')) {
            pointer++;
            return new BinaryOperationTreeNode(left, processMult(data), lex.operation);
        } else
            return left;
    }

    private SyntaxTreeNode processAdditive(Lexeme[] data) throws Exception {
        SyntaxTreeNode left = processValue(data);
        if (!(data[pointer] instanceof BinaryOperationLexeme)) {
            return left;
        }
        BinaryOperationLexeme lex = (BinaryOperationLexeme) data[pointer];
        if ((lex.operation == '+') || (lex.operation == '-')) {
            pointer++;
            return new BinaryOperationTreeNode(left, processMult(data), lex.operation);
        } else
            return left;
    }

    private SyntaxTreeNode processValue(Lexeme[] data) throws Exception {
        if (data[pointer] instanceof ValueLexeme) {

            ValueTreeNode res = new ValueTreeNode(((ValueLexeme) data[pointer]).value);
            pointer++;
            return res;
        }
        if (data[pointer] instanceof VariableLexeme) {

            VariableTreeNode res = new VariableTreeNode(((VariableLexeme) data[pointer]).value);
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
        SyntaxTreeNode res = processExpr(data);
        if (!(data[pointer] instanceof CloseBracketLexeme))
            throw new Exception("Syntax error!");
        pointer++;
        return res;
    }
}