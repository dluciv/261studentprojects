package j2se.g261.eda.automator.minimization;

/**
 * @author Dmitry
 *
 */
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
	
	
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Edge other = (Edge) obj;
        if (this.outgoing != other.outgoing) {
            return false;
        }
        if (this.incoming != other.incoming) {
            return false;
        }
        if (this.name != other.name) {
            return false;
        }
        return true;
    }
}
