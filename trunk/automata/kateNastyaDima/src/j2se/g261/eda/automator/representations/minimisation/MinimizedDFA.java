package j2se.g261.eda.automator.representations.minimisation;

import java.util.HashMap;
import java.util.Vector;

/**
 * @author Dmitry
 * class for graph with edges
 */
public class MinimizedDFA {

    private Vector<Edge> all;
    HashMap<Integer, Vector<Edge>> allEdges;

    public MinimizedDFA() {
        all = new Vector<Edge>();
        allEdges = new HashMap<Integer, Vector<Edge>>();
    }

    public void add(Edge n) {
        all.add(n);
        addToAllEdges(n);
    }

    private void addToAllEdges(Edge i) {
        if (allEdges.containsKey(i.getIncoming())) {
            if (!allEdges.get(i.getIncoming()).contains(i)) {
                allEdges.get(i.getIncoming()).add(i);
            }

        } else {
            Vector<Edge> v = new Vector<Edge>();
            v.add(i);
            allEdges.put(i.getIncoming(), v);
        }
    }

    private void removeFromAllEdges(Edge e) {
        if (allEdges.containsKey(e.getIncoming())) {
            allEdges.get(e.getIncoming()).remove(e);
        } else {
            ;
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
        //removeFromAllEdges(e.getIncoming(),e.getName());
        removeFromAllEdges(e);
    }

    public boolean contains(Edge e) {
        return all.contains(e);
    }

    public Vector<Edge> findOutgoingEdge(int state) {
        Vector<Edge> list = new Vector<Edge>();
        list = allEdges.get(state);
        /*for (Edge e : all) {
        
        if (e.getIncoming() == state) {
        list.add(e);
        }
        
        }*/
        return list;
    }
}
