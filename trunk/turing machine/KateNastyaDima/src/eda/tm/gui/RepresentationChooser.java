/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eda.tm.gui;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author nastya
 */
public class RepresentationChooser {

    private static ChooserPanel panel = new ChooserPanel();

    public static Representations show(Component parent) {
        JOptionPane.showMessageDialog(parent, panel, "Attention!", JOptionPane.QUESTION_MESSAGE);
        if(panel.rbShowTableGenerateTex.isSelected()){
            return Representations.TABLE_AND_TEX;
        }
        
        if(panel.rbGenerateTex.isSelected()){
            return Representations.TEX;
        }

        if(panel.rbShowTable.isSelected()){
            return Representations.TABLE;
        }
        
        return Representations.TABLE;
    }

    public enum Representations {

        TABLE, TEX, TABLE_AND_TEX
    }
}
