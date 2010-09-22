/*
 * This class receives the string, then by the recursive descent builds the program tree
 * Antonov Kirill(c), 2010
 */
package Parser;

import Exception.ParserException;
import AST.Begin;
import AST.unequality;
import AST.And;
import AST.Application;
import AST.Div;
import AST.GE;
import AST.Negate;
import AST.Number;
import AST.If;
import AST.BooleanOp;
import AST.Binding;
import AST.Identificator;
import AST.Or;
import AST.equality;
import AST.Expression;
import AST.Function;
import AST.Minus;
import AST.Mult;
import AST.Less;
import AST.LE;
import AST.Print;
import AST.Plus;
import AST.Greater;
import AST.Sequence;
import AST.Type;
import AST.Types;

import Exception.RightBracketException;
import Exception.UnknownSymbolException;
import Lexer.LexemKind;
import Lexer.LexerTwo;

public class ParserTwo {

    private LexerTwo lexer;
    //private LexemKind Type;

    public ParserTwo(LexerTwo args) {
        lexer = args;
        lexer.moveNext();
    }

    public Type ParseType() throws ParserException {
        return ParseTypeFunction();
    }

    public Type ParseTypeFunction() throws ParserException {
        Type temp = ParseSimpleType();
        if (lexer.getCurrent().getTypeLexem() == LexemKind.TypeArrow) {
            lexer.moveNext();
            Type fun = new Type(temp, ParseTypeFunction(), Types.Function);
            return fun;
        }
        return temp;
    }

    public Type ParseSimpleType() throws ParserException {
        Type temp = null;
        if (lexer.getCurrent().getTypeLexem() == LexemKind.LeftBracket) {
            lexer.moveNext();
            temp = ParseTypeFunction();
            if (lexer.getCurrent().getTypeLexem() != LexemKind.RightBracket) {
                throw new ParserException(lexer.getCurrent().getPosition());
                //error
            }
            lexer.moveNext();
        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.Int) {
            temp = new Type(Types.Int);
            lexer.moveNext();
        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.Bool) {
            temp = new Type(Types.Bool);
            lexer.moveNext();
        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.Unit) {
            temp = new Type(Types.Unit);
            lexer.moveNext();
        }
        return temp;
    }

    public Sequence getSequence() throws ParserException {
        Sequence sequence = new Sequence();
        while (lexer.getCurrent().getTypeLexem() != LexemKind.EOL && lexer.getCurrent().getTypeLexem() != LexemKind.END) {
            if (lexer.getCurrent().getTypeLexem() == LexemKind.semicolon) {
                lexer.moveNext();
            } else {
                sequence.addStatement(getExpression());
            }
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
                return new unequality(left, right);
            case equality:
                lexer.moveNext();
                right = comparison();
                return new equality(left, right);
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
            case Identificator:
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
        Identificator identificator = null;
        Type type = null;


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
        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.Identificator) {
            left = new Identificator(lexer.getCurrent().getStringLexem());
            lexer.moveNext();
            return left;
        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.FUN) {
            lexer.moveNext();
            if (lexer.getCurrent().getTypeLexem() != LexemKind.Identificator) {
                throw new ParserException(lexer.getCurrent().getPosition());
            }
            String idName = lexer.getCurrent().getStringLexem();
            lexer.moveNext();
            if (lexer.getCurrent().getTypeLexem() == LexemKind.Colon) {
                lexer.moveNext();
                identificator = new Identificator(idName, ParseType());
            } else {
                throw new ParserException(lexer.getCurrent().getPosition());
            }
            //lexer.moveNext();
            //if (lexer.getCurrent().getTypeLexem() == LexemKind.Colon) {
            //    lexer.moveNext();
            //    type = ParseType();
            //}
            if (lexer.getCurrent().getTypeLexem() != LexemKind.ARROW) {
                throw new ParserException(lexer.getCurrent().getPosition());
            }
            lexer.moveNext();
            expr = getExpression();
            return new Function(identificator, expr);

        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.LET) {
            lexer.moveNext();
            if (lexer.getCurrent().getTypeLexem() != LexemKind.Identificator) {
                throw new ParserException(lexer.getCurrent().getPosition());
            }
            String idName = lexer.getCurrent().getStringLexem();
            lexer.moveNext();
            if (lexer.getCurrent().getTypeLexem() == LexemKind.Colon) {
                lexer.moveNext();
                identificator = new Identificator(idName, ParseType());
            } else {
                throw new ParserException(lexer.getCurrent().getPosition());
            }

            //lexer.moveNext();
            //if (lexer.getCurrent().getTypeLexem() == LexemKind.Colon) {
            //    lexer.moveNext();
            //    type = ParseType();
            //}
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
            //type = ParseType();
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

//let fun x:Int->>Unit -> print x in x 2
