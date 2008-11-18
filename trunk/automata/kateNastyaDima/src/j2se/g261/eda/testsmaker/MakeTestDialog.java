/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.testsmaker;

import j2se.g261.eda.automator.tests.TestItemStorage;
import j2se.g261.eda.testsmaker.table.TestTable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author nastya
 */
public class MakeTestDialog extends JDialog implements ActionListener {

    private JTextField tfPattern;
    private JTextField tfString;
    private JCheckBox tfResult;
    private JButton add;
    private JPanel added;
    private JButton remove;
    private TestTable testTable;
    public int RESULT_OK;

    public MakeTestDialog(Frame parent) {
        super(parent);
        constructGUI();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add.addActionListener(this);
        remove.addActionListener(this);
    }

//    public static void main(String[] args) {
//        MakeTestDialog frame = new MakeTestDialog();
//        ObjectInputStream in = null;
//        try {
//            File file = new File(SER_FILENAME);
//            in = new ObjectInputStream(new FileInputStream(file));
//            TestItemStorage data = (TestItemStorage) in.readObject();
//            frame.loadData(data);
//        } catch (IOException ex) {
//        } catch (ClassNotFoundException ex) {
//        } finally {
//            try {
//                in.close();
//            } catch (IOException ex) {
//            } catch (NullPointerException e){
//            }
//        }
//        frame.setSize(new Dimension(500, 510));
//        frame.setVisible(true);
//    }

    private void constructGUI() {
        getContentPane().setLayout(new BorderLayout());
        constructTable();
        getContentPane().add(added, BorderLayout.SOUTH);
        getContentPane().add(constructPanel(), BorderLayout.NORTH);
    }

    private JPanel constructPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        GridBagConstraints c = new GridBagConstraints();

        tfPattern = new JTextField();
        tfPattern.setPreferredSize(new Dimension(350, 20));
        tfString = new JTextField();
        tfString.setPreferredSize(new Dimension(350, 20));
        tfResult = new JCheckBox();
        add = new JButton("Add");

        c.gridx = 0;
        c.gridy = 0;
        panel.add(new JLabel("Pattern"));
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 15;
        c.insets = new Insets(4, 4, 4, 4);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(tfPattern, c);
        c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = 1;
        panel.add(new JLabel("String"), c);
        c.gridx = 1;
        c.gridwidth = 15;
        panel.add(tfString, c);
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 5;
        panel.add(new JLabel("Matches"), c);
        c.gridx = 5;
        c.gridwidth = 1;
        panel.add(tfResult, c);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BorderLayout());
        btnPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        btnPanel.add(add, BorderLayout.EAST);

        c.gridy = 3;
        c.gridx = 0;
        c.gridwidth = 15;
        panel.add(btnPanel, c);


        return panel;
    }

    private void constructTable() {
        added = new JPanel();
        added.setBorder(new EtchedBorder());
        testTable = new TestTable();
        remove = new JButton("Remove");

        added.setLayout(new BorderLayout());
        added.add(testTable, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BorderLayout());
        btnPanel.add(remove, BorderLayout.EAST);
        btnPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        added.add(btnPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (add.equals(e.getSource())) {
            if (tfPattern.getText().isEmpty() || tfString.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter input int" +
                        "o\n pattern and string fields ", "Error!", JOptionPane.ERROR_MESSAGE);
            } else {
                testTable.addData(tfPattern.getText(), tfString.getText(), tfResult.isSelected());
            }
        }
        if (remove.equals(e.getSource())) {
            testTable.removeSelected();
        }
    }

    @Override
    public void dispose() {
//        try {
//            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(SER_FILENAME));
//            out.writeObject(testTable.getDataObject());
//            out.close();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(MakeTestDialog.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(MakeTestDialog.class.getName()).log(Level.SEVERE, null, ex);
//        }
        super.dispose();
    }

    private void loadData(TestItemStorage data) {
        testTable.loadData(data);
    }
}
