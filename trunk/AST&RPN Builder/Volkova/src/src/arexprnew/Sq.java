/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexprnew;

//import java.util.ArrayList;
import java.util.ArrayList;
//import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kate
 */
public class Sq implements Expression {

    private ArrayList<Expression> ex = new ArrayList<Expression>();

    
    public Expression addOp(Expression expression) {
        ex.add(expression);
        return expression;
    }


    //interface List
    public List<Expression> returnOps() {
        return ex;
    }
}
