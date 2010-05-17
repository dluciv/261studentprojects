package savenko.ast;

//import java.util.Scanner;
import savenko.Debugger;
import savenko.Interpreter;
import savenko.InterpreterException;
import savenko.ParserException;

public class Program {

     private Sequence sequence;
     private Interpreter interpreter;

     public String Interpret() throws InterpreterException {
          interpreter = new Interpreter();

          IntValue result = (IntValue)interpreter.interpret(sequence);

          return String.valueOf(result.getValue());
     }
     
     public String Debug() throws InterpreterException{
    	 Debugger debugger = new Debugger();

         //IntValue result = (IntValue)interpreter.interpret(sequence);

         return null;//String.valueOf(result.getValue());
     }

     public Program(String program) throws ParserException {
          Lexer lexer = new Lexer(program);
          sequence = (new Parser(lexer)).getSequence();
     }
}
