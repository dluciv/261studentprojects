/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eda.tm.gui;

import eda.tm.StateSymbol;
import eda.tm.StateSymbolMove;
import eda.tm.Moving;

/**
 *
 * @author nastya
 */
public class TemporaryData {

    private StateSymbol stateSymbol;
    private StateSymbolMove stateSymbolMove;
    

    public TemporaryData(String startState, char startSymbol, String endState, char endSymbol, Moving move) {
        stateSymbol = new StateSymbol(startState, startSymbol);
        stateSymbolMove = new StateSymbolMove(move, endState, endSymbol);
    }

    public StateSymbol getStateSymbol() {
        return stateSymbol;
    }

    public StateSymbolMove getStateSymbolMove() {
        return stateSymbolMove;
    }


    
}
