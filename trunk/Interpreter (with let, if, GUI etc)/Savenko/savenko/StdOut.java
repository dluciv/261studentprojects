/*
 * Поток печати на консоль
 * Savenko Maria(c)
 */
package savenko;

import java.util.ArrayList;

public class StdOut {
	
	private ArrayList<String> out;
	
	public StdOut(){
		out = new ArrayList<String>();
	}
	
	public void addOutStat(String new_out){
		out.add(new_out);
	}

}
