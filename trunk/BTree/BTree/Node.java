package BTree;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

public class Node implements Externalizable {
	
	private Key[] keys;
	private Node[] childs;
	private int n;
	private int keyc;
	
	public Node() {
		n = 0;
		keyc = 0;
		keys = new Key[2 * BTree.B - 1];
		childs = new Node[2 * BTree.B];
	}
	public Key stitch(Key key) {
		Key cur = key;
		int i;
		for (i = n - 1; i >= 0; i--) {
			if (childs[i + 1] != null) {
				cur = childs[i + 1].stitch(cur);
			}
			keys[i].next = cur;
			cur = keys[i];			
		}
		if (childs[0] != null) {
			return childs[0].stitch(cur);
		} else {
			return keys[0];
		}
	}
	/*
	public Pair search(Key k) {
		int i;
		for (i = 0; i < n && k.compareTo(keys[i]) > 0; i++);			
		if (i < n && keys[i].equals(k)) {
			return new Pair(this, i);
		}
		if (childs[i] == null) {
			return null;
		} else {
			return childs[i].search(k);
		}
	}*/
	public Key searchAtLeast(Key k) {
		int i;
		for (i = 0; i < n && k.compareTo(keys[i]) > 0; i++);			
		if (i < n && keys[i].equals(k)) {
			return keys[i];
		}
		if (childs[i] == null) {
			return keys[i];
		} else {
			return childs[i].searchAtLeast(k);
		}
	}
	public void split(int kp) {
		Node y = childs[kp];
		Node z = new Node();
		z.n = BTree.B - 1;
		for (int i = 0; i < z.n; i++) {
			z.keys[i] = y.keys[i + BTree.B];
			y.keys[i + BTree.B] = null;
		}
		for (int i = 0; i <= z.n; i++) {
			z.childs[i] = y.childs[i + BTree.B];	
			y.childs[i + BTree.B] = null;
		}
		y.n = BTree.B - 1;
		for (int i = n; i > kp; i--) {
			childs[i + 1] = childs[i];
		}
		childs[kp + 1] = z;
		for (int i = n - 1; i >= kp; i--) {
			keys[i + 1] = keys[i];
		}
		keys[kp] = y.keys[BTree.B - 1];
		y.keys[BTree.B - 1] = null;
		n++;
		y.keyc = BTree.B - 1;
		z.keyc = BTree.B - 1;		
		for (int i = 0; i <= BTree.B - 1; i++) {
			if (y.childs[i] != null) {
				y.keyc += y.childs[i].keyc;
			}
			if (z.childs[i] != null) {
				z.keyc += y.childs[i].keyc;
			}
		}
	}
	public void insertNonfull(Key k) {
		if (childs[0] == null) {
			int i;
			for (i = n - 1; i >= 0 && k.compareTo(keys[i]) <= 0; i--) {
				keys[i + 1] = keys[i]; 
			}
			keyc++;
			keys[i + 1] = k;
			n++;
		} else {
			int i;
			for (i = n - 1; i >= 0 && k.compareTo(keys[i]) <= 0; i--);
			i++;
			if (childs[i].n == 2 * BTree.B - 1) {
				split(i);
				if (k.compareTo(keys[i]) > 0) {
					i++;
				}
			} 
			keyc++;
			childs[i].insertNonfull(k);
		}
	}
	Key getKey(int num) {
		if (num >= keyc) {
			return null;
		}
		if (childs[0] != null) {
			int i;
			for (i = 0; i < n && num > childs[i].keyc; i++) {
				num -= childs[i].keyc;
				if (num == 0) {
					return keys[i];
				}
				num--;
			}
			return childs[i].getKey(num);
		} else {
			return keys[num];
		}		
	}
	int countTo(Key k) {		
		int i;
		for (i = n - 1; i >= 0 && k.compareTo(keys[i]) <= 0; i--);
		int res = i + 1;
		for(int j = 0; j <= i; j++) {
			if (childs[j] != null) {
				res += childs[j].keyc;
			}
				
		}
		return res + (childs[i + 1] == null ? 0 : childs[i + 1].countTo(k));
	}
	
	void setChild(int pos, Node r) {
		if (childs[pos] == null) {
			childs[pos] = r;
			keyc += r.keyc;
		} else {
			keyc += r.keyc - childs[pos].keyc;
		}
		
	}
	int getN() {
		return n;
	}
	public void readExternal(ObjectInput arg0) throws IOException, ClassNotFoundException {
		keyc = arg0.readInt();
		n = arg0.readInt();
		
		for (int i = 0; i < n; i++) {
			keys[i] = new Key();
			keys[i].readExternal(arg0);
		}
		boolean leaf = arg0.readBoolean();
		if (!leaf) {
			for (int i = 0; i <= n; i++) {
				childs[i] = new Node();
				childs[i].readExternal(arg0);
			}
		}		
	}
	public void writeExternal(ObjectOutput arg0) throws IOException {
		arg0.writeInt(keyc);
		arg0.writeInt(n);
		for (int i = 0; i < n; i++) {
			keys[i].writeExternal(arg0);
		}
		arg0.writeBoolean(childs[0] == null);
		if (childs[0] != null) {
			for (int i = 0; i <= n; i++) {
				childs[i].writeExternal(arg0);
			}
		}
		
	}
}
