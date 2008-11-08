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

/**
*
* @author dmitriy
*/
public class Minimisation {
	
	private MinGraph edgeGraph;
	private Vector<Couple> different;
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
					Edge edgeNode = new Edge(n2.getName(), n.getNumber(), n2.getNumber());			
					if (!edgeGraph.getAll().contains(edgeNode)){
						edgeGraph.add(edgeNode);
					}
				
				}
				else{
		
			toGraph(n2,passed);
			
			Edge edgeNode = new Edge(n2.getName(), n.getNumber(), n2.getNumber());
				
			if (!edgeGraph.getAll().contains(edgeNode)){
				edgeGraph.add(edgeNode);
			}
				}
			}		
		return edgeGraph;		
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
	
	
	public void addToStorage(int c, Vector<Character> v) {
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
    
    	Vector<Character> diff = new Vector<Character>();
    	for(char name : a){
    		
	    	if(!b.contains(name)){
	    		diff.add(name);
	    	}
	    	
    	}
    	return diff;
    }
	
	public void addAbsorbingState(){
		 for(Edge e : edgeGraph.getAll()){
			 
			 addToStorage(e.getIncoming(), e.getName());
			 
			 if (!uniq.contains(e.getName())){     
				 uniq.add(e.getName());
			 }
			 
		 }
			 int states = storage.size();
			 for(int j = 0; j < states+1; j++){
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
					 edgeGraph.add(new Edge(diff.get(k), j, states+1));
				 }
			 }
			 int size = uniq.size();
			 for(int m = 0; m < size; m++){
				 char name = uniq.get(m);
				 edgeGraph.add(new Edge(name, states+1, states+1));
			 } 
	}
	
	public Vector<Edge> findIncEdge(int state){
		Vector<Edge> list = new Vector<Edge>();
	
		for(Edge e : edgeGraph.getAll()){
		
			if (e.getOutgoing() == state){
				list.add(e);
			}	

		}
		return list;
	}
	
	public Vector<Edge> findOutEdge(int state){
		Vector<Edge> list = new Vector<Edge>();
	
		for(Edge e : edgeGraph.getAll()){
		
			if (e.getIncoming() == state){
				list.add(e);
			}	

		}
		return list;
	}
	
	
	public Vector<Couple> findEquals(Couple c , Vector<Couple> toDelite){
		
		for(Couple p : different){
			if ((p.getFirst() == c.getSecond() || p.getSecond() == c.getSecond()) &&
					(p.getFirst() != c.getFirst() && p.getSecond() != c.getSecond())){
				if(!toDelite.contains(p)) toDelite.add(p);
			}
		}
		return toDelite;
	} 
	
	public void minimize(){
		Vector<Couple> diff = new Vector<Couple>();
		Vector<Couple> toDelite = new Vector<Couple>();
		addAbsorbingState();
		int num = storage.size() + 2;
		
		for(int i = 0; i < num; i++){
			if (i == 1) i++;
			Couple c = new Couple(i,1);
			diff.add(c);	
		}	
		
		for(int j = 0; j < num; j++){
				for (int k = 0; k < num; k++){
					if(j != 1 && k != 1 && j < k) different.add(new Couple(j,k));
				}
			}
		
		HelpToMinimize(diff);
		
		
		for(Couple c : different){
			System.out.println(c.getFirst() + "=" + c.getSecond());
			toDelite = findEquals(c,toDelite);
		}
		
		for(Couple c : toDelite){
			different.remove(c);
		}
		
		
		for(Couple c : different){
			Vector<Edge> listFirst = new Vector<Edge>();
			Vector<Edge> listSecond = new Vector<Edge>();
			listFirst = findIncEdge(c.getSecond());
			listSecond = findOutEdge(c.getSecond());
			
			for(Edge i : listFirst){
				edgeGraph.getAll().remove(i);
			}
			
			for(Edge i : listFirst){
				Edge e = new Edge (i.getName(),i.getIncoming(),c.getFirst());
				if(i.getIncoming() == c.getSecond()) continue;
				if(!edgeGraph.getAll().contains(e)) edgeGraph.getAll().add(e);
			}
			
			for(Edge j : listSecond){
				edgeGraph.getAll().remove(j);
			}
			
			for(Edge j : listSecond){
				Edge e = new Edge (j.getName(),c.getFirst(),j.getOutgoing());
				if(j.getOutgoing() == c.getSecond()) continue;
				if(!edgeGraph.getAll().contains(e)) edgeGraph.getAll().add(e);
			}		
			}
		
		Vector<Edge> list = findIncEdge(storage.size()+1);
		
		for(Edge h : list){
			edgeGraph.getAll().remove(h);
		}
			
		}
		
	
	
	private void HelpToMinimize(Vector<Couple> tmp){
		Vector<Edge> listFirst = new Vector<Edge>();
		Vector<Edge> listSecond = new Vector<Edge>();
		Vector<Couple> newDiff = new Vector<Couple>();
		
		for(Couple t : tmp){
			listFirst = findIncEdge(t.getFirst());
			listSecond = findIncEdge(t.getSecond());
			
			for(Edge j : listFirst){
				for (Edge k : listSecond){
					if(j.getName() == k.getName()){
						
						if(j.getIncoming() < k.getIncoming()){
							Couple c =	new Couple(j.getIncoming(),k.getIncoming());
							if(different.contains(c)) newDiff.add(c);
							different.remove(c);
							}
			
						else{
						Couple c = new Couple(k.getIncoming(), j.getIncoming());
						if(different.contains(c)) newDiff.add(c);
						different.remove(c);
						}				
					}		
				}
			}
		}	
		if(!newDiff.isEmpty()){
			HelpToMinimize(newDiff);
		}
	}

	public boolean check(String s){
		int length = s.length();
		int state = 0;
		boolean isNext = true;
		
		for(int i = 0; i < length; i++){
			if(isNext){
				
			char symbol = s.charAt(i);
			Vector<Edge> v = findOutEdge(state);
			
			for(Edge k : v){
				if (k.getName() == symbol){
					state = k.getOutgoing();
					isNext = true;
					break;
				}
				isNext = false;
			}
			
			}
		}
		if(isNext){
			Vector<Edge> end = findOutEdge(state);
			for(Edge j : end){
				if(j.getOutgoing() == 1){
					isNext = true; 
					break;
				}
				else isNext = false;
			}
		}
		return isNext;
	}
	

}


 


