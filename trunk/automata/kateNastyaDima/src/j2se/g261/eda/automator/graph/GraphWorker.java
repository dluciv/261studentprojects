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
        Graph res = Graph.emptyGraph();

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
        return concatenateOR(g1, Graph.emptyGraph());
    }
    //zvzdochka Klini

    public static Graph concatanateANY(Graph g) {
        if (g == null) {
            return null;
        }
        Node eps1 = Node.epsilonNode();
        Node eps2 = Node.epsilonNode();


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
            Node n1 = g1.getNodeFromEndsAt(i);
            for (int j = 0; j < t; j++) {
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
        Node start = Node.startNode();
        Node end = Node.endNode();
        Vector<Node> deleted = new Vector<Node>();

        int h = g.allSize();
        for (int i = 0; i < h; i++) {
            Node n = g.getNodeFromAllAt(i);
            if (Node.isEpsilonNode(n)) {
                deleted.add(n);
            }
            if (g.isStart(n)) {
                start.addOutgoingNode(n);
                n.addIncomingNode(start);

            }
            if (g.isEnd(n)) {
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

    public static Graph makeDeterministic(Graph graph) {

        Graph g = graph.clone();
        boolean haveChanges = true;

        while (haveChanges) {
            haveChanges = false;
            for (int i = 0; i < g.allSize(); i++) {
                Node n = g.getNodeFromAllAt(i);
                Vector<Node> v = new Vector<Node>();
                for (int j = 0; j < n.getOutgoingSize(); j++) {
                    Node equaled = getEqualed(v, n.getOutgoingAt(j));

                    if (equaled == null) {
                        v.add(n.getOutgoingAt(j));
                    } else {
                        haveChanges = true;
                        n.getOutgoingAt(j).removeNodeFromIncoming(n);
                        for (int k = 0; k < n.getOutgoingAt(j).getOutgoingSize(); k++) {
                            if(!n.getOutgoingAt(j).getOutgoingAt(k).equals(n)){
                            equaled.addOutgoingNode(n.getOutgoingAt(j).getOutgoingAt(k));
                            n.getOutgoingAt(j).getOutgoingAt(k).addIncomingNode(equaled);
                            }else{
                            equaled.addOutgoingNode(equaled);
                            equaled.addIncomingNode(equaled);
                            n.getOutgoingAt(j).getOutgoingAt(k)
                                    .removeNodeFromIncoming(n.getOutgoingAt(j)
                                    .getOutgoingAt(k));
                            }
                        }
                        n.removeNodeFromOutgoing(n.getOutgoingAt(j));
                    }

                }

            }
        }

        int size = g.allSize();
        for (int i = 0; i < size; i++) {
            if (!g.isStart(g.getNodeFromAllAt(i)) && !g.getNodeFromAllAt(i).haveIncoming()) {
                Node n = g.getNodeFromAllAt(i);
                for (int j = 0; j < n.getOutgoingSize(); j++) {
                    Node out = n.getOutgoingAt(j);
                    out.removeNodeFromIncoming(n);
                    n.removeNodeFromOutgoing(out);
                }
                g.simpleDeleteNode(n);
                System.out.println("@@@@@@@@@@@@@");
                System.out.println(n);
                System.out.println("@@@@@@@@@@@@@");
                i--;
                size--;
            }

        }

        return g;

    }

    private static Node getEqualed(Vector<Node> v, Node n) {
        for (Node node : v) {
            if (node.getName() == n.getName()) {
                return node;
            }
        }

        return null;
    }
}
