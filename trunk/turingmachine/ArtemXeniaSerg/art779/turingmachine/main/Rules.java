package art779.turingmachine.main;

import java.util.HashMap;

public class Rules {

	public static String H = "H"; // �� �������� ��������
	public static String R = "R"; // ��� ������
	public static String L = "L"; // ��� �����
	public static String W = "W"; // ��������
	private HashMap<RuleSet, RuleSet> rules = new HashMap<RuleSet, RuleSet>();
	
	Rules()
	{
		RuleSet k;
		RuleSet v;
		
	}
	
	public RuleSet getRule(String State, String Sym)
	{
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
