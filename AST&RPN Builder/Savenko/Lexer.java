/*
 * This class get the string from parser and 
 * then necessary tells parser what kind of symbol is the curr symbol &
 * moves right on one symbol then asked
 * Savenko Maria(c)
 */
package savenko;


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
        
        if (CurrLexem.equals("+")) return Pair.create(lexems.Plus,"0");
        if (CurrLexem.equals("-")) return Pair.create(lexems.Minus,"0");
        if (CurrLexem.equals("*")) return Pair.create(lexems.Multiply,"0");
        if (CurrLexem.equals("/")) return Pair.create(lexems.Divide,"0");
        if (CurrLexem.equals("(")) return Pair.create(lexems.LeftBracket,"0");
        if (CurrLexem.equals(")")) return Pair.create(lexems.RightBracket,"0");
        //if (CurrLexem.equals("=")) return Pair.create(lexems.Assigment,"0");
        //if (CurrLexem.equals("print")) return Pair.create(lexems.Assigment,"0");
        if (CurrLexem.charAt(0) >= '0'&&CurrLexem.charAt(0)<='9') return Pair.create(lexems.Number,CurrLexem);
        if (CurrLexem.equals("/n")) return Pair.create(lexems.EOL,"/n");
        
        return Pair.create(lexems.Unknown,"0");
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
