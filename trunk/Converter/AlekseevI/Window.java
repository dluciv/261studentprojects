/**
 * Modul window
 * @ Alekseev Ilya (c) 2009
 */
package convector;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Window extends JFrame {

    JTextField kilometerField;
    JTextField milsField;

    Window() {
        super("Конвертор расстояний");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Первое окно, для ввода данных
        Box boxMils = Box.createHorizontalBox();
        JLabel milsLabel = new JLabel("Мили:");
        milsField = new JTextField(15);
        boxMils.add(milsLabel);
        boxMils.add(Box.createHorizontalStrut(6));
        boxMils.add(milsField);


        // Второе окно - вывод
        Box boxKilometers = Box.createHorizontalBox();
        JLabel kilometerLabel = new JLabel("Километры:");
        kilometerField = new JTextField(15);
        boxKilometers.add(kilometerLabel);
        boxKilometers.add(Box.createHorizontalStrut(6));
        boxKilometers.add(kilometerField);


        // Кнопка!!!!!!
        Box boxConvect = Box.createHorizontalBox();
        JButton buttonConvect = new JButton("Convect");
        boxConvect.add(Box.createHorizontalGlue());
        boxConvect.add(buttonConvect);

        // Размещаем три горизонтальные панели на одной вертикальной
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(boxMils);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(boxKilometers);
        mainBox.add(Box.createVerticalStrut(17));
        mainBox.add(boxConvect);
        setContentPane(mainBox);
        pack();
        setResizable(false);
        // Действие после нажатия кнопки
        buttonConvect.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent event) {
                kilometerField.setText(ConvectorMilsToKilometers.parseAndConvert(milsField.getText()));
            }
        });

    }
}
