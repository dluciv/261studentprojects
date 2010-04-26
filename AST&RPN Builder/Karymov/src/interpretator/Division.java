package interpretator;

public class Division extends BinaryOperation {

    public Division(Tree divide, Tree divisor) {
        super(divide, divisor);
    }

    @Override
    protected void printOperation() {
        System.out.print("/");
    }
}