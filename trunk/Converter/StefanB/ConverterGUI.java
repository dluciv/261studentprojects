/*
 * (c) Stefan Bojarovski 2009
 * */
package converter;

import javax.swing.*;          
import javax.swing.GroupLayout.Alignment;
import java.awt.event.*;
import java.awt.Dimension;

public class ConverterGUI extends JPanel implements ActionListener {
	
	//Input fields, that also serve as output fields
	private JTextField inputMkd = new JTextField("0"),
    				   inputRub = new JTextField("0");
	//Informative labels 
    private JLabel labelRub = new JLabel ("RUB:"),
    			   labelMkd = new JLabel ("MKD:");
    
    //constructor method, that builds all the components of the GUI 
    public ConverterGUI(){
    	
    	Dimension d = new Dimension(150,20);
    	//so the input fields can't be resized too small
    	inputMkd.setMinimumSize(d);
    	inputRub.setMinimumSize(d);
    	
    	//"listen" for what the user types inside the fields 
    	inputMkd.addActionListener(this);
    	inputRub.addActionListener(this);
    	
    	//allow only digits and the decimal dot as input characters
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
    
    //the main container for the buttons and labels
    JPanel panel = new JPanel();
    GroupLayout layout = new GroupLayout(panel);

    layout.setAutoCreateGaps(true);//automatically create a bit of space between components

    /*	create a horizontal group which aligns horizontally 
     * 	two parallel groups of components
     * 	the first group are the labels 
     * 	and the second group are the two input fields
     * */
    GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

    hGroup.addGroup(layout.createParallelGroup().
    						addComponent(labelMkd).addComponent(labelRub));
    hGroup.addGroup(layout.createParallelGroup().
    						addComponent(inputMkd).addComponent(inputRub));
    layout.setHorizontalGroup(hGroup);
    
    /*create a vertical group which aligns vertically two groups
     * it aligns the labels on the same line as the input fields,
     * that they refer to.
     * */
    GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

    vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
    						addComponent(labelMkd).addComponent(inputMkd));
    vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
    						addComponent(labelRub).addComponent(inputRub));
    layout.setVerticalGroup(vGroup);

    panel.setLayout(layout);
    add(panel);    
    }
    
    /*This method gives functionality to our converter
     * It comes from the ActionListener interface
     * and defines what happens when the components that
     * are changed by the user fire a signal to the listener
     * */
    public void actionPerformed(ActionEvent evt) {
    	//what component fired the signal?
    	Object source = evt.getSource();
    	
    	
    	if (source == inputMkd){
    		Double fieldText = Double.valueOf(inputMkd.getText());			
    		Double converted = ConverterFunctionality.convert(fieldText, "MKD", "RUB");    		
    		inputRub.setText(converted.toString());
    		}
    	else if (source == inputRub){
		    Double fieldText = Double.valueOf(inputRub.getText());			
		    Double converted = ConverterFunctionality.convert(fieldText, "RUB", "MKD");
		    inputMkd.setText(converted.toString());
		    }
    }
    
    /*Wrap the whole thing and initialize some obligatory  properties
     * */
    public void createAndShowGui(){
    	
    	JFrame mainFrame = new JFrame("Monetary Unit Converter");
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	mainFrame.add(new ConverterGUI());
    	mainFrame.setResizable(false);

    	mainFrame.pack();
    	mainFrame.setVisible(true);    	
    }
}
