package ide.operators;

import ide.operations.Expression;

public class BeginEnd implements Expression {

    private Sq sq;

    public BeginEnd(Sq sq) {
        this.sq = sq;
    }

    public Sq getSq() {
        return sq;
    }
}
