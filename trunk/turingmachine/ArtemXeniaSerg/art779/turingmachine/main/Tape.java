package art779.turingmachine.main;

import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

public class Tape {
	private HashMap<Integer, String> tape = new HashMap<Integer, String>();
	private int pointer = 1;
    public static final String startSym = "Ss";
    public static final String finalSym = "Ff";
    public static final String emptySym = "_";
    public static final String infinitySym = "oo";
    
    Tape()
    {
    	String[] tape_r = {
    		"1","1","+","1","1"
    	};
    	fillTape(tape_r);
    }

    public Stack<String> getAlphabett(){
    	Stack<String> alphabett = new Stack<String> ();
    	
    	for (String alpha : tape.values()) {
			if(!alphabett.contains(alpha))
				alphabett.add(alpha);
		}
    	return alphabett;
    	
    	
	}
    
    public void fillTape(String[] tape_r){
    	tape.put(0,infinitySym);
    	for (int i = 1; i <= tape_r.length; i++) {
    		tape.put(i,tape_r[i-1]);
		}
	}
    
	public void movePointerRight(){
		pointer ++;
	}
	
	public void movePointerLeft(){
		pointer --;
	}
	
	public void setValue(String v){
		tape.put(pointer, v);
	}
	
	public String curSym() {
		if(null==tape.get(pointer))
			return infinitySym;
		else
			return tape.get(pointer);
	}
	
	public void printTape() {
		 	System.out.println(tape.toString());
	}
		
}
