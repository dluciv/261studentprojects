/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.representations.nfa;

import j2se.g261.eda.automator.representations.nfa.NFA;
import java.util.Vector;

/**
 *
 * @author Katerina, Anastasiya
 */
public class NFAWorker {

    private NFAWorker() {
    }

    //slogenie
    public static NFA concatenateOR(NFA g1, NFA g2) {
        if (g1 == null) {
            return g2;
        }
        if (g2 == null) {
            return g1;
        }
        NFA res = NFA.emptyGraph();

        int resNum = res.startsSize();

        for (int k = 0; k < resNum; k++) {

            NFANode parent = res.getNodeFromStartsAt(k);

            int num = g1.startsSize();
            for (int i = 0; i < num; i++) {
                NFANode n = g1.getNodeFromStartsAt(i);
                parent.addOutgoingNode(n);
                n.addIncomingNode(parent);
                res.addNode(n);
            }

            num = g2.startsSize();
            for (int i = 0; i < num; i++) {
                NFANode n = g2.getNodeFromStartsAt(i);
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
    public static NFA concatenateONE(NFA g1) {
        return concatenateOR(g1, NFA.emptyGraph());
    }
    //zvzdochka Klini

    public static NFA concatanateANY(NFA g) {
        if (g == null) {
            return null;
        }
        NFANode eps1 = NFANode.epsilonNode();
        NFANode eps2 = NFANode.epsilonNode();


        g.addNode(eps1);
        g.addNode(eps2);

        int h = g.endsSize();
        for (int i = 0; i < h; i++) {
            NFANode n = g.getNodeFromEndsAt(i);
            int t = g.startsSize();
            for (int j = 0; j < t; j++) {
                NFANode n1 = g.getNodeFromStartsAt(j);
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

    public static NFA concatanateAND(NFA g1, NFA g2) {
        if (g1 == null) {
            return g2;
        }
        if (g2 == null) {
            return g1;
        }
        int h = g1.endsSize();
        int t = g2.startsSize();
        for (int i = 0; i < h; i++) {
            NFANode n1 = g1.getNodeFromEndsAt(i);
            for (int j = 0; j < t; j++) {
                NFANode n2 = g2.getNodeFromStartsAt(j);
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
    public static void makeClosure(NFA g) {
        if (g == null) {
            return;
        }
        NFANode start = NFANode.startNode();
        NFANode end = NFANode.endNode();
        Vector<NFANode> deleted = new Vector<NFANode>();

        int h = g.allSize();
        for (int i = 0; i < h; i++) {
            NFANode n = g.getNodeFromAllAt(i);
            if (NFANode.isEpsilonNode(n)) {
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

        for (NFANode node : deleted) {
            g.deleteNode(node);
        }

        g.addNode(start);
        g.addNode(end);


        markAllNodes(g);

    }
//mark all start and ends nodes

    public static void markAllNodes(NFA g) {
        if (g == null) {
            return;
        }

        g.markAllStarts();
        g.markAllEnds();
    }

//    public static NFA makeDeterministic(NFA graph) {
//
//        NFA g = graph.clone();
//        boolean haveChanges = true;
//
//        while (haveChanges) {
//            haveChanges = false;
//            for (int i = 0; i < g.allSize(); i++) {
//                NFANode n = g.getNodeFromAllAt(i);
//                Vector<NFANode> v = new Vector<NFANode>();
//                for (int j = 0; j < n.getOutgoingSize(); j++) {
//                    NFANode equaled = getEqualed(v, n.getOutgoingAt(j));
//
//                    if (equaled == null) {
//                        v.add(n.getOutgoingAt(j));
//                    } else {
//                        haveChanges = true;
//                        n.getOutgoingAt(j).removeNodeFromIncoming(n);//1
//
//                        for (int k = 0; k < n.getOutgoingAt(j).getOutgoingSize(); k++) {
//                            if(!n.getOutgoingAt(j).getOutgoingAt(k).equals(n)){
//                            equaled.addOutgoingNode(n.getOutgoingAt(j).getOutgoingAt(k));
//                            n.getOutgoingAt(j).getOutgoingAt(k).addIncomingNode(equaled);
//                            }else{
//                            equaled.addOutgoingNode(equaled);
//                            equaled.addIncomingNode(equaled);
//                            n.getOutgoingAt(j).getOutgoingAt(k)
//                                    .removeNodeFromIncoming(n.getOutgoingAt(j)
//                                    .getOutgoingAt(k));
//                            }
//                        }
//
//                        n.removeNodeFromOutgoing(n.getOutgoingAt(j));//2
//                    }
//
//                }
//
//            }
//        }
//
//        int size = g.allSize();
//        for (int i = 0; i < size; i++) {
//            if (!g.isStart(g.getNodeFromAllAt(i)) && !g.getNodeFromAllAt(i).haveIncoming()) {
//                NFANode n = g.getNodeFromAllAt(i);
//                for (int j = 0; j < n.getOutgoingSize(); j++) {
//                    NFANode out = n.getOutgoingAt(j);
//                    out.removeNodeFromIncoming(n);
//                    n.removeNodeFromOutgoing(out);
//                }
//                g.simpleDeleteNode(n);
//                System.out.println("@@@@@@@@@@@@@");
//                System.out.println(n);
//                System.out.println("@@@@@@@@@@@@@");
//                i--;
//                size--;
//            }
//
//        }
//
//        return g;
//
//    }
//
//
//    private static NFANode getEqualed(Vector<NFANode> v, NFANode n) {
//        for (NFANode node : v) {
//            if (node.getName() == n.getName()) {
//                return node;
//            }
//        }
//
//        return null;
//    }


}
