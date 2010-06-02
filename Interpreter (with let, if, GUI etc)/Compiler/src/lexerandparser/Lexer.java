/*
 * 
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 * (с) Лебедев Дмитрий, 261, 2010;
 */
package lexerandparser;

import java.util.LinkedList;
import tools.Tool;

public class Lexer {

    private final char EOF = '\0';
    private final int TEN = 10;
    private final int TAB_LENGTH = 2; // можно ли как-то без этого?
    private String sourceProgram; // исходная программа - последовательность символов...
    private LinkedList<Token> tokenStream = new LinkedList<Token>(); // преобразуется в последовательность токенов;
    private LinkedList<TableCell> varTable = new LinkedList<TableCell>(); // таблица для работы с переменными;
    private Position curTokenPos;
    private char curSym;
    private int beginPos;
    private int smblNo;
    private int lineNo;
    private int columnNo;
    private int idCounter;
    private String[] keyWordList = {"if", "let", "in", "then", "else", "print", "true", "false", "begin", "end", "fun"};
    private int beginColumnNo;

    public Lexer(String input) {
        sourceProgram = input;
        idCounter = 0;
        smblNo = 0;
        lineNo = 1;
        columnNo = 0;
    }

    public LinkedList<Token> getTokenStream() {
        return tokenStream;
    }

    public LinkedList<TableCell> getVarTable() {
        return varTable;
    }

    public void analyzeSourceProgram() {
        String word;

        getNextChar();
        while (true) {
            deleteWhitespaces();

            if (sourceProgramEnds()) {
                break;
            } else if (isDigit()) {
                tokenStream.add(new Token(TokenType.NUMBER, getNumber(), curTokenPos));
            } else if (isSign()) {
                tokenStream.add(new Token(getSign(), curTokenPos));
            } else if (isLetter()) {
                word = getWord();

                if (word.equals("let")) {
                    tokenStream.add(new Token(TokenType.LET, curTokenPos));
                } else if (word.equals("in")) {
                    tokenStream.add(new Token(TokenType.IN, curTokenPos));
                } else if (word.equals("if")) {
                    tokenStream.add(new Token(TokenType.IF, curTokenPos));
                } else if (word.equals("then")) {
                    tokenStream.add(new Token(TokenType.THEN, curTokenPos));
                } else if (word.equals("else")) {
                    tokenStream.add(new Token(TokenType.ELSE, curTokenPos));
                } else if (word.equals("print")) {
                    tokenStream.add(new Token(TokenType.PRINT, curTokenPos));
                } else if (word.equals("true")) {
                    tokenStream.add(new Token(TokenType.LOG_OPERAND, 1, curTokenPos));
                } else if (word.equals("false")) {
                    tokenStream.add(new Token(TokenType.LOG_OPERAND, 0, curTokenPos));
                } else if (word.equals("begin")) {
                    tokenStream.add(new Token(TokenType.BEGIN, curTokenPos));
                } else if (word.equals("end")) {
                    tokenStream.add(new Token(TokenType.END, curTokenPos));
                } else if (word.equals("fun")) {
                    tokenStream.add(new Token(TokenType.FUNCTION, curTokenPos));
                } else {
                    fixVariable(word);
                }
            } else if (isLogSign()) {
                tokenStream.add(new Token(getLogOperation(), curTokenPos));
            } else if (isSemicolon()) {
                curTokenPos = new Position(smblNo, smblNo, lineNo, columnNo);
                tokenStream.add(new Token(TokenType.SEMICOLON, curTokenPos));
            } else {
                curTokenPos = new Position(smblNo, smblNo, lineNo, columnNo);
                tokenStream.add(new Token(TokenType.UNKNOWN, curTokenPos));
                Tool.fixError("unknown symbol", curTokenPos);
            }
            getNextChar();
        }
        curTokenPos = new Position(smblNo, smblNo, lineNo, columnNo);
        tokenStream.add(new Token(TokenType.EOF, curTokenPos));
        if (tokenStream.size() == 1) {
            Tool.fixError("try to write some program", curTokenPos);
        }
        //printTokenStream();
    }

    private void fixVariable(String word) {
        if (!isInTable(word)) {
            varTable.add(new TableCell(idCounter, word));
            tokenStream.add(new Token(TokenType.ID, idCounter, curTokenPos));
            idCounter++;
        } else {
            tokenStream.add(new Token(TokenType.ID, findVarNamed(word).getId(), curTokenPos));
        }
    }

    private void deleteWhitespaces() {
        while (isWhitespace() || curSym == '\n' || curSym == '\r') { // !!!
            if (isNewline()) {
                getNextChar();
                columnNo = 0;
                lineNo++;
            } else if (curSym == '\t') {
                columnNo += TAB_LENGTH - 1;
            }
            getNextChar();
        }
        //curTokenPos = new Position(0, smblNo, lineNo, columnNo);
    }

    private int getNumber() {
        int number = 0;
        beginPos = smblNo;
        while (isDigit()) {
            number *= TEN;
            number += symToDigit();
            getNextChar();
        }
        ungetChar();
        curTokenPos = new Position(beginPos, smblNo, lineNo, columnNo);
        return number;
    }

    private String getWord() {
        String result = new String();
        beginPos = smblNo;
        beginColumnNo = columnNo;
        while (isLetter() || isDigit()) {
            result += curSym;
            getNextChar();
        }
        ungetChar();
        curTokenPos = new Position(beginPos, smblNo, lineNo, beginColumnNo);
        return result;
    }

    private TokenType getSign() {
        beginPos = smblNo;
        curTokenPos = new Position(beginPos, smblNo, lineNo, columnNo);
        switch (curSym) {
            case '+':
                return TokenType.PLUS;

            case '-':
                getNextChar();
                if (curSym == '>') {
                    curTokenPos = new Position(beginPos, smblNo, lineNo, columnNo);
                    return TokenType.ARROW;
                } else {
                    ungetChar();
                    return TokenType.MINUS;
                }

            case '*':
                return TokenType.MULTIPLICATION;

            case '/':
                return TokenType.DIVISION;

            case '(':
                return TokenType.LEFT_BRACKET;

            case ')':
                return TokenType.RIGHT_BRACKET;

            default:
                Tool.fixError("getSign error: strange symbol", curTokenPos);
                return null;
        }

    }

    private TokenType getLogOperation() {
        curTokenPos = new Position(beginPos, smblNo, lineNo, columnNo);
        //beginPos = smblNo;
        switch (curSym) {
            case '>':
                getNextChar();
                if (curSym == '=') {
                    curTokenPos.setEndAbs(smblNo);
                    return TokenType.GE;
                } else {
                    ungetChar();
                    return TokenType.GREATER;
                }

            case '<':
                getNextChar();
                if (curSym == '=') {
                    curTokenPos.setEndAbs(smblNo);
                    return TokenType.LE;
                } else {
                    ungetChar();
                    return TokenType.LESS;
                }

            case '!':
                getNextChar();
                if (curSym == '=') {
                    curTokenPos.setEndAbs(smblNo);
                    return TokenType.INEQUALITY;
                } else {
                    ungetChar();
                    return TokenType.NOT;
                }

            case '&':
                getNextChar();
                if (curSym == '&') {
                    curTokenPos.setEndAbs(smblNo);
                    return TokenType.AND;
                } else {
                    ungetChar();
                    return TokenType.UNKNOWN;
                }

            case '|':
                getNextChar();
                if (curSym == '|') {
                    curTokenPos.setEndAbs(smblNo);
                    return TokenType.OR;
                } else {
                    ungetChar();
                    return TokenType.UNKNOWN;
                }

            case '=':
                getNextChar();
                if (curSym == '=') {
                    curTokenPos.setEndAbs(smblNo);
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
        columnNo++;

    }

    private int symToDigit() {
        return curSym - '0';
    }

    private void ungetChar() {
        curSym = sourceProgram.charAt(--smblNo);
        columnNo--;

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
        return curSym == '\r';
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
        for (int i = 0; i <
                varTable.size(); i++) {
            if (varTable.get(i).getVarName().equals(wordForCheck)) {
                return true;
            }

        }

        return false;
    }

    private TableCell findVarNamed(String varName) {
        for (int i = 0; i <
                varTable.size(); i++) {
            if (varTable.get(i).getVarName().equals(varName)) {
                return varTable.get(i);
            }
        }
        Tool.fixError("findVarNamed error: no such id", curTokenPos);
        return null;
    }

// temp;
    public void printTokenStream() {
        for (Token token : tokenStream) {
            System.out.print("<" + token.getType() + " " + token.getAttribute() + " " + token.getPosition().getLine() + " " + token.getPosition().getColumn() + "> ");
        }
        System.out.println();
    }

    public String[] getKeyWordList() {
        return keyWordList;
    }
}
