package arexprnew;

/**
 *
 * @author kate
 */
public class Lexer {

    private String expression;
    private int deletedCharsCount = 0;

    public Lexer(String args) {
        expression = args.replaceAll(" ", "");
    }

    public int getDeletedCharsCount() {
        return deletedCharsCount;
    }

    public Lexem getCurrentLexem() {
        if (!hasNext()) {
            return new Lexem(Lexem.Operation.END);
        }

        String currentLexem = getCurrentChar();

        if (currentLexem.equals("+")) {
            return new Lexem(Lexem.Operation.Plus);
        }
        if (currentLexem.equals("-")) {
            return new Lexem(Lexem.Operation.Minus);
        }
        if (currentLexem.equals("*")) {
            return new Lexem(Lexem.Operation.Multiply);
        }
        if (currentLexem.equals("/")) {
            return new Lexem(Lexem.Operation.Divide);
        }
        if (currentLexem.equals("(")) {
            return new Lexem(Lexem.Operation.LeftBracket);
        }
        if (currentLexem.equals(")")) {
            return new Lexem(Lexem.Operation.RightBracket);
        }

        return new Lexem(Integer.parseInt(currentLexem));
    }

    public void next() {
        deletedCharsCount += getCurrentChar().length();
        expression = expression.substring(getCurrentChar().length());
    }

    public boolean hasNext() {
        return !expression.isEmpty();
    }

    private String getNumber() {//get element by
        String number = String.valueOf(expression.charAt(0));

        int i = 1;
        while (true) {
            if (expression.length() > i && expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                number += String.valueOf(expression.charAt(i));
                i++;
            } else {
                break;
            }
        }

        return number;
    }

    private String getCurrentChar() {
        if (expression.length() > 0) {
            if (expression.charAt(0) >= '0' && expression.charAt(0) <= '9') {
                return getNumber();
            } else {
                return "" + expression.charAt(0);
            }
        } else {
            return "\n";
        }
    }
}


