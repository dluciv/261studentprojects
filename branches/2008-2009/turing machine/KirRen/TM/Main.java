package TM;

import java.util.HashSet;

public class Main {
	public static void main(String[] args) {
		Machine m = XMLParser.getMachine("./TM/Unary.xml");
		m.setStringTape("11_111");
		m.go();
	}
}