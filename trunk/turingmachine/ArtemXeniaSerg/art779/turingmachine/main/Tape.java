package art779.turingmachine.main;

import java.util.HashMap;

public class Tape {
	//private ArrayList<String> tape = new ArrayList<String>();
	private HashMap<Integer, String> tape = new HashMap<Integer, String>();
	private int pointer = 1;
    public static final String startSym = "Ss";
    public static final String finalSym = "Ff";
    public static final String emptySym = "_";
    public static final String infinitySym = "oo";
    
    Tape()
    {
    	tape.put(0,infinitySym);
    	tape.put(1,"1");
    	tape.put(2,"+");
    	tape.put(3,"1");
    	tape.put(4,"1");

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
