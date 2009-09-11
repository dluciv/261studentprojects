package haffman;

import java.util.PriorityQueue;

/**
*
* @author ksenyiacypan
*/

public class Node implements Comparable<Node> {
	byte symbol;
	int height;
	int freq;
	Node left;
	Node right;
	
	boolean isLeaf() {
		return left == null && right == null;
	}
	static public Node buildTree(int[] freq) {
		PriorityQueue<Node> queue = new PriorityQueue<Node>(); 
		for (int i = 0; i < 256; i++) {
			queue.add(new Node((byte)i, freq[i]));
		}
		while (queue.size() != 1) {
			Node f = queue.poll();
			Node s = queue.poll();
			queue.add(new Node(f,s));
		}
		Node root = queue.peek();
		return root;
	}
	
	void fill(Symbol[] table, int path, int length) {
		if (isLeaf()) {
			table[symbol & 0xFF] = new Symbol(symbol, path, length);
		} else {
			left.fill(table, path << 1, length + 1);
			right.fill(table, (path << 1) + 1, length + 1);
		}
	}
	
	public static Symbol[] fillTable(Node root) {
		Symbol[] table = new Symbol[256];
		root.fill(table, 0,0);
		return table;
	}
	
	public Node(byte symbol, int freq) {
		this.symbol = symbol;
		this.freq = freq;
		this.left = null;
		this.right = null;
		height = 0;
	}
	public Node(Node left, Node right) {
		this.left = left;
		this.right = right;
		this.freq = left.freq + right.freq;
		this.height = Math.max(left.height, right.height) + 1;
	}
	public int compareTo(Node arg0) {
		if (arg0.freq == this.freq) {
			return -arg0.height + this.height;
		}
		return -arg0.freq + this.freq ;
	}	
	
}
