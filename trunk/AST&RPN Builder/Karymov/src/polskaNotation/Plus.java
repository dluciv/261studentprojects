/**
 *
 * @author Карымов Антон 261 группа
 */
package polskaNotation;

public class Plus extends BinaryOperation {

    public Plus(Tree leftAddend, Tree rightAddend) {
        super(leftAddend, rightAddend);
    }

    @Override
    protected void printOperation() {
        System.out.print("+");
    }
}
