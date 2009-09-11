package gui;

import dbentities.Condition;
import dbentities.Card;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Vector;
import java.util.List;

import database.search.DatabaseIterator;
import database.index.DatabaseKey;
import database.index.AddressData;
import database.parser.DatabaseParser;
import database.parser.ParserException;
import tree.BPlusTree;
import utils.Util;
import utils.Messages;
import gui.table.CardTableModel;
import gui.table.CardColumnModel;

/**
 * Форма, где демонстрируется поиск по базе
 *
 * @author nastya
 *         Date: 30.08.2009
 *         Time: 13:34:04
 */
public class TestIteratorForm {
    private JPanel panel;
    private ConditionForm condStart;
    private ConditionForm condStop;
    private JButton btnGetCount;
    private JTextField tfCount;
    private JButton btnFirstCard;
    private JTextField tfCardLastName;
    private JTextField tfCardName;
    private JTextField tfCardMiddleName;
    private JTextField tfCardPhone;
    private JTextField tfCardAddress;
    private JComboBox cbCardSex;
    private JButton btnGetAllCards;
    private JTable table;
    private JCheckBox cbNotAll;
    private JTextField tfCardsToRead;
    private JPanel tfCardsCount;
    private FileChoosingForm chDatabase;
    private FileChoosingForm chIndex;
    private JButton btnPrepare;
    private MainDialog mainDialog;
    private BPlusTree<Card, DatabaseKey, AddressData> tree = null;
    private DatabaseParser databaseParser = null;

    public static final String DEFAULT_DB_NAME = "sample.db";
    public static final String DEFAULT_DB_PATTERN = ".*\\.db";
    public static final String DEFAULT_DB_PATTERN_DESCRIPTION = ".db";

    public static final String DEFAULT_INDEX_NAME = "sample.idx";
    public static final String DEFAULT_INDEX_PATTERN = ".*\\.idx";
    public static final String DEFAULT_INDEX_PATTERN_DESCRIPTION = ".idx";


    public TestIteratorForm(MainDialog mainDialog) {
        this.mainDialog = mainDialog;
        btnPrepare.addActionListener(new PrepareDatabaseAndIndexActionListener());
        btnGetCount.addActionListener(new GetCountCardActionListener());
        btnFirstCard.addActionListener(new GetCards() {
            protected void process(Vector<Card> cards) {
                if (cards == null || cards.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, Messages.SEARCH_NO_RESULTS, Messages.ATTENTION, JOptionPane.INFORMATION_MESSAGE);
                    showCard(null);
                } else {
                    showCard(cards.firstElement());
                }
            }

            protected int getCountCard() {
                return 1;
            }


        });
        btnGetAllCards.addActionListener(new GetCards() {
            protected void process(Vector<Card> cards) {
                if (cards == null || cards.isEmpty()) {
                    ((CardTableModel) table.getModel()).setData(new Vector<Card>());
                } else {
                    ((CardTableModel) table.getModel()).setData(cards);
                }
                table.updateUI();
            }

            protected int getCountCard() {
                if (!cbNotAll.isSelected()) {
                    return -1;
                } else {

                    try {
                        return Integer.parseInt(tfCardsToRead.getText().trim());

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, Messages.WRONG_NUMBER_OF_CARDS, Messages.ATTENTION, JOptionPane.WARNING_MESSAGE);
                        return -1;
                    }
                }
            }


        });
        cbNotAll.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(!cbNotAll.isSelected()){
                    tfCardsToRead.setEnabled(false);
                }else{
                    tfCardsToRead.setEnabled(true);
                }
            }
        });
    }

    private void showCard(Card card) {
        tfCardAddress.setText(card == null ? "" : card.getAddress());
        tfCardLastName.setText(card == null ? "" : card.getLastName());
        tfCardMiddleName.setText(card == null ? "" : card.getMiddleName());
        tfCardName.setText(card == null ? "" : card.getName());
        tfCardPhone.setText(card == null ? "" : card.getPhone());
        cbCardSex.removeAllItems();
        cbCardSex.addItem(card == null ? "" : Util.representSex(card.getSex()));
        cbCardSex.setSelectedIndex(0);
    }

    private void createUIComponents() {
        condStart = new ConditionForm("От");
        condStop = new ConditionForm("До");
        chDatabase = new FileChoosingForm("Файл базы данных: ", panel, false, DEFAULT_DB_NAME, DEFAULT_DB_PATTERN, DEFAULT_DB_PATTERN_DESCRIPTION);
        chIndex = new FileChoosingForm("Файл индекса:  ", panel, false, DEFAULT_INDEX_NAME, DEFAULT_INDEX_PATTERN, DEFAULT_INDEX_PATTERN_DESCRIPTION);
        CardColumnModel columnModel = new CardColumnModel();
        CardTableModel tableModel = new CardTableModel(columnModel);
        table = new JTable(tableModel, columnModel);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setStartCondition(Condition condition) {
        if (condition == null) return;
        condStart.setCondition(condition);
    }

    public void setStopCondition(Condition condition) {
        if (condition == null) return;
        condStop.setCondition(condition);
    }

    class GetCountCardActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (tree == null) {
                showIndexNotPrepared();
                return;
            }
            DatabaseIterator iterator = new DatabaseIterator(condStart.getCondition(), condStop.getCondition(), tree);
            int count = 0;
            long time = System.nanoTime();
            while (iterator.hasNext()) {
                Vector<AddressData> addressData = iterator.next();
                count += addressData.size();
            }
            time = System.nanoTime() - time;
            tfCount.setText(count + "");
            mainDialog.log(time);
        }
    }

    abstract class GetCards implements ActionListener {

        private Vector<Card> data;

        GetCards() {
        }

        public void actionPerformed(ActionEvent e) {
            if (tree == null) {
                showIndexNotPrepared();
                return;
            }

            if (databaseParser == null) {
                showDatabaseNotPrepared();
                return;
            }

            DatabaseIterator iterator = new DatabaseIterator(condStart.getCondition(), condStop.getCondition(), tree);
            int countCard = getCountCard();
            int readedCount = 0;
            data = new Vector<Card>();
            Vector<Long> timeStat = new Vector<Long>();
            long time = System.nanoTime();
            while (iterator.hasNext() && (readedCount < countCard || countCard < 0)) {
                Vector<AddressData> addressData = iterator.next();
                List<AddressData> toRead = addressData;
                readedCount += addressData.size();
                if (countCard > -1 && readedCount > countCard) {
                    toRead = addressData.subList(0, addressData.size() - (countCard - readedCount));
                }
                try {
                    timeStat.addAll(read(data, toRead));
                } catch (ParserException e1) {
                    Util.processParserErrors(e1, panel);
                }

            }
            time = System.nanoTime() - time;
             mainDialog.log(data.size(), time, timeStat);
            process(data);
        }

        private Vector<Long> read(Vector<Card> data, List<AddressData> toRead) throws ParserException {
            Vector<Long> timeStat = new Vector<Long>();
            for (AddressData addressData : toRead) {
                Card readed = databaseParser.parseSingle(addressData.getAddress());
                timeStat.add(databaseParser.getTime());
                if (readed != null) data.add(readed);
            }
            return timeStat;
        }

        protected abstract void process(Vector<Card> cards);

        protected abstract int getCountCard();
    }


    class PrepareDatabaseAndIndexActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            prepareDatabase();
            prepareIndex();
        }
    }

    private void prepareIndex() {
        if (chIndex.getChoosedFile() == null) return;
        try {
            tree = BPlusTree.<Card, DatabaseKey, AddressData>deserialize(chIndex.getChoosedFile());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(panel, Messages.PARSING_IDX_FAILED, Messages.ERROR, JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(panel, Messages.PARSING_IDX_FAILED, Messages.ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void prepareDatabase() {
        if (chDatabase.getChoosedFile() == null) return;
        try {
            databaseParser = new DatabaseParser(chDatabase.getChoosedFile().getAbsolutePath());
        } catch (ParserException e) {
            JOptionPane.showMessageDialog(panel, Messages.ERROR_DB_STRUCTURE, Messages.ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showIndexNotPrepared() {
        JOptionPane.showMessageDialog(panel, Messages.ERROR_DB_NOT_PRPARED, Messages.ERROR, JOptionPane.ERROR_MESSAGE);
    }

    private void showDatabaseNotPrepared() {
        JOptionPane.showMessageDialog(panel, Messages.ERROR_IDX_NOT_PRPARED, Messages.ERROR, JOptionPane.ERROR_MESSAGE);
    }
}
