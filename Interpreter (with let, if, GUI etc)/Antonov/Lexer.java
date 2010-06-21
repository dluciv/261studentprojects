
package LexerAndParser;

public class Lexer {


    //public enum lexems {Plus,Minus,Divide,Multiply,LeftBracket,RightBracket,sign,
    //Number,EOL,IF,ELSE,print,Unknown,equality,semicolon,Or,And,unequality,LE,GE};
    private String expression;

    public Lexer(String args){
        expression = args;
    }
    
    public LexemKind getCurrentSymbol() {
        String currentSymbol = expression.substring(0, 1);


        if (currentSymbol.equals("*")) {
            return LexemKind.Multiply;
        }
        if (currentSymbol.equals("+")) {
            return LexemKind.Plus;
        }
        if (currentSymbol.equals("/")) {
            return LexemKind.Divide;
        }
        if (currentSymbol.equals("=")) {
            return LexemKind.equality;
        }
        if (currentSymbol.equals("\n")) {
            return LexemKind.EOL;
        }
        if (currentSymbol.equals("-")) {
            return LexemKind.Minus;
        }
        if (currentSymbol.equals("(")) {
            return LexemKind.LeftBracket;
        }
        if (currentSymbol.equals(")")) {
            return LexemKind.RightBracket;
        }
        if (currentSymbol.equals(";")) {
            return LexemKind.semicolon;
        }
                
        if (expression.charAt(0) >= '0' && expression.charAt(0) <= '9'){
            return LexemKind.Number;
        }else{
            return LexemKind.Unknown;
        }
    }




    public int getNumber(){
        
        int i = 0;
        while (expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
            if (i == expression.length() - 1) {
                return Integer.valueOf(expression.substring(0, i + 1));
            }
            i++;
        }
        return Integer.valueOf(expression.substring(0, i));

    }

    public void moveNext(){ //двигается дальше затирая первый символ
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
}

