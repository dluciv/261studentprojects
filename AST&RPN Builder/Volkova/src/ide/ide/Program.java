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

        IntValue res = (IntValue) interpreter.interpret(sq);

        return String.valueOf(res.getValue());
    }

    public Program(String program) throws ParserException {
        Lexer lexer = new Lexer(program);
        sq = (new Parser(lexer)).getSq();
    }
}
 
