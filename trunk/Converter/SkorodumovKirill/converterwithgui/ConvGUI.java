package converterwithgui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ConvGUI extends JFrame{
	
	private JFrame frame = new JFrame("Converter");
	private JButton button = new JButton("Press to convert");
	private JTextField input = new JTextField("", 5);
	private JLabel answer = new JLabel("Answer:");
	
	public ConvGUI()
	{
		super("Converter");
		this.setBounds(100,100,250,100);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container container = this.getContentPane();
	    container.setLayout(new GridLayout(3,2,2,2));
	    container.add(answer);
	    container.add(input);
	    container.add(button);
	    button.addActionListener(new ButtonEventListener());
	}
	
	public class ButtonEventListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		 {
	      try 
	      {
	   	   Integer inp = Integer.parseInt(input.getText());
	   	   answer.setText("Answer: " + Converter.toBinary(inp) );
	      } catch (NumberFormatException e1) {
	   	   answer.setText("Wrong input!");
	      }
	    }	
	}
	
	

}
