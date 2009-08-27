package gui;

import javax.swing.*;
import java.awt.event.*;

public class MainDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JTabbedPane tabbedPane1;
    private GenerationForm generator;
    private AllDatabaseForm allDatabase;
    private TestingIndexForm indexForm;

    public MainDialog() {
        setContentPane(contentPane);
        setModal(true);
        generator = new GenerationForm();
        allDatabase = new AllDatabaseForm();
        indexForm = new TestingIndexForm();
        tabbedPane1.addTab("Генератор", generator.getPanel());
        tabbedPane1.addTab("Вся база", allDatabase.getPanel());
        tabbedPane1.addTab("Индекс", indexForm.getPanel());


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


//        System.out.println(tests);
    }
}
