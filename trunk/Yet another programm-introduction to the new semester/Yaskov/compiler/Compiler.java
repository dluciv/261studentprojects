/*
 * 
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package compiler;

import lexer.Lexer;
import parser.Parser;
import interpreter.Interpreter;

public class Compiler {
    public static void main(String[] args) {
        String input = "one = 5;" + "\n" +
                       "two = 10;" + "\n" +
                       "print(one);" + "\n" +
                       "print(two);" + "\n" +
                       "one = (one * two) / 25;" + "\n" +
                       "print(one);" + "\n" +
                       "print((one + two * 4) / 2);" +  "\n" +
                       '\0';
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer.getTokenStream(), lexer.getVarTable(), lexer.getErrorQnt());
        Interpreter interpreter = new Interpreter(parser.getOutput(), lexer.getVarTable(), parser.getErrorQnt());

        interpreter.toInterpret();
    }
}
