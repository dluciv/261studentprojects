package moneyconverter;

/**
 * Creating Menu and Actions;
 * @author Zubrilin Andrey (c)2009
 */
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.text.*;

public class MakeMainMenu implements ActionListener {

    //Объявление меток.exFrom - из каких единиц идет пересчет,exTo -куда;
    JLabel exFrom;
    JLabel exTo;

    //jInput - Ввод валюты,jOutput - Результат,jCourse - курс валюты;
    JTextField jInput;
    JTextField jOutput;
    JTextField jCourse;

    //jExchange - производим вычисления после нажатия;
    JButton jExchange;
    
    //jMoney - Список вариантов(Откуда,куда и как);
    JComboBox jMoney;

    //exCourse - Нынишний курс;
    static double exCourse;

    /*whatEx отвечат за то,что куда переводим(usd->rub,rub->usd)
     * Использование типа boolean по причине наличия только 2х вариантов;
     */
    boolean isUSD = false;

    //Создание формы и компонентов;
    MakeMainMenu(){

        /*Создаем форму,задаем размер,указываем действие при закрытие,
         *порядок расположения компонентов;
         */
        JFrame convFrame = new JFrame("Money Converter");
        convFrame.setSize(175,200);
        convFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        convFrame.setLayout(new FlowLayout(FlowLayout.LEFT, 15,5));

        //Создаем строку меню;
        JMenuBar mBar = new JMenuBar();

        //Создаем меню File;
        JMenu jFile = new JMenu("File");
        JMenuItem jmiReset = new JMenuItem("Reset");
        JMenuItem jmiExit = new JMenuItem("Exit");
        jFile.add(jmiReset);
        jFile.add(jmiExit);
        mBar.add(jFile);

        //Создаем меню Help;
        JMenu jHelp = new JMenu("Help");
        JMenuItem jmiAbout = new JMenuItem("About");
        jHelp.add(jmiAbout);
        mBar.add(jHelp);

        //Создаем список вариантов конвертирования валюты;
        jMoney = new JComboBox();
        jMoney.setModel(new DefaultComboBoxModel(new String[] { "Choose Converter", "USD -> RUB", "RUB -> USD"}));

        //Создаем кнопку расчета;
        jExchange = new JButton("Convert");

        //Создаем поле курса валюты;
        jCourse = new JTextField(3);
        JLabel chCourse = new JLabel("1 USD = ");
        JLabel chCourse2 = new JLabel("RUB");

        //Метки валют;
        exFrom = new JLabel("None");
        exTo = new JLabel("None");

        //Поля Ввода\вывода;
        jInput = new JTextField(8);
        jOutput = new JTextField(8);

        //Добавляем действия для изменяемых параметров;
        jmiReset.addActionListener(this);
        jmiExit.addActionListener(this);
        jmiAbout.addActionListener(this);
        jInput.addActionListener(this);
        jMoney.addActionListener(this);
        jOutput.addActionListener(this);
        jExchange.addActionListener(this);

        //Сбрасываем к <default> все значения перед запуском;
        Reset();

        //Добавляем все компоненты в форму;
        convFrame.setJMenuBar(mBar);
        convFrame.add(jMoney);
        convFrame.add(exFrom);
        convFrame.add(jInput);
        convFrame.add(exTo);
        convFrame.add(jOutput);
        convFrame.add(chCourse);
        convFrame.add(jCourse);
        convFrame.add(chCourse2);
        convFrame.add(jExchange);
        convFrame.setResizable(false);
        convFrame.setVisible(true);
    }

    //Сбрасываем к <default>;
    public void Reset(){
        jMoney.setSelectedIndex(0);
        exFrom.setText("None");
        exTo.setText("None");
        jInput.setEditable(false);
        jOutput.setEditable(false);
        jInput.setText("");
        jOutput.setText("");
        jExchange.setEnabled(false);
        converter.resetCourse();
        jCourse.setText("");
        jCourse.setEditable(false);
    }

    //Обработка событий;
    public void actionPerformed(ActionEvent actevent){

        //Передаем команду строке;
        String comStr = actevent.getActionCommand();

        //Обрабатываем нажатие кнопки;
        if(comStr.equals("Convert")){

            //Проверяем изменение курса;
            exCourse = converter.ifNum(jCourse.getText());
            exCourse = exCourse > 0 ? exCourse : 0;

            //Количество цифр после запятой;
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(3);
            
            //Производим расчет и выводим;
            jOutput.setText(nf.format(converter.calcMoney(jInput.getText(), exCourse,isUSD))+"");
        }

        //Обрабатываем выбор в списке;
        if(jMoney.getSelectedIndex() == 0)
            Reset();
        else{
            jInput.setEditable(true);
            jExchange.setEnabled(true);
            jCourse.setEditable(true);
            jCourse.setText(exCourse+"");

            if(jMoney.getSelectedIndex() == 1){

                exFrom.setText("USD :");
                exTo.setText("RUB :");
                isUSD = true;
            }
            else{

                exFrom.setText("RUB :");
                exTo.setText("USD :");
                isUSD = false;
            }
        }

        //Обрабатываем Выбор строки меню;
        if(comStr.equals("Exit"))
            System.exit(0);
        if(comStr.equals("About"))
            JOptionPane.showMessageDialog(null,"   Money Converter\n\n by Zubrilin Andrey (c)2009");
        if(comStr.equals("Reset"))
            Reset();               
    }
}
