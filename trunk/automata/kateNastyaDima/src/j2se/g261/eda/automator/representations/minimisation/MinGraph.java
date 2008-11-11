package j2se.g261.eda.automator.representations.minimisation;

import java.util.Vector;

/**
 * @author Dmitry
 * class for graph with edges
 */
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
	
	public Vector<Edge> findIncomingEdge(int state){
		Vector<Edge> list = new Vector<Edge>();
	
		for(Edge e : this.getAll()){
		
			if (e.getOutgoing() == state){
				list.add(e);
			}	

		}
		return list;
	}
	
	public Vector<Edge> findOutgoingEdge(int state){
		Vector<Edge> list = new Vector<Edge>();
	
		for(Edge e : this.getAll()){
		
			if (e.getIncoming() == state){
				list.add(e);
			}	

		}
		return list;
	}
	
	
}
