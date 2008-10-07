/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator;

import j2se.g261.eda.automator.dot.DotException;
import j2se.g261.eda.automator.graph.WalkerException;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author nastya
 */
public class MainFrame extends JFrame implements ActionListener {

    private JButton btnSetAutomat;
    private JButton btnExit;
    private JButton btnShowGrap;
    private JButton btnShowDetGraph;
    private JButton btnShowTable;
    private JTextField tfPattern;
    private JTextField tfInputString;
    private JButton btnVerify;
    private ImageIcon iconOk = new ImageIcon("images/accepted.png");
    private ImageIcon iconBad = new ImageIcon("images/cancel.png");
    private ImageIcon iconError = new ImageIcon("images/cross.png");
    private Automator automator = null;
    private JLabel lbStatusTable;
    private JLabel lbStatusGraph;
    private JLabel lbTable;
    private JLabel lbGraph;
    private JPanel statusPanel;
    private JPanel runtimePanel;
    private static final int M_WIDTH = 500;
    private static final int M_HEIGHT = 450;
    private JPanel panel;
    private JPanel pButtons;
    private Process dviview = null;

    public MainFrame() {
        btnSetAutomat = new JButton("Задать автомат");
        btnSetAutomat.addActionListener(this);
        btnExit = new JButton("Выход");
        btnExit.addActionListener(this);
        btnVerify = new JButton("Проверить");
        btnVerify.addActionListener(this);
        tfPattern = new JTextField();
        tfPattern.setPreferredSize(new Dimension(140, 20));
        tfInputString = new JTextField();
        tfInputString.setPreferredSize(new Dimension(140, 20));

        constructGUI();

        this.setJMenuBar(createMenuBar());

//        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//        int windowX = Math.max(0, (screen.width - M_WIDTH) / 2);
//        int windowY = Math.max(0, (screen.height - M_HEIGHT) / 2);
//        setLocation(windowX, windowY);

    }

    private Process runProgram(String command, File f) throws IOException {
        System.out.println("run: " + command); 
        Runtime r = Runtime.getRuntime();

        if (Globals.COMMANDS_IN_ENV) {
            return r.exec(command + " " + f.getAbsolutePath());
        } else {
            return r.exec(command + " " + f.getAbsolutePath());
        }
    }

    private void constructStatusPanel() {
        lbStatusTable = new JLabel(iconOk);
        lbStatusTable.setToolTipText("Строка принадлежит автомату");
        lbStatusGraph = new JLabel(iconOk);
        lbStatusGraph.setToolTipText("Строка принадлежит автомату");
        lbTable = new JLabel("<html><body><h2>Таблица</h2></body></html>");
        lbTable.setBorder(new EmptyBorder(0, 0, 0, 70));
        lbGraph = new JLabel("<html><body><h2>Граф</h2></body></html>");
        lbGraph.setBorder(new EmptyBorder(0, 0, 0, 70));
        statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(1, 2, 4, 20));
        statusPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));

        JPanel p1 = new JPanel();
        p1.setBorder(new BevelBorder(BevelBorder.RAISED));
        p1.setLayout(new BorderLayout());
        p1.add(lbStatusTable, BorderLayout.WEST);
        p1.add(lbTable, BorderLayout.EAST);
        JPanel p2 = new JPanel();
        p2.setBorder(new BevelBorder(BevelBorder.RAISED));
        p2.setLayout(new BorderLayout());
        p2.add(lbStatusGraph, BorderLayout.WEST);
        p2.add(lbGraph, BorderLayout.EAST);
        statusPanel.add(p1);
        statusPanel.add(p2);

    }

    private void constructGUI() {
        this.getContentPane().setLayout(new BorderLayout());
        createButtonPanel();
        createMainPart();
        constructStatusPanel();
        createRuntimePanel();
        this.getContentPane().add(panel, BorderLayout.NORTH);
        JPanel center = new JPanel(new BorderLayout());
        center.setBorder(new EmptyBorder(20, 10, 20, 10));
        center.add(statusPanel, BorderLayout.NORTH);
        center.add(runtimePanel, BorderLayout.SOUTH);
        this.getContentPane().add(center, BorderLayout.CENTER);
        this.getContentPane().add(pButtons, BorderLayout.SOUTH);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnExit)) {
            System.exit(0);
        }
        if (e.getSource().equals(btnVerify)) {
            if (automator == null) {
                JOptionPane.showMessageDialog(this, "Задайте сначала автомат",
                        "Ошибка", JOptionPane.ERROR_MESSAGE, iconError);
            } else {
                if (automator.matchTable(tfInputString.getText())) {
                    lbStatusTable.setIcon(iconOk);
                    lbStatusTable.setToolTipText("Строка принадлежит автомату");
                } else {
                    lbStatusTable.setIcon(iconBad);
                    lbStatusTable.setToolTipText("Строка не принадлежит автомату");
                }
                if (automator.matchGraph(tfInputString.getText())) {
                    lbStatusGraph.setIcon(iconOk);
                    lbStatusGraph.setToolTipText("Строка принадлежит автомату");
                } else {
                    lbStatusGraph.setIcon(iconBad);
                    lbStatusGraph.setToolTipText("Строка не принадлежит автомату");
                }
            }
        }
        if (e.getSource().equals(btnSetAutomat)) {
            try {
                automator = new Automator(tfPattern.getText());
                automator.compile();
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

        if (e.getSource().equals(btnShowTable)) {
            System.out.println("SHOW");
            if (automator == null) {
                JOptionPane.showMessageDialog(this, "Задайте сначала автомат", 
                        "Ошибка", JOptionPane.ERROR_MESSAGE, iconError);
                return;
            }
            if (dviview == null) {
                System.out.println("NULL");
                try {
                    Process p = runProgram(Globals.LATEX_COMMAND, automator.getTexFile());
                    int c = 0;
                    String dvifile = automator.getTexFile().getName();
                    dvifile = dvifile.substring(0, dvifile.length() - 4) + ".dvi";
                    dviview = runProgram(Globals.DVI_VIEWER, new File(dvifile));
                } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Внутренняя ошибка(" +
                        "", 
                        "Ошибка", JOptionPane.ERROR_MESSAGE, iconError);
                }
            } else {
                System.out.println("NOT NULL");
                dviview.destroy();
                dviview = null;
            }
        }

    }

    public static void main(String[] args) {
        MainFrame m = new MainFrame();
        m.setSize(M_WIDTH, M_HEIGHT);
        m.setVisible(true);

    }

    private void createButtonPanel() {
        JPanel pButtonsInner = new JPanel();
        pButtonsInner.add(btnExit);
        pButtons = new JPanel();
        pButtons.setLayout(new BorderLayout());
        pButtons.add(pButtonsInner, BorderLayout.EAST);

    }

    private void createMainPart() {


        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        JLabel lbPattern = new JLabel("Регулярное выражение");
        JLabel lbInput = new JLabel("Строка к проверке");
        System.out.println(iconOk);
        System.out.println(iconOk.getIconWidth());


        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        panel.add(lbPattern, c);
        c.gridx = 4;
        c.gridwidth = 4;
        panel.add(tfPattern, c);
        c.gridx = 8;
        c.gridwidth = 3;
        panel.add(btnSetAutomat, c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 4;
        panel.add(lbInput, c);
        c.gridx = 4;
        c.gridwidth = 4;
        panel.add(tfInputString, c);
        c.gridx = 8;
        c.gridwidth = 3;
        panel.add(btnVerify, c);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Помощь");
        menu.add(new JMenuItem(new AbstractAction("Помощь") {

            public void actionPerformed(ActionEvent e) {
                new Help().showHelp();
            }
        }));
        menu.add(new JMenuItem(new AbstractAction("О программе") {

            public void actionPerformed(ActionEvent e) {
                new About().showAbout();
            }
        }));

        menuBar.add(menu);

        return menuBar;
    }

    private void createRuntimePanel() {
        runtimePanel = new JPanel();
        runtimePanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED), "Показать"));
        runtimePanel.setLayout(new GridLayout(1, 3, 4, 50));
        btnShowGrap = new JButton("eps-NFA");
        btnShowGrap.addActionListener(this);
//        btnShowGrap.setBorder(new EmptyBorder(10, 5, 10, 5));
        btnShowDetGraph = new JButton("DFA");
        btnShowDetGraph.addActionListener(this);
//        btnShowDetGraph.setBorder(new EmptyBorder(10, 5, 10, 5));
        btnShowTable = new JButton("NFA-TABLE");
        btnShowTable.addActionListener(this);
//        btnShowTable.setBorder(new EmptyBorder(10, 5,/ 10, 5));
        runtimePanel.add(btnShowGrap);
        runtimePanel.add(btnShowDetGraph);
        runtimePanel.add(btnShowTable);
    }
}

class Help extends JFrame implements ActionListener {

    private static final int M_WIDTH = 120;
    private static final int M_HEIGHT = 100;
    private JButton btnExit;

    public Help() {
        JPanel p = new JPanel();
        p.add(new JLabel("Помощь"));
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
        area.append("Над програмой работали: \n\n");
        area.append("  Волкова Екатерина \n");
        area.append("  Солодкая Анастасия \n");
        area.append("  Колянов Дмитрий ");

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