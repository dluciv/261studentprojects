package interpretator;

public class Lexer {

    public String expression;

   public Lexer(String args) {
        expression = args;
    }

    public int getNumber() {
        int i = 0;
        while (expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
            if (i == expression.length() - 1) {
                return Integer.valueOf(expression.substring(0, i + 1));
            }
            i++;
        }
        return Integer.valueOf(expression.substring(0, i));
    }

    public void moveNext() {
//двигается дальше затирая первый символ
        if (expression.length() == 0) {
            expression = "\n";
        }
        int i = 0;
        if (expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
            while (expression.length() != 0 && expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                //случай когда число последнее и за ним ничего нет
                if (i == expression.length() - 1) {
                    expression = expression.substring(i + 1, expression.length());
                    i--;
                }
                i++;
            }
            if (expression.length() != 0) {
                expression = expression.substring(i, expression.length());
            }
        } else {
            expression = expression.substring(1, expression.length());
        }
        if (expression.length() == 0) {
            expression = "\n";
        }
    }

    public Lexems getCurrentSymbol() {
        String currentSymbol = expression.substring(0, 1);
        if (currentSymbol.equals("*")) {
            return Lexems.multiply;
        }
        if (currentSymbol.equals("+")) {
            return Lexems.plus;
        }
        if (currentSymbol.equals("/")) {
            return Lexems.division;
        }
        if (currentSymbol.equals("\n")) {
            return Lexems.EOL;
        }
        if (currentSymbol.equals("-")) {
            return Lexems.minus;
        }
        if (currentSymbol.equals("(")) {
            return Lexems.openBracked;
        }
        if (currentSymbol.equals(")")) {
            return Lexems.closedBracked;
        }
        if (expression.charAt(0) >= '0' && expression.charAt(0) <= '9'){
            return Lexems.number;
        }else{
            return Lexems.unknownSymbol;
        }
    }
}

