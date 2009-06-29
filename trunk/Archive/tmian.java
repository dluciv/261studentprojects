import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;




//  Archivier.
//	The first argument is a type of action, second - type of encoding,
//  first and fourth - names, may be with paths, of input and outputs files.



public class Main {	
	static void tryToDo() {
		
	}
	static void frameDesign() {
		JFrame frame = new JFrame("Archivier");
		frame.setSize(400, 150);
		frame.setResizable(false);
		
		
		
		JPanel other = new JPanel();
		other.setLayout(new BoxLayout(other, BoxLayout.PAGE_AXIS));
		
		JPanel top = new JPanel();		
		top.setLayout(new BoxLayout(top, BoxLayout.LINE_AXIS));	
				
		JPanel paneType = new JPanel();	
		ButtonGroup encodeGroup = new ButtonGroup();
		
		JRadioButton haffman = new JRadioButton("Haffman encoding");
		JRadioButton arithmet = new JRadioButton("Arithmetic encoding", true);		
		encodeGroup.add(haffman);
		encodeGroup.add(arithmet);
		
		paneType.setLayout(new BoxLayout(paneType, BoxLayout.PAGE_AXIS));
		paneType.add(haffman);
		paneType.add(arithmet);	
		//paneType.add(Box.createVerticalGlue());		
		top.add(paneType);
		
		top.add(Box.createHorizontalStrut(20));		

		ButtonGroup actionGroup = new ButtonGroup();
		JPanel paneAction = new JPanel();		
		paneAction.setLayout(new BoxLayout(paneAction, BoxLayout.PAGE_AXIS));
		JRadioButton encode = new JRadioButton("To encode", true);
		JRadioButton decode = new JRadioButton("To decode");
		actionGroup.add(encode);
		actionGroup.add(decode);
		
		paneAction.add(encode);
		paneAction.add(decode);
		//paneAction.add(Box.createVerticalGlue());
		top.add(paneAction);
		
		top.add(Box.createHorizontalStrut(20));
		
		JPanel paneGo = new JPanel();
		paneGo.setLayout(new BoxLayout(paneGo, BoxLayout.PAGE_AXIS));
		JButton go = new JButton("Run");
		paneGo.add(go);
		//paneGo.add(Box.createVerticalGlue());
		top.add(paneGo);

		other.add(top);
		
		JPanel input = new JPanel();
		JPanel output = new JPanel();
		
		input.setLayout(new BoxLayout(input, BoxLayout.LINE_AXIS));
		output.setLayout(new BoxLayout(output, BoxLayout.LINE_AXIS));
		
		input.setMaximumSize(new Dimension(frame.getWidth(),20));
		output.setMaximumSize(new Dimension(frame.getWidth(),20));
		
		JButton chooseInputFile = new JButton("Open input file");
		JButton chooseOutputFile = new JButton("Open output file");
		
		JEditorPane inputPath = new JEditorPane();
		JEditorPane outputPath = new JEditorPane();
		
		input.add(chooseInputFile);
		input.add(Box.createHorizontalStrut(20));
		input.add(inputPath);
		
		output.add(chooseOutputFile);
		output.add(Box.createHorizontalStrut(20));
		output.add(outputPath);
		output.add(Box.createVerticalGlue());
		
		other.add(Box.createVerticalBox());
		other.add(input);		
		other.add(Box.createVerticalStrut(10));
		other.add(output);
		other.add(Box.createVerticalBox());
		
		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tryToDo();
			}				
		});
		OpenListener il = new OpenListener(inputPath);
		OpenListener ol = new OpenListener(outputPath);
		
		chooseInputFile.addActionListener(il);
		chooseOutputFile.addActionListener(ol);	
		
		frame.add(other);	
		frame.setVisible(true);
	}
	
	
	
	public static void main(String[] arg) throws IOException {		
		if (arg.length == 4) {
			FileInputStream fin = new FileInputStream(arg[2]);
			byte[] inp = new byte[fin.available()];// read whole file
			fin.read(inp);
			fin.close();
			byte[] out;
			
			if (arg[0].equals("encode"))	{	
				if (arg[1].equals("arithmet")) {
					arithmet.Coder c = new arithmet.Coder();
					out = c.code(inp);
				} else  {
					haffman.Coder c = new haffman.Coder();
					out = c.code(inp);
				} 
			} else {
				if (arg[1].equals("arithmet")) {
					arithmet.Decoder c = new arithmet.Decoder();
					out = c.decode(inp);					
				} else {
					haffman.Decoder c = new haffman.Decoder();
					out = c.decode(inp);					
				}
			}			
			FileOutputStream fout = new FileOutputStream(arg[3]);
			fout.write(out);
			fout.close();
			return;
		} else if (arg.length > 0) {
			System.err.println("Invalid arguments");
			return;
		}
		frameDesign();
				
	}
}