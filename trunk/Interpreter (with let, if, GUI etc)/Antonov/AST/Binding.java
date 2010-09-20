/*
 * associates an identifier with its value
 * last expression is the scope of this identifier
 * Antonov Kirill(c), 2010
 */
package AST;

public class Binding extends Expression {

    private Identificator identifier;
    private Expression expression;
    private Expression value;
    private Type type;

    public Binding(Identificator new_identifier, Expression new_expression, Expression new_value, Type new_type) {
        identifier = new_identifier;
        expression = new_expression;
        value = new_value;
        type = new_type;
    }

    public Identificator getIdentifier() {
        return identifier;
    }

    public Expression getExpression() {
        return expression;
    }

    public Type getType() {
        return type;

    }

    public Expression getValue() {
        return value;
    }
}
