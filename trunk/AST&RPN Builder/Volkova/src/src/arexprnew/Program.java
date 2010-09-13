/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexprnew;

/**
 *
 * @author kate
 */
public class Program {

    private Sq sq;
    private Interpreter interpreter;

    public String Interpret() throws InterException {
        interpreter = new Interpreter();

        IntValue res = (IntValue) interpreter.interpret(sq);

        return String.valueOf(res.getValue());
    }

    public Program(String programm) throws ParserException {
        Lexer lexer = new Lexer(programm);
        sq = (new Parser(lexer)).getSq();
    }
}
