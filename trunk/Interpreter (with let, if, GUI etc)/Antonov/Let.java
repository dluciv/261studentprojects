

package LexerAndParser;

public class Let implements Expression{

    private Expression expr, expression;

    public Let(Expression new_clause, Expression new_expr){
        expr = new_clause;
        expression = new_expr;
    }

    public Expression getClause(){
        return expr;
    }

    public Expression getExpression(){
        return expression;
    }

}

