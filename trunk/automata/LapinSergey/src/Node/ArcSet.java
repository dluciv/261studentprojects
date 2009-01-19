/**
 *
 * Lapin Sergey 261 group mat-mex
 * Regular expression analysis
 * 19.01.2009
 */

package Node;
import java.util.HashSet;

public class ArcSet {
    public HashSet<Arc> set;
    
    public ArcSet()
    {
        set = new HashSet<Arc>();
    }
    
    public void addArc(Arc tmp)
    {
        if(set == null)
            set = new HashSet<Arc>();
        set.add(tmp);
    }
    
    public HashSet<Node> getNodeSet ()
    {
        HashSet<Node> res = new HashSet<Node>();
        for(Arc tmp : set)
            res.add(tmp.vertex);
        return res;        
    }
    
    public HashSet<Character> getSymbolSet ()
    {
        HashSet<Character> res = new HashSet<Character>();
        for(Arc tmp : set)
            res.add(tmp.symbol);
        return res;        
    }
    
    public ArcSet getBySymbol (Character tmp)
    {
        if(set == null)
            return null;
        ArcSet res = new ArcSet();
        for(Arc tmpr : set)
            if(tmpr.symbol.equals(tmp))
                res.addArc(tmpr);
        return res;   
    }
    
    public boolean containsNode(Node n)
    {
        for(Arc tmp : set)
            if(tmp.vertex.equals(n))
                return true;
        return false;          
    }
    
    public void removeNode(Node n)
    {
        if(set == null)
            return;
        HashSet<Arc> toDelete = new HashSet<Arc>();
        for(Arc tmp : set)
            if(tmp.vertex.equals(n))
                toDelete.add(tmp);
        for(Arc tmp : toDelete)
            set.remove(tmp);                  
    }
    
    public void switchArcSetOn(ArcSet tmp, Node n)
    {
        for(Arc tmpr : tmp.set)
            tmpr.vertex = n;
    }
    
    public void addArcSet(ArcSet tmp)
    {
        if(tmp == null)
            return;
        for(Arc tmpr : tmp.set)
            this.addArc(tmpr);
    }
    
    @Override
    public String toString() {  
        String s = "";
        for(Arc tmp : set)
            s += tmp + "\n"; 
        return s;
    }
}
