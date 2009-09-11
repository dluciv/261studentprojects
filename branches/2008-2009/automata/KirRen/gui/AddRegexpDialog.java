package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Murashov Kirill
 */

public class AddRegexpDialog extends JDialog
{
    private JTextField regTField;
    private AbstractAction cancelAction;
    private String exp;
    private AbstractAction okAction;

    public AddRegexpDialog(Frame owner)
    {
        super(owner, "Добавление нового регулярно выражения", true);
        JPanel pane = (JPanel)getContentPane();
        int width = 300;
        int height = 150;
        setSize(width, height);
        int scrWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int scrHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((scrWidth - width) / 2, (scrHeight - height) / 2);

        initActions();
        initGUI(pane);
    }

    private void initActions()
    {
        cancelAction = new AbstractAction("Отменить")
        {
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
            }
        };

        okAction = new AbstractAction("Припять")
        {
            public void actionPerformed(ActionEvent e)
            {
                exp = regTField.getText();
                setVisible(false);
            }
        };

    }

    public String getRegExp()
    {
        return exp;
    }

    private void initGUI(JPanel pane)
    {
        pane.setLayout(new GridBagLayout());

        int j = 0;
        int i = 0;
        JLabel regLab = new JLabel("Введите регулярное выражение:");
        pane.add(regLab, new GridBagConstraints(i, j, 2, 1, 0, 0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        j++;
        regTField = new JTextField(20);
        pane.add(regTField, new GridBagConstraints(i, j, 2, 1, 0, 0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        j++;
        JButton okBatton = new JButton(okAction);
        pane.add(okBatton, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        i++;
        JButton cancelBatton = new JButton(cancelAction);
        pane.add(cancelBatton, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));


    }
}
