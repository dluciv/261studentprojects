package savenko.ast;

import savenko.ast.Expression;


public class Begin implements Expression{
    
    private Sequence seq;
    
    public Begin(Sequence new_seq){
        seq = new_seq;    
    }
    
    public Sequence getSequence(){
        return seq;
    }

}
