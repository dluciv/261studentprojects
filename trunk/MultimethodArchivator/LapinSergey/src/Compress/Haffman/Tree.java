package Compress.Haffman;

import Bits.Bits;
import Compress.*;
import Compress.Dictionary;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Lapa
 * Date: 07.03.2009
 * Time: 10:07:00
 * To change this template use File | Settings | File Templates.
 */
public class Tree{
    TreeElement root;

    public void CalcWeight()
    {
        root.CalcWeight();
    }

    private void SortByWeight(ArrayList<TreeElement> res)
    {
        Collections.sort(res, TreeElement.getWeightComparator());
    }

    private void SortBySymbol(ArrayList<TreeElement> res)
    {
        Collections.sort(res, TreeElement.getSymbolComparator());
    }

    public Depth CalcDepth()
    {
        HashMap<Byte, Byte> res = new HashMap<Byte, Byte>();
        if(root == null){
            return new Depth(res);
        }
        CalcDepthFromRoot(root, res, (byte)(Byte.MIN_VALUE));
        return new Depth(res);
    }

    public void CalcDepthFromRoot(TreeElement tmp, HashMap<Byte, Byte> res, Byte depth)
    {
        if(tmp.IsLeaf())
        {
            res.put(tmp.getSymbol(), depth);
            return;
        }
        CalcDepthFromRoot(tmp.getLeft(), res, (byte)(depth + 1));
        CalcDepthFromRoot(tmp.getRight(), res, (byte)(depth + 1));
    }

    public Dictionary CalcDictionary(int mode)
    {
        Dictionary res = new Dictionary();
        Bits startStr = new Bits();
        if(root == null){
            return res;
        }
        if(root.getSymbol() != null){
            startStr.AddBit(false);
            if(mode == 0){                
                    res.direct_dictionary.put(root.getSymbol(), startStr);                    
                }
                if(mode == 1){
                    res.revert_dictionary.put(startStr.GetBitStr(), root.getSymbol());
                }
            return res;
        }
        FillDictionaryRecursivelly(res, root, startStr, mode);
        return res;
    }

    private void FillDictionaryRecursivelly(Dictionary a, TreeElement elem, Bits share, int mode)
    {
        if(elem.getSymbol() != null){
            if(mode == 0){
                a.direct_dictionary.put(elem.getSymbol(), share);
                return;
            }
            if(mode == 1){
                a.revert_dictionary.put(share.GetBitStr(), elem.getSymbol());
                return;
            }
        }
        Bits leftCode = (Bits)share.clone();
        leftCode.AddBit(false);
        Bits rightCode = (Bits)share.clone();
        rightCode.AddBit(true);
        FillDictionaryRecursivelly(a, elem.getLeft(), leftCode, mode);
        FillDictionaryRecursivelly(a, elem.getRight(), rightCode, mode);
    }

    public String toString()
    {
        if(root == null){
            return root  + "\n";
        }
        return root.toString() + "\n";
    }

    public TreeElement MergeElements (TreeElement a, TreeElement b)
    {
        if(a.getWeight() == null) {
            return new TreeElement(a, b, null, null);
        }
        return new TreeElement(a, b, a.getWeight() + b.getWeight(), null);
    }

    public Tree(HashMap<Byte, Integer> frequency)
    {
        if(frequency.isEmpty()){
            root = null;
            return;
        }
        ArrayList<TreeElement> res = new ArrayList<TreeElement>();
        for(Byte tmp : frequency.keySet()) {
            TreeElement tmpr = new TreeElement(null, null, frequency.get(tmp), tmp);
            res.add(tmpr);            
        }

        while(res.size() > 1){
            SortByWeight(res);
            TreeElement a = res.get(0);
            TreeElement b = res.get(1);
            res.add(MergeElements(a, b));
            res.remove(a);
            res.remove(b);
        }

        root = res.get(0);
    }
    
    private void FillTreeByDepth(Byte curDepth, Depth depth, ArrayList<TreeElement> prevLevel)
    {        
        if(curDepth == Byte.MIN_VALUE){
            ArrayList<Byte> tmp = depth.GetByDepth(curDepth);
            if(tmp.isEmpty() && prevLevel.size() == 1){
                root = prevLevel.get(0);
                return;
            }

            TreeElement temp = new TreeElement(null, null, null, tmp.get(0));
            root = temp;
            return;
        }
        
        ArrayList<Byte> tmp = depth.GetByDepth(curDepth);
        
        ArrayList<TreeElement> tmpLevel = new ArrayList<TreeElement>();
        for(Byte tmpr : tmp) {
            TreeElement temp = new TreeElement(null, null, null, tmpr);
            tmpLevel.add(temp);            
        }
        
        LexicalLevelSort(tmpLevel);        
        
        ArrayList<TreeElement> currentLevel = new ArrayList<TreeElement>();
        currentLevel.addAll(prevLevel);
        currentLevel.addAll(tmpLevel);
        
        ArrayList<TreeElement> nextLevel = new ArrayList<TreeElement>();            
        
        while(currentLevel.size() != 0){            
            TreeElement a = currentLevel.get(0);
            TreeElement b = currentLevel.get(1);
            nextLevel.add(MergeElements(a, b));
            currentLevel.remove(a);
            currentLevel.remove(b);
        }
        
        FillTreeByDepth((byte)(curDepth - 1), depth, nextLevel);
    }

    public Tree(Depth depth)
    {
        if(depth.date.isEmpty()){
            root = null;
            return;
        }
        Byte maxDepth = depth.FindMaxDepth();
        ArrayList<TreeElement> prevLevel = new ArrayList<TreeElement>();
        FillTreeByDepth(maxDepth, depth, prevLevel);
    }

    public Object Get(int level, int offset) {
        return GetLevel(level).get(offset);
    }

    private ArrayList<TreeElement> NextLevel(ArrayList<TreeElement> tmp) {
        ArrayList<TreeElement> res = new ArrayList<TreeElement>();
        for (int j = 0; j < tmp.size(); j++) {
            res.addAll((tmp.get(j)).StepDeaper());
        }
        return res;
    }

    private ArrayList<TreeElement> GetLevel(int i) {
        ArrayList<TreeElement> res = new ArrayList<TreeElement>();
        res.add(root);
        while (i-- > 0) {
            res = NextLevel(res);
        }
        return res;
    }

    public boolean SetLevel(int level, ArrayList<TreeElement> obj) {
        if(obj.size() != GetLevel(level).size())
            return false;                  
        ArrayList<TreeElement> coll = new ArrayList<TreeElement>();
        int i = 0;
        for(TreeElement tmp : obj){
            coll.add((TreeElement)obj.get(i++).clone());
        }
        i = 0;
        for(TreeElement res : GetLevel(level)){
            TreeElement tmp = coll.get(i++);                     
            res.Copy(tmp);
        }
        return true;
    }

    public void MakeCanonical() throws CloneNotSupportedException {
        if(root == null){
            return;
        }
        KnotLeafSort();
        LexicalSort();
        CalcWeight();
    }

    public void LexicalSort() {
        ArrayList<TreeElement> res = GetLevel(0);
        int i = 0;
        while (res.size() != 0) {
            this.LexicalLevelSort(res);
            res = NextLevel(res);
            i++;
        }
    }

    private void LexicalLevelSort(ArrayList<TreeElement> tmp) {
        ArrayList<TreeElement> tmpList = new ArrayList<TreeElement>();
        for (TreeElement tmpr : tmp) {
            if (tmpr.getSymbol() != null) {
                tmpList.add((TreeElement) tmpr.clone());
            }
        }

        SortBySymbol(tmpList);

        int i = 0;
        for (TreeElement tmpr : tmp) {
            if (tmpr.getSymbol() != null) {
                tmpr.Copy(tmpList.get(i++));
            }
        }
    }

    private ArrayList<TreeElement> KnotLeafLevelSort(ArrayList<TreeElement> tmp)//shit
    {
        TreeElement res[] = new TreeElement[tmp.size()];
        int min = 0;
        int max = tmp.size() - 1;

        for (TreeElement tmpr : tmp) {
            if (tmpr.getSymbol() == null) {
                res[min++] = tmpr;
            } else {
                res[max--] = tmpr;
            }
        }

        ArrayList<TreeElement> result = new ArrayList<TreeElement>();
        int i = 0;
        for (TreeElement tmpr : tmp) {
            result.add(res[i++]);
        }

        return result;
    }

    private void Swap(TreeElement a, TreeElement b) {
        TreeElement c = a;
        a = b;
        b = c;
    }

    public void KnotLeafSort() {
        ArrayList<TreeElement> tmp = GetLevel(0);
        int i = 0;
        while (tmp.size() != 0) {
            ArrayList<TreeElement> tmpr = KnotLeafLevelSort(tmp);
            SetLevel(i++, tmpr);
            tmp = NextLevel(tmpr);            
        }
    }
}
