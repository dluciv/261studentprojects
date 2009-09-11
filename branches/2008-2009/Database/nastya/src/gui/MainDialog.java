package gui;

import dbentities.Condition;

import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

import utils.Util;

public class MainDialog extends JFrame {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JTabbedPane tabbedPane1;
    private GenerationForm generator;
    private AllDatabaseForm allDatabase;
    private TestingIndexForm indexForm;
    private TestIteratorForm searchForm;
    private LogForm logForm;

    public MainDialog() {
        setContentPane(contentPane);
        setSize(Util.defineSize());
        setLocation(Util.defaineLocation(size()));
        generator = new GenerationForm();
        allDatabase = new AllDatabaseForm(this);
        indexForm = new TestingIndexForm();
        logForm = new LogForm();
        searchForm = new TestIteratorForm(this);
        tabbedPane1.addTab("Генератор", generator.getPanel());
        tabbedPane1.addTab("Вся база", allDatabase.getPanel());
        tabbedPane1.addTab("Индекс", indexForm.getPanel());
        tabbedPane1.addTab("Поиск", searchForm.getPanel());
        tabbedPane1.addTab("Log", logForm.getPanel());


        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }




    private void onCancel() {
        dispose();
        System.exit(0);
    }

    public static void main(String[] args) {
        MainDialog dialog = new MainDialog();
        
        dialog.setVisible(true);
//        System.exit(0);
    }

    public void log(long recordCount, long time, Vector<Long> timeStat) {
        logForm.log(recordCount, time, timeStat);
    }

    public void log(long time) {
        logForm.log(time);
    }

    public void setStartCondition(Condition condition) {
        searchForm.setStartCondition(condition);
    }

    public void setStopCondition(Condition condition) {
        searchForm.setStopCondition(condition);
    }
}
