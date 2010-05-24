/*
 * класс булевых значений
 * Savenko Maria(c)
 */
package savenko.interpreter;

public class BoolValue extends Value{
	
	private boolean booleanVal;
    
    public BoolValue(boolean value){
    	booleanVal = value;
    }
    
    public boolean getValue(){
        return booleanVal;
    }

}
