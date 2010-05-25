package karymov;

//import java.awt.TextPane;
import javax.swing.JTextPane;

public interface IMainForm {

    public void setTextInTextPanel(String text);

    public void setTextInOutputPane(String text);

    public void setTextInErrorPane(String text);

    public void clearTextPanel();

    public JTextPane getTextPanel();

    public void clearOutputPane();

    public void clearErrorPane();
}
