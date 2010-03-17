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
    private final int NONE = 0;
    private LinkedList<Token> tokenStream;
    private LinkedList<TableCell> varTable;
    private LinkedList<Statement> output;
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

    public LinkedList<Statement> getOutput() {
        return output;
    }

    public int getErrorQnt() {
        return errorCounter;
    }

    private LinkedList<Statement> parseProgram() {
        LinkedList<Statement> result = new LinkedList<Statement>();
        nextToken();

        while (curToken.getType() != TokenType.EOF) {
            if (curToken.getType() == TokenType.PRINT) {
                result.add(parseKeyword());
            }
            else {            
                result.add(parseAssignment());
            }
            nextToken();
        }

        return result;
    }

    private Assignment parseAssignment() {    
        int varId = curToken.getAttribute();
        nextToken();

        if (curToken.getType() == TokenType.ASSIGNMENT) {
            Assignment result = new Assignment(varId, parseExpression());
            
            if (curToken.getType() == TokenType.SEMICOLON) {
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
            Keyword result = new Print(NONE, parseExpression());

            return result;
        }
        else {
            fixError("strange symbol, 'print' expected");
            return null;
        }
    }

    private BinOp parseExpression() {
        BinOp result;

        result = parseTerm();
        while (curToken.getType() == TokenType.PLUS || curToken.getType() == TokenType.MINUS) {
            Token sign = curToken;
            BinOp toFind = parseTerm();

            if (sign.getType() == TokenType.PLUS) {
                result = new Plus(result, toFind);
            }
            else {
                result = new Minus(result, toFind);
            }
        }

        return result;
    }

    private BinOp parseTerm() {
        BinOp result = parseFormula();
        
        while (curToken.getType() == TokenType.MULTIPLICATION || curToken.getType() == TokenType.DIVISION) {
            Token sign = curToken;

            BinOp toFind = parseFormula();
            if (sign.getType() == TokenType.MULTIPLICATION) {
                result = new Mult(result, toFind);
            }
            else {
                result = new Div(result, toFind);
            }
        }

        return result;
    }

    private BinOp parseFormula()
    {
        nextToken();
        if (curToken.getType() == TokenType.NUMBER) {
            BinOp toReturn = new Num(null, null, curToken.getAttribute());
            nextToken();

            return toReturn;
        }
        else if (curToken.getType() == TokenType.ID) {
            BinOp toReturn = new Variable(null, null, curToken.getAttribute());
            nextToken();

            return toReturn;
        }
        else if (curToken.getType() == TokenType.LEFT_BRACKET) {
            BinOp toFind = parseExpression();
            
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
