package savenko.ast;


public class Identifier implements Expression{

    private String identifier;
    
    public Identifier(String new_identifier){
        identifier = new_identifier;
    }
    
    public String GetName(){
        return identifier;
    }
}
