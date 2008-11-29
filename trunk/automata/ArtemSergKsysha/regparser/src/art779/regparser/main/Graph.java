package art779.regparser.main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Graph {
    public static final int FIRST_STATE = 0;
    public static final int LAST_STATE = 1;
    public HashMap<Integer, String> alfabett = new HashMap<Integer, String>();
	public HashMap<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
	private Lexer lexer;
	public int nextStateIndex;
	
	Graph(){
		alfabett.put(Graph.FIRST_STATE, "S");
		alfabett.put(Graph.LAST_STATE, "F");
		nextStateIndex = getlStateIndex()+1;
	}
	
	public void addOneStrict(Integer node, Integer cur) {
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
    
	public void mergeSequence(Graph mergeWith)
	{
		alfabett.putAll(mergeWith.alfabett);
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
				addOneStrict(endsA.get(i), curL.get(j));
			}
		}
		A.putAll(B);		
	}

	public void mergeParallel(Graph mergeWith)
	{
		alfabett.putAll(mergeWith.alfabett);
		
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

	public void buildNode(Lexer currentLexem) {		//a
		int pointer = currentLexem.getPointer();
		char cur = currentLexem.getCurrent().val;	
		int stateNum = pointer + getlStateIndex();

		alfabett.put(stateNum, ""+cur);
		addOneStrict(FIRST_STATE,stateNum);
		addOneStrict(stateNum,LAST_STATE);
	}
	
	public void buildVopros() {				//a?
		addOneStrict(FIRST_STATE,LAST_STATE);
	}
	
	public void buildZvezda() {				//a*
		buildVopros();
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
	
	public char getChar(int State) {
		return alfabett.get(State).charAt(0);
	}
	
	public int getFinalState() {
		return LAST_STATE;
	}

	public int getlStateIndex() {
		int preStateCount = FIRST_STATE + LAST_STATE;
		if(preStateCount<0) preStateCount = preStateCount*(-1);
		preStateCount++;
		return preStateCount;
	}	
	
	public ArrayList<Integer> getNextState(int State, char ch) {
		
		List<Integer> nStatesList = getNextState(State);
		
		ArrayList<Integer> nStatesListCh = new ArrayList<Integer>(); 
		
		for (int i = 0; i < nStatesList.size(); i++) {
			if(null!=alfabett.get(nStatesList.get(i)))
			{
				char curChar = alfabett.get(nStatesList.get(i)).charAt(0);
				if(curChar == ch)
				{			
					nStatesListCh.add(nStatesList.get(i));
				}
			}
		}
		return nStatesListCh;
	}
	
	public ArrayList<Integer> getNextState(int State) {
		return graph.get(State);
	}

	public int getStartState() {
		return FIRST_STATE;
	}

	public void addNode(Integer node, List<Integer> list) {
		for (int k = 0; k < list.size(); k++)
			addOne(node,list.get(k));
	}

	public void addOne(int from, int what) {
		ArrayList<Integer> fromStates;
		if(graph.containsKey(from))
		{
			fromStates = getNextState(from);
			graph.remove(from);
			fromStates.add(what);
		}
		else
		{
			fromStates = new ArrayList<Integer> ();
			fromStates.add(what);
		}
		graph.put(from, fromStates);
	}
	
	public void removeNode(int node) {
		if(graph.containsKey(node))
		{
			graph.remove(node);
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

	public void removeFromAlfabett(int node) {
		if(alfabett.containsKey(node))
		{
			alfabett.remove(node);
		}
	}
	
	public boolean isInCycle(int theNode,int curKid,int checkedKid) {
		
		ArrayList<Integer> nStates = getNextState(curKid);
		
		if(nStates != null){
			if(nStates.get(0) == null)
				return false;
			else if(nStates.get(0) == Graph.LAST_STATE)
				return false;
		}else return false;
		
		for (Integer curNode : nStates) {
				if(curNode==theNode)
				{
					System.out.println("cycle found at " + alfabett.get(curNode)+curNode);
					return true;
				}
				else{
					if(checkedKid <= curNode){
						return isInCycle(theNode, curNode, curNode);
					}
				}
			
		}
		return false;
	}

	public boolean determinateNode(int curState, String val) {
		boolean result = false;
		
		ArrayList<Integer> nStates = getNextState(curState,val.charAt(0));
		
		if(nStates.size()>1)
		{
			ArrayList<Integer> nnStates = new ArrayList<Integer> ();
			for (Integer nsval : nStates) {
				nnStates.addAll(getNextState(nsval));
				removeOne(curState, nsval);
				if(!isInCycle(nsval,nsval,0)){
					System.out.println("removed node " + alfabett.get(nsval) + nsval);
					removeNode(nsval);
					removeFromAlfabett(nsval);
					result = true;
				}
			}
			
			int newNodeNum = lexer.getPointer();
			alfabett.put(newNodeNum, val);
			System.out.println("added node " + val + newNodeNum);
			addNode(newNodeNum, nnStates);
			addOne(curState, newNodeNum);
			lexer.incPointer();
		}
		return result;
	}
	
	public void determinateNFA(Lexer lex) {
		lexer = lex;
		determinateBranches(getlStateIndex());
	}

	public void determinateBranches(int index) {
		// *** как получить не ссылки, а копию ?
		Set<Integer> allNodes = alfabett.keySet();
		boolean modified = false;
		
		for (int node : allNodes) {
			if(node >= index){
				ArrayList<Integer> nodeKids = getNextState(node);
				for (Integer nodeKid : nodeKids) {
					if(determinateNode(node, alfabett.get(nodeKid))){
						System.out.println(node + " HAS BEEN DETERMINATED");
						index = node;
						modified = true;
						break;
					}
				}
			}
			if(modified)
				break;
		}
		if(modified)
			determinateBranches(index);
	}
	
	public Graph buildNFA(String regexp) {
		Parser parser = new Parser(regexp);
		Graph NFA = parser.getNFA();
		return NFA;
	}
	
	public Graph buildDFA(String regexp) {
		Parser parser = new Parser(regexp);
		Graph NFA = parser.getNFA();
		Lexer lexer = parser.getLexer();
		NFA.determinateNFA(lexer);
		return NFA;
	}
}
