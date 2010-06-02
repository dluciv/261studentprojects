package name.stepa.ml.model.interpreter.lexer;

import name.stepa.ml.model.interpreter.lexer.Lexeme;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class LexemeStream {
    Lexeme[] lexemes;
    int pos = 0;

    public LexemeStream(Lexeme[] lexemes) {
        this.lexemes = lexemes;
    }

    public boolean haveNext() {
        return pos < lexemes.length - 1;
    }

    public Lexeme currentAndNext() {
        if (pos < lexemes.length - 1)
            return lexemes[pos++];
        else
            return null;
    }

    public Lexeme next() {
        if (pos < lexemes.length - 1)
            return lexemes[++pos];
        else
            return null;
    }

    public Lexeme current() {
        if (pos < lexemes.length)
            return lexemes[pos];
        else
            return null;
    }

    public Lexeme peek() {
        if (pos < lexemes.length - 1)
            return lexemes[pos + 1];
        else
            return null;
    }

    public void reset() {
        pos = 0;
    }

    @Override
    public String toString() {
        return "LexemeStream: current: " + lexemes[pos];
    }
}
