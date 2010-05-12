/*
 * 
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */
package lebedev;

import java.util.LinkedList;

public class Lexer {

    private final char EOF = '\0';
    private final int TEN = 10;
    private String sourceProgram; // исходная программа - последовательность символов...
    private String lexErrorLog = "";
    private LinkedList<Token> tokenStream = new LinkedList<Token>(); // преобразуется в последовательность токенов;
    private LinkedList<TableCell> varTable = new LinkedList<TableCell>(); // таблица для работы с переменными;
    private char curSym;
    private int smblNo;
    private int lineNo;
    private int idCounter;
    private int errorCounter;

    public Lexer(String input) {
        sourceProgram = input;
        idCounter = 0;
        smblNo = 0;
        lineNo = 0;
        errorCounter = 0;
    }

    public LinkedList<Token> getTokenStream() {
        return tokenStream;
    }

    public LinkedList<TableCell> getVarTable() {
        return varTable;
    }

    public int getErrorQnt() {
        return errorCounter;
    }

    public String getErrorLog() {
        return lexErrorLog;
    }

    public void analyzeSourceProgram() {
        String word;

        getNextChar();
        while (true) {
            deleteWhitespaces();

            if (sourceProgramEnds()) {
                break;
            } else if (isDigit()) {
                tokenStream.add(new Token(TokenType.NUMBER, getNumber()));
            } else if (isSign()) {
                tokenStream.add(new Token(getSign()));
            } else if (isLetter()) {
                word = getWord();

                if (word.equals("let")) {
                    tokenStream.add(new Token(TokenType.LET));
                } else if (word.equals("in")) {
                    tokenStream.add(new Token(TokenType.IN));
                } else if (word.equals("if")) {
                    tokenStream.add(new Token(TokenType.IF));
                } else if (word.equals("then")) {
                    tokenStream.add(new Token(TokenType.THEN));
                } else if (word.equals("else")) {
                    tokenStream.add(new Token(TokenType.ELSE));
                } else if (word.equals("print")) {
                    tokenStream.add(new Token(TokenType.PRINT));
                } else if (word.equals("true")) {
                    tokenStream.add(new Token(TokenType.LOG_OPERAND, 1));
                } else if (word.equals("false")) {
                    tokenStream.add(new Token(TokenType.LOG_OPERAND, 0));
                } else if (word.equals("begin")) {
                    tokenStream.add(new Token(TokenType.BEGIN));
                } else if (word.equals("end")) {
                    tokenStream.add(new Token(TokenType.END));
                } else {
                    fixVariable(word);
                }
            } else if (isLogSign()) {
                tokenStream.add(new Token(getLogOperation()));
            } else if (isSemicolon()) {
                tokenStream.add(new Token(TokenType.SEMICOLON));
            } else {
                fixError("unknown symbol");
            }
            getNextChar();
        }
        tokenStream.add(new Token(TokenType.EOF));
    }

    private void fixVariable(String word) {
        if (!isInTable(word)) {
            varTable.add(new TableCell(idCounter, word));
            tokenStream.add(new Token(TokenType.ID, idCounter));
            idCounter++;
        } else {
            tokenStream.add(new Token(TokenType.ID, findVarNamed(word).getId()));
        }
    }

    private void deleteWhitespaces() {
        while (isWhitespace() || isNewline()) {
            if (isNewline()) {
                lineNo++;
            }
            getNextChar();
        }
    }

    private int getNumber() {
        int number = 0;

        while (isDigit()) {
            number *= TEN;
            number += symToDigit();
            getNextChar();
        }
        if (isLetter()) {
            fixError("invalid variable name");
        }
        ungetChar();

        return number;
    }

    private String getWord() {
        String result = new String();

        if (isLetter()) {
            while (isLetter() || isDigit()) {
                result += curSym;
                getNextChar();
            }
            ungetChar();
        } else {
            fixError("invalid variable name");
        }

        return result;
    }

    private TokenType getSign() {
        switch (curSym) {
            case '+':
                return TokenType.PLUS;

            case '-':
                return TokenType.MINUS;

            case '*':
                return TokenType.MULTIPLICATION;

            case '/':
                return TokenType.DIVISION;

            case '(':
                return TokenType.LEFT_BRACKET;

            case ')':
                return TokenType.RIGHT_BRACKET;

            default:
                fixError("getSign error: strange symbol");
                return null;
        }
    }

    private TokenType getLogOperation() {
        switch (curSym) {
            case '>':
                getNextChar();
                if (curSym == '=') {
                    return TokenType.GE;
                } else {
                    ungetChar();
                    return TokenType.GREATER;
                }

            case '<':
                getNextChar();
                if (curSym == '=') {
                    return TokenType.LE;
                } else {
                    ungetChar();
                    return TokenType.LESS;
                }

            case '!':
                getNextChar();
                if (curSym == '=') {
                    return TokenType.INEQUALITY;
                } else {
                    ungetChar();
                    return TokenType.NOT;
                }

            case '&':
                getNextChar();
                if (curSym == '&') {
                    return TokenType.AND;
                } else {
                    ungetChar();
                    return TokenType.UNKNOWN;
                }

            case '|':
                getNextChar();
                if (curSym == '|') {
                    return TokenType.OR;
                } else {
                    ungetChar();
                    return TokenType.UNKNOWN;
                }

            case '=':
                getNextChar();
                if (curSym == '=') {
                    return TokenType.EQUALITY;
                } else {
                    ungetChar();
                    return TokenType.EQUALS_SIGN;
                }

            default:
                return TokenType.UNKNOWN;
        }
    }

    private void getNextChar() {
        curSym = sourceProgram.charAt(smblNo++);
    }

    private int symToDigit() {
        return curSym - '0';
    }

    private void ungetChar() {
        curSym = sourceProgram.charAt(--smblNo);
    }

    private boolean sourceProgramEnds() {
        return curSym == EOF;
    }

    private boolean isDigit() {
        return curSym >= '0' && curSym <= '9';
    }

    private boolean isLetter() {
        return (curSym >= 'a' && curSym <= 'z') || (curSym >= 'A' && curSym <= 'Z');
    }

    private boolean isWhitespace() {
        return curSym == ' ' || curSym == '\t';
    }

    private boolean isNewline() {
        return curSym == '\n';
    }

    private boolean isSemicolon() {
        return curSym == ';';
    }

    private boolean isSign() {
        return curSym == '+' || curSym == '-' || curSym == '*' ||
                curSym == '/' || curSym == '(' || curSym == ')';
    }

    private boolean isLogSign() {
        return curSym == '=' || curSym == '<' || curSym == '>' ||
                curSym == '!' || curSym == '&' || curSym == '|';
    }

    private boolean isInTable(String wordForCheck) {
        for (int i = 0; i < varTable.size(); i++) {
            if (varTable.get(i).getVarName().equals(wordForCheck)) {
                return true;
            }
        }

        return false;
    }

    private TableCell findVarNamed(String varName) {
        for (int i = 0; i < varTable.size(); i++) {
            if (varTable.get(i).getVarName().equals(varName)) {
                return varTable.get(i);
            }
        }

        fixError("findVarNamed error: no such id");
        return null;
    }

    private void fixError(String message) {
        errorCounter++;
        lexErrorLog += "lexer error: " + message + " in line: " + lineNo + ";\n";
        //  System.out.println("lexer error: " + message + " in line: " + lineNo + ";\n");
    }
}
