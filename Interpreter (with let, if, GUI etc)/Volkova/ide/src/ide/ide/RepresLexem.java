package ide.ide;

public class RepresLexem {

    private int number;
    private String id;
    private Lexeme type;
    private Position position = null;

    public RepresLexem(Lexeme lexem, Position position) {
        type = lexem;
        this.position = position;
    }

/*
 *
 *
 * (c) Volkova Ekatetina
 */
    public RepresLexem(Lexeme lexem, int number, Position position) {
        type = lexem;
        this.number = number;
        this.position = position;
    }

    public RepresLexem(Lexeme lexem, String id, Position position) {
        type = lexem;
        this.id = id;
        this.position = position;
    }

    public Lexeme getTypeLexem() {
        return type;
    }

    public String getStringIdLexem() {
        return id;
    }

    public int getIntLexem() {
        return number;
    }

    public Position getPosition() {
        return position;
    }
}
