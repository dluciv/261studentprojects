package gui;

import javax.swing.JTextPane;

public interface IMainForm {

    public void setTextInOutputPane(String text);

    public void setTextInErrorPane(String text, int column, int line);

    public Controller getController();

    public JTextPane getTextPane();

    public void clearOutputPane();

    public void clearErrorPane();
}
