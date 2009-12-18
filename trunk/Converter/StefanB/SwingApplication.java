package gui_application;

import javax.swing.*;          
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class SwingApplication {
  private static final String labelPrefix = "Number of clicks: ";
  private static int numClicks = 0;

  /**
   * Этот метод создает внутреннее содержание окна
   * @return внутреннее содержание окна
   */
  public static Component createComponents() {
    // Кнопка, которую мы нажимаем
    JButton button = new JButton("Click Here!");
    button.setMnemonic(KeyEvent.VK_S);
    // верх, лево, низ, право. 
    Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    button.setBorder(border);
    
    // Метка, содержащая информацию о числе нажатий кнопки
    //if no text is entered in the constructor, 
    //it will not reserve enough space for it 
    final JLabel label = new JLabel(labelPrefix + numClicks + "    ");
    label.setLabelFor(button);
    label.setBorder(border);

    // Задаем действие, выполняющееся при нажатии кнопки.
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        numClicks++;
        label.setText(labelPrefix + numClicks);
      }
    });
    
    JTextField rubIn = new JTextField();
    rubIn.setText("rub");
   
    JTextField mkdIn = new JTextField();
    mkdIn.setText("mkd");
   

    // Поместим кнопку и метку внутрь панели с внутренними границами
    JPanel pane = new JPanel();
    pane.setBorder(border);
    pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
    pane.add(button);
    pane.add(label);
    pane.add(rubIn);
    pane.add(mkdIn);

    return pane;
  }

  /**
   * В главной функции создается окно приложения.
   * @param args
   */
  public static void main(String[] args) {
    try {
      // Кросс-платформенный Look & Feel - Java-стандарт.
      UIManager.setLookAndFeel(
          UIManager.getSystemLookAndFeelClassName());
      //UIManager.setLookAndFeel(
      //        UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {}

    // Создаем окно приложения и помещаем в него компоненты.
    JFrame frame = new JFrame("SwingApplication");
    Component contents = createComponents();
    frame.getContentPane().add(contents, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
