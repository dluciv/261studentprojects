package art779.regparser.main;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ru.sscc.util.TimeCounter;


public class WithGUI extends JFrame {
	/**
	 * 
	 */
	private Basic tool;
	//private NFABuilder NFA;
	//private NFAVisualizer visualizer;
	//private Checker checker;
	
	private static final long serialVersionUID = 1L;
	private JButton button2 = new JButton("Print");	
	private JButton button3 = new JButton("Compare");
	private JButton button4 = new JButton("Test");
	
	private JLabel label = new JLabel("Reg expr:");
	private JLabel label2 = new JLabel("Compare:");
	private JLabel label3 = new JLabel("Deterministic or not:");
	private JLabel label4 = new JLabel("Output to:");
	private JLabel label5 = new JLabel("empty");
	private JLabel label6 = new JLabel("Test runs:");
	private JLabel label7 = new JLabel("parse time");
	private JLabel label8 = new JLabel("parse speed");
	private JLabel label9 = new JLabel("");
	
	private JTextField input = new JTextField("ab|bc", 15);
	private JTextField input2 = new JTextField("abc", 15);
	private JTextField input3 = new JTextField("6000", 15);
	private TextArea textarea = new TextArea("", 29, 19, TextArea.SCROLLBARS_VERTICAL_ONLY);
	
	
	private JRadioButton radio1 = new JRadioButton("Regular");
	private JRadioButton radio2 = new JRadioButton("GraphViz");
	private JRadioButton radio3 = new JRadioButton("TEX");
	
	private JRadioButton radio4 = new JRadioButton("NFA");
	private JRadioButton radio5 = new JRadioButton("DFA");
	
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	public WithGUI() {
		super("regparser");
	    this.setBounds(100,100,350,500);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setResizable(false);
	    
	    getContentPane().setLayout(new GridLayout(1,2));
	    panel3.add(panel1);
	    panel3.add(panel2);
	    
	    panel1.setLayout(new GridLayout(20,1));
	    panel1.setBackground(Color.white);
	    getContentPane().add(panel1);
	    
	    panel1.add(label);
	    panel1.add(input);
	    panel1.add(label3);
	    ButtonGroup group1 = new ButtonGroup();
	    group1.add(radio4);
//	    group1.add(radio5);
	    panel1.add(radio4);
	    radio4.setSelected(true);
//	    panel1.add(radio5);
	    panel1.add(label4);
	    ButtonGroup group2 = new ButtonGroup();
	    group2.add(radio1);
	    group2.add(radio2);
	    group2.add(radio3);
	    panel1.add(radio1);
	    radio1.setSelected(true);
	    panel1.add(radio2);
	    panel1.add(radio3);
	    
	    button2.addActionListener(new Button2EventListener());
	    panel1.add(button2);
	    
	    panel1.add(label2);
	    panel1.add(input2);
	    button3.addActionListener(new Button3EventListener());
	    panel1.add(button3);
	    panel1.add(label5);
	    panel1.add(label6);
	    panel1.add(input3);
	    button4.addActionListener(new Button4EventListener());
	    panel1.add(button4);
	    panel1.add(label7);
	    panel1.add(label8);
	    panel1.add(label9);
    
	    panel2.setLayout(new FlowLayout());
	    panel2.setBackground(Color.gray);
	    getContentPane().add(panel2);
	    panel2.add(textarea);
	    
	}
	
	class Button2EventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			

			tool = new Basic();
			NFABuilder NFA =  tool.buildNFA(input.getText());
			//textarea.setText(tool.printRegular(NFA));

			if(radio5.isSelected())
				NFA.determinateNFA();
			
			if(radio1.isSelected())
				textarea.setText(tool.printRegular(NFA));
			else if(radio2.isSelected())
				textarea.setText(tool.printGraphViz(NFA));
			else if(radio3.isSelected())
				textarea.setText(tool.printTEX(NFA));
		}
	}

	class Button3EventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			tool = new Basic();
			NFABuilder NFA;
			
			if(radio5.isSelected())
				NFA = tool.buildNFA(input.getText());
			else
				NFA = tool.buildDFA(input.getText());
			
			if(null!=NFA)
			{
				if(tool.chekWord(NFA, input2.getText()))
					label5.setText("match word");
				else label5.setText("mismatch");
			}
		}
	}
	
	class Button4EventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			tool = new Basic();
			NFABuilder NFA;
			
			if(radio5.isSelected())
				NFA = tool.buildNFA(input.getText());
			else
				NFA = tool.buildDFA(input.getText());
			
			try {
				int runsCount = Integer.parseInt(input3.getText());
				if(runsCount > 0)
				{
					tool.startTimer();
					for (int i = 0; i <= runsCount; i++) {
						
						if(null!=NFA)
						{
							if(tool.chekWord(NFA, input2.getText()))
								label5.setText("match word");
							else label5.setText("mismatch");
						}
						else label5.setText("NFA is missing");
	
					}

					long timeSpent = tool.getTime();
					String timeSpentSec = tool.getTimeSec();
					
					label7.setText("time is " + timeSpentSec + " sec.");
					//!! почему выдает ошибку ??
					double speed = runsCount * input.getText().length() / timeSpent ;
					
					label8.setText("speed is " + speed + " sym/ms");
					
					label9.setText(speed*1000/1024 + " kb/s");
				}
			}
			catch(NumberFormatException e1)
			{
				label7.setText("error");
			}
		}
	}
	
	public static void main(String[] args) {
		WithGUI app = new WithGUI();
		app.setVisible(true);
	}
}