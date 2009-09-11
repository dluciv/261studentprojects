/**
* BTree
* Ksenyia Cypan (c) 2009
*/

package BTree;

import java.io.Externalizable;

public interface Manager extends Externalizable {
	public Value getValue();
	public int compare(Value a, Value b);	
}
