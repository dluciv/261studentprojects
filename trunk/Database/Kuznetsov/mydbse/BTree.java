package mydbse;

/**
 *
 * @author Kirill Kuznetsov
 */
import java.util.ArrayList;

public class BTree {

    private ArrayList<Record> keys = new ArrayList<Record>();
    private ArrayList<BTree> children = new ArrayList<BTree>();
    private boolean leaf = true;
    private BTree parent;
    private static int ORDER = 16;
    private static int INIT = -1;

    BTree() {
    }

    BTree(Record k) {
        keys.add(k);
        children.add(new BTree());
        children.add(new BTree());
    }

    public void addKey(Record newKey) {
        BTree root = this;

        root = getRoot();
        BTree cNode = root.findNode(newKey);
        cNode.addKeyToNode(newKey, new BTree());
        root = getRoot();
//        root.printTree(0);
//        System.out.println();
    }

    public void addKeyToNode(Record newKey, BTree right) {
        int pos = findPos(newKey);


        if (children.isEmpty()) {
            children.add(new BTree());
        }
        if (pos > 0 && keys.get(pos - 1).compareTo(newKey) == 0) {
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
        
        BTree brother = new BTree();
        Record midKey = new Record();
        midKey = this.keys.get(ORDER / 2);

        share(brother);
        if (parent == null) {
            BTree newRoot = new BTree();
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

    private BTree findNode(Record r) {
        if (this.leaf) {
            return this;
        }
        Record curr;
        int lastKey = keys.size() - 1;
        for (int i = 0; i <= lastKey; ++i) {
            curr = keys.get(i);
            if (r.compareTo(curr) <= 0) {
                return children.get(i).findNode(r);
            }
        }
        return children.get(lastKey + 1).findNode(r);
    }

    private int findPos(Record newKey) {

        for (int i = 0; i < keys.size(); ++i) {
            if (newKey.compareTo(keys.get(i)) < 0) {
                return i;
            }
        }
        return keys.size();

    }

    public ArrayList<Integer> find(Record from, Record to) {
        
        ArrayList<Integer> lines = new ArrayList<Integer>();
        Record key, prevKey = new Record("");
        BTree child;
        int lastProperChild = INIT;

        if (leaf) {
            for (Record k : keys) {
                if (k.compareTo(to) <= 0 && k.compareTo(from) >= 0) {
                    lines.addAll(k.getLineNums());
                }
            }
        } else {
            for (int i = 0; i < keys.size(); ++i) {
                key = keys.get(i);
                lastProperChild = i + 1;
                if (key.compareTo(to) <= 0 && key.compareTo(from) >= 0) {
                    if (key.compareTo(from) > 0) {
                        child = children.get(i);
                        lines.addAll(child.find(from, to));
                    }
                    lines.addAll(key.getLineNums());
                    if (key.compareTo(to) < 0) {
                        lastProperChild = i + 1;
                    }
                } else if (key.compareTo(to) > 0 && prevKey.compareTo(from) < 0) {
                    child = children.get(i);
                    lines.addAll(child.find(from, to));
                    return lines;
                } else if (key.compareTo(to) > 0) {
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

    public ArrayList<Integer> findRecord(String k) {
        Record req = new Record(k);
        BTree node = findNode(req);

        for (Record nodeKey : node.keys) {
            if (nodeKey.compareTo(req) == 0) {
                return nodeKey.getLineNums();
            }
        }
        return new ArrayList<Integer>();
    }

    public void printTree(int n) {
        int lastChild = 0;

        if (this.leaf) {
            for (int i = 0; i < keys.size(); ++i) {
                System.out.print(n + " - ");
                System.out.println(keys.get(i).getKey());
            }
        } else {
            for (int i = 0; i < keys.size(); ++i) {
                children.get(i).printTree(n + 1);
                System.out.print(n + " - ");
                System.out.println(keys.get(i).getKey());
                lastChild = i + 1;
            }
            children.get(lastChild).printTree(n + 1);
        }

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
