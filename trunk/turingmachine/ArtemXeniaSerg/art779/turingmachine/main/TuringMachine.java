/*
 * @author art779j
 * Licensed under the GNU General Public License v2 
 * @copyrights 261studentprojects
 *  
 */

package art779.turingmachine.main;

public class TuringMachine {

	public static void main(String[] args){

		String str = "";
    	String[] tape_r = {"1","1","+","1","1","+","1","1","1"};
		Tape tape = new Tape(tape_r);	

		//Rules rules =  RulesMaker.parseXMLFileProgram("UMT.xml");
		Rules rules =  RulesMaker.parseXMLFileProgram("unarsum.xml");
		str += rules.toString();
		str += "\n";
		str += tape.toString();
		
		Performer p = new Performer(tape,rules);
		p.execute();

		str += "\n";
		str += "iterations " + p.getIterationsCount();
		str += "\n";
		str += tape.toString();
		
		System.out.println(str);

	}
}
