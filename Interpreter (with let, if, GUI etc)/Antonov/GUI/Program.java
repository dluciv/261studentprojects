/*
 * Содержит дерево программы, и функции обращающиеся к интерпретатору 
 * Antonov Kirill(c), 2010
 */
package GUI;

import AST.Sequence;
import Exception.InterpreterException;
import Exception.ParserException;
import Interpreter.IntValue;
import Interpreter.Interpret;
import Lexer.LexerTwo;
import Parser.ParserTwo;

public class Program {

    private Sequence sequence;
    private Interpret interpreter;

    public String Interpret() throws InterpreterException {
        interpreter = new Interpret();

        IntValue result = (IntValue) interpreter.interpret(sequence);
        interpreter.interpret(sequence);

        return String.valueOf(result.getValue());
        //return "";
    }
    

    public Program(String program) throws ParserException {
        LexerTwo lexer = new LexerTwo(program);
        sequence = (new ParserTwo(lexer)).getSequence();
    }
}

