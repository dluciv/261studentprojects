/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexprnew;

/**
 *
 * @author kate
 */
public class Position {

    private int columnNum = 0;
    private int rowNum = 0;
    private int currInd = 0;
    private int sizeOfLexem = 0;

    public Position(int columnNum, int rowNum, int currInd, int sizeOfLexem) {
        this.columnNum = columnNum;
        this.rowNum = rowNum;
        this.currInd = currInd;
        this.sizeOfLexem = sizeOfLexem;
    }

    public Position() {
    }

    public int getColumnNumBegin() {
        return columnNum;
    }

    public int getColumnNumEnd() {
        return columnNum + sizeOfLexem;
    }

    public int getRowNum() {
        return rowNum;
    }

    public int getCurrInd() {
        return currInd;
    }

    public int getEndInd() {
        return currInd + sizeOfLexem;
    }
}
