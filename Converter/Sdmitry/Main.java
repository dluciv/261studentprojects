/*
 * soldatov dmitry ©, 09
.* converter
 * кнопка - это не эффективно. Пользователю приходится отрываться от клавиатуры,
 * тянуться к мыше, понимать, что он что-то ввел не правильно, тянуться обратно...
 * Гораздо лучше, когда конвертер на лету выдает результат!
 */
package converter;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Main {

    private final static String explaneText = "Переводит дюжины в обычную, десятичную " +
            "систему счисления. И наоборот.";
    private final static String DoDoseToDecText = "Введи дюжины:";
    private final static String DoDecToDoseText = "Введи единицы:";
    private final static String DoDoseToDecBotton = "Дюжины --> Единицы";
    private final static String DoDecToDoseBotton = "Единицы -->Дюжины";
    private final static String ResultText = "В результате: ";
    private final static String ResultFailedText = "Таких дюжин не бывает.";
    private final static String Title = "Недюжий конвертер";


    //осуществляет конверцию, относительно ее направления
    public static float convert(Float input, boolean toDec) {
        float result = 0;

        if (toDec) {
            result = Converter.covertFromDoseToDec(input);
        } else {
            result = Converter.covertFromDecToDose(input);
        }

        return result;
    }


    //проверяем, является ли входящая строка числом типа флоат
    public static boolean isDouble(String inputLine) {

        try {
            Float.valueOf(inputLine);
        } catch (Exception exc) {
            return false;
        }

        return true;
    }

    //вызывает конвертор и выводит значение в лэйбл
    public static void showResult(String inputLine, boolean toDec, JLabel result) {

        if (isDouble(inputLine)) {
            result.setText(ResultText + convert(Float.valueOf(inputLine), toDec));
        } else {
            result.setText(ResultFailedText);
        }
    }

    public static Component createComponents() {

        //задаем все объекты панели
        Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        final JTextField input = new JTextField();

        final JLabel explaneLabel = new JLabel(explaneText);
        explaneLabel.setLabelFor(input);
        explaneLabel.setBorder(border);

        final JLabel result = new JLabel(ResultText);
        result.setLabelFor(input);
        result.setBorder(border);
        result.setBorder(border);

        ButtonGroup bGroup = new ButtonGroup();
        final JRadioButton doseToDecButton = new JRadioButton(DoDoseToDecBotton, true);
        JRadioButton decToDoseButton = new JRadioButton(DoDecToDoseBotton, false);
        bGroup.add(doseToDecButton);
        bGroup.add(decToDoseButton);

        final JLabel whatToDoLabel = new JLabel(DoDoseToDecText);
        whatToDoLabel.setLabelFor(input);
        whatToDoLabel.setBorder(border);

        //описываем методы
        doseToDecButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                whatToDoLabel.setText(DoDoseToDecText);
                showResult(input.getText(), doseToDecButton.isSelected(), result);
            }
        });

        decToDoseButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                whatToDoLabel.setText(DoDecToDoseText);
                showResult(input.getText(), doseToDecButton.isSelected(), result);
            }
        });

        //как указывалось ранее, подсчет выполняется на нажатие любой клавиши
        input.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {

                showResult(input.getText(), doseToDecButton.isSelected(), result);
            }

            public void keyPressed(KeyEvent e) {

                showResult(input.getText(), doseToDecButton.isSelected(), result);
            }

            public void keyReleased(KeyEvent e) {

                showResult(input.getText(), doseToDecButton.isSelected(), result);
            }
        });

        JPanel pane = new JPanel();
        pane.setBorder(border);
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        pane.add(explaneLabel);
        pane.add(doseToDecButton);
        pane.add(decToDoseButton);
        pane.add(whatToDoLabel);
        pane.add(input);
        pane.add(result);

        return pane;
    }

    public static void main(String[] args) {
       
        try {

            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        
        JFrame frame = new JFrame(Title);
        Component contents = createComponents();
        frame.getContentPane().add(contents, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
