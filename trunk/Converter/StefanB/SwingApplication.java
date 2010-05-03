package gui_application;

import javax.swing.*;          
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class SwingApplication {
  private static final String labelPrefix = "Number of clicks: ";
  private static int numClicks = 0;

  /**
   * ���� ����� ������� ���������� ���������� ����
   * @return ���������� ���������� ����
   */
  public static Component createComponents() {
    // ������, ������� �� ��������
    JButton button = new JButton("Click Here!");
    button.setMnemonic(KeyEvent.VK_S);
    // ����, ����, ���, �����. 
    Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    button.setBorder(border);
    
    // �����, ���������� ���������� � ����� ������� ������
    //if no text is entered in the constructor, 
    //it will not reserve enough space for it 
    final JLabel label = new JLabel(labelPrefix + numClicks + "    ");
    label.setLabelFor(button);
    label.setBorder(border);

    // ������ ��������, ������������� ��� ������� ������.
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
   

    // �������� ������ � ����� ������ ������ � ����������� ���������
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
   * � ������� ������� ��������� ���� ����������.
   * @param args
   */
  public static void main(String[] args) {
    try {
      // �����-������������� Look & Feel - Java-��������.
      UIManager.setLookAndFeel(
          UIManager.getSystemLookAndFeelClassName());
      //UIManager.setLookAndFeel(
      //        UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {}

    // ������� ���� ���������� � �������� � ���� ����������.
    JFrame frame = new JFrame("SwingApplication");
    Component contents = createComponents();
    frame.getContentPane().add(contents, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
