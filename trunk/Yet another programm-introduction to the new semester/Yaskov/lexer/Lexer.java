/*
 * 
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package lexer;

import java.util.LinkedList;

public class Lexer {
    private final int NONE = 0;
    private final char EOF = '\0';
    private final int TEN = 10;
    
    private String sourceProgram; // исходная программа - последовательность символов...
    private LinkedList<Token> tokenStream = new LinkedList<Token>(); // преобразуется в последовательность токенов;
    private LinkedList<TableCell> varTable = new LinkedList<TableCell>(); // таблица для работы с переменными;
    
    private char curSym;
    private int smblNo;
    private int lineNo;
    private int curId;
    private int errorCounter = 0;

    public Lexer(String input) {
        sourceProgram = input;
        curId = 0;
        smblNo = 0;
        lineNo = 0;
        analyzeProgram();
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

    private void analyzeProgram() {
        getNextChar();
        while (!sourceProgramEnds()) {
            analyzeStatement();
            getNextChar();
            deleteWhitespaces();
        }
        tokenStream.add(new Token(TokenType.EOF, NONE));
    }

    private void analyzeStatement() {
        String word = getWord();

        if (word.equals("print")) {     // ключевое слово;
            deleteWhitespaces();
            if (isAssignment()) {
                fixError("statement error, invalid varname");
            }
            tokenStream.add(new Token(TokenType.PRINT, NONE));
        }
        else {                          // присваивание;
            fixVariable(word);
            deleteWhitespaces();
            if (!isAssignment()) {
                fixError("statement error, '=' expected");
            }
            tokenStream.add(new Token(TokenType.ASSIGNMENT, NONE));
            getNextChar();
        }

        analyzeExpression();
    }

    private void analyzeExpression() {
        getNextChar();

        while (!isSemicolon()) {
            deleteWhitespaces();
            if (isDigit()) {
                tokenStream.add(new Token(TokenType.NUMBER, getNumber()));
            }
            else if (isLetter()) {
                fixVariable(getWord());
            }
            else if (isSign()) {
                tokenStream.add(new Token(getSign(), NONE));
            }
            getNextChar();
        }
        tokenStream.add(new Token(TokenType.SEMICOLON, NONE));
    }

    private void fixVariable(String word) {
        if (word.equals("print")) {
            fixError("invalid var name");
        } else if(!isInTable(word)) {
            varTable.add(new TableCell(curId, word));
            tokenStream.add(new Token(TokenType.ID, curId));
            curId++;
        }
        else {
            tokenStream.add(new Token(TokenType.ID, findVarNamed(word).getId()));
        }
    }

    private void deleteWhitespaces() {
        while(isWhitespace() || isNewline()) {
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
            while(isLetter() || isDigit()) {
                result += curSym;
                getNextChar();
            }
            ungetChar();
        }
        else {
            fixError("invalid variable name");
        }

        return result;
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

    private boolean isSign() {
        return curSym == '+' || curSym == '-' || curSym == '*' ||
               curSym == '/' || curSym == '(' || curSym == ')';
    }

    private boolean isAssignment() {
        return curSym == '=';
    }

    private boolean isSemicolon() {
        return curSym == ';';
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
                return null;
        }
    }

    void fixError(String message) {
        errorCounter++;
        System.out.println("\nlexer error: " + message + ";");
    }

    boolean isInTable(String wordForCheck) {
        for (int i = 0; i < varTable.size(); ++i) {
            if (varTable.get(i).getVarName().equals(wordForCheck)) {
                return true;
            }
        }

        return false;
    }

    private TableCell findVarNamed(String varName) {
        for (int i = 0; i < varTable.size(); ++i) {
            if (varTable.get(i).getVarName().equals(varName)) {
                return varTable.get(i);
            }
        }

        fixError("no such id");
        return null;
    }
}
