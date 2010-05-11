/*
 * Lexer
 * Dmitriy Zabranskiy g261 (c)2010
 */
package analizator;

import java.util.LinkedList;

public class Lexer {

    public static LinkedList<Lexem> lexems = new LinkedList<Lexem>();
    private static int currentCol;
    private static int currentRow = 1;
    private static int temp = 0;
    public static int count = 0;

    public static LinkedList<Lexem> convertStrToLexems(String inputLine) throws Exception {

        if (inputLine.isEmpty()) {
            throw new IllegalArgumentException("Illegal Argument!!!");
        }
        
        int i = 0;
        int length = inputLine.length() - 1;
        String res = "";

        while (i <= length) {
            currentCol = i - temp + 1;
            if (inputLine.charAt(i) == '\n') {
                currentRow++;
                temp = temp + currentCol;
            }
            if ((inputLine.charAt(i) == ' ') || (inputLine.charAt(i) == '\n')
                    || (inputLine.charAt(i) == '\t')
                    || (inputLine.charAt(i) == '\r')) {
                i++;
                continue;
            } else if ((inputLine.charAt(i) >= '0') && (inputLine.charAt(i) <= '9')) {
                while ((i <= length) && ((inputLine.charAt(i) >= '0') && (inputLine.charAt(i) <= '9'))) {
                    res = res + inputLine.charAt(i);
                    i++;
                }
                lexems.add(new Number(Integer.parseInt(res), new Position(currentCol, currentRow)));
                res = "";
                continue;
            } else if (inputLine.substring(i, length + 1).startsWith("if")) {
                lexems.add(new Lexem(LexType.IF, new Position(currentCol, currentRow)));
                i = i + 2;
                continue;
            } else if (inputLine.substring(i, length + 1).startsWith("then")) {
                lexems.add(new Lexem(LexType.THEN, new Position(currentCol, currentRow)));
                i = i + 4;
                continue;
            } else if (inputLine.substring(i, length + 1).startsWith("else")) {
                lexems.add(new Lexem(LexType.ELSE, new Position(currentCol, currentRow)));
                i = i + 4;
                continue;
            } else if (inputLine.substring(i, length + 1).startsWith("print")) {
                lexems.add(new Lexem(LexType.PRINT, new Position(currentCol, currentRow)));
                i = i + 5;
                continue;
            } else if (inputLine.substring(i, length + 1).startsWith("==")) {
                lexems.add(new Lexem(LexType.EQUAL, new Position(currentCol, currentRow)));
                i = i + 2;
                continue;
            } else if ((inputLine.charAt(i) >= 'a') && (inputLine.charAt(i) <= 'z')) {
                lexems.add(new Variable(Character.toString(inputLine.charAt(i)), new Position(currentCol, currentRow)));
                i++;
                continue;
            }
            switch (inputLine.charAt(i)) {
                case ('+'): {
                    lexems.add(new Lexem(LexType.ADD, new Position(currentCol, currentRow)));
                    i++;
                    break;
                }
                case ('-'): {
                    lexems.add(new Lexem(LexType.SUB, new Position(currentCol, currentRow)));
                    i++;
                    break;
                }
                case ('*'): {
                    lexems.add(new Lexem(LexType.MULT, new Position(currentCol, currentRow)));
                    i++;
                    break;
                }
                case ('/'): {
                    lexems.add(new Lexem(LexType.DIV, new Position(currentCol, currentRow)));
                    i++;
                    break;
                }
                case ('^'): {
                    lexems.add(new Lexem(LexType.DEGREE, new Position(currentCol, currentRow)));
                    i++;
                    break;
                }
                case ('('): {
                    lexems.add(new Lexem(LexType.BREAKITOPEN, new Position(currentCol, currentRow)));
                    i++;
                    break;
                }
                case (')'): {
                    lexems.add(new Lexem(LexType.BREAKITCLOSE, new Position(currentCol, currentRow)));
                    i++;
                    break;
                }
                case ('='): {
                    lexems.add(new Lexem(LexType.ASSIGN, new Position(currentCol, currentRow)));
                    i++;
                    break;
                }
                case (';'): {
                    lexems.add(new Lexem(LexType.EOL, new Position(currentCol, currentRow)));
                    i++;
                    break;
                }
                default: {
                    throw new Exception("invalid character " + inputLine.charAt(i) + " at " + currentRow + " " + currentCol + "\n");
                }
            }
        }
        currentCol++;
        lexems.add(new Lexem(LexType.EOL, new Position(currentCol, currentRow)));
        return lexems;
    }

    public static Lexem getCurrent() {
        return lexems.get(count);
    }

    public static int Next() {
        return count++;
    }
}

