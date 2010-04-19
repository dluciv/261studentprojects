/*
 * This class get the string from parser and 
 * then necessary tells parser what kind of symbol is the curr symbol &
 * moves right on one symbol then asked
 * Savenko Maria(c)
 */
package savenko.ast;

import java.util.HashMap;

public class Lexer {
    
    public enum lexems {Plus,Minus,Divide,Multiply,LeftBracket,RightBracket,Number,
                        Identifier,EOF,Unknown, Keyword,
                        IF,THEN,ELSE,LET,IN,BEGIN,END,FUN,PRINT,
                        Semicolon,Equation,
                        AND, OR, NO, LE, GE, LESS, GREATER, UNEQUAL};
    private String expression;
    private int curr_index = 0;
    private int old_index = 0 ;
    private int row_num = 0;
    private int col_num = 0;
    private static HashMap<String, lexems> keywords = new HashMap<String, lexems>() {{
    		put("if",lexems.IF);
    		put("then",lexems.THEN);
                put("else",lexems.ELSE);
    		put("let",lexems.LET);
    		put("in",lexems.IN);
    		put("begin",lexems.BEGIN);
                put("end",lexems.END);
    		put("fun",lexems.FUN);
    		put("print",lexems.PRINT);
                put("&&",lexems.AND);
                put("||",lexems.OR);
                put("!",lexems.NO);
                put(">",lexems.GREATER);
                put("<",lexems.LESS);
                put(">=",lexems.GE);
                put("<=",lexems.LE);
                put("<>",lexems.UNEQUAL);
    	}};

    public Lexer(String programm){
        expression = programm;
    }
    
    public Lexem getCurrent(){
    	return currLexem;
    }
    
    private boolean ifKeyword(String keyword){        
        return (keywords.containsKey(keyword));
    }
    
    private boolean ifIdentifier(){
        return (expression.charAt(curr_index) >= 'A' && expression.charAt(curr_index) <= 'z');
    }
    
    private boolean ifNumber(){
    	return (expression.charAt(curr_index)>='0'&&expression.charAt(curr_index)<='9');
    }
    
    private boolean ifEOF(){
    	return (curr_index == expression.length());
    }
    
    private String getIdentifier(){
        String identifier = String.valueOf(expression.charAt(curr_index));
        
        curr_index++;
        while (true) {
            if (expression.length()>curr_index && expression.charAt(curr_index)>= 'A' && expression.charAt(curr_index) <= 'z'){
                identifier += String.valueOf(expression.charAt(curr_index));
                curr_index++;
            } else break;
        }
        
        return identifier;
    }
    
    private Integer getNumber(){  
        String number = String.valueOf(expression.charAt(curr_index));
        
        curr_index++;
        while (true) {
            if (expression.length()>curr_index && expression.charAt(curr_index)>='0' && expression.charAt(curr_index)<='9'){
                number += String.valueOf(expression.charAt(curr_index));
                curr_index++;
            } else break;
        }
        
        return Integer.parseInt(number);
    }
    
    private Lexem currLexem;
    
    private Position getPosition(){
    	return new Position(col_num ,row_num, curr_index, curr_index - old_index);
    }
    
    public void moveNext(){    	
        char currChar = ' ';

        if (curr_index < expression.length()){
            currChar = expression.charAt(curr_index);
        
        while (currChar == ' ' || currChar == '\r' || currChar == '\n') {
        	curr_index++;
        	if (currChar == '\n'){
        		col_num = 0;
        		row_num++;
        	}else col_num++;
        	currChar =  expression.charAt(curr_index);
        }
        }

        old_index = curr_index;
        
        switch (currChar) {
        case '+': currLexem = new Lexem(lexems.Plus,'+', getPosition()); curr_index++; break;        
        case '-': currLexem = new Lexem(lexems.Minus,'-', getPosition());curr_index++; break;
    	case '*': currLexem = new Lexem(lexems.Multiply,'*', getPosition());curr_index++; break;
    	case '/': currLexem = new Lexem(lexems.Divide,'/', getPosition());curr_index++; break;
    	case '(': currLexem = new Lexem(lexems.LeftBracket,'(', getPosition());curr_index++; break;
    	case ')': currLexem = new Lexem(lexems.RightBracket,')', getPosition());curr_index++; break;
    	case '=': currLexem = new Lexem(lexems.Equation,'=', getPosition()); curr_index++; break;
    	case ';': currLexem = new Lexem(lexems.Semicolon,';', getPosition());curr_index++; break;
    	//---------------------------Think About It!-----------------------
    	//if (currChar.equals('<')) return new Lexem(lexems.Greater,'<');
    	//if (currChar.equals('>')) return new Lexem(lexems.Less,'>');
    	default:
	        if (ifEOF()) {
	        	currLexem = new Lexem(lexems.EOF,'0', getPosition());
	        }else 
	        if (ifNumber()){
	        	currLexem = new Lexem(lexems.Number,getNumber(),getPosition());
	        }else
	        
	        if (ifIdentifier()){
	        	String identifier = getIdentifier();
	        	if (ifKeyword(identifier)){
	        		currLexem = new Lexem(keywords.get(identifier),identifier, getPosition());
	        	}else 
	        		currLexem = new Lexem(lexems.Identifier,identifier, getPosition());
	        }else
	        	currLexem = new Lexem(lexems.Unknown,'0',getPosition());
        }

    }

}
