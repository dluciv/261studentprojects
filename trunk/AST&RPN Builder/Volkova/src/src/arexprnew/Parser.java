/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexprnew;

/**
 *
 * @author kate
 */
public class Parser {

    private String s;
    private Lexer lexer;

    public Parser(String s) {
        this.s = s;
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

        while (lexer.getCurrentLexem().isOperation() &&
                (Lexem.Operation.Plus.equals(lexer.getCurrentLexem().getOperation())) || 
                (Lexem.Operation.Minus.equals(lexer.getCurrentLexem().getOperation()))) {
            Lexem lexem = lexer.getCurrentLexem();
            lexer.next();

            Tree next = parseTerm();

            result = new Tree(lexem, result, next);
        }

        return result;
    }

    private Tree parseTerm() throws ParseException {
        Tree result = parseFactor();

        while (lexer.getCurrentLexem().isOperation() && (Lexem.Operation.Multiply.equals(lexer.getCurrentLexem().getOperation()) ||
                Lexem.Operation.Divide.equals(lexer.getCurrentLexem().getOperation()))) {
            Lexem lexem = lexer.getCurrentLexem();
            lexer.next();

            Tree next = parseFactor();

            result = new Tree(lexem, result, next);
        }

        return result;
    }

    private Tree parseFactor() throws ParseException {
        Lexem current = lexer.getCurrentLexem();

        if (current.isOperation() && Lexem.Operation.Minus.equals(current.getOperation())) {
            lexer.next();
            current = lexer.getCurrentLexem();
            if (!current.isOperation()) {
                lexer.next();
                return new Tree(new Lexem(Lexem.Operation.Minus), new Tree(new Lexem(0), null, null), new Tree(current, null, null));
            }else {
                if(!Lexem.Operation.LeftBracket.equals(current.getOperation())){
                    throw new ParseException("No left bracket on " + (lexer.getDeletedCharsCount() + 1) + " position!");
                }

                lexer.next();
                Tree result = parseExpr();

                if (!Lexem.Operation.RightBracket.equals(lexer.getCurrentLexem().getOperation())) {
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

                if (!Lexem.Operation.RightBracket.equals(lexer.getCurrentLexem().getOperation())) {
                    throw new ParseException("No right bracket on " + (lexer.getDeletedCharsCount() + 1) + " position!");
                }
                lexer.next();

                return result;
            }
        }



    }
}
