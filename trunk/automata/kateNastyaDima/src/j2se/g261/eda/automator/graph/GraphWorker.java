/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.graph;

import java.util.Vector;

/**
 *
 * @author Katerina, Anastasiya
 */
public class GraphWorker {

    private GraphWorker() {
    }

    //slogenie
    public static Graph concatenateOR(Graph g1, Graph g2) {
        if (g1 == null) {
            return g2;
        }
        if (g2 == null) {
            return g1;
        }
        Graph res = Graph.getEmpty();

        int resNum = res.startsSize();

        for (int k = 0; k < resNum; k++) {

            Node parent = res.getNodeFromStartsAt(k);

            int num = g1.startsSize();
            for (int i = 0; i < num; i++) {
                Node n = g1.getNodeFromStartsAt(i);
                parent.addOutgoingNode(n);
                n.addIncomingNode(parent);
                res.addNode(n);
            }

            num = g2.startsSize();
            for (int i = 0; i < num; i++) {
                Node n = g2.getNodeFromStartsAt(i);
                parent.addOutgoingNode(n);
                n.addIncomingNode(parent);
                res.addNode(n);
            }
        }

        markAllNodes(res);

        return res;
    }

    //slogenie s 0
    //dlya togo chto by po zadache razdelyat' dva sluchaya
    public static Graph concatenateONE(Graph g1) {
        return concatenateOR(g1, Graph.getEmpty());
    }
    //zvzdochka Klini

    public static Graph concatanateANY(Graph g) {
        if (g == null) {
            return null;
        }
        Node eps1 = Node.getEpsilonNode();
        Node eps2 = Node.getEpsilonNode();


        g.addNode(eps1);
        g.addNode(eps2);

        int h = g.endsSize();
        for (int i = 0; i < h; i++) {
            Node n = g.getNodeFromEndsAt(i);
            int t = g.startsSize();
            for (int j = 0; j < t; j++) {
                Node n1 = g.getNodeFromStartsAt(j);
                n.addOutgoingNode(n1);
                n1.addIncomingNode(n);
                n.addOutgoingNode(eps1);
                eps1.addIncomingNode(n);
                n1.addIncomingNode(eps2);
                eps2.addOutgoingNode(n1);
            }
        }


        markAllNodes(g);

        return concatenateONE(g);
    }

    public static Graph concatanateAND(Graph g1, Graph g2) {
        if (g1 == null) {
            return g2;
        }
        if (g2 == null) {
            return g1;
        }
        int h = g1.endsSize();
        int t = g2.startsSize();
        for (int i = 0; i < h; i++) {
            System.out.println(i);
            Node n1 = g1.getNodeFromEndsAt(i);
            for (int j = 0; j < t; j++) {
                System.out.println("===" + j);
                Node n2 = g2.getNodeFromStartsAt(j);
                n1.addOutgoingNode(n2);
                n2.addIncomingNode(n1);
            }

        }


        for (int i = 0; i < t; i++) {
            g1.addNode(g2.getNodeFromStartsAt(i));

        }

        markAllNodes(g1);

        return g1;
    }

    //remove epsilon-nodes
    public static void makeClosure(Graph g) {
        if (g == null) {
            return;
        }
        Node start = Node.getStartNode();
        Node end = Node.getEndNode();
        Vector<Node> deleted = new Vector<Node>();

        int h = g.allSize();
        System.out.println("&&&&&&&");
        System.out.println(h);
        for (int i = 0; i < h; i++) {
            Node n = g.getNodeFromAllAt(i);
            System.out.println(i);
            System.out.println(n);
            if (Node.isEpsilonNode(n)) {
                System.out.println("delete");
                deleted.add(n);
            }
                if (g.isStart(n)) {
                    start.addOutgoingNode(n);
                    n.addIncomingNode(start);
//                    int t = n.getOutgoingSize();
//                    for (int j = 0; j < t; j++) {
//                        Node n1 = n.getOutgoingAt(j);
//                        n1.addIncomingNode(start);
//                        start.addOutgoingNode(n1);
//                    }
//
//                    t = n.getIncomingSize();
//                    for (int j = 0; j < t; j++) {
//
//                        Node n1 = n.getIncomingAt(j);
//                        n1.addOutgoingNode(end);
//                        end.addIncomingNode(n1);
//                    }
                }
                if(g.isEnd(n)){
                    end.addIncomingNode(n);
                    n.addOutgoingNode(end);
                }
        }
        
        for (Node node : deleted) {
            g.deleteNode(node);
        }

        g.addNode(start);
        g.addNode(end);


        markAllNodes(g);
    }
//mark all start and ends nodes

    public static void markAllNodes(Graph g) {
        if (g == null) {
            return;
        }

        g.markAllStarts();
        g.markAllEnds();
    }
    
    public static void makeDeterministic(Graph graph){
        int sz = graph.allSize();
        
        for (int i = 0; i < sz; i++) {
            Node n = graph.getNodeFromAllAt(i);
            
            Vector<Node> uniq = new Vector<Node>();
            
            int out = n.getOutgoingSize();
            
            for (int j = 0; j < out; j++) {
                Node n1 = n.getOutgoingAt(j);
                if(uniq.contains(n1)){
                    graph.simpleDeleteNode(n1);
                    Node n2 = getEqualed(uniq, n1);
                    n1.shiftConnections(n2);
                }else{
                    uniq.add(n1);
                }
                
            }
            
        }
        
    }
    
    private static Node getEqualed(Vector<Node> v, Node n){
        for (Node node : v) {
            if(node.equals(n)) return node;
        }
        
        return null;
    }
    
}
