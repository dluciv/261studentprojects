/*
 * хранит все переменные с их значениями
 * Antonov Kirill(c), 2010
 */
package Interpreter;

import AST.Identifier;
import java.util.HashMap;

public class Environment {

    private HashMap<Identifier, Value> environment = new HashMap<Identifier, Value>();

    public Value addToEnvironment(Identifier Identificator, Value value) {
        return environment.put(Identificator, value);
    }

    public void removeEnvironment() {

        environment.clear();
    }

    public Value getValue(Identifier Identificator) {
        for (Identifier i : environment.keySet()) {
            if (i.GetName().equals(Identificator.GetName())) {
                return environment.get(i);
            }
        }
        return null;
    }

    public boolean ifIdentificatorExist(Identifier Identificator) {
        for (Identifier i : environment.keySet()) {
            if (i.GetName().equals(Identificator.GetName())) {
                return true;
            }
        }
        return false;
    }

    public void removeIdentificator(Identifier ident) {
        for (Identifier i : environment.keySet()) {
            if (i.GetName().equals(ident.GetName())) {
                environment.remove(i);
            }
        }
    }

    @Override
    public Environment clone() {
        Environment env = new Environment();
        env.environment = (HashMap<Identifier, Value>) this.environment.clone();
        return env;
    }
}
