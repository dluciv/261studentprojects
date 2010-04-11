package savenko.AST;

//import java.util.Scanner;

import savenko.AST.Sequence;
import savenko.AST.Value;
import savenko.AST.Parser;
import savenko.AST.Lexer;
import savenko.Interpreter;
import savenko.InterpreterException;
import savenko.NullIDException;
import savenko.ParserException;


public class Programm {
    
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
    
    public Programm(String args) throws ParserException{
        Lexer lexer = new Lexer(args);
	sequence = (new Parser(lexer)).getSequence();
    }

}
