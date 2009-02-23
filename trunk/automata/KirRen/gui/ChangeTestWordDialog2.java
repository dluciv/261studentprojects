package gui;

import javax.swing.*;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Murashov Kirill
 */

public class ChangeTestWordDialog2 extends JDialog
{
    private AbstractAction cancelAction;
    private AbstractAction okAction;
    private JTextField lengthTField;
    private JTextField numberTField;
    private String lengthText;
    private String numberText;
    private int length;
    private boolean num;
    private int number;
    private boolean len;
    private boolean ok;

    public ChangeTestWordDialog2(Frame owner)
    {
        super(owner, "Изменение слова для теста", true);
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
        ok = false;
        cancelAction = new AbstractAction("Отменить")
        {
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
            }
        };

        okAction = new AbstractAction("Принять")
        {
            public void actionPerformed(ActionEvent e)
            {
                ok = true;
                setVisible(false);
            }
        };
        okAction.setEnabled(false);

    }

    public boolean isOk()
    {
        return ok;
    }

    public int getLength()
    {
        return length;
    }

    public int getNumber()
    {
        return number;
    }

    private void initGUI(JPanel pane)
    {
        pane.setLayout(new GridBagLayout());
        CaretListener lengthLisnerner = new CaretListener()
        {
            public void caretUpdate(CaretEvent e)
            {
                lengthText = lengthTField.getText();
                try
                {
                    length = Integer.parseInt(lengthText);
                    num = true;
                }
                catch ( NumberFormatException ee)
                {
                    num = false;
                }
                okAction.setEnabled(len && num);
            }
        };

        CaretListener numberLisnerner = new CaretListener()
        {
            public void caretUpdate(CaretEvent e)
            {
                numberText = numberTField.getText();
                try
                {
                    number = Integer.parseInt(numberText);
                    len = true;
                }
                catch ( NumberFormatException ee)
                {
                    len = false;
                }
                okAction.setEnabled(len && num);
            }
        };

        int j = 0;
        int i = 0;
        JLabel nameLengthLab = new JLabel("Введите длину слов:");
        pane.add(nameLengthLab, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.WEST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        i++;
        lengthTField = new JTextField(5);
        lengthTField.addCaretListener(lengthLisnerner);
        pane.add(lengthTField, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        i--;
        j++;
        JLabel numeNamberLab = new JLabel("Введите количество слов:");
        pane.add(numeNamberLab, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.WEST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        i++;
        numberTField = new JTextField(5);
        numberTField.addCaretListener(numberLisnerner);
        pane.add(numberTField, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        i--;
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
