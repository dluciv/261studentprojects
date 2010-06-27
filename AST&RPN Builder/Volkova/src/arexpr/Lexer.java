/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arexpr;

/**
 *
 * @author kate
 */
public class Lexer {

public enum lexems {Plus,Minus,Divide,Multiply,LeftBracket,RightBracket,Number,Assigment,EOL,Unknown};
    private String expression;

    Lexer(String args){
        expression = args;
    }

    private String getCurrentChar(){
        if (expression.length()>0){
            if (expression.charAt(0)>='0'&&expression.charAt(0)<='9') return getNumber();
            else return expression.substring(0, 1);
        } else return "/n";
    }

    public Pair<lexems,String> getCurrent(){
        String CurrLexem = getCurrentChar();

        if (CurrLexem.equals("+")) return new Pair<lexems, String>(lexems.Plus,"0");
        if (CurrLexem.equals("-")) return new Pair<lexems, String>(lexems.Minus,"0");
        if (CurrLexem.equals("*")) return new Pair<lexems, String>(lexems.Multiply,"0");
        if (CurrLexem.equals("/")) return new Pair<lexems, String>(lexems.Divide,"0");
        if (CurrLexem.equals("(")) return new Pair<lexems, String>(lexems.LeftBracket,"0");
        if (CurrLexem.equals(")")) return new Pair<lexems, String>(lexems.RightBracket,"0");
        //if (CurrLexem.equals("=")) return new Pair<lexems, String>(lexems.Assigment,"0");
        //if (CurrLexem.equals("print")) return new Pair<lexems, String>(lexems.Assigment,"0");
        if (CurrLexem.charAt(0) >= '0'&&CurrLexem.charAt(0)<='9') return new Pair<lexems, String>(lexems.Number,CurrLexem);
        if (CurrLexem.equals("/n")) return new Pair<lexems, String>(lexems.EOL,"/n");

        return new Pair<lexems, String>(lexems.Unknown,"0");
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

    public void moveNext(){
        String expr = "";

        if (expression.length()>0){
            while (expression.charAt(0)>='0'&&expression.charAt(0)<='9'){
                expr = expression.substring(1);
                expression = expr;   //??? maybe not important
                if (expression.length()==0||expression.charAt(0)<'0'||expression.charAt(0)>'9') return;
            }
            expr = expression.substring(1);
            expression = expr;
        }else expression = "/n";

    }

}


