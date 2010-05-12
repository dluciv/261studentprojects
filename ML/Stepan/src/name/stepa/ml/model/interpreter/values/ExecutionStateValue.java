package name.stepa.ml.model.interpreter.values;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 18:25:57
 * To change this template use File | Settings | File Templates.
 */
public class ExecutionStateValue {
    public int state;

    public ExecutionStateValue(int state) {
        this.state = state;
    }

    public ExecutionStateValue() {
        this.state = -1;
    }
}
