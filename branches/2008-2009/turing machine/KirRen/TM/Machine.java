package TM;

import java.util.HashMap;
import java.util.Vector;

class InitCondition {
	Integer state;
	String c;
	
	public InitCondition(Integer state, String c) {
		this.state = state;
		this.c = c;
	}
	
	public void out() {
		System.out.println("["+state+", "+c+"]");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (state == null) {
			return false;
		}
		if (this.getClass() != obj.getClass())
		{
			return false;
		}
		InitCondition a = (InitCondition) obj;
		return (a.state.equals(this.state) && a.c.equals(this.c));
	}
	
	@Override
	public int hashCode() {
		int hash = 3;
		hash = 73 * hash + (this.state != null ? (int)this.state : 0);
		hash = 73 * hash + this.c.hashCode();
		return hash;
	}
}

class Transition {
	Integer state;
	String c;
	char d; // 'L', 'H', 'R'
	
	public Transition(Integer state, String c, char d) throws MachineException {
		if (d != 'L' && d != 'H' && d != 'R') {
			throw new MachineException("Wrong direction '"+d+"'. Should be 'L', 'H' or 'R'");
		}
		
		this.state = state;
		this.c = c;
		this.d = d;
	}
	
	public void out() {
		System.out.println("["+state+", "+c+", "+d+"]");
	}
}

public class Machine {
	HashMap<InitCondition, Transition> rules = new HashMap<InitCondition, Transition>();
	Vector<String> tape = new Vector<String>();
	Integer first, fin;
	Integer pos;
	
	public void Machine() {
	}
	
	public void go() {
		try {
			this.tape.insertElementAt("_", 0);
			this.pos = 0;
			InitCondition cur = new InitCondition(this.first, this.tape.get(this.pos));
			Integer lev = 0;
			while (cur.state != this.fin)
			{
				this.tapeOut();
				Transition next = this.rules.get(cur);
				
				if (next == null) {
					throw new MachineException("Nothing to do");
				}
				
				this.tape.set(this.pos, next.c);
				this.move(next.d);
				cur.state = next.state;
				cur.c = this.tape.get(this.pos);
				
				++lev;
				if (lev > 5000) {
					System.out.println("Broken");
					break;
				}
			}
			this.tapeOut();
		} catch (MachineException e) {
			System.out.println("Error: "+e.getMessage());
		}
	}
	
	public void addRule(Integer state, String read, Integer next, String write, char move) throws MachineException {
		this.rules.put(new InitCondition(state, read), new Transition(next, write, move));
	}
	
	public void setStringTape(String tape) { // _11_111
		for(int i=0; i<tape.length(); i++) {
			this.tape.add(""+tape.charAt(i));
		}
	}
	
	public void setPos(Integer pos) {
		this.pos = pos;
	}
	
	public void setFirst(Integer first) {
		this.first = first;
	}
	
	public void setFin(Integer fin) {
		this.fin = fin;
	}
	
	private void move(char d) throws MachineException {
		if (d == 'R') {
			++this.pos;
			if (this.pos == this.tape.size()) {
				this.tape.add("_");
			}
		} else if (d == 'L') {
			--this.pos;
			if (this.pos == -1) {
				this.tape.insertElementAt("_", 0);
				++this.pos;
			}
		} else if (d != 'H') {
			throw new MachineException("Wrong direction '"+d+"'. Should be 'L', 'H' or 'R'");
		}
	}
	
	private void tapeOut() {
		for(String s: this.tape) {
			System.out.print(s);
		}
		System.out.println();
		int i = 0;
		for(String s: this.tape) {
			System.out.print(this.pos == i ? "^" : " ");
			i++;
		}
		System.out.println();
	}
	
	private void rulesOut() {
		for(InitCondition c: this.rules.keySet()) {
			System.out.println("["+c.state+", "+c.c+"] -> ["+rules.get(c).state+", "+rules.get(c).c+", "+rules.get(c).d+"]");
		}
	}
}