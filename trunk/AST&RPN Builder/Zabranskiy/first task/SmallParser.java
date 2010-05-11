/*
 * Small Parser
 * Dmitriy Zabranskiy g261 (c)2010
 */
package analizator;

import java.util.Scanner;

public class SmallParser {

    private static Lexer lexer;

    public static void main(String[] args) throws Exception {

        lexer = new Lexer();
        Node left = null;
        Scanner input = new Scanner(System.in);

        lexer.lexems = lexer.convertStrToLexems(input.nextLine());
        
        if ((lexer.lexems.isEmpty()) || (lexer.lexems.getFirst().getType() == LexType.EOL)) {
            throw new Exception("Warning: List is empty.");
        }

        left = exp();
    }

    private static Node exp() throws Exception {
        Node left = null;

        left = term();
        while (lexer.getCurrent().getType() == LexType.ADD || lexer.getCurrent().getType() == LexType.SUB) {
            LexType sign = lexer.getCurrent().getType();
            lexer.Next();
            Node right = term();
            if (sign == LexType.ADD) {
                left = new BinaryOperation(left, right, BinaryOperationType.ADD);
                ((BinaryOperation) left).print();
            } else {
                left = new BinaryOperation(left, right, BinaryOperationType.SUB);
                ((BinaryOperation) left).print();
            }
        }

        return left;
    }

    private static Node term() throws Exception {
        Node left = null;

        left = parseDegree();
        while (lexer.getCurrent().getType() == LexType.MULT || lexer.getCurrent().getType() == LexType.DIV) {
            LexType sign = lexer.getCurrent().getType();
            lexer.Next();
            Node right = parseDegree();
            if (sign == LexType.MULT) {
                left = new BinaryOperation(left, right, BinaryOperationType.MULT);
                ((BinaryOperation) left).print();
            } else {
                left = new BinaryOperation(left, right, BinaryOperationType.DIV);
                ((BinaryOperation) left).print();
            }
        }

        return left;
    }

    private static Node parseDegree() throws Exception {
        Node left = factor();

        if (lexer.getCurrent().getType() == LexType.DEGREE) {
            lexer.Next();
            Node right = factor();
            left = new BinaryOperation(left, right, BinaryOperationType.DEGREE);
            ((BinaryOperation) left).print();
        }

        return left;
    }

    private static Node factor() throws Exception {
        Node left = null;

        if (lexer.getCurrent().getType() == LexType.NUMBER) {
            left = new Number(((Number) lexer.getCurrent()).getValue(), lexer.getCurrent().getPosition());
            ((Number) left).print();
            lexer.Next();
            return left;
        } else if (lexer.getCurrent().getType() == LexType.BREAKITOPEN) {
            lexer.Next();
            left = exp();
            if (lexer.getCurrent().getType() != LexType.BREAKITCLOSE) {
                throw new Exception("Error in the input string (missing right bracket)");
            } else {
                lexer.Next();
                return left;
            }
        } else if (lexer.getCurrent().getType() == LexType.BREAKITCLOSE) {
            throw new Exception("Error in the input string (unexpected Right Bracket) at " + lexer.getCurrent().getPosition().getRow() + " " + lexer.getCurrent().getPosition().getColumn() + "\n");
        }
        return left;
    }
}
