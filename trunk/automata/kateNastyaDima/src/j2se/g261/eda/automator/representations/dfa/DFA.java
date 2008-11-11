/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.representations.dfa;

import j2se.g261.eda.automator.representations.*;

/**
 *
 * @author nastya
 */
public class DFA extends FiniteAutomata<DFANode>{

    public DFA(DFANode root) {
        ends.clear();
    }

    public void markAsEnd(DFANode node){
        if(!all.contains(node)){
            all.add(node);
        }
        ends.add(node);
    }

}
