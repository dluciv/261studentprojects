
package interpretator;

public class Minus extends BinaryOperation {

    public Minus(Tree subtract, Tree subtracter) {
        super(subtract, subtracter);
    }

    @Override
    protected void printOperation() {
        System.out.print("-");
    }
}