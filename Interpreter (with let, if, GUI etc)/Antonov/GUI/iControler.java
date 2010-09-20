/*
 * 
 * Antonov Kirill(c), 2010
 */

package GUI;

public interface iControler {

    public void printInOutputPane(String output);

    public void selectDebugLine(int line, int column);

    public void setInterpreterStateFalse();
}
