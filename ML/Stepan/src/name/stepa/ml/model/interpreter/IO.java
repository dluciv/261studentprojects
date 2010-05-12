package name.stepa.ml.model.interpreter;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 16:25:29
 * To change this template use File | Settings | File Templates.
 */
public class IO {
    private static IOutput output;

    public static void setOutputInterface(IOutput output) {
        IO.output = output;
    }

    public static void println(String s) {
        if (output != null)
            output.println(s);
    }

    /**
     * Created by IntelliJ IDEA.
     * User: Ex3NDR
     * Date: 08.05.2010
     * Time: 19:27:36
     * To change this template use File | Settings | File Templates.
     */
    public static interface IOutput {
        public void println(String s);
    }
}
