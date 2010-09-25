/*
 *  класс для целых значений 
 * Antonov Kirill(c), 2010
 */
package Interpreter;

public class IntValue extends Value{

    private int intValue;

    public IntValue(int value){
        intValue = value;
    }

    public int getValue(){
        return intValue;
    }

}