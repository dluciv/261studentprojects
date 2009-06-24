package btreedatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lapin Sergey
 */
public class Btree<N extends BasicItem>{
    N own_key;
    int record_size;
    File workspaceFile;
    Comparator Current_comparator;
    int tree_degree;
    BtreeNode root;
    HashMap<Integer, ArrayList<Integer>> index_table = new HashMap<Integer, ArrayList<Integer>>();

    private ArrayList<Integer> CalcFullTreeIndexes() throws IOException
    {
       ArrayList<Integer> res = new ArrayList<Integer>();
       return CalcIndexesOnFullTree(res);
    }

    private ArrayList<Integer> CalcIndexesOnFullTree(ArrayList<Integer> res) throws IOException
    {
       if(root.child.isEmpty()){           
           return root.offsets_in_workspace;
       }       
       
       for(int i = 0; i < root.keyset.size(); i++){
            res.addAll(UnderTree(i).CalcIndexesOnFullTree(res));
            res.add((Integer)root.offsets_in_workspace.get(i));
       }

       res.addAll(UnderTree(root.keyset.size()).CalcIndexesOnFullTree(res));
       return res;
    }

    ArrayList<Integer> CalcIndexes(N left, N right) throws IOException
    {
       Disk_Read(root, own_key);
       if(Current_comparator.compare(left, root.keyset.get(root.keyset.size() - 1)) > 0){
           if(root.child.isEmpty()){
               ArrayList<Integer> tmp = new ArrayList<Integer>();
               return tmp;
           }
           return UnderTree(root.keyset.size()).CalcIndexes(left, right);
       }

       if(Current_comparator.compare(right, root.keyset.get(0)) < 0){
           if(root.child.isEmpty()){
               ArrayList<Integer> tmp = new ArrayList<Integer>();
               return tmp;
           }
           return UnderTree(0).CalcIndexes(left, right);
       }

       // если мы находимся в листе возвращаем пересечение ключей листа и исходного интервала
       if(root.child.isEmpty()){
           ArrayList<Integer> tmp = new ArrayList<Integer>();
           for(int i = 0; i < root.keyset.size(); i++){
                if(Current_comparator.compare(left, root.keyset.get(i)) > 0){
                    continue;
                }
                if(Current_comparator.compare(right, root.keyset.get(i)) < 0){
                    return tmp;
                }
                tmp.add((Integer)root.offsets_in_workspace.get(i));
           }
           return tmp;
       }

       ArrayList<Integer> res = new ArrayList<Integer>();

       int i = 0;

       while(Current_comparator.compare(left, root.keyset.get(i)) > 0){
            i++;
       }      

       res.addAll(UnderTree(i).CalcIndexes(left, right));

       boolean isfirst = false;
       for(; i < root.keyset.size(); i++){
           if(Current_comparator.compare(right, root.keyset.get(i)) >= 0 ){
               if(isfirst){                   
                   res.addAll(UnderTree(i).CalcFullTreeIndexes());
               }
               isfirst = true;
               res.add((Integer)root.offsets_in_workspace.get(i));
           } else {               
               break;
           }
       }
       if(isfirst)
            res.addAll(UnderTree(i).CalcIndexes(left, right));

       return res;
    }

    ArrayList<Integer> FindMinIndex(N left, N right) throws IOException
    {
       Disk_Read(root, own_key);
       // если левая граница правее последнего ключа берём крайнее правое поддерево
       if(Current_comparator.compare(left, root.keyset.get(root.keyset.size() - 1)) > 0){
           if(root.child.isEmpty()){
               ArrayList<Integer> tmp = new ArrayList<Integer>();
               return tmp;
           }
           return UnderTree(root.keyset.size()).FindMinIndex(left, right);
       }

       // если правая граница левее последнего ключа берём крайнее левое поддерево
       if(Current_comparator.compare(right, root.keyset.get(0)) < 0){
           if(root.child.isEmpty()){
               ArrayList<Integer> tmp = new ArrayList<Integer>();
               return tmp;
           }
           return UnderTree(0).FindMinIndex(left, right);
       }

       if(root.child.isEmpty()){
           ArrayList<Integer> tmp = new ArrayList<Integer>();
           for(int i = 0; i < root.keyset.size(); i++){
                if(Current_comparator.compare(left, root.keyset.get(i)) > 0){
                    continue;
                }
                if(Current_comparator.compare(right, root.keyset.get(i)) < 0){
                    return tmp;
                }
                tmp.add((Integer)root.offsets_in_workspace.get(i));
                return tmp;
           }
           return tmp;
       }

       ArrayList<Integer> res = new ArrayList<Integer>();

       for(int i = 0; i < root.keyset.size(); i++){
           if(Current_comparator.compare(left, root.keyset.get(i)) > 0){
               continue;
           }
           res = UnderTree(i).FindMinIndex(left, right);
           if(res.isEmpty()){
               res.add((Integer)root.keyset.get(i));
               return res;
           }
           ArrayList<Integer> finres = new ArrayList<Integer>();
           finres.add((Integer)res.get(0));
           return finres;
       }
       return res;
    }

    private int GetUniqIndex(){
        Integer i = Math.abs((int)Double.doubleToLongBits(Math.random()));
        if(!index_table.containsKey(i))
        {
            index_table.put(i, null);
            return i;
        }
        return GetUniqIndex();
    }

    public Btree(Comparator com, int d, File file, N key) throws IOException {
        own_key = key;
        record_size = own_key.getRecordSize();
        workspaceFile = file;
        tree_degree = d;
        Current_comparator = com;
        root = new BtreeNode(tree_degree, GetUniqIndex());

        RandomAccessFile F = new RandomAccessFile(workspaceFile, "r");

        for(int i = 0; i < (int)workspaceFile.length(); i += record_size){
            byte [] record = new byte[record_size];
            F.seek(i);
            F.read(record);
            BasicItem obj = own_key.Instance();
            obj.unserialize(record);
            B_Tree_Insert((N)obj, i / record_size);
        }
    }

    public Btree(Btree smth) throws IOException {
        own_key = (N) smth.own_key;
        record_size = own_key.getRecordSize();
        workspaceFile = smth.workspaceFile;
        tree_degree = smth.tree_degree;
        Current_comparator = smth.Current_comparator;
    }

    public class KnotInfo
    {
        BtreeNode res;
        Integer index;

        public KnotInfo(BtreeNode res, Integer index) {
            this.res = res;
            this.index = index;
        }
    }
    
    public Btree UnderTree(int i) throws IOException{
        Btree res = new Btree(this);
        BtreeNode tmp = (BtreeNode) root.child.get(i);
        Disk_Read(tmp, (N)root.keyset.get(0));
        res.root = tmp;
        return res;
    }
    
    public String toString()
    {
        String res = "";
        res += root + " <- root" + "\n";    
        for(int i = 0; i < root.child.size(); i++){
            res += "child " + String.valueOf(i) + ":\n";
            try {
                Btree tmp = UnderTree(i);
                res += tmp.toString();
                Disk_Write(tmp.root);
            } catch (IOException ex) {
                Logger.getLogger(Btree.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
        return res;
    }

    public KnotInfo B_Tree_Search(BtreeNode x, N key)
    {
        int i = 0;
        while(i < x.keyset.size() && Current_comparator.compare(key, x.keyset.get(i)) > 0){
            i++;
        }
        if(i < x.keyset.size() && Current_comparator.compare(key, x.keyset.get(i)) == 0){
            return new KnotInfo(x, i);
        }
        if(x.child.isEmpty()){
            return null;
        } else {
            BtreeNode x_child = new BtreeNode(tree_degree, (Integer)x.child.get(i));
            Disk_Read(x_child, key);
            return B_Tree_Search(x_child, key);
        }        
    }
    
    public void B_Tree_Split_Child(BtreeNode x, int i, BtreeNode y)
    {        
        BtreeNode z = new BtreeNode(tree_degree, GetUniqIndex());
        z.isLoaded = true;
        for(int j = 0; j < tree_degree - 1; j++){
            z.keyset.add(y.keyset.get(j + tree_degree));
            z.offsets_in_workspace.add(y.offsets_in_workspace.get(j + tree_degree));
        }

        y.keyset.removeAll(z.keyset);
        y.offsets_in_workspace.removeAll(z.offsets_in_workspace);
        
        if(!y.child.isEmpty()){
            for(int j = 0; j < tree_degree; j++){
                z.child.add(y.child.get(j + tree_degree));
                y.child.removeAll(x.child);
            }
        }
        
        x.child.add(i, z);
        x.keyset.add(i - 1, y.keyset.get(tree_degree - 1));
        y.keyset.removeAll(x.keyset);
        x.offsets_in_workspace.add(i - 1, y.offsets_in_workspace.get(tree_degree - 1));
        y.offsets_in_workspace.removeAll(x.offsets_in_workspace);

        Disk_Write(y);
        Disk_Write(x);
        Disk_Write(z);
    }

    public void B_Tree_Insert(N key, int offset)
    {
        BtreeNode r = root;
        if(root.keyset.size() == 2 * tree_degree - 1){
            BtreeNode s = new BtreeNode(tree_degree, GetUniqIndex());
            s.isLoaded = true;
            root = s;
            root.child.add(r);
            B_Tree_Split_Child(s, 1, r);
            B_Tree_Insert_Nonfull(s, key, offset);
        } else {
            B_Tree_Insert_Nonfull(r, key, offset);
        }
    }

    public void B_Tree_Insert_Nonfull(BtreeNode x, N key, int offset)
    {

        int i = x.keyset.size();
        if(x.child.isEmpty()){
            while(i >= 1 && Current_comparator.compare(key, x.keyset.get(i - 1)) < 0){
                i--;
            }
            x.keyset.add(i, key);
            x.offsets_in_workspace.add(i, offset);
            x.isLoaded = true;
            Disk_Write(x);
        } else {
            while(i >= 1 && Current_comparator.compare(key, x.keyset.get(i - 1)) < 0){
                i--;
            }
            i++;
            BtreeNode x_child = (BtreeNode) x.child.get(i - 1);
            Disk_Read(x_child, key);

            if(x_child.keyset.size() == 2 * tree_degree - 1){
                B_Tree_Split_Child(x, i, x_child);
                Disk_Read(x, key);
                Disk_Read(x_child, key);                               
                i--;
                if(Current_comparator.compare(key, x.keyset.get(i)) > 0){
                    i++;
                }
                
                x_child = (BtreeNode) x.child.get(i);
            }            
            Disk_Read(x_child, key);
            B_Tree_Insert_Nonfull(x_child, key, offset);            
        }
    }

    public void Disk_Write(BtreeNode res)
    {
        if(res != root){
            res.Free();
        }
    }

    private ArrayList<N> ReadKeysFromWorkspace(BtreeNode knot, N key) throws FileNotFoundException, IOException
    {
        ArrayList<N> res = new ArrayList<N>();
        ArrayList<Integer> n_list = knot.offsets_in_workspace;
        RandomAccessFile F = new RandomAccessFile(workspaceFile, "r");
        int size_of_record = key.getRecordSize();
        for(Integer tmp : n_list){
            F.seek(tmp * size_of_record);
            byte [] record = new byte[size_of_record];
            F.read(record, 0, size_of_record);
            BasicItem obj = key.Instance();
            obj.unserialize(record);
            res.add((N)obj);
        }
        knot.isLoaded = true;
        return res;
    }

    public void Disk_Read(BtreeNode res, N key)
    {
        if(res.isLoaded){
            return;
        }
        try {
            res.keyset = ReadKeysFromWorkspace(res, key);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Btree.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Btree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
