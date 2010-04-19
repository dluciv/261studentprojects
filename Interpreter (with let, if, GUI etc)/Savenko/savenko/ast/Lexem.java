package savenko.ast;

import savenko.ast.Lexer.lexems;

public class Lexem {
	
	private int number;
	private String identifier;
	private lexems type;
	private Position position = null;
	
	public Lexem(lexems lexem, Position position){
		type = lexem;
		this.position = position;
	}
	
	public Lexem(lexems lexem,int number, Position position){
		type = lexem;
		this.number = number;
		this.position = position;
	}
	
	public Lexem(lexems lexem,String identifier, Position position){
		type = lexem;
		this.identifier = identifier;
		this.position = position;
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

        public  Position getPosition(){
             return position;
        }

}
