/*
 * Содержит дерево программы, и функции обращающиеся к интерпретатору 
 * Antonov Kirill(c), 2010
 */
package GUI;

import AST.Sequence;
import Exception.InterpreterException;
import Exception.ParserException;
import Interpreter.BoolValue;
import Interpreter.IntValue;
import Interpreter.Interpret;
import Interpreter.Value;
import Lexer.LexerTwo;
import Parser.ParserTwo;

public class Program {

    private Sequence sequence;
    private Interpret interpreter;

    public String Interpret() throws InterpreterException {
        interpreter = new Interpret();

        Value result = interpreter.interpret(sequence);
        interpreter.interpret(sequence);

        if (result instanceof IntValue) {
            return String.valueOf(((IntValue) result).getValue());
        } else if (result instanceof BoolValue) {
            return String.valueOf(((BoolValue) result).getValue());
        } else {
            return null;
        }

        //return "";
    }

    public Program(String program) throws ParserException {
        LexerTwo lexer = new LexerTwo(program);
        sequence = (new ParserTwo(lexer)).getSequence();
    }
}
