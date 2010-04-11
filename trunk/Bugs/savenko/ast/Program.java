package savenko.ast;

import savenko.Interpreter;
import savenko.InterpreterException;
import savenko.NullIDException;
import savenko.ParserException;


public class Program {
    
    private Sequence sequence;
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
        Parser parser =new Parser(lexer);
	sequence = (parser).getSequence();
    }

}
