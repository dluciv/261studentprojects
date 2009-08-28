package gui;

import utils.Util;

import javax.swing.*;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Sergey
 * Date: 28.08.2009
 * Time: 14:43:05
 * To change this template use File | Settings | File Templates.
 */
public class FileChoosingForm extends JComponent{
    private JPanel panel;
    private JLabel lbChooser;
    private JTextField tfFileAddress;
    private JButton btnChoose;
    private File choosedFile;
    private JComponent parentPanel;
    private boolean save;
    private String defaultName;
    private String filterPattern;
    private String patternDescription;

    public FileChoosingForm(String labelText, final JComponent parentPanel, final boolean save, final String defaultName, final String filterPattern, final String patternDescription) {
        this.parentPanel = parentPanel;
        this.save = save;
        this.defaultName = defaultName;
        this.filterPattern = filterPattern;
        this.patternDescription = patternDescription;
        lbChooser.setText(labelText);
        btnChoose.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                openChooserDialog();
            }
        });
    }

    private void onChooseFile(JComponent parentPanel, boolean save, String defaultName, String filterPattern, String patternDescription) {
        choosedFile = Util.openFileChooser(parentPanel, save, defaultName, filterPattern, patternDescription);
        tfFileAddress.setText(choosedFile == null ? "" : choosedFile.getAbsolutePath());
    }

    public JPanel getPanel(){
        return panel;
    }


    public File getChoosedFile() {
        return choosedFile;
    }

    public void openChooserDialog(){
        onChooseFile(parentPanel, save, defaultName, filterPattern, patternDescription);
    }

}
