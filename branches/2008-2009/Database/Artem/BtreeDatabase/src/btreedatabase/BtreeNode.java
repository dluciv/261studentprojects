package btreedatabase;

import java.util.ArrayList;

/**
 *
 * @author Artem Mironov
 * @copyright 2009 Artem Mironov
 * @license GNU/GPL v2
 */


public class BtreeNode<N extends BasicItem>{
    // эти данные всегда загружены
    boolean isLoaded = false;
    int degree;
    int index_in_share_pull;
    ArrayList<Integer> offsets_in_workspace = new ArrayList<Integer>();
    ArrayList<BtreeNode<N>> child = new ArrayList<BtreeNode<N>>();

    // загрузить если Disk_Read от данного узла, дети остаются незагруженными
    ArrayList<N> keyset = new ArrayList<N>();

    public BtreeNode(int deg, int index) {
        degree = deg;
        index_in_share_pull = index;
    }

    @Override
    public String toString() {
        String res = "keys:";
        for(N tmp : keyset){
            res += tmp.toString() + " ";
        }
        res += "end";
        return res;
    }

    public int getRecordSize() {
        int keyset_size = keyset.size() * keyset.get(0).getRecordSize();
        int child_size = child.size() * 4;
        return keyset_size + 1 + child_size + 1;
    }

    void Free() {
        keyset.clear();
        isLoaded = false;
    }

}
