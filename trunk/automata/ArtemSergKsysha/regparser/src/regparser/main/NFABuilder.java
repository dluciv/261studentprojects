package regparser.main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NFABuilder implements INFA {
    public static final int FIRST_STATE = 0;
    public static final int LAST_STATE = 1;
    public static HashMap<Integer, String> alfabett = new HashMap<Integer, String>();
	public HashMap<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
	
	public void add(Integer node, Integer cur) {
		ArrayList<Integer> list;
		if(graph.containsKey(node))
		{
			list = graph.get(node);
		}
		else
		{
			list = new ArrayList<Integer>();
		}		
		
		if(!list.contains(cur))
			list.add(cur);
		graph.put(node, list);
		
	}
    
	public void mergeSequence(NFABuilder mergeWith)
	{
		HashMap<Integer, ArrayList<Integer>> A = graph;
		HashMap<Integer, ArrayList<Integer>> B = mergeWith.graph;
		ArrayList<Integer> endsA = new ArrayList<Integer>();
		for (Integer key : A.keySet())
		{
			ArrayList<Integer> val = A.get(key);
			if(val.contains(LAST_STATE))endsA.add(key);
		}
		ArrayList<Integer> startsB = B.get(FIRST_STATE);
		B.remove(FIRST_STATE);

		for (int i = 0; i < endsA.size(); i++) {
			Integer curN = endsA.get(i);
			ArrayList<Integer> curL= A.get(curN);
			A.remove(curN);
			curL.remove(curL.indexOf(LAST_STATE));
			curL.addAll(startsB);
			for (int j = 0; j < curL.size(); j++) {
				add(endsA.get(i), curL.get(j));
			}
		}
		A.putAll(B);		
	}

	public void mergeParallel(NFABuilder mergeWith)
	{
		HashMap<Integer, ArrayList<Integer>> A = graph;
		HashMap<Integer, ArrayList<Integer>> B = mergeWith.graph;

		ArrayList<Integer> startsB = B.get(FIRST_STATE);
		B.remove(FIRST_STATE);
		
		ArrayList<Integer> startsA = A.get(FIRST_STATE);
		A.remove(FIRST_STATE);
		
		for (int i = 0; i < startsB.size(); i++) {
			startsA.add(startsB.get(i));
		}
		A.put(FIRST_STATE,startsA);
		A.putAll(B);
	}

	public void bNode(Lexer reg) {		//a
		int pointer = reg.getPointer();
		char cur = reg.getCurrent().val;
		
		int shift = FIRST_STATE + LAST_STATE;
		if(shift<0) shift = shift*(-1);
		shift++;

		alfabett.put(pointer+shift, ""+cur);
		
		add(FIRST_STATE,pointer+2);
		add(pointer+2,LAST_STATE);
	}
	
	public void bVopros() {				//a?
		add(FIRST_STATE,LAST_STATE);
	}
	
	public void bZvezda() {				//a*
		bVopros();
		HashMap<Integer, ArrayList<Integer>> A = graph;
		ArrayList<Integer> endsA = new ArrayList<Integer>();
		for (Integer key : A.keySet())
		{
			ArrayList<Integer> val = A.get(key);
			if(val.contains(LAST_STATE) && key != FIRST_STATE)endsA.add(key);
		}
		ArrayList<Integer> startsA = A.get(FIRST_STATE);	
		for (int i = 0; i < endsA.size(); i++) {
			ArrayList<Integer> list = A.get(endsA.get(i));
			A.remove(endsA.get(i));
			list.remove(list.indexOf(LAST_STATE));
			list.addAll(startsA);
			A.put(endsA.get(i), list);
		}
	}
	
	public int getFinalState() {
		return LAST_STATE;
	}

	public List<Integer> getNextState(int State, char ch) {
		return getNextState(State);
	}

	public List<Integer> getNextState(int State) {
		return graph.get(State);
	}

	public int getStartState() {
		return FIRST_STATE;
	}

	public String printGraph() {
		String output = new String();
		output = "";
		NFABuilder.alfabett.put(NFABuilder.FIRST_STATE, "S");
		NFABuilder.alfabett.put(NFABuilder.LAST_STATE, "F");
		
		if(!graph.isEmpty())
		{
			
			for (Integer key : graph.keySet())
			{
				ArrayList<Integer> list = graph.get(key);
				output += NFABuilder.alfabett.get(key);
				output += "=[ ";
				for (int i = 0; i < list.size(); i++) {
					output += NFABuilder.alfabett.get(list.get(i));
					output += " ";
				}
				output += "] ";
			}
		}
		else
		{
			output += "graph is empty";		
		}
		return output; 
	}
}
