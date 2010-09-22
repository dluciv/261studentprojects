/*
 * Identificator node
 * Antonov Kirill(c), 2010
 */
package AST;

public class Identificator extends Expression {

    private String identificator;
    private Type type;

    public Identificator(String new_identificator) {
        identificator = new_identificator;
    }

    public Identificator(String new_identificator, Type new_type) {
        identificator = new_identificator;
        type = new_type;
    }

    public String GetName() {
        return identificator;
    }

    public Type GetType() {
        return type;
    }
}
