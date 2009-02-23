package gui;

/**
 * @author Murashov Kirill
 */


import regexp.*;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.HashMap;
import java.util.Vector;

public class TestWindow extends JFrame
{
    private NFA nfa;
    private DFA dfa;
    private MFA mfa;

    private HashMap<String, Vector<JLabel>> testDataLab = new HashMap<String,Vector<JLabel>>();
    private AbstractAction closeAction;
    private AbstractAction nfaAction;

    private Runtime runtime;
    private Desktop desktop;
    private String dotPath;
    private String texPath;

    private String resRegActiv;
    private AbstractAction dfaAction;
    private AbstractAction mfaAction;
    private AbstractAction texAction;
    private AbstractAction addRegExp;
    private RegExpData regActiv;

    private JList regList;
    private DefaultListModel listModel;
    private AbstractAction dellRegExp;
    private JLabel resRegActivLab;
    private AbstractAction clearRegExp;
    private Vector<AbstractAction> changeAction;
    private RegExpDataList expList;

    public TestWindow(String dotPath, String texPath) throws HeadlessException
    {
        setTitle("Mulya");

        this.dotPath = dotPath;
        this.texPath = texPath;

        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                expList.saved();
                System.exit(0);
            }
        });

        int width = 800;
        int height = 600;
        setSize(width, height);
        int scrWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int scrHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((scrWidth - width) / 2, (scrHeight - height) / 2);

        JPanel pane = (JPanel)getContentPane();
        try
        {
            expList = new RegExpDataList();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        initActionsClose();
        initActionsReg();
        initActionsGener();
        initGUI(pane);


        runtime = Runtime.getRuntime();
        desktop = Desktop.getDesktop();
    }

    private void initActionsReg()
    {
        addRegExp = new AbstractAction("Добавить")
        {
            public void actionPerformed(ActionEvent e)
            {
                AddRegexpDialog d = new AddRegexpDialog(TestWindow.this);
                d.setVisible(true);
                RegExpData regExp = new RegExpData(d.getRegExp());
                expList.getVecRegExpData().add(regExp);
                listModel.addElement(regExp);
            }
        };

        dellRegExp = new AbstractAction("Удалить")
        {
            public void actionPerformed(ActionEvent e)
            {
                listModel.removeElement(regList.getSelectedValue());
                dellRegExp.setEnabled(false);
                regActionEnabled(false);
                resRegActivLab.setText("");
                for(int i = 1; i<5; i++ )
                {
                    setNullResTest(i);
                }
            }
        };
        dellRegExp.setEnabled(false);

        clearRegExp = new AbstractAction("Очистить")
        {
            public void actionPerformed(ActionEvent e)
            {
                listModel.removeAllElements();
                dellRegExp.setEnabled(false);
                regActionEnabled(false);
                resRegActivLab.setText("");
                for(int i = 1; i<5; i++ )
                {
                    setNullResTest(i);
                }
            }
        };
    }

    private void setResTest1(String word)
    {
        testDataLab.get("test1").get(1).setText(word);
        double[] res = Statistics.autoTest(word,nfa,dfa,mfa);
        for (int i=0; i<3; i++)
        {
            testDataLab.get("test1").get(i+2).setText("" + res[i] + " Mb/c");
        }
    }
    private void setResTest2(String word)
    {
        testDataLab.get("test2").get(1).setText(word);
        double[] res = Statistics.autoTest(word,nfa,dfa,mfa);
        for (int i=0; i<3; i++)
        {
            testDataLab.get("test2").get(i+2).setText("" + res[i] + " Mb/c");
        }
    }
    private void setResTest3(String word)
    {
        testDataLab.get("test3").get(1).setText(word);
        double[] res = Statistics.autoTest(word,nfa,dfa,mfa);
        for (int i=0; i<3; i++)
        {
            testDataLab.get("test3").get(i+2).setText("" + res[i] + " Mb/c");
        }
    }
    private void setNullResTest(int numTest)
    {
        String tmpStr;
        if (numTest != 4)
            for(int i=1; i<5;i++ )
            {
                if (i==1) tmpStr = "no word";
                else tmpStr = "no res";
                switch(numTest)
                {
                    case 1:
                        testDataLab.get("test1").get(i).setText(tmpStr);
                        break;
                    case 2:
                        testDataLab.get("test2").get(i).setText(tmpStr);
                        break;
                    case 3:
                        testDataLab.get("test3").get(i).setText(tmpStr);
                        break;
                }
            }
         else
            for(int i=1; i<5;i++ )
            {
                if (i<=2) tmpStr = "no value";
                else tmpStr = "no res";
                testDataLab.get("test4").get(i).setText(tmpStr);
            }
    }

    private void setResTest4(int len, int num )
    {
        regActiv.setTest4Word(len, num);
        testDataLab.get("test4").get(1).setText(""+ len);
        testDataLab.get("test4").get(2).setText(""+ num);

        int res = Statistics.campTest(len, num, nfa, mfa);
        testDataLab.get("test4").get(3).setText("" + res + " / "+ num );
    }

    private AbstractAction getChangeAction(final int numTest)
    {
        AbstractAction changeAction = new AbstractAction("Изменить")
        {
            private int num = numTest;

            public void actionPerformed(ActionEvent e)
            {
                if ( num <= 2 )
                {
                    ChangeTestWordDialog1 d = new ChangeTestWordDialog1(TestWindow.this,dfa);
                    d.setVisible(true);
                    if (d.isOk())
                    {
                        switch(num)
                        {
                            case 0:
                                regActiv.setTest1Word(d.getWord());
                                setResTest1(d.getWord());
                                break;
                            case 1:
                                regActiv.setTest2Word(d.getWord());
                                setResTest2(d.getWord());
                                break;
                            case 2:
                                regActiv.setTest3Word(d.getWord());
                                setResTest3(d.getWord());
                                break;
                        }
                    }
                }
                else
                {
                    ChangeTestWordDialog2 d = new ChangeTestWordDialog2(TestWindow.this);
                    d.setVisible(true);
                    if (d.isOk())
                    {
                        setResTest4(d.getLength(),d.getNumber());
                    }
                }
            }
        };
        changeAction.setEnabled(false);
        return changeAction;
    }

    private void initActionsClose()
    {
        closeAction = new AbstractAction("Выход")
        {
            public void actionPerformed(ActionEvent e)
            {
                expList.saved();                
                System.exit(0);
            }
        };

    }

    private void initActionsGener()
    {
        nfaAction = new AbstractAction("Показать НКА")
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    String res = nfa.produceDotty();
                    FileOutputStream fout = new FileOutputStream(new File("./nfa.dot"));
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(fout, "cp1251"));
                    pw.print(res);
                    pw.close();
                    fout.close();

                    String command = "dot.exe ./nfa.dot -T gif -o nfa.gif";
                    Process p = runtime.exec(dotPath + File.separator + command);

                    try
                    {
                        p.waitFor();
                    }
                    catch (InterruptedException e1)
                    {
                        e1.printStackTrace();
                    }

                    if ( desktop.isSupported(Desktop.Action.OPEN))
                        desktop.open(new File("./nfa.gif").getCanonicalFile());
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        };
        nfaAction.setEnabled(false);

        dfaAction = new AbstractAction("Показать ДКА")
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    String res = dfa.produceDotty();
                    FileOutputStream fout = new FileOutputStream(new File("./dfa.dot"));
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(fout, "cp1251"));
                    pw.print(res);
                    pw.close();
                    fout.close();

                    String command = "dot.exe ./dfa.dot -T gif -o dfa.gif";
                    Process p = runtime.exec(dotPath + File.separator + command);

                    try
                    {
                        p.waitFor();
                    }
                    catch (InterruptedException e1)
                    {
                        e1.printStackTrace();
                    }

                    if ( desktop.isSupported(Desktop.Action.OPEN))
                        desktop.open(new File("./dfa.gif").getCanonicalFile());

                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        };
        dfaAction.setEnabled(false);

        mfaAction = new AbstractAction("Показать МДКА")
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    String res = mfa.produceDotty();
                    FileOutputStream fout = new FileOutputStream(new File("./mfa.dot"));
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(fout, "cp1251"));
                    pw.print(res);
                    pw.close();
                    fout.close();

                    String command = "dot.exe ./mfa.dot -T gif -o mfa.gif";
                    Process p = runtime.exec(dotPath + File.separator + command);

                    try
                    {
                        p.waitFor();
                    }
                    catch (InterruptedException e1)
                    {
                        e1.printStackTrace();
                    }

                    if ( desktop.isSupported(Desktop.Action.OPEN))
                        desktop.open(new File("./mfa.gif").getCanonicalFile());

                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        };
        mfaAction.setEnabled(false);

        texAction = new AbstractAction("Показать таблицу")
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {

                    TexTable tt = new TexTable(nfa);

                    String res = tt.getTexTable();
                    FileOutputStream fout = new FileOutputStream(new File("./table.tex"));
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(fout, "cp1251"));
                    pw.print(res);
                    pw.close();
                    fout.close();

                    String command = "latex.exe ./table.tex";
                    Process p = runtime.exec(texPath + File.separator + command);

// в данном месте происходят истенные святые чудеса:
// с данным куском кода все нормально работает (что ожидаемо)
// без этого куска зависает (что не ожидаемо)                   
                    InputStream is = p.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    while (br.readLine() != null)
                    {
                    }
//окончание действия чудо-дерева
                    try
                    {
                        p.waitFor();
                    }
                    catch (InterruptedException e1)
                    {
                        e1.printStackTrace();
                    }

                    command = "dvipdfm.exe ./table.dvi";
                    p = runtime.exec(texPath + File.separator + command);

                    try
                    {
                        p.waitFor();
                    }
                    catch (InterruptedException e1)
                    {
                        e1.printStackTrace();
                    }

                    if ( desktop.isSupported(Desktop.Action.OPEN))
                        desktop.open(new File("./table.pdf").getCanonicalFile());

                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        };
        texAction.setEnabled(false);
    }

    private void regActionEnabled(boolean value)
    {
        nfaAction.setEnabled(value);
        dfaAction.setEnabled(value);
        mfaAction.setEnabled(value);
        texAction.setEnabled(value);
        for(int i=0; i<4; i++)
        {
            changeAction.get(i).setEnabled(value);
        }
    }

    private void initGUI(JPanel pane)
    {
        pane.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel(new BorderLayout());
        pane.add(leftPanel, BorderLayout.WEST);

        JPanel downPanel = new JPanel(new BorderLayout());
        pane.add(downPanel, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        pane.add(centerPanel, BorderLayout.CENTER);

        JPanel upPanel = new JPanel(new BorderLayout());
        centerPanel.add(upPanel, BorderLayout.NORTH);

        JPanel testingPanel = new JPanel(new GridBagLayout());
        centerPanel.add(testingPanel, BorderLayout.CENTER);

        initLeftPanel(leftPanel);
        initDownPanel(downPanel);
        initUpPanel(upPanel);
        initTestDataLab();
        initTestingPanel(testingPanel);

    }

    private void initTestingPanel(JPanel testPanel)
    {
        changeAction = new Vector<AbstractAction>();
        int j=0;
        String numTest = "test1";
        changeAction.add(getChangeAction(0));
        j = initTestPanel(testPanel,numTest,j,changeAction.get(0));

        numTest = "test2";
        changeAction.add(getChangeAction(1));
        j = initTestPanel(testPanel,numTest,j,changeAction.get(1));

        numTest = "test3";
        changeAction.add(getChangeAction(2));
        j = initTestPanel(testPanel,numTest,j,changeAction.get(2));

        numTest = "test4";
        changeAction.add(getChangeAction(3));
        initTestPanel(testPanel,numTest,j,changeAction.get(3));
    }

    private int initTestPanel(JPanel testPanel, String nameTest, int coordinateBeg, AbstractAction change )
    {

        int i=0;
        int j=coordinateBeg;
        testPanel.add(testDataLab.get(nameTest).get(0), new GridBagConstraints(i, j, 4, 1, 0, 0,
            GridBagConstraints.WEST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        if (nameTest == "test4")
        {
            j++;
            JLabel lengthLab = new JLabel("Длина:");
            testPanel.add(lengthLab, new GridBagConstraints(i, j, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));

            i++;
            testPanel.add(testDataLab.get(nameTest).get(1), new GridBagConstraints(i, j, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));

            j++;
            i=0;
            JLabel numLab = new JLabel("Слов:");
            testPanel.add(numLab, new GridBagConstraints(i, j, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));

            i++;
            testPanel.add(testDataLab.get(nameTest).get(2), new GridBagConstraints(i, j, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));

            i=0;
            j++;
            JButton changeWordTest1Button = new JButton(change);
            testPanel.add(changeWordTest1Button, new GridBagConstraints(i, j, 2, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));

            i=4;
            j=coordinateBeg+2;
            testPanel.add(testDataLab.get(nameTest).get(3), new GridBagConstraints(i, j, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));

            i=3;
            j=coordinateBeg+2;
            JLabel NfaLab = new JLabel("Попадание:");
            testPanel.add(NfaLab, new GridBagConstraints(i, j, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(5, 20, 5, 5), 0, 0));
        }
        else
        {
            j++;
            JLabel wordLab = new JLabel("Слово:");
            testPanel.add(wordLab, new GridBagConstraints(i, j, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));

            i++;
            testPanel.add(testDataLab.get(nameTest).get(1), new GridBagConstraints(i, j, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));

            i=0;
            j++;
            JButton changeWordTestButton = new JButton(change);
            testPanel.add(changeWordTestButton, new GridBagConstraints(i, j, 2, 2, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));

            i=4;
            j=coordinateBeg+1;
            testPanel.add(testDataLab.get(nameTest).get(2), new GridBagConstraints(i, j, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));

            j++;
            testPanel.add(testDataLab.get(nameTest).get(3), new GridBagConstraints(i, j, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0));

            j++;
            testPanel.add(testDataLab.get(nameTest).get(4), new GridBagConstraints(i, j, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));


            i=3;
            j=coordinateBeg+1;
            JLabel NfaLab = new JLabel("НКА:");
            testPanel.add(NfaLab, new GridBagConstraints(i, j, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(5, 20, 5, 5), 0, 0));

            j++;
            JLabel DfaLab = new JLabel("ДКА:");
            testPanel.add(DfaLab, new GridBagConstraints(i, j, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(5, 20, 5, 5), 0, 0));

            j++;
            JLabel MfaLab = new JLabel("МДКА:");
            testPanel.add(MfaLab, new GridBagConstraints(i, j, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(5, 20, 5, 5), 0, 0));
        }
        return coordinateBeg + 4;
    }

    private void initUpPanel(JPanel upPanel)
    {
        JPanel regActivPanel = new JPanel(new GridBagLayout());
        upPanel.add(regActivPanel, BorderLayout.NORTH);

        JLabel regActivLab = new JLabel("Регулярное выражение:  ");
        regActivPanel.add(regActivLab, new GridBagConstraints(0, 0, 1, 1, 0, 0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        resRegActivLab = new JLabel();
        regActivPanel.add(resRegActivLab, new GridBagConstraints(1, 0, 1, 1, 0, 0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        JPanel regActionPanel = new JPanel(new GridBagLayout());
        upPanel.add(regActionPanel, BorderLayout.SOUTH);

        int i = 0;
        int j = 0;
        JButton nfaButton = new JButton(nfaAction);
        regActionPanel.add(nfaButton, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        i++;
        JButton dfaButton = new JButton(dfaAction);
        regActionPanel.add(dfaButton, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        i++;
        JButton mfaButton = new JButton(mfaAction);
        regActionPanel.add(mfaButton, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        i++;
        JButton texButton = new JButton(texAction);
        regActionPanel.add(texButton, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

    }

    private void initDownPanel(JPanel downPanel)
    {
        JPanel closePanel = new JPanel(new GridBagLayout());
        downPanel.add(closePanel, BorderLayout.EAST);

        JButton closeButton = new JButton(closeAction);
        closePanel.add(closeButton, new GridBagConstraints(0, 0, 1, 1, 0, 0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(20, 20, 20, 20), 0, 0));
    }

    private void initLeftPanel(JPanel leftPanel)
    {
        JPanel upLeftPanel = new JPanel(new GridBagLayout());
        leftPanel.add(upLeftPanel, BorderLayout.NORTH);

        JLabel regLab = new JLabel("Регулярные выражения");
        upLeftPanel.add(regLab, new GridBagConstraints(0, 0, 1, 1, 0, 0,
            GridBagConstraints.WEST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        JPanel downLeftPanel = new JPanel(new GridBagLayout());
        leftPanel.add(downLeftPanel, BorderLayout.SOUTH);

        JButton addRegButton = new JButton(addRegExp);
        downLeftPanel.add(addRegButton, new GridBagConstraints(0, 0, 1, 1, 0, 0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        JButton delRegButton = new JButton(dellRegExp);
        downLeftPanel.add(delRegButton, new GridBagConstraints(0, 1, 1, 1, 0, 0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        JButton clearRegButton = new JButton(clearRegExp);
        downLeftPanel.add(clearRegButton, new GridBagConstraints(0, 2, 1, 1, 0, 0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));


        listModel = new DefaultListModel();
        regList = new JList(listModel);
        leftPanel.add(regList, BorderLayout.CENTER);

        for(RegExpData exp : expList.getVecRegExpData())
            listModel.addElement(exp);

        regList.addListSelectionListener(new ListSelectionListener()
        {
            boolean isFirst = true;
            public void valueChanged(ListSelectionEvent e)
            {
                if (regList.getSelectedValue()!= null)
                {
                    isFirst = !isFirst;                    
                    if ( isFirst )
                    {
                        regActiv = (RegExpData)regList.getSelectedValue();
                        resRegActivLab.setText(regActiv.getExp());
                        dellRegExp.setEnabled(true);
                        initActivReg(regActiv);
                    }
                }
            }
        });
    }

    private void initActivReg(RegExpData regexp)
    {
        resRegActiv = regexp.getExp();
        Parser parser = new Parser(resRegActiv);
        try
        {
            nfa = parser.parse();
            dfa = DFA.buildDFA(nfa, parser.alphabet);
            mfa = MFA.buildMFA(dfa, parser.alphabet);
        }
        catch (ParserException e1)
        {
            e1.printStackTrace();
        }
        regActionEnabled(true);
        if (regActiv.getTest1Word()!= null)
        {
            setResTest1(regActiv.getTest1Word());
        }
        else
        {
            setNullResTest(1);
        }

        if (regActiv.getTest2Word()!= null)
        {
            setResTest2(regActiv.getTest2Word());
        }
        else
        {
            setNullResTest(2);
        }

        if (regActiv.getTest3Word()!= null)
        {
            setResTest3(regActiv.getTest1Word());
        }
        else
        {
            setNullResTest(3);
        }

        if (regActiv.getLengthTest4Word()!= 0)
        {
            setResTest4(regActiv.getLengthTest4Word(),regActiv.getNumberTest4Word());
        }
        else
        {
            setNullResTest(4);
        }


    }

    private void initTestDataLab()
    {
        Vector<JLabel> tmp = new Vector<JLabel>();
        testDataLab.put("test1",tmp);

        tmp = new Vector<JLabel>();
        testDataLab.put("test2",tmp);

        tmp = new Vector<JLabel>();
        testDataLab.put("test3",tmp);

        tmp = new Vector<JLabel>();
        testDataLab.put("test4",tmp);

        testDataLab.get("test1").add(0,new JLabel("Результаты тестов по пропускной способности для слова из языка."));
        testDataLab.get("test2").add(0,new JLabel("Результаты тестов по пропускной способности для слова не из языка."));
        testDataLab.get("test3").add(0,new JLabel("Результаты тестов по пропускной способности на откат ДКА."));
        testDataLab.get("test4").add(0,new JLabel("Результаты тестов на попадание случайного слова в язык."));

        String tmpStr;
        for(int i=1; i<5;i++ )
        {
            if (i==1) tmpStr = "no word";
            else tmpStr = "no res";
            testDataLab.get("test1").add(i,new JLabel(tmpStr));
            testDataLab.get("test2").add(i,new JLabel(tmpStr));
            testDataLab.get("test3").add(i,new JLabel(tmpStr));
        }
        for(int i=1; i<5;i++ )
        {
            if (i<=2) tmpStr = "no value";
            else tmpStr = "no res";
            testDataLab.get("test4").add(i,new JLabel(tmpStr));
        }
    }

    public static void main(String[] args)
    {
        String[] newArgs = new String[]{"",""};
        if (args.length != 0)
        {
            newArgs[0] = args[0] + File.separator;
            newArgs[1] = args[1] + File.separator;
        }
        TestWindow mw = new TestWindow(newArgs[0], newArgs[1]);
        mw.setVisible(true);
    }

}
