//Lebedev Dmitry 2010 (c)
package ast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Parser {

    public static Lexer lexer;
    public static Scanner scan;
    public static String path = "C:/Users/leeevd/Documents/NetBeansProjects/AST/src/ast/input.txt";
    public static File file = new File(path);

    public Parser(Scanner scanner) {
        Parser.lexer = new Lexer(scanner);
    }
//    public Parser(Scanner args) {
//        Parser.lexer = new Lexer(args);
//    }

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
            //System.out.print("input your programm");

            //Scanner input = new Scanner(System.in);
            //lexer = new Lexer(input.nextLine());

            seq = parser.seq();
            //tree = parser.expr();

            //String position = String.valueOf(lexer.getPosition());
            if (lexer.getCurrentLexem().getLexemKind() != LexemKind.eof) {
                if (lexer.getCurrentLexem().getLexemKind() == LexemKind.unknown) {
                    System.out.printf("%s %s %i %s %i %s", "Error in the input string (unknown symbol)",
                            "at", lexer.getLinePosition(), "line", lexer.getSymbolPosition(), "column");
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
        result = seq.evaluate();
        System.out.println(result);
        seq.print();
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
        return new Sequence(programm);
    }

    public Tree expr() throws Exception {
        Tree left = term();
        while (lexer.getCurrentLexem().getLexemKind() == LexemKind.plus || lexer.getCurrentLexem().getLexemKind() == LexemKind.minus) {
            LexemKind sign = lexer.getCurrentLexem().getLexemKind();
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
        while (lexer.getCurrentLexem().getLexemKind() == LexemKind.multiply || lexer.getCurrentLexem().getLexemKind() == LexemKind.divide) {
            LexemKind sign = lexer.getCurrentLexem().getLexemKind();
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
        if (lexer.getCurrentLexem().getLexemKind() == LexemKind.number) {
            value = lexer.getCurrentLexem().getValue();
            lexer.moveNext();
            return new Number(value);
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
                System.out.printf("%s %s %i %s %i %s", "Error in the input string ",
                        "at", lexer.getLinePosition(), "line", lexer.getSymbolPosition(), "column");
                throw new Exception();
            }
        }
    }
}
