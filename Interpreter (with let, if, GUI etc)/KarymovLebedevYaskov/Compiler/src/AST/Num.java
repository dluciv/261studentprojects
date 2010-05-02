//Lebedev Dmitry 2010 (c)
package AST;

public class Num extends Tree {
    private int value;

    public Num(int value) {
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
