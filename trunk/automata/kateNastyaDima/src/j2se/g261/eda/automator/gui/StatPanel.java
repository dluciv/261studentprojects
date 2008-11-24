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

import j2se.g261.eda.automator.util.Globals;
import j2se.g261.eda.automator.visualizing.dot.DotException;
import j2se.g261.eda.automator.visualizing.tex.TexWriter;
import j2se.g261.eda.testsmaker.MakeTestDialog;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Timer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
    private Timer timer = null;
    FilterDialog filters;

    /** Creates new form StatPanel */
    public StatPanel(JFrame parentFrame) {
        initComponents();

        table = new StatisticTable();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(table, BorderLayout.CENTER);
//        TestResultItemStorage st = new TestResultItemStorage();
//        TestResultItem item1 = new TestResultItem("abc", "abcbcbc", false);
//        item1.setDFA(new Result(false, 1, 1, 1));
//        item1.setTable(new Result(false, 1, 1, 1));
//        item1.setMinGraph(new Result(false, 1, 1, 1));
//        item1.setNFA(new Result(false, 1, 1, 1));
//        TestResultItem item2 = new TestResultItem("sd*", "sdddd", true);
//        item2.setDFA(new Result(true, 1, 1, 1));
//        item2.setTable(new Result(true, 1, 1, 1));
//        item2.setMinGraph(new Result(true, 1, 1, 1));
//        item2.setNFA(new Result(true, 1, 1, 1));
//        st.addTestResult(item1);
//        st.addTestResult(item2);
//        st.setAdditionalInfo("Test test test");
//        fillTable(new TestResultItemStorage[]{st});
//        fillComputerInfo(new TestResultItemStorage[]{st});
        this.parentFrame = parentFrame;
        dialog = new LoadDataDialog(parentFrame, true);
        btnLoadData.addActionListener(this);
        btnSaveResults.addActionListener(this);
        saveAsDVI.addActionListener(this);
        btnFilter.addActionListener(this);
        filters = new FilterDialog(null, true, table, lbShowed);
        patternList.addListSelectionListener(this);
        cbIgnoreNulls.addActionListener(this);
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
        stor.countBandwidth();
        table.setData(stor);
        Vector<String> data = stor.getAllPatterns();
        ((DefaultListModel) filters.patternList.getModel()).removeAllElements();
        ((DefaultListModel) patternList.getModel()).removeAllElements();
        for (String pattern : data) {
            ((DefaultListModel) filters.patternList.getModel()).addElement(pattern);
            ((DefaultListModel) patternList.getModel()).addElement(pattern);
        }
        if (((DefaultListModel) patternList.getModel()).getSize() != 0) {
            patternList.setSelectedIndex(0);
        }
        table.updateTableUI();
        patternList.updateUI();
        updateShowLabel();
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
        jLabel2 = new javax.swing.JLabel();
        saveAsDVI = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnLoadData = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        btnFilter = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        patternList = new javax.swing.JList();
        tfBandwidth = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbIgnoreNulls = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        tfCountOfResults = new javax.swing.JTextField();
        lbShowed = new javax.swing.JLabel();

        btnSaveResults.setText("Save Results");

        jLabel2.setText("Additional info:");

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

        btnFilter.setText("Manage filters");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Bandwidths:"));

        patternList.setModel(new DefaultListModel());
        jScrollPane1.setViewportView(patternList);

        tfBandwidth.setEditable(false);

        jLabel3.setText("Pattern:");

        jLabel4.setText("Bandwidth:");

        cbIgnoreNulls.setSelected(true);
        cbIgnoreNulls.setText("Ignore nulls");

        jLabel5.setText("Count of results");

        tfCountOfResults.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbIgnoreNulls)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfBandwidth, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addComponent(tfCountOfResults, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(cbIgnoreNulls))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tfBandwidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(tfCountOfResults, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        lbShowed.setText("Show : 0 from 0");

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
                        .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(571, 571, 571)
                                .addComponent(jLabel2)
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnSaveResults, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(saveAsDVI, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2))
                                    .addComponent(tfComputer, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnLoadData, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addComponent(lbShowed)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfComputer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saveAsDVI)
                            .addComponent(btnSaveResults)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lbShowed)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFilter)
                            .addComponent(btnLoadData))))
                .addContainerGap(13, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnLoadData;
    private javax.swing.JButton btnSaveResults;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JCheckBox cbIgnoreNulls;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbShowed;
    private javax.swing.JList patternList;
    private javax.swing.JButton saveAsDVI;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JTextField tfBandwidth;
    private javax.swing.JTextField tfComputer;
    private javax.swing.JTextField tfCountOfResults;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e) {
        if (btnLoadData.equals(e.getSource())) {
            showLoadDataDialog();
        }

        if (e.getSource().equals(btnFilter)) {
            showFilter();
        }


        if (e.getSource().equals(btnSaveResults)) {
            serializeResults();

        }

        if (e.getSource().equals(saveAsDVI)) {
            saveResultAsDVI();
        }


        if (e.getSource().equals(cbIgnoreNulls)) {
            recalculateBandwidth();
        }
    }

    private void recalculateBandwidth() {
        TestResultItemStorage.IGNORE_NULL_TIME = cbIgnoreNulls.isSelected();

        if (table.getData() == null) {
            return;
        }
        table.getData().countBandwidth();
        showBandwidth();
    }

    private void saveResultAsDVI() {
        if (table.getData() == null) {
            return;
        }
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

    private File saveResultsAsTex(TestResultItemStorage dataForSerializing) {
        File f = TexWriter.representateResultsAsTex(dataForSerializing);
        System.out.println("|||||||||||||||||||||||||||||||||||||");
        System.out.println(f);
        return f;
    }

    private void serializeResults() {
        if (table.getData() == null) {
            return;
        }
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

    private void showBandwidth() {
        if (table.getData() == null || patternList.getSelectedValue() == null) {
            return;
        }
        double d = table.getData().getBandwidthByPattern(
                (String) patternList.getSelectedValue());
        String s = " B / sec";
        if(d > 1024*1024){
            d = d / (1024 * 1024);
            s = " MB / sec";
        }else if (d > 1024) {
            d = d / 1024;
            s = " kB / sec";
        }
        DecimalFormat f = new DecimalFormat("#.##");

        tfBandwidth.setText(f.format(d) + s);
        tfCountOfResults.setText(String.valueOf(
                table.getData().getCountOfItemsForPattern(
                (String) patternList.getSelectedValue())));
    }

    private void showFilter() {
        if (table.getData() == null) {
            return;
        }
        filters.setVisible(true);
    }

    private void showLoadDataDialog() {
        dialog.setVisible(true);
        if (dialog.RESULT_OK) {
            if (dialog.getToTest() instanceof TestItemStorage[]) {
                filters.rbFilterNone.setSelected(true);
//                    switchFilters(lastSelectedRadioButton, false);
//                    switchFilters(rbFilterNone, true);
                Tester tester = new Tester((TestItemStorage[]) dialog.getToTest());
                tester.setNumberOfMesures(dialog.getNumberOfMeasures());
                test(tester);
//                    Tester tester = new Tester(dialog.getToTest());
            } else if (dialog.getToTest() instanceof TestResultItemStorage[]) {
                fillTable((TestResultItemStorage[]) dialog.getToTest());
                fillComputerInfo((TestResultItemStorage[]) dialog.getToTest());
            }
        }
    }

    private void test(Tester tester) {
//        timer = new Timer();
//        TimerTask task = new TesterTask(tester, this);
//        timer.schedule(task, 0, 500);
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

    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource().equals(patternList)) {
            showBandwidth();
        }
    }

    public void updateShowLabel() {
        lbShowed.setText("Show " + table.getData().size() + " from " + table.getData().allSize());
        lbShowed.updateUI();
    }


}
