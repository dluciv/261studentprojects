package j2se.g261.eda.automator.minimization;

public class Edge {
	
	private char name;
	private int incoming;
	private int outgoing; 
	
	public Edge (char name, int incoming, int outgoing){
		this.outgoing = outgoing;
		this.incoming = incoming;
		this.name = name;
	}
	
	public int getIncoming(){
		return this.incoming;
	}
	
	public int getOutgoing(){
		return this.outgoing;
	}
	
	public char getName(){
		return this.name;
	}
}
