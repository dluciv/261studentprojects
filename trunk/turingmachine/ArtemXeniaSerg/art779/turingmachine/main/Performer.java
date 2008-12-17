package art779.turingmachine.main;

public class Performer {

	public void run()
	{
		Tape tape = new Tape();
		Rules rules = new Rules();
		tape.printTape();

		String theState = Tape.startSym;
		String theSym = tape.curSym();

		/*
		System.out.println(rules.toString());		
		RuleSet toDo = rules.getRule(theState, theSym);
		System.out.println(toDo.toString());
		*/

		int k=0;
		while(theState!=Tape.finalSym && k<99)
		{
			k++;
			System.out.print(theState+theSym);
			RuleSet toDo = rules.getRule(theState, theSym);
			System.out.println(toDo.toString());
			theState = toDo.getState();

			String op = toDo.getOperation();
			if(op == Rules.R)
				tape.movePointerRight();
			else if(op == Rules.L)
				tape.movePointerLeft();
			else if (op == Rules.W)
				tape.setValue(toDo.getValue());
			else
			{
				System.out.println("else");
				break;
			}
			theSym = tape.curSym();
		}
		tape.printTape();

	}
	
	public static void main(String[] args) {

		String str = "";
		
		Performer p = new Performer();
		p.run();
		
		System.out.println(str + " \n.");
	}

}
