package ide.operators;

import ide.operations.Expression;
import java.util.ArrayList;
import java.util.List;

public class Sq implements Expression {

    private ArrayList<Expression> ex = new ArrayList<Expression>();

    public Expression addOp(Expression expression) {
        ex.add(expression);
        return expression;
    }

    public List<Expression> returnOps() {
        return ex;
    }
}
