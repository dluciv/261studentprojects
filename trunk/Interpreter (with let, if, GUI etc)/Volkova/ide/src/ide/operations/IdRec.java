/*
 *
 *
 * (c) Volkova Ekatetina
 */
package ide.operations;

public class IdRec implements Expression {

    private String id;

    public IdRec(String id) {
        this.id = id;
    }

    public String getIdRec() {
        return id;
    }
}
