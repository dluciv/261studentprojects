//Lebedev Dmitry 2010 (c)
package lebedev1;

import ast.Lexer.LexemKind;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Parser {

    public Parser(String programm) {
        Parser.lexer = new Lexer(programm);
    }

    public static void main() throws Exception {
        Tree seq;
        int result = 0;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }
        Parser parser = new Parser(scan);

        try {
            System.out.print("input your programm");

          
            seq = parser.seq();
            //tree = parser.expr();
            String position = String.valueOf(lexer.getPosition());
            if (lexer.getCurrentLexem().getLeft() != Lexer.LexemKind.eol) {
                if (lexer.getCurrentLexem().getLeft() == Lexer.LexemKind.unknown) {
                    System.out.printf("%s %s %s", "Error in the input string (unknown symbol)", "at", position);
                }

                if (lexer.getCurrentLexem().getLeft() == Lexer.LexemKind.closebracket) {
                    System.out.printf("%s %s %s", "Error in the input string (unexpected closebracket)", "at", position);
                }
                return;
            }

        } catch (FileNotFoundException e) {
            return;
        }
        result = seq.evaluate();
        System.out.println(result);
        seq.print();
        System.out.println();
    }

    public Tree seq() throws Exception {
        LinkedList<Tree> programm = new LinkedList();
        Tree left = expr();
        programm.add(left);
        while (lexer.getCurrentLexem().getLeft() == LexemKind.semicolon) {
            lexer.moveNext();
            Tree right = expr();
            programm.add(right);
        }
        return new Sequence(programm);
    }

    public Tree expr() throws Exception {
        Tree left = term();
        while (lexer.getCurrentLexem().getLeft() == LexemKind.plus || lexer.getCurrentLexem().getLeft() == LexemKind.minus) {
            LexemKind sign = lexer.getCurrentLexem().getLeft();
            lexer.moveNext();
            Tree right = term();
            if (sign == LexemKind.plus) {
                left = new Addition(left, right);
            } else {
                left = new Subtraction(left, right);
            }
        }
        return left;
    }

    public Tree term() throws Exception {
        Tree left = factor();
        while (lexer.getCurrentLexem().getLeft() == LexemKind.multiply || lexer.getCurrentLexem().getLeft() == LexemKind.divide) {
            LexemKind sign = lexer.getCurrentLexem().getLeft();
            lexer.moveNext();
            Tree right = factor();
            if (sign == LexemKind.multiply) {
                left = new Multiplication(left, right);
            } else {
                left = new Division(left, right);
            }
        }
        return left;
    }

    public Tree factor() throws Exception {
        int value;
        Tree left;
        if (lexer.getCurrentLexem().getLeft() == LexemKind.number) {
            value = Integer.parseInt(lexer.getCurrentLexem().getRight());
            lexer.moveNext();
            return new Number(value);
        } else {
            if (lexer.getCurrentLexem().getLeft() == LexemKind.openbracket) {
                lexer.moveNext();
                left = expr();
                if (lexer.getCurrentLexem().getLeft() == LexemKind.unknown) {
                    System.out.printf("%s %s %i", "Error in the input string (unknown symbol)", "at", lexer.getPosition());
                    throw new Exception();
                }

                if (lexer.getCurrentLexem().getLeft() != LexemKind.closebracket) {
                    System.out.printf("%s %s %i", "Error in the input string (missing right bracket)", "at", lexer.getPosition());
                    throw new Exception();
                }
                lexer.moveNext();
                return left;
            } else {
                System.out.printf("%s %s %i", "Error in the input string ", "at", lexer.getPosition());
                throw new Exception();
            }
        }
    }
}
