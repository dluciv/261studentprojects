package art779.regparser.main;
import java.util.ArrayList;

public class NFAVisualizer {
	
	private NFABuilder NFA;
	
	NFAVisualizer(NFABuilder tNFA){
		NFA = tNFA;
	}

	public String printGraph() {
		String output = new String();
		output = "";
		
		if(!NFA.graph.isEmpty())
		{
			
			for (Integer key : NFA.graph.keySet())
			{
				ArrayList<Integer> list = NFA.graph.get(key);
				output += NFABuilder.alfabett.get(key);
				output += "=[ ";
				for (int i = 0; i < list.size(); i++) {
					output += NFABuilder.alfabett.get(list.get(i));
					output += " ";
				}
				output += "] ";
			}
		}
		else
		{
			output += "graph is empty";		
		}
		return output; 
	}
	
	public String printGraphViz() {
		String output = new String();
		output = "";
		
		if(!NFA.graph.isEmpty())
		{
			output += "digraph G{";
			for (Integer key : NFA.graph.keySet())
			{
				ArrayList<Integer> list = NFA.graph.get(key);
				for (int i = 0; i < list.size(); i++) {
					output += "\""+NFABuilder.alfabett.get(key)+" "+key+"\"->\""+NFABuilder.alfabett.get(list.get(i))+" "+list.get(i)+"\"\n";
				}
			}
			output += "}";
		}
		else
		{
			output += "graph is empty";		
		}
		return output; 
	}

	public int getFinalState() {
		return NFABuilder.LAST_STATE;
	}
	
}
