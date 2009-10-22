/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package transport;

import java.io.IOException;


/**
 *
 * @author Artem
 */
public class TableStructure {
    Cell[][] table;
    int[] upotential;
    int[] vpotential;    
    int[] columns;
    int[] arrows;
    int[] columnsrest;
    int[] arrowsrest;

    public TableStructure(int col, int arr) {
        table = new Cell[col][arr];
        columns = new int[col];
        arrows = new int[arr];
        vpotential = new int[col];
        upotential = new int[arr];        
        columnsrest = new int[col];
        arrowsrest = new int[arr];        
    }

    public void copyHeader() {
        for (int i = 0; i < columns.length;i++) {
            columnsrest[i] = columns[i];
        }
        for (int i = 0; i < arrows.length;i++) {
            arrowsrest[i] = arrows[i];
        }
    }

    public void fillCell(int col, int arr, int price, int max) {
        table[col][arr] = new Cell(price, max, 0, col, arr);
    }

    public void fillCellVal(int col, int arr, int val) {
        table[col][arr].setValue(val);
    }

    public void markAsBase(int col, int arr) {
        table[col][arr].setBase();
    }

    public void setCol(int pos, int val) {
        columns[pos] = val;
    }

    public void setArr(int pos, int val) {
        arrows[pos] = val;
    }

    public int getColNumber() {
        return columns.length;
    }

    public int getArrNumber() {
        return arrows.length;
    }

    public int getColumnsRest(int col) {
        return columnsrest[col];
    }

    public int getArrowsRest(int arr) {
        return arrowsrest[arr];
    }

    public int getColumns(int col) {
        return columns[col];
    }

    public int getArrows(int arr) {
        return arrows[arr];
    }

    public void outputCurState(OutputFile ofstream, Cell cell, int value) throws IOException {
        String s = "                          ";
        int length = 0;
        for (int arr = 0; arr < getArrNumber(); arr++) {
            for (int col = 0; col < getColNumber(); col++) {                                
                if (!table[col][arr].base) {
                    ofstream.write(String.valueOf(table[col][arr].getValue()) + ",");
                    ofstream.write(String.valueOf(table[col][arr].getPrice()) + ",");                    
                    length = String.valueOf(table[col][arr].getValue()).length() 
                            + String.valueOf(table[col][arr].getPrice()).length() 
                            + String.valueOf(table[col][arr].getMaxAllowed()).length()
                            + 2;
                    ofstream.write(String.valueOf(table[col][arr].getMaxAllowed()) + s.substring(length));
                } else {
                    ofstream.write("<|" + String.valueOf(table[col][arr].getValue()) + ",");
                    ofstream.write(String.valueOf(table[col][arr].getPrice()) + ",");
                    length = String.valueOf(table[col][arr].getValue()).length()
                            + String.valueOf(table[col][arr].getPrice()).length()
                            + String.valueOf(table[col][arr].getMaxAllowed()).length()
                            + 6;
                    ofstream.write(String.valueOf(table[col][arr].getMaxAllowed()) + "|>"  + s.substring(length));
                }

            }
            ofstream.write("||" + upotential[arr] + "||");
            ofstream.write("  " + "\r\n" + "\r\n");
        }
        for (int col = 0; col < getColNumber(); col++) {
            length = String.valueOf(vpotential[col]).length() + 4;
            ofstream.write("||" + vpotential[col] + "||"  + s.substring(length));
        }
        ofstream.write("  " + "\r\n" + "\r\n");
        ofstream.write("function = " + countCost());
        if (cell != null) {
            ofstream.write("\r\n" + "arrow = " + String.valueOf(cell.getArr()) + "   column = " +String.valueOf(cell.getCol()));
            ofstream.write("\r\n" + "Moved value = " + String.valueOf(value));
        }
        ofstream.write("  " + "\r\n" + "\r\n");
        ofstream.write("  " + "\r\n" + "\r\n");
        ofstream.write("  " + "\r\n" + "\r\n");    
    }

    public void outputLastState(OutputFile ofstream, Cell cell, int value) throws IOException {
        ofstream.write("  " + "\r\n" + "\r\n");
        ofstream.write("  " + "\r\n" + "\r\n");
        ofstream.write("Answer");
        ofstream.write("  " + "\r\n" + "\r\n");
        String s = "                          ";
        int length = 0;
        for (int arr = 0; arr < getArrNumber()-1; arr++) {
            for (int col = 0; col < getColNumber()-1; col++) {
                if (!table[col][arr].base) {
                    ofstream.write(String.valueOf(table[col][arr].getValue()) + ",");
                    ofstream.write(String.valueOf(table[col][arr].getPrice()) + ",");
                    length = String.valueOf(table[col][arr].getValue()).length()
                            + String.valueOf(table[col][arr].getPrice()).length()
                            + String.valueOf(table[col][arr].getMaxAllowed()).length()
                            + 2;
                    ofstream.write(String.valueOf(table[col][arr].getMaxAllowed()) + s.substring(length));
                } else {
                    ofstream.write("<|" + String.valueOf(table[col][arr].getValue()) + ",");
                    ofstream.write(String.valueOf(table[col][arr].getPrice()) + ",");
                    length = String.valueOf(table[col][arr].getValue()).length()
                            + String.valueOf(table[col][arr].getPrice()).length()
                            + String.valueOf(table[col][arr].getMaxAllowed()).length()
                            + 6;
                    ofstream.write(String.valueOf(table[col][arr].getMaxAllowed()) + "|>"  + s.substring(length));
                }

            }
            ofstream.write("||" + upotential[arr] + "||");
            ofstream.write("  " + "\r\n" + "\r\n");
        }
        for (int col = 0; col < getColNumber() - 1; col++) {
            length = String.valueOf(vpotential[col]).length() + 4;
            ofstream.write("||" + vpotential[col] + "||"  + s.substring(length));
        }
        ofstream.write("  " + "\r\n" + "\r\n");
        ofstream.write("function = " + countCost());
        if (cell != null) {
            ofstream.write("\r\n" + "arrow = " + String.valueOf(cell.getArr()) + "   column = " +String.valueOf(cell.getCol()));
            ofstream.write("\r\n" + "Moved value = " + String.valueOf(value));
        }
        ofstream.write("  " + "\r\n" + "\r\n");
        ofstream.write("  " + "\r\n" + "\r\n");
        ofstream.write("  " + "\r\n" + "\r\n");
    }

    public void setPotentials() {
        boolean[] potentialu = new boolean[getArrNumber()];
        boolean[] potentialv = new boolean[getColNumber()];
        upotential[0] = 0;
        potentialu[0] = true;
        int counter = 1;
        final int needed = getColNumber() + getArrNumber();
        
        while (counter != needed) {
            for (int u = 0; u < upotential.length; u++) {
                if (potentialu[u]) {
                    for (int v = 0; v < getColNumber(); v++) {
                        if (table[v][u].base && !potentialv[v]) {
                            vpotential[v] = table[v][u].getPrice() - upotential[u];
                            potentialv[v] = true;
                            counter++;
                        }
                    }
                }             
            }

            for (int v = 0; v < vpotential.length; v++) {
                if (potentialv[v]) {
                    for (int u = 0; u < getArrNumber(); u++) {
                        if (table[v][u].base && !potentialu[u]) {
                            upotential[u] = table[v][u].getPrice() - vpotential[v];
                            potentialu[u] = true;
                            counter++;
                        }
                    }
                }             
            }
        }   
    }

    public Cell getMostPotentialCell() {
        Cell result = null;
        for (int col = 0; col < getColNumber(); col++) {
            for (int arr = 0; arr < getArrNumber(); arr++) {
                if (!table[col][arr].base && !checkIfOptimal(table[col][arr])) {
                    if (result == null) {
                        result = table[col][arr];
                        continue;
                    }
                    result = compareCellsReturnMax(result, table[col][arr]);
                }
            }
        }
        return result;
    }

    private Cell compareCellsReturnMax(Cell cell1, Cell cell2) {
        Cell result = cell1;
        if (Math.abs(getMarkValue(cell1)) == Math.abs(getMarkValue(cell2))) {
            if(cell1.getPrice() > cell2.getPrice()) {
                result = cell2;
            }
        } else {
            if (Math.abs(getMarkValue(result)) < Math.abs(getMarkValue(cell2))) {
                result = cell2;
            }
        }
        return result;
    }

    public int countCost() {
        int result = 0;
        for (int arr = 0; arr < getArrNumber() - 1; arr++) {
            for (int col = 0; col < getColNumber() - 1; col++) {
                if (table[col][arr].getValue() != 0) {
                    result += table[col][arr].getValue() * table[col][arr].getPrice();
                }
            }
        }
        return result;
    }

    public boolean checkIfOptimal(Cell cell) {
        if (cell.getValue() == 0) {
            return getMarkValue(cell) >= 0;
        }
        if (cell.getValue() == cell.getMaxAllowed()) {
            return getMarkValue(cell) <= 0;
        }
        return false;
    }

    public int getMarkValue(Cell cell) {
        return cell.getPrice()-(vpotential[cell.getCol()] + upotential[cell.getArr()]);
    }

    public Cell getNextBaseCellToRight(int startcol,int startarr, HelperForCycleFinder helper, int step, int begcol, int begarr) {
       for (int i = startcol + 1; i < getColNumber(); i++) {
           if (i == begcol && startarr == begarr && step > 2) {
               return table[i][startarr];
           }
           if (table[i][startarr].base && helper.getVisited(i, startarr)) {
               return null;
           }
           if (table[i][startarr].base && !helper.getVisited(i, startarr) ) {
               if (step == 2 && i == begcol) {
                   return null;
               }
               helper.setVisited(i, startarr);
               return table[i][startarr];
           }
       }
       return null;
    }

    public Cell getNextBaseCellToLeft(int startcol,int startarr, HelperForCycleFinder helper, int step, int begcol, int begarr) {
       for (int i = startcol - 1; i >= 0; i--) {
           if (i == begcol && startarr == begarr && step > 2) {
               return table[i][startarr];
           }
           if (table[i][startarr].base && helper.getVisited(i, startarr)) {
               return null;
           }
           if (table[i][startarr].base && !helper.getVisited(i, startarr) ) {
               if (step == 2 && i == begcol) {
                   return null;
               }
               helper.setVisited(i, startarr);
               return table[i][startarr];
           }
       }
       return null;
    }

    public Cell getNextBaseCellToUp(int startcol,int startarr, HelperForCycleFinder helper, int step, int begcol, int begarr) {
       for (int i = startarr - 1; i >= 0; i--) {
           if (startcol == begcol && i == begarr && step > 2) {
               return table[startcol][i];
           }
           if (table[startcol][i].base && helper.getVisited(startcol, i)) {
               return null;
           }
           if (table[startcol][i].base && !helper.getVisited(startcol, i) ) {
               if (step == 2 && i == begarr) {
                   return null;
               }
               helper.setVisited(startcol, i);
               return table[startcol][i];
           }
       }
       return null;
    }

    public Cell getNextBaseCellToDown(int startcol,int startarr, HelperForCycleFinder helper, int step, int begcol, int begarr) {
       for (int i = startarr + 1; i < getArrNumber(); i++) {
           if (startcol == begcol && i == startarr && step > 2) {
               return table[startcol][i];
           }
           if (table[startcol][i].base && helper.getVisited(startcol, i)) {
               return null;
           }
           if (table[startcol][i].base && !helper.getVisited(startcol, i) ) {
               if (step == 2 && i == begarr) {
                   return null;
               }
               helper.setVisited(startcol, i);
               return table[startcol][i];
           }
       }
       return null;
    }

    public void setColumnsRest(int col,int val) {
        columnsrest[col] = val;
    }

    public void setArrowsRest(int arr,int val) {
        arrowsrest[arr] = val;
    }

    public Cell getMinPriceEmptyCell() {
        int curprice = 0;
        Cell curcell = null;
        for (int arr = 0; arr < getArrNumber();arr++) {
            if (arrowsrest[arr] <= 0)
                continue;
            for (int col = 0; col < getColNumber();col++) {
                if (columnsrest[col] <= 0)
                    continue;
                if (curprice == 0 && table[col][arr].isEmpty()) {
                    curprice = table[col][arr].price;
                    curcell = table[col][arr];
                } else {
                    if (curprice > table[col][arr].price && table[col][arr].isEmpty()) {
                        curprice = table[col][arr].price;
                        curcell = table[col][arr];
                    }
                }
            }
        }
        return curcell;
    }
}
