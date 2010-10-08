/*
 * Содержит дерево программы, и функции обращающиеся к интерпретатору 
 * Antonov Kirill(c), 2010
 */
package name.kirill.ml.gui;

import name.kirill.ml.ast.Sequence;
import name.kirill.ml.environment.BoolValue;
import name.kirill.ml.environment.IntValue;
import name.kirill.ml.exception.InterpreterException;
import name.kirill.ml.exception.ParserException;
import name.kirill.ml.interpreter.Interpret;
import name.kirill.ml.environment.Value;
import name.kirill.ml.lexer.Lexer;
import name.kirill.ml.parser.Parser;
import name.kirill.ml.types.TypeCheking;
//import sun.awt.SunHints.Value;

public class Program {

    private Sequence sequence;
    private Interpret interpreter;

    public String Interpret() throws InterpreterException {
        interpreter = new Interpret();

        (new TypeCheking()).Check(sequence);
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
