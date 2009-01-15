package art779.regparser.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Minimization {		
	
	Minimization(Graph nFA) {		
		dFA = CloneGr(nFA);			
		h = dFA.MaxStId() + 1;	//h - virtual state in minimizing algorithm			
		BuildRev();	
	}
	
	private Graph dFA;
	private Graph revgraph = new Graph();
	private int h = 0;	
	
	private Graph CloneGr(Graph nFA) {
		dFA = new Graph();
		try { 
			dFA = (Graph) nFA.clone();
	    } catch ( CloneNotSupportedException e ) { 
	    	e.printStackTrace () ; 
	    }
	    return dFA;
	}
	
	private HashMap<Integer, ArrayList<Integer>> FirstFront() {
		HashMap<Integer, ArrayList<Integer>> NewFront = new HashMap<Integer, ArrayList<Integer>>();				
		for (int innode : dFA.alfabett.keySet()) {			
			if (innode != h ) {				
				addOneInHash( h, innode, NewFront );
			}
		}
		return NewFront;
	}
	
	private void addOneInRev(int in, int what) {
		ArrayList<Integer> fromStates;
		if(revgraph.graph.containsKey(in))
		{
			fromStates = revgraph.graph.get(in);
			revgraph.graph.remove(in);
			fromStates.add(what);
		}
		else
		{
			fromStates = new ArrayList<Integer> ();
			fromStates.add(what);
		}
		revgraph.graph.put(in, fromStates);
	}
	
	private HashMap<Integer, ArrayList<Integer>> FirstNonEqualPairs() {
		HashMap<Integer, ArrayList<Integer>> NonEqualPairs = new HashMap<Integer, ArrayList<Integer>>();			
		for (int innode : dFA.alfabett.keySet()) {
			if (innode != dFA.getFinalState() && innode != h) {
				addOneInHash( dFA.getFinalState(), innode, NonEqualPairs);				
			}			
		}
		return NonEqualPairs;
	}
	
	private boolean HashContainsPair(int a, int b, HashMap<Integer, ArrayList<Integer>> hash) {		
		if(hash.containsKey(b)) {
			ArrayList<Integer> stlst = new ArrayList<Integer>();
			stlst = hash.get(b);
			for (int st : stlst) {
				if( st == a ) {
					return true;
				}
			}				
		}
		if(hash.containsKey(a)) {
			ArrayList<Integer> stlst = new ArrayList<Integer>();
			stlst = hash.get(a);
			for (int st : stlst) {
				if( st == b ) {
					return true;
				}
			}				
		}
		return false;
	}
	
	private  HashMap<Integer, ArrayList<Integer>> addOneInHash(int in, int what, HashMap<Integer, ArrayList<Integer>> hash) {
		ArrayList<Integer> states;	
		if ( !HashContainsPair(in, what, hash) ) {
			if(hash.containsKey(in)) {
				states = hash.get(in);
				hash.remove(in);				
				states.add(what);		
				hash.put(in, states);
				return hash;
			}
			if(hash.containsKey(what)) {
				states = hash.get(what);
				hash.remove(what);				
				states.add(in);		
				hash.put(what, states);
				return hash;
			}
			states = new ArrayList<Integer> ();
			states.add(what);
			hash.put(in, states);			
		}		
		return hash;
	}
	
	private void addOneWithoutRep(int in, int what) {
		ArrayList<Integer> states = new ArrayList<Integer> ();		
		boolean foundWhat = false;
		if(dFA.graph.containsKey(in))
		{			
			states = dFA.graph.get(in);
			dFA.graph.remove(in);				
			for (int st : states) {
				if( st == what ) {
					foundWhat = true;
				}
			}				
			if(!foundWhat){
				states.add(what);		
			}				
			dFA.graph.put(in, states);				
		}
		else 
		{		
			states.add(what);
			dFA.graph.put(in, states);					
		}		
	}		
	
	private void BuildRev() {		
		Set<Integer> allNodes = dFA.alfabett.keySet();	
		for (int innode : allNodes) {
			for (int fromnode : allNodes) {
				ArrayList<Integer> Kids = dFA.getNextState(fromnode);
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
	
	private HashMap<Integer, ArrayList<Integer>> FrontResolve(HashMap<Integer, ArrayList<Integer>> NonEqualPairs, HashMap<Integer, ArrayList<Integer>> NewFront) {		
		HashMap<Integer, ArrayList<Integer>> NextFront = new HashMap<Integer, ArrayList<Integer>>();		
		NextFront.clear();
		Set<Integer> allNodes = NewFront.keySet();	
		for (int keynode : allNodes) {
			ArrayList<Integer> allvalues = NewFront.get(keynode);
			for (int valnode : allvalues) {
				if ( keynode == h ) {
					if( revgraph.graph.get(valnode) != null ){
						for (int node1 : revgraph.graph.get(valnode)) {
							for (int node2 : dFA.alfabett.keySet()) {															
								if(dFA.getNextState(node2, dFA.alfabett.get(valnode).charAt(0)).size()== 0) {
									addOneInHash(node1, node2, NextFront);
									addOneInHash(node1, node2, NonEqualPairs);									
								}
							}
						}
					}
				}
				if (dFA.alfabett.get(keynode) == dFA.alfabett.get(valnode)) {
					if( revgraph.graph.get(keynode) != null && revgraph.graph.get(valnode) != null ){
						for (int node1 : revgraph.graph.get(keynode)) {
							for (int node2 : revgraph.graph.get(valnode)) {
								addOneInHash(node1, node2, NextFront);
								addOneInHash(node1, node2, NonEqualPairs);								
							}
						}
					}
				}
			}
		}		
		if (!NewFront.isEmpty()) {
			return FrontResolve(NonEqualPairs,NextFront);
		} else {
			return NonEqualPairs;
		}		
	}
	
	private void delEqualStFromGraph(HashMap<Integer, ArrayList<Integer>> EqualPairs) {		
		for(int key : EqualPairs. keySet()) {			
			if(EqualPairs.get(key).size() == 0) {
				continue;
			}
			for(int val : EqualPairs.get(key)) {
				for(int grkey :dFA.alfabett.keySet()) {
					if(dFA.graph.get(grkey) != null) {
						for(int grval : dFA.graph.get(grkey)) {
							if(grval == val) {
								dFA.removeOne(grkey, grval);
								addOneWithoutRep(grkey,key);
								break;
							}
						}
					}	
				}				
				for(int arrow : dFA.graph.get(val)) {
					addOneWithoutRep(key,arrow);
				}
				dFA.graph.remove(val);
				dFA.alfabett.remove(val);
			}			
		}	
	}
	
	private HashMap<Integer, ArrayList<Integer>> transClose(HashMap<Integer, ArrayList<Integer>> EqualPairs) {
		HashMap<Integer, Integer> classes = new HashMap<Integer, Integer>();
		int maxclass = 0;
		for(int key : EqualPairs. keySet()) {			
			if(EqualPairs.get(key).size() == 0) {
				continue;
			}
			if(classes.get(key) == null) {
				maxclass += 1;
				classes.put(key, maxclass);
			}
			for (int values : EqualPairs.get(key)) {				
				for (int fkey : EqualPairs.keySet()) {
					if(classes.get(fkey) != null) {
						continue;
					}
					for (int fvalues : EqualPairs.get(fkey)) {
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
					addOneInHash(key, key1, EqualPairs);
					for(int element : EqualPairs.get(key1)) {
						addOneInHash(key, element, EqualPairs);
					}
					EqualPairs.remove(key1);
					classes.put(key1, h);
				}
			}
		}
		return EqualPairs;
	}
	
	private HashMap<Integer, ArrayList<Integer>> getEqualPairs(HashMap<Integer, ArrayList<Integer>> NonEqualPairs) {
		ArrayList<Integer> States = new ArrayList<Integer> ();		
		HashMap<Integer, ArrayList<Integer>> EqualPairs = new HashMap<Integer, ArrayList<Integer>>();
		for(int key : dFA.alfabett.keySet() ) {
			for(int val : dFA.alfabett.keySet() ) {
				if(key != val && key != 0 && val != 0) {
					EqualPairs = addOneInHash(key, val, EqualPairs);
				}	
			}
		}
		for (int key : NonEqualPairs.keySet()) {
			for (int val : NonEqualPairs.get(key)) {
				if(EqualPairs.get(key) != null) {
					int element = -1;
					for (int value : EqualPairs.get(key)) {
						if(val == value) {							
							element++;
							States = EqualPairs.get(key);
							EqualPairs.remove(key);				
							States.remove(element);							
							EqualPairs.put(key, States);
							break;
						}
						element++;
					}						
				}		
				
				if(EqualPairs.get(val) != null) {
					int element = -1;
					for (int value : EqualPairs.get(val)) {
						if(key == value) {							
							element++;
							States = EqualPairs.get(val);
							EqualPairs.remove(val);				
							States.remove(element);							
							EqualPairs.put(val, States);
							break;
						}
						element++;
					}					
				}				
			}			
		}
		return EqualPairs;		
	}
	
	private Graph minimizing() {						
		delEqualStFromGraph(transClose(getEqualPairs(FrontResolve(FirstNonEqualPairs(), FirstFront()))));		
		return dFA;
	}
	
	public static Graph minimizeDFA(Graph nFA) 	{		
		Minimization m = new Minimization(nFA);
		m.minimizing();		
		return m.dFA;
	}
}
