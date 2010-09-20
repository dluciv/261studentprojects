/*
 * Number node
 * Antonov Kirill(c), 2010
 */
package AST;

public class Number extends Expression {

    private int number;

    public Number(int value) {
        number = value;
    }

    public int Value() {
        return number;
    }

    /*@Override
    public void print() {
    System.out.print(number);
    }
     */
    public int calculate() {
        return number;
    }
}
