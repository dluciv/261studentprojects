/*
 *
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 * (с) Лебедев Дмитрий, 261, 2010;
 */
package lebedev;

import ast.*;
import java.util.LinkedList;

public class Parser {

    private LinkedList<Token> tokenStream;
    private String parseErrorLog = "";
    private LinkedList<Expression> sequence = new LinkedList<Expression>();
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
        } else {
            fixError("there are lexical errors");
            output = null;
        }
    }

    private Expression parseSequence() {

        do {
            sequence.add(parseExpression());
            // System.out.println(curToken.getType());
        } while (curToken.getType() == TokenType.SEMICOLON);

        return new ExSequence(sequence);
    }

    private Expression parseExpression() {
        Expression result = parseDisjunctor();

        while (curToken.getType() == TokenType.OR) {
            Expression toFind = parseDisjunctor();
            result = new LogOr(result, toFind);
        }

        /*if (curToken.getType() != TokenType.EOF)
        parseExpression();*/
        //System.out.println(curToken.getType());
        /*if (curToken.getType() != TokenType.EOF) { // если не конец, то добавить применение;
        prevToken();
        return new ExApplication(result, parseExpression());
        }*/

        return result;
    }

    private Expression parseDisjunctor() {
        Expression result = parseConjunctor();

        while (curToken.getType() == TokenType.AND) {
            Expression toFind = parseConjunctor();
            result = new LogAnd(result, toFind);
        }

        return result;
    }

    private Expression parseConjunctor() {
        Expression result = parseMatching();

        while (curToken.getType() == TokenType.EQUALITY || curToken.getType() == TokenType.INEQUALITY) {
            Token sign = curToken;
            Expression toFind = parseMatching();

            if (sign.getType() == TokenType.EQUALITY) {
                result = new LogEquality(result, toFind);
            } else if (sign.getType() == TokenType.INEQUALITY) {
                result = new LogEquality(result, toFind);
            } else {
                fixError("code: 0");
                result = null;
            }
        }

        return result;
    }

    private Expression parseMatching() {
        Expression result = parseCompared();

        if (curToken.getType() == TokenType.GREATER || curToken.getType() == TokenType.LESS ||
                curToken.getType() == TokenType.GE || curToken.getType() == TokenType.LE) {
            Token sign = curToken;
            Expression toFind = parseCompared();

            if (sign.getType() == TokenType.GREATER) {
                result = new LogGreater(result, toFind);
            } else if (sign.getType() == TokenType.LESS) {
                result = new LogLess(result, toFind);
            } else if (sign.getType() == TokenType.GE) {
                result = new LogGE(result, toFind);
            } else if (sign.getType() == TokenType.LE) {
                result = new LogLE(result, toFind);
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
                result = new ArAddition(result, toFind);
            } else {
                result = new ArSubtraction(result, toFind);
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
                result = new ArMultiplication(result, toFind);
            } else {
                result = new ArDivision(result, toFind);
            }
        }
        return result;
    }

    private Expression parseFactor() {
        nextToken();
        if (curToken.getType() == TokenType.NOT) {
            nextToken();
            return new LogNot(parsePrime());
        } else if (curToken.getType() == TokenType.MINUS) {
            nextToken();
            return new ArNegate(parsePrime());
        } else {
            return parsePrime();
        }
    }

    private Expression parsePrime() { //System.out.println(curToken.getType());
        if (curToken.getType() == TokenType.NUMBER) {
            Expression result = new ArOperand(curToken.getAttribute());
            nextToken();
            return result;
        } else if (curToken.getType() == TokenType.LOG_OPERAND) {
            Expression result;

            if (curToken.getAttribute() == 0) {
                result = new LogOperand(false);
            } else {
                result = new LogOperand(true);
            }
            nextToken();

            return result;
        } else if (curToken.getType() == TokenType.ID) {
            Expression result = new Variable(curToken.getAttribute());
            nextToken();

            return result;
        } else if (curToken.getType() == TokenType.LEFT_BRACKET) {
            Expression toFind = parseExpression();

            if (curToken.getType() != TokenType.RIGHT_BRACKET) {
                fixError("strange symbol, ')' expected");
                return null;
            } else {
                nextToken();
                return toFind;
            }
        } else if (curToken.getType() == TokenType.LET) {
            nextToken();
            if (curToken.getType() != TokenType.ID) {
                fixError("missed identifier");
                return null;
            } else {
                int id = curToken.getAttribute();

                nextToken();
                if (curToken.getType() != TokenType.EQUALS_SIGN) {
                    fixError("strange symbol, error code: 2 (missed equals sign)");
                    return null;
                } else {
                    Expression letExpression = parseExpression();
                    if (curToken.getType() != TokenType.IN) {
                        fixError("missed IN expression");
                        return null;
                    } else {
                        Expression inExpression = parseExpression();
                        Expression binding = new ExBinding(id, letExpression, inExpression);
                        return binding;
                    }
                }
            }
        } else if (curToken.getType() == TokenType.PRINT) {
            nextToken();
            if (curToken.getType() != TokenType.LEFT_BRACKET) {
                fixError("strange symbol, error code: 5");
                return null;
            } else {
                ExPrint exPrint = new ExPrint(parseExpression());
                if (curToken.getType() != TokenType.RIGHT_BRACKET) {
                    fixError("strange symbol, error code: 6");
                    return null;
                } else {
                    nextToken();
                    return exPrint;
                }
            }
        } else if (curToken.getType() == TokenType.IF) {
            Expression conditional = new ExConditional(parseExpression(), parseExpression(), parseExpression());

            return conditional;
        } else if (curToken.getType() == TokenType.BEGIN) {
            Expression seqExpr = parseSequence();
            if (curToken.getType() != TokenType.END) {
                fixError("strange symbol, error code: 7(missed end statement)");
                return null;
            }

            nextToken();
            return seqExpr;
        } else if (curToken.getType() == TokenType.FUNCTION) {
            nextToken();
            int id = curToken.getAttribute();

            nextToken();
            if (curToken.getType() != TokenType.ARROW) {
                fixError("strange symbol, error code: 4");
                return null;
            } else {
                Expression function = new ExFunction(id, parseExpression());
                return function;
            }
        } else {
            System.out.println(curToken.getType());
            fixError("strange symbol, error code: 3");
            return null;
        }
    }

    private void nextToken() {
        curToken = tokenStream.get(tokenNo);
        tokenNo++;
    }

    private void prevToken() {
        tokenNo--;
        curToken = tokenStream.get(tokenNo);
    }

    private void fixError(String message) {
        errorCounter++;
        parseErrorLog += "parser error: " + message + ";\n";
        // System.out.println("\nparser error: " + message + ";");
    }
}
