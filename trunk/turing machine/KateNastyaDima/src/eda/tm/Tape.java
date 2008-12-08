package eda.tm;

import java.util.Vector;

public class Tape {

	public static final char LEFT = 'L';
	public static final char RIGHT = 'R';
	public static final char STEND = '_';
	public static final char STAR = '*';
	public static final char EMPTY = '^';
 	
	private Vector<Character> tape;
	private int position;
	
	public Tape(Vector<Character> tape) {
		this.tape = tape;
		this.position = 0;
	}
	
	public Vector<Character> getTape() {
		return tape;
	}
	public void setTape(Vector<Character> tape) {
		this.tape = tape;
	}
	
	public void write(char symbol){
		tape.remove(position);
		tape.add(position, symbol);			
	}
	public char returnCurrentSymbol(){
		if(position < 0 || position > tape.size()) return Tape.EMPTY;
		return tape.get(position);	
	}
	public void changePosition(char move){
		if(move == Tape.STEND) position += 0;
		if(move == Tape.RIGHT) position += 1;
		if(move == Tape.LEFT) position -= 1; 
	}
}
