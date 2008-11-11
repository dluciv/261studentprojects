/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author nastya
 */
public class StatisticsPanel extends JPanel{

    private JRadioButton rbLoadTests;
    private JRadioButton rbLoadResults;
    private JRadioButton rbGenerate;
    private JRadioButton rbCreate;
            
    private JTextField tfTestsPath;
    private JButton btnLoadTests;
    private JButton btnCreateTests;
    private JTextField tfPatternForRandomGenerating;
    private JButton btnGenerate;
    private JFormattedTextField tfGenerateTests;
    private JTextField tfResultsPath;
    private JButton btnLoadResults;
    
    
    
    public StatisticsPanel() {
        setLayout(new BorderLayout());
        add(createLoadPanel(), BorderLayout.NORTH);
    }
    
    JPanel createLoadPanel(){
        JPanel panel = new JPanel();

        JPanel p1 = new JPanel();
        p1.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        rbLoadTests = new JRadioButton("Load tests");
        rbLoadTests.setHorizontalAlignment(SwingConstants.LEFT);
        tfTestsPath = new JTextField();
        tfTestsPath.setEditable(false);
        btnLoadTests = new JButton("Load");
        p1.add(rbLoadTests, c);
        c.gridy = 1;
        c.gridwidth = 2;
        p1.add(tfTestsPath, c);
        c.gridx = 2;
        c.gridwidth = 1;
        p1.add(btnLoadTests, c);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        rbLoadResults = new JRadioButton("Load Results");
        tfResultsPath= new JTextField();
        tfResultsPath.setEditable(false);
        btnLoadResults = new JButton("Load");
        p2.add(rbLoadResults, c);
        c.gridy = 1;
        c.gridwidth = 2;
        p1.add(tfResultsPath, c);
        c.gridx = 2;
        c.gridwidth = 1;
        p2.add(btnLoadResults, c);
        
        JPanel p3 = new JPanel();
        p3.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        rbCreate = new JRadioButton("Create tests");
        btnCreateTests = new JButton("Create");
        p3.add(rbCreate, c);
        c.gridy = 1;
        c.gridwidth = 1;
        p3.add(btnCreateTests, c);

        JPanel p4 = new JPanel();
        p4.setLayout(new GridBagLayout());
        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 3;
        rbGenerate = new JRadioButton("Generate random tests");
        tfPatternForRandomGenerating = new JTextField();
        btnGenerate = new JButton("Generate");
        p4.add(rbGenerate, c);
        c.gridy = 0;
        c.gridwidth = 1;
        p4.add(new JLabel("Pattern"), c);
        c.gridy = 1;
        c.gridx = 1;
        p4.add(tfPatternForRandomGenerating, c);
        c.gridx = 2;
        c.gridwidth = 1;
        p4.add(btnGenerate, c);
        

        JPanel panelInner = new JPanel();
        panelInner.setLayout(new GridLayout(2,2,4,4));
        panelInner.add(p1);
        panelInner.add(p3);
        panelInner.add(p2);
        panelInner.add(p4);

        panel.setLayout(new BorderLayout());
        panel.add(panelInner, BorderLayout.WEST);
        return panel;
    }

    

}
