package BTree;

  /**
  *
  * @author ksenyiacypan
  */

import java.util.Iterator;

public class BList implements Iterator<Key> {

	Key start;
	Key cur;
		
	int pos;
	int size;
	
	BList(Key start, int size) {
		this.pos = 0;
		this.start = start;
		this.size = size;
		if (size == 0) {
			this.start = null;
		}
		this.cur = start;
	}
	
	public boolean hasNext() {
		return pos < size - 1;
	}
	
	public Key getStart() {
		return start;
	}
	public Key next() {
		if (hasNext()) {
			cur = cur.next;
			pos++;
			return cur;
		}
		return null;
	}
	

	public void remove() {
		// no use
	}

}
