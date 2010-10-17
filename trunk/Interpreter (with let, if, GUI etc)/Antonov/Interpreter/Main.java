package interpreter;

import ast.Sequence;
import exception.InterpreterException;
import lexer.Lexer;
import parser.Parser;
import types.TypeCheckingVisitor;

public class Main {

    public static void main(String[] args) throws InterpreterException {

        //String input = "let x = 8 in x; if x != 7 then let x = 2*x+3 in x else let x = 0 in x; print x";
        //String input = "let x:bool = true in x; print(x)";
        //String input = "let f:(int->int) = fun x:(int->int) -> 10 in f(3);";
        //String input = "if 4>8 then true else false";
        //String input = "let f:(bool->bool) = fun x:(bool->bool) -> x in f (true);";
        String input = "let a:bool = 3 in a; print a";
        //String input = "fun x:(int -> unit) -> print x";
        //String input = "print (let a:int = 5 in a + 5);";
        Interpret interpreter = new Interpret();
        Sequence tree = (new Parser(new Lexer(input))).getSequence();
        TypeCheckingVisitor typeCheckingVisitor = new TypeCheckingVisitor();
        tree.Accept(typeCheckingVisitor);
        interpreter.interpret(tree);
    }
}
