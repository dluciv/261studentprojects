/**
 *
 * @author Карымов Антон 261 группа
 */
package gui;

import javax.swing.text.StyledDocument;

public interface IMainForm {

    public void setTextInOutputPane(String text);

    public void setTextInErrorPane(String text, int column, int line);

    public void setStatus(String status);

    public void setTextInTextPane(String text);

    public String getTextInTextPane();

    public StyledDocument getStyledDocumentInTextPane();

    public void setCharacterAttributesInTextPane();

    public void setFocusInTextPane();

    public void setCaretPositionInTextPane(int position);

    public void clearOutputPane();

    public void clearErrorPane();
}
