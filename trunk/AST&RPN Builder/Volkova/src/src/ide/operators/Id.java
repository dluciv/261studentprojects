package ide.operators;

import ide.operations.Expression;

public class Id implements Expression {

    private String id;

    public Id(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
