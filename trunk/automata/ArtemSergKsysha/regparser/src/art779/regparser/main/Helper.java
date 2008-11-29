package art779.regparser.main;

import ru.sscc.util.TimeCounter;

public class Helper {
	private TimeCounter timeCounter;

	public Graph buildNFA(String regexp) {
		Graph graph = new Graph(); 
		return graph.buildNFA(regexp);
	}

	public Graph buildDFA(String regexp) {
		Graph graph = new Graph(); 
		return graph.buildDFA(regexp);
	}
	
	public boolean chekWord(Graph NFA, String word) {
		Checker checker = new Checker(NFA);
		return checker.checkWord(word);
	}
	
	public void startTimer() {
		timeCounter = new TimeCounter();
		timeCounter.restart();
	}

	public long getTime() {
		long time = timeCounter.timeMillis();
		if(0==time)time=1;
		return time; 
	}
	
	public String getTimeSec() {
		return timeCounter.toString(); 
	}

	public String printRegular(Graph NFA) {
		GraphVisualizer visualizer = new GraphVisualizer(NFA);
		return visualizer.printGraph();
	}

	public String printGraphViz(Graph NFA) {
		GraphVisualizer visualizer = new GraphVisualizer(NFA);
		return visualizer.printGraphViz();
	}

	public String printTEX(Graph NFA) {
		GraphVisualizer visualizer = new GraphVisualizer(NFA);
		return visualizer.printGraphTEX();
	}
	
	public static String runTestsAndGetLog() {
		Tester tester = new Tester();
		tester.runTests();
		return tester.getLog();
	}

	public static void main(String[] args) {
	
		Helper face = Singleton.getInstanceHelper();
		String reg = "a((bcd)*|bcf|b)";
		
		System.out.println(face.printRegular(face.buildNFA(reg)));
		System.out.println(face.printRegular(face.buildDFA(reg)));
		
		System.out.println(".");
	}



}
