package huffman;
import java.util.ArrayList;
public class Tree implements Comparable<Tree> {

    long freq;
    int symbol = EMPTY;
    Tree left;
    Tree right;
    Tree parent;
    ArrayList<Integer> code = new ArrayList<Integer>();
    int num;

    static int EMPTY = -1;
    static int currNum = 0;

    Tree() {
    }

    Tree(int symbol, long freq) {
        this.freq = freq;
        this.symbol = symbol;
        //System.out.println(symbol + " - " + freq);
    }

    Tree(Tree first, Tree second) {
        this.right = first;
        this.right.parent = this;
        this.left = second;
        this.left.parent = this;
        this.freq = first.freq + second.freq;
        this.num = currNum++;
    }

    public int compareTo(Tree o) {
        if (this.freq > o.freq) {
            return 1;
        } else if (this.freq < o.freq){
            return -1;
        } else if(this.symbol > o.symbol)
            return 1;
        else if(this.symbol < o.symbol)
            return -1;
        else if(this.num > o.num)
            return 1;
        else if(this.num < o.num)
            return -1;
        else return 0;
    }
    public void printCode(){
        System.out.println(symbol + " - " + freq + " : " + code);
    }
    public boolean isLeaf(){
        return (symbol != EMPTY);
    }


}
