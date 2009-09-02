package BTree;

import java.io.Externalizable;

/**
*
* @author ksenyiacypan
*/


public interface Manager extends Externalizable {
	public Value getValue();
	public int compare(Value a, Value b);	
}
