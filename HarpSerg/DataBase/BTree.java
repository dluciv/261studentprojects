/**
 * @copyright 2009 HarpSerg
 * @author HarpSerg
 */
import java.util.ArrayList;
import java.util.Comparator;
/**
 * @copyright 2009 HarpSerg
 * @author HarpSerg
 */
public class BTree {

    private ArrayList<Entry> keys = new ArrayList<Entry>();
    private ArrayList<BTree> children = new ArrayList<BTree>();
    private boolean leaf = true;
    private BTree parent;
    private Comparator<Entry> c;
    private static final int ORDER = 16;
    private static final int INIT = -1;
    private static Entry prevKey = new Entry("","0");

    BTree(Comparator<Entry> c) {
        this.c = c;
    }

    BTree(Entry k, Comparator<Entry> c) {
        keys.add(k);
        children.add(new BTree(c));
        children.add(new BTree(c));
        this.c = c;
    }

    public void addKey(Entry newKey) {
        BTree root = this;

        root = getRoot();
        BTree cNode = root.findNode(newKey);
        cNode.addKeyToNode(newKey, new BTree(c));
        root = getRoot();
    }

    public void addKeyToNode(Entry newKey, BTree right) {
        int pos = findPos(newKey);


        if (children.isEmpty()) {
            children.add(new BTree(c));
        }
        if (pos > 0 && c.compare(keys.get(pos - 1),newKey) == 0) {
            keys.get(pos - 1).addToLineNums(newKey.getLineNums());
        } else {
            keys.add(pos, newKey);
            children.add(pos + 1, right);
            right.parent = this;
            if (keys.size() > ORDER) {
                split();
            }
        }

    }

    private void split() {
        
        BTree brother = new BTree(c);
        Entry midKey = this.keys.get(ORDER / 2);

        share(brother);
        if (parent == null) {
            BTree newRoot = new BTree(c);
            parent = newRoot;
            parent.leaf = false;
            parent.children.add(this);
        }
        this.keys.remove(ORDER / 2);
        parent.addKeyToNode(midKey, brother);
    }

    private void share(BTree brother) {
        for (int i = ORDER / 2 + 1; i <= ORDER; ++i) {
            brother.keys.add(this.keys.get(ORDER / 2 + 1));
            brother.children.add(this.children.get(ORDER / 2 + 1));
            this.keys.remove(ORDER / 2 + 1);
            this.children.remove(ORDER / 2 + 1);

        }
        brother.children.add(this.children.get(ORDER / 2 + 1));
        this.children.remove(ORDER / 2 + 1);
        for (BTree child : brother.children) {
            child.setParent(brother);
            if (!child.keys.isEmpty()) {
                brother.leaf = false;
            }
        }
    }

    private BTree findNode(Entry r) {
        if (this.leaf) {
            return this;
        }
        Entry curr;
        int lastKey = keys.size() - 1;
        for (int i = 0; i <= lastKey; ++i) {
            curr = keys.get(i);
            if (c.compare(r,curr) <= 0) {
                return children.get(i).findNode(r);
            }
        }
        return children.get(lastKey + 1).findNode(r);
    }

    private int findPos(Entry newKey) {

        for (int i = 0; i < keys.size(); ++i) {
            if (c.compare(newKey,keys.get(i)) < 0) {
                return i;
            }
        }
        return keys.size();
    }

    public ArrayList<Integer> find(Entry from, Entry to) {
        
        ArrayList<Integer> lines = new ArrayList<Integer>();
        Entry key;
        BTree child;
        int lastProperChild = INIT;

        if (leaf) {
            for (Entry k : keys) {
                if (c.compare(k,to) <= 0 && c.compare(k,from) >= 0) {
                    lines.addAll(k.getLineNums());
                }
            }
        } else {
            for (int i = 0; i < keys.size(); ++i) {
                key = keys.get(i);
                if(key.getSN().compareTo("K") >= 0 && key.getTel().compareTo("1500000")<=0){
                    int a = 1;
                }
                lastProperChild = i + 1;
                if (c.compare(key,to) <= 0 && c.compare(key,from) >= 0) {
                    if (c.compare(key,from) > 0) {
                        child = children.get(i);
                        lines.addAll(child.find(from, to));
                    }
                    lines.addAll(key.getLineNums());
                    if (c.compare(key,to) < 0) {
                        lastProperChild = i + 1;
                    }
                } else if (c.compare(key,to) > 0 && c.compare(prevKey, from) < 0) {
                    child = children.get(i);
                    lines.addAll(child.find(from, to));
                    return lines;
                } else if (c.compare(key,to) > 0) {
                    return lines;
                }
                prevKey = key;
            }
            if (lastProperChild != INIT) {
                lines.addAll(children.get(lastProperChild).find(from, to));
            }
        }
        return lines;
    }


    private int getKeysNum() {
        int n = 0;
        int lastChild = 0;

        for (BTree child : children) {
            if (child != null) {
                leaf = false;
            }
        }
        if (this.leaf) {
            return keys.size();
        } else {
            for (int i = 0; i < keys.size(); ++i) {
                n += children.get(i).getKeysNum() + 1;
                lastChild = i + 1;
            }
            n += children.get(lastChild).getKeysNum();
        }
        return n;
    }

    public BTree getParent() {
        return parent;
    }

    public void setParent(BTree newParent) {
        parent = newParent;
    }

    public BTree getRoot(){
        BTree root = this;
        while (root.parent != null) {
            root = root.parent;
        }
        return root;
    }
}
