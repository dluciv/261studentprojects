package dk.bTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Vector;

import dk.generator.AdressBookGenerator;

public class BTree {

	static int DEGREE = 2;
	private static int ORDER_LOW = 0;
	private static int ORDER_HIGH = 9;

	private Vector<Node> nodes;
	private BufferedReader reader;
	private Node root;
	private Vector<Node> leafs;
	private int position;
	private long fileLength;
	private int recordCounter;
	public static String EOFString = "eof";
	public static char separateStrings = 's';
	public static char separateSymbols = '|';

	public static String indexFileName = "index.txt";
	public static String indexLinksFileName = "indexLinks.txt";

	public BTree(File f) throws IOException {
		fileLength = AdressBookGenerator.BOOK_SIZE;
		reader = new BufferedReader(new FileReader(f));
		nodes = new Vector<Node>();
		leafs = new Vector<Node>();
		position = -1;
		createRoot();
	}

	private void createRoot() throws IOException {
		root = new Node();
		if (fileLength != 0) {
			root.addKey(readKey(), position);
			fileLength--;
		}
		nodes.add(root);
		leafs.add(root);
	}

	private String readKey() throws IOException {
		String s = reader.readLine().substring(ORDER_LOW, ORDER_HIGH);
		position++;
		return s;
	}

	private void separateNode(Node n, String key) {
		String newKey = n.getKey(DEGREE - 1);
		Vector<String> keys = n.getKeys();
		Vector<String> keys1 = new Vector<String>();
		Vector<String> keys2 = new Vector<String>();
		Vector<Node> children = n.getChildren();
		Vector<Node> children1 = new Vector<Node>();
		Vector<Node> children2 = new Vector<Node>();
		HashMap<String, Vector<Integer>> links = n.getLinks();
		HashMap<String, Vector<Integer>> links1 = new HashMap<String, Vector<Integer>>();
		HashMap<String, Vector<Integer>> links2 = new HashMap<String, Vector<Integer>>();

		int size = children.size();

		for (int i = 0; i < DEGREE; i++) {
			if (i != DEGREE - 1) {
				String key1 = keys.get(i);
				keys1.add(key1);
				links1.put(key1, links.get(key1));
			}
			if (size != 0) {
				children1.add(children.get(i));
				children2.add(children.get(i + DEGREE));
			}
			if (i != 0) {
				String key2 = keys.get(i + DEGREE - 1);
				keys2.add(key2);
				links2.put(key2, links.get(key2));
			}
		}

		Node n1;
		Node n2;
		Node parent;

		if (!isRoot(n)) {
			parent = n.getParent();
		} else {
			parent = new Node(newKey, n.getLinks().get(newKey));
		}

		n1 = new Node(keys1, links1, children1, parent);
		n2 = new Node(keys2, links2, children2, parent);
		for (int i = 0; i < children1.size(); i++) {
			children1.get(i).setParent(n1);
			children2.get(i).setParent(n2);
		}

		if (!isRoot(n)) {
			parent.addKey(newKey, n.getLinks().get(newKey));
			int newKeyPlace = parent.getKeys().indexOf(newKey);
			Vector<Node> parentChildrens = parent.getChildren();
			parentChildrens.remove(n);
			parentChildrens.add(newKeyPlace, n1);
			parentChildrens.add(newKeyPlace + 1, n2);
		}

		else {
			Vector<Node> c = new Vector<Node>();
			c.add(n1);
			c.add(n2);
			parent.setChildren(c);
			root = parent;
			nodes.add(parent);
		}

		nodes.add(n1);
		nodes.add(n2);
		nodes.remove(n);
		leafs.remove(n);
		if (n1.getChildren().size() == 0)
			leafs.add(n1);
		if (n2.getChildren().size() == 0)
			leafs.add(n2);
	}

	private void insertKey(Node n, String key) {
		int place = n.compareKey(key);
		int place2 = place;
		if (place == n.getKeys().size()) {
			place2 = place - 1;
		} else {
			;
		}
		if (n.getKey(place2).compareTo(key) == 0) {
			n.addLink(key, position);
		} else if (!n.isFull()) {
			if (isLeaf(n)) {
				n.addKey(key, position);
			} else {
				insertKey(n.getChield(place), key);
			}
		} else {
			if (isRoot(n)) {
				separateNode(n, key);
				insertKey(root, key);
			} else {
				Node next = n.getParent();
				separateNode(n, key);
				insertKey(next, key);
			}
		}
	}

	private boolean isRoot(Node n) {
		if (n.equals(root))
			return true;
		else
			return false;
	}

	public void showTree() {
		Vector<Node> tmp = new Vector<Node>();
		Vector<Node> front = new Vector<Node>();
		tmp.add(root);
		while (true) {
			System.out.println("tmp " + tmp);
			for (int i = 0; i < tmp.size(); i++) {
				Node temp = tmp.get(i);
				front.addAll(temp.getChildren());
			}
			if (isLeaf(tmp.get(0))) {
				break;
			}
			tmp.clear();
			tmp.addAll(front);
			front.clear();
		}
	}

	private boolean isLeaf(Node n) {
		if (leafs.contains(n))
			return true;
		else
			return false;
	}

	/*
	 * private void writeNode(BufferedWriter bf,Node n) throws IOException{
	 * String s = ""; Vector<String> keys = n.getKeys(); for(int i = 0; i <
	 * keys.size(); i++){ s += keys.get(i); } bf.write(s); bf.newLine();
	 * 
	 * for(int i = 0; i < keys.size(); i++){ Vector<Integer> links =
	 * n.getLinks().get(keys.get(i)); String l = ""; for(int j = 0; j <
	 * links.size(); j++){ l += links.get(j) + "|"; } l += separateStrings;
	 * bf.write(l); } bf.newLine(); }
	 */

	private int compareKeys(String lowKey, String highKey, String key) {
		if (key.compareTo(lowKey) >= 0 && key.compareTo(highKey) <= 0) {
			return 1;
		} else
			return 0;
	}

	private int printRecords(Vector<Integer> links) throws IOException {
		File f = new File(AdressBookGenerator.bookFileName);
		RandomAccessFile r = new RandomAccessFile(f, "rw");
		for (int i = 0; i < links.size(); i++) {
			if (recordCounter > 0) {
				r.seek(links.get(i) * AdressBookGenerator.RECORD_LENGTH + 2
						* links.get(i));
				System.out.println(r.readLine());
				recordCounter--;
			}
		}
		return recordCounter;
	}

	private void findRecords(String key1, String key2, Node n)
			throws IOException {
		Vector<String> keys = n.getKeys();

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);

			if (i == 0 && compareKeys(key1, key2, key) == 1) {
				if (recordCounter > 0 && n.hasChildren()) {
					findRecords(key1, key2, n.getChield(i));
				}
			}

			if (compareKeys(key1, key2, key) == 1) {
				printRecords(n.getLinks().get(key));
				if (recordCounter > 0 && n.hasChildren()) {
					findRecords(key1, key2, n.getChield(i + 1));
				}
			}
			if (i == 0 && key.compareTo(key2) > 0) {
				if (recordCounter > 0 && n.hasChildren()) {
					findRecords(key1, key2, n.getChield(i));
				}
			}
			if (i == keys.size() - 1 && key.compareTo(key1) < 0) {
				if (recordCounter > 0 && n.hasChildren()) {
					findRecords(key1, key2, n.getChield(i + 1));
				}
			} else if (key.compareTo(key1) < 0
					&& keys.get(i + 1).compareTo(key2) > 0) {
				if (recordCounter > 0 && n.hasChildren()) {
					findRecords(key1, key2, n.getChield(i + 1));
				}
				break;
			}
		}
	}

	public void showRecords(String key1, String key2, int numberRecords)
			throws IOException {
		if (key1.compareTo(key2) > 0) {
			System.out.println("wrong range!");
		} else {
			if (numberRecords < 0) {
				recordCounter = AdressBookGenerator.BOOK_SIZE;
			} else {
				recordCounter = numberRecords;
			}
			findRecords(key1, key2, root);
		}
	}

	/*
	 * public void createIndex() throws IOException { File indexLinks = new
	 * File(indexLinksFileName); File index = new File(indexFileName);
	 * BufferedWriter r = new BufferedWriter(new FileWriter(indexLinks));
	 * BufferedWriter bf = new BufferedWriter(new FileWriter(index));
	 * Vector<Node> tmp = new Vector<Node>(); Vector<Node> front = new
	 * Vector<Node>(); tmp.add(root); while (true) { System.out.println("tmp " +
	 * tmp); String childrenSize = ""; for (int i = 0; i < tmp.size(); i++) {
	 * Node temp = tmp.get(i); childrenSize += temp.getChildrenSize() +
	 * separateSymbols; front.addAll(temp.getChildren()); writeNode(bf, temp); }
	 * if(isLeaf(tmp.get(0))){ r.write(EOFString); break; } childrenSize +=
	 * separateStrings; r.write(childrenSize); r.newLine(); tmp.clear();
	 * tmp.addAll(front); front.clear(); } r.close(); bf.close(); }
	 */

	public void buildTree() throws IOException {
		while (fileLength != 0) {
			String key = readKey();
			insertKey(root, key);
			--fileLength;
		}
	}
}
