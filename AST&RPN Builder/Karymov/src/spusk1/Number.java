package spusk1;

public class Number extends Expression {

    public int number;

    Number(int number1) {
        number = number1;
    }
    public int getNumber(){
        return number;
    }
    public void print() {
        System.out.print(number);
    }
public int calculate() {
        return number;
    }
}
