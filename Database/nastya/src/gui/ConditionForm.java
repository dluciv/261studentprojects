package gui;

import dbentities.Condition;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

/**
 * Вспомогательная форма для представления панели, в которой можно отметить данные,
 * по которым должен производиться поиск в базе
 * @author nastya
 *         Date: 30.08.2009
 *         Time: 13:40:09
 */
public class ConditionForm extends JComponent{
    private JTextField tfLastName;      
    private JTextField tfName;
    private JTextField tfMiddleName;
    private JPanel panel;
    private JPanel layoutPanel;

    public ConditionForm(String title){
        layoutPanel.setBorder(new TitledBorder(new EtchedBorder(), title));
    }

    public void setCondition(Condition condition){
        tfLastName.setText(condition.getLastName());
        tfName.setText(condition.getName());
        tfMiddleName.setText(condition.getMiddleName());
    }

    public Condition getCondition(){
        return new Condition(tfLastName.getText().trim(),tfName.getText().trim(), tfMiddleName.getText().trim() );
    }

    public JPanel getPanel(){
        return panel;
    }
}
