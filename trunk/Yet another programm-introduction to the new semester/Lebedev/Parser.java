//Lebedev Dmitry 2010 (c)
package ast;

import ast.Lexer.lexem;

public class Parser {

    public static Lexer lexer;

    public Parser(String args) {

        Parser.lexer = new Lexer(args);
    }

    public static void main(String args) {
        Parser parser = new Parser(args);
        Tree tree;
        int result = 0;
        try {
            tree = parser.expr();
            String position = String.valueOf(lexer.getPosition());
            if (lexer.getCurrentLexem().getLeft() != Lexer.lexem.eol) {
                if (lexer.getCurrentLexem().getLeft() == Lexer.lexem.unknown) {
                    System.out.printf("%s %s %s", "Error in the input string (unknown symbol)", "at", position);
                }

                if (lexer.getCurrentLexem().getLeft() == Lexer.lexem.closebracket) {
                    System.out.printf("%s %s %s", "Error in the input string (unexpected closebracket)", "at", position);
                }
                return;
            }

        } catch (Exception e) {
            return;
        }
        result = tree.evaluate();
        System.out.println(result);
        tree.print();
        System.out.println();
    }

    public Tree expr() throws Exception {
        Tree left = term();
        while (lexer.getCurrentLexem().getLeft() == lexem.plus || lexer.getCurrentLexem().getLeft() == lexem.minus) {
            lexem sign = lexer.getCurrentLexem().getLeft();
            lexer.moveNext();
            Tree right = term();
            if (sign == lexem.plus) {
                left = new Addition(left, right);
            } else {
                left = new Subtraction(left, right);
            }
        }
        return left;
    }

    public Tree term() throws Exception {
        Tree left = factor();
        while (lexer.getCurrentLexem().getLeft() == lexem.multiply || lexer.getCurrentLexem().getLeft() == lexem.divide) {
            lexem sign = lexer.getCurrentLexem().getLeft();
            lexer.moveNext();
            Tree right = factor();
            if (sign == lexem.multiply) {
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
        if (lexer.getCurrentLexem().getLeft() == lexem.number) {
            value = Integer.parseInt(lexer.getCurrentLexem().getRight());
            lexer.moveNext();
            return new Number(value);
        } else {
            if (lexer.getCurrentLexem().getLeft() == lexem.openbracket) {
                lexer.moveNext();
                left = expr();
                if (lexer.getCurrentLexem().getLeft() == lexem.unknown) {
                    System.out.printf("%s %s %i", "Error in the input string (unknown symbol)", "at", lexer.getPosition());
                    throw new Exception();
                }

                if (lexer.getCurrentLexem().getLeft() != lexem.closebracket) {
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
