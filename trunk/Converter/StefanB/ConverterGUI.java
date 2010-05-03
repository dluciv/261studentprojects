package gui_application;
/*
 * 1 Macedonian denar = 0.719575287 Russian rubles
 * 1 Russian ruble = 1.38970865 Macedonian denari
 * */

import javax.swing.*;          
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class ConverterGUI {
	public static Component createComponents(){
		JLabel header = new JLabel("Monetary Unit Converter");
		
		JTextField inputMkd = new JTextField("mkd");
		JTextField inputRub = new JTextField("rub");
		
		JButton convertToRub = new JButton ("To RUB ->");
		JButton convertToMkd = new JButton ("<- To MKD");
		
		
		JPanel bottomPanel = new JPanel();
	    bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));    
	    bottomPanel.add(inputMkd);
	    bottomPanel.add(Box.createHorizontalStrut(5));
	    bottomPanel.add(convertToRub);
	    bottomPanel.add(Box.createHorizontalStrut(5));
	    bottomPanel.add(convertToMkd);
	    bottomPanel.add(Box.createHorizontalStrut(5));
	    bottomPanel.add(inputRub);
	    
	    JPanel pane = new JPanel();
	    pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
	    pane.add(header);
	    pane.add(bottomPanel);
	    
	    return pane;
	}
	public static void main(String[] args) {
	    try {
	      // Кросс-платформенный Look & Feel - Java-стандарт.
	      UIManager.setLookAndFeel(
	          UIManager.getCrossPlatformLookAndFeelClassName());
	    } catch (Exception e) {}

	    // Создаем окно приложения и помещаем в него компоненты.
	    JFrame frame = new JFrame("SwingApplication");
	    Component contents = createComponents();
	    frame.getContentPane().add(contents, BorderLayout.CENTER);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	  }

}
