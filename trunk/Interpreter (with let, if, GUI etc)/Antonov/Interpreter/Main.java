package Interpreter;

import Exception.InterpreterException;
import Lexer.LexerTwo;
import Parser.ParserTwo;

public class Main {

    public static void main(String[] args) throws InterpreterException {

        //String input = "let x = 8 in x; if x != 7 then let x = 2*x+3 in x else let x = 0 in x; print x";
        //String input = "let x = 6 in x; let x = x + 1 in x; print x";
        //String input = "let x:bool = true in x; print(x)";
        String input = "let f:int->>int = fun x:int->>int -> x+1 in f(0);";
        Interpret interpreter = new Interpret();
        interpreter.interpret((new ParserTwo(new LexerTwo(input))).getSequence());

    }
}
