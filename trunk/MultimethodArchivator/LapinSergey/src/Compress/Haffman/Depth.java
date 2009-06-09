/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Compress.Haffman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author SLapin
 */
public class Depth {
    public HashMap<Byte, Byte> date = null;

    public Depth(HashMap<Byte, Byte> newdate)
    {
        date = newdate;
    }

    public Byte FindMaxDepth ()
    {
        Integer res = (int)Byte.MIN_VALUE;
        for(Byte tmp : date.values()){
            if(tmp > res){
                res = (int)tmp;
            }
        }
        return res.byteValue();
    }

    public ArrayList<Byte> GetByDepth(Byte tmp)
    {
       ArrayList<Byte> res = new ArrayList<Byte>();
       for(Byte tmpr : date.keySet()){
           if(date.get(tmpr) == tmp){
               res.add(tmpr);
           }
       }
       return res;
    }

    public void DeleteByDepth(Byte tmp)
    {
        ArrayList<Byte> toDelete = GetByDepth(tmp);
        for(Byte tmpr : toDelete){
            date.remove(tmpr);
        }
    }

    @Override
    public String toString()
    {
        String res = "";
        Byte max = FindMaxDepth();

        while(max != Byte.MIN_VALUE){
            ArrayList<Byte> level = GetByDepth(max);
            if(level.isEmpty()){
                max--;
                continue;
            }
            res += "Depth:" + max + "\n";
            res += "Values:" + "\n";
            for(Byte tmp : level){
                res += tmp + " ";
            }
            res += "with count:" + level.size();
            res += "\n";
            max--;
        }
        return res;
    }
}
