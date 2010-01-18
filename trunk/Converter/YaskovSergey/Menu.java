/*
 * there we draw conversion window and process actions;
 * (c) Yaskov Sergey, 2009;
 */

package converter;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.text.*;

public class Menu implements ActionListener {
    JTextField inputField;
    JTextField outputField;
    JButton conversionButton;

    Menu() {
        JFrame frame = new JFrame("Length's Converter");
        frame.setSize(138,140);
        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        inputField = new JTextField(8);
        outputField = new JTextField(8);

        conversionButton = new JButton("Convert");

        inputField.addActionListener(this);
        outputField.addActionListener(this);
        conversionButton.addActionListener(this);

        frame.add(inputField);
        frame.add(outputField);
        frame.add(conversionButton);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("Convert")) {
            NumberFormat formatOfResultNumber = NumberFormat.getInstance();
            String inputedText = inputField.getText();
            double resultNumber = CentimetreToInch.
                    centimetreToInch(inputedTextToNumber(inputedText));

            formatOfResultNumber.setMaximumFractionDigits(2);

            outputField.setText(formatOfResultNumber.format(resultNumber));
        }
    }

    public static double inputedTextToNumber(String inputedString) {
        try {
            return Double.parseDouble(inputedString);
        } catch (NumberFormatException e) {
              return 0;
        }
    }
}
