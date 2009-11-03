/*Anton Karymov,gr 261
Component for Converter
 */
package converter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class Components {

    private static String textStartWord = "Введите сумму: ";
    private static String textResult = "Это будет: ";
    private static String sum = "0";
    private static String rouble = " руб.";
    private static String dollar = " $";
    private static String textChoiceOperation = "Выберите нужную операцию перевода:";

// непосредственно сам конвертер
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
                    sum = Converter.dollarToRuble(textfield.getText());
                    result.setText(textResult + sum + rouble);
                }
                if (radioButtonRoubleToDollar.isSelected() == true) {
                    sum = Converter.rubleToDollar(textfield.getText());
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
