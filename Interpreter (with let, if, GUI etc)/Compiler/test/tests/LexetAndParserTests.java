//Lebedev Dmitry 2010 (c)
package tests;

import java.util.LinkedList;
import lexerandparser.Lexer;
import lexerandparser.Parser;
import lexerandparser.TableCell;
import org.junit.Assert;
import org.junit.Test;
import tools.Tool;

public class LexetAndParserTests {

    private String program;

    @Test
    public void getVarTable() throws Exception {
        program = "x = cc 2\0";
        Tool.clearErrorQnt();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();

        LinkedList<TableCell> varTable = lexer.getVarTable();
        int firstId = varTable.getFirst().getId();
        int lastId = varTable.getLast().getId();
        Assert.assertTrue(firstId == 0 && lastId == 1);
    }

    @Test
    public void EmptyProgramError() throws Exception {
        program = "\0";
        String errorMessage = "try to write some program";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Assert.assertTrue(Tool.getErrorQnt() > 0 && Tool.getErrorLog().getFirst().getErrorMessage() == errorMessage);
    }

    @Test
    public void HaveLexicalErorrs() throws Exception {
        program = "2+Ж2;\ntrue\0";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Assert.assertTrue(Tool.getErrorQnt() > 0);
    }

    @Test
    public void NoLexicalErrors() throws Exception {
        program = "2+2;\ntrue\0";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Assert.assertTrue(Tool.getErrorQnt() == 0);

    }

    @Test
    public void NoParseErrors() throws Exception {
        program = "(let x = 2 in print(x))\0";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Parser parser = new Parser(lexer.getTokenStream(), Tool.getErrorQnt());
        parser.parseProgramm();
        Assert.assertTrue(Tool.getErrorQnt() == 0);
    }

    @Test
    public void MissedRightBracketError() throws Exception {
        program = "(let x = 2 in print(x)\0";
        String errorMessage = "missed right bracket";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Parser parser = new Parser(lexer.getTokenStream(), Tool.getErrorQnt());
        parser.parseProgramm();
        Assert.assertTrue(Tool.getErrorQnt() > 0 && Tool.getErrorLog().getFirst().getErrorMessage() == errorMessage);
    }

    @Test
    public void MissedIdentifierError() throws Exception {
        program = "let  = 2 in print(x)\0";
        String errorMessage = "missed variable";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Parser parser = new Parser(lexer.getTokenStream(), Tool.getErrorQnt());
        parser.parseProgramm();
        Assert.assertTrue(Tool.getErrorQnt() > 0 && Tool.getErrorLog().getFirst().getErrorMessage() == errorMessage);
    }

    @Test
    public void MissedEqualsSignError() throws Exception {
        program = "let x  2 in print(x)\0";
        String errorMessage = "missed equals sign";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Parser parser = new Parser(lexer.getTokenStream(), Tool.getErrorQnt());
        parser.parseProgramm();
        Assert.assertTrue(Tool.getErrorQnt() > 0 && Tool.getErrorLog().getFirst().getErrorMessage() == errorMessage);
    }

    @Test
    public void MissedLetExpressionError() throws Exception {
        program = "let x = in print(x)\0";
        String errorMessage = "missed let expression";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Parser parser = new Parser(lexer.getTokenStream(), Tool.getErrorQnt());
        parser.parseProgramm();
        Assert.assertTrue(Tool.getErrorQnt() > 0 && Tool.getErrorLog().getFirst().getErrorMessage() == errorMessage);
    }

    @Test
    public void MissedKeywordInError() throws Exception {
        program = "let x = 2 print(x)\0";
        String errorMessage = "missed keyword IN";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Parser parser = new Parser(lexer.getTokenStream(), Tool.getErrorQnt());
        parser.parseProgramm();
        Assert.assertTrue(Tool.getErrorQnt() > 0 && Tool.getErrorLog().getFirst().getErrorMessage() == errorMessage);
    }

    @Test
    public void MissedInExpressionError() throws Exception {
        program = "let x = 2 in \0";
        String errorMessage = "no expression";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Parser parser = new Parser(lexer.getTokenStream(), Tool.getErrorQnt());
        parser.parseProgramm();
        Assert.assertTrue(Tool.getErrorQnt() > 0 && Tool.getErrorLog().getFirst().getErrorMessage() == errorMessage);
    }

    @Test
    public void MissedLeftBracetInPrintError() throws Exception {
        program = "print 2) \0";
        String errorMessage = "missed left bracket";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Parser parser = new Parser(lexer.getTokenStream(), Tool.getErrorQnt());
        parser.parseProgramm();
        Assert.assertTrue(Tool.getErrorQnt() > 0 && Tool.getErrorLog().getFirst().getErrorMessage() == errorMessage);
    }

    @Test
    public void MissedRightBracetInPrintError() throws Exception {
        program = "print(2 \0";
        String errorMessage = "missed right bracket";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Parser parser = new Parser(lexer.getTokenStream(), Tool.getErrorQnt());
        parser.parseProgramm();
        Assert.assertTrue(Tool.getErrorQnt() > 0 && Tool.getErrorLog().getFirst().getErrorMessage() == errorMessage);
    }

    @Test
    public void MissedFirstConditionError() throws Exception {
        program = "if then 2 else 3\0";
        String errorMessage = "missed condition";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Parser parser = new Parser(lexer.getTokenStream(), Tool.getErrorQnt());
        parser.parseProgramm();
        Assert.assertTrue(Tool.getErrorQnt() > 0 && Tool.getErrorLog().getFirst().getErrorMessage() == errorMessage);
    }

    @Test
    public void MissedKeywordThenError() throws Exception {
        program = "if true 2 else 3\0";
        String errorMessage = "missed keyword then";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Parser parser = new Parser(lexer.getTokenStream(), Tool.getErrorQnt());
        parser.parseProgramm();
        Assert.assertTrue(Tool.getErrorQnt() > 0 && Tool.getErrorLog().getFirst().getErrorMessage() == errorMessage);
    }

    @Test
    public void MissedThenExpressionError() throws Exception {
        program = "if true then else 3\0";
        String errorMessage = "missed then expression";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Parser parser = new Parser(lexer.getTokenStream(), Tool.getErrorQnt());
        parser.parseProgramm();
        Assert.assertTrue(Tool.getErrorQnt() > 0 && Tool.getErrorLog().getFirst().getErrorMessage() == errorMessage);
    }

    @Test
    public void MissedKeywordElseError() throws Exception {
        program = "if true then 2 3\0";
        String errorMessage = "missed keyword else";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Parser parser = new Parser(lexer.getTokenStream(), Tool.getErrorQnt());
        parser.parseProgramm();
        Assert.assertTrue(Tool.getErrorQnt() > 0 && Tool.getErrorLog().getFirst().getErrorMessage() == errorMessage);
    }

    @Test
    public void MissedElseExpressionError() throws Exception {
        program = "if true then 2 else\0";
        String errorMessage2 = "missed else expression";
        String errorMessage1 = "no expression";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Parser parser = new Parser(lexer.getTokenStream(), Tool.getErrorQnt());
        parser.parseProgramm();
        Assert.assertTrue(Tool.getErrorQnt() > 0 && Tool.getErrorLog().get(0).getErrorMessage() == errorMessage1 && Tool.getErrorLog().get(1).getErrorMessage() == errorMessage2);
    }

    @Test
    public void MissedVariableError() throws Exception {
        program = "(fun -> print(x)) 2\0";
        String errorMessage = "missed variable";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Parser parser = new Parser(lexer.getTokenStream(), Tool.getErrorQnt());
        parser.parseProgramm();
        Assert.assertTrue(Tool.getErrorQnt() > 0 && Tool.getErrorLog().getFirst().getErrorMessage() == errorMessage);
    }

    @Test
    public void MissedArrowError() throws Exception {
        program = "(fun x print(x)) 2\0";
        String errorMessage = "missed arrow";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Parser parser = new Parser(lexer.getTokenStream(), Tool.getErrorQnt());
        parser.parseProgramm();
        Assert.assertTrue(Tool.getErrorQnt() > 0 && Tool.getErrorLog().getFirst().getErrorMessage() == errorMessage);
    }

    @Test
    public void MissedFunctionExpressionError() throws Exception {
        program = "(fun x -> ) 2\0";
        String errorMessage = "missed function expression";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Parser parser = new Parser(lexer.getTokenStream(), Tool.getErrorQnt());
        parser.parseProgramm();
        Assert.assertTrue(Tool.getErrorQnt() > 0 && Tool.getErrorLog().getFirst().getErrorMessage() == errorMessage);
    }

    @Test
    public void NoExpressionError() throws Exception {
        program = "true;\0";
        String errorMessage = "no expression";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Parser parser = new Parser(lexer.getTokenStream(), Tool.getErrorQnt());
        parser.parseProgramm();
        Assert.assertTrue(Tool.getErrorQnt() > 0 && Tool.getErrorLog().getFirst().getErrorMessage() == errorMessage);
    }

    @Test
    public void EmptyStatementError() throws Exception {
        program = "true;\n;\0";
        String errorMessage = "empty statement";
        Tool.clearErrorQnt();
        Tool.clearErrorLog();
        Lexer lexer = new Lexer(program);
        lexer.analyzeSourceProgram();
        Parser parser = new Parser(lexer.getTokenStream(), Tool.getErrorQnt());
        parser.parseProgramm();
        Assert.assertTrue(Tool.getErrorQnt() > 0 && Tool.getErrorLog().getFirst().getErrorMessage() == errorMessage);
    }
}
