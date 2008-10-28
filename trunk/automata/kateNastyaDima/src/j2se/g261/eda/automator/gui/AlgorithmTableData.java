/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.gui;

import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nastya
 */
public class AlgorithmTableData  extends AbstractTableModel{

    static final  ColumnData columns[] = {
        new ColumnData("Algorithm", 320, JLabel.LEFT),
        new ColumnData("", 10, JLabel.LEFT)
    };
    
    protected Vector<AlgorithmResult> data = new Vector<AlgorithmResult>();

    public  AlgorithmResult ALGORITHM_MINIMIZATION = new AlgorithmResult("Minimization graph", Result.UNKNOWN);
    public  AlgorithmResult ALGORITHM_TABLE = new AlgorithmResult("NFA Table", Result.PASSED);
    public  AlgorithmResult ALGORITHM_DFA = new AlgorithmResult("DFA graph", Result.NOT_PASSED);
    public  AlgorithmResult ALGORITHM_NFA = new AlgorithmResult("NFA graph", Result.UNKNOWN);

    public AlgorithmTableData() {
        data.add(ALGORITHM_MINIMIZATION);
        data.add(ALGORITHM_TABLE);
        data.add(ALGORITHM_DFA);
        data.add(ALGORITHM_NFA);
    }
    
    public AlgorithmResult getAlgorithmResultAtRow(int index){
        return data.get(index);
    }
    
    public int getAlgorithmResultIndex(AlgorithmResult r){
        return data.indexOf(r);
    }
    
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
        switch (columnIndex){
            case 0 : return data.get(rowIndex).name;
            case 1 : return data.get(rowIndex).result;
        }
        return "";
    }
    
    
    @Override
    public boolean isCellEditable(int nRow, int nCol) {
        return false;
    }

    
    public class AlgorithmResult{
        private String name;
        private Result result;

        AlgorithmResult(String name, Result result) {
            this.name = name;
            this.result = result;
        }

        public void setResult(Result result) {
            this.result = result;
        }
        
    }
    
    public enum Result{
        UNKNOWN, PASSED, NOT_PASSED;
    }
}