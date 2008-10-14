package regparser.main;

import java.util.List; 

public interface INFA { 	
	public int getStartState();
	public int getFinalState(); 	
	public List<Integer> getNextState(int State, char ch);   
	public List<Integer> getNextState(int State);
	
} 
