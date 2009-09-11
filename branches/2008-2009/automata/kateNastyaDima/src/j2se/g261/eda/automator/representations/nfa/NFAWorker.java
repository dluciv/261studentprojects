/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.representations.nfa;

import j2se.g261.eda.automator.representations.nfa.NFA;
import j2se.g261.eda.automator.representations.table.Table;
import j2se.g261.eda.automator.representations.table.TableRecord;
import java.util.Vector;

/**
 * Different operation over a graph 
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

    public static Table generateTable(NFA g) {
        Table table = new Table();

        int a = g.allSize();
        for (int i = 0; i < a; i++) {
            writeNodeInfoToTable(g, g.getNodeFromAllAt(i), table);

        }
        return table;
    }

    private static void writeNodeInfoToTable(NFA g, NFANode n, Table table) {
        TableRecord t = new TableRecord();

        if (NFANode.isEndNode(n)) {
            char c = TableRecord.SYMBOL_END;
            t.add(c, g.getNodeIndex(n));
        } else {
            int a = n.getOutgoingSize();
            for (int i = 0; i < a; i++) {
                NFANode n1 = (NFANode) n.getOutgoingAt(i);

                char c = n1.getName();
                if (c == NFANode.START) {
                    c = TableRecord.SYMBOL_START;
                } else if (c == NFANode.END) {
                    c = TableRecord.SYMBOL_END;
                }
                t.add(c, g.getNodeIndex(n1));
            }
        }
        table.add(g.getNodeIndex(n), t);
    }
}
