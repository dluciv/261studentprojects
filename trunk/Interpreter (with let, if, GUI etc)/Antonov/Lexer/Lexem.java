/*
 *класс лексема - хранит тип, позицию и значение (если есть) лексемы
 * Antonov Kirill(c), 2010
 */
package Lexer;

public class Lexem {

    private int number;
    private String identifier;
    private LexemKind type;
    private Position position = null;

    public Lexem(LexemKind lexem, Position position) {
        type = lexem;
        this.position = position;
    }

    public Lexem(LexemKind lexem, int number, Position position) {
        type = lexem;
        this.number = number;
        this.position = position;
    }

    public Lexem(LexemKind lexem, String identifier, Position position) {
        type = lexem;
        this.identifier = identifier;
        this.position = position;
    }

    public LexemKind getTypeLexem() {
        return type;
    }

    public String getStringLexem() {
        return identifier;
    }

    public int getIntLexem() {
        return number;
    }

    public Position getPosition() {
        return position;
    }
}
