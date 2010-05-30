/*
 *
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 * (с) Лебедев Дмитрий, 261, 2010;
 *
 */
package lebedev;

import ast.*;
import java.util.LinkedList;

public class Parser {

    private LinkedList<Token> tokenStream;
    private String parseErrorLog = "";
    private Expression output;
    private int tokenNo = 0;
    private int errorCounter;
    private Token curToken;

    public Parser(LinkedList<Token> tokenStream, int lexErrorQnt) {
        this.tokenStream = tokenStream;
        errorCounter = lexErrorQnt;
    }

    public Expression getOutput() {
        return output;
    }

    public int getErrorQnt() {
        return errorCounter;
    }

    public String getErrorLog() {
        return parseErrorLog;
    }

    public void parseProgramm() {
        if (errorCounter == 0) {
            output = parseSequence();
            //output = parseExpression();
        } else {
            fixError("there are lexical errors");
            output = null;
        }
    }

    private Expression parseSequence() {
        LinkedList<Expression> sequence = new LinkedList<Expression>();

        do {
            sequence.add(parseExpression());
        } while (curToken.getType() == TokenType.SEMICOLON);

        return new ExSequence(sequence, curToken.getPosition());
    }

    private Expression parseExpression() {
        Expression result = parseDisjunctor();

        while (curToken.getType() == TokenType.OR) {
            Expression toFind = parseDisjunctor();
            result = new LogOr(result, toFind, curToken.getPosition());
        }

        return result;
    }

    private Expression parseDisjunctor() {
        Expression result = parseConjunctor();

        while (curToken.getType() == TokenType.AND) {
            Expression toFind = parseConjunctor();
            result = new LogAnd(result, toFind, curToken.getPosition());
        }

        return result;
    }

    private Expression parseConjunctor() {
        Expression result = parseMatching();

        while (curToken.getType() == TokenType.EQUALITY || curToken.getType() == TokenType.INEQUALITY) {
            Token sign = curToken;
            Expression toFind = parseMatching();

            if (sign.getType() == TokenType.EQUALITY) {
                result = new LogEquality(result, toFind, curToken.getPosition());
            } else if (sign.getType() == TokenType.INEQUALITY) {
                result = new LogEquality(result, toFind, curToken.getPosition());
            } else {
                fixError("code: 0");
                result = null;
            }
        }

        return result;
    }

    private Expression parseMatching() {
        Expression result = parseCompared();

        if (curToken.getType() == TokenType.GREATER || curToken.getType() == TokenType.LESS || curToken.getType() == TokenType.GE || curToken.getType() == TokenType.LE) {
            Token sign = curToken;
            Expression toFind = parseCompared();

            if (sign.getType() == TokenType.GREATER) {
                result = new LogGreater(result, toFind, curToken.getPosition());
            } else if (sign.getType() == TokenType.LESS) {
                result = new LogLess(result, toFind, curToken.getPosition());
            } else if (sign.getType() == TokenType.GE) {
                result = new LogGE(result, toFind, curToken.getPosition());
            } else if (sign.getType() == TokenType.LE) {
                result = new LogLE(result, toFind, curToken.getPosition());
            } else {
                fixError("code: 1");
                result = null;
            }
        }
        return result;
    }

    private Expression parseCompared() {
        Expression result = parseTerm();

        while (curToken.getType() == TokenType.PLUS || curToken.getType() == TokenType.MINUS) {
            Token sign = curToken;

            Expression toFind = parseTerm();
            if (sign.getType() == TokenType.PLUS) {
                result = new ArAddition(result, toFind, curToken.getPosition());
            } else {
                result = new ArSubtraction(result, toFind, curToken.getPosition());
            }
        }
        return result;
    }

    private Expression parseTerm() {
        Expression result = parseFactor();

        while (curToken.getType() == TokenType.MULTIPLICATION || curToken.getType() == TokenType.DIVISION) {
            Token sign = curToken;

            Expression toFind = parseFactor();
            if (sign.getType() == TokenType.MULTIPLICATION) {
                result = new ArMultiplication(result, toFind, curToken.getPosition());
            } else {
                result = new ArDivision(result, toFind, curToken.getPosition());
            }
        }
        return result;
    }

    private Expression parseFactor() {
        nextToken();
        if (curToken.getType() == TokenType.NOT) {
            // nextToken();
            return new LogNot(parseFactor(), curToken.getPosition());
        } else if (curToken.getType() == TokenType.MINUS) {
            nextToken();
            return new ArNegate(parsePrime(), curToken.getPosition());
        } else {
            return parsePrime();
        }
    }

    private Expression parsePrime() { // System.out.println(curToken.getType());
        Expression result;

        if (curToken.getType() == TokenType.NUMBER) {
            result = new ArOperand(curToken.getAttribute(), curToken.getPosition());
            nextToken();
        } else if (curToken.getType() == TokenType.LOG_OPERAND) {
            if (curToken.getAttribute() == 0) {
                result = new LogOperand(false, curToken.getPosition());
            } else {
                result = new LogOperand(true, curToken.getPosition());
            }
            nextToken();
        } else if (curToken.getType() == TokenType.ID) {
            result = new Variable(curToken.getAttribute(), curToken.getPosition());
            nextToken();
        } else if (curToken.getType() == TokenType.LEFT_BRACKET) {
            Expression toFind = parseExpression();

            if (curToken.getType() != TokenType.RIGHT_BRACKET) {
                fixError("strange symbol, ')' expected");
                result = null;
            } else {
                nextToken();
                result = toFind;
            }
        } else if (curToken.getType() == TokenType.LET) {
            nextToken();
            if (curToken.getType() != TokenType.ID) {
                fixError("missed identifier");
                result = null;
            } else {
                int id = curToken.getAttribute();

                nextToken();
                if (curToken.getType() != TokenType.EQUALS_SIGN) {
                    fixError("strange symbol, error code: 2 (missed equals sign)");
                    result = null;
                } else {
                    Expression letExpression = parseExpression();
                    if (curToken.getType() != TokenType.IN) {
                        fixError("missed IN expression");
                        result = null;
                    } else {
                        Expression inExpression = parseExpression();
                        Expression binding = new ExBinding(id, letExpression,
                                inExpression, curToken.getPosition());
                        result = binding;
                    }
                }
            }
        } else if (curToken.getType() == TokenType.PRINT) {
            nextToken();
            if (curToken.getType() != TokenType.LEFT_BRACKET) {
                fixError("strange symbol, error code: 5");
                result = null;
            } else {
                ExPrint exPrint = new ExPrint(parseExpression(), curToken.getPosition());
                if (curToken.getType() != TokenType.RIGHT_BRACKET) {
                    fixError("strange symbol, error code: 6");
                    result = null;
                } else {
                    nextToken();
                    result = exPrint;
                }
            }
        } else if (curToken.getType() == TokenType.IF) {
            Expression conditional = new ExConditional(parseExpression(),
                    parseExpression(), parseExpression(), curToken.getPosition());
            result = conditional;
        } else if (curToken.getType() == TokenType.BEGIN) {
            Expression seqExpr = parseSequence();
            if (curToken.getType() != TokenType.END) {
                fixError("strange symbol, error code: 7(missed end statement)");
                result = null;
            }

            nextToken();
            result = seqExpr;
        } else if (curToken.getType() == TokenType.FUNCTION) {
            nextToken();
            if (curToken.getType() != TokenType.ID) {
                fixError("invalid variable");
                result = null;
            } else {
                int id = curToken.getAttribute();
                nextToken();
                if (curToken.getType() != TokenType.ARROW) {
                    fixError("strange symbol, error code: 4");
                    result = null;
                } else {
                    ExFunction function = new ExFunction(id, parseExpression(), curToken.getPosition());
                    result = function;
                }
            }
        } else if (curToken.getType() == TokenType.EOF){
            //System.out.println(curToken.getType());
            fixError("no expression after semicolon at " + curToken.getPosition().getAbs());
            result = null;
        } else {
            System.out.println(curToken.getType());
            fixError("strange symbol, error code: 3 at " + curToken.getPosition().getAbs());
            result = null;
        }
        // nextToken();
        // System.out.println(curToken.getType());
        if (result != null) { // && (
            switch (curToken.getType()) {
                case NUMBER:
                case LOG_OPERAND:
                case ID:
                case BEGIN:
                case LET:
                case FUNCTION:
                case LEFT_BRACKET:
                    prevToken();
                    // System.out.print(curToken.getType() + "!!!");
                    result = new ExApplication(result, parseExpression(), curToken.getPosition());
                    // System.out.println(result.getClass());
                    break;
            }
        }
        return result;
    }

    private void nextToken() {
        curToken = tokenStream.get(tokenNo++);
    }

    private void prevToken() {
        curToken = tokenStream.get(--tokenNo);
    }

    private void fixError(String message) {
        errorCounter++;
        parseErrorLog += "parser error: " + message + ";\n";
        // System.out.println("\nparser error: " + message + ";");
    }
}
