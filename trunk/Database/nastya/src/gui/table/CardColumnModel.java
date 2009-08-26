package gui.table;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

/**
 * @author nastya
 *         Date: 21.08.2009
 *         Time: 2:28:31
 */
public class CardColumnModel extends DefaultTableColumnModel {

    public CardColumnModel() {
        addColumn("Фамилия", 50);
        addColumn("Имя", 20);
        addColumn("Отчество", 30);
        TableColumn sexColumn = addColumn("Пол", 10);
        addColumn("Телефон", 30);
        addColumn("Адрес", 50);
        sexColumn.setCellRenderer(new SexCellRenderer());
    }

    private TableColumn addColumn(String title, int width){
        TableColumn column = new TableColumn(getColumnCount(), width);
        column.setHeaderValue(title);
        addColumn(column);
        return column;
    }


}
