package art779.turingmachine.main;

import java.util.HashMap;

public class Rules {

	public String SState;
	public String FState;
	
	private HashMap<RuleKey, RuleAction> rules = new HashMap<RuleKey, RuleAction>();
	
	
	
	Rules(String SState,String FState)
	{
		this.SState = SState;
		this.FState = FState;
		
		setRule(this.SState,"1","mrgs",Action.W,"#");
		setRule("mrgs","#","mrgs",Action.R);
		setRule("mrgs","1","mr",Action.W,"#");
		setRule("mrgs","+","mrgs",Action.W,"#");
		setRule("mrgs","#","mrgs",Action.R);
		setRule("mr","1","mr",Action.R);
		setRule("mr","+","mr",Action.R);
		setRule("mr","#","mr",Action.R);
		setRule("mr",Tape.infinitySym,"=",Action.W,"=");
		setRule("=","=","=",Action.R);
		setRule("=",Tape.infinitySym,"mle",Action.W,"1");
		setRule("=","1","=",Action.R);
		setRule("mle","1","mle",Action.L);
		setRule("mle","=","mlc",Action.L);
		setRule("mlc","1","ml",Action.L);
		setRule("mlc","#",this.FState,Action.H);
		setRule("ml","1","ml",Action.L);
		setRule("ml","+","ml",Action.L);
		setRule("ml","#","mrgs",Action.R);
		setRule("mr","=","=",Action.R);
	}
	public boolean hasConversionForAlpha(String alpha)
	{
		for (RuleKey vt : rules.keySet()) {
			if(vt.getSym().equals(alpha))
				return true;
		}
		return false;
	}
	public void setRule(String state, String sym, String st, Action op, String val)
	{
		RuleKey k = new RuleKey(state,sym);
		RuleAction v = new RuleAction(st,op,val);
		rules.put(k, v);
	}
	public void setRule(String state, String sym, String st, Action op)
	{
		RuleKey k = new RuleKey(state,sym);
		RuleAction v = new RuleAction(st,op);
		rules.put(k, v);
	}

	public boolean hasRuleForAlpha(String alpha)
	{
		for (RuleKey vt : rules.keySet()) {
			System.out.println(vt);
		}

		return false;
	}
	
	public RuleAction getAct(String State, String Sym)
	{
		//RuleKey vt = new RuleKey(State,Sym);
		//return rules.get(vt);
		///*
		for (RuleKey vt : rules.keySet()) {
			if(vt.getState().equals(State))
				if(vt.getSym().equals(Sym))
					return rules.get(vt);
		}
		return null;
		//*/
	}
	
	public String toString()
	{
		String str = "[";
		for (RuleKey k : rules.keySet()) {
			 RuleAction v = rules.get(k);		 
			 str += k.toString() + "->" + v.toString()+" ";
		}
		str += "]";
		return str;
	}
		
}
