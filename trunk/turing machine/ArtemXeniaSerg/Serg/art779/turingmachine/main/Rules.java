/*
 * @author ksenyiacypan
 * Licensed under the GNU General Public License v2 
 * @copyrights 261studentprojects
 *  
 */

package art779.turingmachine.main;

import java.util.HashMap;

public class Rules {

	public String sState;
	public String fState;
	
	private HashMap<RuleKey, RuleAction> rules = new HashMap<RuleKey, RuleAction>();
	
	Rules(String SState,String FState)
	{
		this.sState = SState;
		this.fState = FState;
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
		/*
		RuleKey vt = new RuleKey(State,Sym);
		return rules.get(vt);
		equals
		hashcode
		*///*
		
		for (RuleKey vt : rules.keySet()) {
			if(vt.getState().equals(State))
				if(vt.getSym().equals(Sym))
					return rules.get(vt);
		}
		return null;
		
	}
	
	@Override
	public String toString()
	{
		String str = "[ {"+ sState + "->" + fState + "}: ";
		for (RuleKey k : rules.keySet()) {
			 RuleAction v = rules.get(k);		 
			 str += k.toString() + "->" + v.toString()+" ";
		}
		str += "]";
		return str;
	}
		
}
