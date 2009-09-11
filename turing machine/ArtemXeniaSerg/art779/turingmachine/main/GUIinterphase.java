package art779.turingmachine.main;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.*;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;


public class GUIinterphase extends JFrame {	
	private JTable table;	
	private static DefaultTableModel model;
	private JComboBox comboBox = new JComboBox();	
	private JTextField input = new JTextField("1111+111+11", 15);		
	private String lastSelectedPath = null;
	
	
	public GUIinterphase(int width, int height){
		constructGUI();
		 setTitle("TuringMachine");
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setSize(width, height);
	}	
	
	class TML implements TableModelListener {
		public void tableChanged(TableModelEvent e) {	
			if(model.getValueAt(model.getRowCount()-1,model.getColumnCount()-1) != null)
			{				
				model.addColumn("");				
			}
		}
	}
	
	class startMT implements ActionListener {
		public void actionPerformed(ActionEvent e) {			
			ArrayList<String> tapeConstruct = new ArrayList<String>();			
			for (int i = 0; i < input.getText().length(); i++) {
				tapeConstruct.add(input.getText().substring(i, i+1));
			}				
			int st = tapeConstruct.size() - model.getColumnCount();
			for (int i = 0; i <= st; i++) {
				model.addColumn("");
			}				
			Tape tape = new Tape(tapeConstruct);			
			if (lastSelectedPath != null) {
				Rules rules =  RulesMaker.parseXMLFileProgram(lastSelectedPath);			
				Performer p = new Performer(tape,rules,false);
				p.execute();
			}
		}
	}
	
	public static void addDelimRow() {		
		model.addRow(new ArrayList<String>().toArray());	
	}
	
	public static void addRow(ArrayList<String> tape,String state, String sym, int pointer) { 
		ArrayList<String> tapeWithRule = new ArrayList<String>();
		int iterTo = 0;		
		if ( pointer <= tape.size() )
			iterTo = tape.size();
		else
			iterTo = pointer;		
		for (int i = 0; i <= iterTo + 1; i++) {			
			if (i >= tape.size()) {
				if ( i == pointer) {
					tapeWithRule.add(" <==");
				}	
			}
			else {
				if ( i == pointer) {
					tapeWithRule.add(tape.get(i) + " <==");
				}
				else {
					tapeWithRule.add(tape.get(i));
				}
			}						
		}	
		tapeWithRule.add("(" + state + ")" + sym);		
		model.addRow(tapeWithRule.toArray());		
	}

	public void constructGUI() { //initialize GUI window	
		Container cp = getContentPane();			
		model = new DefaultTableModel(1,strToStrAr(input.getText()).toArray().length+1);	
		model.addTableModelListener(new TML());			
		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);		
		JButton button2 = new JButton("execute the program");		
		JButton button3 = new JButton("browse");		
		JButton button4 = new JButton("Clear");				 
		JPanel panelControllers = new JPanel();			
		panelControllers.add(button2);		
		panelControllers.add(button3);
		panelControllers.add(button4);
		button2.addActionListener(new startMT());
		button3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser(lastSelectedPath);
				chooser.setMultiSelectionEnabled(false);
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setFileFilter(new FileFilter(){
					@Override
					public boolean accept(File arg0) {
						return arg0.isDirectory() || arg0.getName().toLowerCase().endsWith(".xml");
					}
					@Override
					public String getDescription() {
						return "XML";
					}});
				int val = chooser.showOpenDialog(GUIinterphase.this);
				if(val == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile() != null){					
					lastSelectedPath = chooser.getSelectedFile().getAbsolutePath();
				}				
			}});		
		button4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel tm = (DefaultTableModel)table.getModel();
				while(tm.getRowCount() != 1){
					tm.removeRow(0);
				}			
				table.updateUI();
			}});
		panelControllers.add(input);	
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(new GridLayout(2,1));		
		cp.add(new JScrollPane(table));		
		panelSouth.add(panelControllers);		
		cp.add(BorderLayout.SOUTH, panelSouth);			
		JMenu jMenu = new JMenu("Help");
		JMenuItem help = new JMenuItem(new AbstractAction("Help"){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				HelpDialog helpDialog = new HelpDialog("HELP");
				helpDialog.setSize(400,450);
				helpDialog.setLocation(new Point(100, 200));
				helpDialog.setContent("browse - выбор программы для машины тьюринга.\n" +
									  "Clear - очистка поля вывода.\n" +
									  "execute the program - запускает программу на выполнение\n" +
									  "для ленты, заданной в строке ввода справа снизу.\n" +
									  "\n" +
									  "Программа выводит ленту для каждой итерации машины.\n" +
									  "В последней ячейке каждой строки выводится имя\n" +
									  "текущего состояния и символ, который считывает\n" +
									  "каретка на текущей итерации.\n" +
									  "Каретка обозначена символом <==.\n" +
									  "Вывод очередного запуска программы\n" +
									  "отделяется от предыдущей пустой строкой.");
				helpDialog.setVisible(true);								
			}});
		JMenuItem about = new JMenuItem(new AbstractAction("About"){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				HelpDialog helpDialog = new HelpDialog("ABOUT");
				helpDialog.setSize(350,450);
				helpDialog.setLocation(new Point(100, 200));
				helpDialog.setContent("Программа реализующая машину Тьюринга\n\n" +
									  "Авторы:\nМиронов Артем\nГовейнович Сергей\nЦипан Ксения\n\n" +
									  "С пожеланиями и предложениями обращаться:\n" +
									  "0integral0@rambler.ru");
				helpDialog.setVisible(true);				
			}});
		help.setMnemonic('H');
		about.setMnemonic('A');
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(jMenu);
		jMenu.add(help);
		jMenu.add(about);
		setJMenuBar(menuBar);
	}
	
	private ArrayList<String> strToStrAr(String str) {
		ArrayList<String> result = new ArrayList<String>();		
		for (int i = 0; i < str.length(); i++) {
			result.add(i, str.substring(i, i+1));	
		}
		return result;
	}
	
	public  static void main(String[] args) {
		GUIinterphase G = new GUIinterphase(800,600);
		G.setVisible(true);
	}
}
