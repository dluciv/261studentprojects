package j2se.g261.eda.automator.minimization;

import j2se.g261.eda.automator.graph.Node;

import java.util.Vector;

public class MinGraph {

	private Vector<Edge> all;
	
	public MinGraph(){
		all = new Vector<Edge>();
	}
	
	public Vector<Edge> getAll(){
    	return all;
    }
	
	public void add(Edge n){
		this.all.add(n);
	}
	
	public int sizeAll(){
		return this.all.size();
	}
	
	public Edge getEdgeAt(int index){
		return this.all.get(index);
	}
	
}
