package gui;

import regexp.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import javax.swing.filechooser.FileFilter;

/**
 * @author Murashov Kirill
 */

public class MainWindow extends JFrame
{
    private AbstractAction closeAction;
    private AbstractAction generateAction;
    private AbstractAction testAction;
    private JTextField regTField;
    private JTextField wordTField;
    private NFA nfa;
    private DFA dfa;
    private MFA mfa;
    private String resTest;
    private JLabel resTestLab;
    private JLabel nfaResLab;
    private JLabel mfaResLab;
    private AbstractAction nfaAction;
    private AbstractAction dfaAction;
    private AbstractAction mfaAction;
    private AbstractAction exportAction;
    private AbstractAction texAction;

    private Runtime runtime;
    private Desktop desktop;

    private String dotPath;
    private String texPath;
    private JLabel dfaResLab;

    public MainWindow(String dotPath, String texPath) throws HeadlessException
    {
        setTitle("Mulya");

        this.dotPath = dotPath;
        this.texPath = texPath;

        setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);

        int width = 600;
        int height = 400;
        setSize(width, height);
        int scrWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int scrHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((scrWidth - width) / 2, (scrHeight - height) / 2);

        JPanel pane = (JPanel)getContentPane();
        initActions();
        initGUI(pane);

        runtime = Runtime.getRuntime();
        desktop = Desktop.getDesktop();
    }

    private void initActions()
    {
        closeAction = new AbstractAction("Выход")
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        };

        generateAction = new AbstractAction("Генерировать")
        {
            public void actionPerformed(ActionEvent e)
            {
                String regStr = regTField.getText();
                Parser parser = new Parser(regStr);
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

                try
                {
                    String res = nfa.produceDotty();
                    FileOutputStream fout = new FileOutputStream(new File("./nfa.dot"));
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(fout, "cp1251"));
                    pw.print(res);
                    pw.close();
                    fout.close();

                    String command = "dot.exe ./nfa.dot -T gif -o nfa.gif";
                    runtime.exec(dotPath + File.separator + command);

                    res = dfa.produceDotty();
                    fout = new FileOutputStream(new File("./dfa.dot"));
                    pw = new PrintWriter(new OutputStreamWriter(fout, "cp1251"));
                    pw.print(res);
                    pw.close();
                    fout.close();

                    command = "dot.exe ./dfa.dot -T gif -o dfa.gif";
                    runtime.exec(dotPath + File.separator + command);

                    res = mfa.produceDotty();
                    fout = new FileOutputStream(new File("./mfa.dot"));
                    pw = new PrintWriter(new OutputStreamWriter(fout, "cp1251"));
                    pw.print(res);
                    pw.close();
                    fout.close();

                    command = "dot.exe ./mfa.dot -T gif -o mfa.gif";
                    runtime.exec(dotPath + File.separator + command);


                    TexTable tt = new TexTable(nfa);

                    res = tt.getTexTable();
                    fout = new FileOutputStream(new File("./table.tex"));
                    pw = new PrintWriter(new OutputStreamWriter(fout, "cp1251"));
                    pw.print(res);
                    pw.close();
                    fout.close();


                    command = "latex.exe ./table.tex";
                    Process p = runtime.exec(texPath + File.separator + command);

                    InputStream is = p.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    String line;
                    while ((line = br.readLine()) != null)
                    {
                    }

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


                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }

                dfaAction.setEnabled(true);
                nfaAction.setEnabled(true);
                mfaAction.setEnabled(true);
                texAction.setEnabled(true);
                exportAction.setEnabled(true);
            }
        };

        exportAction = new AbstractAction("Сохранить")
        {
            public void actionPerformed(ActionEvent e)
            {
                File[] dirs = chooseFile(MainWindow.this, "Сохранить", null, true, false, false, JFileChooser.DIRECTORIES_ONLY);
                if ( dirs.length != 0 )
                {

                    try
                    {
                        FileInputStream fin = new FileInputStream("./nfa.gif");
                        FileOutputStream fout = new FileOutputStream(new File(dirs[0]+File.separator+"nfa.gif"));
                        copyFile(fout,fin);
                        fin.close();
                        fout.close();

                        fin = new FileInputStream("./dfa.gif");
                        fout = new FileOutputStream(new File(dirs[0]+File.separator+"dfa.gif"));
                        copyFile(fout,fin);
                        fin.close();
                        fout.close();

                        fin = new FileInputStream("./mfa.gif");
                        fout = new FileOutputStream(new File(dirs[0]+File.separator+"mfa.gif"));
                        copyFile(fout,fin);
                        fin.close();
                        fout.close();

                        fin = new FileInputStream("./table.tex");
                        fout = new FileOutputStream(new File(dirs[0]+File.separator+"table.tex"));
                        copyFile(fout,fin);
                        fin.close();
                        fout.close();

                    }
                    catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }

                }

            }
        };
        exportAction.setEnabled(false);

        testAction = new AbstractAction("Проверить")
        {
            public void actionPerformed(ActionEvent e)
            {
                String testWord = wordTField.getText();

                if (dfa.check(testWord))
                {
                    resTest = "принадлежит";
                }
                else
                {
                    resTest = "не принадлежит";
                }
                resTestLab.setText(resTest);

                double[] stat = Statistics.autoTest(testWord,nfa,dfa,mfa);

                nfaResLab.setText(stat[0] + "");
                dfaResLab.setText(stat[1] + "");
                mfaResLab.setText(stat[2] + "");

            }
        };

        nfaAction = new AbstractAction("Показать НКА")
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
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

    private void initGUI(JPanel pane)
    {
        pane.setLayout(new GridBagLayout());

        int j = 0;
        int i = 0;
        JLabel regLab = new JLabel("Регулярное выражение:");
        pane.add(regLab, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.EAST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        i++;
        regTField = new JTextField(16);
        pane.add(regTField, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.WEST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        i++;
        JButton generateButton = new JButton(generateAction);
        pane.add(generateButton, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.WEST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        i--;
        j++;
        JButton nfaButton = new JButton(nfaAction);
        pane.add(nfaButton, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.WEST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        j++;
        JButton dfaButton = new JButton(dfaAction);
        pane.add(dfaButton, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.WEST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        j++;
        JButton mfaButton = new JButton(mfaAction);
        pane.add(mfaButton, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.WEST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        j++;
        JButton texButton = new JButton(texAction);
        pane.add(texButton, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.WEST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        i++;
        JButton exportButton = new JButton(exportAction);
        pane.add(exportButton, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.WEST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));



        i-=2;
        j++;
        JLabel wordLab = new JLabel("Слово:");
        pane.add(wordLab, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.EAST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        i++;
        wordTField = new JTextField(16);
        pane.add(wordTField, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.WEST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        i++;
        JButton testButton = new JButton(testAction);
        pane.add(testButton, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.WEST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));


        j++;
        resTestLab = new JLabel();
        pane.add(resTestLab, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.WEST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        i-=2;
        JLabel nfaLab = new JLabel("Пропускная способность НКА");
        pane.add(nfaLab, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.EAST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        i++;
        nfaResLab = new JLabel();
        pane.add(nfaResLab, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.EAST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        i--;
        j++;
        JLabel dfaLab = new JLabel("Пропускная способность ДКА");
        pane.add(dfaLab, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.EAST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        i++;
        dfaResLab = new JLabel();
        pane.add(dfaResLab, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.EAST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        i--;
        j++;
        JLabel mfaLab = new JLabel("Пропускная способность МДКА");
        pane.add(mfaLab, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.EAST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        i++;
        mfaResLab = new JLabel();
        pane.add(mfaResLab, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.EAST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));

        i++;
        j++;
        JButton closeButton = new JButton(closeAction);
        pane.add(closeButton, new GridBagConstraints(i, j, 1, 1, 0, 0,
            GridBagConstraints.EAST, GridBagConstraints.NONE,
            new Insets(5, 5, 5, 5), 0, 0));
    }

    public static File[] chooseFile(Component owner, String title, FileFilter[] filters,
                                    boolean acceptAllFileFilterUsed,
                                    boolean isMultiFileSelection, boolean isOpenDialog,
                                    int fileChooserMode)
    {
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setDialogTitle(title);
        fileChooser.setMultiSelectionEnabled(isMultiFileSelection);
        fileChooser.setFileSelectionMode(fileChooserMode);

        fileChooser.setAcceptAllFileFilterUsed(acceptAllFileFilterUsed);

        if ( filters != null )
        {
            for ( FileFilter filter : filters )
            {
                fileChooser.addChoosableFileFilter(filter);
            }
        }

        if (isOpenDialog && fileChooser.showOpenDialog(owner) == JFileChooser.APPROVE_OPTION)
        {
            return ( isMultiFileSelection )? fileChooser.getSelectedFiles() : new File[]{fileChooser.getSelectedFile()};
        }
        else if ( !isOpenDialog && fileChooser.showSaveDialog(owner) == JFileChooser.APPROVE_OPTION)
        {
            return new File[]{fileChooser.getSelectedFile()};
        }

        return new File[0];
    }

    private static void copyFile(OutputStream os, InputStream is) throws IOException
    {
        byte[] buf = new byte[8000];
        int nLength;
        while( true )
        {
            nLength = is.read(buf);

            if( nLength < 0 )
                break;

            os.write(buf, 0, nLength);
        }
    }

    public static void main(String[] args)
    {
        MainWindow mw = new MainWindow(args[0], args[1]);
        mw.setVisible(true);
    }
}
