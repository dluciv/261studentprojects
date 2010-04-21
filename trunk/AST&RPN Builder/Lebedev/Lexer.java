//Lebedev Dmitry 2010 (c)
package ast;

import java.util.Scanner;

public class Lexer {

//    public enum LexemKind {
//        plus, minus, divide, multiply, openbracket, closebracket, number, unknown, semicolon, let, eof
//    }
    private String currentString;
    private int symbolPosition;
    private int linePosition;
    private int absPosition;
    private int counter;
    private Scanner programm;
    private Lexem lexem;

    public Lexer(Scanner scanner) {
        symbolPosition = 0;
        programm = scanner;
        if (programm.hasNextLine()) {
            currentString = programm.nextLine();
        }
        lexem = new Lexem(LexemKind.unknown);//Pair.create(LexemKind.unknown, "0");
        if (currentString.charAt(symbolPosition) == ';') {
            lexem = new Lexem(LexemKind.semicolon);//Pair.create(LexemKind.semicolon, "0");
            }
        if (currentString.charAt(symbolPosition) == '+') {
            lexem = new Lexem(LexemKind.plus);//Pair.create(LexemKind.plus, "0");
            }
        if (currentString.charAt(symbolPosition) == '-') {
            lexem = new Lexem(LexemKind.minus);// Pair.create(LexemKind.minus, "0");
            }
        if (currentString.charAt(symbolPosition) == '*') {
            lexem = new Lexem(LexemKind.multiply);//Pair.create(LexemKind.multiply, "0");
            }
        if (currentString.charAt(symbolPosition) == '/') {
            lexem = new Lexem(LexemKind.divide);//Pair.create(LexemKind.divide, "0");
            }
        if (currentString.charAt(symbolPosition) == '(') {
            lexem = new Lexem(LexemKind.openbracket);//Pair.create(LexemKind.openbracket, "0");
            }
        if (currentString.charAt(symbolPosition) == ')') {
            lexem = new Lexem(LexemKind.closebracket);//Pair.create(LexemKind.closebracket, "0");
            }
        if (currentString.charAt(symbolPosition) >= '0' && currentString.charAt(symbolPosition) <= '9') {
            lexem = new Lexem(LexemKind.number, programm.nextInt()); //Pair.create(LexemKind.number, CurrLexem);
            }
        if (currentString.length() == symbolPosition && !(programm.hasNextLine())) {
            lexem = new Lexem(LexemKind.eof);//Pair.create(LexemKind.eol, "/n");

        }
        //lexem = ;
    }

    public int getSymbolPosition() {
        return symbolPosition;
    }

    public int getLinePosition() {
        return linePosition;
    }

    public int getAbsPosition() {
        return absPosition;
    }
//    public String getCurrent() {
//        if (currentString.length() > symbolPosition) {
//            if (currentString.charAt(symbolPosition) >= '0' && currentString.charAt(symbolPosition) <= '9') {
//                return getNumber();
//            }
//            return currentString.substring(symbolPosition, symbolPosition + 1);
//        } else {
//            return "/n";
//        }
//    }
//    String CurrLexem = getCurrent();

    public Lexem getCurrentLexem() {
        return lexem;
    }

//    public int getNumber() {
//        return programm.nextInt();
//    }
    public int getNumber() {
        String number = "";
        counter = symbolPosition;
        while (currentString.length() > counter && currentString.charAt(counter) >= '0' && currentString.charAt(counter) <= '9') {
            number += String.valueOf(currentString.charAt(counter));
            counter++;
        }
        return Integer.parseInt(number);
    }

//    private int getInt() {
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
    public void moveNext() {
        if (currentString.length() > symbolPosition + 1) {
            while (currentString.length() > symbolPosition && currentString.charAt(symbolPosition) == ' ') {
                symbolPosition++;
                absPosition++;
            }

            if (currentString.charAt(symbolPosition) <= '0' || currentString.charAt(symbolPosition) >= '9') {
                symbolPosition++;
                absPosition++;
            } else {
                while (currentString.length() > symbolPosition &&
                        currentString.charAt(symbolPosition) >= '0' &&
                        currentString.charAt(symbolPosition) <= '9') {
                    symbolPosition++;
                    absPosition++;
                }
                //Curr
            }
        } else {
            if (currentString.length() == symbolPosition + 1 && programm.hasNextLine()) {
                currentString = programm.nextLine();
                linePosition++;
                absPosition++;
                symbolPosition = 0;
            } else {
                currentString = "\n";
            }
        }
        lexem = new Lexem(LexemKind.unknown);//Pair.create(LexemKind.unknown, "0");
        if (currentString.charAt(symbolPosition) == ';') {
            lexem = new Lexem(LexemKind.semicolon);//Pair.create(LexemKind.semicolon, "0");
            }
        if (currentString.charAt(symbolPosition) == '+') {
            lexem = new Lexem(LexemKind.plus);//Pair.create(LexemKind.plus, "0");
            }
        if (currentString.charAt(symbolPosition) == '-') {
            lexem = new Lexem(LexemKind.minus);// Pair.create(LexemKind.minus, "0");
            }
        if (currentString.charAt(symbolPosition) == '*') {
            lexem = new Lexem(LexemKind.multiply);//Pair.create(LexemKind.multiply, "0");
            }
        if (currentString.charAt(symbolPosition) == '/') {
            lexem = new Lexem(LexemKind.divide);//Pair.create(LexemKind.divide, "0");
            }
        if (currentString.charAt(symbolPosition) == '(') {
            lexem = new Lexem(LexemKind.openbracket);//Pair.create(LexemKind.openbracket, "0");
            }
        if (currentString.charAt(symbolPosition) == ')') {
            lexem = new Lexem(LexemKind.closebracket);//Pair.create(LexemKind.closebracket, "0");
            }
        if (currentString.charAt(symbolPosition) >= '0' && currentString.charAt(symbolPosition) <= '9') {
            lexem = new Lexem(LexemKind.number, getNumber()); //Pair.create(LexemKind.number, CurrLexem);
            }
        if (currentString.length() == symbolPosition && !(programm.hasNextLine())) {
            lexem = new Lexem(LexemKind.eof);//Pair.create(LexemKind.eol, "/n");
            }
    }
}
