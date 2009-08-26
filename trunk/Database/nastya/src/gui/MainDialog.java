package gui;

import tree.BTree;

import javax.swing.*;
import java.awt.event.*;

import gui.tree.TestData;

public class MainDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JTabbedPane tabbedPane1;
    private GenerationForm generator;
    private AllDatabaseForm allDatabase;
    private IndexDemonstrationForm indexForm;

    public MainDialog() {
        setContentPane(contentPane);
        setModal(true);
        generator = new GenerationForm();
        allDatabase = new AllDatabaseForm();
        indexForm = new IndexDemonstrationForm();
        tabbedPane1.addTab("Генератор", generator.getPanel());
        tabbedPane1.addTab("Вся база", allDatabase.getPanel());
        tabbedPane1.addTab("Индекс", indexForm.getPanel());
        BTree<TestData> tree = new BTree<TestData>(3);
        tree.add(new TestData(1, 1l));
        tree.add(new TestData(3, 3l));
        tree.add(new TestData(8, 8l));
        tree.add(new TestData(4, 4l));
        tree.add(new TestData(2, 4l));
        tree.add(new TestData(5, 4l));
        tree.add(new TestData(6, 4l));
        tree.add(new TestData(9, 4l));
        tree.add(new TestData(10, 4l));
        tree.add(new TestData(11, 4l));
        tree.add(new TestData(13, 4l));
        tree.add(new TestData(14, 4l));
//        tree.add(new TestData(15, 4l));
//        tree.add(new TestData(16, 4l));
//        tree.add(new TestData(17, 4l));
        indexForm.fill(tree);


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
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);


//        System.out.println(tree);
    }
}
