/*
 * *узел для функции - содержит идентификатор и выражение, в котором он используется
 * fun ident -> expr(ident)
 * Antonov Kirill(c), 2010
 */
package AST;

public class Function extends Expression {

    private Identificator identifier;
    private Expression expression;
    private Type type;

    public Function(Identificator new_identifier, Expression new_expression, Type new_type) {
        identifier = new_identifier;
        expression = new_expression;
        type = new_type;
    }

    public Identificator getIdentifier() {
        return identifier;
    }

    public Type getType() {
        return type;
    }

    public Expression getExpression() {
        return expression;
    }
}
