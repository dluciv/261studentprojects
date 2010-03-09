//Lebedev Dmitry g261 2009 (c)
package temperatureconverter;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class FrameConverter {

    public static Component createComponents() {
        //метка для выбора
        String CHOICE = "Select the direction of conversion";
        String DIRECTION_OF_CONVERSE_1 = "Fahreinheit --> Celsius";
        String DIRECTION_OF_CONVERSE_2 = "Celsius --> Fahreinheit";
        String BUTTON_NAME = "Convert";
        String label = "Enter the temperature to convert";
        JLabel choice = new JLabel(CHOICE);

        //Создадим группу кнопок jradiobutton
        ButtonGroup group = new ButtonGroup();

        //Создадим возможность выбора конвертера с помощью кнопок jradiobutton
        final JRadioButton toCelsius =
                new JRadioButton(DIRECTION_OF_CONVERSE_1, true);
        JRadioButton toFahreinheit =
                new JRadioButton(DIRECTION_OF_CONVERSE_2, false);

        // Кнопка, которую мы нажимаем
        final JButton button = new JButton(BUTTON_NAME);
        button.setMnemonic(KeyEvent.VK_C);
        Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        //Создадим поле для ввода данных
        final JTextField argumentField = new JTextField();

        // Заголовок над кнопкой
        final JLabel title = new JLabel(label);
        title.setLabelFor(button);

        // Метка, содержащая половину ответа
        final String answer = "The temperature is: ";
        final JLabel result = new JLabel(answer);

        //Зададим действие, выполняющееся при изменении поля ввода
        argumentField.addKeyListener(new KeyListener() {

            public void isDouble() {
                try {
                    Double.valueOf(argumentField.getText());
                    button.setEnabled(true);
                } catch (Exception exc) {
                    button.setEnabled(false);
                }
            }

            public void keyTyped(KeyEvent e) {
                isDouble();
            }

            public void keyPressed(KeyEvent e) {
                isDouble();
            }

            public void keyReleased(KeyEvent e) {
                isDouble();
            }
        });

        // Задаем действие, выполняющееся при нажатии кнопки
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String text = argumentField.getText();
                Double textInDouble = 0.0;
                String warning = "Invalid argument, enter double number";
                try {
                    textInDouble = Double.valueOf(text);
                    Double convertedTemp;

                    if (toCelsius.isSelected()) {
                        convertedTemp = TemperatureConverter.convertFahrenheitToCelsius(textInDouble);
                    } else {
                        convertedTemp = TemperatureConverter.convertCelsiusToFahrenheit(textInDouble);
                    }

                    result.setText(answer + convertedTemp.toString());
                } catch (NumberFormatException ex) {
                    result.setText(warning);
                }
            }
        });

        JPanel panel = new JPanel();
        group.add(toCelsius);
        group.add(toFahreinheit);
        panel.setBorder(border);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(choice);
        panel.add(toCelsius);
        panel.add(toFahreinheit);
        panel.add(title);
        panel.add(argumentField);
        panel.add(button);
        panel.add(result);
        argumentField.setEditable(true);

        return panel;
    }
}
