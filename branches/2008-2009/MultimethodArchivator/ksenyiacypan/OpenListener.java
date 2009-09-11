import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;


/**
*
* @author ksenyiacypan
*/

public class OpenListener implements ActionListener {

	JEditorPane editPane =  null;
	
	public void setEditPane(JEditorPane ep) { 
		editPane = ep;		
	}
	public OpenListener(JEditorPane ep) {
		editPane = ep;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser chooser = new JFileChooser();
		int code = chooser.showOpenDialog(null);
		if (code == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			editPane.setText(file.getAbsolutePath());
		}
		
	}

}
