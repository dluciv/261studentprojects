package Node;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lapin
 */
public class Arc {
    public Node vertex;
    public Character symbol;
    public static final char EPSILON = '\r';   
    
    public Arc ()
    {
        vertex = null;
        symbol = null;
    }
    
    public Arc (char tmp)
    {
        this.symbol = new Character(tmp);
        vertex = null;
    }
    
    public Arc (Node Outgoing, Character Symbol)
    {
        this.symbol = Symbol;
        this.vertex = Outgoing;
    }
    
    
    public static Arc getEpsilonNode(){
        return new Arc(EPSILON);
    }
    
    public static boolean isEpsilonNode(Arc n){
        Character tmp = new Character(EPSILON);
        return n.symbol.equals(tmp);
    }
    
    private String toWellName(char c){
        if(c == '\r'){
            return "eps";
        }
        else{
            return String.valueOf(c);
        }
    }
    
    @Override
    public String toString() {          
        String s = "(" + toWellName(symbol)+ "," + vertex.condition + ")";
        return s;        
    }
}
