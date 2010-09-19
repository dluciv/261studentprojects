/*
 *
 *
 * (c) Volkova Ekatetina
 */
package ide.ide;

import java.util.HashMap;

public class Lexer {

    private String program;
    private RepresLexem currLexeme;
    private int currIndex = 0;
    private int prevIndex = 0;
    private int lineNumber = 0;
    private int colomnNumber = 0;
    public HashMap<String, Lexeme> keyTable = new HashMap<String, Lexeme>() {

        {
            put("if", Lexeme.If);
            put("then", Lexeme.Then);
            put("else", Lexeme.Else);
            put("let", Lexeme.Let);
            put("in", Lexeme.In);
            put("print", Lexeme.Print);
            put("begin", Lexeme.Begin);
            put("fun", Lexeme.Fun);
            put("end", Lexeme.End);
            put("->", Lexeme.OpFun);
            put("rec", Lexeme.Rec);

        }
    };

    public Lexer(String program) {
        this.program = program;
    }

    public boolean isKeyWord(String kw) {
        return (keyTable.containsKey(kw));
    }

    public HashMap<String, Lexeme> getKeyTable() {
        return keyTable;
    }

    public RepresLexem getCurrLexeme() {
        return currLexeme;
    }

    private boolean isId() {
        return ((program.charAt(currIndex) >= 'a' && program.charAt(currIndex) <= 'z') || (program.charAt(currIndex) >= 'A' && program.charAt(currIndex) <= 'Z'));
    }

    private boolean isNumber() {
        return (program.charAt(currIndex) >= '0' && program.charAt(currIndex) <= '9');
    }

    private boolean isEOL() {
        return (currIndex == program.length());
    }

    private String getId() {

        String id = String.valueOf(program.charAt(currIndex));

        currIndex++;
        while (true) {
            if (program.length() > currIndex && isId()) {
                id += String.valueOf(program.charAt(currIndex));
                currIndex++;
            } else {
                break;
            }
        }

        return id;
    }

    private Integer getNumber() {
        String number = String.valueOf(program.charAt(currIndex));

        currIndex++;
        while (true) {
            if (program.length() > currIndex && program.charAt(currIndex) >= '0' && program.charAt(currIndex) <= '9') {
                number += String.valueOf(program.charAt(currIndex));
                currIndex++;
            } else {
                break;
            }
        }

        return Integer.parseInt(number);
    }

    private Position getPosition() {
        return new Position(colomnNumber, lineNumber, prevIndex, currIndex - prevIndex);
    }

    public void next() {
        char currChar = ' ';

        if (currIndex < program.length()) {
            currChar = program.charAt(currIndex);

            while ((currChar == ' ' || currChar == '\r' || currChar == '\n') && currIndex < (program.length() - 1)) {
                currIndex++;
                if (currChar == '\n') {
                    colomnNumber = 0;
                    lineNumber++;
                } else {
                    colomnNumber++;
                }
                currChar = program.charAt(currIndex);
            }
        }

        prevIndex = currIndex;

        if (isEOL()) {
            currLexeme = new RepresLexem(Lexeme.EOL, '0', getPosition());
        } else {
            switch (currChar) {
                case '+':
                    currIndex++;
                    currLexeme = new RepresLexem(Lexeme.Plus, '+', getPosition());
                    break;
                case '*':
                    currIndex++;
                    currLexeme = new RepresLexem(Lexeme.Mult, '*', getPosition());
                    break;
                case '/':
                    currIndex++;
                    currLexeme = new RepresLexem(Lexeme.Div, '/', getPosition());
                    break;
                case '(':
                    currIndex++;
                    currLexeme = new RepresLexem(Lexeme.LeftBracket, '(', getPosition());
                    break;
                case ')':
                    currIndex++;
                    currLexeme = new RepresLexem(Lexeme.RightBracket, ')', getPosition());
                    break;
                case '=':
                    currIndex++;
                    currLexeme = new RepresLexem(Lexeme.Equal, '=', getPosition());
                    break;
                case ';':
                    currIndex++;
                    currLexeme = new RepresLexem(Lexeme.Semi, ';', getPosition());
                    break;
                case '!':
                    currIndex++;
                    currLexeme = new RepresLexem(Lexeme.Not, '!', getPosition());
                    break;
                case '-':
                    if (program.charAt(currIndex + 1) == '>') {
                        currIndex += 2;
                        currLexeme = new RepresLexem(Lexeme.OpFun, '-', getPosition());
                        break;
                    } else {
                        currIndex++;
                        currLexeme = new RepresLexem(Lexeme.Minus, '-', getPosition());
                        break;
                    }
                case '>':
                    if (program.charAt(currIndex + 1) == '=') {
                        currIndex += 2;
                        currLexeme = new RepresLexem(Lexeme.GreaterEq, '>', getPosition());
                        break;
                    } else {
                        currIndex++;
                        currLexeme = new RepresLexem(Lexeme.Greater, '>', getPosition());
                        break;
                    }
                case '<':
                    if (program.charAt(currIndex + 1) == '=') {
                        currIndex += 2;
                        currLexeme = new RepresLexem(Lexeme.LessEq, '<', getPosition());
                        break;
                    } else {
                        currIndex++;
                        currLexeme = new RepresLexem(Lexeme.Less, '>', getPosition());
                        break;
                    }
                case '&':

                    if (program.charAt(currIndex + 1) == '&') {
                        currIndex += 2;
                        currLexeme = new RepresLexem(Lexeme.And, '&', getPosition());
                    }
                    break;

                case '|':
                    if (program.charAt(currIndex + 1) == '|') {
                        currIndex += 2;
                        currLexeme = new RepresLexem(Lexeme.Or, '|', getPosition());
                    }
                    break;

                default:
                    if (isNumber()) {
                        currLexeme = new RepresLexem(Lexeme.Number, getNumber(), getPosition());
                    } else if (isId()) {
                        String id = getId();
                        if (isKeyWord(id)) {
                            currLexeme = new RepresLexem(keyTable.get(id), id, getPosition());
                        } else {
                            currLexeme = new RepresLexem(Lexeme.Id, id, getPosition());
                        }
                    } else {
                        currLexeme = new RepresLexem(Lexeme.UnKnown, '?', getPosition());
                    }
            }
        }

    }
}


