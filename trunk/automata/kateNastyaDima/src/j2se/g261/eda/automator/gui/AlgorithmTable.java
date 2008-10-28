/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;


/**
 *
 * @author nastya
 */
public class AlgorithmTable extends JPanel{

    public JTable table;
    private AlgorithmTableData tableData  = new AlgorithmTableData();
    public  AlgorithmTableData.AlgorithmResult ALGORITHM_MINIMIZATION = tableData.ALGORITHM_MINIMIZATION;
    public  AlgorithmTableData.AlgorithmResult ALGORITHM_TABLE = tableData.ALGORITHM_TABLE;
    public  AlgorithmTableData.AlgorithmResult ALGORITHM_DFA = tableData.ALGORITHM_DFA;
    public  AlgorithmTableData.AlgorithmResult ALGORITHM_NFA = tableData.ALGORITHM_NFA;
    public AlgorithmTable(){
        UIManager.put("Table.focusCellHighlightBorder",
                new LineBorder(Color.black, 0));
        
        table = new JTable();
        table.setAutoCreateColumnsFromModel(false);
        table.setModel(tableData);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        for (int k = 0; k < tableData.getColumnCount(); k++) {
            DefaultTableCellRenderer renderer = new ResultCellRenderer();
            renderer.setHorizontalAlignment(
                    AlgorithmTableData.columns[k].alignment);
            TableColumn column = new TableColumn(k,
                    AlgorithmTableData.columns[k].width, renderer, null);
            table.addColumn(column);
        }
        JTableHeader header = table.getTableHeader();
        header.setUpdateTableInRealTime(false);
        JScrollPane ps = new JScrollPane();
        ps.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        ps.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ps.getViewport().setBackground(table.getBackground());
        ps.getViewport().add(table);
        ps.setPreferredSize(new Dimension(350, 150));
        add(ps, BorderLayout.CENTER);
    }
    
    public void addSelectionLisener(ListSelectionListener l){
        table.getSelectionModel().addListSelectionListener(l);
    }
    
    public void isSource(ListSelectionEvent e){
        table.getSelectionModel().equals(e.getSource());
    }
    
    public AlgorithmTableData.AlgorithmResult getSelected(){
        if(table.getSelectedRow() > -1){
            return tableData.getAlgorithmResultAtRow(table.getSelectedRow());
        }
        return null;
    }
    
    public void setSelected(AlgorithmTableData.AlgorithmResult r){
        table.getSelectionModel().setSelectionInterval(
                tableData.getAlgorithmResultIndex(r), 
                tableData.getAlgorithmResultIndex(r));
    }

    void updateTableUI() {
        table.updateUI();
    }
}
class ColumnData {

    public String title;
    public int width;
    public int alignment;

    public ColumnData(String title, int width, int alignment) {
        this.title = title;
        this.width = width;
        this.alignment = alignment;
    }
}



    class ResultCellRenderer extends DefaultTableCellRenderer {

    private static final String IMAGE_OK = "icons" + File.separator + "ok.png";
    private static final String IMAGE_CANCEL = "icons" + File.separator + "cancel.png";
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component o = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (o instanceof JLabel && column == 1) {
                ((JLabel) o).setText("");
                if (value == AlgorithmTableData.Result.PASSED) {
                    ((JLabel) o).setIcon(new ImageIcon(IMAGE_OK));
                } else if(value == AlgorithmTableData.Result.NOT_PASSED){
                    ((JLabel) o).setIcon(new ImageIcon(IMAGE_CANCEL));
                }else{
                    ((JLabel) o).setIcon(null);
                }
            }
            return o;
        }
    }



