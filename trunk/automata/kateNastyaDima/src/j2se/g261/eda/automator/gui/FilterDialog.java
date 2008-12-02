/*
 * FilterDialog.java
 *
 * Created on 24 Ноябрь 2008 г., 3:45
 */
package j2se.g261.eda.automator.gui;

import j2se.g261.eda.automator.tests.ItemFilter;
import j2se.g261.eda.automator.tests.filters.DevationFilter;
import j2se.g261.eda.automator.tests.filters.ResultMatchingFilter;
import j2se.g261.eda.automator.tests.filters.ResultPatternFilter;
import j2se.g261.eda.automator.tests.filters.TimeCompareFilter;
import j2se.g261.eda.automator.tests.filters.TimeResultTypeObject;
import j2se.g261.eda.automator.tests.filters.ValueFilter;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author  nastya
 */
public class FilterDialog extends javax.swing.JDialog implements ActionListener, ListSelectionListener {

    private static final String MORE = ">";
    private static final String LESS = "<";
    private static final String MORE_OR_EQUALS = ">=";
    private static final String EQUALS = "==";
    private static final String NOT_EQUALS = "<>";
    StatisticTable table;
    private JRadioButton lastSelectedRadioButton = null;
    private JLabel lbShowed;

    /** Creates new form FilterDialog */
    public FilterDialog(java.awt.Frame parent, boolean modal, StatisticTable table, JLabel lbShowed) {
        super(parent, modal);
        this.table = table;
        this.lbShowed = lbShowed;
        initComponents();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
        cbGraphType.addItem(TimeResultTypeObject.RESULT_NFA);
        cbGraphType.addItem(TimeResultTypeObject.RESULT_DFA);
        cbGraphType.addItem(TimeResultTypeObject.RESULT_MIN_DFA);
        cbGraphType.addItem(TimeResultTypeObject.RESULT_TABLE);
        cbGraphType.addItem(TimeResultTypeObject.RESULT_ALL);
        cbGraphType.addItem(TimeResultTypeObject.RESULT_SOME);
        
        cbRes.addItem(DevationFilter.ResultType.MIN);
        cbRes.addItem(DevationFilter.ResultType.MAX);
        cbRes.addItem(DevationFilter.ResultType.BOTH);
        cbRes.addItem(DevationFilter.ResultType.SOME);
        
        cbGraphType1.addItem(TimeResultTypeObject.RESULT_NFA);
        cbGraphType1.addItem(TimeResultTypeObject.RESULT_DFA);
        cbGraphType1.addItem(TimeResultTypeObject.RESULT_MIN_DFA);
        cbGraphType1.addItem(TimeResultTypeObject.RESULT_TABLE);
        cbGraphType1.addItem(TimeResultTypeObject.RESULT_ALL);
        cbGraphType1.addItem(TimeResultTypeObject.RESULT_SOME);
        
        cbDevation.addItem(MORE);
        cbDevation.addItem(EQUALS);
        cbDevation.addItem(LESS);

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
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        rbFilterByPattern.addActionListener(this);
        rbFilterByResults.addActionListener(this);
        rbFilterByTime.addActionListener(this);
        rbFilterNone.addActionListener(this);
        rbValues.addActionListener(this);
        rbDeviation.addActionListener(this);
        rbAllOk.addActionListener(this);
        rbHaveWrong.addActionListener(this);
        patternList.addListSelectionListener(this);
        cbFirst.addActionListener(this);
        cbSecond.addActionListener(this);
        cbSign.addActionListener(this);
        btnApply.addActionListener(this);
        btnApplyDev.addActionListener(this);
        switchFilters(rbValues, false);
        switchFilters(rbDeviation, false);
        switchFilters(rbFilterByPattern, false);
        switchFilters(rbFilterByResults, false);
        switchFilters(rbFilterByTime, false);
        switchFilters(rbFilterNone, true);
        lastSelectedRadioButton = rbFilterNone;
    }

    public void updateShowLabel() {
        lbShowed.setText("Show " + table.getData().size() + " from " + table.getData().allSize());
        lbShowed.updateUI();
    }

    private ItemFilter createDeviationFilter() {
        DevationFilter.CompareType compareType = DevationFilter.CompareType.MORE;
        if(cbDevation.getSelectedItem() == MORE){
            compareType = DevationFilter.CompareType.MORE;
        }else if(cbDevation.getSelectedItem() == LESS){
            compareType = DevationFilter.CompareType.LESS;
        }else if(cbDevation.getSelectedItem() == EQUALS){
            compareType = DevationFilter.CompareType.EQUALS;
        }
        
        return new DevationFilter(slDeviation.getValue() / 100, (TimeResultTypeObject)cbGraphType1.getSelectedItem(), compareType, (DevationFilter.ResultType)cbRes.getSelectedItem());
    }

    private ItemFilter createValueFilter() {
        long min = -1l;
        long max = Long.MAX_VALUE;
        if(!tfMax.getText().isEmpty()){
            try{
                max = Long.parseLong(tfMax.getText());
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(this,"Please enter correct " +
                        "number values or remain fields empty",
                        "Error!", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        
        if(!tfMin.getText().isEmpty()){
            try{
                min = Long.parseLong(tfMin.getText());
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(this,"Please enter correct " +
                        "number values or remain fields empty",
                        "Error!", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        
        if(min > max){
                JOptionPane.showMessageDialog(this,"Max-value field value less then Min-field value",
                        "Warning!", JOptionPane.WARNING_MESSAGE);
        }
        
        
            return new ValueFilter(min, max,
                    (TimeResultTypeObject) cbGraphType.getSelectedItem());
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
        } else if(rb == rbValues){
            tfMax.setEnabled(enable);
            tfMin.setEnabled(enable);
            cbGraphType.setEnabled(enable);
        
        } else if(rb == rbDeviation){
            slDeviation.setEnabled(enable);
            cbGraphType1.setEnabled(enable);
            cbDevation.setEnabled(enable);
            btnApplyDev.setEnabled(enable);
        }
    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource().equals(patternList)) {
            table.setFilter(createPatternFilter());
            updateShowLabel();
        }
    }

    private ItemFilter createPatternFilter() {
        if (patternList.getSelectedValue() != null) {
            return new ResultPatternFilter(String.valueOf(patternList.getSelectedValue()));
        }
        return null;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
       // bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
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
        rbValues = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        cbGraphType = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfMin = new javax.swing.JTextField();
        tfMax = new javax.swing.JTextField();
        btnApply = new javax.swing.JButton();
        rbDeviation = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        slDeviation = new javax.swing.JSlider();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnApplyDev = new javax.swing.JButton();
        cbDevation = new javax.swing.JComboBox();
        cbGraphType1 = new javax.swing.JComboBox();
        cbRes = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Filter", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        buttonGroup1.add(rbFilterByResults);
        rbFilterByResults.setText("By results");

        buttonGroup2.add(rbAllOk);
        rbAllOk.setText("All ok");
        rbAllOk.setEnabled(false);

        buttonGroup2.add(rbHaveWrong);
        rbHaveWrong.setSelected(true);
        rbHaveWrong.setText("Have wrong result");
        rbHaveWrong.setEnabled(false);

        buttonGroup1.add(rbFilterByTime);
        rbFilterByTime.setText("By time");

        cbFirst.setModel(new DefaultComboBoxModel());
        cbFirst.setEnabled(false);

        cbSign.setEnabled(false);

        cbSecond.setEnabled(false);

        buttonGroup1.add(rbFilterByPattern);
        rbFilterByPattern.setText("By pattern");

        lbChoose.setText("Choose one:");
        lbChoose.setEnabled(false);

        patternList.setModel(new DefaultListModel());
        patternList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        patternList.setEnabled(false);
        jScrollPane3.setViewportView(patternList);

        buttonGroup1.add(rbFilterNone);
        rbFilterNone.setSelected(true);
        rbFilterNone.setText("None");

        buttonGroup1.add(rbValues);
        rbValues.setText("By value's rang");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("<=");

        jLabel2.setText("<=");

        btnApply.setText("Apply");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfMin, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbGraphType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfMax, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnApply)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbGraphType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(tfMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnApply))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonGroup1.add(rbDeviation);
        rbDeviation.setText("By deviation");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        slDeviation.setMaximum(1000);
        slDeviation.setPaintLabels(true);
        slDeviation.setToolTipText("Choose value in percents");
        slDeviation.setValue(30);

        //org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, slDeviation, org.jdesktop.beansbinding.ELProperty.create("${value}"), jTextField1, org.jdesktop.beansbinding.BeanProperty.create("text"));
        //bindingGroup.addBinding(binding);

        jLabel3.setText("%");

        btnApplyDev.setText("Apply");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(cbGraphType1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cbDevation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(197, 197, 197)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                .addComponent(jLabel3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(slDeviation, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(253, Short.MAX_VALUE)
                        .addComponent(btnApplyDev)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(slDeviation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDevation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbGraphType1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(btnApplyDev)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbFilterNone)
                            .addGroup(jPanel3Layout.createSequentialGroup()
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
                                    .addComponent(rbFilterByTime)
                                    .addComponent(rbDeviation))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 233, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rbValues)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rbFilterByPattern, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lbChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbFilterNone)
                            .addComponent(rbFilterByPattern))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbFilterByResults)
                            .addComponent(lbChoose))
                        .addGap(3, 3, 3)
                        .addComponent(rbAllOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbHaveWrong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbFilterByTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSign, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSecond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(rbDeviation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbValues)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        //bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//                FilterDialog dialog = new FilterDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnApplyDev;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox cbDevation;
    private javax.swing.JComboBox cbFirst;
    private javax.swing.JComboBox cbGraphType;
    private javax.swing.JComboBox cbGraphType1;
    private javax.swing.JComboBox cbRes;
    private javax.swing.JComboBox cbSecond;
    private javax.swing.JComboBox cbSign;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbChoose;
    javax.swing.JList patternList;
    private javax.swing.JRadioButton rbAllOk;
    private javax.swing.JRadioButton rbDeviation;
    private javax.swing.JRadioButton rbFilterByPattern;
    private javax.swing.JRadioButton rbFilterByResults;
    private javax.swing.JRadioButton rbFilterByTime;
    javax.swing.JRadioButton rbFilterNone;
    private javax.swing.JRadioButton rbHaveWrong;
    private javax.swing.JRadioButton rbValues;
    private javax.swing.JSlider slDeviation;
    private javax.swing.JTextField tfMax;
    private javax.swing.JTextField tfMin;
   // private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JRadioButton) {
            if (!(e.getSource() == rbAllOk || e.getSource() == rbHaveWrong)) {
                switchFilters(lastSelectedRadioButton, false);
                switchFilters((JRadioButton) e.getSource(), true);
                lastSelectedRadioButton = (JRadioButton) e.getSource();
            }

            if(e.getSource().equals(rbValues) || e.getSource().equals(rbDeviation)) return;
            table.setFilter(createFilter((JRadioButton) e.getSource()));
            updateShowLabel();
            table.updateTableUI();
        }
        if (e.getSource() instanceof JComboBox) {
            if(e.getSource().equals(cbGraphType)){
                return;
            }else{
            table.setFilter(createCompareFilter());
            }
            updateShowLabel();
        }
        
        if(e.getSource().equals(btnApply)){
            table.setFilter(createValueFilter());
        }
        if(e.getSource().equals(btnApplyDev)){
            table.setFilter(createDeviationFilter());
        }
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
        }else if(rb == rbValues){
        }
        return result;
    }
}
