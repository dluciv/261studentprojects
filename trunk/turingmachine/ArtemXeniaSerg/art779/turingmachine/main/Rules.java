package art779.turingmachine.main;

import java.util.HashMap;

public class Rules {

	public static String H = "H"; // не выполяем дейстиве
	public static String R = "R"; // шаг вправо
	public static String L = "L"; // шаг влево
	public static String W = "W"; // записать
	private HashMap<RuleSet, RuleSet> rules = new HashMap<RuleSet, RuleSet>();
	
	Rules()
	{
		setRule(Tape.startSym,"1","mr","W","#");
		setRule(Tape.startSym,"+",Tape.startSym,"W","#");
		setRule(Tape.startSym,"#",Tape.startSym,R);
		setRule("mr","1","mr",R);
		setRule("mr","+","mr",R);
		setRule("mr","#","mr",R);
		setRule("mr",Tape.infinitySym,"=",W,"=");
		setRule("=","=","=",R);
		setRule("=",Tape.infinitySym,"mle",W,"1");
		setRule("=","1","=",R);
		setRule("mle","1","mle",L);
		setRule("mle","=","mlc",L);
		setRule("mlc","1","ml",L);
		setRule("mlc","#",Tape.finalSym,H);
		setRule("ml","1","ml",L);
		setRule("ml","+","ml",L);
		setRule("ml","#",Tape.startSym,R);
		setRule("mr","=","=",R);
	}
	
	public void setRule(String state, String sym, String st, String op, String val)
	{
		RuleSet k = new RuleSet(state,sym);
		RuleSet v = new RuleSet(st,op,val);
		rules.put(k, v);
	}
	public void setRule(String state, String sym, String st, String op)
	{
		RuleSet k = new RuleSet(state,sym);
		RuleSet v = new RuleSet(st,op);
		rules.put(k, v);
	}

	
	
	public RuleSet getRule(String State, String Sym)
	{
		for (RuleSet vt : rules.keySet()) {
			if(vt.getState().equals(State))
				if(vt.getSym().equals(Sym))
					return rules.get(vt);
		}
		return null;
	}
	
	public String toString()
	{
		String str = "[";
		for (RuleSet k : rules.keySet()) {
			 RuleSet v = rules.get(k);		 
			 str += k.toString() + "->" + v.toString()+" ";
		}
		str += "]";
		return str;
	}
		
}
