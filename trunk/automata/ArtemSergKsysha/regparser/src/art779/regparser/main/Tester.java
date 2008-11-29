package art779.regparser.main;

import java.util.ArrayList;
import java.util.HashMap;

public class Tester {
	private String log="";
	
	public void runTests() {
		
		runTest("Test On DFA 02", "a(b(d|c)|b(e|f)|c)", "ac", true);
		runTest("Test On DFA 01", "a(b(d|c)|b(e|f))", "ac", false);
		runTest("nameTest", "regexp", "word", false);
		runTest("Test01", "absd", "absd", false);
	}
	
	public void runTest(String nameTest, String regexp, String word, boolean isMatchWord) {
		// название теста
		log += "\n\n";
		log += nameTest+" ... ";	
		
		// проверка слова на построение графа
		Graph NFA;
		Helper tool = new Helper();	
		try
		{
			NFA = tool.buildNFA(regexp);
		}
		catch(Exception e1)
		{
			log += "\n";
			log += " failure in buildNFA. Exception: "+e1.toString();
			return;
		}
		log += "\nbuildNFA passed";
		
		// проверка слова на соответсвие
		boolean isMatched;
		try
		{
			isMatched = tool.chekWord(NFA, word);
		}
		catch(Exception e2)
		{
			log += "\n";
			log += " failure in NFA chekWord. Exception: "+e2.toString();
			return;
		}
		if(isMatched != isMatchWord)
			log += "\nchekWord failure";
		else
			log += "\nchekWord passed";	
	}
	
	public String getLog() {
		return log;
	}
}
