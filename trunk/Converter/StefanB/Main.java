/*
 * (c) Stefan Bojarovski 2009
 * */
package converter;

import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) {
		try {
	    	UIManager.setLookAndFeel(
	    				UIManager.getCrossPlatformLookAndFeelClassName());
	    } catch (Exception e) {}
	    
	    ConverterGUI convert = new ConverterGUI();
	    convert.createAndShowGui();
	}

}
