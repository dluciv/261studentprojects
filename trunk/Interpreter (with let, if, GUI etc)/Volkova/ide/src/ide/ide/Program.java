/*
 *
 *
 * (c) Volkova Ekatetina
 */
package ide.ide;

import ide.value.IntValue;
import ide.operators.Sq;
import ide.exceptions.InterException;
import ide.exceptions.ParserException;

public class Program {

    private Sq sq;
    private Interpreter interpreter;

    public String Interpret() throws InterException {
        interpreter = new Interpreter();

        return String.valueOf(((IntValue)interpreter.beginInterpretation(sq)).getValue());
    }

    public Program(String program) throws ParserException {
        Lexer lexer = new Lexer(program);
        sq = (new Parser(lexer)).getSq();
    }
}
 
