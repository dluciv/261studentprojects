/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexprnew;

/**
 *
 * @author kate
 */
public class RepresLexem {

    private int number;
    private String id;
    private Lexeme type;
    private Position position = null;

    //diffrent constructors for different types of lexems - for example, fun
    public RepresLexem(Lexeme lexem, Position position) {
        type = lexem;
        this.position = position;
    }

    //for example, 3
    public RepresLexem(Lexeme lexem, int number, Position position) {
        type = lexem;
        this.number = number;
        this.position = position;
    }

    //for example, >
    public RepresLexem(Lexeme lexem, String id, Position position) {
        type = lexem;
        this.id = id;
        this.position = position;
    }

    public Lexeme getTypeLexem() {
        return type;
    }

    public String getStringLexem() {
        return id;
    }

    public int getIntLexem() {
        return number;
    }

    public Position getPosition() {
        return position;
    }
}
