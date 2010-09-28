/*
 * Number node
 * Antonov Kirill(c), 2010
 */
package name.kirill.ml.ast;

public class Number extends Expression {

    private int number;

    public Number(int value) {
        number = value;
    }

    public int Value() {
        return number;
    }
}
