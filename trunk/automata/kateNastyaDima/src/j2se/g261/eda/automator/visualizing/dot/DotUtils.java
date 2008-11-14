/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.visualizing.dot;

import j2se.g261.eda.automator.representations.nfa.NFA;
import j2se.g261.eda.automator.representations.nfa.NFANode;
import j2se.g261.eda.automator.representations.dfa.DFA;
import j2se.g261.eda.automator.representations.minimisation.Edge;
import j2se.g261.eda.automator.representations.minimisation.MinimizedDFA;
import j2se.g261.eda.automator.representations.minimisation.MinimizedDFAWorker;

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
    private NFA nfa;
    private DFA dfa;
    private MinimizedDFA mdfa; 
    private int index = 0;

    public DotUtils(NFA n, DFA d, MinimizedDFA m) {
        this.nfa = n;
        this.dfa = d;
        this.mdfa = m;
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
        HashMap<NFANode, String> passed = new HashMap<NFANode, String>();
        File f1 = File.createTempFile(filename, ".dot");
//        f1.deleteOnExit();
        BufferedWriter bf = new BufferedWriter(new FileWriter(f1));
        bf.write(DIGRAPH);
        bf.write(" g{");
        bf.newLine();
        int num = nfa.startsSize();
        for (int i = 0; i < num; i++) {
            processNode(nfa.getNodeFromStartsAt(i), bf, passed);
        }
        bf.write("}");
        bf.close();
        return f1;
    }
    
    public File generateDotFileForDFA(String filename) throws IOException, DotException {
    	File f1 = File.createTempFile(filename, ".dot");
    	MinimizedDFAWorker mdw = new MinimizedDFAWorker();
    	MinimizedDFA h = mdw.convertFromNFAToMinimizedDFA(dfa);
        BufferedWriter bf = new BufferedWriter(new FileWriter(f1));
        bf.write("digraph");
        bf.write(" g{");
        bf.newLine();
        
        int num = h.sizeAll();
        for(int i = 0; i<num ;i++){
        	Edge edge = h.getEdgeAt(i); 
        	if(edge.getName() == '\n'){
        		bf.write(edge.getIncoming() + "->" + edge.getOutgoing() + "[label = " +
            			"end" + "];");
            	bf.newLine();
        	}
        	else{
        	bf.write(edge.getIncoming() + "->" + edge.getOutgoing() + "[label = " +
        			edge.getName() + "];");
        	bf.newLine();
        	}
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
    
    private String writeNode(NFANode n, BufferedWriter bf) throws IOException {
        String s1;
        if (n.getIncomingSize() == 0){
        	s1 = "node" + index + "[label = \"" + "START" + "\" ";
            s1 += ", shape = \"rectangle\"";
        }
        else if (n.getOutgoingSize() == 0) {
        	s1 = "node" + index + "[label = \"" + "END" + "\" ";
            s1 += ", shape = \"rectangle\"";
        }
        else if (n.getName() == '\r'){
        	s1 = "node" + index + "[label = \"" + "EPSILON" + "\" ";
            s1 += ", shape = \"triangle\"";
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
    
    private void processNode(NFANode n, BufferedWriter bf, HashMap<NFANode, String> passed) throws IOException, DotException {
        System.out.println("" + n.getName() + n.getNumber());
        if (!passed.containsKey(n)) {
            passed.put(n, writeNode(n, bf));
        }


        int num = n.getOutgoingSize();
        for (int i = 0; i < num; i++) {
            NFANode n1 = (NFANode)n.getOutgoingAt(i);
            if (passed.containsKey(n1) && !NFANode.isEndNode(n1))
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
    
   /**
     * This method create a dot file(In a correct format) the containing count
     * conclude file in BufferedWriter
     * only for MinimizedDFA
     * @param filename the name of this file
     * @param g constructed graph
     * @return Temp file with dot-representation of graph
     * @throws IOException if some IO errors occured
     */
    public File edgeDot(String filename)throws IOException{
		File f1 = File.createTempFile(filename, ".dot");
        BufferedWriter bf = new BufferedWriter(new FileWriter(f1));
        bf.write("digraph");
        bf.write(" g{");
        bf.newLine();
        
        int num = mdfa.sizeAll();
        for(int i = 0; i<num ;i++){
        	Edge edge = mdfa.getEdgeAt(i); 
        	if(edge.getName() == '\n'){
        		bf.write(edge.getIncoming() + "->" + edge.getOutgoing() + "[label = " +
            			"end" + "];");
            	bf.newLine();
        	}
        	else{
        	bf.write(edge.getIncoming() + "->" + edge.getOutgoing() + "[label = " +
        			edge.getName() + "];");
        	bf.newLine();
        	}
        }
        bf.write("}");
        bf.close();
        return f1;
        }
    
    

//    public static void main(String[] args) {
////        NFA g1 = new NFA();
////        g1.addNode(new NFANode('a'));
////        GraphWorker.markAllNodes(g1);
////        NFA g2 = new NFA();
////        g2.addNode(new NFANode('b'));
////        GraphWorker.markAllNodes(g2);
////        NFA g3 = GraphWorker.concatanateAND(g1, g2);
////        System.out.println("______________G3______________");
////        System.out.println(g3);
////        System.out.println("______________________________");
////
////        g1 = new NFA();
////        g2 = new NFA();
////        g1.addNode(new NFANode('c'));
////        GraphWorker.markAllNodes(g1);
////        g2.addNode(new NFANode('d'));
////        GraphWorker.markAllNodes(g2);
////        NFA g4 = GraphWorker.concatanateANY(g1);
////        System.out.println("______________G4______________");
////        System.out.println(g4);
////        System.out.println("______________________________");
////        NFA g5 = GraphWorker.concatenateONE(g2);
////        System.out.println("______________G5______________");
////        System.out.println(g5);
////        System.out.println("______________________________");
////        NFA g0 = GraphWorker.concatanateAND(g4, g5);
////        System.out.println("______________G0______________");
////        System.out.println(g0);
////        System.out.println("______________________________");
////        NFA g6 = GraphWorker.concatenateOR(g3, g0);
////        System.out.println("______________G6______________");
////        System.out.println(g6);
////        System.out.println("______________________________");
////        GraphWorker.makeClosure(g6);
////        System.out.println(g6);

//
//        g1 = new NFA();
//        g2 = new NFA();
//        g1.addNode(new NFANode('c'));
//        GraphWorker.markAllNodes(g1);
//        g2.addNode(new NFANode('d'));
//        GraphWorker.markAllNodes(g2);
//        NFA g4 = GraphWorker.concatanateANY(g1);
//        System.out.println("______________G4______________");
//        System.out.println(g4);
//        System.out.println("______________________________");
//        NFA g5 = GraphWorker.concatenateONE(g2);
//        System.out.println("______________G5______________");
//        System.out.println(g5);
//        System.out.println("______________________________");
//        NFA g0 = GraphWorker.concatanateAND(g4, g5);
//        System.out.println("______________G0______________");
//        System.out.println(g0);
//        System.out.println("______________________________");
//        NFA g6 = GraphWorker.concatenateOR(g3, g0);
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


