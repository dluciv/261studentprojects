/*
 * @author art779j
 * Licensed under the GNU General Public License v2 
 * @copyrights 261studentprojects
 *  
 */

package art779.turingmachine.main;

import java.util.ArrayList;

public class TuringMachine {

	public static void main(String[] args){

		String str = "";
		
    	String[] tapesr = {"1","1","+","1","1","+","1","1","1"};
    	ArrayList<String> tape_r = new ArrayList<String>();
    	for (int i = 0; i < tapesr.length; i++)
    		tape_r.add(tapesr[i]);
    	
    		
    	Tape tape = new Tape(tape_r);	

		//Rules rules =  RulesMaker.parseXMLFileProgram("UMT.xml");
		Rules rules =  RulesMaker.parseXMLFileProgram("unarsum.xml");
		
		str += rules.toString();
		str += "\n";
		str += tape.toString();
		
		Performer p = new Performer(tape,rules,true);
		p.execute();

		str += "\n";
		str += "iterations " + p.getIterationsCount();
		str += "\n";
		str += tape.toString();
		
		System.out.println(str);
	}
}
