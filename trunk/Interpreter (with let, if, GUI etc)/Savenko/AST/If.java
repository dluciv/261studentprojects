package savenko.AST;

import savenko.AST.Expression;



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
