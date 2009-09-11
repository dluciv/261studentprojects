package eda.tm.gui;

/**
 *Represents column data used in table
 * 
 * @author Anastasiya
 */
public class ColumnData {

    public String title;
    public int width;
    public int alignment;

    public ColumnData(String title, int width, int alignment) {
        this.title = title;
        this.width = width;
        this.alignment = alignment;
    }
}
