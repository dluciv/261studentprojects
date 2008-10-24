package art779.regparser.main;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class WithGUI extends JFrame {
	/**
	 * 
	 */
	private NFABuilder NFA;
	private NFAVisualizer visualizer;
	private Checker checker;
	
	private static final long serialVersionUID = 1L;
	private JButton button2 = new JButton("Print");	
	private JButton button3 = new JButton("Compare");
	
	private JLabel label = new JLabel("Reg expr:");
	private JLabel label2 = new JLabel("Compare:");
	private JLabel label3 = new JLabel("Choise:");
	private JLabel label4 = new JLabel("Choise:");
	private JLabel label5 = new JLabel("empty");
	
	private JTextField input = new JTextField("i(s|a*|b(c|d)*(a)*)?p", 15);
	private JTextField input2 = new JTextField("isa", 15);
	private TextArea textarea = new TextArea("", 23, 19, TextArea.SCROLLBARS_VERTICAL_ONLY);
	
	
	private JRadioButton radio1 = new JRadioButton("Regular");
	private JRadioButton radio2 = new JRadioButton("GraphViz");
	private JRadioButton radio3 = new JRadioButton("TEX");
	
	private JRadioButton radio4 = new JRadioButton("NFA");
	private JRadioButton radio5 = new JRadioButton("DFA");
	
	private JScrollBar sb = new JScrollBar(); 

	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	public WithGUI() {
		super("my Simple Example");
	    this.setBounds(100,100,350,400);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setResizable(false);
	    
	    getContentPane().setLayout(new GridLayout(1,2));
	    panel3.setLayout(new GridLayout(2,1));
	    panel3.add(panel1);
	    panel3.add(panel2);
	    
	    panel1.setLayout(new GridLayout(16,1));
	    panel1.setBackground(Color.white);
	    getContentPane().add(panel1);
	    
	    panel1.add(label);
	    panel1.add(input);
	    panel1.add(label3);
	    ButtonGroup group1 = new ButtonGroup();
	    group1.add(radio4);
	    group1.add(radio5);
	    panel1.add(radio4);
	    radio4.setSelected(true);
	    panel1.add(radio5);
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
	    
    
	    panel2.setLayout(new FlowLayout());
	    panel2.setBackground(Color.gray);
	    getContentPane().add(panel2);
	    panel2.add(textarea);
	    
	}
	
	class Button2EventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			Parser parser = new Parser(input.getText());
			NFA = parser.getNFA();
			visualizer = new NFAVisualizer(NFA);
			textarea.setText(visualizer.printGraph());

			if(radio5.isSelected())
				NFA.determinateNFA();
			
			if(radio1.isSelected())
				textarea.setText(visualizer.printGraph());
			else if(radio2.isSelected())
				textarea.setText(visualizer.printGraphViz());
			else if(radio3.isSelected())
				textarea.setText(visualizer.printGraphTEX());
		}
	}

	class Button3EventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if(null!=NFA)
			{
				checker = new Checker(NFA);
				if(checker.checkWord(input2.getText()))
					label5.setText("match word");
				else label5.setText("mismatch");
			}
		}
	}
	
	public static void main(String[] args) {
		WithGUI app = new WithGUI();
		app.setVisible(true);
	}
}