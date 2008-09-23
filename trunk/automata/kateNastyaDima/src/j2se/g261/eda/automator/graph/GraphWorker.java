/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.graph;

import java.util.Iterator;

/**
 *
 * @author nastya
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
        Iterator<Node> resIt = res.iteratorStarts();

        while (resIt.hasNext()) {
            Node parent = resIt.next();
            Iterator<Node> g1It = g1.iteratorStarts();
            while (g1It.hasNext()) {
                Node n = g1It.next();
                parent.addOutgoingNode(n);
                n.addIncomingNode(parent);
                res.addNode(n);
            }
            Iterator<Node> g2It = g2.iteratorStarts();
            while (g2It.hasNext()) {
                Node n = g2It.next();
                parent.addOutgoingNode(n);
                n.addIncomingNode(parent);
                res.addNode(n);
            }
        }

        markAllNodes(res);
        System.out.println("$$$$$$$$");
        System.out.println(g1);
        System.out.println("%%%%");
        System.out.println(res);

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


        Iterator<Node> a = g.iteratorEnds();
        while (a.hasNext()) {
            Node n = a.next();
//            System.out.println("&&&");
//            System.out.println(n);

            Iterator<Node> b = g.iteratorStarts();

            while (b.hasNext()) {
                Node n1 = b.next();
                n.addOutgoingNode(n1);
                n1.addIncomingNode(n);
                n.addOutgoingNode(eps1);
                eps1.addIncomingNode(n);
                n1.addIncomingNode(eps2);
                eps2.addOutgoingNode(n1);
            }
        }

        markAllNodes(g);
        System.out.println("^^^^^");
        System.out.println(g);

        return concatenateONE(g);
    }

    public static Graph concatanateAND(Graph g1, Graph g2) {
        if (g1 == null) {
            return g2;
        }
        if (g2 == null) {
            return g1;
        }
        Iterator<Node> a = g1.iteratorEnds();
        while (a.hasNext()) {
            Node n1 = a.next();

            Iterator<Node> b = g2.iteratorStarts();
            while (b.hasNext()) {
                Node n2 = b.next();
                n1.addOutgoingNode(n2);
                n2.addIncomingNode(n1);
            }
        }

        a = g2.iteratorStarts();
        while (a.hasNext()) {
            g1.addNode(a.next());
        }

        g1.markAllEnds();

        return g1;
    }

    //remove epsilon-nodes
    public static void makeClosure(Graph g) {
        if (g == null) {
            return;
        }
        Node start = Node.getStartNode();
        Node end = Node.getEndNode();

        g.addNode(start);
        g.addNode(end);
        Iterator<Node> a = g.iteratorAll();
        while (a.hasNext()) {
            Node n = a.next();
            if (Node.isEpsilonNode(n)) {
                g.deleteNode(n);
                if (g.isStart(n)) {
                    Iterator<Node> i = n.getOutcomingIterator();
                    while (i.hasNext()) {
                        Node n1 = i.next();
                        n1.addIncomingNode(start);
                        start.addOutgoingNode(n1);
                    }
                    i = n.getIncomingIterator();
                    while (i.hasNext()) {
                        Node n1 = i.next();
                        n1.addOutgoingNode(end);
                        end.addIncomingNode(n1);
                    }
                }
            }
        }

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
}
