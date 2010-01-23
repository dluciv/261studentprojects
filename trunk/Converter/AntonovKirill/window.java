/* (c) Antonov Kirill 2009
   Представление окна
 */
package converter;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


class Window extends JFrame {
    Window(){
        super("Перевод Валют");
        setDefaultCloseOperation(EXIT_ON_CLOSE);//операция close

        //границы
        final int FORM_WIDTH = 250;
        final int FORM_HEIGHT = 150;
        final int MAIN_INDENT = 10;
        final int R_LABEL_INDENT = 5;

        // Настраиваем горизонтальную панель для ввода данных
        Box boxRub = Box.createHorizontalBox();
        JLabel rubLabel = new JLabel("Rub:");
        final JTextField rubText = new JTextField();
        boxRub.add(rubLabel);
        boxRub.add(Box.createHorizontalStrut(R_LABEL_INDENT));
        boxRub.add(rubText);

        // Настраиваем горизонтальную панель для вывода результата
        Box boxUsd = Box.createHorizontalBox();
        JLabel usdLabel = new JLabel("USD:");
        final JTextField usdText = new JTextField();
        usdText.setEditable(false);
        boxUsd.add(usdLabel);
        boxUsd.add(Box.createHorizontalStrut(R_LABEL_INDENT));
        boxUsd.add(usdText);

        // Ворлшебная кнопка=)
        Box boxTranclate = Box.createHorizontalBox();
        JButton buttonTranclate = new JButton("Tranclate");
        boxTranclate.add(Box.createHorizontalGlue());
        boxTranclate.add(buttonTranclate);

        // Размещаем три горизонтальные панели на одной вертикальной
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(MAIN_INDENT, MAIN_INDENT, MAIN_INDENT, MAIN_INDENT));
        mainBox.add(boxRub);
        mainBox.add(Box.createVerticalStrut(MAIN_INDENT));
        mainBox.add(boxUsd);
        mainBox.add(Box.createVerticalStrut(MAIN_INDENT));
        mainBox.add(boxTranclate);
        setContentPane(mainBox);
        setSize(FORM_WIDTH, FORM_HEIGHT);
        setResizable(false);

        // Действие принажатие кнопки
        buttonTranclate.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent event) {
                usdText.setText(ParseAndConvert.parseAndConvert(rubText.getText()));
            }
        });
    }

}