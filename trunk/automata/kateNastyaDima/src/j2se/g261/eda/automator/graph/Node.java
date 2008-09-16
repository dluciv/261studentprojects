/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.graph;

import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author nastya
 */
public class Node {
    
    private String name;
    private boolean start;
    private boolean end;
    private Vector<Node> outgoing;
    private Vector<Node> incoming;

    public Node(String name, boolean start, boolean end) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.outgoing = new Vector<Node>();
        this.incoming = new Vector<Node>();
    }

    public Node(String name) {
        this.name = name;
        this.start = false;
        this.end = false;
        this.outgoing = new Vector<Node>();
        this.incoming = new Vector<Node>();
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }
    
    public void addIncomingNode(Node n) {
        incoming.add(n);
    }
    
    public void addOutgoingNode(Node n) {
        outgoing.add(n);
    }
    
    public void prepareForDeleting() {
        
        Iterator<Node> out = outgoing.iterator();
        while (out.hasNext()) {
            out.next().removeNodeFromIncoming(this);
        }
        
        
        Iterator<Node> i = incoming.iterator();
        while (i.hasNext()) {
            Node n = i.next();
            n.removeNodeFromOutgoing(this);
            Iterator<Node> o = outgoing.iterator();
            while (o.hasNext()) {
                Node n1 = o.next();
                n.addOutgoingNode(n1);
                n1.addIncomingNode(n);
            }
            
        }
        
        incoming = null;
        outgoing = null;
        
    }

    public void removeNodeFromIncoming(Node n) {
        incoming.remove(n);
    }

    public void removeNodeFromOutgoing(Node n) {
        outgoing.remove(n);
    }

    @Override
    public String toString() {
        return "Node: " + name + "; [start: " + String.valueOf(start) + "; end: " + String.valueOf(end) + "]";
    }

    
}
