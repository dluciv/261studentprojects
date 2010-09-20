/*
 * Negatenode (!=)
 * Antonov Kirill(c), 2010
 */
package AST;

public class Negate extends Expression {

    private Expression expr = null;

    public Negate(Expression expr) {
        this.expr = expr;
    }
}
