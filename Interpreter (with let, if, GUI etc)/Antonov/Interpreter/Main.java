package name.kirill.ml.interpreter;

import name.kirill.ml.ast.Sequence;
import name.kirill.ml.ast.Tree;
import name.kirill.ml.exception.InterpreterException;
import name.kirill.ml.lexer.Lexer;
import name.kirill.ml.parser.Parser;
import name.kirill.ml.types.TypeCheking;

public class Main {

    public static void main(String[] args) throws InterpreterException {

        //String input = "let x = 8 in x; if x != 7 then let x = 2*x+3 in x else let x = 0 in x; print x";
        //String input = "let x:bool = true in x; print(x)";
        //String input = "let f:(int->int) = fun x:(int->int) -> 10 in f(9);";
        //String input = "if 6>8 then true else false";
        //String input = "let f:(bool->bool) = fun x:(bool->bool) -> x in f (true);";
        //String input = "let a:int = 4 in a; print a";
        String input = "fun x:(int -> unit) -> print x";
        //String input = "print (let a:int = 5 in a + 5);";
        Interpret interpreter = new Interpret();
        Sequence tree = (new Parser(new Lexer(input))).getSequence();
        (new TypeCheking()).Check(tree);
        interpreter.interpret(tree);
    }
}
