package art779.turingmachine.main;

public class TuringMachine {

	public static void main(String[] args) {

		String str = "";
		
		Tape tape = new Tape();
		str += tape.getAlphabett().toString();
		str += "\n";
		Rules rules = new Rules();
		str += rules.toString();
		tape.printTape();
		
		//str += "\n";
		//str += Performer.getInstance.setForAlphabettRules(tape.getAlphabett(),rules); 
		Performer.getInstance().run(tape,rules);
		
		tape.printTape();
		
		System.out.println(str);
		
	}

}
