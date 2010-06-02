package name.stepa.ml.model.interpreter.exceptions;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class UnexectedSymbolException extends Exception {
    char symbol;

    public UnexectedSymbolException(char symbol) {
        super("Unexpected symbol: " + symbol);
        this.symbol = symbol;
    }
}
