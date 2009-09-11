
package art779.regparser.main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;


public class Determination {

	public HashMap<Integer, TreeSet<Integer>> newDFAStates = new HashMap<Integer, TreeSet<Integer>>();
    public HashMap<Integer, String> alfabett = new HashMap<Integer, String>();
	public HashMap<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
	public Graph NFA;
	public Graph DFA = new Graph();;
	public int pointer = 60; 


	Determination(Graph TNFA){
		NFA = TNFA;
		

		TreeSet<Integer> fistState = new TreeSet<Integer>();
		fistState.add(Graph.FIRST_STATE);
		newDFAStates.put(Graph.FIRST_STATE, fistState);
		/*
		 * 
		 */
		System.out.print("\n\n  setByState " + newDFAStates);
	}
	
	public  Set<String> uniqueAlfabett(HashMap<Integer, String> alfabett)
	{
	    TreeSet<String> newAlfabett= new TreeSet<String>();
	    for( String x : alfabett.values()) 
	    {
	    	newAlfabett.add(x);
	    }
		
	    System.out.print("\n\n" + newAlfabett);
		return newAlfabett;
	}

	private TreeSet<Integer> makeTreeSetOfArrayList(ArrayList<Integer> list){ //Делаем из листа трисет
		TreeSet<Integer> treeset = new TreeSet<Integer>();
		if(!list.isEmpty())
			treeset.addAll(list); 
		return treeset;
		
	}

	private int getKeyInDFAbyTreeSet(TreeSet<Integer> treeset) 
	{
		
		Set<Integer> keys = newDFAStates.keySet();
		for (Integer i : keys) {
	
			if(newDFAStates.get(i).equals(treeset))
			return i;			
		}
		return -1;
	}
	
	
	public Graph makeDFA()
	{
		HashMap<Integer, String> alfabettCopy = new HashMap<Integer, String>();
		alfabettCopy.putAll(NFA.alfabett);
		alfabettCopy.remove(Graph.FIRST_STATE);
		Set<String> uniqueAlfabett = uniqueAlfabett(alfabettCopy);

		Stack<Integer> stack = new Stack<Integer> ();  
		stack.push(Graph.FIRST_STATE);
		
		while(!stack.empty())
		{
			System.out.print("\n\n  stack is " + stack );
			int CurrentStateInStack = stack.pop();
			System.out.print("\n\n  current sate in state is " + CurrentStateInStack );

			TreeSet<Integer> ThisStateInNFA = new TreeSet<Integer>();
			if(newDFAStates.containsKey(CurrentStateInStack)){
				ThisStateInNFA = newDFAStates.get(CurrentStateInStack);
			}
			
			for (Integer curKid : ThisStateInNFA){
				System.out.print("\n  curKid " + curKid + " owner is " + CurrentStateInStack );
				
				for (String alfa : uniqueAlfabett){
					System.out.print("\n  alfa " + alfa);
					
					TreeSet<Integer> kidsByAlfa = makeTreeSetOfArrayList(NFA.getNextState(curKid,alfa.charAt(0)));
					
					if(!kidsByAlfa.isEmpty()){
						System.out.print(" can go to " + kidsByAlfa );
						
						int key = getKeyInDFAbyTreeSet(kidsByAlfa);

						if(key == -1)
						{
						/*
						 * 
						 */
							
							newDFAStates.put(pointer, kidsByAlfa);
							System.out.print("\n  newDFAStates is  " + newDFAStates);
							stack.push(pointer);
							DFA.addOneStrict(CurrentStateInStack, pointer);
						
							DFA.alfabett.put(pointer, alfa);
							pointer++;
		
							
						}
						else 
						{
							DFA.addOneStrict(CurrentStateInStack, key);
							System.out.print(" key in DFA " + key );
						}
						
					}
					
				}
				
			}
		}
		System.out.println("\n  ." );
		return DFA;
	}
	
	public static void main(String[] args) {
		// 
		Helper face = new Helper();
		String reg = "a(b|b)|a";

		Graph NFA = face.buildNFA(reg);
		
		System.out.println(face.printRegular(NFA));
		System.out.println(face.printRegular(new Determination(NFA).makeDFA()));

	}
}
