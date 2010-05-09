//Lebedev Dmitry 2010 (c)
package AST;

public class Let extends Expression {

    private String id;
    private int value;
    private Expression expression;

    public Let(String id, int value, Expression expr) {
        this.id = id;
        this.value = value;
        this.expression = expr;
    }
}
