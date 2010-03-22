/*
 *
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package parser;

import lexer.*;
import java.util.LinkedList;

public class Parser {
    private LinkedList<Token> tokenStream;
    private LinkedList<TableCell> varTable;
    private Sequence output;
    private int tokenNo = 0;
    private int errorCounter = 0;
    private Token curToken;

    public Parser(LinkedList<Token> tokenStream, LinkedList<TableCell> varTable, int lexErrorQnt) {
        this.tokenStream = tokenStream;
        this.varTable = varTable;
        
        if (lexErrorQnt == 0) {
            output = parseProgram();
        }
        else {
            fixError("there are lexical errors");
            output = null;
        }
    }

    public Sequence getOutput() {
        return output;
    }

    public int getErrorQnt() {
        return errorCounter;
    }

    private Sequence parseProgram() {
        Sequence sequence = null;

        nextToken();
        while (curToken.getType() != TokenType.EOF) {
            if (curToken.getType() != TokenType.PRINT) {
                sequence = new Sequence(sequence, parseAssignment());
            }
            else {
                sequence = new Sequence(sequence, parseKeyword());
            }
        }

        return sequence;
    }

    private Assignment parseAssignment() {
        int varId = curToken.getAttribute();
        AbstractTree leftPart = new Variable(null, null, varId);
        nextToken();

        if (curToken.getType() == TokenType.ASSIGNMENT) {
            Assignment result = new Assignment(leftPart, parseExpression());
            
            if (curToken.getType() == TokenType.SEMICOLON) {
                nextToken();
                return result;
            }
            else {
                fixError("wrong expression");
                return null;
            }
        }
        else {
            fixError("strange symbol, '=' expected");
            return null;
        }
    }

    private Keyword parseKeyword() {
        if (curToken.getType() == TokenType.PRINT) {
            Keyword result = new Print(null, parseExpression());
            nextToken();

            return result;
        }
        else {
            fixError("strange symbol, 'print' expected");
            return null;
        }
    }

    private Expression parseExpression() {
        Expression result;

        result = parseTerm();
        while (curToken.getType() == TokenType.PLUS || curToken.getType() == TokenType.MINUS) {
            Token sign = curToken;
            Expression toFind = parseTerm();

            if (sign.getType() == TokenType.PLUS) {
                result = new Plus(result, toFind);
            }
            else {
                result = new Minus(result, toFind);
            }
        }

        return result;
    }

    private Expression parseTerm() {
        Expression result = parseFormula();
        
        while (curToken.getType() == TokenType.MULTIPLICATION || curToken.getType() == TokenType.DIVISION) {
            Token sign = curToken;

            Expression toFind = parseFormula();
            if (sign.getType() == TokenType.MULTIPLICATION) {
                result = new Mult(result, toFind);
            }
            else {
                result = new Div(result, toFind);
            }
        }

        return result;
    }

    private Expression parseFormula()
    {
        nextToken();
        if (curToken.getType() == TokenType.NUMBER) {
            Expression toReturn = new Num(null, null, curToken.getAttribute());
            nextToken();

            return toReturn;
        }
        else if (curToken.getType() == TokenType.ID) {
            Expression toReturn = new Variable(null, null, curToken.getAttribute());
            nextToken();

            return toReturn;
        }
        else if (curToken.getType() == TokenType.LEFT_BRACKET) {
            Expression toFind = parseExpression();
            
            if (curToken.getType() != TokenType.RIGHT_BRACKET) {
                fixError("strange symbol, ')' expected");
                return null;
            }
            else {
                nextToken();
                return toFind;
            }
        }
        else {
            fixError("strange symbol, '(' expected");
            return null;
        }
    } 

    private void nextToken() {
        curToken = tokenStream.get(tokenNo);
        tokenNo++;
    }

    private void fixError(String message) {
        errorCounter++;
        System.out.println("\nparser error: " + message + ";");
    }
}
