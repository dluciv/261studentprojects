package analizator;

public class Number extends Lexem implements Node {

    int number;

    public Number(int value, Position position) {
        super(LexType.NUMBER, position);
        number = value;
    }

    public int getValue() {
        return number;
    }

    @Override
    public void print() {
        System.out.print(number + " ");
    }
}
