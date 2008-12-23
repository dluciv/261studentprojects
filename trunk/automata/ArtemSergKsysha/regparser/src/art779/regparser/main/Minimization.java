package art779.regparser.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Minimization {
	public Graph DFA;
	Minimization(Graph TMDFA){
		DFA = TMDFA;
		

		
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
		
		List<Integer> nStatesList = DFA.getNextState(State);
		
		ArrayList<Integer> nStatesListCh = new ArrayList<Integer>(); 
		if(nStatesList == null)
		{
			return null;
		}
		
		for (int i = 0; i < nStatesList.size(); i++) {
			if(null!=DFA.alfabett.get(nStatesList.get(i)))
			{
				char curChar = DFA.alfabett.get(nStatesList.get(i)).charAt(0);
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
		Set<Integer> allNodes = DFA.alfabett.keySet();	
		for (int innode : allNodes) {
			if (innode > id) {
				id = innode;
			}
		}
		h = id + 2;
	}
	
	public void FirstFront() {
		Set<Integer> allNodes = DFA.alfabett.keySet();	
		for (int innode : allNodes) {
			if (innode != DFA.getFinalState() && innode != h) {
				addOneInPares( DFA.getFinalState(), innode );				
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
		if(DFA.graph.containsKey(in))
		{			
			States = DFA.graph.get(in);
			DFA.graph.remove(in);				
			for (int st : States) {
				if( st == what ) {
					flag2 = 1;
				}
			}				
			if(flag2 == 0){
				States.add(what);		
			}				
			DFA.graph.put(in, States);				
		}
		else 
		{		
			States.add(what);
			DFA.graph.put(in, States);					
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
		Set<Integer> allNodes = DFA.alfabett.keySet();	
		for (int innode : allNodes) {
			for (int fromnode : allNodes) {
				ArrayList<Integer> Kids = DFA.getNextState(fromnode);
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
							for (int node2 : DFA.alfabett.keySet()) {
								flag = 0;
								if(getNextSt(node2, DFA.alfabett.get(valnode).charAt(0)) != null) {
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
				if (DFA.alfabett.get(keynode) == DFA.alfabett.get(valnode)) {
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
				for(int grkey :DFA.alfabett.keySet()) {
					if(DFA.graph.get(grkey) != null) {
						for(int grval : DFA.graph.get(grkey)) {
							if(grval == val) {
								DFA.removeOne(grkey, grval);
								addOneWithoutRep(grkey,key);
								break;
							}
						}
					}	
				}
				for(int arrow : DFA.graph.get(val)) {
					addOneWithoutRep(key,arrow);
				}
				DFA.graph.remove(val);
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
		for(int key : DFA.alfabett.keySet() ) {
			for(int val : DFA.alfabett.keySet() ) {
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
	
	
	public Graph minimizeDFA() {
		/*for(int key : graph. keySet()) {
			for(int val : graph.get(key)) {
				System.out.print(key + "-" + val + "\n");
			}	
		}*/		
		MaxStId();
		BuildRev();
		FirstFront();
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
		for(int key : DFA.graph. keySet()) {
			for(int val : DFA.graph.get(key)) {
				System.out.print(key + "-" + val + "\n");
			}	
		}
		return DFA;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////
	//////MINIMIZING//////MINIMIZING//////MINIMIZING//////MINIMIZING//////MINIMIZING//////
	//////////////////////////////////////////////////////////////////////////////////////

}
