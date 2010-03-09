//autor MurKA

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI extends JFrame
{
    private JTextField standartTField;
    private JLabel resLable;
    private AbstractAction actionConvert;

    public GUI() throws HeadlessException
    {
        setTitle("convector from standart form in inversel polish record");
        setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        int width = 600;
        int height = 150;
        setSize(width, height);
        setResizable(false);
        int scrWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int scrHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((scrWidth - width) / 2, (scrHeight - height) / 2);

        JPanel pane = (JPanel)getContentPane();
        initGUI (pane);
    }

    private void initGUI(JPanel pane)
    {

        pane.setLayout(new GridBagLayout());
        int i=0;
        int j=0;

        new AbstractAction("Convert")
        {
            public void actionPerformed(ActionEvent e)
            {
                NodOfTree root = new NodOfTree("",null,null);
                root = root.parser(standartTField.getText());
                resLable.setText(" Result:  "+root);
            }
        };


        JLabel enterLable = new JLabel(" enter expression");
        pane.add(enterLable,new GridBagConstraints(i,j,1,1,0,0,
                GridBagConstraints.WEST,GridBagConstraints.NONE,
                new Insets(5,5,5,5),0,0));

        i++;
        standartTField = new JTextField(20);
        pane.add(standartTField,new GridBagConstraints(i,j,1,1,0,0,
                GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,
                new Insets(5,5,5,5),0,0));

        actionConvert = new AbstractAction("Convert")
        {
            public void actionPerformed(ActionEvent e)
            {
                NodOfTree root = new NodOfTree("",null,null);
                root = root.parser(standartTField.getText());
                resLable.setText(" Result:  "+root);
            }
        };

        i++;
        JButton conv = new JButton(actionConvert);
        pane.add(conv,new GridBagConstraints(i,j,1,1,0,0,
                GridBagConstraints.EAST,GridBagConstraints.NONE,
                new Insets(5,5,5,5),0,0));

        j++;
        i=0;
        resLable = new JLabel(" Result:  ");
        pane.add(resLable,new GridBagConstraints(i,j,2,1,0,0,
                GridBagConstraints.WEST,GridBagConstraints.NONE,
                new Insets(5,5,5,5),0,0));
        
        i=2;
        JButton exitButton = new JButton(new AbstractAction("Exit")
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        pane.add(exitButton,new GridBagConstraints(i,j,1,1,0,0,
                GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,
                new Insets(5,5,5,5),0,0));

        standartTField.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                int key = e.getKeyCode();
                if (key == 10)
                {
                     actionConvert.actionPerformed(null);
                }
                if (key == 27)
                {
                     standartTField.setText("");
                }
            }
        });
    }

    public static void main(String[] args)
    {
        GUI conv = new GUI();
        conv.setVisible(true);
    }

}
