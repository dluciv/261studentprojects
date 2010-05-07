/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LexerAndParser;

public class Lexer {


    public enum lexems {Plus,Minus,Divide,Multiply,LeftBracket,RightBracket,sign,
    Number,EOL,IF,ELSE,print,Unknown,equality,semicolon};
    private String expression;

    Lexer(String args){
        expression = args;
    }

     private String getCurrentChar(){
        if (expression.length()>0){
            if (expression.charAt(0)>='0'&& expression.charAt(0)<='9')
                return getNumber();
            else return expression.substring(0, 1);
        } else return "/n";
    }
    public Pair<lexems,String> getCurrent(){
        String CurrLexem = getCurrentChar();


        //if(CurrLexem.charAt(0)=='+') return Pair.create(lexems.Plus,"0");
        //if(CurrLexem.charAt(0)=='-') return Pair.create(lexems.Minus,"0");
        //if(CurrLexem.charAt(0)=='*') return Pair.create(lexems.Multiply,"0");
        //if(CurrLexem.charAt(0)=='/') return Pair.create(lexems.Divide,"0");
        //if(CurrLexem.charAt(0)=='(') return Pair.create(lexems.LeftBracket,"0");
        //if(CurrLexem.charAt(0)==')') return Pair.create(lexems.RightBracket,"0");
        //if(CurrLexem.charAt(0)>='0' &&CurrLexem.charAt(0)<='9') return Pair.create(lexems.Number,CurrLexem);
        //if(CurrLexem.equals("/n"))  return Pair.create(lexems.EOL,"/n");
        if (CurrLexem.equals(";")) return Pair.create(lexems.semicolon,"0");
        if (CurrLexem.equals("print") && isPrint()) return Pair.create(lexems.print,"0");
        if (CurrLexem.equals("if") && isIF()) return Pair.create(lexems.IF,"0");
        if (CurrLexem.equals("else") && isELSE()) return Pair.create(lexems.ELSE,"0");
        if (CurrLexem.equals("=")) return Pair.create(lexems.equality,"0");
        if (CurrLexem.equals("+")) return Pair.create(lexems.Plus,"0");
        if (CurrLexem.equals("-")) return Pair.create(lexems.Minus,"0");
        if (CurrLexem.equals("*")) return Pair.create(lexems.Multiply,"0");
        if (CurrLexem.equals("/")) return Pair.create(lexems.Divide,"0");
        if (CurrLexem.equals("(")) return Pair.create(lexems.LeftBracket,"0");
        if (CurrLexem.equals(")")) return Pair.create(lexems.RightBracket,"0");
        if (CurrLexem.charAt(0) >= '0'&&CurrLexem.charAt(0)<='9') return Pair.create(lexems.Number,CurrLexem);
        if (CurrLexem.equals("/n")) return Pair.create(lexems.EOL,"/n");

       return Pair.create(lexems.Unknown,"0");
    }

    public boolean isPrint() {
        String currentWord = expression.substring(0,5);
        if (currentWord.equals("print")) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isIF() {
        String currentWord = expression.substring(0, 2);
        if (currentWord.equals("if")) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isELSE() {
        String currentWord = expression.substring(0, 4);
        if (currentWord.equals("else")) {
            return true;
        } else {
            return false;
        }
    }


    private String getNumber(){
        
        String number = String.valueOf(expression.charAt(0));

        int i = 1;
        while (true) {
            if (expression.length()>i && expression.charAt(i)>='0' && expression.charAt(i)<='9'){
                number += String.valueOf(expression.charAt(i));
                i++;
            } else break;
        }

        return number;
    }

    public void moveNext(){ //двигается дальше затирая первый символ

        String expr = "";

        if (expression.length()>0){
            while (expression.charAt(0)>='0'&&expression.charAt(0)<='9'){
                expr = expression.substring(1);
                expression = expr;
                if (expression.length()==0||expression.charAt(0)<'0'||expression.charAt(0)>'9') return;
            }
            expr = expression.substring(1);
            expression = expr;
        }else expression = "/n";

    }
}

