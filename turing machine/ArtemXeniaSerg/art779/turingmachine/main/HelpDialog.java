package art779.turingmachine.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class HelpDialog extends JDialog implements ActionListener{

	private JTextArea textArea;
	private JButton btnOk;
	
	public HelpDialog(String name){
		super();
		JLabel lb = new JLabel("<html><body><b1>" + name);
		lb.setHorizontalAlignment(JLabel.CENTER);
		lb.setHorizontalTextPosition(JLabel.CENTER);
		textArea = new JTextArea();
		textArea.setEditable(false);
		JPanel pMain = new JPanel();
		pMain.setLayout(new BorderLayout());
		pMain.setBorder(new EmptyBorder(10,10,10,10));
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setPreferredSize(new Dimension(270, 320));
		pMain.add(scroll, BorderLayout.CENTER);
		pMain.add(lb, BorderLayout.NORTH);
		
		JPanel pButtons = new JPanel();
		pButtons.setBorder(new EmptyBorder(10,10,10,10));
		pButtons.setLayout(new BorderLayout());
		btnOk = new JButton("OK");
		pButtons.add(btnOk, BorderLayout.EAST);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(pMain, BorderLayout.NORTH);
		getContentPane().add(pButtons, BorderLayout.SOUTH);
		
		btnOk.addActionListener(this);
		pack();
	}	
	
	public void setContent(String content){
		textArea.setText(content);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnOk)){
			dispose();
		}		
	}
}
