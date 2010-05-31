/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainForm.java
 *
 * Created on 02.05.2010, 17:39:21
 */
package karymov;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.*;
import tools.ErrorLogCell;
import yaskov.Interpreter;

/**
 *
 * @author Антон
 */
public class MainForm extends javax.swing.JFrame implements IMainForm {

    Controller controller = null;
    String currentFileName = "";
    String textOpenedProgramm;
    byte buf[];
    int height;
    int width;
    int coordinateX;
    int coordinateY;
    LinkedList<String> recentOpenedFileList;

    public MainForm() {
        initComponents();
        controller = new Controller(this);
        height = Memento.getMemento().getHeight();
        width = Memento.getMemento().getWidth();
        coordinateX = Memento.getMemento().getCoordinateX();
        coordinateY = Memento.getMemento().getCoordinateY();
        recentOpenedFileList = Memento.getMemento().getList();
    }
 public  Controller getController(){
        return controller;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FrameAbout = new javax.swing.JFrame();
        FirstDeveloper = new javax.swing.JLabel();
        SecondDeveloper = new javax.swing.JLabel();
        ThirdDeveloper = new javax.swing.JLabel();
        CloseAboutButton = new javax.swing.JButton();
        SaveFrame = new javax.swing.JFrame();
        SaveFileChooser = new javax.swing.JFileChooser();
        OpenFrame = new javax.swing.JFrame();
        OpenFileChooser = new javax.swing.JFileChooser();
        FileChangedWarningFrame = new javax.swing.JFrame();
        SaveWarningButton = new javax.swing.JButton();
        NotSaveButtonWarning = new javax.swing.JButton();
        CanselButtonWarning = new javax.swing.JButton();
        FileChangedLabel = new javax.swing.JLabel();
        jButtonPanel = new javax.swing.JPanel();
        SaveButton = new javax.swing.JButton();
        RunButton = new javax.swing.JButton();
        DebugButton = new javax.swing.JButton();
        StepNextButton = new javax.swing.JButton();
        StepOverButton = new javax.swing.JButton();
        jSplitPane = new javax.swing.JSplitPane();
        jScrollTextPane = new javax.swing.JScrollPane();
        TextPane = new javax.swing.JTextPane();
        jOutputPane = new javax.swing.JTabbedPane();
        jOutputScrollPane = new javax.swing.JScrollPane();
        jTextOutputPane = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableError = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        OpenMenuItem = new javax.swing.JMenuItem();
        SaveMenuItem = new javax.swing.JMenuItem();
        SaveAsMenuItem = new javax.swing.JMenuItem();
        RecentFileMenu = new javax.swing.JMenu();
        ExitMenuItem = new javax.swing.JMenuItem();
        HelpMenu = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();

        FrameAbout.setTitle("About");
        FrameAbout.setMinimumSize(new java.awt.Dimension(400, 300));

        FirstDeveloper.setText("FirstDeveloper");

        SecondDeveloper.setText("SecondDeveloper");

        ThirdDeveloper.setText("ThirdDeveloper");

        CloseAboutButton.setText("Close");
        CloseAboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseAboutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FrameAboutLayout = new javax.swing.GroupLayout(FrameAbout.getContentPane());
        FrameAbout.getContentPane().setLayout(FrameAboutLayout);
        FrameAboutLayout.setHorizontalGroup(
            FrameAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FrameAboutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FirstDeveloper, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SecondDeveloper, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ThirdDeveloper, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
            .addGroup(FrameAboutLayout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(CloseAboutButton)
                .addContainerGap(183, Short.MAX_VALUE))
        );
        FrameAboutLayout.setVerticalGroup(
            FrameAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FrameAboutLayout.createSequentialGroup()
                .addGroup(FrameAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SecondDeveloper, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(ThirdDeveloper, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(FirstDeveloper, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(97, 97, 97)
                .addComponent(CloseAboutButton)
                .addGap(41, 41, 41))
        );

        SaveFrame.setMinimumSize(new java.awt.Dimension(600, 400));

        SaveFileChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        SaveFileChooser.setFileFilter(null);

        javax.swing.GroupLayout SaveFrameLayout = new javax.swing.GroupLayout(SaveFrame.getContentPane());
        SaveFrame.getContentPane().setLayout(SaveFrameLayout);
        SaveFrameLayout.setHorizontalGroup(
            SaveFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(SaveFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(SaveFrameLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(SaveFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        SaveFrameLayout.setVerticalGroup(
            SaveFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(SaveFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(SaveFrameLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(SaveFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        OpenFrame.setMinimumSize(new java.awt.Dimension(600, 400));

        javax.swing.GroupLayout OpenFrameLayout = new javax.swing.GroupLayout(OpenFrame.getContentPane());
        OpenFrame.getContentPane().setLayout(OpenFrameLayout);
        OpenFrameLayout.setHorizontalGroup(
            OpenFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 586, Short.MAX_VALUE)
            .addGroup(OpenFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(OpenFrameLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(OpenFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        OpenFrameLayout.setVerticalGroup(
            OpenFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 377, Short.MAX_VALUE)
            .addGroup(OpenFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(OpenFrameLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(OpenFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        FileChangedWarningFrame.setTitle("Warning!!!");
        FileChangedWarningFrame.setMinimumSize(new java.awt.Dimension(450, 200));

        SaveWarningButton.setText("Сохранить");
        SaveWarningButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveWarningButtonActionPerformed(evt);
            }
        });

        NotSaveButtonWarning.setText("Не сохранять");
        NotSaveButtonWarning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NotSaveButtonWarningActionPerformed(evt);
            }
        });

        CanselButtonWarning.setText("Отмена");
        CanselButtonWarning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CanselButtonWarningActionPerformed(evt);
            }
        });

        FileChangedLabel.setText("FileChangedLabel");

        javax.swing.GroupLayout FileChangedWarningFrameLayout = new javax.swing.GroupLayout(FileChangedWarningFrame.getContentPane());
        FileChangedWarningFrame.getContentPane().setLayout(FileChangedWarningFrameLayout);
        FileChangedWarningFrameLayout.setHorizontalGroup(
            FileChangedWarningFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FileChangedWarningFrameLayout.createSequentialGroup()
                .addGroup(FileChangedWarningFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FileChangedWarningFrameLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(FileChangedLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
                    .addGroup(FileChangedWarningFrameLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(SaveWarningButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(NotSaveButtonWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(CanselButtonWarning, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)))
                .addGap(22, 22, 22))
        );
        FileChangedWarningFrameLayout.setVerticalGroup(
            FileChangedWarningFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FileChangedWarningFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FileChangedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(FileChangedWarningFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveWarningButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NotSaveButtonWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CanselButtonWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Compiler");
        setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        setMinimumSize(new java.awt.Dimension(565, 389));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        SaveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/karymov/icons/filesave.png"))); // NOI18N
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        RunButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/karymov/icons/run.png"))); // NOI18N
        RunButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RunButtonActionPerformed(evt);
            }
        });

        DebugButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/karymov/icons/debug_wiz.png"))); // NOI18N
        DebugButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DebugButtonActionPerformed(evt);
            }
        });

        StepNextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/karymov/icons/redo.png"))); // NOI18N
        StepNextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StepNextButtonActionPerformed(evt);
            }
        });

        StepOverButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/karymov/icons/undo.png"))); // NOI18N
        StepOverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StepOverButtonActionPerformed(evt);
            }
        });

        jSplitPane.setDividerLocation(150);
        jSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane.setPreferredSize(new java.awt.Dimension(120, 453));

        TextPane.setBackground(new java.awt.Color(255, 255, 254));
        TextPane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextPaneKeyReleased(evt);
            }
        });
        jScrollTextPane.setViewportView(TextPane);

        jSplitPane.setTopComponent(jScrollTextPane);

        jTextOutputPane.setBackground(new java.awt.Color(255, 255, 254));
        jTextOutputPane.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        jTextOutputPane.setEnabled(false);
        jOutputScrollPane.setViewportView(jTextOutputPane);

        jOutputPane.addTab("Output", jOutputScrollPane);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 204));

        tableError.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Message", "Column", "Line"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableError.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableErrorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableError);

        jOutputPane.addTab("Error", jScrollPane1);

        jSplitPane.setRightComponent(jOutputPane);
        jOutputPane.getAccessibleContext().setAccessibleName("Output");

        javax.swing.GroupLayout jButtonPanelLayout = new javax.swing.GroupLayout(jButtonPanel);
        jButtonPanel.setLayout(jButtonPanelLayout);
        jButtonPanelLayout.setHorizontalGroup(
            jButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jButtonPanelLayout.createSequentialGroup()
                .addGroup(jButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jButtonPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(RunButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(DebugButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(StepNextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(StepOverButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSplitPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE))
                .addContainerGap())
        );
        jButtonPanelLayout.setVerticalGroup(
            jButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RunButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DebugButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StepNextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StepOverButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE))
        );

        jMenuBar1.setName(""); // NOI18N

        FileMenu.setText("File");

        OpenMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        OpenMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/karymov/icons/File_Open.png"))); // NOI18N
        OpenMenuItem.setText("Open");
        OpenMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenMenuItemActionPerformed(evt);
            }
        });
        FileMenu.add(OpenMenuItem);

        SaveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        SaveMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/karymov/icons/filesave16.png"))); // NOI18N
        SaveMenuItem.setText("Save");
        SaveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveMenuItemActionPerformed(evt);
            }
        });
        FileMenu.add(SaveMenuItem);

        SaveAsMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/karymov/icons/filesaveas.png"))); // NOI18N
        SaveAsMenuItem.setText("Save As...");
        SaveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveAsMenuItemActionPerformed(evt);
            }
        });
        FileMenu.add(SaveAsMenuItem);

        RecentFileMenu.setText("Open Recent File");
        FileMenu.add(RecentFileMenu);

        ExitMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/karymov/icons/exit.png"))); // NOI18N
        ExitMenuItem.setText("Exit");
        ExitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitMenuItem(evt);
            }
        });
        FileMenu.add(ExitMenuItem);

        jMenuBar1.add(FileMenu);

        HelpMenu.setText("Help");

        jMenuItem6.setText("About");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutActionPerformed(evt);
            }
        });
        HelpMenu.add(jMenuItem6);

        jMenuBar1.add(HelpMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OpenMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenMenuItemActionPerformed
        OpenFrame.setTitle("Open File");
        setCenterPosition(OpenFrame);
        javax.swing.filechooser.FileFilter fileFilter = new MyFilter(".txt");
        OpenFileChooser.addChoosableFileFilter(fileFilter);
        if (OpenFileChooser.showOpenDialog(null) != OpenFileChooser.APPROVE_OPTION) {
            return;//Нажали Cancel
        }

        File file = OpenFileChooser.getSelectedFile();
        if (file != null) {
            currentFileName = OpenFileChooser.getSelectedFile().getPath();
            controller.openFile(file);
            if (areWeNeedAddRecentFile(recentOpenedFileList, currentFileName)) {
                addRecentFile(recentOpenedFileList, currentFileName);
                createRecentFile(file);
            }
            textOpenedProgramm = TextPane.getText();
        }
        controller.lightKeywords(TextPane);
    }//GEN-LAST:event_OpenMenuItemActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        if (currentFileName.equals("")) {
            SaveAsMenuItemActionPerformed(null);
        } else {
            controller.saveFile(TextPane.getText(), currentFileName);
            textOpenedProgramm = TextPane.getText();
            jTextOutputPane.setText("File saved in " + currentFileName);
        }
    }//GEN-LAST:event_SaveButtonActionPerformed

        private void ExitMenuItem(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitMenuItem
            String textCurrentProgramm = TextPane.getText();
            if (textCurrentProgramm.equals(textOpenedProgramm) || textCurrentProgramm.equals("")) {
                Memento.getMemento().saveBounds(MainForm.this.getHeight(),
                        MainForm.this.getWidth(), MainForm.this.getX(), MainForm.this.getY(), this.recentOpenedFileList);
                System.exit(0);
            } else {
                FileChangedLabel.setText("Сохранить изменения в файле: " + currentFileName + "???");
                setCenterPosition(FileChangedWarningFrame);
                FileChangedWarningFrame.setVisible(true);
            }

        }//GEN-LAST:event_ExitMenuItem

        private void RunButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RunButtonActionPerformed
            clearOutputPane();
            clearErrorPane();

            String textProgramm = TextPane.getText();
            setTextInOutputPane(controller.runProgram(textProgramm, false));
        }//GEN-LAST:event_RunButtonActionPerformed

        private void CloseAboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseAboutButtonActionPerformed
            FrameAbout.hide();
        }//GEN-LAST:event_CloseAboutButtonActionPerformed

        private void AboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutActionPerformed
            setCenterPosition(FrameAbout);
            FrameAbout.show();
            FirstDeveloper.setVisible(true);
            SecondDeveloper.setVisible(true);
            ThirdDeveloper.setVisible(true);
            CloseAboutButton.setVisible(true);
        }//GEN-LAST:event_AboutActionPerformed

        private void DebugButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DebugButtonActionPerformed
            clearOutputPane();
            clearErrorPane();
            String textPragramm = TextPane.getText();
            setTextInOutputPane(controller.runProgram(textPragramm, true));
        }//GEN-LAST:event_DebugButtonActionPerformed

        private void StepNextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StepNextButtonActionPerformed
            Interpreter.unlockInterpreter();
        }//GEN-LAST:event_StepNextButtonActionPerformed

        private void StepOverButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StepOverButtonActionPerformed
            // TODO add your handling code here:
        }//GEN-LAST:event_StepOverButtonActionPerformed

        private void SaveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveMenuItemActionPerformed
            SaveButtonActionPerformed(null);
        }//GEN-LAST:event_SaveMenuItemActionPerformed

        private void SaveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveAsMenuItemActionPerformed
            SaveFrame.setTitle("Save As...");
            setCenterPosition(SaveFrame);
            javax.swing.filechooser.FileFilter filter = new MyFilter(".txt");
            SaveFileChooser.addChoosableFileFilter(filter);
            if (SaveFileChooser.showSaveDialog(null) != SaveFileChooser.APPROVE_OPTION) {
                return;
            }
            ;
            currentFileName = SaveFileChooser.getSelectedFile().getPath() + ".txt";
            SaveButtonActionPerformed(null);
        }//GEN-LAST:event_SaveAsMenuItemActionPerformed

        private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
            ExitMenuItem(null);
        }//GEN-LAST:event_formWindowClosing

        private void CanselButtonWarningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CanselButtonWarningActionPerformed
            FileChangedWarningFrame.hide();
        }//GEN-LAST:event_CanselButtonWarningActionPerformed

        private void NotSaveButtonWarningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NotSaveButtonWarningActionPerformed
            System.exit(0);
        }//GEN-LAST:event_NotSaveButtonWarningActionPerformed

        private void SaveWarningButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveWarningButtonActionPerformed
            SaveButtonActionPerformed(null);
            FileChangedWarningFrame.hide();
        }//GEN-LAST:event_SaveWarningButtonActionPerformed

        private void tableErrorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableErrorMouseClicked
            SelectLine(TextPane, (Integer) (tableError.getModel().getValueAt(tableError.getSelectedRow(), 2)),
                    (Integer) (tableError.getModel().getValueAt(tableError.getSelectedRow(), 1)));
}//GEN-LAST:event_tableErrorMouseClicked

        private void TextPaneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextPaneKeyReleased
            controller.lightKeywords(TextPane);
}//GEN-LAST:event_TextPaneKeyReleased

    public void SelectLine(JTextPane pane, int line, int column) {

        MutableAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setBackground(attr, Color.yellow);
        StyledDocument doc = pane.getStyledDocument();
        String text = pane.getText();

        doc.setCharacterAttributes(0, text.length(), new SimpleAttributeSet(), true);

        controller.lightKeywords(pane);

        int curLine = 1;
        int startSelection = 0;
        int lengthSelection = 0;
        int curPos = 0;

        while ((curLine < line + 1) && (curPos < text.length())) {
            if (text.charAt(curPos) == '\n') {
                curLine++;
                if ((line == curLine) && (line != 1)) {
                    startSelection = curPos + 2 - line;
                }
            }
            curPos++;
        }
        if (curPos != text.length()) {
            lengthSelection = curPos - startSelection - 1 - line;
        } else {
            lengthSelection = curPos - startSelection + 1 - line;
        }

        pane.setCaretPosition(startSelection + column - 1);
        doc.setCharacterAttributes(startSelection, lengthSelection, attr, false);

        pane.setCharacterAttributes(new SimpleAttributeSet(), true);
        pane.requestFocus();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception exception) {
                }
                Memento.getMemento().loadBounds();
                MainForm mainForm = new MainForm();
                loadReacentFile(mainForm);
                mainForm.TextPane.setFont(new Font("Times New Roman", Font.BOLD, 14));
                mainForm.jTextOutputPane.setFont(new Font("Times New Roman", Font.BOLD, 14));
                mainForm.setBounds(mainForm.coordinateX, mainForm.coordinateY, mainForm.width, mainForm.height);
                mainForm.setTextInErrorPane("dsfdsf", 44, 44);
                //               tableError.removeAll();
//       DefaultTableModel model = (DefaultTableModel)tableError.getModel();
//       while(model.getRowCount()>=0){
//           model.removeRow(model.getRowCount());
//     }
                mainForm.TextPane.requestFocus();
                mainForm.setVisible(true);
            }

            private void loadReacentFile(MainForm mainForm) {
                for (String rfile : mainForm.recentOpenedFileList) {
                    File file = new File(rfile);
                    mainForm.createRecentFile(file);
                }
            }
        });
    }
    public void selectLineForDebug(int line, int column) {

        MutableAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setBackground(attr, Color.green);
        StyledDocument doc = TextPane.getStyledDocument();
        String text = TextPane.getText();

        doc.setCharacterAttributes(0, text.length(), new SimpleAttributeSet(), true);

        controller.lightKeywords(TextPane);

        int curLine = 1;
        int startSelection = 0;
        int lengthSelection = 0;
        int curPos = 0;

        while ((curLine < line + 1) && (curPos < text.length())) {
            if (text.charAt(curPos) == '\n') {
                curLine++;
                if ((line == curLine) && (line != 1)) {
                    startSelection = curPos + 2 - line;
                }
            }
            curPos++;
        }
        if (curPos != text.length()) {
            lengthSelection = curPos - startSelection - 1 - line;
        } else {
            lengthSelection = curPos - startSelection + 1 - line;
        }

        TextPane.setCaretPosition(startSelection + column - 1);
        doc.setCharacterAttributes(startSelection, lengthSelection, attr, false);

        TextPane.setCharacterAttributes(new SimpleAttributeSet(), true);
        TextPane.requestFocus();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CanselButtonWarning;
    private javax.swing.JButton CloseAboutButton;
    private javax.swing.JButton DebugButton;
    private javax.swing.JMenuItem ExitMenuItem;
    private javax.swing.JLabel FileChangedLabel;
    private javax.swing.JFrame FileChangedWarningFrame;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JLabel FirstDeveloper;
    private javax.swing.JFrame FrameAbout;
    private javax.swing.JMenu HelpMenu;
    private javax.swing.JButton NotSaveButtonWarning;
    private javax.swing.JFileChooser OpenFileChooser;
    private javax.swing.JFrame OpenFrame;
    private javax.swing.JMenuItem OpenMenuItem;
    private javax.swing.JMenu RecentFileMenu;
    private javax.swing.JButton RunButton;
    private javax.swing.JMenuItem SaveAsMenuItem;
    private javax.swing.JButton SaveButton;
    private javax.swing.JFileChooser SaveFileChooser;
    private javax.swing.JFrame SaveFrame;
    private javax.swing.JMenuItem SaveMenuItem;
    private javax.swing.JButton SaveWarningButton;
    private javax.swing.JLabel SecondDeveloper;
    private javax.swing.JButton StepNextButton;
    private javax.swing.JButton StepOverButton;
    private javax.swing.JTextPane TextPane;
    private javax.swing.JLabel ThirdDeveloper;
    private javax.swing.JPanel jButtonPanel;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JTabbedPane jOutputPane;
    private javax.swing.JScrollPane jOutputScrollPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollTextPane;
    private javax.swing.JSplitPane jSplitPane;
    private javax.swing.JTextPane jTextOutputPane;
    private javax.swing.JTable tableError;
    // End of variables declaration//GEN-END:variables

    public void setTextInTextPanel(String text) {
        TextPane.setText(text);
    }

    public void setTextInOutputPane(String text) {
        jTextOutputPane.setText(jTextOutputPane.getText()+text);
    }

    public void clearTextPanel() {
        TextPane.setText(null);
    }

    public JTextPane getTextPanel() {
        return TextPane;
    }

    public void setTextInErrorPane(String text, int column, int line) {
        DefaultTableModel model = (DefaultTableModel) tableError.getModel();
        Object[] rowData = {text, column, line};
        model.insertRow(model.getRowCount(), rowData);
        tableError.setModel(model);
    }

    public void clearOutputPane() {
        jTextOutputPane.setText(null);
    }

    public void clearErrorPane() {
        DefaultTableModel model = (DefaultTableModel) tableError.getModel();
        while (model.getRowCount()>0) {
            model.removeRow(0);
        }
        tableError.setModel(model);
    }

    private void createRecentFile(File file) {
        final JMenuItem newItem = new JMenuItem(file.getName());
        newItem.setToolTipText(file.getPath());
        newItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                recentFilesItemClick(newItem.getToolTipText());
            }

            private void recentFilesItemClick(String toolTipText) {
                File file = new File(toolTipText);
                controller.openFile(file);
                currentFileName = toolTipText;
            }
        });
        RecentFileMenu.add(newItem);
    }

    private boolean areWeNeedAddRecentFile(LinkedList<String> list, String addingRecentFile) {
        for (String havingRecentFile : list) {
            if (havingRecentFile.equals(addingRecentFile)) {
                return false;
            }
        }
        return true;
    }

    private void addRecentFile(LinkedList<String> list, String addingRecentFile) {
        for (String havingRecentFile : list) {
            if (havingRecentFile.equals(addingRecentFile)) {
                list.remove(havingRecentFile);
            }
        }
        list.add(addingRecentFile);
    }

    private static void setCenterPosition(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getPreferredSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }

    public void errorRevise(LinkedList<ErrorLogCell> errorList) {
        for (ErrorLogCell cellError : errorList) {
            setTextInErrorPane(cellError.getErrorMessage(), cellError.getPosition().getColumn(), cellError.getPosition().getLine());
        }

    }

}
