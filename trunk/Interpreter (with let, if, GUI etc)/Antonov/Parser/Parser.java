/*
 * This class receives the string, then by the recursive descent builds the program tree
 * Antonov Kirill(c), 2010
 */
package name.kirill.ml.parser;

import name.kirill.ml.exception.ParserException;
import name.kirill.ml.ast.Begin;
import name.kirill.ml.ast.Unequality;
import name.kirill.ml.ast.And;
import name.kirill.ml.ast.Application;
import name.kirill.ml.ast.BinaryOperation;
import name.kirill.ml.ast.Div;
import name.kirill.ml.ast.GE;
import name.kirill.ml.ast.Negate;
import name.kirill.ml.ast.Number;
import name.kirill.ml.ast.If;
import name.kirill.ml.ast.BooleanOp;
import name.kirill.ml.ast.Binding;
import name.kirill.ml.ast.Identifier;
import name.kirill.ml.ast.Or;
import name.kirill.ml.ast.Equality;
import name.kirill.ml.ast.Expression;
import name.kirill.ml.ast.Function;
import name.kirill.ml.ast.Minus;
import name.kirill.ml.ast.Mult;
import name.kirill.ml.ast.Less;
import name.kirill.ml.ast.LE;
import name.kirill.ml.ast.Print;
import name.kirill.ml.ast.Plus;
import name.kirill.ml.ast.Greater;
import name.kirill.ml.ast.Sequence;
import name.kirill.ml.ast.Type;
import name.kirill.ml.ast.Types;
import name.kirill.ml.exception.IncompatibleTypedException;

import name.kirill.ml.exception.RightBracketException;
import name.kirill.ml.exception.UnknownSymbolException;
import name.kirill.ml.lexer.LexemKind;
import name.kirill.ml.lexer.Lexer;

public class Parser {

    private Lexer lexer;

    public Parser(Lexer args) {
        lexer = args;
        lexer.moveNext();
    }

    public Type ParseType() throws ParserException {
        Types left = null;
        Types right = null;

        if (lexer.getCurrent().getTypeLexem() == LexemKind.LeftBracket) {
            lexer.moveNext();
            left = ParseSimpleType();
            lexer.moveNext();
            if (lexer.getCurrent().getTypeLexem() == LexemKind.ARROW) {
                lexer.moveNext();
            } else {
                throw new ParserException(lexer.getCurrent().getPosition());
            }
            right = ParseSimpleType();
            lexer.moveNext();
            if (lexer.getCurrent().getTypeLexem() == LexemKind.RightBracket) {
                lexer.moveNext();
            } else {
                throw new ParserException(lexer.getCurrent().getPosition());
            }
        } else {
            left = ParseSimpleType();
            lexer.moveNext();
        }
        return new Type(left, right);
    }

    public Types ParseSimpleType() throws ParserException {
        switch (lexer.getCurrent().getTypeLexem()) {
            case Int:
                return Types.Int;
            case Bool:
                return Types.Bool;
            case Unit:
                return Types.Unit;
            default:
                throw new ParserException(lexer.getCurrent().getPosition());
        }
    }

    private Type checkTypes(Expression expr) throws IncompatibleTypedException {
        Type left = null;
        Type right = null;

        if (expr.getClass() == Number.class) {
            return new Type(Types.Int);
        }
        if (expr.getClass() == BooleanOp.class) {
            return new Type(Types.Bool);
        }
        if (expr.getClass() == Print.class) {
            return new Type(Types.Unit);
        }
        if (expr instanceof BinaryOperation) {
            if ((((BinaryOperation) expr) instanceof Plus)
                    || (((BinaryOperation) expr) instanceof Minus)
                    || (((BinaryOperation) expr) instanceof Mult)
                    || (((BinaryOperation) expr) instanceof Div)) {
                return new Type(Types.Int);
            } else {
                return new Type(Types.Bool);
            }
        }
        if (expr.getClass() == Negate.class) {
            return new Type(Types.Bool);
        }
        if (expr.getClass() == Function.class) {
            if (((((Function) expr).getIdentifier().GetType().RightNode()) != null)
                    && (checkTypes(((Function) expr).getExpression())) != null) {
                if ((((Function) expr).getIdentifier().GetType().RightNode())
                        != checkTypes(((Function) expr).getExpression()).LeftNode()) {
                    throw new IncompatibleTypedException(lexer.getCurrent().getPosition());
                }
            }
            return (((Function) expr).getIdentifier().GetType());
        }
        if (expr.getClass() == Identifier.class) {
            return (((Identifier) expr).GetType());
        }
        if (expr.getClass() == If.class) {
            left = checkTypes(((If) expr).getIfExpression());
            right = checkTypes(((If) expr).getElseExpression());
        }
        if (expr.getClass() == Application.class) {
            return checkTypes(((Application) expr).getExpression());
        }
        if (expr.getClass() == Binding.class) {
            left = checkTypes(((Binding) expr).getIdentifier());
            right = checkTypes(((Binding) expr).getExpression());
            if (((((Binding) expr).getIdentifier().GetType().RightNode()) != null)
                    && ((checkTypes(((Binding) expr).getValue())) != null)) {
                if ((((Binding) expr).getIdentifier().GetType().LeftNode())
                        != checkTypes(((Binding) expr).getValue()).LeftNode()) {
                    throw new IncompatibleTypedException(lexer.getCurrent().getPosition());
                }
            }
        }


        if ((left == null) || (right == null)) {
            if (left == right) {
                return new Type(Types.Unit);
            }
            throw new IncompatibleTypedException(null);
        }
        if (!((left.LeftNode() == right.LeftNode()) && (left.RightNode() == right.RightNode()))) {
            throw new IncompatibleTypedException(null);
        }

        return new Type(Types.Unit);
    }

    public Sequence getSequence() throws ParserException, IncompatibleTypedException {
        Sequence sequence = new Sequence();
        while (lexer.getCurrent().getTypeLexem() != LexemKind.EOL && lexer.getCurrent().getTypeLexem() != LexemKind.END) {
            if (lexer.getCurrent().getTypeLexem() == LexemKind.semicolon) {
                lexer.moveNext();
            } else {
                sequence.addStatement(getExpression());
            }
        }

        for (Expression expr : sequence.returnStatement()) {
            checkTypes(expr);
        }

        return sequence;
    }

    private Expression getBoolExpression() throws ParserException {

        return boolExpression();

    }

    private Expression boolExpression() throws ParserException {
        Expression left = null;

        left = or();
        while (lexer.getCurrent().getTypeLexem() == LexemKind.Or) {
            lexer.moveNext();
            Expression right = or();
            left = new Or(left, right);
        }

        return left;
    }

    private Expression or() throws ParserException {
        Expression left = null;

        left = and();
        while (lexer.getCurrent().getTypeLexem() == LexemKind.And) {
            lexer.moveNext();
            Expression right = and();
            left = new And(left, right);
        }

        return left;
    }

    private Expression and() throws ParserException {
        return comparison();
    }

    private Expression comparison() throws ParserException {
        Expression left = null;
        Expression right = null;

        left = arithmeticExpression();
        switch (lexer.getCurrent().getTypeLexem()) {
            case LE:
                lexer.moveNext();
                right = comparison();
                return new LE(left, right);
            case GE:
                lexer.moveNext();
                right = comparison();
                return new GE(left, right);
            case Greater:
                lexer.moveNext();
                right = comparison();
                return new Greater(left, right);
            case Less:
                lexer.moveNext();
                right = comparison();
                return new Less(left, right);
            case unequality:
                lexer.moveNext();
                right = comparison();
                return new Unequality(left, right);
            case equality:
                lexer.moveNext();
                right = comparison();
                return new Equality(left, right);
            case TRUE:
                lexer.moveNext();
                return new BooleanOp(true);
            case FALSE:
                lexer.moveNext();
                return new BooleanOp(false);
            default:
                return left;
        }
    }

    private Expression getExpression() throws ParserException {
        Expression left = null;

        left = boolExpression();
        if (lexer.getCurrent().getTypeLexem() == LexemKind.Unknown) {
            throw new UnknownSymbolException(lexer.getCurrent().getPosition());
        }

        return left;
    }

    private Expression arithmeticExpression() throws ParserException {
        Expression left = null;

        left = term();
        while (lexer.getCurrent().getTypeLexem() == LexemKind.Plus
                || lexer.getCurrent().getTypeLexem() == LexemKind.Minus) {
            LexemKind sign = lexer.getCurrent().getTypeLexem();
            lexer.moveNext();
            Expression right = term();
            if (sign == LexemKind.Plus) {
                left = new Plus(left, right);
            } else {
                left = new Minus(left, right);
            }
        }

        return left;
    }

    private Expression term() throws ParserException {
        Expression left = null;

        left = Application();
        while (lexer.getCurrent().getTypeLexem() == LexemKind.Multiply || lexer.getCurrent().getTypeLexem() == LexemKind.Divide) {
            LexemKind sign = lexer.getCurrent().getTypeLexem();
            lexer.moveNext();
            Expression right = UnarOp();
            if (sign == LexemKind.Multiply) {
                left = new Mult(left, right);
            } else {
                left = new Div(left, right);
            }
        }

        return left;
    }

    private Expression Application() throws ParserException {
        Expression left = UnarOp();

        switch (lexer.getCurrent().getTypeLexem()) {
            case Number:
            case TRUE:
            case FALSE:
            case LeftBracket:
            case Identifier:
            case FUN:
            case LET:
            case PRINT:
            case IF:
            case BEGIN:
                return new Application(left, UnarOp());
            default:
                break;
        }

        return left;
    }

    private Expression UnarOp() throws ParserException {
        if (lexer.getCurrent().getTypeLexem() == LexemKind.No) {
            lexer.moveNext();
            return new Negate(prime());
        } else {
            return prime();
        }
    }

    private Expression prime() throws ParserException {
        Expression left = null;
        Expression expr = null;
        Expression expr2 = null;
        Identifier identificator = null;

        if (lexer.getCurrent().getTypeLexem() == LexemKind.Number) {
            left = new Number(lexer.getCurrent().getIntLexem());
            lexer.moveNext();
            return left;
        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.TRUE) {
            left = new BooleanOp(true);
            lexer.moveNext();
            return left;
        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.Minus) {
            left = new Number(0);
            lexer.moveNext();
            Minus minus = new Minus(left, arithmeticExpression());
            return minus;

        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.FALSE) {
            left = new BooleanOp(false);
            lexer.moveNext();
            return left;
        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.LeftBracket) {
            lexer.moveNext();
            left = arithmeticExpression();
            if (lexer.getCurrent().getTypeLexem() != LexemKind.RightBracket) {
                throw new RightBracketException(lexer.getCurrent().getPosition());
            }
            lexer.moveNext();
            return left;
        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.Identifier) {
            left = new Identifier(lexer.getCurrent().getStringLexem());
            lexer.moveNext();
            return left;
        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.FUN) {
            lexer.moveNext();
            if (lexer.getCurrent().getTypeLexem() != LexemKind.Identifier) {
                throw new ParserException(lexer.getCurrent().getPosition());
            }
            String idName = lexer.getCurrent().getStringLexem();
            lexer.moveNext();
            if (lexer.getCurrent().getTypeLexem() == LexemKind.Colon) {
                lexer.moveNext();
                identificator = new Identifier(idName, ParseType());
            } else {
                throw new ParserException(lexer.getCurrent().getPosition());
            }

            if (lexer.getCurrent().getTypeLexem() != LexemKind.ARROW) {
                throw new ParserException(lexer.getCurrent().getPosition());
            }
            lexer.moveNext();
            expr = getExpression();
            return new Function(identificator, expr);

        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.LET) {
            lexer.moveNext();
            if (lexer.getCurrent().getTypeLexem() != LexemKind.Identifier) {
                throw new ParserException(lexer.getCurrent().getPosition());
            }
            String idName = lexer.getCurrent().getStringLexem();
            lexer.moveNext();
            if (lexer.getCurrent().getTypeLexem() == LexemKind.Colon) {
                lexer.moveNext();
                identificator = new Identifier(idName, ParseType());
            } else {
                throw new ParserException(lexer.getCurrent().getPosition());
            }


            if (lexer.getCurrent().getTypeLexem() != LexemKind.equality) {
                throw new ParserException(lexer.getCurrent().getPosition());
            }
            lexer.moveNext();
            expr = getExpression();
            if (lexer.getCurrent().getTypeLexem() != LexemKind.IN) {
                throw new ParserException(lexer.getCurrent().getPosition());
            }
            lexer.moveNext();
            expr2 = getExpression();
            return new Binding(identificator, expr, expr2);
        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.PRINT) {
            lexer.moveNext();
            return new Print(getExpression());
        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.IF) {
            Expression else_expr = null;
            lexer.moveNext();
            expr = getBoolExpression();
            if (lexer.getCurrent().getTypeLexem() != LexemKind.THEN) {
                throw new ParserException(lexer.getCurrent().getPosition());
            }
            lexer.moveNext();
            expr2 = getExpression();
            if (lexer.getCurrent().getTypeLexem() == LexemKind.ELSE) {
                lexer.moveNext();
                else_expr = getExpression();
            }
            return new If(expr, expr2, else_expr);
        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.BEGIN) {
            Sequence seq;
            lexer.moveNext();
            seq = getSequence();
            if (lexer.getCurrent().getTypeLexem() != LexemKind.END) {
                throw new ParserException(lexer.getCurrent().getPosition());
            }
            lexer.moveNext();
            return new Begin(seq);
        } else {
            throw new ParserException(lexer.getCurrent().getPosition());
        }
    }
}
//let f=fun x:Int->>Unit -> print x in x 2

