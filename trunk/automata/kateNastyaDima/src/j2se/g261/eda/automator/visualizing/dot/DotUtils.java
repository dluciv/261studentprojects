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
    private static final String EXTENSION = ".dot";
    private int index;

    public DotUtils() {
        index = 0;
    }

    /**
     * This method create a dot file(In a correct format) the containing count
     * conclude file in BufferedWriter
     * @param filename the name of this file
     * @return Temp file with dot-representation of graph
     * @throws IOException if some IO errors occured
     * @throws DotException if some internal errors occured
     */
    public File generateDotFileForNFA(NFA nfa, String filename) throws IOException, DotException {
        index = 0;
        HashMap<NFANode, String> passed = new HashMap<NFANode, String>();
        File f1 = File.createTempFile(filename, EXTENSION);
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
   
    public File generateDotFileForDFA(DFA dfa, String filename) throws IOException, DotException {
        File f1 = File.createTempFile(filename, EXTENSION);
        MinimizedDFAWorker mdw = new MinimizedDFAWorker();
        MinimizedDFA h = mdw.convertFromNFAToMinimizedDFA(dfa);
        BufferedWriter bf = new BufferedWriter(new FileWriter(f1));
        bf.write(DIGRAPH);
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
    public File edgeDot(MinimizedDFA mdfa, String filename)throws IOException{
                File f1 = File.createTempFile(filename, EXTENSION);
        BufferedWriter bf = new BufferedWriter(new FileWriter(f1));
        bf.write(DIGRAPH);
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
   
   

    }

