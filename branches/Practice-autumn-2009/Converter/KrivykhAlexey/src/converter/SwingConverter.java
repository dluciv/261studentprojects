//(c) Кривых Алексей 2009г.
//Swing converter
package converter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class SwingConverter {

    private static final int TEXTFIELDWIDE = 2;
    private static final int BORDER = 10;
    private static final String LABELRES = "Result: ";
    public static String input = "0.0";

    public static Component createComponents() {
        //Содаем кнопку
        JButton button = new JButton("Convert");
        button.setMnemonic(KeyEvent.VK_S);
        //создаем фабрику границ для компонентов
        Border border = BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER);
        //создаем метку и помещаем её в границы
        final JLabel label = new JLabel(LABELRES + input);
        label.setBorder(border);
        //создаем поля для текста и помещаем её в границы
        final JTextField text = new JTextField(TEXTFIELDWIDE);
        text.setBorder(border);
        //задаем действие выполняющееся при нажатии кнопки
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Converter converter = new Converter();
                input = text.getText();
                label.setText(LABELRES + (converter.convert(input)));
            }
        });
        //создаем панель и помещаем все созданные компоненты на нее
        JPanel panel = new JPanel();
        panel.setBorder(border);
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(text);
        panel.add(button);
        panel.add(label);

        return panel;
    }

    public static void main(String[] args) {
        try {
            // Кросс-платформенный Look & Feel - Java-стандарт.
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        //создаем окошко помещаем туда содержимое 
        JFrame frame = new JFrame("Celsius to Fahrenheit");
        Component contents = createComponents();
        frame.getContentPane().add(contents, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
