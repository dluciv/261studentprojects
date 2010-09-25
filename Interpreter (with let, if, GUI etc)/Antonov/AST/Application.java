/*
 * application of function to expr:
 * expr expr
 * Antonov Kirill(c), 2010
 */
package AST;

public class Application extends Expression {

    private Expression function;
    private Expression expression;

    public Application(Expression function, Expression expression) {
        this.function = function;
        this.expression = expression;
    }

    public Expression getFunction() {
        return function;
    }

    public Expression getExpression() {
        return expression;
    }
}
