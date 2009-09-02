package BTree;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import Cards.CardManager;

/**
*
* @author ksenyiacypan
*/


public class Key implements Comparable, Externalizable {
	Value val;
	BTree btree;
	Key next;	

	public Key(BTree btree) {
		this.btree = btree;
	}
	public Key(Value val, BTree btree) {
		this.val = val;
		this.btree = btree;
	}
	public int compareTo(Object arg0) {
		return btree.comp.compare(val, ((Key)arg0).val);
	}
	public boolean equals(Object o) {		
		return compareTo(o) == 0;		
	}
	public String toString() {
		return val.toString();
	}
	public void readExternal(ObjectInput arg0) throws IOException, ClassNotFoundException {
		val = btree.comp.getValue();
		val.readExternal(arg0);
	}
	public void writeExternal(ObjectOutput arg0) throws IOException {
		val.writeExternal(arg0);
	}
}
