package ide;

import ide.exceptions.InterException;
import ide.exceptions.ParserException;

public class Main {

    public static void main(String[] args) throws ParserException, InterException {

        String input = "print(1+3)";
        Interpreter interpreter = new Interpreter();
        interpreter.interpret(new Parser(new Lexer(input)).getSq());

    }
}
