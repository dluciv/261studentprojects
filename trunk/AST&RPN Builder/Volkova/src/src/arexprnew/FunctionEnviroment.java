/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arexprnew;

/**
 *
 * @author kate
 */
public class FunctionEnviroment extends Value{

    private Function function;
    private Enviroment env;

    public FunctionEnviroment(Function function, Enviroment env){
        this.function = function;
        this.env = env;
    }

    public Function getFunction(){
        return function;
    }

    public Enviroment getEnv(){
        return env;
    }
}
