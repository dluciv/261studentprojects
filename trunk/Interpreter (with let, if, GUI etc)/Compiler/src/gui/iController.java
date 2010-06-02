  /**
 *
 * @author Карымов Антон 261 группа
 */
package gui;

public interface iController {

    public void printInOutputPane(String output);

    public void selectDebugLine(int line, int column);

    public void setInterpreterStateFalse();
}
