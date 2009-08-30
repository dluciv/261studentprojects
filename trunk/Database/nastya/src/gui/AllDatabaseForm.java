package gui;

import gui.table.CardColumnModel;
import gui.table.CardTableModel;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.Vector;

import utils.Util;
import utils.Messages;
import database.parser.DatabaseParser;
import database.parser.ParserException;
import dbentities.Card;
import dbentities.Condition;

/**
 * На этой форме демонстрируется полное содержимое базы
 * @author nastya
 * Date: 21.08.2009
 * Time: 1:20:52
 * To change this template use File | Settings | File Templates.
 */
public class AllDatabaseForm {
    private JButton btnLoad;
    private JTable table;
    private JLabel lbAllCount;
    private JLabel lbAllTime;
    private JPanel panel;
    public static final String DEFAULT_DB_NAME = "sample.db";
    public static final String DEFAULT_DB_PATTERN = ".*\\.db";
    public static final String DEFAULT_DB_PATTERN_DESCRIPTION = ".db";
    DatabaseParser parser = null;
    private MainDialog mainDialog;
    private JPopupMenu popupMenu;

    public AllDatabaseForm(MainDialog mainDialog) {
        this.mainDialog = mainDialog;
        btnLoad.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                onLoad();
            }
        });
        addPopupMenu();
        table.setAutoCreateRowSorter(true);
    }

    private void addPopupMenu() {
        popupMenu = new JPopupMenu();
        JMenuItem startItem = new JMenuItem("Вставить как начальное условие");
        JMenuItem stopItem = new JMenuItem("Вставить как конечное условие");
        startItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAddConditionAsStart();
            }
        });
        stopItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAddConditionAsStop();
            }
        });
        popupMenu.add(startItem);
        popupMenu.add(stopItem);


        MouseListener popupListener = new PopupListener();
        table.addMouseListener(popupListener);


    }

    class PopupListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            showPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            showPopup(e);
        }

        private void showPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }


    private void onAddConditionAsStop() {
        mainDialog.setStopCondition(getCurrentCondition());
    }

    private void onAddConditionAsStart() {
        mainDialog.setStartCondition(getCurrentCondition());
    }

    private Condition getCurrentCondition() {
        int selected = table.getSelectedRow();
        if (selected == -1) return null;
        Card card = ((CardTableModel) table.getModel()).getData(table.convertRowIndexToModel(selected));
        return new Condition(card.getLastName(), card.getName(), card.getMiddleName());
    }

    private void onLoad() {
        btnLoad.setEnabled(false);
        final File file = Util.openFileChooser(panel, false, DEFAULT_DB_NAME, DEFAULT_DB_PATTERN, DEFAULT_DB_PATTERN_DESCRIPTION);
        if (file == null) return;
        if (parser == null) try {
            parser = new DatabaseParser(file.getAbsolutePath());
        } catch (ParserException e) {
            JOptionPane.showMessageDialog(panel, Messages.ERROR_DB_STRUCTURE, Messages.ERROR, JOptionPane.ERROR_MESSAGE);
            return;
        }
        Thread generationThread = new Thread() {
            @Override
            public void run() {
                try {
                    Vector<Card> data = parser.parse();
                    ((CardTableModel) table.getModel()).setData(data);
                    updatePanel();
                } catch (ParserException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(panel, Messages.PARSING_DB_FAILED, Messages.ERROR, JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(panel, Messages.PARSING_DB_SUCCEDED, Messages.ATTENTION, JOptionPane.INFORMATION_MESSAGE);

                btnLoad.setEnabled(true);
            }
        };
        generationThread.start();
    }

    public void updatePanel() {
        lbAllCount.setText(String.valueOf(table.getRowCount()));
        lbAllTime.setText(String.valueOf(Util.reprsentTime(parser.getTime())));
        mainDialog.log(table.getRowCount(), parser.getTime(), parser.getTimeStat());
        table.updateUI();
        lbAllCount.repaint();
        lbAllTime.repaint();

    }

    private void createUIComponents() {
        CardColumnModel columnModel = new CardColumnModel();
        CardTableModel tableModel = new CardTableModel(columnModel);
        table = new JTable(tableModel, columnModel);
    }

    public JPanel getPanel() {
        return panel;
    }
}
