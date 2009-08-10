package BTree;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;


public class  BTree implements Externalizable {
	
	int B = 256;
	Manager comp;	
	Node root;	
	
	int getB() {
		return B;		
	}
		
	public BTree() {
		
	}
	
	public BTree(int B, Manager comp) {
		this.B = B;
		this.comp = comp;
		root = new Node(this);		
	}
	public Key find(Key k) {
		return root.searchAtLeast(k);
	}
	public void stitch() {
		root.stitch(null);
	}
	public void insert(Value v) {
		if (root.getN() == 2 * B - 1) {
			Node s = new Node(this);
			Node r = root;
			root = s;
			root.setChild(0, r);			
			root.split(0);			
		}
		root.insertNonfull(new Key(v, this));
	}
	Key getKey(int num) {
		return root.getKey(num);
	}
	public int countBetween(Key a, Key b) {
		return root.countTo(b) - root.countTo(a);
	}
	public BList listBetween(Key a, Key b) {
		Key A = root.searchAtLeast(a);
		
		if (compare(a,b) > 0) {
			return new BList(null, 0);
		}
		return new BList(A, countBetween(a, b));
	}
	public BList listBetween(Key a, Key b, int n) {
		Key A = root.searchAtLeast(a);
		if (compare(A, b) > 0) {
			return new BList(null, 0);
		}
		return new BList(A, Math.min(countBetween(a, b), n));
	}
	public void readExternal(ObjectInput arg0) throws IOException, ClassNotFoundException {
		B = arg0.readInt();
		comp.readExternal(arg0);
		root.readExternal(arg0);
		stitch();
	}
	public void writeExternal(ObjectOutput arg0) throws IOException {
		arg0.writeInt(B);
		comp.writeExternal(arg0);
		root.writeExternal(arg0);
	}
	
	public int compare(Key a, Key b) {
		if (a == null) {
			return a == b ? 0 : 1;
		}
		if (b == null) {
			return -1;
		}
		return comp.compare(a.val, b.val);
	}
}
