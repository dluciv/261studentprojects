package eda.tm;

import java.util.HashMap;
import java.util.Vector; 

public class Programm {

	private HashMap<StateSymbol, StateSymbolMove> rules;
	private String beginState;
	private String endState;
	
	public Programm(String beginState, String endState) {
		this.beginState = beginState;
		this.endState = endState;
		this.rules = new HashMap<StateSymbol, StateSymbolMove>();
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

	public void addRule(StateSymbol stateSymbol, StateSymbolMove stateSymbolMove){
		rules.put(stateSymbol, stateSymbolMove);
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
	
	public Tape execute(Tape tape){
		StateSymbol start = new StateSymbol(beginState, tape.returnCurrentSymbol());
		return performMove(start, tape);
	}
	
}
