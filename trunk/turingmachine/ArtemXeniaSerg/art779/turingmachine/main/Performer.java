package art779.turingmachine.main;

import java.util.Stack;

public class Performer {
	
	private static Performer instance = new Performer();
	private Performer(){}
	public static Performer getInstance()
	{	return instance;}

	public boolean setForAlphabettRules(Stack<String> alphabett,Rules rules)
	{	
		while(!alphabett.isEmpty())
		{
			String alpha = alphabett.pop();
			//for
		}
		return true;
	}
	
	public void run(Tape tape,Rules rules)
	{

		String theState = Tape.startSym;
		String theSym = tape.curSym();

		int k=0;
		int lim=99;
		while(theState!=Tape.finalSym && k<lim)
		{
			k++;
			if(k==lim)
				System.out.println("k lim reached");

			
			RuleSet toDo = rules.getRule(theState, theSym);
			//System.out.print(theState+theSym);			
			//System.out.println(toDo.toString());
			theState = toDo.getState();
			
			String op = toDo.getOperation();
			
			if(op == Rules.R)
				tape.movePointerRight();
			else if(op == Rules.L)
				tape.movePointerLeft();
			else if (op == Rules.W)
				tape.setValue(toDo.getValue());
			else if (op == Rules.H)
				{	/* do stuff */	}
			else
			{
				System.out.println("else");
				break;
			}
			theSym = tape.curSym();
		}
	}

}
