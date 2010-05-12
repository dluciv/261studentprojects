//Lebedev Dmitry 2010 (c)
package ast;

public class ArOperand extends Expression {
    private Integer value;

    public ArOperand(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
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
