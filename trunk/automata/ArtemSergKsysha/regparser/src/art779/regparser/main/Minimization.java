package art779.regparser.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Minimization {		
	private static Graph dFA;
	private static Graph revgraph;	
	private static HashMap<Integer, ArrayList<Integer>> NonEqualPares;
	private static HashMap<Integer, ArrayList<Integer>> NewFront;
	private static HashMap<Integer, ArrayList<Integer>> NextFront;
	private static HashMap<Integer, ArrayList<Integer>> EqualPares;
	private static HashMap<Integer, Integer> classes;	
	private static int h = 0;	
	
	private static int MaxStId(){
		int id = 0;
		Set<Integer> allNodes = dFA.alfabett.keySet();	
		for (int innode : allNodes) {
			if (innode > id) {
				id = innode;
			}
		}
		return id + 2;
	}
	
	private static void FirstFront() {
		Set<Integer> allNodes = dFA.alfabett.keySet();	
		for (int innode : allNodes) {
			if (innode != dFA.getFinalState() && innode != h) {
				addOneInPares( dFA.getFinalState(), innode );				
			}
			if (innode != h ) {				
				addOneInNewFront( h, innode );
			}
		}
	}
	
	private static void addOneInRev(int in, int what) {
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
	
	private static void addOneInPares(int in, int what) {
		ArrayList<Integer> states = new ArrayList<Integer> ();		
		boolean keyInContainsWhat = false;
		boolean keyWhatContainsIn = false;
		if(NonEqualPares.containsKey(what)) {
			ArrayList<Integer> stlst = new ArrayList<Integer>();
			stlst = NonEqualPares.get(what);
			for (int st : stlst) {
				if( st == in ) {
					keyWhatContainsIn = true;
				}
			}				
		}		
		if(NonEqualPares.containsKey(in))
		{			
			if( keyWhatContainsIn == false){
				states = NonEqualPares.get(in);
				NonEqualPares.remove(in);				
				for (int st : states) {
					if( st == what ) {
						keyInContainsWhat = true;
					}
				}				
				if(keyInContainsWhat == false){
					states.add(what);		
				}				
				NonEqualPares.put(in, states);
			}	
		}
		else 
		{		
			if( keyWhatContainsIn == false){
				//States = new ArrayList<Integer> ();
				states.add(what);
				NonEqualPares.put(in, states);
			}			
		}		
	}
	
	private static void addOneWithoutRep(int in, int what) {
		ArrayList<Integer> states = new ArrayList<Integer> ();		
		boolean keyInContainsWhat = false;
		if(dFA.graph.containsKey(in))
		{			
			states = dFA.graph.get(in);
			dFA.graph.remove(in);				
			for (int st : states) {
				if( st == what ) {
					keyInContainsWhat = true;
				}
			}				
			if(keyInContainsWhat == false){
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
	
	private static void addOneInEqualPares(int in, int what) {
		ArrayList<Integer> States = new ArrayList<Integer> ();		
		boolean keyInContainsWhat = false;
		boolean keyWhatContainsIn = false;
		if(EqualPares.containsKey(what)) {
			ArrayList<Integer> stlst = new ArrayList<Integer>();
			stlst = EqualPares.get(what);
			for (int st : stlst) {
				if( st == in ) {
					keyWhatContainsIn = true;
				}
			}				
		}		
		if(EqualPares.containsKey(in))
		{		
			if( keyWhatContainsIn == false){
				States = EqualPares.get(in);
				EqualPares.remove(in);				
				for (int st : States) {
					if( st == what ) {
						keyInContainsWhat = true;
					}
				}				
				if(keyInContainsWhat == false){
					States.add(what);		
				}				
				EqualPares.put(in, States);
			}	
		}
		else 
		{		
			if( keyWhatContainsIn == false){
				//States = new ArrayList<Integer> ();
				States.add(what);
				EqualPares.put(in, States);
			}			
		}		
	}	
	
	private static void addOneInNewFront(int in, int what) {
		ArrayList<Integer> States = new ArrayList<Integer> ();		
		boolean keyInContainsWhat = false;
		boolean keyWhatContainsIn = false;
		if(NewFront.containsKey(what)) {
			ArrayList<Integer> stlst = new ArrayList<Integer>();
			stlst = NewFront.get(what);
			for (int st : stlst) {
				if( st == in ) {
					keyWhatContainsIn = true;
				}
			}				
		}		
		if(NewFront.containsKey(in))
		{		
			if( keyWhatContainsIn == false){
				States = NewFront.get(in);
				NewFront.remove(in);				
				for (int st : States) {
					if( st == what ) {
						keyInContainsWhat = true;
					}
				}				
				if(keyInContainsWhat == false){
					States.add(what);		
				}				
				NewFront.put(in, States);
			}	
		}
		else 
		{		
			if( keyWhatContainsIn == false){
				//States = new ArrayList<Integer> ();
				States.add(what);
				NewFront.put(in, States);
			}			
		}		
	}
	
	private static void addOneInNextFront(int in, int what) {
		ArrayList<Integer> States = new ArrayList<Integer> ();		
		boolean keyInContainsWhat = false;
		boolean keyWhatContainsIn = false;
		if(NextFront.containsKey(what)) {
			ArrayList<Integer> stlst = new ArrayList<Integer>();
			stlst = NextFront.get(what);
			for (int st : stlst) {
				if( st == in ) {
					keyWhatContainsIn = true;
				}
			}				
		}		
		if(NextFront.containsKey(in))
		{		
			if( keyWhatContainsIn == false){
				States = NextFront.get(in);
				NextFront.remove(in);				
				for (int st : States) {
					if( st == what ) {
						keyInContainsWhat = true;
					}
				}				
				if(keyInContainsWhat == false){
					States.add(what);		
				}				
				NextFront.put(in, States);
			}	
		}
		else 
		{		
			if( keyWhatContainsIn == false){				
				States.add(what);
				NextFront.put(in, States);
			}			
		}		
	}
	
	private static void BuildRev() {		
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
	
	private static void FrontResolve() {
		int flag = 0;
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
									addOneInNextFront(node1, node2);
									addOneInPares(node1, node2);									
								}
							}
						}
					}
				}
				if (dFA.alfabett.get(keynode) == dFA.alfabett.get(valnode)) {
					if( revgraph.graph.get(keynode) != null && revgraph.graph.get(valnode) != null ){
						for (int node1 : revgraph.graph.get(keynode)) {
							for (int node2 : revgraph.graph.get(valnode)) {
								addOneInNextFront(node1, node2);
								addOneInPares(node1, node2);								
							}
						}
					}
				}
			}
		}
		NewFront = NextFront;
	}
	
	private static void delEqualStFromGraph() {			
		for(int key : EqualPares. keySet()) {			
			if(EqualPares.get(key).size() == 0) {
				continue;
			}
			for(int val : EqualPares.get(key)) {
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
	
	private static void transClose() {
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
	
	private static void getEqualPaires() {
		ArrayList<Integer> States = new ArrayList<Integer> ();		
		for(int key : dFA.alfabett.keySet() ) {
			for(int val : dFA.alfabett.keySet() ) {
				if(key != val && key != 0 && val != 0) {
					addOneInEqualPares(key, val);
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
	
	public static Graph minimizeDFA(Graph nFA) 	{		
		dFA = new Graph();
		revgraph = new Graph();
		NonEqualPares = new HashMap<Integer, ArrayList<Integer>>();
		NewFront = new HashMap<Integer, ArrayList<Integer>>();
		NextFront = new HashMap<Integer, ArrayList<Integer>>();
		EqualPares = new HashMap<Integer, ArrayList<Integer>>();
		classes = new HashMap<Integer, Integer>();
		
		dFA = nFA;	
		h = MaxStId();
		BuildRev();
		FirstFront();
		FrontResolve();
		while(!NewFront.isEmpty()) {
			FrontResolve();
		}		
		getEqualPaires();
		transClose();
		delEqualStFromGraph();		
		return dFA;
	}
}
