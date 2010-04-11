package savenko.ast;

//import java.util.Scanner;

import savenko.ast.Sequence;
import savenko.ast.Value;
import savenko.ast.Parser;
import savenko.ast.Lexer;
import savenko.Interpreter;
import savenko.InterpreterException;
import savenko.NullIDException;
import savenko.ParserException;


public class Program {
    
    private static Sequence sequence;
    private Interpreter interpreter;
    
    public String Interpret(){
        interpreter = new Interpreter();
        Value result = null;

        try {
            result = interpreter.interpret(sequence);
        }
        catch (NullIDException e) {
            System.out.print("NullIDException");
            return "NullIDException";
        }
        catch (InterpreterException e) {
            System.out.print("unknown Error");
            return "unknown Error";
        }

        return String.valueOf(result.getIntValue())+"\nBuild Successfull!";
    }
    
    public Program(String args) throws ParserException{
        Lexer lexer = new Lexer(args);
	sequence = (new Parser(lexer)).getSequence();
    }

}
