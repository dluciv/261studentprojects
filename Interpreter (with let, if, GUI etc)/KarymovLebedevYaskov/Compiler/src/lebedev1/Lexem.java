package lebedev;

public class Lexem {
    private int value;
    private Lexer.LexemKind lexem;

    public Lexem(Lexer.LexemKind lexem) {
        this.lexem = lexem;
    }

    public Lexem(Lexer.LexemKind lexem, int value) {
        this.lexem = lexem;
        this.value = value;
    }

    public Lexer.LexemKind getLexemKind(){
        return lexem;
    }

    public int getValue(){
        return value;
    }

}
