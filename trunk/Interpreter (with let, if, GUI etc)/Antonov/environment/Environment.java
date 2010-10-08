/*
 * хранит все переменные с их значениями
 * Antonov Kirill(c), 2010
 */
package name.kirill.ml.environment;

import name.kirill.ml.ast.Identifier;
import java.util.HashMap;

public class Environment {

    private HashMap<Identifier, Value> environment = new HashMap<Identifier, Value>();

    public Value addToEnvironment(Identifier Identificator, Value value) {
        return environment.put(Identificator, value);
    }

    public void removeEnvironment() {

        environment.clear();
    }

    public Identifier GetIdentifier(String name){
        for (Identifier i : environment.keySet()) {
            if (i.GetName().equals(name)) {
                return i;
            }
        }
        return null;
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
        environment.remove(ident);
    }

    @Override
    public Environment clone() {
        Environment env = new Environment();
        env.environment = (HashMap<Identifier, Value>) this.environment.clone();
        return env;
    }
}
