/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.graph;

/**
 * throws if error occured during prepare graph for walking
 * @author dmitriy
 */
public class WalkerException extends Exception {

    public WalkerException() {
        super("You can use graph only after epsilon-closure");
    }
}
