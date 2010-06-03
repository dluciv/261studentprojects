/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interpretertests;

import ast.*;
import gui.Controller;
import interpreter.Interpreter;
import interpreter.ValBoolean;
import interpreter.ValInt;
import lexerandparser.Lexer;
import lexerandparser.Parser;
import lexerandparser.Position;
import org.junit.Test;
import tools.Tool;
import static org.junit.Assert.*;

public class InterpretorTests {
    Position somePosition = new Position(0, 0, 0, 0);
    Controller someController = new Controller(null);

    @Test
    public void testArithmetic() {
        ArOperand arOprnd1 = new ArOperand(2, somePosition);
        ArOperand arOprnd2 = new ArOperand(4, somePosition);
        ArMultiplication arMult = new ArMultiplication(arOprnd1, arOprnd2, somePosition);
        ArNegate arNeg = new ArNegate(arMult, somePosition);

        Interpreter interpreter = new Interpreter(arNeg, false, someController);
        interpreter.run();

        ValInt res = (ValInt) interpreter.getResult();
        assertEquals(res.getValue(), -8);
    }

    @Test
    public void testLogic() {
        LogOperand lgOprnd1 = new LogOperand(true, somePosition);
        LogOperand lgOprnd2 = new LogOperand(false, somePosition);
        LogOr lgOr = new LogOr(lgOprnd1, lgOprnd2, somePosition);
        LogNot logNot = new LogNot(lgOr, somePosition);

        Interpreter interpreter = new Interpreter(logNot, false, someController);
        interpreter.run();
        
        ValBoolean res = (ValBoolean) interpreter.getResult();
        assertEquals(res.getValue(), false);
    }

    @Test
    public void testApplication() {
        String input = "let x = 3 in let f = fun y -> x + y in let x = 5 in f 4";
        Lexer lexer = new Lexer(input + '\0');
        lexer.analyzeSourceProgram();

        Parser parser = new Parser(lexer.getTokenStream());
        parser.parseProgram();

        Interpreter interpreter = new Interpreter(parser.getOutput(), false, someController);
        interpreter.run();

        ValInt res = (ValInt) interpreter.getResult();
        assertEquals(res.getValue(), 7);
    }

     @Test
    public void testExpression() {
        String input = "(((3 * 100) - (-6)) / 2 - 151) / 2";
        Lexer lexer = new Lexer(input + '\0');
        lexer.analyzeSourceProgram();

        Parser parser = new Parser(lexer.getTokenStream());
        parser.parseProgram();

        Interpreter interpreter = new Interpreter(parser.getOutput(), false, someController);
        interpreter.run();

        ValInt res = (ValInt) interpreter.getResult();
        assertEquals(res.getValue(), 1);
    }

    @Test
    public void testBinding() {
        Expression letExpression = new ArOperand(2, somePosition);
        Expression inExpression = new Variable(0, somePosition);
        ExBinding binding = new ExBinding(0, letExpression, inExpression, somePosition);

        Interpreter interpreter = new Interpreter(binding, false, someController);
        interpreter.run();

        ValInt res = (ValInt) interpreter.getResult();
        assertEquals(res.getValue(), 2);
    }

    @Test
    public void testError() {
        ArOperand arOprnd1 = new ArOperand(1, somePosition);
        ArOperand arOprnd2 = new ArOperand(0, somePosition);
        ArDivision arDiv = new ArDivision(arOprnd1, arOprnd2, somePosition);

        Interpreter interpreter = new Interpreter(arDiv, false, someController);
        interpreter.run();

        assertEquals(Tool.getErrorQnt(), 1);
    }
}
