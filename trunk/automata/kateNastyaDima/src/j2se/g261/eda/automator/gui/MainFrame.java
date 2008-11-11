/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author nastya
 */
public class MainFrame extends JFrame implements ActionListener {

    private static final int M_WIDTH = 400;
    private static final int M_HEIGHT = 530;
    private JPanel buttonPanel;
    private JButton btnExit;

    public MainFrame() {
//        this.setResizable(false);
        createButtonPanel();
        btnExit.addActionListener(this);
        getContentPane().setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Automator", new AutomPanel());
        tabbedPane.add("Statistics", new StatPanel());
        getContentPane().add(tabbedPane, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnExit)) {
            dispose();
        }
    }
}
