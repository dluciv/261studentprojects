/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * StatPanel.java
 *
 * Created on 10.11.2008, 0:29:06
 */
package j2se.g261.eda.automator.gui;

import j2se.g261.eda.automator.NoConditionsException;
import j2se.g261.eda.automator.parser.ParserException;
import j2se.g261.eda.automator.representations.nfa.NFAWalkerException;
import j2se.g261.eda.automator.tests.*;

import j2se.g261.eda.automator.tests.filters.ResultMatchingFilter;
import j2se.g261.eda.automator.tests.filters.ResultPatternFilter;
import j2se.g261.eda.automator.tests.filters.TimeCompareFilter;
import j2se.g261.eda.automator.tests.filters.TimeResultTypeObject;
import j2se.g261.eda.automator.util.Globals;
import j2se.g261.eda.automator.visualizing.dot.DotException;
import j2se.g261.eda.automator.visualizing.tex.TexWriter;
import j2se.g261.eda.testsmaker.MakeTestDialog;
import j2se.g261.eda.testsmaker.TestMakerDialog;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author nastya
 */
public class StatPanel extends javax.swing.JPanel implements ActionListener, ListSelectionListener {

    LoadDataDialog dialog;
    JFrame parentFrame;
    StatisticTable table;
    private JRadioButton lastSelectedRadioButton = null;
    private static final String MORE = ">";
    private static final String MORE_OR_EQUALS = ">=";
    private static final String EQUALS = "==";
    private static final String NOT_EQUALS = "<>";
    private Timer timer = null;

    /** Creates new form StatPanel */
    public StatPanel(JFrame parentFrame) {
        initComponents();
        cbFirst.addItem(TimeResultTypeObject.RESULT_NFA);
        cbFirst.addItem(TimeResultTypeObject.RESULT_DFA);
        cbFirst.addItem(TimeResultTypeObject.RESULT_MIN_DFA);
        cbFirst.addItem(TimeResultTypeObject.RESULT_TABLE);
        cbSecond.addItem(TimeResultTypeObject.RESULT_NFA);
        cbSecond.addItem(TimeResultTypeObject.RESULT_DFA);
        cbSecond.addItem(TimeResultTypeObject.RESULT_MIN_DFA);
        cbSecond.addItem(TimeResultTypeObject.RESULT_TABLE);
        cbSign.addItem(MORE);
        cbSign.addItem(MORE_OR_EQUALS);
        cbSign.addItem(EQUALS);
        cbSign.addItem(NOT_EQUALS);
        table = new StatisticTable();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(table, BorderLayout.CENTER);
        TestResultItemStorage st = new TestResultItemStorage();
        TestResultItem item1 = new TestResultItem("abc", "abcbcbc", false);
        item1.setDFA(new Result(false, 1, 1, 1));
        item1.setTable(new Result(false, 1, 1, 1));
        item1.setMinGraph(new Result(false, 1, 1, 1));
        item1.setNFA(new Result(false, 1, 1, 1));
        TestResultItem item2 = new TestResultItem("sd*", "sdddd", true);
        item2.setDFA(new Result(true, 1, 1, 1));
        item2.setTable(new Result(true, 1, 1, 1));
        item2.setMinGraph(new Result(true, 1, 1, 1));
        item2.setNFA(new Result(true, 1, 1, 1));
        st.addTestResult(item1);
        st.addTestResult(item2);
        st.setAdditionalInfo("Test test test");
        fillTable(new TestResultItemStorage[]{st});
        fillComputerInfo(new TestResultItemStorage[]{st});
        this.parentFrame = parentFrame;
        dialog = new LoadDataDialog(parentFrame, true);
        btnLoadData.addActionListener(this);
        rbFilterByPattern.addActionListener(this);
        rbFilterByResults.addActionListener(this);
        rbFilterByTime.addActionListener(this);
        rbFilterNone.addActionListener(this);
        rbAllOk.addActionListener(this);
        rbHaveWrong.addActionListener(this);
        patternList.addListSelectionListener(this);
        cbFirst.addActionListener(this);
        cbSecond.addActionListener(this);
        cbSign.addActionListener(this);
        btnSaveResults.addActionListener(this);
        saveAsDVI.addActionListener(this);
    }

    private TestResultItemStorage concatanate(TestResultItemStorage[] toTest) {
        TestResultItemStorage result = new TestResultItemStorage();

        for (TestResultItemStorage testResultItemStorage : toTest) {
            for (int i = 0; i < testResultItemStorage.size(); i++) {
                result.addTestResult(testResultItemStorage.getTestResult(i));
            }
        }

        return result;
    }

    private ItemFilter createCompareFilter() {
        if (cbFirst.getSelectedItem() == cbSecond.getSelectedItem()) {
            return null;
        }
        TimeCompareFilter.CompareType type;
        if (cbSign.getSelectedItem().equals(MORE)) {
            type = TimeCompareFilter.CompareType.MORE;
        } else if (cbSign.getSelectedItem().equals(MORE_OR_EQUALS)) {
            type = TimeCompareFilter.CompareType.MORE_AND_EQUALS;
        } else if (cbSign.getSelectedItem().equals(EQUALS)) {
            type = TimeCompareFilter.CompareType.EQUALS;
        } else {
            type = TimeCompareFilter.CompareType.NOT_EQUALS;
        }
        return new TimeCompareFilter(
                (TimeResultTypeObject) cbFirst.getSelectedItem(),
                type, (TimeResultTypeObject) cbSecond.getSelectedItem());
    }

    private ItemFilter createFilter(JRadioButton rb) {
        ItemFilter result = null;
        if (rb == rbFilterByResults) {
            if (rbAllOk.isSelected()) {
                result = new ResultMatchingFilter(true);
            } else {
                result = new ResultMatchingFilter(false);
            }
        } else if (rb == rbFilterByPattern) {
            result = createPatternFilter();
        } else if (rb == rbFilterByTime) {
            result = createCompareFilter();
        } else if (rb == rbAllOk) {
            result = new ResultMatchingFilter(true);
        } else if (rb == rbHaveWrong) {
            result = new ResultMatchingFilter(false);
        }
        return result;
    }

    private ItemFilter createPatternFilter() {
        if (patternList.getSelectedValue() != null) {
            return new ResultPatternFilter(String.valueOf(patternList.getSelectedValue()));
        }
        return null;
    }

    private void fillComputerInfo(TestResultItemStorage[] testResultItemStorage) {
        String info = "";
        Vector<String> infoParts = new Vector<String>();
        for (TestResultItemStorage testResultItemStorage1 : testResultItemStorage) {
            if (info.isEmpty()) {
                info += testResultItemStorage1.getAdditionalInfo();
            } else {
                if (!infoParts.contains(testResultItemStorage1.getAdditionalInfo())) {
                    info += "; " + testResultItemStorage1.getAdditionalInfo();
                    infoParts.add(testResultItemStorage1.getAdditionalInfo());
                }
            }
        }
        tfComputer.setText(info);
    }

    private void fillTable(TestResultItemStorage[] toTest) {
        TestResultItemStorage stor = concatanate(toTest);
        table.setData(stor);
        Vector<String> data = stor.getAllPatterns();
        ((DefaultListModel) patternList.getModel()).removeAllElements();
        for (String pattern : data) {
            ((DefaultListModel) patternList.getModel()).addElement(pattern);
        }
        table.updateTableUI();
        updateUI();
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        tfComputer = new javax.swing.JTextField();
        btnSaveResults = new javax.swing.JButton();
        statP = new javax.swing.JPanel();
        lbPercents = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbItems = new javax.swing.JLabel();
        progressTests = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        rbFilterByResults = new javax.swing.JRadioButton();
        rbAllOk = new javax.swing.JRadioButton();
        rbHaveWrong = new javax.swing.JRadioButton();
        rbFilterByTime = new javax.swing.JRadioButton();
        cbFirst = new javax.swing.JComboBox();
        cbSign = new javax.swing.JComboBox();
        cbSecond = new javax.swing.JComboBox();
        rbFilterByPattern = new javax.swing.JRadioButton();
        lbChoose = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        patternList = new javax.swing.JList();
        rbFilterNone = new javax.swing.JRadioButton();
        saveAsDVI = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnLoadData = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();

        btnSaveResults.setText("Save Results");

        statP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbPercents.setText("0/100");

        jLabel3.setText("%");

        jLabel4.setText("Items:");

        lbItems.setText("0/0");

        javax.swing.GroupLayout statPLayout = new javax.swing.GroupLayout(statP);
        statP.setLayout(statPLayout);
        statPLayout.setHorizontalGroup(
            statPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbPercents, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 631, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbItems, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(statPLayout.createSequentialGroup()
                .addComponent(progressTests, javax.swing.GroupLayout.DEFAULT_SIZE, 911, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );
        statPLayout.setVerticalGroup(
            statPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statPLayout.createSequentialGroup()
                .addGroup(statPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbItems, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(lbPercents, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressTests, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Additional info:");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Filter", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        buttonGroup3.add(rbFilterByResults);
        rbFilterByResults.setText("By results");

        buttonGroup2.add(rbAllOk);
        rbAllOk.setText("All ok");
        rbAllOk.setEnabled(false);

        buttonGroup2.add(rbHaveWrong);
        rbHaveWrong.setSelected(true);
        rbHaveWrong.setText("Have wrong result");
        rbHaveWrong.setEnabled(false);

        buttonGroup3.add(rbFilterByTime);
        rbFilterByTime.setText("By time");

        cbFirst.setModel(new DefaultComboBoxModel());
        cbFirst.setEnabled(false);

        cbSign.setEnabled(false);

        cbSecond.setEnabled(false);

        buttonGroup3.add(rbFilterByPattern);
        rbFilterByPattern.setText("By pattern");

        lbChoose.setText("Choose one:");
        lbChoose.setEnabled(false);

        patternList.setModel(new DefaultListModel());
        patternList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        patternList.setEnabled(false);
        jScrollPane3.setViewportView(patternList);

        buttonGroup3.add(rbFilterNone);
        rbFilterNone.setSelected(true);
        rbFilterNone.setText("None");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(cbFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbSign, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbSecond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rbFilterByResults)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbHaveWrong)
                            .addComponent(rbAllOk)))
                    .addComponent(rbFilterByTime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rbFilterByPattern)
                        .addGap(59, 59, 59)
                        .addComponent(rbFilterNone))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lbChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(87, 87, 87))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rbFilterByResults)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbAllOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbHaveWrong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbFilterByTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSign, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSecond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbFilterByPattern)
                            .addComponent(rbFilterNone))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbChoose)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)))
                .addContainerGap())
        );

        saveAsDVI.setText("Save as DVI");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18));
        jLabel1.setText("Results");

        btnLoadData.setText("Load data");

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 939, Short.MAX_VALUE)
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 209, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(467, 467, 467)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLoadData, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnSaveResults)
                                    .addGap(12, 12, 12)
                                    .addComponent(saveAsDVI, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tfComputer)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(statP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnLoadData)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfComputer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSaveResults)
                            .addComponent(saveAsDVI))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(statP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoadData;
    private javax.swing.JButton btnSaveResults;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox cbFirst;
    private javax.swing.JComboBox cbSecond;
    private javax.swing.JComboBox cbSign;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbChoose;
    private javax.swing.JLabel lbItems;
    private javax.swing.JLabel lbPercents;
    private javax.swing.JList patternList;
    private javax.swing.JProgressBar progressTests;
    private javax.swing.JRadioButton rbAllOk;
    private javax.swing.JRadioButton rbFilterByPattern;
    private javax.swing.JRadioButton rbFilterByResults;
    private javax.swing.JRadioButton rbFilterByTime;
    private javax.swing.JRadioButton rbFilterNone;
    private javax.swing.JRadioButton rbHaveWrong;
    private javax.swing.JButton saveAsDVI;
    private javax.swing.JPanel statP;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JTextField tfComputer;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e) {
        if (btnLoadData.equals(e.getSource())) {
            dialog.setVisible(true);
            if (dialog.RESULT_OK) {
                if (dialog.getToTest() instanceof TestItemStorage[]) {
                    rbFilterNone.setSelected(true);
                    switchFilters(lastSelectedRadioButton, false);
                    switchFilters(rbFilterNone, true);
                    Tester tester = new Tester((TestItemStorage[]) dialog.getToTest());
                    tester.setNumberOfMesures(dialog.getNumberOfMeasures());
                    tester.setCicle(dialog.getNumberOfCicles());
                    test(tester);
//                    Tester tester = new Tester(dialog.getToTest());
                } else if (dialog.getToTest() instanceof TestResultItemStorage[]) {
                    fillTable((TestResultItemStorage[]) dialog.getToTest());
                    fillComputerInfo((TestResultItemStorage[]) dialog.getToTest());
                }
            }
        }

        if (e.getSource() instanceof JRadioButton) {
            switchFilters(lastSelectedRadioButton, false);
            switchFilters((JRadioButton) e.getSource(), true);
            table.setFilter(createFilter((JRadioButton) e.getSource()));
            table.updateTableUI();
        }

        if (e.getSource() instanceof JComboBox) {
            table.setFilter(createCompareFilter());
        }

        if (e.getSource().equals(btnSaveResults)) {
            JFileChooser fc = new JFileChooser();
            fc.setApproveButtonText("Save");
            fc.setMultiSelectionEnabled(false);
//            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fc.setSelectedFile(new File("./results" + new Random().nextInt() + ".ser"));
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                if (fc.getSelectedFile().exists()) {
                    if (JOptionPane.showConfirmDialog(this, "Overwrite existing file?",
                            "Confirm Overwrite",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE) != JOptionPane.OK_OPTION) {
                        return;
                    }
                }
                System.out.println(fc.getSelectedFile());
                serializeResults(TestResultItemStorage.getDataForSerializing(table.getData()), fc.getSelectedFile());
            }

        }

        if (e.getSource().equals(saveAsDVI)) {
            JFileChooser fc = new JFileChooser();
            fc.setApproveButtonText("Save");
            fc.setMultiSelectionEnabled(false);
//            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fc.setSelectedFile(new File("./results_table" + new Random().nextInt() + ".dvi"));
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    if (fc.getSelectedFile().exists()) {
                        if (JOptionPane.showConfirmDialog(this, "Overwrite existing file?", "Confirm Overwrite", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.OK_OPTION) {
                            return;
                        }
                    }

                    File tex = saveResultsAsTex(TestResultItemStorage.getDataForSerializing(table.getData()));
                    System.out.println(Globals.LATEX_COMMAND);
                    Runtime.getRuntime().exec(Globals.LATEX_COMMAND + " " + tex);
                    String dvifile = tex.getName();
                    dvifile = dvifile.substring(0, dvifile.length() - 4) + ".dvi";
                    System.out.println(new File(dvifile).exists());
                    System.out.println(new File(dvifile).getAbsolutePath());
                    AutomPanel.copy(new File(dvifile), fc.getSelectedFile());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Error occurs during copying file", JOptionPane.ERROR_MESSAGE);

                }


            }
        }

    }

    private File saveResultsAsTex(TestResultItemStorage dataForSerializing) {
        File f = TexWriter.representateResultsAsTex(dataForSerializing);
        System.out.println("|||||||||||||||||||||||||||||||||||||");
        System.out.println(f);
        return f;
    }

    private void serializeResults(TestResultItemStorage dataForSerializing, File selectedFile) {
        dataForSerializing.setAdditionalInfo(tfComputer.getText());
        try {
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(selectedFile));
            out.writeObject(dataForSerializing);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MakeTestDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MakeTestDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void switchFilters(JRadioButton rb, boolean enable) {
        if (rb == rbFilterByPattern) {
            lbChoose.setEnabled(enable);
            patternList.setEnabled(enable);
        } else if (rb == rbFilterByResults) {
            rbAllOk.setEnabled(enable);
            rbHaveWrong.setEnabled(enable);
        } else if (rb == rbFilterByTime) {
            cbFirst.setEnabled(enable);
            cbSecond.setEnabled(enable);
            cbSign.setEnabled(enable);
        }
    }

    private void test(Tester tester) {
        timer = new Timer();
        TimerTask task = new TesterTask(tester, this);
        timer.schedule(task, 0, 500);
//        timer.start();
        try {
            tester.start();
        } catch (ParserException ex) {
            JOptionPane.showMessageDialog(this, "Wrong pattern",
                    "Attention!", JOptionPane.ERROR_MESSAGE);
//            timer.stop();
        } catch (NFAWalkerException ex) {
            JOptionPane.showMessageDialog(this, "Internal error during " +
                    "creating NFA walker", "Attention!", JOptionPane.ERROR_MESSAGE);
//            timer.stop();
        } catch (NoConditionsException ex) {
            JOptionPane.showMessageDialog(this, "Internal error during " +
                    "processing pattern", "Attention!", JOptionPane.ERROR_MESSAGE);
//            timer.stop();
        } catch (DotException ex) {
            JOptionPane.showMessageDialog(this, "Internal error during " +
                    "generation dot file", "Attention!", JOptionPane.ERROR_MESSAGE);
//            timer.stop();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Internal error during " +
                    "writing in TEMP directory", "Attention!", JOptionPane.ERROR_MESSAGE);
//            timer.stop();
        }
//        timer.stop();
//        timer.cancel();
        fillTable(new TestResultItemStorage[]{tester.getResults()});
        tfComputer.setText("");
        table.setFilter(null);
    }

    class TesterTask extends TimerTask {

        private Tester tester;
        private JPanel mainPanel;

        public TesterTask(Tester tester, JPanel mainPanel) {
            this.tester = tester;
            this.mainPanel = mainPanel;
        }

//        public void actionPerformed(ActionEvent e) {
//            System.out.println("&&&&&&&&&&&&");
//            System.out.println(tester.getCurrentPercent());
//            lbPercents.setText(tester.getCurrentPercent() + "/" + "100");
//            lbItems.setText(tester.getCurrentNumber() + "/" + tester.getAllFileSize());
//            progressTests.setValue((int) tester.getCurrentPercent());
//        }
        @Override
        public void run() {
            if (tester.getCurrentPercent() == 100l) {
                cancel();
            }
            System.out.println("&&&&&&&&&&&&");
            System.out.println(tester.getCurrentPercent());
            lbPercents.setText((int) tester.getCurrentPercent() + "/" + "100");
            lbItems.setText(tester.getCurrentNumber() + "/" + tester.getAllFileSize());
            progressTests.setValue((int) tester.getCurrentPercent());
            mainPanel.updateUI();
            updateStatPanel();
        }
    }

    private void updateStatPanel() {
        lbItems.updateUI();
        lbPercents.updateUI();
        progressTests.updateUI();
        statP.updateUI();
        updateUI();
    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource().equals(patternList)) {
            table.setFilter(createPatternFilter());
        }
    }
}
