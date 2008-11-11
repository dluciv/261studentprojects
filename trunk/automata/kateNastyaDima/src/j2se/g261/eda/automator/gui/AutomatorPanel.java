/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.gui;

import j2se.g261.eda.automator.Automator;
import j2se.g261.eda.automator.NoConditionsException;
import j2se.g261.eda.automator.visualizing.dot.DotException;
import j2se.g261.eda.automator.representations.nfa.NFAWalkerException;
import j2se.g261.eda.automator.gui.AlgorithmTableData.AlgorithmResult;
import j2se.g261.eda.automator.gui.AlgorithmTableData.Result;
import j2se.g261.eda.automator.parser.ParserException;
import j2se.g261.eda.automator.util.Globals;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 *
 * @author nastya
 */
public class AutomatorPanel extends JPanel implements ActionListener {

    private JPanel northPanel;
    private JTextField tfPattern;
    private JTextField tfWord;
    private JButton btnSetAutomator;
    private JButton btnVerify;
    private JButton btnShow;
    private JButton btnSave;
    private JButton btnCloseAll;
    private JRadioButton rbMinimized;
    private JRadioButton rbNFA;
    private JRadioButton rbDFA;
    private JRadioButton rbEpsNFA;
    private JRadioButton rbTable;
    private AlgorithmTable tablePanel;
    private JPanel showPanel;
    private JPanel centerPanel;
    private Automator automator = null;
    private Stack<Process> processes;

    public AutomatorPanel() {
        processes = new Stack<Process>();
        createNorthPanel();
        createCenterPanel();
        setLayout(new BorderLayout());
        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        tablePanel.addSelectionLisener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                AlgorithmResult selected = tablePanel.getSelected();
                if (selected == tablePanel.ALGORITHM_DFA) {
                    rbDFA.setSelected(true);
                } else if (selected == tablePanel.ALGORITHM_NFA) {
                    rbNFA.setSelected(true);
                } else if (selected == tablePanel.ALGORITHM_MINIMIZATION) {
                    rbMinimized.setSelected(true);
                } else if (selected == tablePanel.ALGORITHM_TABLE) {
                    rbTable.setSelected(true);
                }
            }
        });
        rbMinimized.addActionListener(this);
        rbNFA.addActionListener(this);
        rbDFA.addActionListener(this);
        rbEpsNFA.addActionListener(this);
        rbTable.addActionListener(this);
        btnSetAutomator.addActionListener(this);
        btnVerify.addActionListener(this);
        btnShow.addActionListener(this);
        btnSave.addActionListener(this);
        btnCloseAll.addActionListener(this);
        btnVerify.setEnabled(false);
        btnSave.setEnabled(false);
        btnShow.setEnabled(false);
    }



    private void createCenterPanel() {
        //radio buttons creating
        rbMinimized = new JRadioButton("Minimized DFA");
        rbNFA = new JRadioButton("NFA");
        rbDFA = new JRadioButton("DFA");
        rbEpsNFA = new JRadioButton("Epsilon-NFA");
        rbTable = new JRadioButton("Table by NFA");
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbMinimized);
        bg.add(rbNFA);
        bg.add(rbDFA);
        bg.add(rbEpsNFA);
        bg.add(rbTable);
        JPanel radioPanel = new JPanel();
        radioPanel.setBorder(new TitledBorder(new EtchedBorder(), "Representations"));
//        radioPanel.setBorder(new EmptyBorder(10,10,10,10));
        radioPanel.setLayout(new GridLayout(5, 1, 4, 4));
        radioPanel.add(rbEpsNFA);
        radioPanel.add(rbNFA);
        radioPanel.add(rbTable);
        radioPanel.add(rbDFA);
        radioPanel.add(rbMinimized);
        //button panel creating
        btnShow = new JButton("Show");
//        btnShow.setMaximumSize(new Dimension(60, 30));
        btnSave = new JButton("Save");
        btnCloseAll = new JButton("Close windows");
//        btnSave.setPreferredSize(new Dimension(60, 30));
        JPanel btnInnerPanel = new JPanel();

        btnInnerPanel.setLayout(new GridLayout(3, 1, 4, 4));
        btnInnerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        btnInnerPanel.add(btnShow);
        btnInnerPanel.add(btnSave);
        btnInnerPanel.add(btnCloseAll);
        JPanel btnInnerPanel2 = new JPanel();
        btnInnerPanel2.setLayout(new BorderLayout());
        btnInnerPanel2.add(btnInnerPanel, BorderLayout.WEST);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BorderLayout());
        btnPanel.add(btnInnerPanel, BorderLayout.NORTH);
        //table creating
        tablePanel = new AlgorithmTable();

        JPanel showInnerPanel = new JPanel();
        showInnerPanel.setLayout(new GridBagLayout());
        showInnerPanel.setBorder(new EmptyBorder(20, 5, 10, 5));
        GridBagConstraints b = new GridBagConstraints();
        b.fill = GridBagConstraints.BOTH;
        b.insets = new Insets(4, 4, 4, 4);
        b.gridx = 0;
        b.gridy = 0;
        b.gridheight = 2;
        b.gridwidth = 8;
        showInnerPanel.add(radioPanel, b);
        b.fill = GridBagConstraints.NONE;
        b.gridx = 8;
        b.gridheight = 1;
        b.gridwidth = 1;
        showInnerPanel.add(btnPanel, b);

        showPanel = new JPanel();
        showPanel.setLayout(new BorderLayout());
        showPanel.add(showInnerPanel, BorderLayout.CENTER);

        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 1, 4, 4));
        centerPanel.add(tablePanel);
        centerPanel.add(showPanel);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Help");
        menu.add(new JMenuItem(new AbstractAction("Help") {

            public void actionPerformed(ActionEvent e) {
                new Help().showHelp();
            }
        }));
        menu.add(new JMenuItem(new AbstractAction("About") {

            public void actionPerformed(ActionEvent e) {
                new About().showAbout();
            }
        }));

        menuBar.add(menu);

        return menuBar;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(rbMinimized)) {
            tablePanel.setSelected(tablePanel.ALGORITHM_MINIMIZATION);
            tablePanel.updateUI();
        }
        if (e.getSource().equals(rbNFA)) {
            tablePanel.setSelected(tablePanel.ALGORITHM_NFA);
            tablePanel.updateUI();
        }
        if (e.getSource().equals(rbDFA)) {
            tablePanel.setSelected(tablePanel.ALGORITHM_DFA);
            tablePanel.updateUI();
        }
        if (e.getSource().equals(rbTable)) {
            tablePanel.setSelected(tablePanel.ALGORITHM_TABLE);
            tablePanel.updateUI();
        }

        if (e.getSource().equals(btnSetAutomator)) {
            try {
                automator = new Automator(tfPattern.getText());
                automator.compile();
                btnVerify.setEnabled(true);
                btnSave.setEnabled(true);
                btnShow.setEnabled(true);
            } catch (ParserException ex) {
                Logger.getLogger(AutomatorPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NFAWalkerException ex) {
                Logger.getLogger(AutomatorPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoConditionsException ex) {
                Logger.getLogger(AutomatorPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DotException ex) {
                Logger.getLogger(AutomatorPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AutomatorPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (e.getSource().equals(btnVerify)) {
            System.out.println(automator.matchNFAGraph(tfWord.getText()));
            tablePanel.ALGORITHM_DFA.setResult(automator.matchDFAGraph(tfWord.getText()) ? Result.PASSED : Result.NOT_PASSED);
            tablePanel.ALGORITHM_NFA.setResult(automator.matchNFAGraph(tfWord.getText()) ? Result.PASSED : Result.NOT_PASSED);
            tablePanel.ALGORITHM_TABLE.setResult(automator.matchTable(tfWord.getText()) ? Result.PASSED : Result.NOT_PASSED);
            tablePanel.ALGORITHM_MINIMIZATION.setResult(automator.matchMinGraph(tfWord.getText()) ? Result.PASSED : Result.NOT_PASSED);
            tablePanel.updateTableUI();
        }


        if (e.getSource().equals(btnShow)) {
            if (rbMinimized.isSelected()) {
                showMinimizedGraphRepresentation();
            } else if (rbNFA.isSelected()) {
                showNFAGraphRepresentation();
            } else if (rbDFA.isSelected()) {
                showDFAGraphRepresentation();
            } else if (rbEpsNFA.isSelected()) {
                showEpsNFAGraphRepresentation();
            } else if (rbTable.isSelected()) {
                showTableRepresentation();
            } else {
                JOptionPane.showMessageDialog(this, "Please choose type of representation first", "Attention!", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource().equals(btnCloseAll)) {
            while (!processes.empty()) {
                processes.pop().destroy();
            }
        }
        
        if(e.getSource().equals(btnSave)){
            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(false);
            if (rbMinimized.isSelected()) {
                int retVal = chooser.showSaveDialog(this);
//                if(retVal == JFileChooser.APPROVE_OPTION){
//                    chooser.getSelectedFile().delete();
//                    chooser.getSelectedFile().createNewFile();
//                    
//                }
            } else if (rbNFA.isSelected()) {
                int retVal = chooser.showSaveDialog(this);
            } else if (rbDFA.isSelected()) {
                int retVal = chooser.showSaveDialog(this);
            } else if (rbEpsNFA.isSelected()) {
                int retVal = chooser.showSaveDialog(this);
            } else if (rbTable.isSelected()) {
                int retVal = chooser.showSaveDialog(this);
            } else {
                JOptionPane.showMessageDialog(this, "Please choose type of representation first", "Attention!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void createNorthPanel() {
        northPanel = new JPanel();
//        northPanel.setBorder(new EtchedBorder());
        northPanel.setLayout(new GridBagLayout());

        tfPattern = new JTextField();
//        tfPattern.setPreferredSize(new Dimension(250, 20));
        tfWord = new JTextField();
//        tfWord.setPreferredSize(new Dimension(250, 20));
        btnVerify = new JButton("Verify");
        btnSetAutomator = new JButton("Set pattern");
//        btnVerify.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 4;
        northPanel.add(new JLabel("Regular Expression"), c);
        c.gridx = 7;
        northPanel.add(new JLabel("Word to be checked"), c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = 7;
        northPanel.add(tfPattern, c);
        c.gridx = 7;
        northPanel.add(tfWord, c);
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 3;
//        c.fill = GridBagConstraints.NONE;
        northPanel.add(btnSetAutomator, c);
        c.gridx = 7;
        c.gridwidth = 3;
        northPanel.add(btnVerify, c);
    }

    private void showDFAGraphRepresentation() {
        try {
            processes.push(Runtime.getRuntime().exec(Globals.DOT_VIEWR + " " + automator.getDotDFAFile()));
        } catch (IOException ex) {
            Logger.getLogger(AutomatorPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showEpsNFAGraphRepresentation() {
        try {
            processes.push(Runtime.getRuntime().exec(Globals.DOT_VIEWR + " " + automator.getDotEpsNFAFile()));
        } catch (IOException ex) {
            Logger.getLogger(AutomatorPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showMinimizedGraphRepresentation() {
    	String dotFileName;
    	String fileName;
        try {
        	System.out.println(automator.getDotMinGraphFile().getAbsolutePath());
        	dotFileName = automator.getDotMinGraphFile().getName();
        	fileName = dotFileName.substring(0, dotFileName.length() - 4);
        	Runtime.getRuntime().exec(Globals.DOT_VIEWR + " -Tgif " + 
        			automator.getDotMinGraphFile().getAbsolutePath() + " -o " +
        			fileName + ".gif");
    
        	//System.out.println(Globals.IMG_VIEWER + " " + fileName + ".gif");
        	//Process p = Runtime.getRuntime().exec(Globals.IMG_VIEWER + " " + fileName + ".gif");

        	
            processes.push(Runtime.getRuntime().exec(Globals.IMG_VIEWER + " " + fileName + ".gif"));
        } catch (IOException ex) {
            Logger.getLogger(AutomatorPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showNFAGraphRepresentation() {
        try {
            processes.push(Runtime.getRuntime().exec(Globals.DOT_VIEWR + " " + automator.getDotNFAFile()));
        } catch (IOException ex) {
            Logger.getLogger(AutomatorPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showTableRepresentation() {
        try {
            Process p = Runtime.getRuntime().exec(Globals.LATEX_COMMAND + " " + automator.getTexFile());
            String dvifile = automator.getTexFile().getName();
            dvifile = dvifile.substring(0, dvifile.length() - 4) + ".dvi";
            processes.add(Runtime.getRuntime().exec(Globals.DVI_VIEWER + " " + new File(dvifile)));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "interior error",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
class Help extends JFrame implements ActionListener {

    private static final int M_WIDTH = 120;
    private static final int M_HEIGHT = 100;
    private JButton btnExit;

    public Help() {
        JPanel p = new JPanel();
        p.add(new JLabel("Show"));
        getContentPane().add(p);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new Dimension(M_WIDTH, M_HEIGHT));
        int windowX = Math.max(0, (screen.width - M_WIDTH) / 2);
        int windowY = Math.max(0, (screen.height - M_HEIGHT) / 2);
        setLocation(windowX, windowY);
        JPanel pButtonsInner = new JPanel();
        btnExit = new JButton("Exit");
        btnExit.addActionListener(this);
        pButtonsInner.add(btnExit);
        JPanel pButtons = new JPanel();
        pButtons.setLayout(new BorderLayout());
        pButtons.add(pButtonsInner, BorderLayout.EAST);
        getContentPane().add(pButtons, BorderLayout.SOUTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void showHelp() {
        setSize(M_WIDTH, M_HEIGHT);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnExit)) {
            dispose();
        }
    }
}

class About extends JFrame implements ActionListener {

    private static final int M_WIDTH = 300;
    private static final int M_HEIGHT = 170;
    private JButton btnExit;

    public About() {
        JPanel p = new JPanel();
        JTextArea area = new JTextArea();
        area.append("Authors \n\n");
        area.append("  Volkova E. V. \n");
        area.append("  Solodkaya A. S. \n");
        area.append("  Kolyanov D. A. ");

        area.setEditable(false);
        area.setBackground(this.getContentPane().getBackground());
        area.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        p.add(area);
        getContentPane().add(p);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new Dimension(M_WIDTH, M_HEIGHT));
        int windowX = Math.max(0, (screen.width - M_WIDTH) / 2);
        int windowY = Math.max(0, (screen.height - M_HEIGHT) / 2);
        setLocation(windowX, windowY);
        JPanel pButtonsInner = new JPanel();
        btnExit = new JButton("Закрыть");
        btnExit.addActionListener(this);
        pButtonsInner.add(btnExit);
        JPanel pButtons = new JPanel();
        pButtons.setLayout(new BorderLayout());
        pButtons.add(pButtonsInner, BorderLayout.EAST);
        getContentPane().add(pButtons, BorderLayout.SOUTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void showAbout() {
        setSize(M_WIDTH, M_HEIGHT);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnExit)) {
            dispose();
        }
    }
}