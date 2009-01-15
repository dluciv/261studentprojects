package Node;

import graph.*;

public class Node{
    
    public Condition condition;
    private ArcSet outgoing;
    private ArcSet incoming;
       
    public Node(){}
    
    public Node(Node n)
    {
        this.condition = n.condition;
        this.outgoing = n.outgoing;
        this.incoming = n.incoming;        
    }   
    
    public Node(Integer condition) {
        Condition res = new Condition(condition);
        this.condition = res;
    }
    
    public Node(Condition condition) {
        this.condition = condition;        
    }
    
    public Node(Condition condition, ArcSet outgoing, ArcSet incoming) {
        this.condition = condition;
        this.outgoing = outgoing;
        this.incoming = incoming;
    }
    
    public void setCondition(Integer condition) {
        Condition res = new Condition(condition);
        this.condition.condition.clear();
        this.condition = res;
    }
    
    public Condition getCondition() {
        return condition;
    }
    
    public void setIncoming(ArcSet incoming) {
        this.incoming = incoming;
    }
    
    public ArcSet getIncoming() {
        if(incoming == null)
            incoming = new ArcSet();
        return incoming;
    }
    
    public void setOutgoing(ArcSet outgoing) {
        this.outgoing = outgoing;
    }
    
    public ArcSet getOutgoing() {
        if(outgoing == null)
            outgoing = new ArcSet();
        return outgoing;
    }
    
    public ArcSet getAll() {
        ArcSet all = new ArcSet();
        all.addArcSet(incoming);
        all.addArcSet(outgoing);
        return all;
    }    
    
    // pure node delete
    public void prepareForDeleting() {
        
        outgoing.removeNode(this);
        incoming.removeNode(this);
        
        for(Node tmp : incoming.getNodeSet())
            tmp.outgoing.addArcSet(outgoing);
        
        for(Node tmp : outgoing.getNodeSet())
            tmp.incoming.addArcSet(incoming);
        
        for(Node tmp : outgoing.getNodeSet())
            tmp.incoming.removeNode(this);
        
        for(Node tmp : incoming.getNodeSet())
            tmp.outgoing.removeNode(this);       
    }   

    @Override
    public String toString() {  
        
        String s = "Node: " + condition;
        
        s += "\nincoming \n" + incoming;
        s += "outgoing \n" + outgoing;
        return s;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        if (this.condition != other.condition) {
            return false;
        }        
        if (this.incoming != other.incoming) {
            return false;
        }
        if (this.outgoing != other.outgoing) {
            return false;
        }
        return true;
    }   
    
    public boolean haveIncoming(){
        if(incoming == null)
            return false;
        return incoming.set.size() != 0;
    }
    
    public boolean haveOutgoing(){
        if(outgoing == null)
            return false;
        return outgoing.set.size() != 0;
    }    
    
    public int getOutgoingSize(){
        return outgoing.set.size();
    }
    
    public int getIncomingSize(){
        return incoming.set.size();
    }        
    
    public void shiftConnections(Node to){
        to.incoming.addArcSet(incoming);
        to.outgoing.addArcSet(outgoing);        
    }    

    public void addIncomingNode(Node n, Character c) {
        if(incoming == null)
            incoming = new ArcSet();
        if(!incoming.containsNode(n)){
            Arc res = new Arc(n, c);
            incoming.addArc(res);
        }
    }

    public void addOutgoingNode(Node n, Character c) {
        if(outgoing == null)
            outgoing = new ArcSet();
        
            Arc res = new Arc(n, c);
            outgoing.addArc(res);        
    } 
}