package archiver;

import com.sun.java.swing.plaf.windows.WindowsTreeUI.CollapsedIcon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;



public class HuffmanCoder {
	HuffmanTree tree= new HuffmanTree(); // дерево Хаффмана
	public HashMap<Integer,String> codes = new HashMap<Integer, String>();
	ArrayList<HuffmanChar> itemWeightList = new ArrayList<HuffmanChar>();

	public void printCodes(){
		System.out.println("codes");
		for (HuffmanChar iw : itemWeightList)
			System.out.println(iw.key+" - "+codes.get(iw.key));
	}

	public int getWeight(int key){
		for (HuffmanChar iw : itemWeightList)
			if(iw.key == key)
				return iw.newWeight;
		return 0;
	}
	public void setTreeLeaveWeight( byte[] data ){

		for (int i=0; i<data.length; i++){
			Boolean found = false;
			for (HuffmanChar iw : itemWeightList) {
				if(iw.key == data[i]){
					iw.oldWeight++;
					found = true;
				}
			}
			if(!found){
				HuffmanChar iw = new HuffmanChar();
				iw.key = data[i];
				iw.oldWeight = 1;
				iw.newWeight = 0;
				itemWeightList.add(iw);
			}
		}
	}
	public void makeWeights(){

		Collections.sort(itemWeightList);

		int curNewWeight = 1;
		long curOldWeight = 1;
		for (HuffmanChar iw : itemWeightList) {
			if(iw.oldWeight > curOldWeight){
				curOldWeight = iw.oldWeight;
				curNewWeight++;
			}
			iw.newWeight = curNewWeight;
		}
	}


	public ArrayList<HuffmanTree> getListOfTrees(){
		ArrayList<HuffmanTree> trees = new ArrayList<HuffmanTree>();
		int j=0;
		for (int i=0; i < itemWeightList.size(); i++){
			if(itemWeightList.get(i).newWeight > 0){
				HuffmanTree tTree = new HuffmanTree();
				tTree.weight = itemWeightList.get(i).newWeight;
				tTree.character = itemWeightList.get(i).key;
				tTree.leaf = true;
				trees.add(tTree);
			}
		}
		return trees;
	}

	public HuffmanTree getSmallestTree(ArrayList<HuffmanTree> trees){
		int smallesWeight = 255;
		int smallestTreeIndex = 0;
		for (int i=0; i < trees.size(); i++){
			if(smallesWeight >= trees.get(i).weight){
				smallesWeight = trees.get(i).weight;
				smallestTreeIndex = i;
			}
		}
		HuffmanTree smallestTree = trees.get(smallestTreeIndex);
		trees.remove(smallestTreeIndex);
		return smallestTree;
	}
	public void makeTree( ){
		ArrayList<HuffmanTree> trees = getListOfTrees();
		while (trees.size() > 1){
			HuffmanTree theSmallestTree = getSmallestTree(trees);
			HuffmanTree secondSmallestTree = getSmallestTree(trees);
			HuffmanTree newNode = new HuffmanTree();
			newNode.leaf = false;
			newNode.weight = theSmallestTree.weight + secondSmallestTree.weight;
			newNode.child0 = theSmallestTree;
			newNode.child1 = secondSmallestTree;
			trees.add(newNode);

		}
		tree = trees.get(0);
	}

	public void makeCode(HuffmanTree node, String codeStr){
		if(node.leaf){
			//System.out.println(node.character+" - "+codeStr);
			codes.put(node.character, codeStr);
			//codes.put(3,"001");
			//System.out.println(codes.get(3));
		}else{
			if(node.child0!= null)
				makeCode(node.child0, codeStr+"0");
			if(node.child1!= null)
				makeCode(node.child1, codeStr+"1");
		}
    }
	public void makeCodes(){
		makeCode(tree, "");
    }

	public String codeToBits( byte data ) {
		String str = "";
		//System.out.print(data + " " + codes.get((int)data));
		str += codes.get((int)data);
		return str;
	}

	public TwoString decodeBits(String data) {
		//System.out.println("decodeBits");
		//System.out.println(data);
		HuffmanTree curNode = tree;
		int p = 0;
		while(!curNode.leaf && data.length()>=p+1){
			if(data.charAt(p)=='0')
				curNode = curNode.child0;
			else curNode = curNode.child1;
			p++;
		}
		String str;
		if(curNode.leaf){
			data = data.substring(p, data.length());
			str = (char)curNode.character+"";
		}else str = "";

		TwoString ts = new TwoString();
        ts.str1 = data;
        ts.str2 = str;
		return ts;
	}
}
