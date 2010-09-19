/*
 *
 *
 * (c) Volkova Ekatetina
 */
package ide.ide;

public class Position {

    private int columnNum = 0;
    private int lineNum = 0;
    private int currInd = 0;
    private int sizeOfLexem = 0;

    public Position(int columnNum, int lineNum, int currInd, int sizeOfLexem) {
        this.columnNum = columnNum;
        this.lineNum = lineNum;
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

    public int getLineNum() {
        return lineNum;
    }

    public int getCurrInd() {
        return currInd;
    }

    public int getEndInd() {
        return currInd + sizeOfLexem;
    }
}
