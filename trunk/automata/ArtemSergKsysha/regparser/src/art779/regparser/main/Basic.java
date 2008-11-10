package art779.regparser.main;

import ru.sscc.util.TimeCounter;

public class Basic {
	private TimeCounter timeCounter;

	public NFABuilder buildNFA(String regexp) {
		Parser parser = new Parser(regexp);
		NFABuilder NFA = parser.getNFA();
		return NFA;
	}

	public NFABuilder buildDFA(String regexp) {
		Parser parser = new Parser(regexp);
		NFABuilder NFA = parser.getNFA();
		NFA.determinateNFA();
		return NFA;
	}
	
	public boolean chekWord(NFABuilder NFA, String word) {
		Checker checker = new Checker(NFA);
		return checker.checkWord(word);
	}
	
	public void startTimer() {
		timeCounter = new TimeCounter(); 
		timeCounter.restart();
	}

	public long getTime() {
		return timeCounter.timeMillis(); 
	}
	
	public String getTimeSec() {
		return timeCounter.toString(); 
	}

	public String printRegular(NFABuilder NFA) {
		NFAVisualizer visualizer = new NFAVisualizer(NFA);
		return visualizer.printGraph();
	}

	public String printGraphViz(NFABuilder NFA) {
		NFAVisualizer visualizer = new NFAVisualizer(NFA);
		return visualizer.printGraphViz();
	}

	public String printTEX(NFABuilder NFA) {
		NFAVisualizer visualizer = new NFAVisualizer(NFA);
		return visualizer.printGraphTEX();
	}

	public static void main(String[] args) {
		
		Basic face = new Basic();
		
		//face.startTimer();
		
		NFABuilder graph = face.buildNFA("a*n*|b");
		
		System.out.println(face.printRegular(graph));
		
		System.out.println(face.chekWord(graph, ""));
		
		//System.out.println("time spent " + face.getTime());
		
		System.out.println(".");
	}

}
