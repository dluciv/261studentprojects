package name.stepa.ml.model.interpreter.lexer;

import java.util.ArrayList;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class Lexer {

    private static final int SYMBOL_DIGIT = 0;
    private static final int SYMBOL_OPEN_BRACKET = 1;
    private static final int SYMBOL_CLOSE_BRACKET = 2;
    private static final int SYMBOL_LETTER = 3;
    private static final int SYMBOL_BINARY_OPERATION = 4;
    private static final int SYMBOL_SPACE = 5;
    private static final int SYMBOL_EQUALITY = 6;
    private static final int SYMBOL_EXCLAMATION = 7;
    private static final int SYMBOL_COMPARISON = 8;
    private static final int SYMBOL_SEMICOLON = 9;

    private static final int SYMBOL_UNKNOWN = -1;

    public Lexeme[] parse(String text) throws Exception {
        ArrayList<Lexeme> res = new ArrayList<Lexeme>();
        res.add(new BeginLexeme());

        char[] data = text.toCharArray();
        int pos = 0;
        int nexSymbol;
        while (pos < data.length) {
            int symType = getSymbolType(data[pos]);
            int posStart = pos;
            switch (symType) {
                case SYMBOL_DIGIT:
                    String num = Character.toString(data[pos++]);
                    while ((pos < data.length) && (getSymbolType(data[pos]) == SYMBOL_DIGIT))
                        num += Character.toString(data[pos++]);
                    res.add(new ValueLexeme(Double.parseDouble(num), posStart, posStart + data.length));
                    break;
                case SYMBOL_LETTER:
                    String var = Character.toString(data[pos++]);
                    while ((pos < data.length) && ((getSymbolType(data[pos]) == SYMBOL_LETTER) || (getSymbolType(data[pos]) == SYMBOL_DIGIT)))
                        var += Character.toString(data[pos++]);

                    if (var.equals("let"))
                        res.add(new LetLexeme(posStart));
                    else if (var.equals("in"))
                        res.add(new InLexeme(posStart));
                    else if (var.equals("begin"))
                        res.add(new BeginLexeme(posStart));
                    else if (var.equals("end"))
                        res.add(new EndLexeme(posStart));
                    else if (var.equals("if"))
                        res.add(new IfLexeme(posStart));
                    else if (var.equals("then"))
                        res.add(new ThenLexeme(posStart));
                    else if (var.equals("else"))
                        res.add(new ElseLexeme(posStart));
                    else
                        res.add(new IdentifierLexeme(var, posStart));
                    break;
                case SYMBOL_SEMICOLON:
                    res.add(new SemicolonLexeme(pos));
                    pos++;
                    break;
                case SYMBOL_SPACE:
                    while ((pos < data.length) && (getSymbolType(data[pos]) == SYMBOL_SPACE)) {
                        pos++;
                    }
                    break;
                case SYMBOL_EQUALITY:
                    nexSymbol = getSymbolType(data[pos + 1]);
                    if (nexSymbol != SYMBOL_EQUALITY) {
                        pos++;
                        res.add(new AssignLexeme(posStart));
                        break;
                    } else {
                        // "=="
                        pos += 2;
                        res.add(new ComparisonLexeme(ComparisonLexeme.E, posStart, posStart + 2));
                        break;
                    }
                case SYMBOL_COMPARISON:
                    nexSymbol = getSymbolType(data[pos + 1]);
                    if (nexSymbol != SYMBOL_EQUALITY) {
                        if (data[pos] == '>')
                            res.add(new ComparisonLexeme(ComparisonLexeme.G, posStart, posStart + 1));
                        else
                            res.add(new ComparisonLexeme(ComparisonLexeme.L, posStart, posStart + 1));
                        pos++;
                    } else {
                        if (data[pos] == '>')
                            res.add(new ComparisonLexeme(ComparisonLexeme.GE, posStart, posStart + 2));
                        else
                            res.add(new ComparisonLexeme(ComparisonLexeme.LE, posStart, posStart + 2));
                        pos += 2;
                    }
                    break;
                case SYMBOL_EXCLAMATION:
                    nexSymbol = getSymbolType(data[pos + 1]);
                    if (nexSymbol != SYMBOL_EQUALITY) {
                        res.add(new NotLexeme(posStart));
                        pos++;
                    } else {
                        res.add(new ComparisonLexeme(ComparisonLexeme.NE, posStart, posStart + 2));
                        pos += 2;
                    }
                    break;
                case SYMBOL_OPEN_BRACKET:
                    res.add((new OpenBracketLexeme(posStart)));
                    pos++;
                    break;
                case SYMBOL_CLOSE_BRACKET:
                    res.add((new CloseBracketLexeme(posStart)));
                    pos++;
                    break;
                case SYMBOL_BINARY_OPERATION:
                    res.add(new OperationLexeme(data[pos++], posStart));
                    break;
                case SYMBOL_UNKNOWN:
                    throw new Exception("Unexpected symbol");
            }
        }
        res.add(new EndLexeme());
        res.add(new EOFLexeme());
        return res.toArray(new Lexeme[0]);
    }

    private int getSymbolType(char c) {

        if (Character.isDigit(c))
            return SYMBOL_DIGIT;
        if (Character.isLetter(c))
            return SYMBOL_LETTER;
        if (Character.isWhitespace(c))
            return SYMBOL_SPACE;
        if (c == '+')
            return SYMBOL_BINARY_OPERATION;
        if (c == '-')
            return SYMBOL_BINARY_OPERATION;
        if (c == '/')
            return SYMBOL_BINARY_OPERATION;
        if (c == '*')
            return SYMBOL_BINARY_OPERATION;
        if (c == '&')
            return SYMBOL_BINARY_OPERATION;

        if (c == '(')
            return SYMBOL_OPEN_BRACKET;
        if (c == ')')
            return SYMBOL_CLOSE_BRACKET;

        if (c == '>')
            return SYMBOL_COMPARISON;
        if (c == '<')
            return SYMBOL_COMPARISON;

        if (c == '=')
            return SYMBOL_EQUALITY;
        if (c == '!')
            return SYMBOL_EXCLAMATION;

        if (c == ';')
            return SYMBOL_SEMICOLON;

        return SYMBOL_UNKNOWN;
    }
}
