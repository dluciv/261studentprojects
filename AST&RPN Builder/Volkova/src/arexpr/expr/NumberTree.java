/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arexpr.expr;

/**
 *
 * @author kate
 */
public class NumberTree extends Tree<Integer> {

    public NumberTree(Integer value) {
        super(null, null, value);
    }


    @Override
    public String toString(){
       return value + "";
    }

}
