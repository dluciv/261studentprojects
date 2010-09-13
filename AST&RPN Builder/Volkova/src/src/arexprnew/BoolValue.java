/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arexprnew;

/**
 *
 * @author kate
 */
public class BoolValue extends Value {

    private boolean value;

    public BoolValue(boolean value){
        this.value = value;
    }

    public boolean getBoolValue(){
        return value;
    }
}
