/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotheart.converter;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

/**
 *
 * @author HotHeart
 */
public class Main {

    /**
     * Parsing an IP-adress in A.B.C.D format to new int[] {A, B, C, D}
     * @param s - String representation of IP
     * @return
     */
    private static int[] tryParse(String s) {
        String[] nums = s.split("\\.");
        if (nums.length != 4) {
            return null;
        }

        int[] res = new int[4];
        for (int i = 0; i < 4; i++) {
            try {
                res[i] = Integer.parseInt(nums[i]);
            } catch (NumberFormatException e) {
                return null;
            }
            if ((res[i] < 0) & (res[i] > 255)) {
                return null;
            }
        }
        return res;

    }

    /**
     * Converting int[] {A,B,C,D} IP to long int representation
     * @param ip source IP representation
     * @return result of conversion
     */
    private static long convertIp(int[] ip) {
        return ((long) ip[0] << 24) + ((long) ip[1] << 16) +
                ((long) ip[2] << 8) + (long) ip[3];
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("HotHeart's IP converter");

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        final JLabel label = new JLabel("Введите IP в формате *.*.*.*:");
        label.setBorder(border);

        final JTextField textField = new JTextField("192.168.0.1");
        textField.setBorder(border);

        final JLabel answer = new JLabel("Результат:");
        answer.setBorder(border);

        textField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int[] vals = tryParse(textField.getText());
                if (vals != null) {
                    long ip = convertIp(vals);
                    answer.setText("Результат:" + Long.toString(ip));
                } else {
                    answer.setText("Неверный ввод");
                }
            }
        });

        JPanel pane = new JPanel();
        pane.setBorder(border);
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

        pane.add(label);
        pane.add(textField);
        pane.add(answer);

        mainFrame.getContentPane().add(pane, BorderLayout.CENTER);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }
}
