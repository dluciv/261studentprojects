package dk.bTree;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

public class Node {

	private Vector<String> keys;
	private HashMap<String, Vector<Integer>> links;
	private Vector<Node> children;
	private Node parent;

	public Node() {
		keys = new Vector<String>();
		links = new HashMap<String, Vector<Integer>>();
		children = new Vector<Node>();
	}

	public Node(String key, Vector<Integer> link) {
		keys = new Vector<String>();
		keys.add(key);

		links = new HashMap<String, Vector<Integer>>();
		links.put(key, link);

		children = new Vector<Node>();
	}
	
	public Node(Vector<String> k, HashMap<String, Vector<Integer>> l, Vector<Node> c, Node p) {
		children = c;
		keys = k;
		links = l;
		parent = p;
	}

	@Override
	public String toString() {
		String s = "Keys: " + keys;
		return s;
	}

	public int compareKey(String key) {
		Vector<String> keys = this.getKeys();
		int childNumber = -1;
		for (int i = 0; i < keys.size(); i++) {
			if (keys.get(i).compareTo(key) >= 0) {
				childNumber = i;
				break;
			}
		}
		if (childNumber == -1) {
			return keys.size();
		}
		return childNumber;
	}

	public void addLink(String key, int link) {
		if (!links.containsKey(key)) {
			Vector<Integer> p = new Vector<Integer>();
			p.add(link);
			links.put(key, p);
		} else if (links.containsKey(key)){
			Vector<Integer> p = links.get(key);
			p.add(link);
		}
	}

	public void addKey(String key, int link) {
		keys.add(key);
		Collections.sort(keys, new StringsComparator());
		addLink(key, link);
	}

	public void addKey(String key, Vector<Integer> link) {
		keys.add(key);
		Collections.sort(keys, new StringsComparator());
		links.put(key, link);
	}
	
	public boolean hasChildren() {
		if (children.size() != 0) {
			return true;
		} else
			return false;
	}

	public boolean isFull() {
		if (keys.size() < 2*BTree.DEGREE - 1) {
			return false;
		} else
			return true;
	}

	public Vector<String> getKeys() {
		return this.keys;
	}

	public int getChildrenSize(){
		return this.children.size();
	}
	
	public String getKey(int number) {
		return this.keys.get(number);
	}

	public Node getParent() {
		return this.parent;
	}

	public Node getChield(int position) {
		return this.children.get(position);
	}

	public Vector<Node> getChildren() {
		return this.children;
	}

	public void setParent(Node n) {
		this.parent = n;
	}

	public void setChildren(Vector<Node> c) {
		this.children = c;
	}

	public void setLinks(HashMap<String, Vector<Integer>> l) {
		this.links = l;
	}

	public void setKeys(Vector<String> k){
		this.keys = k;
	}
	
	public HashMap<String, Vector<Integer>> getLinks() {
		return this.links;
	}

	public void showNode(){
		System.out.println("keys: " + keys);
		System.out.println("links: " + links);
		System.out.println("nodes: " + children);
	}
	
	class StringsComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			String first = (String) o1;
			String second = (String) o2;

			if (first.compareTo(second) > 0) {
				return 1;
			} else if (first.compareTo(second) == 0) {
				return 0;
			} else
				return -1;
		}
	}

}
