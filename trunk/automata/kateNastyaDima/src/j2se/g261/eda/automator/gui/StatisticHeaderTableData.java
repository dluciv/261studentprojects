/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.gui;

import j2se.g261.eda.automator.tests.ItemFilter;
import j2se.g261.eda.automator.tests.TestResultItem;
import j2se.g261.eda.automator.tests.TestResultItemStorage;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nastya
 */
public class StatisticHeaderTableData extends AbstractTableModel{
    static final  ColumnData columns[] = {
        new ColumnData("Pattern", 100, JLabel.LEFT),
        new ColumnData("String", 150, JLabel.LEFT),
        new ColumnData("", 25, JLabel.LEFT)
    };
    
    private TestResultItemStorage data;

    public StatisticHeaderTableData() {
        data = new TestResultItemStorage();
    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
                TestResultItem item = data.getTestResult(rowIndex);
        switch(columnIndex){
            case 0:
                return item.getPattern();
            case 1:
                return item.getString();
            case 2: 
                return item.isMatches();
        }
        return "";
    }

    public TestResultItemStorage getData() {
        return data;
    }

    public void setData(TestResultItemStorage data) {
        this.data = data;
    }

    void setFilter(ItemFilter filter) {
        data.setFilter(filter);
    }
    
    
    

}
