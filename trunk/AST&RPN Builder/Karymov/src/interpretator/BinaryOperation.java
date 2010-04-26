package interpretator;

public abstract class BinaryOperation implements Tree {

    public Tree left;
    public Tree right;

    BinaryOperation(Tree leftArgument, Tree rightArgument) {
        left = leftArgument;
        right = rightArgument;
    }

    public Tree GetLeft() {
        return left;
    }

    public Tree GetRight() {
        return right;
    }

    public void print() {
        left.print();
        System.out.print(" ");
        right.print();
        System.out.print(" ");
        printOperation();
    }

    abstract protected void printOperation();
}
