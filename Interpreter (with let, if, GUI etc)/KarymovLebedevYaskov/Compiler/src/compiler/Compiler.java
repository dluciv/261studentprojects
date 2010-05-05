/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package compiler;

import AST.*;
import Yaskov.Interpreter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Compiler {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileLoader fileLoader = new FileLoader("d:\\mlProg.txt");
        String input = fileLoader.loadFile();

        /*Lexer lexer = new Lexer(input);

        for (Token token : lexer.getTokenStream()) {
            System.out.print("<" + token.getType() + " " + token.getAttribute() + "> ");
        }
        System.out.println();
        for (TableCell cell : lexer.getVarTable()) {
            System.out.print("<" + cell.getVarName() + " = " + cell.getVarValue() + ">\n");
        }
        Num num1 = new Num(null, null, 0);
        Num num2 = new Num(null, null, 4);
        Minus minus = new Minus(num2, num1);
        Plus plus2 = new Plus(num1, minus);
        Div div = new Div(num2, num1);
        //FileLoader fileLoader = new FileLoader("d:\\tst.mls");
        //String input = fileLoader.loadFile();

        //Lexer lexer = new Lexer(input);
        //Parser parser = new Parser(lexer.getTokenStream(), lexer.getVarTable(), lexer.getErrorQnt());
        Interpreter interpreter = new Interpreter(plus2, null, 0);
        Num res = (Num) interpreter.interpretNode(div);
        System.out.println(res.getValue());*/
    }

}
