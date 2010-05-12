package name.stepa.ml.model.interpreter.functions;

import name.stepa.ml.model.interpreter.IO;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 16:44:48
 * To change this template use File | Settings | File Templates.
 */
public class PrintlnFunctionValue extends AbstractFunctionValue {
    @Override
    public Object execute(Object arg) throws Exception {
        IO.println(arg.toString());
        return "void";
    }
}
