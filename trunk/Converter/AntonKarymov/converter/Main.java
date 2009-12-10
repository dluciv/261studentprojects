/*Anton Karymov,gr 261
Main for Converter
 */
package converter;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
// Создаем окно приложения и помещаем в него компоненты.
        JFrame frame = new JFrame("Конвертер");
        Components components = new Components();
        Component contents = components.createComponents();
        frame.getContentPane().add(contents, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
