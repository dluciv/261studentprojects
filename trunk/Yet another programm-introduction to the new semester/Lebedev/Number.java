//Lebedev Dmitry 2010 (c)
package ast;

public class Number implements Tree {

    private int value;

    public Number(int value) {
        this.value = value;
    }

    public int evaluate() {
        return value;
    }

    public void print() {
        System.out.printf("%s ", value);
    }
}
