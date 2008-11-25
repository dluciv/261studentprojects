package j2se.g261.eda.automator.representations.minimisation;

import java.util.Collection;
import java.util.Vector;
import java.util.HashMap;

/**
 *
 * @author dmitriy
 * class for minimize graph
 * only for graph with edges(MinimizedDFA)
 */
public class Minimisation {

    private MinimizedDFA edgeGraph;
    private Vector<Couple> different;
    private Vector<Character> uniq;
    private HashMap<Integer, Vector<Character>> storage;

    public Minimisation(MinimizedDFA g) {
        edgeGraph = g;
        different = new Vector<Couple>();
        uniq = new Vector<Character>();
        storage = new HashMap<Integer, Vector<Character>>();
    }

    /**
     * this method put to storage element i with key c
     * @param c - key
     * @param i - element
     */
    private void addToStorage(int c, char i) {
        if (storage.containsKey(c)) {
            Vector<Character> v = storage.get(c);
            if (!v.contains(i)) {
                v.add(i);
            }
        } else {
            Vector<Character> v = new Vector<Character>();
            v.add(i);
            storage.put(c, v);
        }
    }

    /**
     * find difference between two vectors
     * @param a - first vector
     * @param b - second vector
     * @return vector of all symbols consisted in a and not consisted in b
     */
    private Vector<Character> difference(Vector<Character> a, Vector<Character> b) {

        Vector<Character> diff = new Vector<Character>();
        for (char name : a) {

            if (!b.contains(name)) {
                diff.add(name);
            }

        }
        return diff;
    }

    /**
     * add absorbing state in graph for minimize
     */
    private void addAbsorbingState() {

        for (int i = 0; i < edgeGraph.sizeAll(); i++) {

            addToStorage(edgeGraph.getEdgeAt(i).getIncoming(), edgeGraph.getEdgeAt(i).getName());

            if (!uniq.contains(edgeGraph.getEdgeAt(i).getName())) {
                uniq.add(edgeGraph.getEdgeAt(i).getName());
            }

        }
        int states = storage.size();
        for (int j = 0; j < states + 1; j++) {
            if (j == 1) {
                j++;
            }
            Vector<Character> diff = new Vector<Character>();

            if (storage.get(j).size() == 0) {
                diff = uniq;
            } else {
                diff = difference(uniq, storage.get(j));
            }
            int size = diff.size();
            for (int k = 0; k < size; k++) {
                edgeGraph.add(new Edge(diff.get(k), j, states + 1));
            }
        }
        int size = uniq.size();
        for (int m = 0; m < size; m++) {
            char name = uniq.get(m);
            edgeGraph.add(new Edge(name, states + 1, states + 1));
        }
    }

    private Vector<Couple> findEquals(Couple c, Vector<Couple> toDelite) {

        for (Couple p : different) {
            if ((p.getFirst() == c.getSecond() || p.getSecond() == c.getSecond()) &&
                    (p.getFirst() != c.getFirst() && p.getSecond() != c.getSecond())) {
                if (!toDelite.contains(p)) {
                    toDelite.add(p);
                }
            }
        }
        return toDelite;
    }

    /**
     * this method minimize graph
     * @return minimize graph
     */
    public MinimizedDFA minimize() {
        Vector<Couple> diff = new Vector<Couple>();
        Vector<Couple> toDelite = new Vector<Couple>();
        addAbsorbingState();
        int num = storage.size() + 2;

        for (int i = 0; i < num; i++) {
            if (i == 1) {
                i++;
            }
            Couple c = new Couple(i, 1);
            diff.add(c);
        }

        for (int j = 0; j < num; j++) {
            for (int k = 0; k < num; k++) {
                if (j != 1 && k != 1 && j < k) {
                    different.add(new Couple(j, k));
                }
            }
        }

        helpToMinimize(diff);


        for (Couple c : different) {
            toDelite = findEquals(c, toDelite);
        }

        for (Couple c : toDelite) {
            different.remove(c);
        }


        for (Couple c : different) {
            Vector<Edge> listFirst;
            Collection<Edge> listSecond2;


            listFirst = edgeGraph.findIncomingEdge(c.getSecond());
            listSecond2 = edgeGraph.findOutgoingEdge(c.getSecond());

            
            Vector<Edge> listSecond = new Vector<Edge>();
            
            
            for (Edge e : listSecond2) {
                listSecond.add(e);
            }

            for (Edge i : listFirst) {
                edgeGraph.remove(i);
            }

            for (Edge i : listFirst) {
                Edge e = new Edge(i.getName(), i.getIncoming(), c.getFirst());
                if (i.getIncoming() == c.getSecond()) {
                    continue;
                }
                if (!edgeGraph.contains(e)) {
                    edgeGraph.add(e);
                }
            }


            for (Edge j : listSecond) {
                edgeGraph.remove(j);
            }

            for (Edge j : listSecond) {
                Edge e = new Edge(j.getName(), c.getFirst(), j.getOutgoing());
                if (j.getOutgoing() == c.getSecond()) {
                    continue;
                }
                if (!edgeGraph.contains(e)) {
                    edgeGraph.add(e);
                }
            }
        }

        Vector<Edge> list = edgeGraph.findIncomingEdge(storage.size() + 1);

        for (Edge h : list) {
            edgeGraph.remove(h);
        }
        
        System.out.println(edgeGraph);
        System.out.println(edgeGraph.numEdges(0));
        return edgeGraph;
        //((0011)|(1010))*(0|1)*
    }

    /**
     * this method finds couples of equal states
     * @param tmp - beginning vector of different states
     */
    private void helpToMinimize(Vector<Couple> tmp) {

        while (true) {

            Vector<Edge> listFirst = new Vector<Edge>();
            Vector<Edge> listSecond = new Vector<Edge>();
            Vector<Couple> newDiff = new Vector<Couple>();

            for (Couple t : tmp) {
                listFirst = edgeGraph.findIncomingEdge(t.getFirst());
                listSecond = edgeGraph.findIncomingEdge(t.getSecond());

                for (Edge j : listFirst) {
                    for (Edge k : listSecond) {
                        if (j.getName() == k.getName()) {

                            if (j.getIncoming() < k.getIncoming()) {
                                Couple c = new Couple(j.getIncoming(), k.getIncoming());
                                if (different.contains(c)) {
                                    newDiff.add(c);
                                }
                                different.remove(c);
                            } else {
                                Couple c = new Couple(k.getIncoming(), j.getIncoming());
                                if (different.contains(c)) {
                                    newDiff.add(c);
                                }
                                different.remove(c);
                            }
                        }
                    }
                }
            }
            if (newDiff.isEmpty()) {
                break;
            }
            tmp = newDiff;
        }

    }
}


 


