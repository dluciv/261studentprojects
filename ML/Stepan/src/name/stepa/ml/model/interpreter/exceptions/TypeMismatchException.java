package name.stepa.ml.model.interpreter.exceptions;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class TypeMismatchException extends Exception {
    public TypeMismatchException(String expregted, Object got) {
        super("Type mismatch! Expected: " + expregted + ", got: " + ((got == null) ? "null" : got.getClass().getSimpleName()));
    }
}
