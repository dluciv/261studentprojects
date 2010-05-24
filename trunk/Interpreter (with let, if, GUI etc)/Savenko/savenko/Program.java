/*
 * Содержит дерево программы, и функции обращающиеся к интерпретатору и дебаггеру
 * Savenko Maria(c)
 */
package savenko;

import savenko.lexer.Lexer;
import savenko.interpreter.IntValue;
import savenko.parser.Parser;
import savenko.ast.Sequence;
import savenko.interpreter.Debugger;
import savenko.interpreter.Interpreter;
import savenko.interpreter.InterpreterException;
import savenko.parser.ParserException;

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
