/*
 * Содержит дерево программы, и функции обращающиеся к интерпретатору 
 * Antonov Kirill(c), 2010
 */
package gui;

import ast.Sequence;
import environment.BoolValue;
import environment.IntValue;
import exception.InterpreterException;
import exception.ParserException;
import interpreter.Interpret;
import environment.Value;
import lexer.Lexer;
import parser.Parser;
import types.TypeCheckingVisitor;
//import sun.awt.SunHints.Value;

public class Program {

    private Sequence sequence;
    private Interpret interpreter;

    public String Interpret() throws InterpreterException {
        interpreter = new Interpret();

        TypeCheckingVisitor typeCheckingVisitor = new TypeCheckingVisitor();
        sequence.Accept(typeCheckingVisitor);

        Value result = interpreter.interpret(sequence);
        interpreter.interpret(sequence);

        if (result instanceof IntValue) {
            return String.valueOf(((IntValue) result).getValue());
        } else if (result instanceof BoolValue) {
            return String.valueOf(((BoolValue) result).getValue());
        } else {
            return null;
        }

    }

    public Program(String program) throws ParserException {
        Lexer lexer = new Lexer(program);
        sequence = (new Parser(lexer)).getSequence();
    }
}
