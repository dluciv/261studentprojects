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
public class B_Tree<N extends iRecord>{
    
    String workspaceFile;
    Comparator Current_comparator;
    int tree_degree;
    B_Tree_Node root;
    HashMap<Integer, Integer> index_table = new HashMap<Integer, Integer>();

    private int GetUniqIndex(){
        Integer i = Math.abs((int)Double.doubleToLongBits(Math.random()));
        if(!index_table.containsKey(i))
        {
            index_table.put(i, null);
            return i;
        }
        return GetUniqIndex();
    }

    public B_Tree(Comparator com, int d, String FileName) {
        workspaceFile = FileName;
        tree_degree = d;
        Current_comparator = com;
        root = new B_Tree_Node(tree_degree, GetUniqIndex());
    }

    public class KnotInfo
    {
        B_Tree_Node res;
        Integer index;

        public KnotInfo(B_Tree_Node res, Integer index) {
            this.res = res;
            this.index = index;
        }
    }

    public KnotInfo B_Tree_Search(B_Tree_Node x, N key)
    {
        int i = 0;
        while(i <= x.keyset.size() && Current_comparator.compare(key, x.keyset.get(i)) < 0){
            i++;
        }
        if(i <= x.keyset.size() && Current_comparator.compare(key, x.keyset.get(i)) == 0){
            return new KnotInfo(x, i);
        }
        if(x.child.isEmpty()){
            return null;
        } else {
            B_Tree_Node x_child = new B_Tree_Node(tree_degree, i);
            Disk_Read(x_child, key);
            B_Tree_Search(x, key);
        }
        return null;
    }
    
    public void B_Tree_Split_Child(B_Tree_Node x, int i, B_Tree_Node y)
    {
        B_Tree_Node z = new B_Tree_Node(tree_degree, GetUniqIndex());
        for(int j = 0; j < tree_degree - 1; j++){
            z.keyset.add(y.keyset.get(j + tree_degree));
        }
        
        if(!y.child.isEmpty()){
            for(int j = 0; j < tree_degree; j++){
                z.child.add(y.child.get(j + tree_degree));
            }
        }
        
        x.child.add(i, z.index_in_share_pull);
        x.keyset.add(i - 1, y.keyset.get(tree_degree));
        
        Disk_Write(y);
        Disk_Write(x);
        Disk_Write(z);
    }

    public void B_Tree_Insert(N key)
    {
        B_Tree_Node r = root;
        if(root.keyset.size() == 2 * tree_degree - 1){
            B_Tree_Node s = new B_Tree_Node(tree_degree, GetUniqIndex());
            root = s;
            root.child.add(r.index_in_share_pull);
            B_Tree_Split_Child(s, 1, r);
            B_Tree_Insert_Nonfull(s, key);
        } else {
            B_Tree_Insert_Nonfull(r, key);
        }
    }

    public void B_Tree_Insert_Nonfull(B_Tree_Node x, N key)
    {
        int i = x.keyset.size();
        if(x.child.isEmpty()){
            while(i >= 1 && Current_comparator.compare(key, x.keyset.get(i - 1)) < 0){
                i--;
            }
            x.keyset.add(i, key);
            Disk_Write(x);
        } else {
            while(i >= 1 && Current_comparator.compare(key, x.keyset.get(i - 1)) < 0){
                i--;
            }
            i++;
            B_Tree_Node x_child = new B_Tree_Node(tree_degree, ((Integer)x.child.get(i)).intValue());

            Disk_Read(x_child, key);
            if(x_child.keyset.size() == 2 * tree_degree - 1){
                B_Tree_Split_Child(x, i, x_child);
                if(Current_comparator.compare(key, x.keyset.get(i)) < 0){
                    i++;
                }
            }
            B_Tree_Insert_Nonfull(x_child, key);
        }
    }

    public void Disk_Write(B_Tree_Node res)
    {
        try {
            RandomAccessFile F = new RandomAccessFile(workspaceFile, "rw");
            int filesize = (int)F.length();
            F.seek(F.length());
            F.writeInt(res.getRecordSize());
            F.write(res.serialize());
            index_table.put(res.index_in_share_pull, filesize);
            if(!res.equals(root)){
                res.clear();
            }
        } catch (IOException ex) {
            Logger.getLogger(B_Tree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Disk_Read(B_Tree_Node res, N key)
    {
        try {
            RandomAccessFile F = new RandomAccessFile(workspaceFile, "r");
            F.seek(index_table.get(res.index_in_share_pull));
            int size_of_record = F.readInt();
            byte [] record = new byte[size_of_record];
            F.read(record, 0, size_of_record);
            res.unserialize(record, key);
        } catch (IOException ex) {
            Logger.getLogger(B_Tree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
