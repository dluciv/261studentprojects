package karymov;

//import java.awt.TextPane;
import java.util.LinkedList;
import javax.swing.JTextPane;
import tools.ErrorLogCell;

public interface IMainForm {

    public void setTextInTextPanel(String text);

    public void setTextInOutputPane(String text);

    public void setTextInErrorPane(String text, int a, int b);

    public void clearTextPanel();

    public Controller getController();

    public void selectLineForDebug(int line, int column);

    public JTextPane getTextPanel();

    public void clearOutputPane();

    public void clearErrorPane();

    public void errorRevise(LinkedList<ErrorLogCell> errorList);
}
