/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author nastya
 */
public class MainFrame extends JFrame implements ActionListener {

    private static final int M_WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.95);
    private static final int M_HEIGHT =(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.95);;
    private JPanel buttonPanel;
    private JButton btnExit;
    private PropertiesDialog propDialog;

    public MainFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setResizable(false);
        propDialog = new PropertiesDialog(MainFrame.this);
        propDialog.loadProperties();
        createButtonPanel();
        btnExit.addActionListener(this);
        getContentPane().setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Automator", new AutomPanel());
        tabbedPane.add("Statistics", new StatPanel(this));
        getContentPane().add(tabbedPane, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu file = new JMenu("File");
        JMenuItem showProp = new JMenuItem(new AbstractAction("Settings"){

            public void actionPerformed(ActionEvent e) {
                
                propDialog.setVisible(true);
            }
        });
        file.add(showProp);
        menuBar.add(file);

    }

    public static void main(String[] args) {
        MainFrame m = new MainFrame();
        m.setSize(M_WIDTH, M_HEIGHT);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - M_WIDTH) / 2;
        int y = (screenSize.height - M_HEIGHT) / 2;
        m.setLocation(x, y);
        m.setVisible(true);

    }

    private void createButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonPanel.setLayout(new BorderLayout());
        btnExit = new JButton("Exit");
        buttonPanel.add(btnExit, BorderLayout.EAST);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnExit)) {
            dispose();
        }
    }
}
