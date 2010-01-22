package converter;
/*
 * 1 Macedonian denar = 0.719575287 Russian rubles
 * 1 Russian ruble = 1.38970865 Macedonian denari
 * */


import javax.swing.*;          
import javax.swing.GroupLayout.Alignment;

import converter.ConverterFunctionality;

import java.awt.event.*;
import java.awt.Dimension;

public class ConverterGUI extends JPanel implements ActionListener {
	
	private JTextField inputMkd = new JTextField("0"),
    				   inputRub = new JTextField("0");

    private JLabel labelRub = new JLabel ("RUB:"),
    			   labelMkd = new JLabel ("MKD:");

    public ConverterGUI(){
    	
    	Dimension d = new Dimension(150,20);
    	
    	inputMkd.setMinimumSize(d);
    	inputRub.setMinimumSize(d);

    	inputMkd.addActionListener(this);
    	inputRub.addActionListener(this);

    	inputMkd.addKeyListener(new KeyAdapter() {    
    			public void keyTyped(KeyEvent e) {
    				char c = e.getKeyChar();
    				if (!((Character.isDigit(c) ||
    					  (c == KeyEvent.VK_PERIOD) ||	
    					  (c == KeyEvent.VK_BACK_SPACE) ||
    					  (c == KeyEvent.VK_DELETE)))) {
    						e.consume();
    						}
    				}
    			});

    	inputRub.addKeyListener(new KeyAdapter() {
    			public void keyTyped(KeyEvent e) {
    				char c = e.getKeyChar();
    				if (!((Character.isDigit(c) ||
    					  (c == KeyEvent.VK_PERIOD) ||		
    					  (c == KeyEvent.VK_BACK_SPACE) ||
    					  (c == KeyEvent.VK_DELETE)))) {
    						e.consume();
    						}
    				}
    			});

    JPanel panel = new JPanel();
    GroupLayout layout = new GroupLayout(panel);

    layout.setAutoCreateGaps(true);

    GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

    hGroup.addGroup(layout.createParallelGroup().
    						addComponent(labelMkd).addComponent(labelRub));
    hGroup.addGroup(layout.createParallelGroup().
    						addComponent(inputMkd).addComponent(inputRub));
    layout.setHorizontalGroup(hGroup);

    GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

    vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
    						addComponent(labelMkd).addComponent(inputMkd));
    vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
    						addComponent(labelRub).addComponent(inputRub));
    layout.setVerticalGroup(vGroup);

    panel.setLayout(layout);
    add(panel);    
    }

    public void actionPerformed(ActionEvent evt) {
    	
    	Object source = evt.getSource();
    	if (source == inputMkd){
    		Double fieldText = Double.valueOf(inputMkd.getText());			
    		Double converted = ConverterFunctionality.convertToRub(fieldText);
    		inputRub.setText(converted.toString());
    		}
    	else if (source == inputRub){
		    Double fieldText = Double.valueOf(inputRub.getText());			
		    Double converted = ConverterFunctionality.convertToMkd(fieldText);
		    inputMkd.setText(converted.toString());
		    }
    }

    private static void createAndShowGui(){
    	
    	JFrame mainFrame = new JFrame("Monetary Unit Converter");
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	mainFrame.add(new ConverterGUI());
    	mainFrame.setResizable(false);

    	mainFrame.pack();
    	mainFrame.setVisible(true);    	
    }

    public static void main(String[] args) {
    try {
    	UIManager.setLookAndFeel(
    				UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {}
    
    createAndShowGui();
    
    }
}
