//Lebedev Dmitry 2010 (c)
package lebedev;

//import LexemKind;
import AST.*;
import java.util.LinkedList;
import AST.Tree;
import lebedev.Lexer.LexemKind;

public class Parser {

    private static Lexer lexer;

    public Parser(String programm) {
        Parser.lexer = new Lexer(programm);
    }

    public static void main(String programm) throws Exception {
        Tree seq;
        //int result = 0;
        Parser parser = new Parser(programm);
        try {
            seq = parser.seq();
            //tree = parser.expr();

            //String position = String.valueOf(lexer.getPosition());
            if (lexer.getCurrentLexem().getLexemKind() != LexemKind.eof) {
                if (lexer.getCurrentLexem().getLexemKind() == LexemKind.unknown) {
                    System.out.printf("%s %s %i %s %i %s", "Error in the input string (unknown symbol)",
                            "at", lexer.getSymbolPosition(), "line", lexer.getSymbolPosition(), "column");
                }

                if (lexer.getCurrentLexem().getLexemKind() == LexemKind.closebracket) {
                    System.out.printf("%s %s %i %s %i %s", "Error in the input string (unexpected closebracket)",
                            "at", lexer.getLinePosition(), "line", lexer.getSymbolPosition(), "column");
                }
                return;
            }

        } catch (Exception e) {
            return;
        }
        //result = seq.evaluate();
        //System.out.println(result);
        //seq.print();
        System.out.println();
    }

    public Tree seq() throws Exception {
        LinkedList<Tree> programm = new LinkedList();
        Tree left = expr();
        programm.add(left);
        while (lexer.getCurrentLexem().getLexemKind() == LexemKind.semicolon) {
            lexer.moveNext();
            Tree right = expr();
            programm.add(right);
        }
        return new ExSequence(programm);
    }

    public Tree expr() throws Exception {
        Tree left = term();
        while (lexer.getCurrentLexem().getLexemKind() == LexemKind.plus || lexer.getCurrentLexem().getLexemKind() == LexemKind.minus) {
            LexemKind sign = lexer.getCurrentLexem().getLexemKind();
            lexer.moveNext();
            Tree right = term();
            if (sign == LexemKind.plus) {
                left = new ArAddition(left, right);
            } else {
                left = new ArSubtraction(left, right);
            }
        }
        return left;
    }

    public Tree term() throws Exception {
        Tree left = factor();
        while (lexer.getCurrentLexem().getLexemKind() == LexemKind.multiply || lexer.getCurrentLexem().getLexemKind() == LexemKind.divide) {
            LexemKind sign = lexer.getCurrentLexem().getLexemKind();
            lexer.moveNext();
            Tree right = factor();
            if (sign == LexemKind.multiply) {
                left = new ArMultiplication(left, right);
            } else {
                left = new ArDivision(left, right);
            }
        }
        return left;
    }

    public Tree factor() throws Exception {
        int value;
        Tree left;
        if (lexer.getCurrentLexem().getLexemKind() == LexemKind.number) {
            value = lexer.getCurrentLexem().getValue();
            lexer.moveNext();
            return new ArOperand(value);
        } else {
            if (lexer.getCurrentLexem().getLexemKind() == LexemKind.openbracket) {
                lexer.moveNext();
                left = expr();
                if (lexer.getCurrentLexem().getLexemKind() == LexemKind.unknown) {
                    System.out.printf("%s %s %i %s %i %s", "Error in the input string (unknown symbol)",
                            "at", lexer.getLinePosition(), "line", lexer.getSymbolPosition(), "column");
                    throw new Exception();
                }

                if (lexer.getCurrentLexem().getLexemKind() != LexemKind.closebracket) {
                    System.out.printf("%s %s %i %s %i %s", "Error in the input string (missing right bracket)",
                            "at", lexer.getLinePosition(), "line", lexer.getSymbolPosition(), "column");
                    throw new Exception();
                }
                lexer.moveNext();
                return left;
            } else {
                System.out.printf("%s %s %i", "Error in the input string ", "at", lexer.getSymbolPosition());
                throw new Exception();
            }
        }
    }
}
