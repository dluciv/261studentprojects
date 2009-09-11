package dk.huffman;

import java.util.HashMap;
import java.util.Vector;

import dk.main.DataList;

public class HuffmanTree {

	private Vector<Node> nodes;
	private Vector<Node> leafs;
	private int summFrequency;
	private int maxCodeSize;
	DataList frequencyList;
	DataList s;

	public HuffmanTree(DataList frequencys) {
		frequencyList = frequencys;
		nodes = new Vector<Node>();
		leafs = new Vector<Node>();
		summFrequency = 0;
		maxCodeSize = 0;
		createLeafs();
		buildTree();
	}

	private void createLeafs() {
		for (int i = 0; i < frequencyList.size(); i++) {
			int frequency = frequencyList.getData(i);
			summFrequency += frequency;
			if (frequency != 0) {
				leafs.add(new Node(frequency, i));
			}
		}
	}

	private void buildTree() {
		Vector<Node> tmp = new Vector<Node>();
		for (int i = 0; i < leafs.size(); i++) {
			tmp.add(leafs.get(i));
		}
		if (tmp.size() != 0) {
			while (true) {
				Node n1 = findMinElement(tmp);
				tmp.remove(n1);
				if (tmp.size() == 0) {
					nodes.add(n1);
					break;
				}
				Node n2 = findMinElement(tmp);
				tmp.remove(n2);
				Node n3 = new Node(n1, n2);
				tmp.add(n3);
				n1.setParent(n3);
				n2.setParent(n3);
				nodes.add(n1);
				nodes.add(n2);
				if (tmp.size() == 1) {
					nodes.add(n3);
					break;
				}
			}
		}
	}

	public HashMap<Integer, Integer> getLenghtCode() {
		int size = leafs.size();
		HashMap<Integer, Integer> codes = new HashMap<Integer, Integer>();

		for (int i = 0; i < size; i++) {
			Node n = leafs.get(i);
			int bitCode = 0;
			Node n2 = n;

			while (n.getFrequency() != summFrequency) {
				bitCode++;
				n = n.getParent();
			}

			if (size == 1) {
				bitCode = 1;
			}
			codes.put(n2.getName(), bitCode);
			if (maxCodeSize < bitCode) {
				maxCodeSize = bitCode;
			}
		}

		// create DataList t and s
		DataList t = new DataList(maxCodeSize, 0);
		s = new DataList(maxCodeSize, 0);
		
		for (int i = 0; i < size; i++) {
			t.addOne(codes.get(leafs.get(i).getName()) - 1);
		}
		for (int i = maxCodeSize - 2; i >= 0; i--) {
			s.putData(i, (t.getData(i + 1) + s.getData(i + 1)) >> 1);
		}
		return codes;
	}

	public HashMap<Integer, Vector<Boolean>> getCanonicalCode(
			HashMap<Integer, Integer> codes) {

		HashMap<Integer, Vector<Boolean>> canonicalCodes = new HashMap<Integer, Vector<Boolean>>();
		int size = leafs.size();
		DataList tmp = new DataList(maxCodeSize, -1);
		for (int i = 0; i < size; i++) {
			int symbol = leafs.get(i).getName();
			int length = codes.get(symbol);
			if (tmp.getData(length - 1) == -1) {
				canonicalCodes.put(symbol, newCode(length, s
						.getData(length - 1)));
			} else {
				canonicalCodes.put(symbol, nextCode(canonicalCodes.get(tmp
						.getData(length - 1))));
			}
			tmp.putData(length - 1, symbol);
		}
		return canonicalCodes;
	}
	
	/*
	 * create code of first symbol on level
	 */
	private Vector<Boolean> newCode(int length, int number) {
		Vector<Boolean> newCode = new Vector<Boolean>();
		while (number != 0) {
			newCode.add(0, (number % 2) != 0);
			number = number / 2;
		}
		if (newCode.size() < length) {
			for (int i = newCode.size(); i < length; i++) {
				newCode.add(0, false);
			}
		}
		return newCode;
	}

	/*
	 * create code of next symbol on level 
	 */
	private Vector<Boolean> nextCode(Vector<Boolean> code) {
		Vector<Boolean> nextCode = (Vector<Boolean>) code.clone();
		for (int i = nextCode.size() - 1; i >= 0; i--) {
			if (!nextCode.get(i)) {
				nextCode.remove(i);
				nextCode.add(i, true);
				break;
			} else {
				nextCode.remove(i);
				nextCode.add(i, false);
			}
		}
		return nextCode;
	}

	/*
	 * find Node with minimal frequency
	 */
	private Node findMinElement(Vector<Node> tmp) {
		int size = tmp.size();
		Node n1 = tmp.get(0);
		for (int i = 0; i < size; i++) {
			Node n2 = tmp.get(i);
			if (n1.getFrequency() > n2.getFrequency()) {
				n1 = n2;
			}
		}
		return n1;
	}

	public int getTreeSize() {
		return leafs.size();
	}

	public int getMaxCodeLength() {
		return maxCodeSize;
	}

}
