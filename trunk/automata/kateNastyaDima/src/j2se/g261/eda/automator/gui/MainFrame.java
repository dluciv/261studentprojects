/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.gui;

import j2se.g261.eda.automator.Automator;
import j2se.g261.eda.automator.NoConditionsException;
import j2se.g261.eda.automator.dot.DotException;
import j2se.g261.eda.automator.graph.WalkerException;
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
public class MainFrame extends JFrame implements ActionListener {

//    private JButton btnSetAutomat;
//    private JButton btnExit;
//    private JButton btnShowGrap;
//    private JButton btnShowDetGraph;
//    private JButton btnShowTable;
//    private JTextField tfPattern;
//    private JTextField tfInputString;
//    private JButton btnVerify;
//    private ImageIcon iconOk = new ImageIcon("images/accepted.png");
//    private ImageIcon iconBad = new ImageIcon("images/cancel.png");
//    private ImageIcon iconError = new ImageIcon("images/cross.png");
//    private Automator automator = null;
//    private JLabel lbStatusTable;
//    private JLabel lbStatusGraph;
//    private JLabel lbTable;
//    private JLabel lbGraph;
//    private JPanel statusPanel;
//    private JPanel runtimePanel;
    private static final int M_WIDTH = 400;
    private static final int M_HEIGHT = 530;
//    private JPanel panel;
//    private JPanel pButtons;
//    private Process dviview = null;
//
//    public MainFrame() {
//        btnSetAutomat = new JButton("Create automator");
//        btnSetAutomat.addActionListener(this);
//        btnExit = new JButton("Exit");
//        btnExit.addActionListener(this);
//        btnVerify = new JButton("Verify");
//        btnVerify.addActionListener(this);
//        tfPattern = new JTextField();
//        tfPattern.setPreferredSize(new Dimension(140, 20));
//        tfInputString = new JTextField();
//        tfInputString.setPreferredSize(new Dimension(140, 20));
//
//        constructGUI();
//
//        this.setJMenuBar(createMenuBar());
//
////        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
////        int windowX = Math.max(0, (screen.width - M_WIDTH) / 2);
////        int windowY = Math.max(0, (screen.height - M_HEIGHT) / 2);
////        setLocation(windowX, windowY);
//
//    }
//
//    private Process runProgram(String command, File f) throws IOException {
//        Runtime r = Runtime.getRuntime();
//
//        if (Globals.COMMANDS_IN_ENV) {
//            return r.exec(command + " " + f.getAbsolutePath());
//        } else {
//            return r.exec(command + " " + f.getAbsolutePath());
//        }
//    }
//
//    private void constructStatusPanel() {
//        lbStatusTable = new JLabel(iconOk);
//        lbStatusTable.setToolTipText("String belongs to automator ");
//        lbStatusGraph = new JLabel(iconOk);
//        lbStatusGraph.setToolTipText("String belongs to automator ");
//        lbTable = new JLabel("<html><body><h2>Table</h2></body></html>");
//        lbTable.setBorder(new EmptyBorder(0, 0, 0, 70));
//        lbGraph = new JLabel("<html><body><h2>Graph</h2></body></html>");
//        lbGraph.setBorder(new EmptyBorder(0, 0, 0, 70));
//        statusPanel = new JPanel();
//        statusPanel.setLayout(new GridLayout(1, 2, 4, 20));
//        statusPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
//
//        JPanel p1 = new JPanel();
//        p1.setBorder(new BevelBorder(BevelBorder.RAISED));
//        p1.setLayout(new BorderLayout());
//        p1.add(lbStatusTable, BorderLayout.WEST);
//        p1.add(lbTable, BorderLayout.EAST);
//        JPanel p2 = new JPanel();
//        p2.setBorder(new BevelBorder(BevelBorder.RAISED));
//        p2.setLayout(new BorderLayout());
//        p2.add(lbStatusGraph, BorderLayout.WEST);
//        p2.add(lbGraph, BorderLayout.EAST);
//        statusPanel.add(p1);
//        statusPanel.add(p2);
//
//    }
//
//    private void constructGUI() {
//        this.getContentPane().setLayout(new BorderLayout());
//        createButtonPanel();
//        createMainPart();
//        constructStatusPanel();
//        createRuntimePanel();
//        this.getContentPane().add(panel, BorderLayout.NORTH);
//        JPanel center = new JPanel(new BorderLayout());
//        center.setBorder(new EmptyBorder(20, 10, 20, 10));
//        center.add(statusPanel, BorderLayout.NORTH);
//        center.add(runtimePanel, BorderLayout.SOUTH);
//        this.getContentPane().add(center, BorderLayout.CENTER);
//        this.getContentPane().add(pButtons, BorderLayout.SOUTH);
//
//    }
//
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource().equals(btnExit)) {
//            System.exit(0);
//        }
//        if (e.getSource().equals(btnVerify)) {
//            if (automator == null) {
//                JOptionPane.showMessageDialog(this,"Please, create automator first",
//                        "Error", JOptionPane.ERROR_MESSAGE, iconError);
//            } else {
//                if (automator.matchTable(tfInputString.getText())) {
//                    lbStatusTable.setIcon(iconOk);
//                    lbStatusTable.setToolTipText("String belongs to automator");
//                } else {
//                    lbStatusTable.setIcon(iconBad);
//                    lbStatusTable.setToolTipText("String does not belong to automator");
//                }
//                if (automator.matchNFAGraph(tfInputString.getText())) {
//                    lbStatusGraph.setIcon(iconOk);
//                    lbStatusGraph.setToolTipText("String belongs to automator");
//                } else {
//                    lbStatusGraph.setIcon(iconBad);
//                    lbStatusGraph.setToolTipText("String does not belong to automator");
//                }
//            }
//        }
//        if (e.getSource().equals(btnSetAutomat)) {
//            try {
//                automator = new Automator(tfPattern.getText());
//                automator.compile();
//            } catch (ParserException ex) {
//                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (WalkerException ex) {
//                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (NoConditionsException ex) {
//                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (DotException ex) {
//                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        if (e.getSource().equals(btnShowTable)) {
//            System.out.println("SHOW");
//            if (automator == null) {
//                JOptionPane.showMessageDialog(this, "Please, create automator first", 
//                        "Error", JOptionPane.ERROR_MESSAGE, iconError);
//                return;
//            }
//            if (dviview == null) {
//                try {
//                    Process p = runProgram(Globals.LATEX_COMMAND, automator.getTexFile());
//                    int c = 0;
//                    String dvifile = automator.getTexFile().getName();
//                    dvifile = dvifile.substring(0, dvifile.length() - 4) + ".dvi";
//                    dviview = runProgram(Globals.DVI_VIEWER, new File(dvifile));
//                } catch (IOException ex) {
//                JOptionPane.showMessageDialog(this, "interior error" +
//                        "", 
//                        "Error", JOptionPane.ERROR_MESSAGE, iconError);
//                }
//            } else {
//                System.out.println("NOT NULL");
//                dviview.destroy();
//                dviview = null;
//            }
//        }
//
//    }
    private JPanel northPanel;
//    private JPanel centerPanel;
    private JPanel buttonPanel;
    private JTextField tfPattern;
    private JTextField tfWord;
    private JButton btnSetAutomator;
    private JButton btnVerify;
    private JButton btnExit;
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

    public MainFrame() {
        processes = new Stack<Process>();
        createNorthPanel();
        createCenterPanel();
        createButtonPanel();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(northPanel, BorderLayout.NORTH);
        getContentPane().add(centerPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        this.setResizable(false);
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
        btnExit.addActionListener(this);
        btnCloseAll.addActionListener(this);
        btnVerify.setEnabled(false);
        btnSave.setEnabled(false);
        btnShow.setEnabled(false);
//        getContentPane().setLayout(new BorderLayout());
//        getContentPane().setLayout(new GridBagLayout());
//        GridBagConstraints c = new GridBagConstraints();
//        c.fill= GridBagConstraints.BOTH;
//        c.gridx = 0;
//        c.gridy = 0;
//        c.gridheight = 1;
//        JPanel np = new JPanel();
//        np.setPreferredSize(new Dimension(450, 280));
//        np.setLayout(new BorderLayout());
//        np.add(northPanel, BorderLayout.CENTER);
//        showPanel.setPreferredSize(new Dimension(450, 450));
//        
//        
//        getContentPane().add(np, c);
//        c.gridy = 1;
//        c.gridheight = 3;
//        getContentPane().add(tablePanel, c);
//        c.gridy = 4;
//        c.gridheight = 5;
//        getContentPane().add(showPanel, c);
//        c.gridy = 9;
//        c.gridheight = 1;
//        getContentPane().add(buttonPanel, c);
    }

    public static void main(String[] args) {
        MainFrame m = new MainFrame();
        m.setSize(M_WIDTH, M_HEIGHT);
        m.setVisible(true);

    }

    private void createButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonPanel.setLayout(new BorderLayout());
        btnExit = new JButton("Exit");
        buttonPanel.add(btnExit, BorderLayout.EAST);
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
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WalkerException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoConditionsException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DotException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
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

        if (e.getSource().equals(btnExit)) {
            dispose();
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
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showEpsNFAGraphRepresentation() {
        try {
            processes.push(Runtime.getRuntime().exec(Globals.DOT_VIEWR + " " + automator.getDotEpsNFAFile()));
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showMinimizedGraphRepresentation() {
        try {
            processes.push(Runtime.getRuntime().exec(Globals.DOT_VIEWR + " " + automator.getDotMinGraphFile()));
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showNFAGraphRepresentation() {
        try {
            processes.push(Runtime.getRuntime().exec(Globals.DOT_VIEWR + " " + automator.getDotNFAFile()));
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
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