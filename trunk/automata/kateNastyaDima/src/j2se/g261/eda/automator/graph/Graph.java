/*
 * Graph.java
 * 
 * Version 1.0
 * 
 * 16.0.2008
 * 
 * (c) "EDA"
 */
package j2se.g261.eda.automator.graph;

import j2se.g261.eda.automator.table.Table;
import j2se.g261.eda.automator.table.TableRecord;
import java.util.Vector;
import javax.naming.CompoundName;

/**
 *Represents graph with start and end nodes. Created for presentation of 
 * NFA and DFA. All actions you may perform with graph locates in GraphWorker 
 * class. Graph object just a storage for nodes with possibility of node 
 * ordering accordingly to topologoical role(start, end or nothing)
 * 
 * 
 * @author Anastasiya
 * @author Dmitry
 * 
 * @see j2se.g261.eda.automator.graph.GraphWorker
 */
public class Graph implements Cloneable {

    /**
     * Storage for start nodes
     */
    private Vector<Node> starts;
    /**
     * Storage for end nodes
     */
    private Vector<Node> ends;
    /**
     * Storage for all nodes
     */
    private Vector<Node> all;

    /**
     * Constructor with one node which will be only node in graph.
     * 
     * @param root 
     *        root node. This node will be only node in graph. It is start 
     *        and end node.
     */
    public Graph(Node root) {
        starts = new Vector<Node>();
        ends = new Vector<Node>();
        all = new Vector<Node>();
        starts.add(root);
        ends.add(root);
        all.add(root);
    }
    
    private Graph(Vector<Node> allNodes, Vector<Node> startNodes, Vector<Node> endNodes){
        starts = new Vector<Node>();
        ends = new Vector<Node>();
        all = new Vector<Node>();

        for (Node node : allNodes) {
            all.add(node.cloneWithoutConnections());
        }
        
        for (Node node : startNodes) {
            starts.add(all.get(all.indexOf(node)));
        }
        
        for (Node node : endNodes) {
            ends.add(all.get(all.indexOf(node)));
        }
        
        for (Node node : allNodes) {
            Node newNode = all.get(all.indexOf(node));
            for (int i = 0; i < node.getIncomingSize(); i++) {
                newNode.addIncomingNode(all.get(all.indexOf(node.getIncomingAt(i))));
            }
            for (int i = 0; i < node.getOutgoingSize(); i++) {
                newNode.addOutgoingNode(all.get(all.indexOf(node.getOutgoingAt(i))));
            }
        }
        
    }

    /**
     * Recursively adds node to graph. All connected node will be added too.
     * 
     * @param n node to be added to graph. 
     */
    void addNode(Node n) {
        if (!all.contains(n)) {
            all.add(n);
        }

        int num = n.getOutgoingSize();

        for (int i = 0; i < num; i++) {
            Node n1 = n.getOutgoingAt(i);
            if (!all.contains(n1)) {
                addNode(n1);
            }
        }
    }

    /**
     * Deletes node from graph with keeping of connections between nodes
     * 
     * @param n
     *        node to be deleted
     */
    void deleteNode(Node n) {
        n.prepareForDeleting();
        all.remove(n);
        starts.remove(n);
        ends.remove(n);
    }

    void simpleDeleteNode(Node n) {
        all.remove(n);
        starts.remove(n);
        ends.remove(n);
    }

    /**
     * Represents graph as string (for debug)
     * 
     * @return string representation of graph
     */
    @Override
    public String toString() {
        String s = "Graph: " + all.size() + "nodes\n";

        for (Node node : all) {
            s += node.toString();
            s += "\n";

        }

        return s;
    }

    /**
     * Constructs epsilon-graph. Epsilon-graph have one node with epsilon-name.
     * 
     * @return epsilon-graph
     */
    public static Graph emptyGraph() {
        return new Graph(Node.epsilonNode());
    }

    /**
     * 
     * Walks through the graph and checks each node if it haven't incoming 
     * connections. If true when marks node as start.
     * 
     * @see j2se.g261.eda.automator.graph.Graph#markAllEnds() 
     */
    void markAllStarts() {
        starts.removeAll(starts);

        for (Node node : all) {

            if (!node.haveIncoming()) {
                starts.add(node);
            }
        }
    }

    /**
     * 
     * Walks through the graph and checks each node if it haven't outgoing 
     * connections. If true when marks node as end.
     * 
     * @see j2se.g261.eda.automator.graph.Graph#markAllStarts() 
     * @see j2se.g261.eda.automator.graph.Graph#isEnd(j2se.g261.eda.automator.graph.Node) 
     */
    void markAllEnds() {

        ends.removeAll(ends);

        for (Node node : all) {
            if (!node.haveOutgoing()) {
                ends.add(node);
            }
        }

    }

    /**
     * Checks if node marked as start node
     * 
     * @param n node to be tested
     * @return {@code  true} if node marked as start, {@code false} otherwise
     * 
     * @see j2se.g261.eda.automator.graph.Graph#markAllStarts() 
     * @see j2se.g261.eda.automator.graph.Graph#isEnd(j2se.g261.eda.automator.graph.Node) 
     */
    boolean isStart(Node n) {
        return (starts.contains(n));
    }

    /**
     * Returns number of start nodes (size of start node list)
     * 
     * @return start nodes size
     */
    public int startsSize() {
        return starts.size();
    }

    /**
     * Returns number of end nodes (size of end node list)
     * 
     * @return end nodes size
     */
    int endsSize() {
        return ends.size();
    }

    /**
     * Returns number of nodes ing graph(size of all node list)
     * 
     * @return all nodes size
     */
    public int allSize() {
        return all.size();
    }

    /**
     * Returns node in specified position in start node list.
     * 
     * @param index index of element to return
     * @return node at the specified index
     */
    public Node getNodeFromStartsAt(int index) {
        return (Node) starts.get(index);
    }

    /**
     * Returns node in specified position in end node list.
     * 
     * @param index index of element to return
     * @return node at the specified index
     */
    Node getNodeFromEndsAt(int index) {
        return (Node) ends.get(index);
        
    }

    /**
     * Returns node in specified position in all node list.
     * 
     * @param index index of element to return
     * @return node at the specified index
     */
    public Node getNodeFromAllAt(int index) {
        return (Node) all.get(index);
    }

    public int getNodeIndex(Node n) {
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i) == n) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if node marked as end node
     * 
     * @param n node to be tested
     * @return {@code  true} if node marked as end, {@code false} otherwise
     * 
     * @see j2se.g261.eda.automator.graph.Graph#markAllEnds() 
     * @see j2se.g261.eda.automator.graph.Graph#isStart(j2se.g261.eda.automator.graph.Node) 
     */
    boolean isEnd(Node n) {
        return ends.contains(n);
    }

    //@todo rewrite accordingly to replacing; document
    public void fillDeterminatedTable(Table table) {
        table.clear();
        int a = allSize();
        for (int i = 0; i < a; i++) {
            writeNodeInfoToTable(getNodeFromAllAt(i), table);

        }
    }

    private void writeNodeInfoToTable(Node n, Table table) {
        TableRecord t = new TableRecord();

        if (Node.isEndNode(n)) {
//            t.add(TableRecord.SYMBOL_END, -1);

            return;
        }
//        if(Node.isStartNode(n)){
//            t.add(TableRecord.SYMBOL_START, getNodeIndex(n));
//        }
        int a = n.getOutgoingSize();
        for (int i = 0; i < a; i++) {
            Node n1 = n.getOutgoingAt(i);
            char c = n1.getName();
            if (c == '\t') {
                c = TableRecord.SYMBOL_START;
            } else if (c == '\n') {
                c = TableRecord.SYMBOL_END;
            }
            t.add(c, getNodeIndex(n1));
        }
        table.add(getNodeIndex(n), t);
    }

    @Override
    public Graph clone() {
        return new Graph(all, starts, ends);
    }
    

//    public static void main(String[] args) {
//        Vector<Node> v = new Vector<Node>();
//
//        v.add(new Node('a'));
//        System.out.println(v.contains(new Node('a')));
//
//
//    }
}
