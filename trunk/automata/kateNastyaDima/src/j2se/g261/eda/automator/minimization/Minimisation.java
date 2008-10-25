package j2se.g261.eda.automator.minimization;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import j2se.g261.eda.automator.graph.Node;
import j2se.g261.eda.automator.minimization.MinGraph;
import j2se.g261.eda.automator.graph.Graph;
import java.util.HashMap;
import java.util.HashSet;

/**
*
* @author dmitriy
*/
public class Minimisation {
	
	private MinGraph edgeGraph;
	private Vector<Couple> different;
	private static char additionalNode = 'h';
	private Vector<Character> uniq;
	private HashMap<Integer, Vector<Character>> storage;

	public Minimisation(){
		edgeGraph = new MinGraph();
		different = new Vector<Couple>();
		uniq = new Vector<Character>();
		storage = new HashMap<Integer, Vector<Character>>();
	}
	
	public void add(Couple p){
		if (!different.contains(p)){
		different.add(p);
		}
	}
	
	public MinGraph transform(Graph g){
		int num = g.allSize();
		int newNumber = 2;
		for(int i = 0;i < num;i++){
			if(g.isEnd(g.getNodeFromAllAt(i))){
				g.getNodeFromAllAt(i).setNumber(1);	
				
			}
			else if(g.isStart(g.getNodeFromAllAt(i))){
				g.getNodeFromAllAt(i).setNumber(0);
			}
			else{	
					g.getNodeFromAllAt(i).setNumber(newNumber);
					newNumber++;
			}
		}
		Node n = g.getNodeFromStartsAt(0);
		Vector<Node> passed = new Vector<Node>();
		return toGraph(n,passed);
	}
	
	private MinGraph toGraph(Node n,Vector<Node> passed){
		
		int num = n.getOutgoingSize();
		
		
		for(int i = 0; i < num; i++){
			
			Node n2 = n.getOutgoingAt(i);
			passed.add(n);
			if(Node.isEndNode(n2)){
				Edge endEdge = new Edge('\n', n.getNumber(), n2.getNumber());
				edgeGraph.add(endEdge);
				
			}
			
			else if (passed.contains(n2)){
					Edge edgeNode = new Edge(n2.getName(),n.getNumber(),n2.getNumber());			
					if (!edgeGraph.getAll().contains(edgeNode)){
						edgeGraph.add(edgeNode);
					}
				
				}
				else{
		
			toGraph(n2,passed);
			
			Edge edgeNode = new Edge(n2.getName(),n.getNumber(),n2.getNumber());
				
			if (!edgeGraph.getAll().contains(edgeNode)){
				edgeGraph.add(edgeNode);
			}
				}
			}		
			
		return edgeGraph;		
	}
	
	public int pr(){
		return uniq.size();
	}
	
	
	public File edgeDot(String filename)throws IOException{
		File f1 = File.createTempFile(filename, ".dot");
        BufferedWriter bf = new BufferedWriter(new FileWriter(f1));
        bf.write("digraph");
        bf.write(" g{");
        bf.newLine();
        
        int num = edgeGraph.sizeAll();
        for(int i = 0; i<num ;i++){
        	Edge edge = edgeGraph.getEdgeAt(i); 
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
	
	
	public void addToStrage(int c, Vector<Character> v) {
        if (!storage.containsKey(c)) {
            storage.put(c, v);
        }
    }

    public void addToStorage(int c, char i) {
        if (storage.containsKey(c)) {
            Vector<Character> v = storage.get(c);
            if (!v.contains(i)) {
                v.add(i);
            }
        } else {
            Vector<Character> v = new Vector<Character>();
            v.add(i);
            storage.put(c, v);
        }
    }
	
    private Vector<Character> difference(Vector<Character> a,Vector<Character> b){
    	int num = a.size();
    	Vector<Character> diff = new Vector<Character>();
    	for(int i = 0; i < num; i++){
    		char name = a.get(i);
    	if(!b.contains(name)){
    		diff.add(name);
    	}
    	
    	}
    	return diff;
    }
	
	public void addAbsorbingState(){
		int num = edgeGraph.sizeAll();
		
		 for(int i = 0; i < num; i++){
			 char name =  edgeGraph.getEdgeAt(i).getName();
			 int incom = edgeGraph.getEdgeAt(i).getIncoming();
			 addToStorage(incom, name);
			 if (!uniq.contains(name)){      //&& name != '\n'
				 uniq.add(name);
			 }
		 }
			 int states = storage.size();
			 for(int j = 0; j < states; j++){
				 if (j == 1) j++;
				 Vector<Character> diff = new Vector<Character>();
				 
				 if(storage.get(j).size() == 0){
					 diff = uniq;
				 }
				 else{
				 diff = difference(uniq,storage.get(j));
				 }			 
				 int size = diff.size();
				 for(int k = 0; k < size; k++){
					 edgeGraph.add(new Edge(diff.get(k),j,states+1));
				 }
			 }
	}
}
		 //return edgeGraph;
 
	

