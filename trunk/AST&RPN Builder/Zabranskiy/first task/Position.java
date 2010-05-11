package analizator;

public class Position {

    private int currentCol = 0;
    private int currentRow = 0;

    public Position(int col, int row) {
        currentCol = col;
        currentRow = row;
    }

    public int getColumn() {
        return currentCol;
    }

    public int getRow() {
        return currentRow;
    }

    @Override
    public String toString() {
        return currentRow + " " + currentCol;
    }
}
