/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexprnew;

//import java.util.ArrayList;
//import com.sun.imageio.plugins.common.I18N;
//import java.util.ArrayList;
/**
 *
 * @author kate
 *
 *
 */
public class Parser {

    private Lexer lexer;
    public int count = 0;
    public int rec = 0;

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
                return new Application(left, unarOp());
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
        Id idRec = null;

        if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Number) {
            left = new Number(lexer.getCurrLexeme().getIntLexem());
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
            left = new Id(lexer.getCurrLexeme().getStringLexem());
            lexer.next();
            return left;
        } else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Fun) {
            lexer.next();
            if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.Id) {
                throw new ParserException(lexer.getCurrLexeme().getPosition());
            }
            id = new Id(lexer.getCurrLexeme().getStringLexem());
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
                idRec = new Id(lexer.getCurrLexeme().getStringLexem());
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
                rec = 1;                                                       //  !!!!!!!!!!!!!!!!
                return new Binding(idRec, expr1, expr2);
            } else {

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
                return new Binding(idRec, expr1, expr2);
            }
        } else  if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Print) {
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
/*public Sq parseSq() throws ParserException {
ArrayList<Expression> sq = new ArrayList<Expression>();
do {
sq.add(parseExpression());
} while (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Semi);
return new Sq(sq);
}

private Expression parseExpression() throws ParserException {
Expression res = null;
res = parseOr();
while (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Or) {
Expression expr = parseOr();
res = new Or(res, expr);
}
return res;
}

private Expression parseOr() throws ParserException {
Expression res = null;
res = parseAnd();
while (lexer.getCurrLexeme().getTypeLexem() == Lexeme.And) {
Expression expr = parseAnd();
res = new And(res, expr);
}
return res;
}

private Expression parseAnd() throws ParserException {
Expression res = null;
res = parseCom();
while (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Equal ||
lexer.getCurrLexeme().getTypeLexem() == Lexeme.UnEq) {

Expression expr = parseCom();
if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Equal) {
res = new Equal(res, expr);

} else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.UnEq) {
res = new UnEq(res, expr);
} else {
throw new ParserException(lexer.getCurrLexeme().getPosition());
}
}
return res;
}

private Expression parseCom() throws ParserException {
Expression res = null;
res = parseCompare();
if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Greater || lexer.getCurrLexeme().getTypeLexem() == Lexeme.GreaterEq ||
lexer.getCurrLexeme().getTypeLexem() == Lexeme.Less || lexer.getCurrLexeme().getTypeLexem() == Lexeme.LessEq) {
Expression expr = parseCompare();
if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Greater) {
res = new Greater(res, expr);
} else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.GreaterEq) {
res = new GreaterOrEq(res, expr);
} else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Less) {
res = new Less(res, expr);
} else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.LessEq) {
res = new LessOrEq(res, expr);
} else {
throw new ParserException(lexer.getCurrLexeme().getPosition());

}
}
return res;
}

private Expression parseCompare() throws ParserException {
Expression res = null;
res = parseTerm();
while (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Plus ||
lexer.getCurrLexeme().getTypeLexem() == Lexeme.Minus) {
Expression expr = parseTerm();
if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Plus) {
res = new Plus(res, expr);
} else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Minus) {
res = new Minus(res, expr);
} else {
throw new ParserException(lexer.getCurrLexeme().getPosition());
}
}

return res;
}

private Expression parseTerm() throws ParserException {
Expression res = null;
res = parseFactor();
while (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Mult || lexer.getCurrLexeme().getTypeLexem() == Lexeme.Div) {
Expression expr = parseFactor();
if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Mult) {
res = new Mult(res, expr);
} else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Div) {
res = new Div(res, expr);
} else {
throw new ParserException(lexer.getCurrLexeme().getPosition());
}
}
return res;
}

private Expression parseFactor() throws ParserException {
lexer.next();
if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Not) {

return new Not(parseFactor());
} else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Minus) {
lexer.next();
return new Not(parsePrime());
} else {
return parsePrime();
}
}

private Expression parsePrime() throws ParserException {
Expression res = null;


if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Number) {
res = new Number(lexer.getCurrLexeme().getIntLexem());
lexer.next();
return res;
} else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.False) {
res = new BoolOp(false);
lexer.next();
return res;
} else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.True) {
res = new BoolOp(true);
lexer.next();
return res;
} else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.LeftBracket) {
lexer.next();
res = parseExpression();
if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.RightBracket) {
throw new RightBrException(lexer.getCurrLexeme().getPosition());
}
lexer.next();
return res;
} else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Id) {
res = new Id(lexer.getCurrLexeme().getStringLexem());
lexer.next();
return res;



} else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Let) {
lexer.next();
if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Id) {
Id id = new Id(lexer.getCurrLexeme().getStringLexem());
lexer.next();
if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.Equal) {
throw new ParserException(lexer.getCurrLexeme().getPosition());
}
lexer.next();
Expression expr1 = parseExpression();
if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.In) {
throw new ParserException(lexer.getCurrLexeme().getPosition());
}

lexer.next();
Expression expr2 = parseExpression();
return new Binding(id, expr1, expr2);
} else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Rec) {
lexer.next();
if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Id) {
Id id = new Id(lexer.getCurrLexeme().getStringLexem());
lexer.next();
if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.Equal) {
throw new ParserException(lexer.getCurrLexeme().getPosition());
}
lexer.next();
Expression expr1 = parseExpression();
if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.In) {
throw new ParserException(lexer.getCurrLexeme().getPosition());
}

lexer.next();
Expression expr2 = parseExpression();
return new Binding(id, expr1, expr2);
}
}
} else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Print) {
lexer.next();
return new Print(parseExpression());
} else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.If) {
lexer.next();
Expression expr1 = parseExpression();

if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.Then) {
throw new ParserException(lexer.getCurrLexeme().getPosition());
} else {
lexer.next();
Expression expr2 = parseExpression();

if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.Else) {
throw new ParserException(lexer.getCurrLexeme().getPosition());
} else {
lexer.next();
Expression expr3 = parseExpression();
res = new IfThenElse(expr1, expr2, expr3);
}
}

} else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Begin) {
Expression expr = parseSq();
if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.End) {
throw new ParserException(lexer.getCurrLexeme().getPosition());
} else {
lexer.next();
res = expr;
}
} else if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.Fun) {
lexer.next();
if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.Id) {
throw new ParserException(lexer.getCurrLexeme().getPosition());
}
Id id = new Id(lexer.getCurrLexeme().getStringLexem());


lexer.next();
if (lexer.getCurrLexeme().getTypeLexem() != Lexeme.OpFun) {
throw new ParserException(lexer.getCurrLexeme().getPosition());
}
lexer.next();
Expression expr = parseExpression();
return new Function(id, expr);
} else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.EOL) {
res = null;
} else if (lexer.getCurrLexeme().getTypeLexem() == Lexeme.Semi) {
res = null;
} else if (res != null) {
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

return new Application(res, parseExpression());
default:
break;
}
}



return res;
}
}
/*
public class Parser {

private String s;
private Lexer lexer;
private Stack <Integer> stack;

public Parser(String s) {
this.s = s;
}

public int result() throws ParseException {
String [] polish = parse().toString().trim().split(" ");

stack = new Stack <Integer> ();

for (int i = 0; i < polish.length; i++) {
switch(polish[i].charAt(0)) {
case '+': stack.push(stack.pop() + stack.pop()); break;
case '-': stack.push(-stack.pop() + stack.pop()); break;
case '*': stack.push(stack.pop() * stack.pop()); break;
case '/': int a = stack.pop(); stack.push(stack.pop() / a); break;
case '&': stack.push(stack.pop() & stack.pop()); break;
case '|': stack.push(stack.pop() | stack.pop()); break;
case '~' : stack.push(~stack.pop()); break;
default: stack.push(Integer.valueOf(polish[i])); break;
}
}

return stack.pop();
}

public Tree parse() throws ParseException {
lexer = new Lexer(s);

Tree tree = parseExpr();

if (lexer.hasNext()) {
throw new ParseException("Illegal characters on " + (lexer.getDeletedCharsCount() + 1) + " position!");
}

return tree;
}

private Tree parseExpr() throws ParseException {
Tree result = parseTerm();

while (lexer.getCurrLexeme()Lexem().isOperation() &&
(Lexem.Operation.Plus.equals(lexer.getCurrLexeme()Lexem().getOperation())) ||
(Lexem.Operation.Minus.equals(lexer.getCurrLexeme()Lexem().getOperation())) ||
Lexem.Operation.And.equals(lexer.getCurrLexeme()Lexem().getOperation())) {
Lexem lexem = lexer.getCurrLexeme()Lexem();
lexer.next();

Tree next = parseTerm();

result = new Tree(lexem, result, next);
}

return result;
}

private Tree parseTerm() throws ParseException {
Tree result = parseFactor();

while (lexer.getCurrLexeme()Lexem().isOperation() && (Lexem.Operation.Mult.equals(lexer.getCurrLexeme()Lexem().getOperation()) ||
Lexem.Operation.Div.equals(lexer.getCurrLexeme()Lexem().getOperation()))) {
Lexem lexem = lexer.getCurrLexeme()Lexem();
lexer.next();

Tree next = parseFactor();

result = new Tree(lexem, result, next);
}

return result;
}

private Tree parseFactor() throws ParseException {
Lexem current = lexer.getCurrLexeme()Lexem();

if (current.isOperation() && Lexem.Operation.Minus.equals(current.getOperation())) {
lexer.next();
current = lexer.getCurrLexeme()Lexem();
if (!current.isOperation()) {
lexer.next();
return new Tree(new Lexem(Lexem.Operation.Minus), new Tree(new Lexem(0), null, null), new Tree(current, null, null));
}else {
if(!Lexem.Operation.LeftBracket.equals(current.getOperation())){
throw new ParseException("No left bracket on " + (lexer.getDeletedCharsCount() + 1) + " position!");
}

lexer.next();
Tree result = parseExpr();

if (!Lexem.Operation.RightBracket.equals(lexer.getCurrLexeme()Lexem().getOperation())) {
throw new ParseException("No right bracket on " + (lexer.getDeletedCharsCount() + 1) + " position!");
}
lexer.next();

return new Tree(new Lexem(Lexem.Operation.Minus), new Tree(new Lexem(0), null, null), result);
}


} else {
if (!current.isOperation()) {
lexer.next();
return new Tree(current, null, null);


} else {
if (!Lexem.Operation.LeftBracket.equals(current.getOperation())) {
throw new ParseException("No left bracket on " + (lexer.getDeletedCharsCount() + 1) + " position!");
}

lexer.next();
Tree result = parseExpr();

if (!Lexem.Operation.RightBracket.equals(lexer.getCurrLexeme()Lexem().getOperation())) {
throw new ParseException("No right bracket on " + (lexer.getDeletedCharsCount() + 1) + " position!");
}
lexer.next();

return result;
}
}



}
}
 */
 
