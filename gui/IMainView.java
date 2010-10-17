/*
 * Интерфейс открывающий доступ констроллеру к функциям главного окна
 * Antonov Kirill(c), 2010
 */
package gui;

import lexer.Position;
import javax.swing.text.StyledDocument;

public interface IMainView {

    public void printError(String error, Position position);

    public String getProgramText();

    public void setProgramText(String program);

    public void printResult(String result);

    public void resetConsole();

    public StyledDocument getStyledDocumentInTextPane();

    public void setCharacterAttributesInTextPane();

    public void setCaretPositionInTextPane(int position);

    public void highlightText(int start_ind, int end_index);

    public void setProgressBarText(String progress);

    public void resetProgressBar();

    public void setFocusInTextPane();
}
