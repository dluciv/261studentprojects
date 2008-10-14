package regparser.main;
import java.util.List; 

public class checking{
	private static INFA automato;
	public checking(INFA tautomato)
	{
		automato = tautomato;
	}

	private static boolean checkNextSymbol(int currentState,String wordRest){
		List<Integer> lst = automato.getNextState(currentState, wordRest.charAt(0));
		if (wordRest.length() == 1){
			for(Integer state:lst){
				if (state.intValue()== automato.getFinalState()){
					return true;
				}
			}
			return false;
		}
		else{
			for(Integer state:lst){
				if (checkNextSymbol(state.intValue(), wordRest.substring(1))){
					return true;			
				}
				return false;
			}
		
		}
		return false;
	}	
	
	
	public static boolean checkWord (String word){
		return checkNextSymbol(automato.getStartState(), word);
	}
	
}