/*
 * хранит все переменные с их значениями
 * Antonov Kirill(c), 2010
 */
package Interpreter;

import AST.Identificator;
import java.util.HashMap;

public class Environment {

    private HashMap<Identificator, Value> environment = new HashMap<Identificator, Value>();

    public Value addToEnvironment(Identificator Identificator, Value value) {
        return environment.put(Identificator, value);
    }

    public void removeEnvironment() {

        environment.clear();
    }

    public Value getValue(Identificator Identificator) {
        for (Identificator i : environment.keySet()) {
            if (i.GetName().equals(Identificator.GetName())) {
                return environment.get(i);
            }
        }
        return null;
    }

    public boolean ifIdentificatorExist(Identificator Identificator) {
        for (Identificator i : environment.keySet()) {
            if (i.GetName().equals(Identificator.GetName())) {
                return true;
            }
        }
        return false;
    }

    public void removeIdentificator(Identificator ident) {
        for (Identificator i : environment.keySet()) {
            if (i.GetName().equals(ident.GetName())) {
                environment.remove(i);
            }
        }
    }

    @Override
    public Environment clone() {
        Environment env = new Environment();
        env.environment = (HashMap<Identificator, Value>) this.environment.clone();
        return env;
    }
}
