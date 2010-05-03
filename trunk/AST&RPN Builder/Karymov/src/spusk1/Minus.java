
package spusk1;

public class Minus extends BinaryOperation {

    public Minus(Tree subtract, Tree subtracter) {
        super(subtract, subtracter);
    }
    @Override
    public int calculate() {
        int result = this.GetLeft().calculate() - this.GetRight().calculate();
        return result;
    }
    @Override
    protected void printOperation() {
        System.out.print("-");
    }
}