/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hotheart.regexp.AST;

/**
 *
 * @author m08ksa
 */
public class AstNode {
    public static final byte TYPE_END = -1;
    public static final byte TYPE_SYMBOL = 0;
    public static final byte TYPE_GROUP = 1;
    public static final byte TYPE_STAR = 2;
    public static final byte TYPE_PLUS = 3;
    public static final byte TYPE_QUESTION = 4;
    
    
    public int type = TYPE_END;
    public char symbol = '\0';
    
    public AstNode next = null;
    public AstNode prev = null;
    public AstNode[] subNodes = new AstNode[0];
    
    public String toString()
    {
        if (type == TYPE_END)
            return "%END%";
        
        String result = "";
        if (type == TYPE_SYMBOL)
            result+= Character.toString(symbol);
        if (type == TYPE_GROUP)
            result+= "Group(" + subNodes[0].toString() +  ")";
        if (type == TYPE_STAR)
            result+= "Mult(>=0)(" + subNodes[0].toString() + ")";
        if (type == TYPE_PLUS)
            result+= "Mult(>0)(" + subNodes[0].toString() + ")";
        if (type == TYPE_QUESTION)
            result+= "Mult(0 or 1)(" + subNodes[0].toString() + ")";
        
        result += "->";
        
        if (next != null)
            result += next.toString();
        
        return result;
    }
}