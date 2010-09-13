/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexprnew;

/**
 *
 * @author kate
 */
public interface IMainView {

    public void printError(String error, Position position);

    public String getProgramText();

    public void setProgramText(String program);

    public void printResult(String result);

    public void resetConsole();

    public void colorKeyword(int startInd, int endIndex);

    public void highlightText(int startInd, int endIndex);

    public void setProgressBarText(String progress);

    public void resetProgressBar();
}
