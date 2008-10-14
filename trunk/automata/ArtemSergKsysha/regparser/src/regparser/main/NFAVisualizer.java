package regparser.main;
import java.util.ArrayList;

public class NFAVisualizer {
	
	private NFABuilder NFA;
	
	NFAVisualizer(NFABuilder tNFA){NFA = tNFA;}

	public String printGraph() {
		String output = new String();
		output = "";
		NFABuilder.alfabett.put(NFABuilder.FIRST_STATE, "S");
		NFABuilder.alfabett.put(NFABuilder.LAST_STATE, "F");
		
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
	
	public void printGraphViz() {
		NFABuilder.alfabett.put(NFABuilder.FIRST_STATE, "S");
		NFABuilder.alfabett.put(NFABuilder.LAST_STATE, "F");
		
		if(!NFA.graph.isEmpty())
		{
			System.out.println("digraph G{");
			for (Integer key : NFA.graph.keySet())
			{
				ArrayList<Integer> list = NFA.graph.get(key);
				for (int i = 0; i < list.size(); i++) {
					System.out.println("\""+NFABuilder.alfabett.get(key)+" "+key+"\"->\""+NFABuilder.alfabett.get(list.get(i))+" "+list.get(i)+"\"");
				}
			}
			System.out.println("}");
		}
		else
		{
			System.out.println("graph is empty");		
		}
	}

	public int getFinalState() {
		return NFABuilder.LAST_STATE;
	}
	
}
