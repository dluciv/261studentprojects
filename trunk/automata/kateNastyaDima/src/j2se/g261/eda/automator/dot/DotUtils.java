/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.dot;

import j2se.g261.eda.automator.graph.Graph;
import j2se.g261.eda.automator.graph.GraphWorker;
import j2se.g261.eda.automator.graph.Node;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dmitry
 */
public class DotUtils {

    private static final String DIGRAPH = "digraph";
    private Graph g;
    private int index = 0;

    public DotUtils(Graph g) {
        this.g = g;
    }

    public File generateDotFile(String filename) throws IOException, DotException {
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
             processNode(g.getNodeFromStartsAt(i),bf,passed);
        }
        bf.write("}");
        bf.close();
        return f1;
    }

    private String writeNode(Node n, BufferedWriter bf) throws IOException {
        String s1 = "node" + index + "[lable = \"" + n.getName() + "\" ";
        if(Node.isStartNode(n) || Node.isEndNode(n)){
            s1 += ", shape = \"rectangle\"";
        }else{
            s1 += ", shape = \"circle\"";
        }
        bf.write( s1 + "]");
        bf.newLine();
        return "node" + ++index;
    }

    private void writeEdge(String n1, String n2, BufferedWriter bf) throws IOException {
        bf.write(n1 + "->" + n2);
        bf.newLine();
    }

    private void processNode(Node n, BufferedWriter bf, HashMap<Node, String> passed) throws IOException, DotException {
        if (!passed.containsKey(n)) {
            passed.put(n, writeNode(n, bf));
        }

        
        int num = n.getOutgoingSize();
        for (int i = 0; i < num; i++) {
            Node n1 = n.getOutgoingAt(i);
            processNode(n1, bf, passed);
            if (passed.containsKey(n) && passed.containsKey(n1)) {
                writeEdge(passed.get(n), passed.get(n1), bf);
            } else {
                throw new DotException();
            }
        }
    }
    
    public static void main(String[] args) {
        Graph g1 = new Graph();
        g1.addNode(new Node("a"));
        Graph g2 = new Graph();
        g2.addNode(new Node("b"));
        Graph g3 = GraphWorker.concatanateAND(g1, g2);
        
        g1 = new Graph();
        g2 = new Graph();
        g1.addNode(new Node("c"));
        g2.addNode(new Node("d"));
        Graph g4 = GraphWorker.concatanateANY(g1);
        Graph g5 = GraphWorker.concatenateONE(g2);
        Graph g6 = GraphWorker.concatenateOR(g3, GraphWorker.concatanateAND(g4, g5));
        
        System.out.println(g6);
        DotUtils d = new DotUtils(g6);
        try {
            d.generateDotFile("DOTFILE");
        } catch (IOException ex) {
            Logger.getLogger(DotUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DotException ex) {
            Logger.getLogger(DotUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

