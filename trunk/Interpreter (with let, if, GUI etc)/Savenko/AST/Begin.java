package savenko.AST;

import savenko.AST.Expression;


public class Begin implements Expression{
    
    private Sequence seq;
    
    public Begin(Sequence new_seq){
        seq = new_seq;    
    }
    
    public Sequence getSequence(){
        return seq;
    }

}
