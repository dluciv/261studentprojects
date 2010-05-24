/*
 * класс целочисленных значений
 * Savenko Maria(c)
 */
package savenko.interpreter;

public class IntValue extends Value{

    private int intValue;
    
    public IntValue(int value){
        intValue = value;
    }
    
    public int getValue(){
        return intValue;
    }    

}
