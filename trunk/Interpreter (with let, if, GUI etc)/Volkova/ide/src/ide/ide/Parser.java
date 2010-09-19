/*
 *
 *
 * (c) Volkova Ekatetina
 */
package ide.ide;

import ide.operators.*;
import ide.exceptions.*;
import ide.operations.*;
import ide.operators.Number;

public class Parser {

    private Lexer lexer;
    public int count = 0;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.lexer.next();
    }

    public Sq getSq() throws ParserException {
        Sq sq = new Sq();
        while (lexer.getCurrLexeme().getTypeLexem() != Lexeme.EOL && lexer.getCurrLexeme().getTypeLexem() != Lexeme.End) {
            if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Semi) {
                lexer.next();
            } else {
                sq.addOp(getExpression());
            }
        }
        return sq;
    }

    private Expression getBoolExpression() throws ParserException {

        return boolExpression();

    }

    private Expression boolExpression() throws ParserException {
        Expression left = null;

        left = or();
        while (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Or) {
            lexer.next();
            Expression right = or();
            left = new Or(left, right);
        }

        return left;
    }

    private Expression or() throws ParserException {
        Expression left = null;

        left = and();
        while (lexer.getCurrLexeme().getTypeLexem() == Lexeme.And) {
            lexer.next();
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

        left = arExpression();
        switch (lexer.getCurrLexeme().getTypeLexem()) {
            case LessEq:
                lexer.next();
                right = comparison();
                return new LessOrEq(left, right);
            case GreaterEq:
                lexer.next();
                right = comparison();
                return new GreaterOrEq(left, right);
            case Greater:
                lexer.next();
                right = comparison();
                return new Greater(left, right);
            case Less:
                lexer.next();
                right = comparison();
                return new Less(left, right);
            case UnEq:
                lexer.next();
                right = comparison();
                return new UnEq(left, right);
            case Equal:
                lexer.next();
                right = comparison();
                return new Equal(left, right);
            case True:
                lexer.next();
                return new BoolOp(true);
            case False:
                lexer.next();
                return new BoolOp(false);
            default:
                return left;
        }
    }

    private Expression getExpression() throws ParserException {
        Expression left = null;

        left = boolExpression();
        if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.UnKnown) {
            throw new UnknownSymbolException(lexer.getCurrLexeme().getPosition());
        }

        return left;
    }

    private Expression arExpression() throws ParserException {
        Expression left = null;

        left = term();
        while (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Plus || lexer.getCurrLexeme().getTypeLexem() == Lexeme.Minus) {
            Lexeme sign = lexer.getCurrLexeme().getTypeLexem();
            lexer.next();
            Expression right = term();
            if (sign == Lexeme.Plus) {
                left = new Plus(left, right);
            } else {
                left = new Minus(left, right);
            }
        }

        return left;
    }

    private Expression term() throws ParserException {
        Expression left = null;

        left = app();
        while (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Mult || lexer.getCurrLexeme().getTypeLexem() == Lexeme.Div) {
            Lexeme sign = lexer.getCurrLexeme().getTypeLexem();
            lexer.next();
            Expression right = unarOp();
            if (sign == Lexeme.Mult) {
                left = new Mult(left, right);
            } else {
                left = new Div(left, right);
            }
        }

        return left;
    }

    private Expression app() throws ParserException {
        Expression left = unarOp();

        switch (lexer.getCurrLexeme().getTypeLexem()) {
            case Number:
            case True:
            case False:
            case LeftBracket:
            case Id:
            case Fun:
            case Let:
            case Rec:
            case Print:
            case If:
            case Begin:
                return new Application(left, getExpression());
            default:
                break;
        }

        return left;
    }

    private Expression unarOp() throws ParserException {
        if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Not) {
            lexer.next();
            return new Not(prime());
        } else {
            return prime();
        }
    }

    private Expression prime() throws ParserException {
        Expression left = null;
        Expression expr1 = null;
        Expression expr2 = null;
        Id id = null;

        if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Number) {
            left = new Number(lexer.getCurrLexeme().getIntLexem());
            lexer.next();
            return left;
        }else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Minus) {
            lexer.next();
            expr1 = arExpression();
            left = new Minus(new Number(0), expr1);
            lexer.next();
            return left;
        } else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.True) {
            left = new BoolOp(true);
            lexer.next();
            return left;
        } else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.False) {
            left = new BoolOp(false);
            lexer.next();
            return left;
        } else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.LeftBracket) {
            lexer.next();
            left = arExpression();
            if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.RightBracket) {
                throw new RightBrException(lexer.getCurrLexeme().getPosition());
            }
            lexer.next();
            return left;
        } else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Id) {
                left = new Id(lexer.getCurrLexeme().getStringIdLexem());
                lexer.next();
                return left;
        } else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Fun) {
            lexer.next();
            if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.Id) {
                throw new ParserException(lexer.getCurrLexeme().getPosition());
            }
            id = new Id(lexer.getCurrLexeme().getStringIdLexem());
            lexer.next();
            if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.OpFun) {
                throw new ParserException(lexer.getCurrLexeme().getPosition());
            }
            lexer.next();
            expr1 = getExpression();
            return new Function(id, expr1);
        } else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Let) {
            lexer.next();
            if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Rec) {
                lexer.next();
                id = new Id(lexer.getCurrLexeme().getStringIdLexem());
                //recfun = lexer.getCurrLexeme().getStringIdLexem();
                lexer.next();
                if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.Equal) {
                    throw new ParserException(lexer.getCurrLexeme().getPosition());
                }
                lexer.next();
                expr1 = getExpression();
                if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.In) {
                    throw new ParserException(lexer.getCurrLexeme().getPosition());
                }
                lexer.next();
                expr2 = getExpression();
                return new Binding(id, expr1, expr2);
            } else {
                if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.Id) {
                    throw new ParserException(lexer.getCurrLexeme().getPosition());
                } else {
                    id = new Id(lexer.getCurrLexeme().getStringIdLexem());
                    lexer.next();
                    if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.Equal) {
                        throw new ParserException(lexer.getCurrLexeme().getPosition());
                    }
                    lexer.next();
                    expr1 = getExpression();
                    if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.In) {
                        throw new ParserException(lexer.getCurrLexeme().getPosition());
                    }
                    lexer.next();
                    expr2 = getExpression();
                    return new Binding(id, expr1, expr2);
                }
            }
        } else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Print) {
            lexer.next();
            return new Print(getExpression());
        } else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.If) {
            Expression else_expr = null;
            lexer.next();
            expr1 = getBoolExpression();
            if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.Then) {
                throw new ParserException(lexer.getCurrLexeme().getPosition());
            }
            lexer.next();
            expr2 = getExpression();
            if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Else) {
                lexer.next();
                else_expr = getExpression();
            }
            return new IfThenElse(expr1, expr2, else_expr);
        } else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Begin) {
            Sq sq;
            lexer.next();
            sq = getSq();
            if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.End) {
                throw new ParserException(lexer.getCurrLexeme().getPosition());
            }
            lexer.next();
            return new BeginEnd(sq);
        } else {
            throw new ParserException(lexer.getCurrLexeme().getPosition());
        }
    }
}
