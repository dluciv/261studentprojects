package spusk1;

public class Binding extends Expression{
    private Ident ident ;
    private Expression expr;

    Binding(Ident ident, Expression expr) {
        this.ident=ident;
        this.expr=expr;
    }
}
