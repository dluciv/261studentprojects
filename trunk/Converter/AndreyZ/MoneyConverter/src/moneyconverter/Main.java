package moneyconverter;

/**
 *  Money Converter (USD->RUB ,RUB->USD)
 *  @author Zubrilin Andrey (c)2009
 */

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // Starting Converter;
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new MakeMainMenu();
            }
        });
    }
}
