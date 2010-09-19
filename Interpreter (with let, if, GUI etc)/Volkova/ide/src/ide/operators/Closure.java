/*
 *
 *
 * (c) Volkova Ekatetina
 */
package ide.operators;

import ide.value.Value;

public class Closure extends Value {

    private Function function;
    private Enviroment env;

    public Closure(Function function, Enviroment env) {
        this.function = function;
        this.env = env;
    }

    public Function getFunction() {
        return function;
    }

    public Enviroment getEnv() {
        return env;
    }
}
