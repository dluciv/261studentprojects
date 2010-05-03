package spusk1;

public class Parser {

    private Lexer lexer;

    public Parser(Lexer args) {
        lexer = args;
    }

    public Expression getExpression() {
        Expression left = new Expression();
        left = expr();
        return left;
    }//koren

    public Sequence getSeq() {
        Sequence seq = new Sequence();
        seq.addStatement(getExpression());
        return seq;
    }

    public Expression factor() {
        if (lexer.getCurrentSymbol() == Lexems.number) {
            int currentNumber = lexer.getNumber();
            lexer.moveNext();
            return new Number(currentNumber);
        } else {
            if (lexer.getCurrentSymbol() == Lexems.openBracked) {
                lexer.moveNext();
                Expression left = expr();
                if (lexer.getCurrentSymbol() != Lexems.closedBracked) {
                    throw new NumberFormatException("Missing closedBracked");
                }
                lexer.moveNext();
                return left;
            } else {
                throw new NumberFormatException("Illegal symbol");
            }
        }
    }

    public Expression term() {
        Expression left = factor();
        while (lexer.getCurrentSymbol() == Lexems.multiply || lexer.getCurrentSymbol() == Lexems.division) {
            Lexems lexem = lexer.getCurrentSymbol();
            lexer.moveNext();
            Expression right = factor();
            if (lexem == Lexems.multiply) {
                //если было умножение умножаем 
                left = new Multiply(left, right);
            } else {
                left = new Division(left, right);
            }
        }
        return left;
    }

    public Expression expr() {
        Expression left = term();
        while (lexer.getCurrentSymbol() == Lexems.plus || lexer.getCurrentSymbol() == Lexems.minus) {
            Lexems lexem = lexer.getCurrentSymbol();
            lexer.moveNext();
            Expression right = term();
//    проверяем плюс или минус и мутим как в терме
            if (lexem == Lexems.plus) {
                left = new Plus(left, right);
            } else {
                left = new Minus(left, right);
            }
        }
        return left;
    }

//    public Tree Calculate() {
//        Tree result = expr();
//        if (lexer.getCurrentSymbol() != Lexems.EOL) {
//            throw new NumberFormatException("Illegal symbol");
//        }
//        return result;
//    }
}



