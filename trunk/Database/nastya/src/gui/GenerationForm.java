package gui;

import utils.Messages;
import utils.Util;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import database.generator.DatabaseGenerator;
import database.generator.GeneratorException;

/**
 * Created by IntelliJ IDEA.
 * User: nastya
 * Date: 20.08.2009
 * Time: 23:35:05
 * To change this template use File | Settings | File Templates.
 */
public class GenerationForm {
    private JTextField tfCardsCount;
    private JButton btnGenerate;
    private JButton btnGenerateIndex;
    private JPanel panel;
    private DatabaseGenerator generator = null;
    public static final String DEFAULT_DB_NAME = "sample.db";
    public static final String DEFAULT_DB_PATTERN = ".*\\.db";
    public static final String DEFAULT_DB_PATTERN_DESCRIPTION = ".db";

    public GenerationForm(){
        btnGenerate.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                onGenerate();
            }
        });
    }

    private void onGenerate() {
        String text = tfCardsCount.getText();
        try{
            int cardsCount = Integer.parseInt(text);
            if(cardsCount <= 0){
                JOptionPane.showMessageDialog(panel, Messages.WRONG_NUMBER_OF_CARDS, Messages.ERROR, JOptionPane.ERROR_MESSAGE);
                tfCardsCount.requestFocus();
                return;
            }
            File file = Util.openFileChooser(panel, true, DEFAULT_DB_NAME, DEFAULT_DB_PATTERN, DEFAULT_DB_PATTERN_DESCRIPTION);
            if(file == null){
                return;
            }
            startCardGeneration(cardsCount, file.getAbsolutePath());
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(panel, Messages.WRONG_NUMBER_OF_CARDS, Messages.ERROR, JOptionPane.ERROR_MESSAGE);
            tfCardsCount.requestFocus();
        }
    }

    private void startCardGeneration(final int cardsCount, final String absolutePath) {
        btnGenerate.setEnabled(false);
        if(generator == null){
            generator = new DatabaseGenerator();
        }
        Thread generationThread = new Thread(){
            @Override
            public void run() {
                try {
                    generator.generate(absolutePath, cardsCount);
                } catch (GeneratorException e) {
                    JOptionPane.showMessageDialog(panel, Messages.GENERATION_FAILED, Messages.ERROR, JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(panel, Messages.GENERATION_SUCCEDED, Messages.ATTENTION, JOptionPane.INFORMATION_MESSAGE);

                btnGenerate.setEnabled(true);
            }
        };
        generationThread.start();
    }

    public JPanel getPanel(){
        return panel;
    }
}
