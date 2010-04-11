package savenko.ast;


public class Value {
    
    private int intValue;
    
    public Value(int value){
        intValue = value;
    }
    
    public int getIntValue(){
        return intValue;
    }
    
    public boolean getBoolValue(){
        if (intValue == 0 )return false;
        else return true;
    }

}
