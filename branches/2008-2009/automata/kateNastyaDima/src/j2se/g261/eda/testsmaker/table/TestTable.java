/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.testsmaker.table;

import j2se.g261.eda.automator.tests.TestItemStorage;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author nastya
 */
public class TestTable extends JPanel {

    private JTable table;
    private TestsTableData tableData;
    private static final String IMAGE_OK = "icons" + File.separator + "ok.png";
    private static final String IMAGE_CANCEL = "icons" + File.separator + "cancel.png";

    public TestTable() {
        UIManager.put("Table.focusCellHighlightBorder",
                new LineBorder(Color.black, 0));
        tableData = new TestsTableData();
        table = new JTable();
        table.setAutoCreateColumnsFromModel(false);
        table.setModel(tableData);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        for (int k = 0; k < tableData.getColumnCount(); k++) {
            DefaultTableCellRenderer renderer = new BooleanTableCellRenderer();
            renderer.setHorizontalAlignment(
                    TestsTableData.columns[k].alignment);
            TableColumn column = new TableColumn(k,
                    TestsTableData.columns[k].width, renderer, null);
            table.addColumn(column);
        }
        JTableHeader header = table.getTableHeader();
        header.setUpdateTableInRealTime(false);
        JScrollPane ps = new JScrollPane();
        ps.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        ps.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ps.getViewport().setBackground(table.getBackground());
        ps.getViewport().add(table);
        ps.setPreferredSize(new Dimension(450, 280));
        add(ps, BorderLayout.CENTER);
    }

    public void addData(String pattern, String string, boolean matches) {
        tableData.addData(pattern, string, matches);
        table.updateUI();
    }

    public void loadData(TestItemStorage data) {
        tableData.setData(data);
    }

    private void removeData(int index) {
        tableData.removeData(index);
        table.updateUI();
    }

    public void removeSelected() {
        if (table.getSelectedRow() != -1) {
            removeData(table.getSelectedRow());
        }
    }

    public TestItemStorage getDataObject() {
        return tableData.getDataObject();
    }

    class BooleanTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component o = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (o instanceof JLabel && column == 2) {
                ((JLabel) o).setText("");
                if (value.toString().equals("1")) {
                    ((JLabel) o).setIcon(new ImageIcon(IMAGE_OK));
                } else {
                    ((JLabel) o).setIcon(new ImageIcon(IMAGE_CANCEL));
                }
            }
            return o;
        }
    }
}

