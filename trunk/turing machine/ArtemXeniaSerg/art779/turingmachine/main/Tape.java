/*
 * @author art779j
 * Licensed under the GNU General Public License v2 
 * @copyrights 261studentprojects
 *  
 */

package art779.turingmachine.main;

import java.util.*;

public class Tape {
	
	private int pointer = 0;	
	private ArrayList<String> tape = new ArrayList<String>();
    public static final String terminalSym = "T";
    public static final String infinitySym = " ";
    
    Tape(ArrayList<String> tape_r)
    {
    	fillTape(tape_r);
    }


    
    public void fillTape(ArrayList<String> tape_r){
    	for (String st : tape_r) {
    		tape.add(st);
		}    	  		
	}
    
    public int getPointer(){
		return pointer;
	}
    
    public void moveRight(){
		pointer ++;
	}
	
	public void moveLeft(){
		pointer --;
	}
	
	public void setValue(String v){
		if(tape.size()-1 < pointer)
			tape.add(v);
		else 
			tape.set(pointer, v);
	}
	
	public String curSym() {
		if(tape.size()-1 < pointer)
			return infinitySym;
		else
			return tape.get(pointer);
	}
	
	@Override
	public String toString() {
		return tape.toString();
	}
	
	public ArrayList<String> gettape() {
		return tape;
	}
	
    public LinkedList<String> getAlphabett(){
    	LinkedList<String> alphabett = new LinkedList<String>();  	
    	alphabett.add(infinitySym);
    			
    	for (String tape_alpha : tape) {
			if(!alphabett.contains(tape_alpha))
				alphabett.add(tape_alpha);
		}
    	return alphabett;  	
	}		
}
