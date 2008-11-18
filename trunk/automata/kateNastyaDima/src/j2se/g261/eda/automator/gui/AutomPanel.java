/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AutomPanel.java
 *
 * Created on 10.11.2008, 1:53:57
 */
package j2se.g261.eda.automator.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.Stack;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import j2se.g261.eda.automator.Automator;
import j2se.g261.eda.automator.NoConditionsException;
import j2se.g261.eda.automator.visualizing.dot.DotException;
import j2se.g261.eda.automator.representations.nfa.NFAWalkerException;
import j2se.g261.eda.automator.gui.AlgorithmTableData.AlgorithmResult;
import j2se.g261.eda.automator.gui.AlgorithmTableData.Result;
import j2se.g261.eda.automator.parser.ParserException;
import j2se.g261.eda.automator.util.Globals;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author nastya
 */
public class AutomPanel extends javax.swing.JPanel implements ActionListener {

    private Automator automator = null;
    private Stack<Process> processes;
    private AlgorithmTable table;

    /** Creates new form AutomPanel */
    public AutomPanel() {
        initComponents();
        processes = new Stack<Process>();
        table = new AlgorithmTable();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(table, BorderLayout.WEST);
        table.addSelectionLisener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                AlgorithmResult selected = table.getSelected();
                if (selected == table.ALGORITHM_DFA) {
                    rbDFA.setSelected(true);
                } else if (selected == table.ALGORITHM_NFA) {
                    rbNFA.setSelected(true);
                } else if (selected == table.ALGORITHM_MINIMIZATION) {
                    rbMinimized.setSelected(true);
                } else if (selected == table.ALGORITHM_TABLE) {
                    rbTable.setSelected(true);
                }
            }
        });
        rbMinimized.addActionListener(this);
        rbNFA.addActionListener(this);
        rbDFA.addActionListener(this);
        rbEpsNFA.addActionListener(this);
        rbTable.addActionListener(this);
        btnSetAutomator.addActionListener(this);
        btnVerify.addActionListener(this);
        btnShow.addActionListener(this);
        btnSave.addActionListener(this);
        btnCloseAll.addActionListener(this);
        btnVerify.setEnabled(false);
        btnSave.setEnabled(false);
        btnShow.setEnabled(false);
//        tablePanel.updateUI();
//        imagePanel.updateUI();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(rbMinimized)) {
            table.setSelected(table.ALGORITHM_MINIMIZATION);
            table.updateUI();
        }
        if (e.getSource().equals(rbNFA)) {
            table.setSelected(table.ALGORITHM_NFA);
            table.updateUI();
        }
        if (e.getSource().equals(rbDFA)) {
            table.setSelected(table.ALGORITHM_DFA);
            table.updateUI();
        }
        if (e.getSource().equals(rbTable)) {
            table.setSelected(table.ALGORITHM_TABLE);
            table.updateUI();
        }

        if (e.getSource().equals(btnSetAutomator)) {
            if(tfPattern.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please, enter pattern", 
                        "Attention!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                automator = new Automator(tfPattern.getText());
                automator.compile();
                btnVerify.setEnabled(true);
                btnSave.setEnabled(true);
                btnShow.setEnabled(true);
            } catch (ParserException ex) {
                JOptionPane.showMessageDialog(this, "Wrong pattern", 
                        "Attention!", JOptionPane.ERROR_MESSAGE);
            } catch (NFAWalkerException ex) {
                JOptionPane.showMessageDialog(this, "Internal error during " +
                        "creating NFA walker", "Attention!", JOptionPane.ERROR_MESSAGE);
            } catch (NoConditionsException ex) {
                JOptionPane.showMessageDialog(this, "Internal error during " +
                        "processing pattern", "Attention!", JOptionPane.ERROR_MESSAGE);
            } catch (DotException ex) {
                JOptionPane.showMessageDialog(this, "Internal error during " +
                        "generation dot file", "Attention!", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Internal error during " +
                        "writing in TEMP directory", "Attention!", JOptionPane.ERROR_MESSAGE);
            }

        }

        if (e.getSource().equals(btnVerify)) {
            table.ALGORITHM_DFA.setResult(automator.matchDFAGraph(
                    tfWord.getText()) ? Result.PASSED : Result.NOT_PASSED);
            table.ALGORITHM_NFA.setResult(automator.matchNFAGraph(
                    tfWord.getText()) ? Result.PASSED : Result.NOT_PASSED);
            table.ALGORITHM_TABLE.setResult(automator.matchTable(
                    tfWord.getText()) ? Result.PASSED : Result.NOT_PASSED);
            table.ALGORITHM_MINIMIZATION.setResult(automator.matchMinGraph(
                    tfWord.getText()) ? Result.PASSED : Result.NOT_PASSED);
            table.updateTableUI();
        }


        if (e.getSource().equals(btnShow)) {
            if (rbMinimized.isSelected()) {
                showMinimizedGraphRepresentation();
            } else if (rbNFA.isSelected()) {
                showNFAGraphRepresentation();
            } else if (rbDFA.isSelected()) {
                showDFAGraphRepresentation();
            } else if (rbEpsNFA.isSelected()) {
                showEpsNFAGraphRepresentation();
            } else if (rbTable.isSelected()) {
                showTableRepresentation();
            } else {
                JOptionPane.showMessageDialog(this, "Please choose type of " +
                        "representation first", "Attention!", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource().equals(btnCloseAll)) {
            while (!processes.empty()) {
                processes.pop().destroy();
            }
        }

        if (e.getSource().equals(btnSave)) {
            JFileChooser fc = new JFileChooser();
            fc.setApproveButtonText("Save");
            fc.setMultiSelectionEnabled(false);
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            File fileToSave = null;
            if (rbMinimized.isSelected()) {
                fileToSave = automator.getDotMinGraphFile();
            } else if (rbNFA.isSelected()) {
                fileToSave = automator.getDotNFAFile();
            } else if (rbDFA.isSelected()) {
                fileToSave = automator.getDotDFAFile();
            } else if (rbEpsNFA.isSelected()) {
                fileToSave = automator.getDotEpsNFAFile();
            } else if (rbTable.isSelected()) {
                fileToSave = automator.getTexFile();
            } else {
                JOptionPane.showMessageDialog(this, "Please choose type of " +
                        "representation first", "Attention!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            fc.setSelectedFile(new File(fileToSave.getName()));
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                if (fc.getSelectedFile().exists()) {
                    if (JOptionPane.showConfirmDialog(this, "Overwrite existing file?",
                            "Confirm Overwrite",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE) != JOptionPane.OK_OPTION) {
                        return;

                    }
                    File selected = fc.getSelectedFile();
                    try{
                    copy(fileToSave, selected);
                    }catch (IOException ex){
                        JOptionPane.showMessageDialog(this, ex.getMessage(), 
                                "Error occurs during copying file", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    private void showDFAGraphRepresentation() {
        try {
            processes.push(Runtime.getRuntime().exec(Globals.DOT + " " + automator.getDotDFAFile()));
        } catch (IOException ex) {
            Logger.getLogger(AutomatorPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showEpsNFAGraphRepresentation() {
        try {
            processes.push(Runtime.getRuntime().exec(Globals.DOT + " " + automator.getDotEpsNFAFile()));
        } catch (IOException ex) {
            Logger.getLogger(AutomatorPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showMinimizedGraphRepresentation() {
        String dotFileName;
        String fileName;
        try {
            dotFileName = automator.getDotMinGraphFile().getName();
            fileName = dotFileName.substring(0, dotFileName.length() - 4);
            Runtime.getRuntime().exec(Globals.DOT + " -Tgif " +
                    automator.getDotMinGraphFile().getAbsolutePath() + " -o " +
                    fileName + ".gif");


            processes.push(Runtime.getRuntime().exec(Globals.IMG_VIEWER + " " + fileName + ".gif"));
        } catch (IOException ex) {
            Logger.getLogger(AutomatorPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showNFAGraphRepresentation() {
        try {
            processes.push(Runtime.getRuntime().exec(Globals.DOT + " " + automator.getDotNFAFile()));
        } catch (IOException ex) {
            Logger.getLogger(AutomatorPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showTableRepresentation() {
        try {
            Process p = Runtime.getRuntime().exec(Globals.LATEX_COMMAND + " " + automator.getTexFile());
            String dvifile = automator.getTexFile().getName();
            dvifile = dvifile.substring(0, dvifile.length() - 4) + ".dvi";
            processes.add(Runtime.getRuntime().exec(Globals.DVI_VIEWER + " " + new File(dvifile)));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "interior error",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        tfPattern = new javax.swing.JTextField();
        btnSetAutomator = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tfWord = new javax.swing.JTextField();
        btnVerify = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        rbEpsNFA = new javax.swing.JRadioButton();
        rbNFA = new javax.swing.JRadioButton();
        rbTable = new javax.swing.JRadioButton();
        rbDFA = new javax.swing.JRadioButton();
        rbMinimized = new javax.swing.JRadioButton();
        btnShow = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCloseAll = new javax.swing.JButton();
        imagePanel = new ImagePanel((new ImageIcon("icons" + File.separator + "wiki_3.png")).getImage());

        jLabel1.setText("Regular Expression");

        btnSetAutomator.setText("Set Pattern");

        jLabel2.setText("Word to be checked");

        btnVerify.setText("Verify");

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 441, Short.MAX_VALUE)
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 169, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Choose representation:", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        buttonGroup1.add(rbEpsNFA);
        rbEpsNFA.setText("Epsilon-NFA");

        buttonGroup1.add(rbNFA);
        rbNFA.setText("NFA");

        buttonGroup1.add(rbTable);
        rbTable.setText("Table by NFA");

        buttonGroup1.add(rbDFA);
        rbDFA.setText("DFA");

        buttonGroup1.add(rbMinimized);
        rbMinimized.setText("Minimized DFA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbEpsNFA)
                    .addComponent(rbNFA)
                    .addComponent(rbTable)
                    .addComponent(rbDFA)
                    .addComponent(rbMinimized))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(rbEpsNFA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbNFA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbTable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbDFA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbMinimized)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnShow.setText("Show");

        btnSave.setText("Save");

        btnCloseAll.setText("Close all windows");

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 967, Short.MAX_VALUE)
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(tfPattern, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSetAutomator))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(tfWord, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVerify, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCloseAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(btnShow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCloseAll)
                        .addGap(92, 92, 92))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tfPattern, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSetAutomator))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVerify))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)))
                .addGap(18, 18, 18)
                .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCloseAll;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSetAutomator;
    private javax.swing.JButton btnShow;
    private javax.swing.JButton btnVerify;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbDFA;
    private javax.swing.JRadioButton rbEpsNFA;
    private javax.swing.JRadioButton rbMinimized;
    private javax.swing.JRadioButton rbNFA;
    private javax.swing.JRadioButton rbTable;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JTextField tfPattern;
    private javax.swing.JTextField tfWord;
    // End of variables declaration//GEN-END:variables

    class ImagePanel extends JPanel {

        Image image;

        ImagePanel(Image image) {
            this.image = image;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            // get size of box we have to draw in
            Dimension dim = getSize();
            if (image != null) {
                /* center Image in box, normally should exactly fill the box.
                If we overflow, no problem, drawImage will clip. */
                int imageWidth = image.getWidth(this);
                int imageHeight = image.getHeight(this);

                // this does not complete the job, just starts it.
                // We are notified of progress through our Component ImageObserver interface.
                g.drawImage(image, /* Image to draw */
                        (dim.width - imageWidth) / 2, /* x */
                        (dim.height - imageHeight) / 2, /* y */
                        imageWidth, /* width */
                        imageHeight, /* height */
                        this);                           /* this ImageViewerSwing component */

            } else {
                /*  we have no Image, clear the box */
                g.setColor(getBackground());
                g.clearRect(0, 0, dim.width, dim.height);
            }
        }
    }

    public static void copy(File fromFile, File toFile)
            throws IOException {
        if (fromFile == null || toFile == null) {
            return;
        }

        if (!fromFile.exists()) {
            throw new IOException("FileCopy: " + "no such source file: " + fromFile.getAbsolutePath());
        }
        if (!fromFile.isFile()) {
            throw new IOException("FileCopy: " + "can't copy directory: " + fromFile.getAbsolutePath());
        }
        if (!fromFile.canRead()) {
            throw new IOException("FileCopy: " + "source file is unreadable: " + fromFile.getAbsolutePath());
        }
        if (toFile.isDirectory()) {
            toFile = new File(toFile, fromFile.getName());
        }
        if (toFile.exists()) {
            if (!toFile.canWrite()) {
                throw new IOException("FileCopy: " + "destination file is unwriteable: " + toFile.getAbsolutePath());
            }
            System.out.print("Overwrite existing file " + toFile.getName() + "? (Y/N): ");
            System.out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    System.in));
            String response = in.readLine();
            if (!response.equals("Y") && !response.equals("y")) {
                throw new IOException("FileCopy: " + "existing file was not overwritten.");
            }
        } else {
            String parent = toFile.getParent();
            if (parent == null) {
                parent = System.getProperty("user.dir");
            }
            File dir = new File(parent);
            if (!dir.exists()) {
                throw new IOException("FileCopy: " + "destination directory doesn't exist: " + parent);
            }
            if (dir.isFile()) {
                throw new IOException("FileCopy: " + "destination is not a directory: " + parent);
            }
            if (!dir.canWrite()) {
                throw new IOException("FileCopy: " + "destination directory is unwriteable: " + parent);
            }
        }

        FileInputStream from = null;
        FileOutputStream to = null;
        try {
            from = new FileInputStream(fromFile);
            to = new FileOutputStream(toFile);
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = from.read(buffer)) != -1) {
                to.write(buffer, 0, bytesRead); // write

            }
        } finally {
            if (from != null) {
                try {
                    from.close();
                } catch (IOException e) {
                    ;
                }
            }
            if (to != null) {
                try {
                    to.close();
                } catch (IOException e) {
                    ;
                }
            }
        }
    }
}
