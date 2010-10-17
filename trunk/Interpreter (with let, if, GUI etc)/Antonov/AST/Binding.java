/*
 * associates an identifier with its value
 * last expression is the scope of this identifier
 * Antonov Kirill(c), 2010
 */
package ast;

public class Binding extends Expression {

    private Identifier identifier;
    private Expression expression;
    private Expression value;

    public Binding(Identifier new_identifier, Expression new_expression, Expression new_value) {
        identifier = new_identifier;
        expression = new_expression;
        value = new_value;

    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public Expression getExpression() {
        return expression;
    }

    public Expression getValue() {
        return value;
    }

}
