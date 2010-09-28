/*
 * Negatenode (!=)
 * Antonov Kirill(c), 2010
 */
package name.kirill.ml.ast;

public class Negate extends Expression {

    private Expression expr = null;

    public Negate(Expression expr) {
        this.expr = expr;
    }
}
