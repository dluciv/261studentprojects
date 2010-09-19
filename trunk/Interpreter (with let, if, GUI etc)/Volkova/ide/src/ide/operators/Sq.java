/*
 * class Sq - class for representation of node of sequence of expressions. Sq implements interface Tree.
 * expressions are to each other separated by the symbol ';'. after the last expression it can not write.
 * the result of a sequence of expressions - the last expression
 *
 * (c) Volkova Ekaterina
 */
package ide.operators;

import ide.operations.Expression;
import ide.operations.Tree;
import java.util.ArrayList;
import java.util.List;

public class Sq implements Tree {

    private ArrayList<Expression> ex = new ArrayList<Expression>();

    public Expression addOp(Expression expression) {
        ex.add(expression);
        return expression;
    }

    public List<Expression> returnOps() {
        return ex;
    }
}
