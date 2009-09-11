/*
 * @author ksenyiacypan
 * Licensed under the GNU General Public License v2 
 * @copyrights 261studentprojects
 *  
 */

package art779.turingmachine.main;

import java.util.*;

public class Performer {
	private boolean isConsole = true;//equals "true" if console application is run and "false" if GUI is run
	private Tape tape;
	private Rules rules;
	private int iterationsCount = 0;
	private int limIterationsCount = 9999;
	
	public Performer(Tape tape,Rules rules,boolean console)
	{
		this.isConsole = console;
		this.tape = tape;
		this.rules = rules;

	}

	
	public int getIterationsCount(){
		return iterationsCount;
	}

	public void execute()
	{		
		String theState = rules.sState;
		String theSym = tape.curSym();
		if (!isConsole)				
			GUIinterphase.addRow(tape.gettape(),theState,theSym,tape.getPointer());
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
			
			
			if (!isConsole)				
				GUIinterphase.addRow(tape.gettape(),theState,theSym,tape.getPointer());			
		}
		if (!isConsole)		
			GUIinterphase.addDelimRow();		
	}	

}
