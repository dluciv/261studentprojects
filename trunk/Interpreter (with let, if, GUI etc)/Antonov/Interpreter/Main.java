package name.kirill.ml.interpreter;

import name.kirill.ml.exception.InterpreterException;
import name.kirill.ml.lexer.Lexer;
import name.kirill.ml.parser.Parser;

public class Main {

    public static void main(String[] args) throws InterpreterException {

        //String input = "let x = 8 in x; if x != 7 then let x = 2*x+3 in x else let x = 0 in x; print x";
        //String input = "let x:int = true in x; print(x)";
        //String input = "let f:(bool->int) = fun x:(int->int) -> x+10 in f(9);";
        //String input = "if 4<5 then 8 else true";
        String input = "let f:(int->unit) = fun x:(int->unit) -> print x in f(123);";
        //String input = "let a:unit = 4 in a; print a";
        //String input = "fun x:(int -> bool) -> 4";
        Interpret interpreter = new Interpret();
        interpreter.interpret((new Parser(new Lexer(input))).getSequence());

    }
}
