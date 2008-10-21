package j2se.g261.eda.automator.minimization;

import j2se.g261.eda.automator.graph.Node;

import java.util.Vector;

public class MinNode {

	private int number;
	private Vector<Edge> incoming;
	private Vector<Edge> outgoing;
	
	public MinNode (int number){
		this.number = number;
		this.outgoing = new Vector<Edge>();
        this.incoming = new Vector<Edge>();
	}
	
	public void setNumber(int number){
		this.number = number;
	}
	
	public int getNumber(int number){
		return this.number;
	}
	
	public void addIncomingEdge(Edge n) {
        if(!incoming.contains(n))
        incoming.add(n);
    }
	
	public void addOutgoingEdge(Edge n) {
        if(!incoming.contains(n))
        incoming.add(n);
    }
	
	public int getOutgoingSize(){
        return outgoing.size();
    }
    
    public int getIncomingSize(){
        return incoming.size();
    }
	
    public int getOutgoingAt(int index){
    	return 
    }
    
}
