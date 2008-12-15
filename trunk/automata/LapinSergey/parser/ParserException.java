package parser;

public class ParserException extends Exception{
    
    public static final int WRONG_STAPLERS = 0;
    public static final int WRONG_USING_OPERATORS = 1;
    
    int type;

    public ParserException(int type) {
        super("Parser exception");
        this.type = type;
    }

    public int getType() {
        return type;
    }
}