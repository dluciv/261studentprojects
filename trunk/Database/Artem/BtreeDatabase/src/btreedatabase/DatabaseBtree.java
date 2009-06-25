package btreedatabase;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Artem Mironov
 * @copyright 2009 Artem Mironov
 * @license GNU/GPL v2
 */

public class DatabaseBtree<N extends BasicItem> {
    File data;
    ArrayList<Comparator> orders;    
    N own_key;
    HashMap<Integer, Btree> constructed_trees = new HashMap<Integer, Btree>();

    public DatabaseBtree(N key) {
        own_key = key;        
        BasicItem obj = key.Instance();
        orders = obj.AvaliableOrders();        
    }

    public void InitDataFile(String filename, int size)
    {
        try {
            Timer construction_file = new Timer();
            System.out.println("File construction");
            data = GenerateDataFile(filename, size);
            System.out.println("Data file size: " + String.valueOf(data.length()));
            construction_file.out();
        } catch (IOException ex) {
            Logger.getLogger(DatabaseBtree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public File GenerateDataFile(String filename, int size) throws IOException
    {
        File f = new File(filename);
        RandomAccessFile fileStream = new RandomAccessFile(f, "rw");
        CardItem tmp;
        for (int i = 0; i < size; i++) {
            tmp = Cards.GenerateRandomCard();
            fileStream.write(tmp.serialize());
        }
        return f;
    }

    public void MakeB_Tree(Comparator order, int degree)
    {
        if(!orders.contains(order)){
            System.out.println("No such allowed order");
            return;
        }
        System.out.println("Tree construction");
        Timer b_tree = new Timer();
        int num = orders.indexOf(order);

        Btree<N> res = null;
        try {
            res = new Btree(order, degree, data, own_key);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseBtree.class.getName()).log(Level.SEVERE, null, ex);
        }
        b_tree.out();
        constructed_trees.put(num, res);
    }

    public ArrayList<Integer> SimpleFindInDataFile(N left_border, N right_border, Comparator order){
        try {
            System.out.println("Simple index construction");
            Timer index_construct = new Timer();
            ArrayList<Integer> res = new ArrayList<Integer>();
            RandomAccessFile F = new RandomAccessFile(data, "r");
            int size_of_record = own_key.getRecordSize();
            for (int i = 0; i < data.length(); i += size_of_record) {
                F.seek(i);
                byte[] record = new byte[size_of_record];
                F.read(record, 0, size_of_record);
                BasicItem obj = own_key.Instance();
                obj.unserialize(record);
                N tmp = (N) obj;
                if (order.compare(left_border, tmp) <= 0 && order.compare(right_border, tmp) >= 0) {
                    res.add(i / size_of_record);
                }
            }
            index_construct.out();
            return res;
        } catch (IOException ex) {
            Logger.getLogger(DatabaseBtree.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Integer> SimpleFindMinInDataFile(N left_border, N right_border, Comparator order){
        try {
            System.out.println("Find simple first element");
            Timer index_construct = new Timer();
            ArrayList<Integer> res = new ArrayList<Integer>();
            RandomAccessFile F = new RandomAccessFile(data, "r");
            int size_of_record = own_key.getRecordSize();

            byte[] record;            
            BasicItem obj;
            N min = left_border;
            Integer min_index = null;

            boolean isfirst = true;
            for (int i = 0; i < data.length(); i += size_of_record) {
                F.seek(i);
                record = new byte[size_of_record];
                F.read(record, 0, size_of_record);
                obj = own_key.Instance();
                obj.unserialize(record);
                N tmp = (N) obj;
                if (order.compare(left_border, tmp) <= 0 && order.compare(right_border, tmp) >= 0) {
                    if(order.compare(tmp, min) < 0 || isfirst){
                        isfirst = false;
                        min = tmp;
                        min_index = i / size_of_record;
                    }
                }
            }
            res.add(min_index);
            index_construct.out();
            return res;
        } catch (IOException ex) {
            Logger.getLogger(DatabaseBtree.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Integer> MakeIndex(N left_border, N right_border, Comparator order)
    {
        if(!orders.contains(order)){
            return null;
        }

        int num = orders.indexOf(order);

        if(!constructed_trees.containsKey(num)){
            MakeB_Tree(order, 2);
        }

        if(order.compare(left_border, right_border) > 0){
           return null;
        }
        
        try {                      
            System.out.println("Index construction");
            Timer index_construct = new Timer();
            ArrayList<Integer> res = new ArrayList<Integer>();  
            res = constructed_trees.get(num).CalcIndexes(left_border, right_border);
            index_construct.out();
            return res;
        } catch (IOException ex) {
            Logger.getLogger(DatabaseBtree.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Integer> FindMin(N left_border, N right_border, Comparator order)
    {
        if(!orders.contains(order)){
            return null;
        }

        int num = orders.indexOf(order);

        if(!constructed_trees.containsKey(num)){
            MakeB_Tree(order, 2);
        }

        if(order.compare(left_border, right_border) > 0){
           return null;
        }

        try {
            System.out.println("Find min");
            Timer index_construct = new Timer();
            ArrayList<Integer> res = new ArrayList<Integer>();
            res = constructed_trees.get(num).FindMinIndex(left_border, right_border);
            index_construct.out();
            return res;
        } catch (IOException ex) {
            Logger.getLogger(DatabaseBtree.class.getName()).log(Level.SEVERE, null, ex);
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
                BasicItem obj = own_key.Instance();
                obj.unserialize(record);
                res.add((N) obj);
            }            
            return res;
        } catch (IOException ex) {
            Logger.getLogger(DatabaseBtree.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
