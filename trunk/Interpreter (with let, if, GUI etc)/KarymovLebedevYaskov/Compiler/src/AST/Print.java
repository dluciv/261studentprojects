//Lebedev Dmitry 2010 (c)
package AST;

public class Print extends Expression {

    private Object value;

    public Print(Object obj) {
        this.value = obj;
    }

    public void print(){
        System.out.println(value);
    }
}
