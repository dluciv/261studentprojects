package j2se.g261.eda.automator.minimization;

/**
*
* @author dmitriy
*/
public class Couple {
	private char first;	
	private char second;
	
	public Couple (char a,char b ){
		this.first = a;
		this.second = b;
	}
	
	public char getFirst(){
		return this.first;
	}
	
	public char getSecond(){
		return this.second;
	}
	
	public void setFirst(char a){
		this.first = a;
	}
	
	public void setSecond(char b){
		this.second = b;
	}
	
}

