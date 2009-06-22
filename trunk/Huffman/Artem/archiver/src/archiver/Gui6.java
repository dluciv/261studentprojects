/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Gui6.java
 *
 * Created on 15.06.2009, 14:15:30
 */

package archiver;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Admin
 */
public class Gui6 extends javax.swing.JFrame {
	private File theInputFile;
	//public BoundedRangeModel mProgressBarModel;

    /** Creates new form Gui6 */
    public Gui6() {
        initComponents();
		

		this.setTitle("Архиватор студенческий");
		// Set icon
		Image icon = Toolkit.getDefaultToolkit().getImage("box_plus.png");
		this.setIconImage(icon);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sfpLabel = new javax.swing.JLabel();
        sfpButton = new javax.swing.JButton();
        sfpTextField = new javax.swing.JTextField();
        rfpLabel = new javax.swing.JLabel();
        rfpTextField = new javax.swing.JTextField();
        acLabel = new javax.swing.JLabel();
        acComboBox = new javax.swing.JComboBox();
        mProgressBar = new javax.swing.JProgressBar();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        sfpLabel.setText("Выберите файл:");

        sfpButton.setText("Обзор..");
        sfpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sfpButtonActionPerformed(evt);
            }
        });

        sfpTextField.setFocusable(false);

        rfpLabel.setText("Конечный файл:");

        rfpTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rfpTextFieldActionPerformed(evt);
            }
        });

        acLabel.setText("Алгоритм сжатия:");

        acComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "сжатие по Хаффману", "Арифметическое кодирование" }));
        acComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                updRfp(evt);
            }
        });

        okButton.setText("OK");
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okButtonMouseClicked(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sfpLabel)
                        .addGap(18, 18, 18)
                        .addComponent(sfpTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(acLabel)
                        .addGap(10, 10, 10)
                        .addComponent(acComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                        .addComponent(sfpButton))
                    .addComponent(mProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rfpLabel)
                        .addGap(18, 18, 18)
                        .addComponent(rfpTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sfpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sfpLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sfpButton)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(acComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(acLabel)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rfpLabel)
                    .addComponent(rfpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addGap(18, 18, 18)
                .addComponent(mProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void setRfp(){
		if(null!=theInputFile){
			if(theInputFile.getName().endsWith(".huff")){
				rfpTextField.setText(theInputFile.getPath().substring(0, (theInputFile.getPath().length())-5));
				acComboBox.disable();
			}
			else if(theInputFile.getName().endsWith(".arithm")){
				rfpTextField.setText(theInputFile.getPath().substring(0, (theInputFile.getPath().length())-7));
				acComboBox.disable();
			}else{
				acComboBox.enable();
				String suff = "";
				if(acComboBox.getSelectedIndex() == 0) suff = ".huff";
				if(acComboBox.getSelectedIndex() == 1) suff = ".arithm";
				rfpTextField.setText(theInputFile.getPath()+suff);
			}
		}
	}

	private void sfpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sfpButtonActionPerformed
      
		
		JFileChooser jfc = new JFileChooser();
		if(jfc.showOpenDialog(this) == JFileChooser.CANCEL_OPTION) return;


		theInputFile = jfc.getSelectedFile();
		sfpTextField.setText(theInputFile.getPath());

		setRfp();
}//GEN-LAST:event_sfpButtonActionPerformed

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
		this.setVisible(false);
		this.dispose();
		this.removeAll();
}//GEN-LAST:event_cancelButtonActionPerformed

	private void updRfp(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_updRfp
		setRfp();
	}//GEN-LAST:event_updRfp

	private void rfpTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rfpTextFieldActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_rfpTextFieldActionPerformed

	private void okButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseClicked
		buttonOkPresed();
	}//GEN-LAST:event_okButtonMouseClicked

	private void buttonOkPresed(){
		String fn1 = sfpTextField.getText();
		String fn2 = rfpTextField.getText();
		theInputFile = new File(fn1);





		if(null!=theInputFile){
			String mess = "";
			if(theInputFile.getName().endsWith(".huff")){
				mess = "Выполняем декодирование по Хаффману файла ";
				HuffmanFileExtractor hfe = new HuffmanFileExtractor();
				try {
					hfe.extractFile(fn1, fn2);
				} catch (IOException ex) {
					Logger.getLogger(Gui6.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			if(theInputFile.getName().endsWith(".arithm"))
				mess = "Выполняем декодирование по Арифметическому алгоритму файла " ;
			else{
				if(acComboBox.getSelectedIndex() == 0){
					mess = "Выполняем сжатие Хаффмана файла " ;
					//HuffmanFileCompressor hfc = new HuffmanFileCompressor();
					HuffmanFileCompressorThread hfct = new HuffmanFileCompressorThread();
					try {
						hfct.run(fn1,fn2);
					} catch (IOException ex) {
						Logger.getLogger(Gui6.class.getName()).log(Level.SEVERE, null, ex);
					}
				}else if(acComboBox.getSelectedIndex() == 1)
					mess = "Выполняем арифметичекое сжатие файла " ;
			}
			//JOptionPane.showMessageDialog(this, mess+theInputFile.getName());
			JOptionPane.showMessageDialog(this, "Операция успешно завершена");
		}else
			JOptionPane.showMessageDialog(this, "Вначале выберите файл");
	}

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {

				try {
					String s = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
					//s = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
					UIManager.setLookAndFeel(s);
				} catch (ClassNotFoundException ex) {
					Logger.getLogger(Gui6.class.getName()).log(Level.SEVERE, null, ex);
				} catch (InstantiationException ex) {
					Logger.getLogger(Gui6.class.getName()).log(Level.SEVERE, null, ex);
				} catch (IllegalAccessException ex) {
					Logger.getLogger(Gui6.class.getName()).log(Level.SEVERE, null, ex);
				} catch (UnsupportedLookAndFeelException ex) {
					Logger.getLogger(Gui6.class.getName()).log(Level.SEVERE, null, ex);
				}

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui6().setVisible(true);
            }
        });
		

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox acComboBox;
    private javax.swing.JLabel acLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JProgressBar mProgressBar;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel rfpLabel;
    private javax.swing.JTextField rfpTextField;
    private javax.swing.JButton sfpButton;
    private javax.swing.JLabel sfpLabel;
    private javax.swing.JTextField sfpTextField;
    // End of variables declaration//GEN-END:variables

}
