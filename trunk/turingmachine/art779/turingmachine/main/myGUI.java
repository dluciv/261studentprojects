package art779.turingmachine.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.*;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;


public class myGUI extends JApplet {
	private TextArea txt = new TextArea("", 4, 22, TextArea.SCROLLBARS_VERTICAL_ONLY);
	private JTable table;
	private static DefaultTableModel model;
	private JComboBox comboBox = new JComboBox();	
		
	class TML implements TableModelListener {
		public void tableChanged(TableModelEvent e) {	
			if(model.getValueAt(model.getRowCount()-1,model.getColumnCount()-1) != null)
			{
				txt.setText(model.getRowCount()+"!1");
				model.addColumn("");
			}
		}
	}
	
	class startMT implements ActionListener {
		public void actionPerformed(ActionEvent e) {			
			String str = "";			
			ArrayList<String> tapeConstruct = new ArrayList<String>();	    		
			int j = model.getRowCount()-1;
			for (int i = 0; i < model.getColumnCount()-1; i++) {
				tapeConstruct.add(model.getValueAt(j, i).toString());
			}
			Tape tape = new Tape(tapeConstruct);			
			Rules rules =  RulesMaker.parseXMLFileProgram(comboBox.getSelectedItem().toString());

			
			str += rules.toString();
			str += "\n";
			str += tape.toString();
			
			Performer p = new Performer(tape,rules);
			p.execute();			
			
			str += "\n";
			str += "iterations " + p.getIterationsCount();
			str += "\n";
			str += tape.toString();			
			txt.append(str);			
		}
	}
	
	public static void addRow(ArrayList<String> tape) { 
		model.addRow(tape.toArray());		
	}

	public void init() { //initialize GUI window	
		Container cp = getContentPane();
		 
		String[] tapein = {"1","1","+","1","1","+","1","1","1"};		
		model = new DefaultTableModel(0,tapein.length+1);
		model.addRow(tapein);		
		model.addTableModelListener(new TML());
		table = new JTable(model);		
		JButton button2 = new JButton("execute the program");	
		JLabel label = new JLabel("chose a program:");		 
		JPanel panelControllers = new JPanel();
		panelControllers.add(txt);
		panelControllers.add(label);
		panelControllers.add(comboBox);
		comboBox.addItem("unarsum.xml");
		panelControllers.add(button2);
		button2.addActionListener(new startMT());	
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(new GridLayout(2,1));		
		cp.add(new JScrollPane(table));
		panelSouth.add(txt);
		panelSouth.add(panelControllers);
		cp.add(BorderLayout.SOUTH, panelSouth);
	}

	public static void main(String[] args) {
		run(new myGUI(),600,600);
	}

	public static void run(JApplet applet, int width, int height) {
		 JFrame frame = new JFrame();
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.getContentPane().add(applet);
		 frame.setSize(width, height);
		 applet.init();
		 applet.start();
		 frame.setVisible(true);
	}
}
