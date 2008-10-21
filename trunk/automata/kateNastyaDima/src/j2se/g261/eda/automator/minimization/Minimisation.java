package j2se.g261.eda.automator.minimization;

import java.util.Vector;
import j2se.g261.eda.automator.graph.Node;
import j2se.g261.eda.automator.graph.Graph;

/**
*
* @author dmitriy
*/
public class Minimisation {
	private Vector<Couple> different;
	private static char additionalNode = 'h';

	public Minimisation(){
		different = new Vector<Couple>();
	}
	
	public void add(Couple p){
		if (!different.contains(p)){
		different.add(p);
		}
	}
	
	public Graph withAdditionNode(Graph g){
		Node n = new Node(additionalNode);
		Graph j = new Graph();
		
		for(Node n2 : g.getAll()){
		n.addIncomingNode(n2);
		}
		
		return ;
	}
	
	public Graph toMinimise(Graph g){
		return g;
	}
	
}
