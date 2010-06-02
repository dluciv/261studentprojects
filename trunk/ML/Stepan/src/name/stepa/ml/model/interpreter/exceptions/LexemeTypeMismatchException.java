package name.stepa.ml.model.interpreter.exceptions;

import name.stepa.ml.model.interpreter.lexer.Lexeme;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class LexemeTypeMismatchException extends Exception {
    public LexemeTypeMismatchException(String expected, Lexeme got) {
        super("Wrong lexeme! Expected: " + expected + ", got: " + got.toString());
    }
}
