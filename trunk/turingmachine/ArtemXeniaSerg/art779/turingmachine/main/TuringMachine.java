package art779.turingmachine.main;


public class TuringMachine {

	public static void main(String[] args){

		String str = "";

		Tape tape = new Tape();	
		Rules rules = new Rules("S","F");
			str += rules.toString();
			str += "\n";
			str += tape.toString();

		Performer p = new Performer(tape,rules);
		try {
			p.run();
		} catch (Exception e) {
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
