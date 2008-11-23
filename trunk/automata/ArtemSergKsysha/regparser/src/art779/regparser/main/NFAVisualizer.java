package art779.regparser.main;
import java.util.ArrayList;

public class NFAVisualizer {
	
	private NDFABuilder NFA;
	
	NFAVisualizer(NDFABuilder tNFA){
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
				output += NFA.alfabett.get(key) + key;
				output += "=[ ";
				for (int i = 0; i < list.size(); i++) {
					output += NFA.alfabett.get(list.get(i)) + list.get(i);
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
					output += "\""+NFA.alfabett.get(key)+" "+key+"\"->\""+NFA.alfabett.get(list.get(i))+" "+list.get(i)+"\"\n";
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
	
	public String printGraphTEX() {
		String output = new String();
		output = "";
		
		if(!NFA.graph.isEmpty())
		{
			output += "\\begin{document} ";
			output += "\n";
			output += "\\begin{tabbing} ";
			output += "\n";
			int rowlen = 0;
			for (Integer key : NFA.alfabett.keySet()) 
			{
				if(NFA.LAST_STATE!=key && NFA.FIRST_STATE!=key && null!=NFA.alfabett.get(key))
				{
					output += "\\ \\= " + key.toString() + NFA.alfabett.get(key);
					rowlen++;
				}
			}
			output += " \\ \\= %";
			output += " \\\\";
			output += "\n";
			
			for (Integer key : NFA.graph.keySet())
			{
				ArrayList<Integer> list = NFA.graph.get(key);
				output += key+" \\> ";
				int i = 0;
				for (i = 0; i < list.size(); i++) {
					String t = list.get(i).toString(); 
					if(list.get(i) == NFA.LAST_STATE)t="!";
					output += "\\> " + t + " \\> ";
				}
				while(i < rowlen){
					i++;
					output += "\\> # \\";
					if(i != rowlen)output += "> ";			
					else output += "\\";
				}
				output += "\n";				
			}
			
			/*
			for (Integer key : NFA.graph.keySet())
			{
				ArrayList<Integer> list = NFA.graph.get(key);
				for (int i = 0; i < list.size(); i++) {
					output += "\""+NFABuilder.alfabett.get(key)+" "+key+"\"->\""+NFABuilder.alfabett.get(list.get(i))+" "+list.get(i)+"\"\n";
				}
			}
			*/
			output += "\\end{tabbing} ";
			output += "\n";
			output += "\\end{document} ";
			output += "\n";
					
		}
		else
		{
			output += "graph is empty";		
		}
		return output; 
	}

	
}
