//Lebedev Dmitry 2010 (c)
package lebedev1;

public class Lexer {

    public enum LexemKind {
        plus, minus, divide, multiply, openbracket, closebracket, number, unknown, semicolon, let, eof, eol, print, begin, end, fun,
    }

    private String programm;
    private int symbolPosition;
    private int linePosition;
    private int absPosition;
    private int counter;
    private Lexem lexem;


    public Lexer(String arg) {
        absPosition = 0;
        programm = arg;
    }

    public int getSymbolPosition() {
        return symbolPosition;
    }

    public String getCurrent() {
        if (programm.length() > absPosition) {
            if (programm.charAt(absPosition) >= '0' && programm.charAt(absPosition) <= '9') {
                String result = "";
                counter = absPosition;
                while (programm.length()>counter && programm.charAt(counter) >= '0' && programm.charAt(counter) <= '9') {
                    result += programm.charAt(counter);
                }
                return result;
            } else {
                if(programm.charAt(absPosition) == '/' && programm.charAt(absPosition + 1) == 'n'){
                    return "/n";
                }
            }
            return programm.substring(absPosition, absPosition + 1);
        } else {
            return "eol";
        }
    }

    String CurrLexem = getCurrent();

    public Lexem getCurrentLexem() {
        return lexem;
    }



    public void moveNext() {
        if (programm.length() > absPosition) {
            while (programm.length() > absPosition && programm.charAt(absPosition) == ' ') {
                absPosition++;
                absPosition++;
            }
            if (programm.charAt(absPosition) == ';') {
                lexem = new Lexem(LexemKind.semicolon);//Pair.create(LexemKind.semicolon, "0");
            }
            if (CurrLexem.equals("+")) {
                lexem = new Lexem(LexemKind.plus);//Pair.create(LexemKind.plus, "0");
            }
            if (CurrLexem.equals("-")) {
                lexem = new Lexem(LexemKind.minus);// Pair.create(LexemKind.minus, "0");
            }
            if (CurrLexem.equals("*")) {
                lexem = new Lexem(LexemKind.multiply);//Pair.create(LexemKind.multiply, "0");
            }
            if (CurrLexem.equals("/")) {
                lexem = new Lexem(LexemKind.divide);//Pair.create(LexemKind.divide, "0");
            }
            if (CurrLexem.equals("(")) {
                lexem = new Lexem(LexemKind.openbracket);//Pair.create(LexemKind.openbracket, "0");
            }
            if (CurrLexem.equals(")")) {
                lexem = new Lexem(LexemKind.closebracket);//Pair.create(LexemKind.closebracket, "0");
            }
            if (CurrLexem.charAt(0) >= '0' && CurrLexem.charAt(0) <= '9') {
                lexem = new Lexem(LexemKind.number, Integer.valueOf(CurrLexem)); //Pair.create(LexemKind.number, CurrLexem);
            }
            if (CurrLexem.equals("/n")) {
                lexem = new Lexem(LexemKind.eol);//Pair.create(LexemKind.eol, "/n");
            }
            if (CurrLexem.equals("eof")) {
                lexem = new Lexem(LexemKind.eof);//Pair.create(LexemKind.eol, "/n");
            }
            lexem = new Lexem(LexemKind.unknown);//Pair.create(LexemKind.unknown, "0");

            if (programm.charAt(absPosition) <= '0' || programm.charAt(absPosition) >= '9') {
                absPosition++;
                absPosition++;
                //Curr=
            } else {
                while (programm.length() > absPosition &&
                        programm.charAt(absPosition) >= '0' &&
                        programm.charAt(absPosition) <= '9') {
                    absPosition++;
                    absPosition++;
                }
                //Curr
            }

        } else {
            if (programm.length() == absPosition ) {
                programm = "eof";
            }
        }
    }
}
