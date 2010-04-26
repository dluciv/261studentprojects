package interpretator;

public class Number implements Tree {

    public int number;

    Number(int number1) {
        number = number1;
    }

    public void print() {
        System.out.print(number);
    }
}
