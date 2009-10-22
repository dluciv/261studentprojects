/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package transport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Artem
 */
public class Performer  {   
    final int M = 1000;
    TableStructure tempstructure;
    TableStructure structure;
    OutputFile ofstream;
    //String AbsoluteOutputPath = "D:/study/netbeans/transport/output.txt";
    //String AbsoluteInputPath = "D:/study/netbeans/transport/input5.txt";
    String AbsoluteOutputPath;
    String AbsoluteInputPath;


    public Performer() throws IOException {
            
        execute(true);

        
    }


    private void execute (boolean contin) throws IOException {
        if (contin) {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Absolute input file path =" + '\n');
            AbsoluteInputPath = in.readLine();
            System.out.print("Absolute output file path =" + '\n');
            AbsoluteOutputPath = in.readLine();
            try {
                ofstream = new OutputFile(AbsoluteOutputPath);
                makeTableStructure();
                fillTable();
                findBasis();
                enlargeBasisTable();
                makeBase();
                optimize(null, 0);
            } catch (IOException ex) {
                Logger.getLogger(Performer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.print("One more? (y/n)" + '\n');
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        if (in.readLine().equals("y")) {
            execute(true);
        } else {
            System.out.print("Goodby!!!");
        }



    }

    private void makeTableStructure() throws IOException {
        LexemBuilder lex  = new LexemBuilder(AbsoluteInputPath);
        int counter = 0;
        int number = 0;
        boolean end = false;
        int col = 0;
        int arr = 0;

        while (!end) {
            number =  lex.getNextNumber();
            counter++;
            if ( number == -1 && arr == 0) {
                col = counter - 1;
            }
            if ( number == -1) {
                arr++;

            }
            System.out.print(number+"\n");
            if(number == -2) {
                end = true;
            }
        }        
        tempstructure = new TableStructure(col,arr);
    }

    private void fillTable() throws IOException {
        LexemBuilder lex  = new LexemBuilder(AbsoluteInputPath);
        int col = tempstructure.getColNumber();
        int arr = tempstructure.getArrNumber();
        for (int i = 0; i < col;i++) {
            tempstructure.setCol(i, lex.getNextNumber());
        }
            lex.getNextNumber();
        for (int i = 0; i < arr;i++) {
            tempstructure.setArr(i, lex.getNextNumber());
            for (int j = 0; j < col;j++) {
                tempstructure.fillCell(j, i, lex.getNextNumber(), lex.getNextNumber());
            }
            lex.getNextNumber();
        }
        tempstructure.copyHeader();
    }

    private void findBasis() {
        for (int i = 0; i <= tempstructure.getArrNumber() * tempstructure.getColNumber(); i++) {
            Cell mincell = tempstructure.getMinPriceEmptyCell();
            if (mincell == null) {
                break;
            }
            int curval = mincell.getMaxAllowed();
            if (tempstructure.getArrowsRest(mincell.getArr()) < curval) {
                curval = tempstructure.getArrowsRest(mincell.getArr());
            }
            if (tempstructure.getColumnsRest(mincell.getCol()) < curval) {
                curval = tempstructure.getColumnsRest(mincell.getCol());
            }
            mincell.setValue(curval);
            tempstructure.setArrowsRest(mincell.getArr(),tempstructure.getArrowsRest(mincell.getArr()) - curval);
            tempstructure.setColumnsRest(mincell.getCol(),tempstructure.getColumnsRest(mincell.getCol()) - curval);
        }
    }

    private void enlargeBasisTable() {
        structure = new TableStructure(tempstructure.getColNumber()+1,tempstructure.getArrNumber()+1);
        int restcounter = 0;

        for (int i = 0; i < tempstructure.getArrNumber();i++) {
            structure.setArr(i, tempstructure.getArrows(i));
        }
        for (int i = 0; i < tempstructure.getColNumber();i++) {
            structure.setCol(i, tempstructure.getColumns(i));
        }
        for (int i = 0; i < tempstructure.getArrNumber();i++) {
            structure.setArrowsRest(i, tempstructure.getArrowsRest(i));
        }
        for (int i = 0; i < tempstructure.getColNumber();i++) {
            structure.setColumnsRest(i, tempstructure.getColumnsRest(i));
        }
        for (int col = 0; col < tempstructure.getColNumber();col++) {
            for (int arr = 0; arr < tempstructure.getArrNumber();arr++) {
                structure.fillCell(col, arr, tempstructure.table[col][arr].getPrice(), tempstructure.table[col][arr].getMaxAllowed());
                structure.fillCellVal(col, arr, tempstructure.table[col][arr].getValue());
            }
        }
        for (int i = 0; i < structure.getColNumber();i++) {
            restcounter += structure.getColumnsRest(i);
        }
        structure.setArr(structure.getArrNumber()-1, restcounter);
        structure.setCol(structure.getColNumber()-1, restcounter);
        for (int i = 0; i < structure.getColNumber()-1;i++) {
            structure.fillCell(i, structure.getArrNumber() - 1, M, M);
        }
        for (int i = 0; i < structure.getArrNumber()-1;i++) {
            structure.fillCell(structure.getColNumber() - 1, i, M, M);
        }
        structure.fillCell(structure.getColNumber() - 1, structure.getArrNumber() - 1, M, M);
        for (int i = 0; i < structure.getColNumber()-1;i++) {
            structure.fillCellVal(i, structure.getArrNumber() - 1, structure.getColumnsRest(i));
        }
        for (int i = 0; i < structure.getArrNumber()-1;i++) {
            structure.fillCellVal(structure.getColNumber() - 1, i, structure.getArrowsRest(i));
        }       
    }

    private void makeBase() {
        int countbase = 0;
        for (int col = 0; col < structure.getColNumber();col++) {
            for (int arr = 0; arr < structure.getArrNumber();arr++) {
                if (structure.table[col][arr].getValue() != 0 && structure.table[col][arr].getValue() < structure.table[col][arr].getMaxAllowed()) {
                    countbase++;
                    structure.markAsBase(col, arr);
                }
            }
        }
        int singular = structure.getArrNumber() + structure.getColNumber() - 1 - countbase;
        if (singular > 0) {            
            HelperForCycleFinder helper = new HelperForCycleFinder(structure.getColNumber(), structure.getArrNumber());
            fillUp_Base(singular, helper, 1);
        }
    }

    private boolean optimize(Cell previousoptimized, int value) {
        Cell[] cycletrace = new Cell[structure.getArrNumber() * structure.getColNumber()];
        structure.setPotentials();                
        Cell c = structure.getMostPotentialCell();
        if (c == null) {
            try {
                structure.outputLastState(ofstream, previousoptimized, value);
            } catch (IOException ex) {
                Logger.getLogger(Performer.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        try {
            structure.outputCurState(ofstream, previousoptimized, value);
        } catch (IOException ex) {
            Logger.getLogger(Performer.class.getName()).log(Level.SEVERE, null, ex);
        }
        cycletrace[0] = c;
        c.setBase();
        HelperForCycleFinder helper = new HelperForCycleFinder(structure.getColNumber(), structure.getArrNumber());
        getCycleTrace(c.getCol(), c.getArr(), c.getCol(), c.getArr(), helper, 1, cycletrace);
        c.unsetBase(); 
        for (int i = 0; i < cycletrace.length; i++) {
            if (cycletrace[i] == null) {
                cycletrace[i] = c;
                break;
            }            
        }
        cycletrace = reduceCycle(cycletrace);
        int val = getValueToMove(cycletrace);
        moveValue(val, cycletrace);
        newBaseSet(cycletrace);        
        
        return optimize(c, val);
       
    }

   
    private void newBaseSet(Cell[] cycletrace) {
        
        cycletrace[0].setBase();

        int i = 1;
        while (cycletrace[i] != null) {
            if (cycletrace[i].getValue() == cycletrace[i].getMaxAllowed() || cycletrace[i].getValue() == 0) {
                cycletrace[i].unsetBase();
                return;
            }
            i++;
        }
        
    }
//баг тут
    private boolean fillUp_Base(int singular, HelperForCycleFinder helper, int step) {
        for (int arr = 0; arr < structure.getArrNumber(); arr++) {
            for (int col = 0; col < structure.getColNumber(); col++) {
                if (!structure.table[col][arr].base && structure.table[col][arr].isEmpty()) {
                    helper = new HelperForCycleFinder(structure.getColNumber(), structure.getArrNumber());
                    structure.table[col][arr].setBase();
                    helper.setVisited(col, arr);
                    if (!checkForCycle(col,arr,col,arr,helper,step)) {
                        singular--;
                        if (singular == 0) {
                            return true;
                        } else {
                            if (fillUp_Base(singular,helper,step)) {
                                return true;
                            }
                        }
                        structure.table[col][arr].unsetBase();
                        helper.unsetVisited(col, arr);
                    } else {                        
                        structure.table[col][arr].unsetBase();
                        helper.unsetVisited(col, arr);
                    }
                }
            }
        }
        return false;
    }

    private void moveValue(int value, Cell[] cycletrace) {
        int val = cycletrace[0].getValue();

        if (val == 0) {
            int i = 0;
            while (cycletrace[i] != null) {
                int selfval = cycletrace[i].getValue();
                cycletrace[i].setValue(value + selfval);
                i = i + 2;
            }

            i = 1;
            while (cycletrace[i] != null) {
                int selfval = cycletrace[i].getValue();
                cycletrace[i].setValue(selfval - value);
                i = i + 2;
            }
        } else {
            int i = 0;
            while (cycletrace[i] != null) {
                int selfval = cycletrace[i].getValue();
                cycletrace[i].setValue(selfval - value);
                i = i + 2;
            }

            i = 1;
            while (cycletrace[i] != null) {
                int selfval = cycletrace[i].getValue();
                cycletrace[i].setValue(selfval + value);
                i = i + 2;
            }
        }
       
    }

    private int getValueToMove(Cell[] cycletrace) {
        if (cycletrace[0].getValue() == 0) {
            int result = cycletrace[0].getMaxAllowed() - cycletrace[0].getValue();
            int i = 0;

            while (cycletrace[i] != null) {
                if (result > cycletrace[i].getMaxAllowed() - cycletrace[i].getValue()) {
                    result = cycletrace[i].getMaxAllowed() - cycletrace[i].getValue();
                }
                i = i + 2;
            }

            i = 1;
            while (cycletrace[i] != null) {
                if (result > cycletrace[i].getValue()) {
                    result = cycletrace[i].getValue();
                }
                i = i + 2;
            }
            return result;
        } else {
            int result = cycletrace[0].getValue();
            int i = 0;

            while (cycletrace[i] != null) {
                if (result > cycletrace[i].getValue()) {
                    result = cycletrace[i].getValue();
                }
                i = i + 2;
            }

            i = 1;
            while (cycletrace[i] != null) {
                if (result >  cycletrace[i].getMaxAllowed() - cycletrace[i].getValue()) {
                    result =  cycletrace[i].getMaxAllowed() - cycletrace[i].getValue();
                }
                i = i + 2;
            }
            return result;
        }
    }

    private Cell[] reduceCycle(Cell[] cycletrace) {
        int i = 1;
        int lastadmited = 0;
        Cell[] result = new Cell[structure.getArrNumber() * structure.getColNumber()];        
        result[0] = cycletrace[0];
        
        while (cycletrace[i+1] != null) {
            if (cycletrace[i+1].getArr() != result[lastadmited].getArr() && cycletrace[i+1].getCol() != result[lastadmited].getCol() ) {
                lastadmited++;
                result[lastadmited] = cycletrace[i];
                i++;
            } else {
                i++;
            }
        }
        return result;
    }

    private boolean getCycleTrace(int startcol, int startarr, int col, int arr, HelperForCycleFinder helper, int step, Cell[] cycletrace) {
        Cell c;    


        c = structure.getNextBaseCellToRight(col, arr, helper, step, startcol, startarr);
        if (c != null) {
            if (c.getCol() == startcol && c.getArr() == startarr) {
                return true;
            } else {
                if (getCycleTrace(startcol, startarr, c.getCol(), c.getArr(), helper, step+1, cycletrace)) {
                    cycletrace[step] = c;
                    return true;
                }
            }
        }
        if (c != null) {
            cycletrace[step] = null;
            helper.unsetVisited(c.getCol(), c.getArr());
        }
        c = structure.getNextBaseCellToLeft(col, arr, helper, step, startcol, startarr);
        if (c != null) {
            if (c.getCol() == startcol && c.getArr() == startarr) {
                return true;
            } else {
                if (getCycleTrace(startcol, startarr, c.getCol(), c.getArr(), helper, step+1, cycletrace)) {
                    cycletrace[step] = c;
                    return true;
                }
            }
        }
        if (c != null) {
            cycletrace[step] = null;
            helper.unsetVisited(c.getCol(), c.getArr());
        }
        c = structure.getNextBaseCellToUp(col, arr, helper, step, startcol, startarr);
        if (c != null) {
            if (c.getCol() == startcol && c.getArr() == startarr) {
                return true;
            } else {
                if (getCycleTrace(startcol, startarr, c.getCol(), c.getArr(), helper, step+1, cycletrace)) {
                    cycletrace[step] = c;
                    return true;
                }
            }
        }
        if (c != null) {
            cycletrace[step] = null;
            helper.unsetVisited(c.getCol(), c.getArr());
        }
        c = structure.getNextBaseCellToDown(col, arr, helper, step, startcol, startarr);
        if (c != null) {
            if (c.getCol() == startcol && c.getArr() == startarr) {
                return true;
            } else {
                if (getCycleTrace(startcol, startarr, c.getCol(), c.getArr(), helper, step+1, cycletrace)) {
                    cycletrace[step] = c;
                    return true;
                }
            }
        }
        if (c != null) {
            cycletrace[step] = null;
            helper.unsetVisited(c.getCol(), c.getArr());
        }
        return false;
    }

    private boolean checkForCycle(int col, int arr, int startcol, int startarr, HelperForCycleFinder helper, int step) {
        Cell c;

        c = structure.getNextBaseCellToRight(col, arr, helper, step, startcol, startarr);
        if (c != null) {
            if (c.getCol() == startcol && c.getArr() == startarr) {
                return true;
            } else {
                if (checkForCycle(c.getCol(), c.getArr(), startcol, startarr, helper, step + 1)) {
                    return true;
                }
            }
        }
        if (c != null)
            helper.unsetVisited(c.getCol(), c.getArr());
        c = structure.getNextBaseCellToLeft(col, arr, helper, step, startcol, startarr);
        if (c != null) {
            if (c.getCol() == startcol && c.getArr() == startarr) {
                return true;
            } else {
                if (checkForCycle(c.getCol(), c.getArr(), startcol, startarr, helper, step + 1)) {
                    return true;
                }
            }
        }
        if (c != null)
            helper.unsetVisited(c.getCol(), c.getArr());
        c = structure.getNextBaseCellToUp(col, arr, helper, step, startcol, startarr);
        if (c != null) {
            if (c.getCol() == startcol && c.getArr() == startarr) {
                return true;
            } else {
                if (checkForCycle(c.getCol(), c.getArr(), startcol, startarr, helper, step + 1)) {
                    return true;
                }
            }
        }
        if (c != null)
            helper.unsetVisited(c.getCol(), c.getArr());
        c = structure.getNextBaseCellToDown(col, arr, helper, step, startcol, startarr);
        if (c != null) {
            if (c.getCol() == startcol && c.getArr() == startarr) {
                return true;
            } else {
                if (checkForCycle(c.getCol(), c.getArr(), startcol, startarr, helper, step + 1)) {
                    return true;
                }
            }
        }
        if (c != null)
            helper.unsetVisited(c.getCol(), c.getArr());
        return false;
    }


}
