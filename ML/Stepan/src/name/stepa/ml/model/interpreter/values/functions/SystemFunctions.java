package name.stepa.ml.model.interpreter.values.functions;

import name.stepa.ml.model.interpreter.IO;
import name.stepa.ml.model.interpreter.exceptions.InvalidOperationException;
import name.stepa.ml.model.interpreter.exceptions.TypeMismatchException;
import name.stepa.ml.model.interpreter.values.Unit;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class SystemFunctions {
    public static class PrintlnFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws TypeMismatchException, InvalidOperationException {
            IO.println(arg.toString());
            return Unit.VALUE;
        }
    }
}
