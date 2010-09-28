 /*
 * IF node
 * If <condition> then <if_expression> else <else_expr>
 *  Antonov Kirill(c), 2010
 */
package name.kirill.ml.ast;

public class If extends Expression {

    private Expression expr, if_expr, else_expr;

    public If(Expression new_expr, Expression new_ifexpr, Expression new_elsexpr) {
        expr = new_expr;
        if_expr = new_ifexpr;
        else_expr = new_elsexpr;
    }

    public Expression getExpression() {
        return expr;
    }

    public Expression getIfExpression() {
        return if_expr;
    }

    public Expression getElseExpression() {
        return else_expr;
    }
}
