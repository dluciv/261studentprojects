package eda.tm;

public class StateSymbolMove {

	private String state;
	private char symbol;
	private char move;
	
	public StateSymbolMove(char move, String state, char symbol) {
		this.move = move;
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
	public char getMove() {
		return move;
	}
	public void setMove(char move) {
		this.move = move;
	}
	
	
}
