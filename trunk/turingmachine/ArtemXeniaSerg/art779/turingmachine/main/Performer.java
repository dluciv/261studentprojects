package art779.turingmachine.main;

import java.util.LinkedList;

public class Performer {
	private Tape tape;
	private Rules rules;
	private int iterationsCount = 0;
	private int limIterationsCount = 1020;
	
	public Performer(Tape tape,Rules rules)
	{
		this.tape = tape;
		this.rules = rules;

	}

	public boolean isRulesFitAlphabet()
	{	
		LinkedList<String> alphabett = tape.getAlphabett();
		for (String alpha : alphabett) {
			if(!rules.hasConversionForAlpha(alpha))
				return false;
		}	
		return true;
	}
	
	public int getIterationsCount(){
		return iterationsCount;
	}

	public void execute()
	{

		String theState = rules.sState;
		String theSym = tape.curSym();

		while(theState != rules.fState & iterationsCount <= limIterationsCount)
		{
			System.out.println(theState);
			iterationsCount++;
			if(iterationsCount == limIterationsCount)
				System.out.println("k lim reached");

			
			RuleAction act = rules.getAct(theState, theSym);
			try {
				theState = act.getState();
			} catch (Exception e) {
				break;
			}
			Action op = act.getAction();
			
			if (null != act.getParam())
				tape.setValue(act.getParam());
				
			if(op == Action.R)
				tape.moveRight();
			else if(op == Action.L)
				tape.moveLeft();
			else if (op == Action.H)
				{	/* do stuff */	}
			else
			{
				System.out.println("else");
				break;
			}
			theSym = tape.curSym();
		}
	}
	
	public void run() throws BadDataException
	{
		if(isRulesFitAlphabet())
			execute();
		else{
			throw new BadDataException();
		}
	}

}
