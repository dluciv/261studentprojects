/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.dot;

import j2se.g261.eda.automator.graph.Graph;
import j2se.g261.eda.automator.graph.Node;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Dmitry
 * Class for dot file
 */
public class DotUtils {

    private static final String DIGRAPH = "digraph";
    private Graph g;
    private int index = 0;

    public DotUtils(Graph g) {
        this.g = g;
    }

    /**
     * This method create a dot file(In a correct format) the containing count
     * conclude file in BufferedWriter
     * @param filename the name of this file
     * @return Temp file with dot-representation of graph
     * @throws IOException if some IO errors occured
     * @throws DotException if some internal errors occured
     */
    public File generateDotFileForNFA(String filename) throws IOException, DotException {
        index = 0;
        HashMap<Node, String> passed = new HashMap<Node, String>();
        File f1 = File.createTempFile(filename, ".dot");
//        f1.deleteOnExit();
        BufferedWriter bf = new BufferedWriter(new FileWriter(f1));
        bf.write(DIGRAPH);
        bf.write(" g{");
        bf.newLine();
        int num = g.startsSize();
        for (int i = 0; i < num; i++) {
            processNode(g.getNodeFromStartsAt(i), bf, passed);
        }
        bf.write("}");
        bf.close();
        return f1;
    }

    /**
     * This method writes down node in a file
     * The starts nodes have a rectangle form, the end nodes have circle form.
     * @param n the node added in count
     * @param bf the buffering
     * @return "name" of node
     * @throws IOException
     */
    
    private String writeNode(Node n, BufferedWriter bf) throws IOException {
        String s1;
        if (Node.isStartNode(n)) {
        	s1 = "node" + index + "[label = \"" + "START" + "\" ";
            s1 += ", shape = \"rectangle\"";
        }
        else if (Node.isEndNode(n)) {
        	s1 = "node" + index + "[label = \"" + "END" + "\" ";
            s1 += ", shape = \"rectangle\"";
        }
        else {
        	s1 = "node" + index + "[label = \"" + n.getName() + n.getNumber() + "\" ";
        	s1 = "node" + index + "[label = \"" + n.getName() + "\" ";
            s1 += ", shape = \"circle\"";
        }
        bf.write(s1 + "];");
        bf.newLine();
        return "node" + index++;
    }


    /**
     * This method write in file arc from node n1(name) to node n2(name)
     * @param n1 "name" of first node
     * @param n2 "name" of second node
     * @param bf the buffering
     * @throws IOException
     */
    
    private void writeEdge(String n1, String n2, BufferedWriter bf) throws IOException {
        bf.write(n1 + "->" + n2 + ";");
        bf.newLine();
    }

    /**
     * This method finds all pairs of nodes for filing
     * @param n node for which the pair is searched
     * @param bf the buffering
     * @param passed the list of passed nodes
     * @throws IOException
     * @throws DotException
     */
    
    private void processNode(Node n, BufferedWriter bf, HashMap<Node, String> passed) throws IOException, DotException {
        System.out.println("" + n.getName() + n.getNumber());
        if (!passed.containsKey(n)) {
            passed.put(n, writeNode(n, bf));
        }


        int num = n.getOutgoingSize();
        for (int i = 0; i < num; i++) {
            Node n1 = n.getOutgoingAt(i);
            if (passed.containsKey(n1) && !Node.isEndNode(n1))
            {
            	writeEdge(passed.get(n), passed.get(n1), bf);
            	continue;
            }
            processNode(n1, bf, passed);
            if (passed.containsKey(n) && passed.containsKey(n1)) {
                writeEdge(passed.get(n), passed.get(n1), bf);
            }
            else {
                throw new DotException();
            }
        }
    }

//    public static void main(String[] args) {
////        Graph g1 = new Graph();
////        g1.addNode(new Node('a'));
////        GraphWorker.markAllNodes(g1);
////        Graph g2 = new Graph();
////        g2.addNode(new Node('b'));
////        GraphWorker.markAllNodes(g2);
////        Graph g3 = GraphWorker.concatanateAND(g1, g2);
////        System.out.println("______________G3______________");
////        System.out.println(g3);
////        System.out.println("______________________________");
////
////        g1 = new Graph();
////        g2 = new Graph();
////        g1.addNode(new Node('c'));
////        GraphWorker.markAllNodes(g1);
////        g2.addNode(new Node('d'));
////        GraphWorker.markAllNodes(g2);
////        Graph g4 = GraphWorker.concatanateANY(g1);
////        System.out.println("______________G4______________");
////        System.out.println(g4);
////        System.out.println("______________________________");
////        Graph g5 = GraphWorker.concatenateONE(g2);
////        System.out.println("______________G5______________");
////        System.out.println(g5);
////        System.out.println("______________________________");
////        Graph g0 = GraphWorker.concatanateAND(g4, g5);
////        System.out.println("______________G0______________");
////        System.out.println(g0);
////        System.out.println("______________________________");
////        Graph g6 = GraphWorker.concatenateOR(g3, g0);
////        System.out.println("______________G6______________");
////        System.out.println(g6);
////        System.out.println("______________________________");
////        GraphWorker.makeClosure(g6);
////        System.out.println(g6);

//
//        g1 = new Graph();
//        g2 = new Graph();
//        g1.addNode(new Node('c'));
//        GraphWorker.markAllNodes(g1);
//        g2.addNode(new Node('d'));
//        GraphWorker.markAllNodes(g2);
//        Graph g4 = GraphWorker.concatanateANY(g1);
//        System.out.println("______________G4______________");
//        System.out.println(g4);
//        System.out.println("______________________________");
//        Graph g5 = GraphWorker.concatenateONE(g2);
//        System.out.println("______________G5______________");
//        System.out.println(g5);
//        System.out.println("______________________________");
//        Graph g0 = GraphWorker.concatanateAND(g4, g5);
//        System.out.println("______________G0______________");
//        System.out.println(g0);
//        System.out.println("______________________________");
//        Graph g6 = GraphWorker.concatenateOR(g3, g0);
//        System.out.println("______________G6______________");
//        System.out.println(g6);
//        System.out.println("______________________________");
//        GraphWorker.makeClosure(g6);
//        System.out.println(g6);

//        System.out.println(g6);
//        DotUtils d = new DotUtils(g6);
//        try {
//            d.generateDotFileForNFA("DOTFILE");
//        } catch (IOException ex) {
//            Logger.getLogger(DotUtils.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (DotException ex) {
//            Logger.getLogger(DotUtils.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }


