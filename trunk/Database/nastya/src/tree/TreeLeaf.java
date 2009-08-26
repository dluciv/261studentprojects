package tree;

import java.util.Vector;

/**
 * @author nastya
 *         Date: 21.08.2009
 *         Time: 3:57:24
 */
public class TreeLeaf extends TreeElement{
    private Vector<Address> filePointers;



    public TreeLeaf(){
        filePointers = new Vector<Address>();
    }

    public void add(Address address){
        filePointers.add(address);
    }

    public void add(TreeLeaf treeLeaf) {
        filePointers.addAll(treeLeaf.filePointers);
    }

    @Override
    public String toString() {
        return "[TreeLeaf: " + filePointers + "]\n";
    }
}
