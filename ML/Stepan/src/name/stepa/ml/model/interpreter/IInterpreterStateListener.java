package name.stepa.ml.model.interpreter;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 08.05.2010
 * Time: 20:25:20
 * To change this template use File | Settings | File Templates.
 */
public interface IInterpreterStateListener {
    public void onLineChanged(int start, int end);

    public void onExecutionStopped();
}
