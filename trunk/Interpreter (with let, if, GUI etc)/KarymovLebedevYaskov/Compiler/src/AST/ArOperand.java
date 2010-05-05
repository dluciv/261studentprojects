//Lebedev Dmitry 2010 (c)
package AST;

public class ArOperand extends Expression {
    private int value;

    public ArOperand(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

//    public int evaluate() {
//        return value;
//    }
//
//    public void print() {
//        System.out.printf("%s ", value);
//    }
}
