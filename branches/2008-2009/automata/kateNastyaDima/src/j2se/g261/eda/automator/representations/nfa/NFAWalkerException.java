/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.representations.nfa;

/**
 * throws if error occured during prepare graph for walking
 * @author dmitriy
 */
public class NFAWalkerException extends Exception {

    public NFAWalkerException() {
        super("You can use graph only after epsilon-closure");
    }
}
