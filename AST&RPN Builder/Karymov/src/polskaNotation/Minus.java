/**
 *
 * @author Карымов Антон 261 группа
 */
package polskaNotation;

public class Minus extends BinaryOperation {

    public Minus(Tree subtract, Tree subtracter) {
        super(subtract, subtracter);
    }

    @Override
    protected void printOperation() {
        System.out.print("-");
    }
}