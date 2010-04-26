package interpretator;


public class Multiply extends BinaryOperation {

    public Multiply(Tree leftMultiplier, Tree rightMultiplier) {
        super(leftMultiplier, rightMultiplier);
    }

    @Override
    protected void printOperation() {
        System.out.print("*");
    }
}