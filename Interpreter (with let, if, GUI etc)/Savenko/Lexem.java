package savenko;

import savenko.Lexer.lexems;

public class Lexem {
	
	private int number;
	private String identifier;
	private lexems type;
	
	public Lexem(lexems lexem){
		type = lexem;
	}
	
	public Lexem(lexems lexem,int new_number){
		type = lexem;
		number = new_number;
	}
	
	public Lexem(lexems lexem,String new_identifier){
		type = lexem;
		identifier = new_identifier;
	}
	
	public lexems getTypeLexem(){
		return type;
	}
	
	public String getStringLexem(){
		return identifier;
	}
	
	public int getIntLexem(){
		return number;
	}

}
