package j2se.g261.eda.automator.minimization;

import j2se.g261.eda.automator.graph.Node;

import java.util.Vector;

public class MinGraph {

	private Vector<MinNode> starts;
	private Vector<MinNode> ends;
	private Vector<MinNode> all;
	
	
	public MinGraph(MinNode root){
		all = new Vector<MinNode>();
		ends = new Vector<MinNode>();
		starts = new Vector<MinNode>();	
		all.add(root);
		ends.add(root);
		starts.add(root);
	}
	
	public void addMinNode(MinNode n){
		if (!all.contains(n)) {
            all.add(n);
        }

        int num = n.getOutgoingSize();

        for (int i = 0; i < num; i++) {
            MinNode n1 = n.getOutgoingAt(i);
            if (!all.contains(n1)) {
                addMinNode(n1);
            }
        }

	}
	
}
