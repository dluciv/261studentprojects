package eda.tm;

public class StateSymbol {

	private String state;
	private char symbol;
	
	public StateSymbol(String state, char symbol) {
		this.state = state;
		this.symbol = symbol;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public char getSymbol() {
		return symbol;
	}
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
}
