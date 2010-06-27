/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arexpr.expr;

/**
 *
 * @author kate
 */
public abstract class Tree<V extends Object> {
    protected Tree left;
    protected Tree right;
    protected V value;

    public Tree(Tree left, Tree right, V value){
        this.left = left;
        this.right = right;
        this.value = value;
    }


}
