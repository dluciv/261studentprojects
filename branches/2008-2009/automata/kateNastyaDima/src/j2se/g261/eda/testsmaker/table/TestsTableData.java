/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.testsmaker.table;

import j2se.g261.eda.automator.tests.TestItemStorage;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nastya
 */
public class TestsTableData extends AbstractTableModel {

    static final public ColumnData columns[] = {
        new ColumnData("Pattern", 180, JLabel.LEFT),
        new ColumnData("String", 180, JLabel.LEFT),
        new ColumnData("", 10, JLabel.LEFT)
    };
    protected TestItemStorage data = new TestItemStorage();

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column].title;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return data.getPattern(rowIndex);
            case 1:
                return data.getString(rowIndex);
            case 2:
                return data.isMatches(rowIndex);
        }
        return "";
    }

    @Override
    public boolean isCellEditable(int nRow, int nCol) {
        return false;
    }

    void addData(String pattern, String string, boolean matches) {
        data.addTest(pattern, string, matches);
    }

    void removeData(int index) {
        data.removeTest(index);
    }

    TestItemStorage getDataObject() {
        return data;
    }

    void setData(TestItemStorage data) {
        this.data = data;
    }
}
