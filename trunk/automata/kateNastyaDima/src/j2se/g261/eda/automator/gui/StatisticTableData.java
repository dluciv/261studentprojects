/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.gui;

import j2se.g261.eda.automator.tests.ItemFilter;
import j2se.g261.eda.automator.tests.TestResultItem;
import j2se.g261.eda.automator.tests.TestResultItemStorage;
import j2se.g261.eda.testsmaker.table.ColumnData;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nastya
 */
public class StatisticTableData extends  AbstractTableModel{
    
    static final  ColumnData columns[] = {
//        new ColumnData("Pattern", 100, JLabel.LEFT),
//        new ColumnData("String", 150, JLabel.LEFT),
//        new ColumnData("", 25, JLabel.LEFT),
        new ColumnData("Table", 50, JLabel.CENTER),
        new ColumnData("Table stat", 200, JLabel.LEFT),
        new ColumnData("DFA", 30, JLabel.CENTER),
        new ColumnData("DFA stat", 200, JLabel.LEFT),
        new ColumnData("NFA", 30, JLabel.CENTER),
        new ColumnData("NFA stat", 200, JLabel.LEFT),
        new ColumnData("MinDFA", 60, JLabel.CENTER),
        new ColumnData("MinDFA stat", 200, JLabel.LEFT),
    };
    
    private TestResultItemStorage data;
    
    public StatisticTableData(){
        data = new TestResultItemStorage();
    }

    public void setData(TestResultItemStorage data) {
        this.data = data;
    }

   public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex].title;
    }


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
//        System.out.println("row index: " + rowIndex);
        TestResultItem item = data.getTestResult(rowIndex);
        switch(columnIndex){
            case 0:
                return item.getTable().isMatches();
            case 1:
                return item.getTable().toString();
            case 2:
                return item.getDFA().isMatches();
            case 3:
                return item.getDFA().toString();
            case 4:
                return item.getNFA().isMatches();
            case 5:
                return item.getNFA().toString();
            case 6:
                return item.getMinGraph().isMatches();
            case 7:
                return item.getMinGraph().toString();
        }
        return "";
    }

    TestResultItemStorage getData() {
        return data;
    }

    void setFilter(ItemFilter filter) {
        data.setFilter(filter);
    }

    

 

}
