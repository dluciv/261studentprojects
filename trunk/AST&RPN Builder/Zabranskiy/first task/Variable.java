package analizator;

public class Variable extends Lexem {

    String value;

    public Variable(String value, Position position) {
        super(LexType.VARIABLE, position);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
