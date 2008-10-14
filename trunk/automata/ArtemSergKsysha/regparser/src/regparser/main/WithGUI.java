package regparser.main;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class WithGUI extends JFrame {
	/**
	 * 
	 */
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
	private JTextArea textarea = new JTextArea();
	
	
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
	    //button3.addActionListener(new ButtonEventListener());
	    panel1.add(button3);
	    panel1.add(label5);
	    
    
	    panel2.setLayout(new FlowLayout());
	    panel2.setBackground(Color.gray);
	    getContentPane().add(panel2);
	    //textarea.setColumns(15);
	    //textarea.setRows(22);
	    textarea.setLineWrap(true);
	    textarea.setAutoscrolls(false);
	    panel2.add(textarea);
	    
	}

	
	class Button2EventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String message = "";
			message += "Button was pressedn";
			JOptionPane.showMessageDialog(null,
		    		message,
		    		"Output",
		    	    JOptionPane.PLAIN_MESSAGE);

			if(radio1.isSelected())
			{
				NFAVisualizer Visualizer = new NFAVisualizer(NFA);
			}
		}
		
		
		Parser parser = new Parser(label.getText());
		NFABuilder NFA = parser.getNFA();

	}

	public static void main(String[] args) {
		WithGUI app = new WithGUI();
		app.setVisible(true);
	}
}