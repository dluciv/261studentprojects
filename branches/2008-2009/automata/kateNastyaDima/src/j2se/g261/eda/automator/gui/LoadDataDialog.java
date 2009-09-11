/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LoadDataDialog.java
 *
 * Created on 11.11.2008, 6:41:53
 */
package j2se.g261.eda.automator.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import j2se.g261.eda.automator.tests.*;
import j2se.g261.eda.testsmaker.TestMakerDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Random;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author nastya
 */
public class LoadDataDialog extends javax.swing.JDialog implements ActionListener {

    TestItemStorage[] lastLoadedTests = null;
    TestResultItemStorage[] lastLoadedResults = null;
    TestItemStorage lastRandomTests = null;
    TestItemStorage lastCreatedTests = null;
    Object[] toTest = null;
    TestResultItemStorage result = null;
    JRadioButton lastSelectedRadioButton = null;
    private static final int MAX_NUMBER_OF_MEASURES = Integer.MAX_VALUE;
    private static final int MINIMUM_NUMBER_OF_MEASURES = 1;
    private static final int MAX_NUMBER_OF_CICLES = Integer.MAX_VALUE;
    private static final int MINIMUM_NUMBER_OF_CICLES = 1;
    private static final int DEFAULT_MAX_LENGTH = 100;
    private static final int DEFAULT_NUMBER = 100;
    public boolean RESULT_OK = false;

    /** Creates new form LoadDataDialog */
    public LoadDataDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
        lastSelectedRadioButton = rbLoadTests;
        switchGroup(rbLoadTests, true);
        switchGroup(rbLoadResults, false);
        switchGroup(rbCreateTests, false);
        switchGroup(rbGenerateRandom, false);
        btnGenerateRandom.addActionListener(this);
        btnLoadTests.addActionListener(this);
        btnLoadResults.addActionListener(this);
        btnCreateTests.addActionListener(this);
        btnCancel.addActionListener(this);
        btnOk.addActionListener(this);
        rbCreateTests.addActionListener(this);
        rbGenerateRandom.addActionListener(this);
        rbLoadResults.addActionListener(this);
        rbLoadTests.addActionListener(this);
        rbLoadTests.setSelected(true);
        spMeasure.setValue(1);
        tfMaxLength.setText(String.valueOf(DEFAULT_MAX_LENGTH));
        ftfNumberOfTests.setText(String.valueOf(DEFAULT_NUMBER));
        ((SpinnerNumberModel) spMeasure.getModel()).setMinimum(MINIMUM_NUMBER_OF_MEASURES);
        ((SpinnerNumberModel) spMeasure.getModel()).setMaximum(MAX_NUMBER_OF_MEASURES);

    }

    public Object[] getToTest() {
        return toTest;
    }

    public int getNumberOfMeasures() {
        return Integer.parseInt(String.valueOf(spMeasure.getValue()));
    }

    private Vector<Character> collectAlphabet(String pattern) {
        Vector<Character> alp = new Vector<Character>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (c != '*' && c != ')' && c != '(' && c != '?' && c != '|' && !alp.contains(c)) {
                alp.add(c);
            }
        }
        return alp;
    }

    private TestItemStorage convertTests(tests.TestItemStorage testItemStorage) {
        TestItemStorage st = new TestItemStorage();
        for (int i = 0; i < testItemStorage.size(); i++) {
            st.addTest(testItemStorage.getPattern(i), testItemStorage.getString(i), testItemStorage.isMatches(i).equals("1"));
        }
        return st;
    }

    private char nextCharacterFromAlphabet(Vector<Character> alphabet, Random rng) {
        if (alphabet.size() == 0) {
            return ' ';
        }
        float coef = (float) 1 / alphabet.size();
        int ind = (int) (rng.nextFloat() / coef);
        if (ind > alphabet.size() - 1) {
            return alphabet.lastElement();
        } else {
            return alphabet.get(ind);
        }

    }

    private void onCreateTests() {
        TestMakerDialog testMaker = new TestMakerDialog(this);
        testMaker.setVisible(true);
//        if (testMaker.RESULT_OK) {
        if (testMaker.getData() != null) {
            lastCreatedTests = testMaker.getData();
            toTest = new TestItemStorage[]{lastCreatedTests};
        }
//        }
    }

    private void onGenerateRandomTests() {
        String pattern = tfPattern.getText().trim();
        int maxLength = DEFAULT_MAX_LENGTH;
        int number = DEFAULT_NUMBER;
        try {
            maxLength = Integer.parseInt(tfMaxLength.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Value in \"Max length\" field must be a number!",
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }
        try {
            number = Integer.parseInt(ftfNumberOfTests.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Value in \"Number\" field must be a number!",
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }


        TestItemStorage s = generateRandomTests(pattern, maxLength, number);
        lastRandomTests = s;
        toTest = new TestItemStorage[]{lastRandomTests};

    }

    private TestItemStorage generateRandomTests(String pattern, int maxLength, int number) {
        Vector<Character> alphabet = collectAlphabet(pattern);
        TestItemStorage storage = new TestItemStorage();
        for (int i = 0; i < number; i++) {
            Random RNG = new Random();
            char[] value = new char[maxLength];
            for (int j = 0; j < maxLength; j++) {
                char c = (char) (RNG.nextInt(Character.MAX_VALUE + 1));
                while (Character.isWhitespace(c)) {
                    c = (char) (RNG.nextInt(Character.MAX_VALUE + 1));
                }
                value[j] = nextCharacterFromAlphabet(alphabet, RNG);
            }
            storage.addTest(pattern, new String(value), false);
        }
        return storage;
    }

    private void onLoadResults() {
        JFileChooser fc = new JFileChooser();

        if (!tfLoadResults.getText().isEmpty()) {
            fc.setSelectedFile(new File(tfLoadResults.getText()));
        }
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setMultiSelectionEnabled(true);
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File[] selected = fc.getSelectedFiles();
            if (selected.length > 0) {
                tfLoadResults.setText(selected[0].getAbsolutePath());
            }
            lastLoadedResults = new TestResultItemStorage[selected.length];
            for (int i = 0; i < selected.length; i++) {
                ObjectInputStream in;
                try {
                    in = new ObjectInputStream(new FileInputStream(selected[i]));
                    lastLoadedResults[i] = (TestResultItemStorage) in.readObject();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this,
                            "You choose wrong files. Please choose another",
                            "Error!", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Some internal error occurs.",
                            "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
            toTest = lastLoadedResults;
        }
    }

    private void switchGroup(JRadioButton rb, boolean state) {
        if (rb == rbCreateTests) {
            btnCreateTests.setEnabled(state);
            if (state) {
                toTest = new TestItemStorage[]{lastCreatedTests};
            }
        } else if (rb == rbGenerateRandom) {
            ftfNumberOfTests.setEnabled(state);
            tfMaxLength.setEnabled(state);
            tfPattern.setEnabled(state);
            btnGenerateRandom.setEnabled(state);
            if (state) {
                toTest = new TestItemStorage[]{lastRandomTests};
            }
        } else if (rb == rbLoadResults) {
            btnLoadResults.setEnabled(state);
            if (state) {
                toTest = lastLoadedResults;
            }

        } else if (rb == rbLoadTests) {
            btnLoadTests.setEnabled(state);
            if (state) {
                toTest = lastLoadedTests;
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rbLoadTests = new javax.swing.JRadioButton();
        rbLoadResults = new javax.swing.JRadioButton();
        tfLoadTests = new javax.swing.JTextField();
        btnLoadTests = new javax.swing.JButton();
        tfLoadResults = new javax.swing.JTextField();
        btnLoadResults = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        rbGenerateRandom = new javax.swing.JRadioButton();
        rbCreateTests = new javax.swing.JRadioButton();
        btnGenerateRandom = new javax.swing.JButton();
        ftfNumberOfTests = new javax.swing.JFormattedTextField();
        tfPattern = new javax.swing.JTextField();
        btnCreateTests = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfMaxLength = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        spMeasure = new javax.swing.JSpinner();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Choose tests", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        buttonGroup1.add(rbLoadTests);
        rbLoadTests.setText("Load Tests");

        buttonGroup1.add(rbLoadResults);
        rbLoadResults.setText("Load Results");

        tfLoadTests.setEditable(false);

        btnLoadTests.setText("Load");

        tfLoadResults.setEditable(false);

        btnLoadResults.setText("Load");

        jLabel1.setText("Pattern");

        buttonGroup1.add(rbGenerateRandom);
        rbGenerateRandom.setText("Generate Random Tests For Pattern");

        buttonGroup1.add(rbCreateTests);
        rbCreateTests.setText("Create new Tests");

        btnGenerateRandom.setText("Generate");

        ftfNumberOfTests.setToolTipText("Enter number of tests to generate");

        btnCreateTests.setText("Create");

        jLabel6.setText("Number");

        jLabel2.setText("Maximum length");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbCreateTests, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbGenerateRandom)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCreateTests)
                        .addContainerGap(373, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfLoadResults, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                                    .addComponent(rbLoadTests, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbLoadResults, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                                    .addComponent(tfLoadTests, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnLoadTests)
                                    .addComponent(btnLoadResults)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfMaxLength, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfPattern, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ftfNumberOfTests, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addComponent(btnGenerateRandom)))))
                        .addGap(31, 31, 31))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rbLoadTests)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLoadTests)
                            .addComponent(tfLoadTests, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbLoadResults)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfLoadResults, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLoadResults)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(rbGenerateRandom)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(ftfNumberOfTests, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPattern, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnGenerateRandom)
                    .addComponent(tfMaxLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(rbCreateTests)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCreateTests, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Additional parameters", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel4.setText("Number of measurements");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(37, 37, 37)
                .addComponent(spMeasure, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(187, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(spMeasure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        btnOk.setText("Ok");

        btnCancel.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel)
                        .addGap(12, 12, 12)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnOk))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//                LoadDataDialog dialog = new LoadDataDialog(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreateTests;
    private javax.swing.JButton btnGenerateRandom;
    private javax.swing.JButton btnLoadResults;
    private javax.swing.JButton btnLoadTests;
    private javax.swing.JButton btnOk;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFormattedTextField ftfNumberOfTests;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton rbCreateTests;
    private javax.swing.JRadioButton rbGenerateRandom;
    private javax.swing.JRadioButton rbLoadResults;
    private javax.swing.JRadioButton rbLoadTests;
    private javax.swing.JSpinner spMeasure;
    private javax.swing.JTextField tfLoadResults;
    private javax.swing.JTextField tfLoadTests;
    private javax.swing.JTextField tfMaxLength;
    private javax.swing.JTextField tfPattern;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e) {
        if (btnGenerateRandom.equals(e.getSource())) {
            onGenerateRandomTests();
        }
        if (btnLoadTests.equals(e.getSource())) {
            onLoadTests();
        }
        if (btnLoadResults.equals(e.getSource())) {
            onLoadResults();
        }
        if (btnCancel.equals(e.getSource())) {
            onCancel();
        }
        if (btnOk.equals(e.getSource())) {
            onOk();
        }

        if (btnCreateTests.equals(e.getSource())) {
            onCreateTests();
        }

        if (e.getSource() instanceof JRadioButton) {
//            if (lastSelectedRadioButton != null) {
            switchGroup(lastSelectedRadioButton, false);
//            }
            switchGroup((JRadioButton) e.getSource(), true);
            lastSelectedRadioButton = (JRadioButton) e.getSource();
        }
    }

    private void onLoadTests() {
        JFileChooser fc = new JFileChooser();

        if (!tfLoadTests.getText().isEmpty()) {
            fc.setSelectedFile(new File(tfLoadTests.getText()));
        }
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setMultiSelectionEnabled(true);
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File[] selected = fc.getSelectedFiles();
            if (selected.length > 0) {
                tfLoadTests.setText(selected[0].getAbsolutePath());
            }
            lastLoadedTests = new TestItemStorage[selected.length];
            for (int i = 0; i < selected.length; i++) {
                ObjectInputStream in;
                try {
                    in = new ObjectInputStream(new FileInputStream(selected[i]));
                    Object o = in.readObject();
                    if (o instanceof tests.TestItemStorage) {
                        lastLoadedTests[i]  = convertTests((tests.TestItemStorage)o);
                    } else if (o instanceof TestItemStorage) {
                        lastLoadedTests[i] = (TestItemStorage) o;
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Some internal error occurs.",
                                "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this,
                            "You choose wrong files. Please choose another",
                            "Error!", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Some internal error occurs.",
                            "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
            toTest = lastLoadedTests;
        }
    }

    public void onOk() {
        if (toTest == null || toTest.length == 0) {
            JOptionPane.showMessageDialog(this,
                    "No data loaded.",
                    "Warning!", JOptionPane.WARNING_MESSAGE);
            RESULT_OK = false;
        } else {
            RESULT_OK = true;
            dispose();

        }

    }

    public void onCancel() {
        RESULT_OK = false;
        dispose();
    }
}
