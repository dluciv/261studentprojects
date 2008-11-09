package j2se.g261.eda.automator.minimization;

import java.util.Vector;

public class EdgeGraphWolker {

	private MinGraph g;
	
	public EdgeGraphWolker(MinGraph g){
		this.g = g;
	}
	
	public boolean check(String s){
		int length = s.length();
		int state = 0;
		boolean isNext = true;
		
		for(int i = 0; i < length; i++){
			if(isNext){
				
			char symbol = s.charAt(i);
			Vector<Edge> v = g.findOutgoingEdge(state);
			
			for(Edge k : v){
				if (k.getName() == symbol){
					state = k.getOutgoing();
					isNext = true;
					break;
				}
				isNext = false;
			}
			
			}
		}
		if(isNext){
			Vector<Edge> end = g.findOutgoingEdge(state);
			for(Edge j : end){
				if(j.getOutgoing() == 1){
					isNext = true; 
					break;
				}
				else isNext = false;
			}
		}
		return isNext;
	}
	
}
