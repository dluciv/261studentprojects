/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator;

import j2se.g261.eda.automator.parser.ParserException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.EtchedBorder;

/**
 *
 * @author nastya
 */
public class MainFrame extends JFrame implements ActionListener {

    private JButton btnSetAutomat;
    private JButton btnExit;
    private JTextField tfPattern;
    private JTextField tfInputString;
    private JButton btnVerify;
    private ImageIcon iconOk = new ImageIcon("images/accepted.png");
    private ImageIcon iconBad = new ImageIcon("images/cancel.png");
    private ImageIcon iconError = new ImageIcon("images/cross.png");
    private Automator automator = null;
    private JLabel lbStatus;
    private static final int M_WIDTH = 500;
    private static final int M_HEIGHT = 190;

    public MainFrame() {
        btnSetAutomat = new JButton("Задать автомат");
        btnSetAutomat.addActionListener(this);
        btnExit = new JButton("Выход");
        btnExit.addActionListener(this);
        btnVerify = new JButton("Проверить");
        btnVerify.addActionListener(this);
        tfPattern = new JTextField();
        tfPattern.setPreferredSize(new Dimension(140,20));
        tfInputString = new JTextField();
        tfInputString.setPreferredSize(new Dimension(140,20));

        constructGUI();
        
        this.setJMenuBar(createMenuBar());
 
//        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//        int windowX = Math.max(0, (screen.width - M_WIDTH) / 2);
//        int windowY = Math.max(0, (screen.height - M_HEIGHT) / 2);
//        setLocation(windowX, windowY);
        
    }

    private void constructGUI() {
        this.getContentPane().setLayout(new BorderLayout());

        JPanel pButtonsInner = new JPanel();
        pButtonsInner.add(btnExit);
        JPanel pButtons = new JPanel();
        pButtons.setLayout(new BorderLayout());
        pButtons.add(pButtonsInner, BorderLayout.EAST);
        getContentPane().add(pButtons, BorderLayout.SOUTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        JLabel lbPattern = new JLabel("Регулярное выражение");
        JLabel lbInput = new JLabel("Строка к проверке");
        System.out.println(iconOk);
        System.out.println(iconOk.getIconWidth());
        lbStatus = new JLabel(iconOk);
        lbStatus.setToolTipText("Строка принадлежит автомату");


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
        c.gridwidth = 1;
        panel.add(lbStatus, c);
        c.gridx = 9;
        c.gridwidth = 2;
        panel.add(btnVerify, c);

        this.getContentPane().add(panel, BorderLayout.CENTER);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnExit)){
            System.exit(0);
        }
        if(e.getSource().equals(btnVerify)){
            if(automator == null){
                JOptionPane.showMessageDialog(this, "Задайте сначала автомат", "Ошибка", JOptionPane.ERROR_MESSAGE, iconError);
            }else{
                if(automator.match(tfInputString.getText())){
                    lbStatus.setIcon(iconOk);
                    lbStatus.setToolTipText("Строка принадлежит автомату");
                }else{
                    lbStatus.setIcon(iconBad);
                    lbStatus.setToolTipText("Строка не принадлежит автомату");
                }
            }
        }
        if(e.getSource().equals(btnSetAutomat)){
            try {
                automator = new Automator(tfPattern.getText());
                automator.compile();
            } catch (ParserException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    public static void main(String[] args) {
        MainFrame m = new MainFrame();
        m.setSize(M_WIDTH, M_HEIGHT);
        m.setVisible(true);
        
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
}


class Help extends JFrame implements ActionListener{
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
    
    public void showHelp(){
        setSize(M_WIDTH, M_HEIGHT);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnExit)){
            dispose();
        }
    }

}
class About extends JFrame implements ActionListener{
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
    
    public void showAbout(){
        setSize(M_WIDTH, M_HEIGHT);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnExit)){
            dispose();
        }
    }

}