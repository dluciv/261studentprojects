
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */

// все узлы хранятся в оперативной памяти но ArrayList<N> keyset загружается
// только по требованию Disk_Read и затем выгружается на диск с помощью Disk_Write
public class B_Tree_Node<N extends iRecord>{
    // эти данные всегда загружены
    boolean isLoaded = false;
    int degree;
    int index_in_share_pull;
    ArrayList<Integer> offsets_in_workspace = new ArrayList<Integer>();
    ArrayList<B_Tree_Node<N>> child = new ArrayList<B_Tree_Node<N>>();

    // загрузить если Disk_Read от данного узла дети остаются незагруженными
    ArrayList<N> keyset = new ArrayList<N>();

    public B_Tree_Node(int deg, int index) {
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

/*    public byte[] serialize() {
        byte[] out = new byte[getRecordSize()];
        int cursor = 0;
        int record_size = keyset.get(0).getRecordSize();

        out[cursor] = (byte)(keyset.size() + Byte.MIN_VALUE);
        cursor++;

        for (int i = 0; i < keyset.size(); i++) {
            byte [] record = keyset.get(i).serialize();
            System.arraycopy(record, 0, out, cursor, record_size);
            cursor += record_size;
        }

        out[cursor] = (byte)(child.size() + Byte.MIN_VALUE);
        cursor++;

        for (int i = 0; i < child.size(); i++) {
            byte [] record = Bits.intToByteArray(child.get(i));            
            System.arraycopy(record, 0, out, cursor, 4);
            cursor += 4;
        }
        
        return out;
    }*/

/*    public void unserialize(byte[] data, N key) {
        int cursor = 0;
        int keyset_size = data[cursor] - Byte.MIN_VALUE;

        cursor++;

        for (int i = 0; i < keyset_size; i++) {
            byte [] record = new byte[key.getRecordSize()];
            System.arraycopy(data, cursor, record, 0, key.getRecordSize());
            iRecord obj = key.Instance();
            obj.unserialize(record);
            keyset.add((N)obj);
            cursor += key.getRecordSize();
        }

        int child_size = data[cursor] - Byte.MIN_VALUE;
        cursor++;

        for (int i = 0; i < child_size; i++) {
            byte [] record = new byte[4];
            System.arraycopy(data, cursor, record, 0, 4);
            Integer obj = Bits.byteArrayToInt(record);
            child.add(obj);
            cursor += 4;
        }
  }*/
}
