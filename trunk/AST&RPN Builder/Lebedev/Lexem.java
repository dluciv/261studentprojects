package ast;

public class Lexem {
    private int value;
    private LexemKind lexem;

    public Lexem(LexemKind lexem) {
        this.lexem = lexem;
    }

    public Lexem(LexemKind lexem, int value) {
        this.lexem = lexem;
        this.value = value;
    }

    public LexemKind getLexemKind(){
        return lexem;
    }

    public int getValue(){
        return value;
    }

}
