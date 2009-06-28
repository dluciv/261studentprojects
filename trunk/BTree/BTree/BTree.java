package BTree;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;


public class BTree implements Externalizable {
	public static int B = 256;		
	Node root;
	
	public BTree() {
		root = new Node();		
	}
	public Key find(Key k) {
		return root.searchAtLeast(k);
	}
	public void stitch() {
		root.stitch(null);
	}
	public void insert(Key k) {
		if (root.getN() == 2 * B - 1) {
			Node s = new Node();
			Node r = root;
			root = s;
			root.setChild(0, r);			
			root.split(0);			
		}
		root.insertNonfull(k);
	}
	Key getKey(int num) {
		return root.getKey(num);
	}
	public int countBetween(Key a, Key b) {
		return root.countTo(b) - root.countTo(a);
	}
	public BList listBetween(Key a, Key b) {
		Key A = root.searchAtLeast(a);
		
		if (A.compareTo(b) > 0) {
			return new BList(null, 0);
		}
		return new BList(A, countBetween(a, b));
	}
	public BList listBetween(Key a, Key b, int n) {
		Key A = root.searchAtLeast(a);
		if (A.compareTo(b) > 0) {
			return new BList(null, 0);
		}
		return new BList(A, Math.min(countBetween(a, b), n));
	}
	public void readExternal(ObjectInput arg0) throws IOException, ClassNotFoundException {
		B = arg0.readInt();
		root.readExternal(arg0);
		stitch();
	}
	public void writeExternal(ObjectOutput arg0) throws IOException {
		arg0.writeInt(B);
		root.writeExternal(arg0);
	}
	
	
}
