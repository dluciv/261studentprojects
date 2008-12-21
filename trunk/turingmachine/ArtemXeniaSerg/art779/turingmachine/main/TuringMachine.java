package art779.turingmachine.main;


public class TuringMachine {

	public static void main(String[] args){

		String str = "";

		Tape tape = new Tape();	
		//Rules rules = new Rules("A","Y");
		//Rules rules =  RulesMaker.parseXMLFileProgram("UMT.xml");
		Rules rules =  RulesMaker.parseXMLFileProgram("unarsum.xml");
			str += rules.toString();
			str += "\n";
			str += tape.toString();
		
		Performer p = new Performer(tape,rules);
		try {
			p.run();
		} catch (BadDataException e) {
			str += "\n";
			str += e.toString();
		}
			str += "\n";
			str += "iterations " + p.getIterationsCount();
			str += "\n";
			str += tape.toString();
		
		System.out.println(str);

	}
}
