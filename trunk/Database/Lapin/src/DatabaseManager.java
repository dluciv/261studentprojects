
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
 * @author Administrator
 */
public class DatabaseManager<N extends iRecord> {
    File data;
    ArrayList<Comparator> orders;    
    N own_key;


    public DatabaseManager(String database, N key) {
        own_key = key;
        data = new File(database);
        iRecord obj = key.Instance();       
        orders = obj.AvaliableOrders();        
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

    public ArrayList<Integer> MakeIndex(N left_border, N right_border, Comparator order)
    {
        if(!orders.contains(order)){
            return null;
        }

        if(order.compare(left_border, right_border) > 0){
           return null;
        }
        B_Tree tree = MakeB_Tree(order, 2);
        System.out.println(tree);
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
