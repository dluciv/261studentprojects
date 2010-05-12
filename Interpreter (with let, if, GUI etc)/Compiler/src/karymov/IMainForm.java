package karymov;

public interface IMainForm {

    public void setTextInTextPanel(String text);

    public String getTextInTextPanel();

    public void setTextInConsolePanel(String text);

    public void printInConsole(String text);

    public void clearTextPanel();

    public void clearConsolePanel();
}
