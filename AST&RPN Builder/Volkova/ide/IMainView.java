package ide;

public interface IMainView {

    void printError(String error, Position position);

    String getProgramText();

    void setProgramText(String program);

    void printResult(String result);

    void resetConsole();

    void colorKeyword(int startInd, int endIndex);

    void highlightText(int startInd, int endIndex);

    void setProgressBarText(String progress);

    void resetProgressBar();
}
