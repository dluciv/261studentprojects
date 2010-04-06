//Lebedev Dmitry 2010 (c)
package ast;

public class Lexer {

    public enum lexem {

        plus, minus, divide, multiply, eol, openbracket, closebracket, number, unknown
    }
    private String expression;
    private int position;
    private int counter;

    public Lexer(String arg) {
        position = 0;
        expression = arg;
    }

    public int getPosition() {
        return position;
    }

    public String getCurrent() {
        if (expression.length() > position) {
            if (expression.charAt(position) >= '0' && expression.charAt(position) <= '9') {
                return getNumber();
            }
            return expression.substring(position, position + 1);
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
        if (CurrLexem.charAt(0) >= '0' && CurrLexem.charAt(0) <= '9') {
            return Pair.create(lexem.number, CurrLexem);
        }
        if (CurrLexem.equals("/n")) {
            return Pair.create(lexem.eol, "/n");
        }

        return Pair.create(lexem.unknown, "0");
    }

    public String getNumber() {
        String number = "";
        counter = position;
        while (expression.length() > counter && expression.charAt(counter) >= '0' && expression.charAt(counter) <= '9') {
            number += String.valueOf(expression.charAt(counter));
            counter++;
        }
        return number;
    }

    public void moveNext() {
        if (expression.length() > position) {
            if (expression.charAt(position) <= '0' || expression.charAt(position) >= '9') {
                position++;
            } else {
                while (expression.length() > position &&
                        expression.charAt(position) >= '0' &&
                        expression.charAt(position) <= '9') {
                    position++;
                }
            }
        } else {
            if (expression.length() == position) {
                return;
            } else {
                expression = "/n";
            }
        }
    }
}
