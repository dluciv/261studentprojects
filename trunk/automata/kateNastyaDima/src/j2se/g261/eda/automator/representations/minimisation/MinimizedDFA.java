package j2se.g261.eda.automator.representations.minimisation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

/**
 * @author Dmitry
 * class for graph with edges
 */
public class MinimizedDFA {

    private Vector<Edge> all;
    HashMap<Integer, HashMap<Character, Edge>> allEdges;

    public MinimizedDFA() {
        all = new Vector<Edge>();
        allEdges = new HashMap<Integer, HashMap<Character, Edge>>();
    }

    public void add(Edge n) {
        all.add(n);
        addToAllEdges(n);
    }

    private void addToAllEdges(Edge i) {
        if (allEdges.containsKey(i.getIncoming())) {
            if (!allEdges.get(i.getIncoming()).containsKey(i.getName())) {
                allEdges.get(i.getIncoming()).put(i.getName(), i);
            }

        } else {
            HashMap<Character, Edge> v = new HashMap<Character, Edge>();
            v.put(i.getName(), i);
            allEdges.put(i.getIncoming(), v);
            
        }
    }

    private void removeFromAllEdges(Edge e) {
        if (allEdges.containsKey(e.getIncoming())) {
            allEdges.get(e.getIncoming()).remove(e.getName());
        } 
    }

    public int sizeAll() {
        return this.all.size();
    }

    public Edge getEdgeAt(int index) {
        return this.all.get(index);
    }

    public Vector<Edge> findIncomingEdge(int state) {
        Vector<Edge> list = new Vector<Edge>();

        for (Edge e : all) {

            if (e.getOutgoing() == state) {
                list.add(e);
            }

        }
        return list;
    }

    public void remove(Edge e) {
        all.remove(e);
        removeFromAllEdges(e);
    }

    public boolean contains(Edge e) {
        return all.contains(e);
    }

    public Collection<Edge> findOutgoingEdge(int state) {
        return  allEdges.get(state).values();
    }
    
    public HashMap<Character, Edge> getX(int state){
    	return allEdges.get(state);
    } 
    
}
