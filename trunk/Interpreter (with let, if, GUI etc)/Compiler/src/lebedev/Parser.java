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
import tools.*;

public class Parser {

    private LinkedList<Token> tokenStream;
    private String parseErrorLog = "";
    private Expression output;
    private int tokenNo = 0;
    private int errorCounter;
    private Token curToken = new Token();

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
        curToken.setPosition(new Position(0, 0, 0, 0));
        if (errorCounter == 0) {
            output = parseSequence();
            //output = parseExpression();
        } else {
            fixError1("there are lexical errors");
            output = null;
        }
    }

    private Expression parseSequence() {
        LinkedList<Expression> sequence = new LinkedList<Expression>();
        Position curPosition = curToken.getPosition();
        //int beginPos = curToken.getPosition().getBegAbs();
        do {
            sequence.add(parseExpression());
        } while (curToken.getType() == TokenType.SEMICOLON);
        curPosition.setEndAbs(curToken.getPosition().getEndAbs());
        System.out.println("ExSequence " + curPosition.getBegAbs() + "-" + curPosition.getEndAbs());
        return new ExSequence(sequence, curPosition);
    }

    private Expression parseExpression() {
        Position curPosition = curToken.getPosition();
        Expression result = parseDisjunctor();

        while (curToken.getType() == TokenType.OR) {
            Expression toFind = parseDisjunctor();
            curPosition.setEndAbs(curToken.getPosition().getEndAbs());
            result = new LogOr(result, toFind, curPosition);
        }

        System.out.println(" Expression " + curPosition.getBegAbs() + "-" + curPosition.getEndAbs());
        return result;
    }

    private Expression parseDisjunctor() {
        Position curPosition = curToken.getPosition();
        Expression result = parseConjunctor();

        while (curToken.getType() == TokenType.AND) {
            Expression toFind = parseConjunctor();
            curPosition.setEndAbs(curToken.getPosition().getEndAbs());
            result = new LogAnd(result, toFind, curPosition);
        }

        System.out.println("  Disjunctor " + curPosition.getBegAbs() + "-" + curPosition.getEndAbs());
        return result;
    }

    private Expression parseConjunctor() {
        Position curPosition = curToken.getPosition();
        Expression result = parseMatching();

        while (curToken.getType() == TokenType.EQUALITY || curToken.getType() == TokenType.INEQUALITY) {
            Token sign = curToken;
            Expression toFind = parseMatching();
            curPosition.setEndAbs(curToken.getPosition().getEndAbs());

            if (sign.getType() == TokenType.EQUALITY) {
                result = new LogEquality(result, toFind, curPosition);
            } else if (sign.getType() == TokenType.INEQUALITY) {
                result = new LogEquality(result, toFind, curPosition);
            } else {
                fixError1("code: 0");
                result = null;
            }
        }

        System.out.println("   Conjunctor " + curPosition.getBegAbs() + "-" + curPosition.getEndAbs());
        return result;
    }

    private Expression parseMatching() {
        Position curPosition = curToken.getPosition();
        Expression result = parseCompared();

        if (curToken.getType() == TokenType.GREATER || curToken.getType() == TokenType.LESS || curToken.getType() == TokenType.GE || curToken.getType() == TokenType.LE) {
            Token sign = curToken;
            Expression toFind = parseCompared();
            curPosition.setEndAbs(curToken.getPosition().getEndAbs());

            if (sign.getType() == TokenType.GREATER) {
                result = new LogGreater(result, toFind, curPosition);
            } else if (sign.getType() == TokenType.LESS) {
                result = new LogLess(result, toFind, curPosition);
            } else if (sign.getType() == TokenType.GE) {
                result = new LogGE(result, toFind, curPosition);
            } else if (sign.getType() == TokenType.LE) {
                result = new LogLE(result, toFind, curPosition);
            } else {
                fixError("code: 1", curPosition);
                result = null;
            }
        }
        System.out.println("    Matching " + curPosition.getBegAbs() + "-" + curPosition.getEndAbs());
        return result;
    }

    private Expression parseCompared() {
        Position curPosition = curToken.getPosition();
        Expression result = parseTerm();

        while (curToken.getType() == TokenType.PLUS || curToken.getType() == TokenType.MINUS) {
            Token sign = curToken;
            Expression toFind = parseTerm();
            curPosition.setEndAbs(curToken.getPosition().getEndAbs());

            if (sign.getType() == TokenType.PLUS) {
                result = new ArAddition(result, toFind, curPosition);
            } else {
                result = new ArSubtraction(result, toFind, curPosition);
            }
        }
        System.out.println("     Comparing " + curPosition.getBegAbs() + "-" + curPosition.getEndAbs());
        return result;
    }

    private Expression parseTerm() {
        Position curPosition = curToken.getPosition();
        Expression result = parseFactor();

        while (curToken.getType() == TokenType.MULTIPLICATION || curToken.getType() == TokenType.DIVISION) {
            Token sign = curToken;
            Expression toFind = parseFactor();
            curPosition.setEndAbs(curToken.getPosition().getEndAbs());

            if (sign.getType() == TokenType.MULTIPLICATION) {
                result = new ArMultiplication(result, toFind, curPosition);
            } else {
                result = new ArDivision(result, toFind, curPosition);
            }
        }
        System.out.println("      Term " + curPosition.getBegAbs() + "-" + curPosition.getEndAbs());
        return result;
    }

    private Expression parseFactor() {
        Position curPosition = curToken.getPosition();
        nextToken();
        Expression result;
        if (curToken.getType() == TokenType.NOT) {
            // nextToken();
            result = parseFactor();
            curPosition.setEndAbs(curToken.getPosition().getEndAbs());
            System.out.println("       LogNot " + curPosition.getBegAbs() + "-" + curPosition.getEndAbs());
            return new LogNot(result, curPosition);
        } else if (curToken.getType() == TokenType.MINUS) {
            nextToken();
            result = parsePrime();
            curPosition.setEndAbs(curToken.getPosition().getEndAbs());
            System.out.println("       Negate " + curPosition.getBegAbs() + "-" + curPosition.getEndAbs());
            return new ArNegate(result, curPosition);
        } else {
            result = parsePrime();
            curPosition.setEndAbs(curToken.getPosition().getEndAbs());
            System.out.println("       Factor " + curPosition.getBegAbs() + "-" + curPosition.getEndAbs());
            return result;
        }
    }

    private Expression parsePrime() { // System.out.println(curToken.getType());
        Expression result;
        Position curPosition = curToken.getPosition();

        if (curToken.getType() == TokenType.NUMBER) {
            System.out.println("        Number " + curPosition.getBegAbs() + "-" + curPosition.getEndAbs());
            result = new ArOperand(curToken.getAttribute(), curPosition);
            nextToken();
        } else if (curToken.getType() == TokenType.LOG_OPERAND) {
            System.out.println("        LogOperand " + curPosition.getBegAbs() + "-" + curPosition.getEndAbs());
            if (curToken.getAttribute() == 0) {
                result = new LogOperand(false, curPosition);
            } else {
                result = new LogOperand(true, curPosition);
            }
            nextToken();
        } else if (curToken.getType() == TokenType.ID) {
            System.out.println("        ID " + curPosition.getBegAbs() + "-" + curPosition.getEndAbs());
            result = new Variable(curToken.getAttribute(), curPosition);
            nextToken();
        } else if (curToken.getType() == TokenType.LEFT_BRACKET) {
            Expression toFind = parseExpression();
            curPosition.setEndAbs(curToken.getPosition().getEndAbs());

            if (curToken.getType() != TokenType.RIGHT_BRACKET) {
                fixError("strange symbol, ')' expected", curPosition);
                result = null;
            } else {
                System.out.println("        (Expression) " + curPosition.getBegAbs() + "-" + curPosition.getEndAbs());
                nextToken();
                result = toFind;
            }
        } else if (curToken.getType() == TokenType.LET) {
            nextToken();
            if (curToken.getType() != TokenType.ID) {
                fixError("missed identifier", curPosition);
                result = null;
            } else {
                int id = curToken.getAttribute();

                nextToken();
                curPosition.setEndAbs(curToken.getPosition().getEndAbs());
                if (curToken.getType() != TokenType.EQUALS_SIGN) {
                    fixError("strange symbol, error code: 2 (missed equals sign)", curPosition);
                    result = null;
                } else {
                    Expression letExpression = parseExpression();
                    curPosition.setEndAbs(curToken.getPosition().getEndAbs());
                    if (curToken.getType() != TokenType.IN) {
                        fixError("missed IN expression", curPosition);
                        result = null;
                    } else {
                        Expression inExpression = parseExpression();
                        Expression binding = new ExBinding(id, letExpression,
                                inExpression, curPosition);
                        System.out.println("        Binding " + curPosition.getBegAbs() + "-" + curPosition.getEndAbs());
                        result = binding;
                    }
                }
            }
        } else if (curToken.getType() == TokenType.PRINT) {
            nextToken();
            curPosition.setEndAbs(curToken.getPosition().getEndAbs());
            if (curToken.getType() != TokenType.LEFT_BRACKET) {
                fixError("strange symbol, error code: 5", curPosition);
                result = null;
            } else {
                Expression printExpr = parseExpression();
                //ExPrint exPrint = new ExPrint(parseExpression(), curToken.getPosition());
                curPosition.setEndAbs(curToken.getPosition().getEndAbs());
                if (curToken.getType() != TokenType.RIGHT_BRACKET) {
                    fixError("strange symbol, error code: 6", curPosition);
                    result = null;
                } else {
                    nextToken();
                    System.out.println("        Print " + curPosition.getBegAbs() + "-" + curPosition.getEndAbs());
                    result = new ExPrint(printExpr, curPosition);
                }
            }
        } else if (curToken.getType() == TokenType.IF) {
            Expression ifExpr = parseExpression();
            curPosition.setEndAbs(curToken.getPosition().getEndAbs());
            if (curToken.getType() != TokenType.THEN) {
                fixError("missed THEN expression", curPosition);
                result = null;
            } else {
                Expression thenExpr = parseExpression();
                curPosition.setEndAbs(curToken.getPosition().getEndAbs());
                if (curToken.getType() != TokenType.ELSE) {
                    fixError("missed ELSE expression", curPosition);
                    result = null;
                } else {
                    Expression elseExpr = parseExpression();
                    curPosition.setEndAbs(curToken.getPosition().getEndAbs());
                    System.out.println("        IF " + curPosition.getBegAbs() + "-" + curPosition.getEndAbs());
                    result = new ExConditional(ifExpr, thenExpr, elseExpr, curToken.getPosition());
                }
            }
        } else if (curToken.getType() == TokenType.BEGIN) {
            Expression seqExpr = parseSequence();
            curPosition.setEndAbs(curToken.getPosition().getEndAbs());
            if (curToken.getType() != TokenType.END) {
                fixError("strange symbol, error code: 7(missed end statement)", curPosition);
                result = null;
            }

            nextToken();
            System.out.println("        Begin Seq End " + curPosition.getBegAbs() + "-" + curPosition.getEndAbs());
            result = seqExpr;
        } else if (curToken.getType() == TokenType.FUNCTION) {
            nextToken();
            curPosition.setEndAbs(curToken.getPosition().getEndAbs());
            if (curToken.getType() != TokenType.ID) {
                fixError("invalid variable", curPosition);
                result = null;
            } else {
                int id = curToken.getAttribute();
                nextToken();
                curPosition.setEndAbs(curToken.getPosition().getEndAbs());
                if (curToken.getType() != TokenType.ARROW) {
                    fixError("strange symbol, error code: 4", curPosition);
                    result = null;
                } else {
                    Expression funExpr = parseExpression();
                    curPosition.setEndAbs(curToken.getPosition().getEndAbs());
                    ExFunction function = new ExFunction(id, funExpr, curPosition);

                    System.out.println("        Function " + curPosition.getBegAbs() + "-" + curPosition.getEndAbs());
                    result = function;
                }
            }
        } else if (curToken.getType() == TokenType.EOF) {
            //System.out.println(curToken.getType());
            fixError("no expression after semicolon ", curToken.getPosition());
            result = null;
        } else {
            System.out.println(curToken.getType());
            fixError("strange symbol, error code: 3 ", curToken.getPosition());
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
                    Expression parseExpr = parseExpression();
                    curPosition.setEndAbs(curToken.getPosition().getEndAbs());
                    System.out.println("        Application " + curPosition.getBegAbs() + "-" + curPosition.getEndAbs());
                    result = new ExApplication(result, parseExpr, curToken.getPosition());
                    // System.out.println(result.getClass());
                    break;
            }
        }
        //System.out.println("        Prime" + curToken.getPosition().getBegAbs());
        return result;
    }

    private void nextToken() {
        curToken = tokenStream.get(tokenNo++);
    }

    private void prevToken() {
        curToken = tokenStream.get(--tokenNo);
    }

    private void fixError1(String message) {
        errorCounter++;
        parseErrorLog += "parser error: " + message + ";\n";
        // System.out.println("\nparser error: " + message + ";");
    }

    private void fixError(String message, Position position) {
        Tool.increaseErrorQnt();
        Tool.fixError(message, position);
    }
}
