/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tools;

import gui.Controller;
import java.util.LinkedList;

public class LogChangedListener {
    Controller controller;
    
    public LogChangedListener(Controller controller) {
        this.controller = controller;
    }

    void logChanged(LinkedList<ErrorLogCell> errorLog) {
        controller.cleanErrorPane();
        controller.errorRevise(errorLog);
    }
}
