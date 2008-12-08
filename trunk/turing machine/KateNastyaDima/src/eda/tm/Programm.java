package eda.tm;

import java.util.HashMap;
import java.util.Vector; 

public class Programm {

	private HashMap<StateSymbol, StateSymbolMove> rules;
	private String beginState;
	private String endState;
	private Vector<String> alphabet;
	
	public Programm(String beginState) {
		this.beginState = beginState;
	}

	public Programm(Vector<String> alphabet, String beginState,
			String endState, HashMap<StateSymbol, StateSymbolMove> rules) {
		this.alphabet = alphabet;
		this.beginState = beginState;
		this.endState = endState;
		this.rules = rules;
	}

	public HashMap<StateSymbol, StateSymbolMove> getRules() {
		return rules;
	}

	public void setRules(HashMap<StateSymbol, StateSymbolMove> rules) {
		this.rules = rules;
	}

	public String getBeginState() {
		return beginState;
	}

	public void setBeginState(String beginState) {
		this.beginState = beginState;
	}

	public String getEndState() {
		return endState;
	}

	public void setEndState(String endState) {
		this.endState = endState;
	}

	public Vector<String> getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(Vector<String> alphabet) {
		this.alphabet = alphabet;
	}
	
	public Tape performMove(StateSymbol tmp, Tape tape){
		StateSymbolMove nextMove = rules.get(tmp);
		tmp.setState(nextMove.getState());
		tape.write(nextMove.getSymbol());
		tape.changePosition(nextMove.getMove());
		tmp.setSymbol(tape.returnCurrentSymbol());
		if(nextMove.getState() != endState) return performMove(tmp, tape);
		else return tape;
	}
	
	public Tape performProgramm(Tape tape){
		StateSymbol start = new StateSymbol(beginState, tape.returnCurrentSymbol());
		return performMove(start, tape);
	}
	
}
