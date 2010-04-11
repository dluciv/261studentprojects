/*
 * This class get the string from parser and 
 * then necessary tells parser what kind of symbol is the curr symbol &
 * moves right on one symbol then asked
 * Savenko Maria(c)
 */
package savenko.ast;
/*
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;*/
import java.util.ArrayList;
import java.util.HashMap;

public class Lexer {
    
    public enum lexems {Plus,Minus,Divide,Multiply,LeftBracket,RightBracket,Number,
                        Identifier,EOF,Unknown, Keyword,
                        IF,THEN,ELSE,LET,IN,BEGIN,END,FUN,PRINT,
                        Semicolon,Equation, LE, GE, Less, Greater};//????
    private ArrayList<Character> expression2;
    private String expression;
    private int curr_index = 0;
    private static HashMap<String, lexems> keywords = new HashMap<String, lexems>() {{
    		put("if",lexems.IF);
    		put("then",lexems.THEN);
    		put("let",lexems.LET);
    		put("else",lexems.ELSE);
    		put("in",lexems.IN);
    		put("begin",lexems.BEGIN);
    		put("fun",lexems.FUN);
    		put("print",lexems.PRINT);
    	}};

    public Lexer(String programm){
        expression = programm;
    }

    /*
    //reads the file -> all symbols one by one adds to the "expression" list
    public Lexer(String filename){
    	BufferedReader in = null;
    	try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
    	expression = new ArrayList<Character>();
    	try {
               while (in.ready()) {
                        char charac =  (char)in.read();
			//exclude all white spaces & chars of EOL  
			//if (charac!=' ' && charac!='\r' && charac!='\n') expression.add(charac);
			expression.add(charac);
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	finally {
		//in.close();
	}
    }

    public Lexer(ArrayList<Character> programm){
        expression = programm;
    }*/
    
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
    
    public void moveNext(){
        char currChar = ' ';
        if (curr_index < expression.length()){
            currChar = expression.charAt(curr_index);
        
        while (currChar == ' ' || currChar == '\r' || currChar == '\n') {
        	curr_index++;
        	currChar =  expression.charAt(curr_index);
        }
        }
        
        switch (currChar) {
        case '+': currLexem = new Lexem(lexems.Plus,'+'); curr_index++; break;        
        case '-': currLexem = new Lexem(lexems.Minus,'-');curr_index++; break;
    	case '*': currLexem = new Lexem(lexems.Multiply,'*');curr_index++; break;
    	case '/': currLexem = new Lexem(lexems.Divide,'/');curr_index++; break;
    	case '(': currLexem = new Lexem(lexems.LeftBracket,'(');curr_index++; break;
    	case ')': currLexem = new Lexem(lexems.RightBracket,')');curr_index++; break;
    	case '=': currLexem = new Lexem(lexems.Equation,'='); curr_index++; break;
    	case ';': currLexem = new Lexem(lexems.Semicolon,';');curr_index++; break;
    	//---------------------------Think About It!-----------------------
    	//if (currChar.equals('<')) return new Lexem(lexems.Greater,'<');
    	//if (currChar.equals('>')) return new Lexem(lexems.Less,'>');
    	default:
	        if (ifEOF()) {
	        	currLexem = new Lexem(lexems.EOF,'0');
	        }else 
	        if (ifNumber()){
	        	currLexem = new Lexem(lexems.Number,getNumber());
	        }else
	        
	        if (ifIdentifier()){
	        	String ident = getIdentifier();
	        	if (ifKeyword(ident)){
	        		currLexem = new Lexem(keywords.get(ident),ident);
	        	}else 
	        		currLexem = new Lexem(lexems.Identifier,ident);
	        }else
	        	currLexem = new Lexem(lexems.Unknown,'0');
        }

    }

}
