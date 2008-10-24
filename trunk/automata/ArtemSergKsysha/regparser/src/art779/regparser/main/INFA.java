package art779.regparser.main;

import java.util.List; 

public interface INFA { 	
	public int getStartState();
	public int getFinalState(); 	
	public List<Integer> getNextState(int State, char ch);   
	public List<Integer> getNextState(int State);
	public char getCurChar(int State);
	
} 
