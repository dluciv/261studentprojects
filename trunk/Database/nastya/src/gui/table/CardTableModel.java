package gui.table;

import dbentities.Card;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

/**
 * Модель таблицы для предоставления списка карточек из базы данных
 * @author nastya
 *         Date: 21.08.2009
 *         Time: 2:34:22
 */
public class CardTableModel extends AbstractTableModel {

    CardColumnModel columnModel;
    Vector<Card> data;

    public CardTableModel(CardColumnModel columnModel, Vector<Card> data){
        this.columnModel = columnModel;
        this.data = data;
    }

    public CardTableModel(CardColumnModel columnModel){
        this(columnModel, new Vector<Card>());
    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return columnModel.getColumnCount();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Card card = data.get(rowIndex);
        switch(columnIndex){
            case 0 : return card.getLastName();
            case 1 : return card.getName();
            case 2 : return card.getMiddleName();
            case 3 : return card.getSex();
            case 4 : return card.getPhone();
            case 5 : return card.getAddress();
        }
        return "";
    }

    public void setData(Vector<Card> data) {
        this.data = data;
    }

    public Card getData(int selected) {
        return data.get(selected);
    }
}
