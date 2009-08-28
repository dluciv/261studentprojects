package gui;

import utils.Messages;
import utils.Util;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import database.generator.DatabaseGenerator;
import database.generator.GeneratorException;
import database.index.IndexBuilder;
import database.parser.ParserException;
import tree.BPlusTree;
import dbentities.Card;

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
    private JPanel panel;
    private FileChoosingForm chDatabase;
    private FileChoosingForm chIndex;
    private JButton btnGenerateIndex;
    private JTextField tfTreeCapacity;
    private DatabaseGenerator generator = null;
    public static final String DEFAULT_DB_NAME = "sample.db";
    public static final String DEFAULT_DB_PATTERN = ".*\\.db";
    public static final String DEFAULT_DB_PATTERN_DESCRIPTION = ".db";

    public static final String DEFAULT_INDEX_NAME = "sample.idx";
    public static final String DEFAULT_INDEX_PATTERN = ".*\\.idx";
    public static final String DEFAULT_INDEX_PATTERN_DESCRIPTION = ".idx";

    public GenerationForm(){
        btnGenerate.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                onGenerate();
            }
        });
        btnGenerateIndex.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                onGenerateIndex();
            }


        });
    }

    private void onGenerateIndex() {
        if(chDatabase.getChoosedFile() == null){
            JOptionPane.showMessageDialog(panel, Messages.PLEASE_CHOOSE_FILE_DB, Messages.ERROR, JOptionPane.ERROR_MESSAGE);
            chDatabase.openChooserDialog();
            if(chDatabase.getChoosedFile() == null) return;
        }

        if(chIndex.getChoosedFile() == null){
            JOptionPane.showMessageDialog(panel, Messages.PLEASE_CHOOSE_FILE_INDEX, Messages.ERROR, JOptionPane.ERROR_MESSAGE);
            chIndex.openChooserDialog();
            if(chIndex.getChoosedFile() == null) return;
        }

        int capacity = -1;
        try {
            capacity = Integer.valueOf(tfTreeCapacity.getText().trim());
        } catch (NumberFormatException e) {

        }
        if (capacity == -1) {
            JOptionPane.showMessageDialog(panel, Messages.WRONG_CAPACITY, Messages.ERROR, JOptionPane.ERROR_MESSAGE);
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    tfTreeCapacity.requestFocus();
                }
            });
            return;
        }
        try {
            BPlusTree<Card> tree = IndexBuilder.generateIndex(chDatabase.getChoosedFile().getAbsolutePath(), capacity);

            Util.serialize(tree, chIndex.getChoosedFile());
        } catch (ParserException e) {
            Util.processParserErrors(e, panel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(panel, Messages.ERROR_IO_INDEX, Messages.ERROR, JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
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

    private void createUIComponents() {
        chDatabase = new FileChoosingForm("Файл базы данных: ", panel, false, DEFAULT_DB_NAME, DEFAULT_DB_PATTERN, DEFAULT_DB_PATTERN_DESCRIPTION); 
        chIndex = new FileChoosingForm("Файл для индекса:  ", panel, true, DEFAULT_INDEX_NAME, DEFAULT_INDEX_PATTERN, DEFAULT_INDEX_PATTERN_DESCRIPTION); 
    }

}
