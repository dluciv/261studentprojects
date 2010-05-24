/*
 * узел для операции If
 * If <clause> then <if_expression> else <else_expr>
 * Savenko Maria(c)
 */
package savenko.ast;

public class If implements Expression{
    
    private Expression clause, if_expression, else_expr;
    
    public If(Expression new_clause, Expression new_ifexpr, Expression new_elsexpr){
        clause = new_clause;
        if_expression = new_ifexpr;
        else_expr = new_elsexpr;
    }
    
    public Expression getClause(){
        return clause;
    }

    public Expression getIfExpression(){
        return if_expression;
    }
    
    public Expression getElseExpression(){
        return else_expr;
    }

}
