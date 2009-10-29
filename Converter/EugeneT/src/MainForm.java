/**
 * Класс окна главной формы
 * @author Eugene Todoruk
 * group 261
 */

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainForm extends JFrame {

    MainForm() {
        super("Конвертор валют");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        final int FORM_WIDTH = 250;
        final int FORM_HEIGHT = 150;
        final int MAIN_INDENT = 12;
        final int R_LABEL_INDENT = 6;


        // Настраиваем первую горизонтальную панель (для ввода данных)
        Box boxRub = Box.createHorizontalBox();
        JLabel rubLabel = new JLabel("Rub:");
        final JTextField rubText = new JTextField();
        boxRub.add(rubLabel);
        boxRub.add(Box.createHorizontalStrut(R_LABEL_INDENT));
        boxRub.add(rubText);

        // Настраиваем вторую горизонтальную панель (для вывода результата)
        Box boxUsd = Box.createHorizontalBox();
        JLabel usdLabel = new JLabel("USD:");
        final JTextField usdText = new JTextField();
        usdText.setEditable(false);
        boxUsd.add(usdLabel);
        boxUsd.add(Box.createHorizontalStrut(R_LABEL_INDENT));
        boxUsd.add(usdText);

        // Настраиваем третью горизонтальную панель (с кнопкой)
        Box boxOk = Box.createHorizontalBox();
        JButton buttonOk = new JButton("OK");
        boxOk.add(Box.createHorizontalGlue());
        boxOk.add(buttonOk);

        // Размещаем три горизонтальные панели на одной вертикальной
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(MAIN_INDENT, MAIN_INDENT, MAIN_INDENT, MAIN_INDENT));
        mainBox.add(boxRub);
        mainBox.add(Box.createVerticalStrut(MAIN_INDENT));
        mainBox.add(boxUsd);
        mainBox.add(Box.createVerticalStrut(MAIN_INDENT));
        mainBox.add(boxOk);
        setContentPane(mainBox);
        setSize(FORM_WIDTH, FORM_HEIGHT);
        setResizable(false);

        // Создаём эвент на кнопку ОК
        buttonOk.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent event) {
                usdText.setText(ConvertRubToUsd.parseAndConvert(rubText.getText()));
            }
        });
    }
}
