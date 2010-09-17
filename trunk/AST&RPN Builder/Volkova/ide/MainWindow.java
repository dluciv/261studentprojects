package ide;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.plaf.basic.BasicTextUI.BasicHighlighter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class MainWindow extends javax.swing.JFrame implements IMainView {

    Program programm = null;
    Controller controller = null;
    String currFilePath = "";
    Boolean ifProgrammChanged = false;
    String fileProgText = "";

    public MainWindow() {
        initComponents();
//        this.setIconImage(new ImageIcon(MainWindow.class.getResource("/home/kate/Загрузки/1.png")).getImage());
        controller = new Controller(this);
        programmTextField.addCaretListener(new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent e) {
                ifProgrammChanged = !compareProgramText();
            }
        });
    }

    private boolean compareProgramText() {
        return programmTextField.getText().equals(fileProgText);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        openFDialogFrame = new javax.swing.JFrame();
        aboutDialog = new javax.swing.JDialog();
        imageLabel = new javax.swing.JLabel();
        okAboutDButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        saveDialog = new javax.swing.JDialog();
        okSaveDButton = new javax.swing.JButton();
        cancelSaveDButton = new javax.swing.JButton();
        saveDLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        programmTextField = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        consoleTextField = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        saveFileButton = new javax.swing.JButton();
        openRecentFileButton = new javax.swing.JButton();
        executeButton = new javax.swing.JButton();
        DebugButton = new javax.swing.JButton();
        NextButton = new javax.swing.JButton();
        progressBar = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        recentFilesMenuItem = new javax.swing.JMenu();
        exitMEnuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        openFDialogFrame.setTitle("Open File");

        javax.swing.GroupLayout openFDialogFrameLayout = new javax.swing.GroupLayout(openFDialogFrame.getContentPane());
        openFDialogFrame.getContentPane().setLayout(openFDialogFrameLayout);
        openFDialogFrameLayout.setHorizontalGroup(
                openFDialogFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
        openFDialogFrameLayout.setVerticalGroup(
                openFDialogFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));

        aboutDialog.setTitle("About");
        aboutDialog.setAlwaysOnTop(true);
        aboutDialog.setLocationByPlatform(true);
        aboutDialog.setMinimumSize(new java.awt.Dimension(451, 341));
        aboutDialog.setModal(true);
        aboutDialog.setResizable(false);

//        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/kate/2.jpg"))); // NOI18N

        okAboutDButton.setText("Okey");
        okAboutDButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okAboutDButtonClick(evt);
            }
        });

        jTextArea2.setBackground(java.awt.SystemColor.activeCaption);
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Bookman Old Style", 1, 10));
        jTextArea2.setRows(5);
        jTextArea2.setText("About: \n NetBeabns IDE 6.5\n\n");
        jTextArea2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextArea2.setEnabled(false);
        jScrollPane4.setViewportView(jTextArea2);

        javax.swing.GroupLayout aboutDialogLayout = new javax.swing.GroupLayout(aboutDialog.getContentPane());
        aboutDialog.getContentPane().setLayout(aboutDialogLayout);
        aboutDialogLayout.setHorizontalGroup(
                aboutDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 451, Short.MAX_VALUE).addGroup(aboutDialogLayout.createSequentialGroup().addContainerGap().addComponent(imageLabel).addGroup(aboutDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(aboutDialogLayout.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(aboutDialogLayout.createSequentialGroup().addGap(69, 69, 69).addComponent(okAboutDButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        aboutDialogLayout.setVerticalGroup(
                aboutDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 311, Short.MAX_VALUE).addGroup(aboutDialogLayout.createSequentialGroup().addContainerGap().addGroup(aboutDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(aboutDialogLayout.createSequentialGroup().addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(okAboutDButton)).addComponent(imageLabel)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        saveDialog.setTitle("save?");
        saveDialog.setAlwaysOnTop(true);
        saveDialog.setMinimumSize(new java.awt.Dimension(373, 117));
        saveDialog.setModal(true);
        saveDialog.setResizable(false);

        okSaveDButton.setText("Save and Exit");
        okSaveDButton.setMaximumSize(new java.awt.Dimension(65, 23));
        okSaveDButton.setMinimumSize(new java.awt.Dimension(65, 23));
        okSaveDButton.setPreferredSize(new java.awt.Dimension(65, 23));
        okSaveDButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveDialogOkButtonClicked(evt);
            }
        });

        cancelSaveDButton.setText("Exit");
        cancelSaveDButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveDialogExitButtonClick(evt);
            }
        });

        saveDLabel.setFont(new java.awt.Font("Bookman Old Style", 1, 12));
        saveDLabel.setText("Document was changed. Save the changes?");

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveDialogCancelButtonClick(evt);
            }
        });

        javax.swing.GroupLayout saveDialogLayout = new javax.swing.GroupLayout(saveDialog.getContentPane());
        saveDialog.getContentPane().setLayout(saveDialogLayout);
        saveDialogLayout.setHorizontalGroup(
                saveDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(saveDialogLayout.createSequentialGroup().addGap(41, 41, 41).addGroup(saveDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(saveDialogLayout.createSequentialGroup().addComponent(okSaveDButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(cancelSaveDButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)).addComponent(saveDLabel)).addContainerGap(52, Short.MAX_VALUE)));
        saveDialogLayout.setVerticalGroup(
                saveDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, saveDialogLayout.createSequentialGroup().addGap(17, 17, 17).addComponent(saveDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(saveDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(okSaveDButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(cancelSaveDButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap()));

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Interpreter");
        setMinimumSize(new java.awt.Dimension(492, 469));
        setName("mainFrame");
        addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                mainViewClosing(evt);
            }
        });

        programmTextField.setName("programmTextField");
        jScrollPane1.setViewportView(programmTextField);
        programmTextField.getAccessibleContext().setAccessibleName("ProgrammTextField");

        jScrollPane2.setMinimumSize(new java.awt.Dimension(114, 74));

        consoleTextField.setColumns(20);
        consoleTextField.setRows(5);
        consoleTextField.setMinimumSize(new java.awt.Dimension(104, 64));
        jScrollPane2.setViewportView(consoleTextField);
        consoleTextField.getAccessibleContext().setAccessibleName("consoleTextField");

        saveFileButton.setToolTipText("Save");
        saveFileButton.setFocusable(false);
        saveFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveFileButton.setMaximumSize(new java.awt.Dimension(31, 31));
        saveFileButton.setMinimumSize(new java.awt.Dimension(31, 31));
        saveFileButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonClick(evt);
            }
        });

//        openRecentFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/kate/загрузки/4.png")));
        openRecentFileButton.setToolTipText("Open File");
        openRecentFileButton.setMaximumSize(new java.awt.Dimension(31, 31));
        openRecentFileButton.setMinimumSize(new java.awt.Dimension(31, 31));
        openRecentFileButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    openRecentFileButtonClick(evt);
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

//        executeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/kate/5.png")));
        executeButton.setToolTipText("Run");
        executeButton.setMaximumSize(new java.awt.Dimension(31, 31));
        executeButton.setMinimumSize(new java.awt.Dimension(31, 31));
        executeButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeButtonClick(evt);
            }
        });

//        DebugButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/kate/7.png")));
        DebugButton.setToolTipText("Start Debug");
        DebugButton.setFocusable(false);
        DebugButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DebugButton.setMaximumSize(new java.awt.Dimension(31, 31));
        DebugButton.setMinimumSize(new java.awt.Dimension(31, 31));

//        NextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/kate/8.png")));
        NextButton.setToolTipText("Next Step");
        NextButton.setFocusable(false);
        NextButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        NextButton.setMaximumSize(new java.awt.Dimension(31, 31));
        NextButton.setMinimumSize(new java.awt.Dimension(31, 31));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(10, 10, 10).addComponent(openRecentFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(saveFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(executeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(31, 31, 31).addComponent(DebugButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(NextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(194, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(openRecentFileButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE).addComponent(executeButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(saveFileButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE).addComponent(NextButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(DebugButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGap(20, 20, 20)));

        progressBar.setText("progressBar");
        progressBar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        fileMenu.setText("File");

//        openMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/kate/9.png")));
        openMenuItem.setText("Open");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    openAsClick(evt);
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
//        saveMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/kate/10.png")));
        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonClick(evt);
            }
        });
        fileMenu.add(saveMenuItem);

//        saveAsMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/kate/11.png")));
        saveAsMenuItem.setText("Save As...");
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsButtonClick(evt);
            }
        });
        fileMenu.add(saveAsMenuItem);

//        recentFilesMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/kate/12.png")));
        recentFilesMenuItem.setText("Recent Files");
        fileMenu.add(recentFilesMenuItem);

//        exitMEnuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/kate/13.png")));
        exitMEnuItem.setText("Exit");
        exitMEnuItem.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonClick(evt);
            }
        });
        fileMenu.add(exitMEnuItem);

        jMenuBar1.add(fileMenu);

        helpMenu.setText("Help");

//        aboutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/kate/14.png")));
        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMIClick(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE).addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE).addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));

        pack();
    }

    private void executeButtonClick(java.awt.event.ActionEvent evt) {
        controller.colorKeywords();
        controller.interpret();
    }

    private void debugButtonClick(java.awt.event.ActionEvent evt) {
        controller.debug();
    }

    private void openRecentFileButtonClick(java.awt.event.ActionEvent evt) throws IOException {
        controller.openFile(currFilePath);
        ifProgrammChanged = false;
        fileProgText = programmTextField.getText();
        controller.colorKeywords();
    }

    private void saveButtonClick(java.awt.event.ActionEvent evt) {
        if (!currFilePath.isEmpty()) {
            controller.saveFile(currFilePath, programmTextField.getText());
        } else {
            saveAsButtonClick(evt);
        }
    }

    private void exitButtonClick(java.awt.event.ActionEvent evt) {
        if (ifProgrammChanged) {
            saveDialog.setLocation(this.getLocation().x + (this.getWidth() - saveDialog.getWidth()) / 2,
                    this.getLocation().y + (this.getHeight() - saveDialog.getHeight()) / 2);
            saveDialog.show();
        } else {
            System.exit(0);
        }
    }
    final JFileChooser fc = new JFileChooser();

    private void openAsClick(java.awt.event.ActionEvent evt) throws IOException {
        fc.showOpenDialog(this.openFDialogFrame);

        File file = fc.getSelectedFile();
        if (file != null) {
            currFilePath = file.getPath();
            controller.openFile(currFilePath);

            final JMenuItem newItem = new JMenuItem(file.getName());
            newItem.setToolTipText(currFilePath);
            newItem.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {
                        recentFilesItemClick(newItem.getToolTipText());
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            ifProgrammChanged = false;
            recentFilesMenuItem.add(newItem);
            fileProgText = programmTextField.getText();
        }

        controller.colorKeywords();
    }

    private void recentFilesItemClick(String filepath) throws IOException {
        currFilePath = filepath;
        openRecentFileButtonClick(null);
    }

    private void aboutMIClick(java.awt.event.ActionEvent evt) {
        aboutDialog.setLocation(this.getLocation().x + (this.getWidth() - aboutDialog.getWidth()) / 2,
                this.getLocation().y + (this.getHeight() - aboutDialog.getHeight()) / 2);
        aboutDialog.show();
    }

    private void okAboutDButtonClick(java.awt.event.ActionEvent evt) {
        aboutDialog.hide();
    }

    private void saveAsButtonClick(java.awt.event.ActionEvent evt) {
        fc.showSaveDialog(this.openFDialogFrame);

        File file = fc.getSelectedFile();
        if (file != null) {
            controller.saveFile(file.getPath() + ".int", programmTextField.getText());
        }
    }

    private void mainViewClosing(java.awt.event.WindowEvent evt) {
        exitButtonClick(null);
    }

    private void saveDialogOkButtonClicked(java.awt.event.ActionEvent evt) {
        saveDialog.hide();
        saveButtonClick(evt);
        System.exit(0);
    }

    private void saveDialogExitButtonClick(java.awt.event.ActionEvent evt) {
        saveDialog.hide();
        System.exit(0);
    }

    private void saveDialogCancelButtonClick(java.awt.event.ActionEvent evt) {
        saveDialog.hide();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    @Override
    public String getProgramText() {
        return programmTextField.getText();
    }

    @Override
    public void printError(String error, Position position) {
        String strPosition = "";
        if (position != null) {
            strPosition =
                    "( row: " + (position.getLineNum() + 1) + " column: " + (position.getColumnNumBegin() + 1) + " )";
            highlightText(position.getCurrInd(), position.getEndInd());
        }

        String fullErr = "Error!: " + error + " " + strPosition;

        if (consoleTextField.getText().isEmpty()) {
            consoleTextField.setText(fullErr);
        } else {
            consoleTextField.setText(consoleTextField.getText() + "\n" + fullErr);
        }
    }

    @Override
    public void highlightText(int startInd, int endIndex) {
        Highlighter highlighter = new BasicHighlighter();
        programmTextField.setHighlighter(highlighter);
        try {
            if (endIndex == 0) {
                endIndex++;
            }
            highlighter.addHighlight(startInd, endIndex, new DefaultHighlighter.DefaultHighlightPainter(Color.red));
        } catch (BadLocationException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setProgressBarText(String progress) {
        progressBar.setText(progress);
    }

    @Override
    public void resetProgressBar() {
        progressBar.setText(null);
    }

    @Override
    public void resetConsole() {
        consoleTextField.setText(null);
    }

    @Override
    public void setProgramText(String program) {
        programmTextField.setText(program);
    }

    @Override
    public void printResult(String result) {
        consoleTextField.setText(result);
    }
    private javax.swing.JButton DebugButton;
    private javax.swing.JButton NextButton;
    private javax.swing.JDialog aboutDialog;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton cancelSaveDButton;
    private javax.swing.JTextArea consoleTextField;
    private javax.swing.JButton executeButton;
    private javax.swing.JMenuItem exitMEnuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JButton okAboutDButton;
    private javax.swing.JButton okSaveDButton;
    private javax.swing.JFrame openFDialogFrame;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JButton openRecentFileButton;
    private javax.swing.JTextPane programmTextField;
    private javax.swing.JLabel progressBar;
    private javax.swing.JMenu recentFilesMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JLabel saveDLabel;
    private javax.swing.JDialog saveDialog;
    private javax.swing.JButton saveFileButton;
    private javax.swing.JMenuItem saveMenuItem;

    public void colorKeyword(int startInd, int endIndex) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
}
