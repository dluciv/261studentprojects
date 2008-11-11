package j2se.g261.eda.automator.representations.minimisation;


import j2se.g261.eda.automator.representations.Node;
import j2se.g261.eda.automator.representations.nfa.NFA;
import j2se.g261.eda.automator.representations.nfa.NFANode;
import java.util.Vector;


public class TransformToEdge {

	private MinGraph edgeGraph;
	
	public TransformToEdge(){
		this.edgeGraph = new MinGraph();
	}
	
	/**
	 * this method transform graph with nodes to graph with edges
	 * @param g - graph with nodes
	 * @return graph with edges
	 */
	public MinGraph transform(NFA g){
		int num = g.allSize();
		int newNumber = 2;
		for(int i = 0; i < num; i++){
			if(g.isEnd(g.getNodeFromAllAt(i))){
				g.getNodeFromAllAt(i).setNumber(1);	
				
			}
			else if(g.isStart(g.getNodeFromAllAt(i))){
				g.getNodeFromAllAt(i).setNumber(0);
			}
			else{	
					g.getNodeFromAllAt(i).setNumber(newNumber);
					newNumber++;
			}
		}
		NFANode n = (NFANode)g.getNodeFromStartsAt(0);
		Vector<Node> passed = new Vector<Node>();
		return toNFA(n,passed);
	}
	
	private MinGraph toNFA(NFANode n,Vector<Node> passed){
		
		int num = n.getOutgoingSize();
		
		for(int i = 0; i < num; i++){
			
			NFANode n2 = (NFANode)n.getOutgoingAt(i);
			passed.add(n);
			if(NFANode.isEndNode((NFANode) n2)){
				Edge endEdge = new Edge('\n', n.getNumber(), n2.getNumber());
				edgeGraph.add(endEdge);
				
			}
			
			else if (passed.contains(n2)){
					Edge edgeNode = new Edge(n2.getName(), n.getNumber(), n2.getNumber());			
					if (!edgeGraph.getAll().contains(edgeNode)){
						edgeGraph.add(edgeNode);
					}
				
				}
				else{
		
			toNFA(n2,passed);
			
			Edge edgeNode = new Edge(n2.getName(), n.getNumber(), n2.getNumber());
				
			if (!edgeGraph.getAll().contains(edgeNode)){
				edgeGraph.add(edgeNode);
			}
				}
			}		
		return edgeGraph;
	}
	
}
