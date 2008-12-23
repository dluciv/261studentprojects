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
		if(nStatesList == null)
		{
			return nStatesListCh;
		}
		
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
	public Graph buildDFAM(String regexp) {
		Parser parser = new Parser(regexp);
		Graph NFA = parser.getNFA();
		Lexer lexer = parser.getLexer();
		NFA.determinateNFA(lexer);
		NFA.minimizeDFA();
		return NFA;
	}
	//////////////////////////////////////////////////////////////////////////////////////
	//////MINIMIZING//////MINIMIZING//////MINIMIZING//////MINIMIZING//////MINIMIZING//////
	//////////////////////////////////////////////////////////////////////////////////////	
	public HashMap<Integer, ArrayList<Integer>> revgraph = new HashMap<Integer, ArrayList<Integer>>();
	public HashMap<Integer, ArrayList<Integer>> NonEqualPares = new HashMap<Integer, ArrayList<Integer>>();
	public HashMap<Integer, ArrayList<Integer>> NewFront = new HashMap<Integer, ArrayList<Integer>>();
	public HashMap<Integer, ArrayList<Integer>> NextFront = new HashMap<Integer, ArrayList<Integer>>();
	public HashMap<Integer, ArrayList<Integer>> EqualPares = new HashMap<Integer, ArrayList<Integer>>();
	public HashMap<Integer, Integer> classes = new HashMap<Integer, Integer>();
	public int h = 0;
	
	
	
	public ArrayList<Integer> getNextSt(int State, char ch) {
		
		List<Integer> nStatesList = getNextState(State);
		
		ArrayList<Integer> nStatesListCh = new ArrayList<Integer>(); 
		if(nStatesList == null)
		{
			return null;
		}
		
		for (int i = 0; i < nStatesList.size(); i++) {
			if(null!=alfabett.get(nStatesList.get(i)))
			{
				char curChar = alfabett.get(nStatesList.get(i)).charAt(0);
				if(curChar == ch)
				{			
					nStatesListCh.add(nStatesList.get(i));
					return nStatesListCh;
				}
			}
		}
		return null;
	}
	
	
	public void MaxStId(){
		int id = 0;
		Set<Integer> allNodes = alfabett.keySet();	
		for (int innode : allNodes) {
			if (innode > id) {
				id = innode;
			}
		}
		h = id + 2;
	}
	
	public void FirstSteps() {
		Set<Integer> allNodes = alfabett.keySet();	
		for (int innode : allNodes) {
			if (innode != getFinalState() && innode != h) {
				addOneInPares( getFinalState(), innode );				
			}
			if (innode != h ) {				
				addOneInNewFront( h, innode );
			}
		}
	}
	
	
	
	
	public ArrayList<Integer> getStates(int State) {
		return revgraph.get(State);
	}
	
	
	public void addOneInRev(int in, int what) {
		ArrayList<Integer> fromStates;
		if(revgraph.containsKey(in))
		{
			fromStates = getStates(in);
			revgraph.remove(in);
			fromStates.add(what);
		}
		else
		{
			fromStates = new ArrayList<Integer> ();
			fromStates.add(what);
		}
		revgraph.put(in, fromStates);
	}
	
	
	
	public void addOneInPares(int in, int what) {
		ArrayList<Integer> States = new ArrayList<Integer> ();		
		int flag2 = 0;
		int flag1 = 0;
		if(NonEqualPares.containsKey(what)) {
			ArrayList<Integer> stlst = new ArrayList<Integer>();
			stlst = NonEqualPares.get(what);
			for (int st : stlst) {
				if( st == in ) {
					flag1 = 1;
				}
			}				
		}		
		if(NonEqualPares.containsKey(in))
		{
			
			if( flag1 == 0){
				States = NonEqualPares.get(in);
				NonEqualPares.remove(in);				
				for (int st : States) {
					if( st == what ) {
						flag2 = 1;
					}
				}				
				if(flag2 == 0){
					States.add(what);		
				}				
				NonEqualPares.put(in, States);
			}	
		}
		else 
		{		
			if( flag1 == 0){
				//States = new ArrayList<Integer> ();
				States.add(what);
				NonEqualPares.put(in, States);
			}			
		}		
	}
	
	public void addOneWithoutRep(int in, int what) {
		ArrayList<Integer> States = new ArrayList<Integer> ();		
		int flag2 = 0;				
		if(graph.containsKey(in))
		{			
			States = graph.get(in);
			graph.remove(in);				
			for (int st : States) {
				if( st == what ) {
					flag2 = 1;
				}
			}				
			if(flag2 == 0){
				States.add(what);		
			}				
			graph.put(in, States);				
		}
		else 
		{		
			States.add(what);
			graph.put(in, States);					
		}		
	}
	
	public void addOneInEqualPares(int in, int what) {
		ArrayList<Integer> States = new ArrayList<Integer> ();		
		int flag2 = 0;
		int flag1 = 0;
		if(EqualPares.containsKey(what)) {
			ArrayList<Integer> stlst = new ArrayList<Integer>();
			stlst = EqualPares.get(what);
			for (int st : stlst) {
				if( st == in ) {
					flag1 = 1;
				}
			}				
		}		
		if(EqualPares.containsKey(in))
		{
			
			if( flag1 == 0){
				States = EqualPares.get(in);
				EqualPares.remove(in);				
				for (int st : States) {
					if( st == what ) {
						flag2 = 1;
					}
				}				
				if(flag2 == 0){
					States.add(what);		
				}				
				EqualPares.put(in, States);
			}	
		}
		else 
		{		
			if( flag1 == 0){
				//States = new ArrayList<Integer> ();
				States.add(what);
				EqualPares.put(in, States);
			}			
		}		
	}
	
	public void addInEqualPares(int in, int what) {
		ArrayList<Integer> States = new ArrayList<Integer> ();		
		int flag2 = 0;
		int flag1 = 0;
		if(EqualPares.containsKey(what)) {
			ArrayList<Integer> stlst = new ArrayList<Integer>();
			stlst = EqualPares.get(what);
			for (int st : stlst) {
				if( st == in ) {
					flag1 = 1;
				}
			}				
		}		
		if(EqualPares.containsKey(in))
		{
			
			if( flag1 == 0){
				States = EqualPares.get(in);
				EqualPares.remove(in);				
				for (int st : States) {
					if( st == what ) {
						flag2 = 1;
					}
				}				
				if(flag2 == 0){
					States.add(what);		
				}				
				EqualPares.put(in, States);
			}	
		}
		else 
		{		
			if( flag1 == 0){
				//States = new ArrayList<Integer> ();
				States.add(what);
				EqualPares.put(in, States);
			}			
		}		
	}
	
	
	public void addOneInNewFront(int in, int what) {
		ArrayList<Integer> States = new ArrayList<Integer> ();		
		int flag2 = 0;
		int flag1 = 0;
		if(NewFront.containsKey(what)) {
			ArrayList<Integer> stlst = new ArrayList<Integer>();
			stlst = NewFront.get(what);
			for (int st : stlst) {
				if( st == in ) {
					flag1 = 1;
				}
			}				
		}	
		
		if(NewFront.containsKey(in))
		{
			
			if( flag1 == 0){
				States = NewFront.get(in);
				NewFront.remove(in);				
				for (int st : States) {
					if( st == what ) {
						flag2 = 1;
					}
				}				
				if(flag2 == 0){
					States.add(what);					
				}
				NewFront.put(in, States);
			}	
		}
		else 
		{		
			if( flag1 == 0){
				//States = new ArrayList<Integer> ();
				States.add(what);
				NewFront.put(in, States);
			}			
		}		
	}
	
	public void addOneInNextFront(int in, int what) {
		ArrayList<Integer> States = new ArrayList<Integer> ();		
		int flag2 = 0;
		int flag1 = 0;
		if(NextFront.containsKey(what)) {
			ArrayList<Integer> stlst = new ArrayList<Integer>();
			stlst = NextFront.get(what);
			for (int st : stlst) {
				if( st == in ) {
					flag1 = 1;
				}
			}				
		}		
		if(NextFront.containsKey(in))
		{
			
			if( flag1 == 0){
				States = NextFront.get(in);
				NextFront.remove(in);				
				for (int st : States) {
					if( st == what ) {
						flag2 = 1;
					}
				}				
				if(flag2 == 0){
					States.add(what);					
				}
				NextFront.put(in, States);
			}	
		}
		else 
		{		
			if( flag1 == 0){
				//States = new ArrayList<Integer> ();
				States.add(what);
				NextFront.put(in, States);
			}			
		}		
	}

	
	public void BuildRev() {		
		Set<Integer> allNodes = alfabett.keySet();	
		for (int innode : allNodes) {
			for (int fromnode : allNodes) {
				ArrayList<Integer> Kids = getNextState(fromnode);
				if ( Kids != null ) {
					for (Integer Kid : Kids) {
						if (Kid == innode) {
							addOneInRev(innode, fromnode);
						}
					}
				}	
			}
		}		
	}
	
	public void FrontResolve() {
		int flag = 0;
		NextFront.clear();
		Set<Integer> allNodes = NewFront.keySet();	
		for (int keynode : allNodes) {
			ArrayList<Integer> allvalues = NewFront.get(keynode);
			for (int valnode : allvalues) {
				if ( keynode == h ) {
					if( revgraph.get(valnode) != null ){
						for (int node1 : revgraph.get(valnode)) {
							for (int node2 : alfabett.keySet()) {
								flag = 0;
								if(getNextSt(node2, alfabett.get(valnode).charAt(0)) != null) {
									flag = 1;				
								}	
								if(flag == 0) {
									addOneInNextFront(node1, node2);
									addOneInPares(node1, node2);
									//System.out.print(NonEqualPares.size() + "\n");
								}
							}
						}
					}
				}
				if (alfabett.get(keynode) == alfabett.get(valnode)) {
					if( revgraph.get(keynode) != null && revgraph.get(valnode) != null ){
						for (int node1 : revgraph.get(keynode)) {
							for (int node2 : revgraph.get(valnode)) {
								addOneInNextFront(node1, node2);
								addOneInPares(node1, node2);
								//System.out.print(NonEqualPares.size() + "\n");
							}
						}
					}
				}
			}
		}
		NewFront = NextFront;
	}
	
	public void delEqualStFromGraph() {			
		for(int key : EqualPares. keySet()) {			
			if(EqualPares.get(key).size() == 0) {
				continue;
			}
			for(int val : EqualPares.get(key)) {
				for(int grkey : alfabett.keySet()) {
					if(graph.get(grkey) != null) {
						for(int grval : graph.get(grkey)) {
							if(grval == val) {
								removeOne(grkey, grval);
								addOneWithoutRep(grkey,key);
								break;
							}
						}
					}	
				}
				for(int arrow : graph.get(val)) {
					addOneWithoutRep(key,arrow);
				}
				graph.remove(val);
			}			
		}	
	}
	
	public void transClose() {
		int maxclass = 0;
		for(int key : EqualPares. keySet()) {			
			if(EqualPares.get(key).size() == 0) {
				continue;
			}
			if(classes.get(key) == null) {
				maxclass += 1;
				classes.put(key, maxclass);
			}
			for (int values : EqualPares.get(key)) {				
				for (int fkey : EqualPares.keySet()) {
					if(classes.get(fkey) != null) {
						continue;
					}
					for (int fvalues : EqualPares.get(fkey)) {
						if(key == fvalues || values == fvalues || values == fkey) {
							classes.put(fkey, maxclass);
						}
					}
				}				
			}
		}
		
		for(int key : classes. keySet()) {
			if(classes.get(key) == h) {
				continue;
			}
			for(int key1 : classes. keySet()) {
				if(key1 == key || classes.get(key) == h) {
					continue;
				}
				if(classes.get(key) == classes.get(key1)) {
					addOneInEqualPares(key, key1);
					for(int element : EqualPares.get(key1)) {
						addOneInEqualPares(key, element);
					}
					EqualPares.remove(key1);
					classes.put(key1, h);
				}
			}
		}
	}
	
	public void getEqualPares() {
		ArrayList<Integer> States = new ArrayList<Integer> ();		
		for(int key : alfabett.keySet() ) {
			for(int val : alfabett.keySet() ) {
				if(key != val) {
					addInEqualPares(key, val);
				}	
			}
		}
		for (int key : NonEqualPares.keySet()) {
			for (int val : NonEqualPares.get(key)) {
				if(EqualPares.get(key) != null) {
					int element = -1;
					for (int value : EqualPares.get(key)) {
						if(val == value) {							
							element++;
							States = EqualPares.get(key);
							EqualPares.remove(key);				
							States.remove(element);							
							EqualPares.put(key, States);
							break;
						}
						element++;
					}						
				}		
				
				if(EqualPares.get(val) != null) {
					int element = -1;
					for (int value : EqualPares.get(val)) {
						if(key == value) {							
							element++;
							States = EqualPares.get(val);
							EqualPares.remove(val);				
							States.remove(element);							
							EqualPares.put(val, States);
							break;
						}
						element++;
					}					
				}				
			}			
		}		
	}
	
	
	public void minimizeDFA() {
		for(int key : graph. keySet()) {
			for(int val : graph.get(key)) {
				System.out.print(key + "-" + val + "\n");
			}	
		}
		
		//System.out.print("////////////////////////////////////" + "\n");
		MaxStId();
		BuildRev();
		FirstSteps();
		FrontResolve();
		while(!NewFront.isEmpty()) {
			FrontResolve();
		}		
		getEqualPares();
		transClose();
		delEqualStFromGraph();
		
		System.out.print("////////////////////////////////////" + "\n");
		for(int key : EqualPares. keySet()) {
			for(int val : EqualPares.get(key)) {
				System.out.print(key + "-" + val + "\n");
			}	
		}
		System.out.print("////////////////////////////////////" + "\n");
		for(int key : graph. keySet()) {
			for(int val : graph.get(key)) {
				System.out.print(key + "-" + val + "\n");
			}	
		}
		
		//for(int key : classes. keySet()) {			
			//System.out.print(key + "-" + classes.get(key) + "\n");				
		//}		
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////
	//////MINIMIZING//////MINIMIZING//////MINIMIZING//////MINIMIZING//////MINIMIZING//////
	//////////////////////////////////////////////////////////////////////////////////////

}
