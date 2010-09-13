/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arexprnew;

/**
 *
 * @author kate
 */
public class FunctionRecEnv extends Value{

    private FunctionRec function;
    private Enviroment env;

    public FunctionRecEnv(FunctionRec function, Enviroment env){
        this.function = function;
        this.env = env;
    }

    public FunctionRec getFunction(){
        return function;
    }

    public Enviroment getEnv(){
        return env;
    }
}
