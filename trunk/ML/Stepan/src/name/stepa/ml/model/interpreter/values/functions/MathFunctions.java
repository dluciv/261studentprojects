package name.stepa.ml.model.interpreter.values.functions;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 17:15:16
 * To change this template use File | Settings | File Templates.
 */
public class MathFunctions {

    public static class SinFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws Exception {
            return Math.sin(getNumericArgument(arg));
        }
    }

    public static class CosFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws Exception {
            return Math.cos(getNumericArgument(arg));
        }
    }

    public static class TanFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws Exception {
            return Math.tan(getNumericArgument(arg));
        }
    }

    public static class AbsFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws Exception {
            return Math.abs(getNumericArgument(arg));
        }
    }

    public static class FloorFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws Exception {
            return Math.floor(getNumericArgument(arg));
        }
    }

    public static class CeilFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws Exception {
            return Math.ceil(getNumericArgument(arg));
        }
    }

    public static class RoundFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws Exception {
            return Math.round(getNumericArgument(arg));
        }
    }

    public static class AcosFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws Exception {
            return Math.acos(getNumericArgument(arg));
        }
    }

    public static class AsinFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws Exception {
            return Math.asin(getNumericArgument(arg));
        }
    }

    public static class AtanFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws Exception {
            return Math.atan(getNumericArgument(arg));
        }
    }

    public static class ExpFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws Exception {
            return Math.exp(getNumericArgument(arg));
        }
    }

    public static class LogFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws Exception {
            return Math.log(getNumericArgument(arg));
        }
    }

    public static class Log10FunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws Exception {
            return Math.log10(getNumericArgument(arg));
        }
    }

    public static class SqrtFunctionValue extends AbstractFunctionValue {
        @Override
        public Object execute(Object arg) throws Exception {
            return Math.sqrt(getNumericArgument(arg));
        }
    }
}