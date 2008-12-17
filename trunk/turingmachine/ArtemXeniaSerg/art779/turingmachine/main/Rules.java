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
		RuleSet k;
		RuleSet v;

		k = new RuleSet(Tape.startSym,"1");
		v = new RuleSet("mr","W","#");
		rules.put(k, v);

		k = new RuleSet(Tape.startSym,"+");
		v = new RuleSet(Tape.startSym,"W","#");
		rules.put(k, v);

		k = new RuleSet(Tape.startSym,"#");
		v = new RuleSet(Tape.startSym,R);
		rules.put(k, v);

		k = new RuleSet("mr","1");
		v = new RuleSet("mr",R);
		rules.put(k, v);

		k = new RuleSet("mr","+");
		v = new RuleSet("mr",R);
		rules.put(k, v);

		k = new RuleSet("mr","#");
		v = new RuleSet("mr",R);
		rules.put(k, v);

		k = new RuleSet("mr",Tape.infinitySym);
		v = new RuleSet("=",W,"=");
		rules.put(k, v);

		k = new RuleSet("=","=");
		v = new RuleSet("=",R);
		rules.put(k, v);

		k = new RuleSet("=",Tape.infinitySym);
		v = new RuleSet("mle",W,"1");
		rules.put(k, v);

		k = new RuleSet("=","1");
		v = new RuleSet("=",R);
		rules.put(k, v);

		k = new RuleSet("mle","1");
		v = new RuleSet("mle",L);
		rules.put(k, v);

		k = new RuleSet("mle","=");
		v = new RuleSet("mlc",L);
		rules.put(k, v);

		k = new RuleSet("mlc","1");
		v = new RuleSet("ml",L);
		rules.put(k, v);

		k = new RuleSet("mlc","#");
		v = new RuleSet(Tape.finalSym,H);
		rules.put(k, v);

		k = new RuleSet("ml","1");
		v = new RuleSet("ml",L);
		rules.put(k, v);

		k = new RuleSet("ml","+");
		v = new RuleSet("ml",L);
		rules.put(k, v);

		k = new RuleSet("ml","#");
		v = new RuleSet(Tape.startSym,R);
		rules.put(k, v);

		k = new RuleSet("mr","=");
		v = new RuleSet("=",R);
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
