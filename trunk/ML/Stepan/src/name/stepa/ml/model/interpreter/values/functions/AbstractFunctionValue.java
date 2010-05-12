package name.stepa.ml.model.interpreter.values.functions;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 16:07:08
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractFunctionValue {
    public abstract Object execute(Object arg) throws Exception;

    protected Double getNumericArgument(Object arg) throws Exception {
        if (arg instanceof Double)
            return (Double) arg;
        else
            throw new Exception("Type mismatch! Expected Double, got " + arg.getClass().getSimpleName());
    }
}
