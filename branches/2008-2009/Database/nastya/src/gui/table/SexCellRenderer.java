package gui.table;

import dbentities.Sex;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
import java.awt.*;

import utils.Util;

/**
 * Отрисовщик, позволяющий на экране видеть предоставление пола, определяемое в классе
 * <code>Util</code> вместо прямых имен enum'а.
 *
 * @author nastya
 *         Date: 21.08.2009
 *         Time: 2:47:45
 * @see Util#representSex(dbentities.Sex) 
 */
public class SexCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);    //To change body of overridden methods use File | Settings | File Templates.
        if (c instanceof JLabel && value instanceof Sex) {
            JLabel label = (JLabel) c;
            Sex sex = (Sex) value;
            label.setText(Util.representSex(sex));

        }
        return c;
    }
}
