/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eda.tm.gui;

import eda.tm.Trace;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nastya
 */
public class TraceTableModel extends AbstractTableModel {

    private Trace data = null;
    String[] columns;

    public TraceTableModel(Trace data) {
        this.data = data;
        data.balance();
        System.out.println(data.getLength());
        initializeHeaders(data.getLength());
        System.out.println(columns.length);
    }

    void initializeHeaders(int count) {
        columns = new String[count];
        for (int i = 0; i < columns.length; i++) {
            columns[i] = "";
        }
    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
//        if (columnIndex == 0) {
//            return rowIndex;
//        } else {
            return data.get(rowIndex).get(columnIndex);
//        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
