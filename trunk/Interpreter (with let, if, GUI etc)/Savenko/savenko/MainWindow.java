/*
 * MainWindow.java
 *
 * Created on 11.04.2010, 15:55:40
 */
package savenko;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.text.BadLocationException;
import savenko.ast.Program;
import savenko.ast.Position;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.plaf.basic.BasicTextUI.BasicHighlighter;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.Highlighter.HighlightPainter;

public class MainWindow extends javax.swing.JFrame implements IMainView {

     Program programm = null;
     Controler controler = null;
     String current_file_path = "src/savenko/1.txt";
     Boolean ifProgrammChanged = false;
     String file_programText = "";

     /** Creates new form MainWindow */
     public MainWindow() {
          initComponents();
          this.setIconImage(new ImageIcon(MainWindow.class.getResource("img/Ico.jpg")).getImage());
          controler = new Controler(this);
          programmTextField.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
				ifProgrammChanged = !compareProgramText();
			}
		});
     }
     
     private boolean compareProgramText(){
    	 return programmTextField.getText().equals(file_programText);
     }

     /** WARNING: Do NOT modify this code. The content of this method is
      * always regenerated by the Form Editor.
      */
     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
               openFDialogFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 400, Short.MAX_VALUE)
          );
          openFDialogFrameLayout.setVerticalGroup(
               openFDialogFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 300, Short.MAX_VALUE)
          );

          aboutDialog.setTitle("About");
          aboutDialog.setAlwaysOnTop(true);
          aboutDialog.setLocationByPlatform(true);
          aboutDialog.setMinimumSize(new java.awt.Dimension(451, 341));
          aboutDialog.setModal(true);
          aboutDialog.setResizable(false);

          imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/savenko/img/About.jpg"))); // NOI18N

          okAboutDButton.setText("OK");
          okAboutDButton.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    okAboutDButtonClick(evt);
               }
          });

          jTextArea2.setBackground(java.awt.SystemColor.activeCaption);
          jTextArea2.setColumns(20);
          jTextArea2.setFont(new java.awt.Font("Bookman Old Style", 1, 10));
          jTextArea2.setRows(5);
          jTextArea2.setText("About:\n\nMain programmer: Savenko Maria\nSupervisor: Victor Polozov\n\nUsed: Eclipse 3.5, NetBeans IDE 6.8\n\nWas created in February - May 2010\n\n\n\n\n\n\n\nall image's rights belong to their \ncreators");
          jTextArea2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
          jTextArea2.setEnabled(false);
          jScrollPane4.setViewportView(jTextArea2);

          javax.swing.GroupLayout aboutDialogLayout = new javax.swing.GroupLayout(aboutDialog.getContentPane());
          aboutDialog.getContentPane().setLayout(aboutDialogLayout);
          aboutDialogLayout.setHorizontalGroup(
               aboutDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 451, Short.MAX_VALUE)
               .addGroup(aboutDialogLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(imageLabel)
                    .addGroup(aboutDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(aboutDialogLayout.createSequentialGroup()
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(aboutDialogLayout.createSequentialGroup()
                              .addGap(69, 69, 69)
                              .addComponent(okAboutDButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );
          aboutDialogLayout.setVerticalGroup(
               aboutDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 311, Short.MAX_VALUE)
               .addGroup(aboutDialogLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(aboutDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                         .addGroup(aboutDialogLayout.createSequentialGroup()
                              .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(okAboutDButton))
                         .addComponent(imageLabel))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );

          saveDialog.setTitle("save?");
          saveDialog.setAlwaysOnTop(true);
          saveDialog.setMinimumSize(new java.awt.Dimension(373, 117));
          saveDialog.setModal(true);
          saveDialog.setResizable(false);

          okSaveDButton.setText("Save&Exit");
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
               saveDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(saveDialogLayout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addGroup(saveDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addGroup(saveDialogLayout.createSequentialGroup()
                              .addComponent(okSaveDButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(cancelSaveDButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addComponent(saveDLabel))
                    .addContainerGap(52, Short.MAX_VALUE))
          );
          saveDialogLayout.setVerticalGroup(
               saveDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, saveDialogLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(saveDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(saveDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(okSaveDButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(cancelSaveDButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
          );

          setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
          setTitle("Interpreter");
          setMinimumSize(new java.awt.Dimension(492, 469));
          setName("main_frame"); // NOI18N
          addWindowListener(new java.awt.event.WindowAdapter() {
               public void windowClosing(java.awt.event.WindowEvent evt) {
                    mainViewClosing(evt);
               }
          });

          programmTextField.setName("programmTextField"); // NOI18N
          jScrollPane1.setViewportView(programmTextField);
          programmTextField.getAccessibleContext().setAccessibleName("ProgrammTextField");

          jScrollPane2.setMinimumSize(new java.awt.Dimension(114, 74));

          consoleTextField.setColumns(20);
          consoleTextField.setRows(5);
          consoleTextField.setMinimumSize(new java.awt.Dimension(104, 64));
          jScrollPane2.setViewportView(consoleTextField);
          consoleTextField.getAccessibleContext().setAccessibleName("consoleTextField");

          saveFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/savenko/icons/i24/save.png"))); // NOI18N
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

          openRecentFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/savenko/icons/i24/folder doc.png"))); // NOI18N
          openRecentFileButton.setToolTipText("Open File");
          openRecentFileButton.setMaximumSize(new java.awt.Dimension(31, 31));
          openRecentFileButton.setMinimumSize(new java.awt.Dimension(31, 31));
          openRecentFileButton.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    openRecentFileButtonClick(evt);
               }
          });

          executeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/savenko/icons/i24/play.png"))); // NOI18N
          executeButton.setToolTipText("Run");
          executeButton.setMaximumSize(new java.awt.Dimension(31, 31));
          executeButton.setMinimumSize(new java.awt.Dimension(31, 31));
          executeButton.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    executeButtonClick(evt);
               }
          });

          DebugButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/savenko/icons/i24/stop_1.png"))); // NOI18N
          DebugButton.setToolTipText("Start Debug");
          DebugButton.setFocusable(false);
          DebugButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          DebugButton.setMaximumSize(new java.awt.Dimension(31, 31));
          DebugButton.setMinimumSize(new java.awt.Dimension(31, 31));

          NextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/savenko/icons/i24/forward.png"))); // NOI18N
          NextButton.setToolTipText("Next Step");
          NextButton.setFocusable(false);
          NextButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
          NextButton.setMaximumSize(new java.awt.Dimension(31, 31));
          NextButton.setMinimumSize(new java.awt.Dimension(31, 31));

          javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
          jPanel1.setLayout(jPanel1Layout);
          jPanel1Layout.setHorizontalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(openRecentFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(saveFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(executeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(31, 31, 31)
                    .addComponent(DebugButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(NextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(194, Short.MAX_VALUE))
          );
          jPanel1Layout.setVerticalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                         .addComponent(openRecentFileButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                         .addComponent(executeButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(saveFileButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                         .addComponent(NextButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(DebugButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(20, 20, 20))
          );

          progressBar.setText("progressBar");
          progressBar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

          fileMenu.setText("File");

          openMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/savenko/icons/i16/folder doc_1.png"))); // NOI18N
          openMenuItem.setText("Open");
          openMenuItem.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    openAsClick(evt);
               }
          });
          fileMenu.add(openMenuItem);

          saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
          saveMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/savenko/icons/i16/save.png"))); // NOI18N
          saveMenuItem.setText("Save");
          saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    saveButtonClick(evt);
               }
          });
          fileMenu.add(saveMenuItem);

          saveAsMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/savenko/icons/i16/save as.png"))); // NOI18N
          saveAsMenuItem.setText("Save As...");
          saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    saveAsButtonClick(evt);
               }
          });
          fileMenu.add(saveAsMenuItem);

          recentFilesMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/savenko/icons/i16/file.png"))); // NOI18N
          recentFilesMenuItem.setText("Recent Files");
          fileMenu.add(recentFilesMenuItem);

          exitMEnuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/savenko/icons/i16/stop.png"))); // NOI18N
          exitMEnuItem.setText("Exit");
          exitMEnuItem.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    exitButtonClick(evt);
               }
          });
          fileMenu.add(exitMEnuItem);

          jMenuBar1.add(fileMenu);

          helpMenu.setText("Help");

          aboutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/savenko/icons/i16/info.png"))); // NOI18N
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
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
               .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
               .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                    .addContainerGap())
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
          );

          pack();
     }// </editor-fold>//GEN-END:initComponents

    private void executeButtonClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeButtonClick
         controler.colorKeywords();
         controler.interpret();
    }//GEN-LAST:event_executeButtonClick

     private void debugButtonClick(java.awt.event.ActionEvent evt) {
          controler.debug();
     }

    private void openRecentFileButtonClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openRecentFileButtonClick
         controler.openFile(current_file_path);
         ifProgrammChanged = false;
         file_programText = programmTextField.getText();
         controler.colorKeywords();
    }//GEN-LAST:event_openRecentFileButtonClick

    private void saveButtonClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonClick
         if (!current_file_path.isEmpty())
        	 controler.saveFile(current_file_path, programmTextField.getText());
         else saveAsButtonClick(evt);
    }//GEN-LAST:event_saveButtonClick

    private void exitButtonClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonClick
         if (ifProgrammChanged){
              saveDialog.setLocation(this.getLocation().x + (this.getWidth()-saveDialog.getWidth())/2,
                      this.getLocation().y+(this.getHeight()-saveDialog.getHeight())/2);
              saveDialog.show();
         }else
              System.exit(0);
    }//GEN-LAST:event_exitButtonClick

    final JFileChooser fc = new JFileChooser();
    private void openAsClick(java.awt.event.ActionEvent evt) {                              
         fc.showOpenDialog(this.openFDialogFrame);
         //fc.setAcceptAllFileFilterUsed(false);

         File file = fc.getSelectedFile();
         if (file != null){
              current_file_path = file.getPath();
              controler.openFile(current_file_path);

              final JMenuItem new_item = new JMenuItem(file.getName());
              new_item.setToolTipText(current_file_path);
              new_item.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                         recentFilesItemClick(new_item.getToolTipText());
                    }
               });
              ifProgrammChanged = false;
              recentFilesMenuItem.add(new_item);
              file_programText = programmTextField.getText();
         }

         controler.colorKeywords();
    }                            

    private void recentFilesItemClick(String filepath){
         current_file_path = filepath;
         openRecentFileButtonClick(null);
    }

    private void aboutMIClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMIClick
         aboutDialog.setLocation(this.getLocation().x + (this.getWidth()-aboutDialog.getWidth())/2,
                      this.getLocation().y+(this.getHeight()-aboutDialog.getHeight())/2);
         aboutDialog.show();
    }//GEN-LAST:event_aboutMIClick

    private void okAboutDButtonClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okAboutDButtonClick
         aboutDialog.hide();
    }//GEN-LAST:event_okAboutDButtonClick

    private void saveAsButtonClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsButtonClick
         programmTextField.setText(null);
         fc.showSaveDialog(this.openFDialogFrame);

         File file = fc.getSelectedFile();
         if (file != null)
              controler.saveFile(file.getPath(),programmTextField.getText());
    }//GEN-LAST:event_saveAsButtonClick

    private void mainViewClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_mainViewClosing
         exitButtonClick(null);
    }//GEN-LAST:event_mainViewClosing

    private void saveDialogOkButtonClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveDialogOkButtonClicked
         saveDialog.hide();
         saveButtonClick(evt);
         System.exit(0);
    }//GEN-LAST:event_saveDialogOkButtonClicked

    private void saveDialogExitButtonClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveDialogExitButtonClick
         saveDialog.hide();
         System.exit(0);
    }//GEN-LAST:event_saveDialogExitButtonClick

    private void saveDialogCancelButtonClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveDialogCancelButtonClick
         saveDialog.hide();
    }//GEN-LAST:event_saveDialogCancelButtonClick

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
          String str_position = "";
          if (position != null) {
               str_position =
                       "( row: " + (position.getRowNum() + 1)
                       + " column: " + (position.getColNumBeg() + 1) + " )";
               highlightText(position.getCurrInd(),position.getEndInd());
          }
          
          String full_error = "Error!: " + error + " " + str_position;

          if (consoleTextField.getText().isEmpty()) {
               consoleTextField.setText(full_error);
          } else {
               consoleTextField.setText(consoleTextField.getText() + "\n" + full_error);
          }
     }

     @Override
     public void highlightText(int start_ind, int end_index){
          Highlighter highlighter = new BasicHighlighter();
          programmTextField.setHighlighter ( highlighter );
          try {
               if (end_index==0)
                    end_index++;
               highlighter.addHighlight(start_ind, end_index, new DefaultHighlighter.DefaultHighlightPainter(Color.red));
          } catch (BadLocationException ex) {
               Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
          }
     }
     //Highlighter highlighter2 = new BasicHighlighter();
     @Override
     public void colorKeyword(int start_ind, int end_index){
    	 //programmTextField.select(start_ind, end_index);
    	 //programmTextField.setHighlighter ( highlighter2 );
    	 //programmTextField.setSelectedTextColor(Color.BLUE);
    	 //programmTextField.getSelectedTextColor();
     }

     @Override
     public void setProgressBarText(String progress){
          progressBar.setText(progress);
     }

     @Override
     public void resetProgressBar(){
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
     // Variables declaration - do not modify//GEN-BEGIN:variables
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
     // End of variables declaration//GEN-END:variables
}
