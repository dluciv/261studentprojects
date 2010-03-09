
package redactor;

import java.awt.*;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.*;



public class Main {
//java.awt.Dimension dim = java.awt.Window.getToolkit().getScreenSize();


public static void main(String[] args) {
        JTextField textfield = new JTextField();
//        JTextField textfield1 = new JTextField();
        JFrame frame = new JFrame("Редактор");
//        JPanel pane = new JPanel();
        Dimension dim1 = Toolkit.getDefaultToolkit().getScreenSize();
       // Dimension dim = new Dimension(500,700);
       // Point p = new Point(200,300);
//
        frame.setLocation(dim1.width/2,dim1.height/2);
       // frame.getContentPane().add(textfield);
       // frame.getContentPane().add(textfield1);
       // frame.setPreferredSize(dim);
       
        //frame.setLocation(100, 250);
//        frame.getSize();
//        frame.setBounds(100, 120, 130, 140);
//        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
//        pane.add(textfield);
//        pane.add(textfield1);
//       frame.getContentPane().add(pane, BorderLayout.CENTER);
        System.out.println(frame.getLocation());
        System.out.println(frame.getPreferredSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
//   java.awt.Dimension dim = getToolkit().getScreenSize();
//        this.setLocation(dim.width/2 - this.getWidth()/2,
//                dim.height/2 - this.getHeight()/2);
 }

}
