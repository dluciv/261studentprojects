package art779.regparser.main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class NFABuilder implements INFA {
    public static final int FIRST_STATE = 0;
    public static final int LAST_STATE = 1;
    public static HashMap<Integer, String> alfabett = new HashMap<Integer, String>();
	public HashMap<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
	
	NFABuilder(){
		alfabett.put(NFABuilder.FIRST_STATE, "S");
		alfabett.put(NFABuilder.LAST_STATE, "F");
	}
	
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
		
		add(FIRST_STATE,pointer+shift);
		add(pointer+shift,LAST_STATE);
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
	
	public char getCurChar(int State) {
		return alfabett.get(State).charAt(0);
	}
	
	public int getFinalState() {
		return LAST_STATE;
	}

	public List<Integer> getNextState(int State, char ch) {
		List<Integer> nStatesList = getNextState(State);
		for (int i = 0; i < nStatesList.size(); i++) {
			char curChar = alfabett.get(nStatesList.get(i)).charAt(0);
			if(curChar == ch)
			{			
				return graph.get(nStatesList.get(i));
			}
		}
		return null;
	}
	
	public List<Integer> getNextState(int State) {
		return graph.get(State);
	}

	public int getStartState() {
		return FIRST_STATE;
	}

	public void addNode(Integer node, List<Integer> list) {
		for (int k = 0; k < list.size(); k++)
			add(node,list.get(k));
	}
	
	public void removeNode(int node) {
		if(graph.containsKey(node))
		{
			graph.remove(node);
			//alfabett.remove(node);
		}
	}
	
	public void removeOne(int from, int what) {
		if(graph.containsKey(from))
		{
			List<Integer> fromStates = getNextState(from);
			graph.remove(from);
			fromStates.remove(fromStates.indexOf(what));
			addNode(from, fromStates);
		}
	}
	
	public void determinateNode(int curState, int curOne) {
		List<Integer> nStates = getNextState(curState);	
		char chA = getCurChar(nStates.get(curOne));
		
		for (int j = curOne+1; j < nStates.size(); j++) {
			char chB = getCurChar(nStates.get(j));
			if(chB == chA)
			{
				List<Integer> nnStates = getNextState(nStates.get(j));
				addNode(nStates.get(curOne), nnStates);
				int removeNode = nStates.get(j);
				removeOne(curState, removeNode);
				removeNode(removeNode);
			}
		}
		if(curOne+1 < nStates.size())
		{
			curOne++;
			determinateNode(curState, curOne);
		}
	}
	
	public void determinateNFA() {	
		Set<Integer> nodes = alfabett.keySet(); 
		for (Integer curNode : nodes) {
			if(graph.containsKey(curNode))
				determinateNode(curNode, 0);
		}
	}

}
