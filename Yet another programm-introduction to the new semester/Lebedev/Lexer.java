//Lebedev Dmitry 2010 (c)
package ast;

public class Lexer {

    public enum lexem {

        plus, minus, divide, multiply, eol, openbracket, closebracket, number, unknown
    }
    String expression;
    private int position;

    public Lexer(String arg) {
        expression = arg.trim();
    }

    public int getPosition() {
        return position;
    }

    public String getCurrent() {
        if (expression.length() > position) {
            if (expression.charAt(position) >= '0' && expression.charAt(position) <= '9') {
                return getNumber();
            }
            return expression.substring(position);
        } else {
            return "/n";
        }
    }

    public Pair<lexem, String> getCurrentLexem() {
        String CurrLexem = getCurrent();

        if (CurrLexem.equals("+")) {
            return Pair.create(lexem.plus, "0");
        }
        if (CurrLexem.equals("-")) {
            return Pair.create(lexem.minus, "0");
        }
        if (CurrLexem.equals("*")) {
            return Pair.create(lexem.multiply, "0");
        }
        if (CurrLexem.equals("/")) {
            return Pair.create(lexem.divide, "0");
        }
        if (CurrLexem.equals("(")) {
            return Pair.create(lexem.openbracket, "0");
        }
        if (CurrLexem.equals(")")) {
            return Pair.create(lexem.closebracket, "0");
        }
        if (CurrLexem.charAt(position) >= '0' && CurrLexem.charAt(position) <= '9') {
            return Pair.create(lexem.number, CurrLexem);
        }
        if (CurrLexem.equals("/n")) {
            return Pair.create(lexem.eol, "/n");
        }

        return Pair.create(lexem.unknown, "0");
    }

    public String getNumber() {
        String number = "";
        int i = 0;
        while (expression.length() > position && expression.charAt(position) >= '0' && expression.charAt(position) <= '9') {
            number += String.valueOf(expression.charAt(position));
            position++;
        }
        return number;
    }

    public void moveNext() {
        if (expression.length() > position) {
            if (expression.charAt(position) <= '0' || expression.charAt(position) >= '9') {
                position++;
            } else {
                while (expression.length() > 0 &&
                        expression.charAt(position) >= '0' &&
                        expression.charAt(position) <= '9') {
                    position++;
                }
            }
        } else {
            if (expression.length() == 0) {
                return;
            } else {
                expression = "/n";
            }
        }
    }
}
