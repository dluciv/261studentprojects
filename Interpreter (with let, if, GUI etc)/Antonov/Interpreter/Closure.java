/*
 * класс значение для вычисления функции
 * содержит копию среды, до добавления в нее значения переменной из функции
 * Antonov Kirill(c), 2010
 */
package name.kirill.ml.interpreter;

import name.kirill.ml.environment.Environment;
import name.kirill.ml.environment.Value;
import name.kirill.ml.ast.Function;

public class Closure extends Value {

    private Function fun;
    private Environment env;

    public Closure(Function function, Environment env) {
        this.fun = function;
        this.env = env;
    }

    public Function getFunction() {
        return fun;
    }

    public Environment getEnvironment() {
        return env;
    }
}
