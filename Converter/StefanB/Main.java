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
	    
	    //initialize the exchange rate table
	    Currency mkd = new Currency("MKD", 0);
	    Currency rub = new Currency("RUB", 0);
	    double rate = 1.38970865;
	    ExchangeRateTable.setExchangeRate(mkd, rub, rate);
	    
	    ConverterGUI convert = new ConverterGUI();
	    convert.createAndShowGui();
	}

}
