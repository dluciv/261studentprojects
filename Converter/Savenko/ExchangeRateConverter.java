/*
 * Converter RUB to USD Savenko Maria (c)2009
 */

package msavenko;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

public class ExchangeRateConverter {
    
    public static Box CreateComponents() {
        int Main_Border = 12;
        int Label_Position = 6;
        final double USD_TO_RUB = 29.1641;
        
        Box boxRub = Box.createHorizontalBox();
        JLabel rubLabel = new JLabel("RUB: ");
        final JTextField rubText = new JTextField();
        boxRub.add(rubLabel);
        boxRub.add(Box.createHorizontalStrut(Label_Position));
        boxRub.add(rubText);
        
        Box boxUsd = Box.createHorizontalBox();
        JLabel usdLabel = new JLabel("USD: ");
        final JTextField usdText = new JTextField();
        usdText.setEditable(false);
        boxUsd.add(usdLabel);
        boxUsd.add(Box.createHorizontalStrut(Label_Position));
        boxUsd.add(usdText);
        
        Box boxOk = Box.createHorizontalBox();
        JButton buttonOk = new JButton("OK");
        boxOk.add(Box.createHorizontalGlue());
        boxOk.add(buttonOk);
        
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(Main_Border, Main_Border,
                Main_Border, Main_Border));
        mainBox.add(boxRub);
        mainBox.add(Box.createVerticalStrut(Main_Border));
        mainBox.add(boxUsd);
        mainBox.add(Box.createVerticalStrut(Main_Border));
        mainBox.add(boxOk);
        
        buttonOk.addMouseListener(new MouseAdapter(){
            
            public void mouseClicked(MouseEvent event) {
                try{
                    usdText.setText(Double.toString(Double.parseDouble(rubText
                            .getText())
                            / USD_TO_RUB));
                }
                catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null,
                            "There is nothing to convert!");
                }
            }
        });
        
        return mainBox;
    }
    
    public static void main(String[] args) {
        int Form_Width = 250;
        int Form_Height = 150;
        
        JFrame frame = new JFrame("ExchangeRateConverter");
        Box contents = CreateComponents();
        frame.setSize(Form_Width, Form_Height);
        frame.setResizable(false);
        frame.setContentPane(contents);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
