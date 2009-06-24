import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lapin Sergey
 */
public class DatabaseManager<N extends iRecord> {
    File data;
    ArrayList<Comparator> orders;    
    N own_key;   

    public DatabaseManager(N key) {
        own_key = key;        
        iRecord obj = key.Instance();       
        orders = obj.AvaliableOrders();        
    }

    public void InitDataFile(String filename, int size)
    {
        try {
            data = GenerateDataFile(filename, size);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public File GenerateDataFile(String filename, int size) throws IOException
    {
        File f = new File(filename);
        RandomAccessFile fileStream = new RandomAccessFile(f, "rw");
        Card tmp;
        for (int i = 0; i < size; i++) {
            tmp = CardSet.GenerateRandomCard();
            fileStream.write(tmp.serialize());
        }
        return f;
    }

    public B_Tree MakeB_Tree(Comparator order, int degree)
    {        
        B_Tree<N> res = null;
        try {
            res = new B_Tree(order, degree, data, own_key);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }

    public ArrayList<Integer> SimpleFindInDataFile(N left_border, N right_border, Comparator order){
        try {
            ArrayList<Integer> res = new ArrayList<Integer>();
            RandomAccessFile F = new RandomAccessFile(data, "r");
            int size_of_record = own_key.getRecordSize();
            for (int i = 0; i < data.length(); i += size_of_record) {
                F.seek(i);
                byte[] record = new byte[size_of_record];
                F.read(record, 0, size_of_record);
                iRecord obj = own_key.Instance();
                obj.unserialize(record);
                N tmp = (N) obj;
                if (order.compare(left_border, tmp) <= 0 && order.compare(right_border, tmp) >= 0) {
                    res.add(i / size_of_record);
                }
            }
            return res;
        } catch (IOException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Integer> MakeIndex(N left_border, N right_border, Comparator order)
    {
        if(!orders.contains(order)){
            return null;
        }

        if(order.compare(left_border, right_border) > 0){
           return null;
        }
        B_Tree tree = MakeB_Tree(order, 20);
        try {
            return tree.CalcIndexes(left_border, right_border);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<N> ReadIndex(ArrayList<Integer> index)
    {
        ArrayList<N> res = new ArrayList<N>();
        try {
            RandomAccessFile F = new RandomAccessFile(data, "r");
            int size_of_record = own_key.getRecordSize();
            for (Integer tmp : index) {
                F.seek(tmp * size_of_record);
                byte[] record = new byte[size_of_record];
                F.read(record, 0, size_of_record);
                iRecord obj = own_key.Instance();
                obj.unserialize(record);
                res.add((N) obj);
            }
            return res;
        } catch (IOException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
