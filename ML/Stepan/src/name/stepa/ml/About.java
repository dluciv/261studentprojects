package name.stepa.ml;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JPanel panel1;

    public About() {
        setContentPane(contentPane);
        pack();
        setModal(false);
        //setSize(300, 100);
        setResizable(false);
        getRootPane().setDefaultButton(buttonOK);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation((size.width - getWidth()) / 2, (size.height - getHeight()) / 2);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        dispose();
    }
}
