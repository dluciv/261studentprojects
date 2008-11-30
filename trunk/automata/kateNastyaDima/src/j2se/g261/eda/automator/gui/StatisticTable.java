/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.gui;

import j2se.g261.eda.automator.tests.ItemFilter;
import j2se.g261.eda.automator.tests.TestResultItem;
import j2se.g261.eda.automator.tests.TestResultItemStorage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;


//class ColumnData {
//
//    public String title;
//    public int width;
//    public int alignment;
//
//    public ColumnData(String title, int width, int alignment) {
//        this.title = title;
//        this.width = width;
//        this.alignment = alignment;
//    }
//}
//
//class ResultCellRenderer extends DefaultTableCellRenderer {
//
//    private static final String IMAGE_OK = "icons" + File.separator + "ok.png";
//    private static final String IMAGE_CANCEL = "icons" + File.separator + "cancel.png";
//
//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        Component o = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//        if (o instanceof JLabel && column == 1) {
//            ((JLabel) o).setText("");
//            if (String.valueOf(value).equals(Boolean.toString(true))) {
//                ((JLabel) o).setIcon(new ImageIcon(IMAGE_OK));
//            } else if (String.valueOf(value).equals(Boolean.toString(false))) {
//                ((JLabel) o).setIcon(new ImageIcon(IMAGE_CANCEL));
//            } else {
//                ((JLabel) o).setIcon(null);
//            }
//        }
//        return o;
//    }
//}
/**
 *
 * @author nastya
 */
public class StatisticTable extends JPanel {

    public JTable table;
    private JTable header;
    private StatisticTableData tableData = new StatisticTableData();
    private StatisticHeaderTableData tableHeaderData = new StatisticHeaderTableData();

    public StatisticTable() {
        UIManager.put("Table.focusCellHighlightBorder",
                new LineBorder(Color.black, 0));

        header = new JTable() {

            public Component prepareRenderer(TableCellRenderer renderer,
                    int rowIndex, int vColIndex) {
                Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
                if (c instanceof JComponent) {
                    JComponent jc = (JComponent) c;
                    jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

                }
                return c;
            }
        };
        header.setAutoCreateColumnsFromModel(false);
        header.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        header.setModel(tableHeaderData);
        header.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table = new JTable() {

            public Component prepareRenderer(TableCellRenderer renderer,
                    int rowIndex, int vColIndex) {
                Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
                if (c instanceof JComponent) {
                    JComponent jc = (JComponent) c;
                    jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

                }
                return c;
            }
        };
        table.setAutoCreateColumnsFromModel(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setModel(tableData);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        for (int k = 0; k < tableData.getColumnCount(); k++) {
            DefaultTableCellRenderer renderer = new StatResultCellRenderer();
            renderer.setHorizontalAlignment(
                    StatisticTableData.columns[k].alignment);
            TableColumn column = new TableColumn(k,
                    StatisticTableData.columns[k].width, renderer, null);
            table.addColumn(column);
        }

        for (int k = 0; k < tableHeaderData.getColumnCount(); k++) {
            TableCellRenderer renderer = new StatHeaderResultCellRenderer(table);
//            renderer.setHorizontalAlignment(
//                    StatisticHeaderTableData.columns[k].alignment);
            TableColumn column = new TableColumn(k,
                    StatisticHeaderTableData.columns[k].width, renderer, null);
            header.addColumn(column);
        }
        header.getTableHeader().setDefaultRenderer(new StatHeaderResultCellRenderer(table));
//        JTableHeader header = table.getTableHeader();
//        header.setUpdateTableInRealTime(false);
        JScrollPane ps = new JScrollPane(table);
        JViewport jv = new JViewport();
        jv.setView(header);
//        jv.setPreferredSize(new Dimension(275, 250));
        Dimension d = header.getPreferredScrollableViewportSize();

        d.width = header.getPreferredSize().width;

        header.setPreferredScrollableViewportSize(d);
        header.setIntercellSpacing(new Dimension(0, 0));
        header.setRowHeight(table.getRowHeight());
        JLabel cornerHeader = new JLabel("Data");
        cornerHeader.setOpaque(table.getTableHeader().isOpaque());
        cornerHeader.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        cornerHeader.setHorizontalAlignment(JLabel.CENTER);
        cornerHeader.setForeground(table.getTableHeader().getForeground());
        cornerHeader.setBackground(table.getTableHeader().getBackground());
        cornerHeader.setFont(table.getTableHeader().getFont());

        ps.setCorner(JScrollPane.UPPER_LEFT_CORNER, cornerHeader);
        ps.setRowHeader(jv);
        ps.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        ps.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ps.getViewport().setBackground(table.getBackground());
        ps.setPreferredSize(new Dimension(900, 250));
        add(ps, BorderLayout.CENTER);
    }

    void deleteSelectedRows() {
        Vector<TestResultItem> toDelete = new Vector<TestResultItem>();
        for (int i = 0; i < table.getSelectedRows().length; i++) {
            toDelete.add(tableData.getData().getTestResult(table.getSelectedRows()[i]));
        }
        
            table.getSelectionModel().removeSelectionInterval(
                    0, table.getRowCount() - 1);
        for (TestResultItem testResultItem : toDelete) {
            tableData.getData().removeTestResult(testResultItem);
        }
        table.updateUI();
        header.updateUI();
    }

    void updateTableUI() {
        table.updateUI();
        header.updateUI();
        updateUI();
    }

    public void setData(TestResultItemStorage data) {
        tableHeaderData.setData(data);
        tableData.setData(data);
    }

    public TestResultItemStorage getData() {
        return tableData.getData();
    }

    public void setFilter(ItemFilter filter) {
        tableData.setFilter(filter);
        tableHeaderData.setFilter(filter);
        table.updateUI();
        header.updateUI();
    }
}
class StatResultCellRenderer extends DefaultTableCellRenderer {

    private static final String IMAGE_OK = "icons" + File.separator + "ok.png";
    private static final String IMAGE_CANCEL = "icons" + File.separator + "cancel.png";

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component o = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (o instanceof JLabel) {
            if (String.valueOf(value).equals(Boolean.toString(true))) {
                ((JLabel) o).setIcon(new ImageIcon(IMAGE_OK));
                ((JLabel) o).setText("");
            } else if (String.valueOf(value).equals(Boolean.toString(false))) {
                ((JLabel) o).setIcon(new ImageIcon(IMAGE_CANCEL));
                ((JLabel) o).setText("");
            } else {
                ((JLabel) o).setIcon(null);
            }
        }
        return o;
    }
}

class StatHeaderResultCellRenderer extends JLabel implements TableCellRenderer {

    private static final String IMAGE_OK = "icons" + File.separator + "ok.png";
    private static final String IMAGE_CANCEL = "icons" + File.separator + "cancel.png";

    public StatHeaderResultCellRenderer(JTable table) {
        JTableHeader header = table.getTableHeader();
        setOpaque(true);
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        setHorizontalAlignment(CENTER);
        setForeground(header.getForeground());
        setBackground(header.getBackground());
        setFont(header.getFont());
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        Component o = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (column == 0) {
            setBackground(new Color(153, 153, 153));
        }
        if (String.valueOf(value).equals(Boolean.toString(true))) {
            setIcon(new ImageIcon(IMAGE_OK));
            setText("");
        } else if (String.valueOf(value).equals(Boolean.toString(false))) {
            setIcon(new ImageIcon(IMAGE_CANCEL));
            setText("");
        } else {
            setText(String.valueOf(value));
            setIcon(null);
        }
        return this;
    }
}

