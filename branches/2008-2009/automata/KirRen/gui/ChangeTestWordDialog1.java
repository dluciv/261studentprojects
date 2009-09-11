package gui;

import regexp.DFA;

import javax.swing.*;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Murashov Kirill
 */

public class ChangeTestWordDialog1 extends JDialog
{
    private JTextField wordTField;
    private AbstractAction cancelAction;
    private AbstractAction okAction;
    private String word;
    private DFA dfa;
    private JLabel testLab;
    private boolean ok;

    public ChangeTestWordDialog1(Frame owner, DFA dfa)
    {
        super(owner, "Изменение данных для теста", true);
        JPanel pane = (JPanel)getContentPane();
        int width = 300;
        int height = 200;
        setSize(width, height);
        int scrWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int scrHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((scrWidth - width) / 2, (scrHeight - height) / 2);
        this.dfa = dfa;

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
                word = wordTField.getText();
                ok = true;
                setVisible(false);
            }
        };

    }

    public String getWord()
    {
        return word;
    }

    public boolean isOk()
    {
        return ok;
    }


    private void initGUI(JPanel pane)
    {
        pane.setLayout(new GridBagLayout());

        CaretListener wordLisnerner = new CaretListener()
        {
            public void caretUpdate(CaretEvent e)
            {
                try
                {
                    testLab.setText(dfa.checkToSttring(wordTField.getText()));
                    okAction.setEnabled(true);
                }
                catch ( NumberFormatException ee)
                {
                    okAction.setEnabled(false);
                }
            }
        };


        int j = 0;
        int i = 0;
        testLab = new JLabel(dfa.checkToSttring(""));
        pane.add(testLab, new GridBagConstraints(i, j, 2, 1, 0, 0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        j++;
        wordTField = new JTextField(20);
        wordTField.addCaretListener(wordLisnerner);
        pane.add(wordTField, new GridBagConstraints(i, j, 2, 1, 0, 0,
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
