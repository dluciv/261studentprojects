package art779.turingmachine.main;

import java.util.LinkedList;

public class Performer {
	private Tape tape;
	private Rules rules;
	private int iterationsCount = 0;
	private int limIterationsCount = 9999;
	
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

		String theState = rules.SState;
		String theSym = tape.curSym();

		while(theState != rules.FState & iterationsCount <= limIterationsCount)
		{
			iterationsCount++;
			if(iterationsCount == limIterationsCount)
				System.out.println("k lim reached");

			
			RuleAction act = rules.getAct(theState, theSym);

			theState = act.getState();
			Action op = act.getAction();
			
			if(op == Action.R)
				tape.moveRight();
			else if(op == Action.L)
				tape.moveLeft();
			else if (op == Action.W)
				tape.setValue(act.getParam());
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
	
	public void run() throws Exception
	{
		if(isRulesFitAlphabet())
			execute();
		else{
			throw new BadDataException();
		}
	}

}
