package name.stepa.ml.model.interpreter.functions;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 16:07:08
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractFunctionValue {
    public abstract Object execute(Object arg) throws Exception;
}
