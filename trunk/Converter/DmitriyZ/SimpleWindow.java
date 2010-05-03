// Dmitriy Zabranskiy g261 (c)2009
// Main form of window
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SimpleWindow extends JFrame {

    SimpleWindow() {
        super("Конвертор расстояний");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        final int WIDTH = 280;
        final int HEIGHT = 150;
        final int INDENT = 16;
        final int R_LABEL_INDENT = 3;

        // box1 - верхняя горизонтальная панель
        Box box1 = Box.createHorizontalBox();
        JLabel kmLabel = new JLabel("km:");
        final JTextField kmText = new JTextField();
        box1.add(kmLabel);
        box1.add(Box.createHorizontalStrut(R_LABEL_INDENT));
        box1.add(kmText);

        // box2 - нижняя горизонтальная панель
        Box box2 = Box.createHorizontalBox();
        JLabel yardLabel = new JLabel("yard:");
        final JTextField yardText = new JTextField();
        yardText.setEditable(false);
        box2.add(yardLabel);
        box2.add(Box.createHorizontalStrut(R_LABEL_INDENT));
        box2.add(yardText);

        // кнопка "Перевести"
        Box box3 = Box.createHorizontalBox();
        JButton button = new JButton("Перевести");
        box3.add(Box.createHorizontalGlue());
        box3.add(button);

        // mainBox - главное окно
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(INDENT, INDENT, INDENT, INDENT));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(INDENT));
        mainBox.add(box2);
        mainBox.add(Box.createVerticalStrut(INDENT));
        mainBox.add(box3);
        setContentPane(mainBox);
        setSize(WIDTH, HEIGHT);
        setResizable(false);

        // Создаём эвент на кнопку "Перевести"
        button.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent event) {
                yardText.setText(Converter.check(kmText.getText()));
            }
        });
    }
}
