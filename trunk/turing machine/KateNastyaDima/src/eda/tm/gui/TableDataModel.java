/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eda.tm.gui;

import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nastya
 */
class TableDataModel extends AbstractTableModel {

    static final ColumnData columns[] = {
        new ColumnData("State", 100, JLabel.CENTER),
        new ColumnData("", 50, JLabel.CENTER),
        new ColumnData("State", 150, JLabel.CENTER),
    };
    private Vector<TemporaryData> data;

    public TableDataModel() {
        data = new Vector<TemporaryData>();
    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return data.get(rowIndex).getStateSymbol();
            case 1:
                return "->";
            case 2:
                return data.get(rowIndex).getStateSymbolMove();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        return columns[column].title;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Vector<TemporaryData> getData() {
        return data;
    }
    
    void removeRow(int index){
        data.remove(index);
    }
}
