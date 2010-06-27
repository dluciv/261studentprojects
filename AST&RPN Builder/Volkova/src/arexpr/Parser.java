/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexpr;

import arexpr.Lexer.lexems;
import arexpr.expr.NumberTree;
import arexpr.expr.OperationTree;
import arexpr.expr.Tree;
import java.util.Scanner;

/**
 *
 * @author kate
 */
public class Parser {

    private Lexer lexer;
    private static final String ERROR_UNKNOWN_SYMBOL = "Error in the input string (unknown symbol)";
    private static final String ERROR_LEFT_BRACKET = "Error in the input string (unexepcted Right Bracket)";
    private static final String ERROR_RIGHT_BRACKET = "Error in the input string (unexepcted Left Bracket)";

    public Parser(String toParse) {
        lexer = new Lexer(toParse);
    }

    public String convert() {
        Tree left = null;
        try {
            left = expressinon();
            if (lexer.getCurrent().getLeft() != lexems.EOL) {
                if (lexer.getCurrent().getLeft() == lexems.Unknown) {
                    return ERROR_UNKNOWN_SYMBOL;
                }
                if (lexer.getCurrent().getLeft() == lexems.RightBracket) {
                    return ERROR_RIGHT_BRACKET;
                }
                if (lexer.getCurrent().getLeft() == lexems.LeftBracket) {
                    return ERROR_LEFT_BRACKET;
                }

            }

            return left.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.print("Введите выражение: ");

        Scanner input = new Scanner(System.in);

        Parser parser = new Parser(input.nextLine());
        String result = parser.convert();
        System.out.println(result);

    }

    private Tree expressinon() throws Exception {
        Tree left = null;

        left = term();
        while (lexer.getCurrent().getLeft() == lexems.Plus || lexer.getCurrent().getLeft() == lexems.Minus) {
            lexems sign = lexer.getCurrent().getLeft();
            lexer.moveNext();
            Tree right = term();
            if (sign == lexems.Plus) {
                left = new OperationTree(left, right, OperationTree.Operations.PLUS);
            } else {
                left = new OperationTree(left, right, OperationTree.Operations.MINUS);
            }
        }

        return left;
    }

    private Tree term() throws Exception {
        Tree left = null;

        left = factor();
        while (lexer.getCurrent().getLeft() == lexems.Multiply || lexer.getCurrent().getLeft() == lexems.Divide) {
            lexems sign = lexer.getCurrent().getLeft();
            lexer.moveNext();
            Tree right = factor();
            if (sign == lexems.Multiply) {
                left = new OperationTree(left, right, OperationTree.Operations.MULTIPLY);
            } else {
                left = new OperationTree(left, right, OperationTree.Operations.DIVIDE);
            }
        }

        return left;
    }

    private Tree factor() throws Exception {
        Tree left = null;

        if (lexer.getCurrent().getLeft() == lexems.Number) {
            left = new NumberTree(Integer.parseInt(lexer.getCurrent().getRight()));
            lexer.moveNext();
            return left;
        } else if (lexer.getCurrent().getLeft() == lexems.LeftBracket) {
            lexer.moveNext();
            left = expressinon();
            if (lexer.getCurrent().getLeft() != lexems.RightBracket) {
                System.out.println("Error in the input string (missing right bracket)");
                new Exception();
            }
            lexer.moveNext();
        } else {
            new Exception();
        }

        return left;
    }
}

