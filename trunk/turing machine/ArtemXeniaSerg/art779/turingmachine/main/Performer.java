/*
 * @author ksenyiacypan
 * Licensed under the GNU General Public License v2 
 * @copyrights 261studentprojects
 *  
 */

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

		String theState = rules.sState;
		String theSym = tape.curSym();

		while(!theState.equals(rules.fState) && iterationsCount <= limIterationsCount)
		{
			
			iterationsCount++;
			if(iterationsCount == limIterationsCount)
				System.out.println("limit if iterations is reached");

			
			RuleAction act = rules.getAct(theState, theSym);
			try {
				theState = act.getState();
			} catch (Exception e) {
				break;
			}
			if (null != act.getParam())
				tape.setValue(act.getParam());			
			Action op = act.getAction();		
			switch (op) {
				case R:
					tape.moveRight();
					break;
				case L:
					tape.moveLeft();
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
