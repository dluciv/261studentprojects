/*
 *
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 * (с) Лебедев Дмитрий, 261, 2010;
 *
 */
package lexerandparser;

import ast.*;
import java.util.LinkedList;
import tools.*;

public class Parser {

    private LinkedList<Token> tokenStream;
    private Expression output;
    private int tokenNo = 0;
    private int errorCounter;
    private Token curToken = new Token();
    private Position defaultPosition = new Position(0, 0, 0, 0);

    public Parser(LinkedList<Token> tokenStream) {
        this.tokenStream = tokenStream;
        errorCounter = Tool.getErrorQnt();
    }

    public Expression getOutput() {
        return output;
    }

    public void parseProgram() {
        curToken.setPosition(defaultPosition);
        if (errorCounter == 0) {
            output = parseSequence();
        } else {
            output = null;
        }
    }

    private Expression parseSequence() {
        LinkedList<Expression> sequence = new LinkedList<Expression>();
        Position curPosition = curToken.getPosition();
        do {
            sequence.add(parseExpression());
        } while (curToken.getType() == TokenType.SEMICOLON);
        curPosition.setEndAbs(curToken.getPosition().getEndAbs());
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
                Tool.fixError("code: 0", curPosition);
                result = null;
            }
        }
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
                Tool.fixError("code: 1", curPosition);
                result = null;
            }
        }
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
        return result;
    }

    private Expression parseFactor() {
        Position curPosition = curToken.getPosition();
        nextToken();
        Expression result;
        if (curToken.getType() == TokenType.NOT) {
            result = parseFactor();
            curPosition.setEndAbs(curToken.getPosition().getEndAbs());
            return new LogNot(result, curPosition);
        } else if (curToken.getType() == TokenType.MINUS) {
            nextToken();
            result = parsePrime();
            curPosition.setEndAbs(curToken.getPosition().getEndAbs());
            return new ArNegate(result, curPosition);
        } else {
            result = parsePrime();
            curPosition.setEndAbs(curToken.getPosition().getEndAbs());
            return result;
        }
    }

    private Expression parsePrime() {
        Expression result;
        Position curPosition = curToken.getPosition();

        if (curToken.getType() == TokenType.NUMBER) {
            result = new ArOperand(curToken.getAttribute(), curPosition);
            nextToken();
        } else if (curToken.getType() == TokenType.LOG_OPERAND) {
            if (curToken.getAttribute() == 0) {
                result = new LogOperand(false, curPosition);
            } else {
                result = new LogOperand(true, curPosition);
            }
            nextToken();
        } else if (curToken.getType() == TokenType.ID) {
            result = new Variable(curToken.getAttribute(), curPosition);
            nextToken();
        } else if (curToken.getType() == TokenType.LEFT_BRACKET) {
            Expression toFind = parseExpression();
            curPosition.setEndAbs(curToken.getPosition().getEndAbs());

            if (curToken.getType() != TokenType.RIGHT_BRACKET) {
                curPosition = curToken.getPosition();
                Tool.fixError("missed right bracket", curPosition);
                result = null;
            } else {
                nextToken();
                result = toFind;
            }
        } else if (curToken.getType() == TokenType.LET) {
            nextToken();
            if (curToken.getType() != TokenType.ID) {
                curPosition = curToken.getPosition();
                Tool.fixError("missed variable", curPosition);
                result = null;
            } else {
                int id = curToken.getAttribute();
                nextToken();
                if (curToken.getType() != TokenType.EQUALS_SIGN) {
                    curPosition = curToken.getPosition();
                    Tool.fixError("missed equals sign", curPosition);
                    result = null;
                } else {
                    Expression letExpression = parseExpression();
                    if (letExpression == null) {
                        curPosition = curToken.getPosition();
                        Tool.fixError("missed let expression", curPosition);
                        result = null;
                    } else {
                        if (curToken.getType() != TokenType.IN) {
                            curPosition = curToken.getPosition();
                            Tool.fixError("missed keyword IN", curPosition);
                            result = null;
                        } else {
                            curPosition.setEndAbs(curToken.getPosition().getEndAbs());
                            Expression inExpression = parseExpression();
                            Expression binding = new ExBinding(id, letExpression,
                                    inExpression, curPosition);
                            result = binding;
                        }
                    }
                }
            }
        } else if (curToken.getType() == TokenType.PRINT) {
            nextToken();
            if (curToken.getType() != TokenType.LEFT_BRACKET) {
                curPosition = curToken.getPosition();
                Tool.fixError("missed left bracket", curPosition);
                result = null;
            } else {
                Expression printExpr = parseExpression();
                curPosition.setEndAbs(curToken.getPosition().getEndAbs());
                if (curToken.getType() != TokenType.RIGHT_BRACKET) {
                    curPosition = curToken.getPosition();
                    Tool.fixError("missed right bracket", curPosition);
                    result = null;
                } else {
                    curPosition.setEndAbs(curToken.getPosition().getEndAbs());
                    nextToken();
                    result = new ExPrint(printExpr, curPosition);
                }
            }
        } else if (curToken.getType() == TokenType.IF) {
            Expression ifExpr = parseExpression();
            if (ifExpr == null) {
                curPosition = curToken.getPosition();
                Tool.fixError("missed condition", curPosition);
                result = null;
            } else {
                if (curToken.getType() != TokenType.THEN) {
                    curPosition = curToken.getPosition();
                    Tool.fixError("missed keyword then", curPosition);
                    result = null;
                } else {
                    Expression thenExpr = parseExpression();
                    if (thenExpr == null) {
                        curPosition = curToken.getPosition();
                        Tool.fixError("missed then expression", curPosition);
                        result = null;
                    } else {
                        if (curToken.getType() != TokenType.ELSE) {
                            curPosition = curToken.getPosition();
                            Tool.fixError("missed keyword else", curPosition);
                            result = null;
                        } else {
                            Expression elseExpr = parseExpression();
                            if (elseExpr == null) {
                                curPosition = curToken.getPosition();
                                Tool.fixError("missed else expression", curPosition);
                                result = null;
                            } else {
                                curPosition.setEndAbs(curToken.getPosition().getEndAbs());
                                result = new ExConditional(ifExpr, thenExpr, elseExpr, curToken.getPosition());
                            }
                        }
                    }
                }
            }
        } else if (curToken.getType() == TokenType.BEGIN) {
            Expression seqExpr = parseSequence();
            if (curToken.getType() != TokenType.END) {
                curPosition = curToken.getPosition();
                Tool.fixError("missed end statement or semicolon", curPosition);
                result = null;
            }
            curPosition.setEndAbs(curToken.getPosition().getEndAbs());
            nextToken();
            result = seqExpr;
        } else if (curToken.getType() == TokenType.FUNCTION) {
            nextToken();
            if (curToken.getType() != TokenType.ID) {
                curPosition = curToken.getPosition();
                Tool.fixError("missed variable", curPosition);
                result = null;
            } else {
                int id = curToken.getAttribute();
                nextToken();
                if (curToken.getType() != TokenType.ARROW) {
                    curPosition = curToken.getPosition();
                    Tool.fixError("missed arrow", curPosition);
                    result = null;
                } else {
                    Expression funExpr = parseExpression();
                    if (funExpr == null) {
                        curPosition = curToken.getPosition();
                        Tool.fixError("missed function expression", curPosition);
                        result = null;
                    } else {
                        curPosition.setEndAbs(curToken.getPosition().getEndAbs());
                        ExFunction function = new ExFunction(id, funExpr, curPosition);
                        result = function;
                    }
                }
            }
        } else if (curToken.getType() == TokenType.EOF) {
            Tool.fixError("no expression", curToken.getPosition());
            result = null;
        } else if (curToken.getType() == TokenType.SEMICOLON) {
            Tool.fixError("empty statement", curToken.getPosition());
            result = null;
        } else {
            System.out.println(curToken.getType());
            //Tool.fixError("strange symbol, error code: 3 ", curToken.getPosition());
            result = null;
        }
        if (result != null) {
            switch (curToken.getType()) {
                case NUMBER:
                case LOG_OPERAND:
                case ID:
                case BEGIN:
                case LET:
                case FUNCTION:
                case LEFT_BRACKET:
                    prevToken();
                    Expression parseExpr = parseExpression();
                    if (parseExpr == null) {
                        curPosition = curToken.getPosition();
                        Tool.fixError("missed expression", curPosition);
                        result = null;
                    } else {
                        curPosition.setEndAbs(curToken.getPosition().getEndAbs());
                        result = new ExApplication(result, parseExpr, curToken.getPosition());
                        break;
                    }
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
}
