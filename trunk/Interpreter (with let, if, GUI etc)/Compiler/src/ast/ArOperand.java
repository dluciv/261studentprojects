//Lebedev Dmitry 2010 (c)
package ast;

import lexerandparser.Position;

public class ArOperand extends Expression {
    private Integer value;

    public ArOperand(Integer value, Position position) {
        this.value = value;
        setPositon(position);
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
