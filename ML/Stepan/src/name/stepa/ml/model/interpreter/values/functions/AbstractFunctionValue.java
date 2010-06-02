package name.stepa.ml.model.interpreter.values.functions;

import name.stepa.ml.model.interpreter.exceptions.InvalidOperationException;
import name.stepa.ml.model.interpreter.exceptions.TypeMismatchException;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public abstract class AbstractFunctionValue {
    public abstract Object execute(Object arg) throws TypeMismatchException, InvalidOperationException;

    protected Double getNumericArgument(Object arg) throws TypeMismatchException {
        if (arg instanceof Double)
            return (Double) arg;
        else
            throw new TypeMismatchException("Double", arg);
    }
}
