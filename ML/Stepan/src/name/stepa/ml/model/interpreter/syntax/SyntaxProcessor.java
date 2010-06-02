package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.exceptions.LexemeTypeMismatchException;
import name.stepa.ml.model.interpreter.lexer.*;
import name.stepa.ml.model.interpreter.lexer.keywords.*;

import java.util.ArrayList;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class SyntaxProcessor {

    public static SyntaxTreeNode process(LexemeStream stream) throws LexemeTypeMismatchException {
        return processExpressionList(stream);
    }

    private static SyntaxTreeNode processExpressionList(LexemeStream stream) throws LexemeTypeMismatchException {
        if (stream.current() instanceof BeginLexeme) {
            BeginLexeme begin = (BeginLexeme) stream.current();
            ArrayList<SyntaxTreeNode> nodes = new ArrayList<SyntaxTreeNode>();
            stream.next();

            while ((!(stream.current() instanceof EndLexeme)) && (!(stream.current() instanceof EOFLexeme))) {
                if ((stream.current() instanceof SemicolonLexeme)) {
                    stream.next();
                    continue;
                }
                nodes.add(processExpressionList(stream));
                if (!(stream.current() instanceof SemicolonLexeme)) {
                    throw new LexemeTypeMismatchException("SemicolonLexeme", stream.current());
                } else
                    stream.next();
            }
            if (!(stream.current() instanceof EndLexeme))
                throw new LexemeTypeMismatchException("EndLexeme", stream.current());

            EndLexeme end = (EndLexeme) stream.currentAndNext();
            return new ExpressionListTreeNode(nodes.toArray(new SyntaxTreeNode[0]), begin, end);
        } else
            return processExpression(stream);
    }


    private static SyntaxTreeNode processExpression(LexemeStream stream) throws LexemeTypeMismatchException {
        if (stream.current() instanceof LetLexeme) {
            LetLexeme lexeme = (LetLexeme) stream.current();
            String variable = ((IdentifierLexeme) stream.next()).value;

            stream.next();
            if (!(stream.current() instanceof AssignLexeme))
                throw new LexemeTypeMismatchException("AssignLexeme", stream.current());

            stream.next();
            AssignNode assign = new AssignNode(variable, processExpression(stream), lexeme);
            if ((stream.haveNext()) && ((stream.current() instanceof InLexeme))) {
                stream.next();
                return new InTreeNode(assign, processExpressionList(stream));
            } else
                return assign;
        } else if (stream.current() instanceof IfLexeme) {
            IfLexeme lexeme = (IfLexeme) stream.current();
            stream.next();
            SyntaxTreeNode comp = processExpression(stream);
            if (!(stream.current() instanceof ThenLexeme))
                throw new LexemeTypeMismatchException("ThenLexeme", stream.current());
            stream.next();
            SyntaxTreeNode thenExpr = processExpression(stream);
            if (!(stream.current() instanceof ElseLexeme))
                throw new LexemeTypeMismatchException("ElseLexeme", stream.current());
            stream.next();
            SyntaxTreeNode elseExpr = processExpression(stream);
            return new IfTreeNode(comp, thenExpr, elseExpr, lexeme);
        } else if (stream.current() instanceof FunLexeme) {
            FunLexeme lexeme = (FunLexeme) stream.current();
            String argument = ((IdentifierLexeme) stream.next()).value;
            if (!(stream.next() instanceof ArrowLexeme))
                throw new LexemeTypeMismatchException("ArrowLexeme", stream.current());
            stream.next();

            SyntaxTreeNode expression = processExpression(stream);
            return new FunctionDeclarationTreeNode(expression, argument, lexeme);
        } else {
            SyntaxTreeNode res = processLogic(stream);
            if (isEndOfExpression(stream.current()))
                return res;
            else {
                return new ExpressionCallTreeNode(res, processExpression(stream));
            }
        }
    }

    private static boolean isEndOfExpression(Lexeme lex) {
        if (lex instanceof SemicolonLexeme)
            return true;
        if (lex instanceof ThenLexeme)
            return true;
        if (lex instanceof ElseLexeme)
            return true;
        if (lex instanceof InLexeme)
            return true;
        if (lex instanceof EOFLexeme)
            return true;
        if (lex instanceof CloseBracketLexeme)
            return true;

        return false;
    }

    private static SyntaxTreeNode processLogic(LexemeStream stream) throws LexemeTypeMismatchException {
        if (stream.current() instanceof NotLexeme) {
            Lexeme lexeme = stream.current();
            stream.next();
            return new UnaryOperationTreeNode(UnaryOperationTreeNode.NOT, processLogic(stream), lexeme);
        }
        SyntaxTreeNode left = processComparison(stream);
        if (!(stream.current() instanceof OperationLexeme)) {
            return left;
        } else {
            char op = ((OperationLexeme) stream.current()).operation;
            if (op == '&') {
                stream.next();
                return new BinaryOperationTreeNode(left, processLogic(stream), op);
            } else if (op == '|') {
                stream.next();
                return new BinaryOperationTreeNode(left, processLogic(stream), op);
            }
            throw new LexemeTypeMismatchException("OperationLexeme - & or |", stream.current());
        }


    }

    private static SyntaxTreeNode processComparison(LexemeStream stream) throws LexemeTypeMismatchException {
        SyntaxTreeNode left = processAlgebraic(stream);
        if (!(stream.current() instanceof ComparisonLexeme)) {
            return left;
        }
        ComparisonLexeme lex = (ComparisonLexeme) stream.current();
        stream.next();
        return new CaparisonTreeNode(lex.type, left, processAlgebraic(stream));
    }

    private static SyntaxTreeNode processAlgebraic(LexemeStream stream) throws LexemeTypeMismatchException {
        return processAdditive(stream);
    }


    private static SyntaxTreeNode processAdditive(LexemeStream stream) throws LexemeTypeMismatchException {
        if ((stream.current() instanceof OperationLexeme) && (((OperationLexeme) stream.current()).operation == '-')) {
            Lexeme lexeme = stream.current();
            stream.next();
            return new UnaryOperationTreeNode(UnaryOperationTreeNode.MINUS, processAdditive(stream), lexeme);
        }

        SyntaxTreeNode left = processFraction(stream);
        if (!(stream.current() instanceof OperationLexeme)) {
            return left;
        }
        OperationLexeme lex = (OperationLexeme) stream.current();
        if ((lex.operation == '+') || (lex.operation == '-')) {
            stream.next();
            return new BinaryOperationTreeNode(left, processAdditive(stream), lex.operation);
        } else
            return left;
    }

    private static SyntaxTreeNode processFraction(LexemeStream stream) throws LexemeTypeMismatchException {
        SyntaxTreeNode left = processValue(stream);
        if (!(stream.current() instanceof OperationLexeme)) {
            return left;
        }
        OperationLexeme lex = (OperationLexeme) stream.current();
        if ((lex.operation == '*') || (lex.operation == '/')) {
            stream.next();
            return new BinaryOperationTreeNode(left, processFraction(stream), lex.operation);
        } else
            return left;
    }

    private static SyntaxTreeNode processValue(LexemeStream stream) throws LexemeTypeMismatchException {
        if (stream.current() instanceof ValueLexeme) {
            return new ValueTreeNode(((ValueLexeme) stream.current()).value, (ValueLexeme) stream.currentAndNext());
        }
        if (stream.current() instanceof IdentifierLexeme) {

            return new VariableTreeNode(((IdentifierLexeme) stream.current()).value, (IdentifierLexeme) stream.currentAndNext());
        }
        if (stream.current() instanceof OpenBracketLexeme) {
            return processBracket(stream);
        }
        throw new LexemeTypeMismatchException("ValueLexeme or IdentifierLexeme or OpenBracketLexeme", stream.current());
    }

    private static SyntaxTreeNode processBracket(LexemeStream stream) throws LexemeTypeMismatchException {
        OpenBracketLexeme start = (OpenBracketLexeme) stream.current();
        stream.next();
        SyntaxTreeNode res = processExpression(stream);
        if (!(stream.current() instanceof CloseBracketLexeme))
            throw new LexemeTypeMismatchException("CloseBracketLexeme", stream.current());
        CloseBracketLexeme end = (CloseBracketLexeme) stream.current();
        stream.next();
        return new BracketsTreeNode(res, start, end);
    }
}