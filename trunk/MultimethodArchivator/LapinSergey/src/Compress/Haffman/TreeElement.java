package Compress.Haffman;

import java.util.*;

/**
 *
 * @author Lapin Sergey
 */
public class TreeElement implements Cloneable{
    private Integer weight;
    private Byte symbol;
    private TreeElement right;
    private TreeElement left;

    static WeightComparator weightcomparator = new WeightComparator();
    static SymbolComparator symbolcomparator = new SymbolComparator();

    public boolean IsLeaf()
    {
        if (symbol == null) {
            return false;
        }
        return true;
    }

    public Object clone() {
        return new TreeElement(left, right, weight, symbol);
    }   

    public void Copy(TreeElement a)
    {
        right = a.getRight();
        left = a.getLeft();
        weight = a.getWeight();
        symbol = a.getSymbol();        
    }

    public ArrayList<TreeElement> StepDeaper() {
        ArrayList<TreeElement> res = new ArrayList<TreeElement>();        
        if (left != null){
            res.add(left);
        }
        if (right != null){
            res.add(right);
        }
        return res;
    }   

    public static class WeightComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            return (int)((TreeElement)o1).getWeight() - (int)((TreeElement)o2).getWeight();
        }
    }

    public static class SymbolComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            return (int)((TreeElement)o1).getSymbol() - (int)((TreeElement)o2).getSymbol();
        }
    }

    public static WeightComparator getWeightComparator()
    {
        return weightcomparator;
    }

    public static SymbolComparator getSymbolComparator()
    {
        return symbolcomparator;
    }

    public TreeElement(TreeElement left, TreeElement right, Integer weight, Byte symbol) {
        this.weight = weight;
        this.symbol = symbol;
        this.right = right;
        this.left = left;
    }

    public TreeElement(Byte symbol) {
        this.weight = null;
        this.symbol = symbol;
        this.right = null;
        this.left = null;
    }

    public Integer CalcWeight()
    {
        if(symbol != null)
            return weight;
        weight = left.CalcWeight() + right.CalcWeight();
        return weight;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String toString()
    {
        String res = "";
        if(symbol != null)  {
            //res += weight;
            res += symbol;
            return res;
        }

        //res += "(" + left.toString() + ", " + right.toString() + ")" + " -> " + weight;
        res += "(" + left.toString() + ", " + right.toString() + ")";
        return res;
    }

    public Byte getSymbol() {
        return symbol;
    }

    public void setSymbol(Byte symbol) {
        this.symbol = symbol;
    }

    public TreeElement getRight() {
        return right;
    }

    public void setRight(TreeElement right) {
        this.right = right;
    }

    public TreeElement getLeft() {
        return left;
    }

    public void setLeft(TreeElement left) {
        this.left = left;
    }
}
