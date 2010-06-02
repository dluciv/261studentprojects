package name.stepa.ml.model.interpreter;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public interface IInterpreterStateListener {
    public void onLineChanged(int start, int end);

    public void onExecutionStarted();
    public void onExecutionStopped();
}
