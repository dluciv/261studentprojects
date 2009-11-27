/*Anton Karymov,gr 261
Component for Converter
 */
package converter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public final class Components {

    private static String textStartWord = "Введите сумму: ";
    private static String textResult = "Это будет: ";
    private static String sum = "0";
    private static String rouble = " руб.";
    private static String dollar = " $";
    private static String textChoiceOperation = "Выберите нужную операцию перевода:";

//проверка корректности ввода пользователя
    public static double isUserEnterCorrect(String value) {
        if (value == null) {
            throw new java.lang.IllegalArgumentException();
        }

        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Введите число,а не билеберду", "Error", JOptionPane.ERROR_MESSAGE);
            throw new NumberFormatException("Введите число,а не билеберду");
        }
    }

    public static Component createComponents() {
// Кнопка, которую мы нажимаем
        JButton button = new JButton("Перевести валюту");
        button.setMnemonic(KeyEvent.VK_S);
// верх, лево, низ, право.
        Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);//(up,left,down,right)
        button.setBorder(border);
//поле ввода
        final JLabel choiceOperation = new JLabel(textChoiceOperation);
        choiceOperation.setLabelFor(button);
        choiceOperation.setBorder(border);
        final JTextField textfield = new JTextField();
// Метка, содержащая анонс того,что надо сделать в поле ввода
        final JLabel startWord = new JLabel(textStartWord);
        startWord.setLabelFor(button);
        startWord.setBorder(border);
// Метка, содержащая уже переведенные единицы
        final JLabel result = new JLabel(textResult + sum);
        result.setLabelFor(button);
        result.setBorder(border);

        final JRadioButton radioButtonRoubleToDollar = new JRadioButton("Рубли в доллары");
        final JRadioButton radioButtonDollarToRouble = new JRadioButton("Доллары в рубли");

        ButtonGroup battonGroup = new ButtonGroup();
        battonGroup.add(radioButtonRoubleToDollar);
        battonGroup.add(radioButtonDollarToRouble);

        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (radioButtonDollarToRouble.isSelected() == true) {
                    sum = String.valueOf(Converter.dollarToRuble(isUserEnterCorrect(textfield.getText())));
                    result.setText(textResult + sum + rouble);
                }
                if (radioButtonRoubleToDollar.isSelected() == true) {
                    sum = String.valueOf(Converter.rubleToDollar(isUserEnterCorrect(textfield.getText())));
                    result.setText(textResult + sum + dollar);
                }
            }
        });
// Поместим кнопку,поле ввода,метки внутрь панели с внутренними границами
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        pane.setBorder(border);
        pane.add(choiceOperation);
        pane.add(radioButtonRoubleToDollar);
        pane.add(radioButtonDollarToRouble);
        pane.add(startWord);
        pane.add(textfield);
        pane.add(button);
        pane.add(result);
        return pane;
    }
}
