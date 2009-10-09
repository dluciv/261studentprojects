/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotheart.regexp.AST.node;

/**
 *
 * @author m08ksa
 */
public abstract class AbstractNode {

    public AbstractNode parent;
    @Override
    public abstract String toString();
}
