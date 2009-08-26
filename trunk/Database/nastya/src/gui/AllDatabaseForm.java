package gui;

import gui.table.CardColumnModel;
import gui.table.CardTableModel;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Vector;

import utils.Util;
import utils.Messages;
import database.parser.DatabaseParser;
import database.parser.ParserException;
import dbentities.Card;

/**
 * Created by IntelliJ IDEA.
 * User: nastya
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

    public AllDatabaseForm() {
        btnLoad.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                onLoad();
            }
        });
    }

    private void onLoad() {
        btnLoad.setEnabled(false);
        final File file = Util.openFileChooser(panel, false, DEFAULT_DB_NAME, DEFAULT_DB_PATTERN, DEFAULT_DB_PATTERN_DESCRIPTION);
        if(file == null) return;
        if(parser == null) parser = new DatabaseParser();
        Thread generationThread = new Thread(){
            @Override
            public void run() {
                try {
                    Vector<Card> data = parser.parse(file.getAbsolutePath());
                    ((CardTableModel)table.getModel()).setData(data);
                    updatePanel();
                } catch (ParserException e) {
                    JOptionPane.showMessageDialog(panel, Messages.PARSING_FAILED, Messages.ERROR, JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(panel, Messages.PARSING_SUCCEDED, Messages.ATTENTION, JOptionPane.INFORMATION_MESSAGE);

                btnLoad.setEnabled(true);
            }
        };
        generationThread.start();
    }

    public void updatePanel(){
        lbAllCount.setText(String.valueOf(table.getRowCount()));
        lbAllTime.setText(String.valueOf(Util.reprsentTime(parser.getTime())));
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
