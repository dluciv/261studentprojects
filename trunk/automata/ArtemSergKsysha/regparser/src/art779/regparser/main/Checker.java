package art779.regparser.main;
import java.util.List;

public class Checker{
	private static INFA NFA;
	public Checker(INFA TNFA)
	{
		NFA = TNFA;
	}

	private boolean checkNextSymbol(int currentState, String wordRest){
		
		List<Integer> nStates = NFA.getNextState(currentState);
		char chA = wordRest.charAt(0);
		
		if(nStates.get(0) == NFA.getFinalState())
			return false;
		else{
			for (int i = 0; i < nStates.size(); i++) {
				char chB = NFA.getCurChar(nStates.get(i));
				if(chB == chA)
				{
					if (wordRest.length() == 1){
						List<Integer> nnStates = NFA.getNextState(nStates.get(i));
						boolean correct = false;
						for (int j = 0; j < nnStates.size(); j++) {
							if(nnStates.get(j) == NFA.getFinalState())
								correct = true;
						}
						return correct;
					}
					else
						return checkNextSymbol(nStates.get(i), wordRest.substring(1));
				}
			}
			return false;
		}
	}	
	
	
	public boolean checkWord (String word){
		if(""==word)
		{
			List<Integer> nStates = NFA.getNextState(NFA.getStartState());
			boolean correct = false;
			for (int j = 0; j < nStates.size(); j++) {
				if(nStates.get(j) == NFA.getFinalState())
					correct = true;
			}
			return correct;
		}
		else
			return checkNextSymbol(NFA.getStartState(), word);
	}
	
}