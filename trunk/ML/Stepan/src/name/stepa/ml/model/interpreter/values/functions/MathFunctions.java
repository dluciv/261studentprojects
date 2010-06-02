package name.stepa.ml.model.interpreter.values.functions;

import name.stepa.ml.model.interpreter.exceptions.InvalidOperationException;
import name.stepa.ml.model.interpreter.exceptions.TypeMismatchException;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class MathFunctions {

    public static class SinFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws TypeMismatchException, InvalidOperationException {
            return Math.sin(getNumericArgument(arg));
        }
    }

    public static class CosFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws TypeMismatchException, InvalidOperationException {
            return Math.cos(getNumericArgument(arg));
        }
    }

    public static class TanFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws TypeMismatchException, InvalidOperationException {
            return Math.tan(getNumericArgument(arg));
        }
    }

    public static class AbsFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws TypeMismatchException, InvalidOperationException {
            return Math.abs(getNumericArgument(arg));
        }
    }

    public static class FloorFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws TypeMismatchException, InvalidOperationException {
            return Math.floor(getNumericArgument(arg));
        }
    }

    public static class CeilFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws TypeMismatchException, InvalidOperationException {
            return Math.ceil(getNumericArgument(arg));
        }
    }

    public static class RoundFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws TypeMismatchException, InvalidOperationException {
            return Math.round(getNumericArgument(arg));
        }
    }

    public static class AcosFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws TypeMismatchException, InvalidOperationException {
            return Math.acos(getNumericArgument(arg));
        }
    }

    public static class AsinFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws TypeMismatchException, InvalidOperationException {
            return Math.asin(getNumericArgument(arg));
        }
    }

    public static class AtanFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws TypeMismatchException, InvalidOperationException {
            return Math.atan(getNumericArgument(arg));
        }
    }

    public static class ExpFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws TypeMismatchException, InvalidOperationException {
            return Math.exp(getNumericArgument(arg));
        }
    }

    public static class LogFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws TypeMismatchException, InvalidOperationException {
            return Math.log(getNumericArgument(arg));
        }
    }

    public static class Log10FunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws TypeMismatchException, InvalidOperationException {
            return Math.log10(getNumericArgument(arg));
        }
    }

    public static class SqrtFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws TypeMismatchException, InvalidOperationException {
            return Math.sqrt(getNumericArgument(arg));
        }
    }
}