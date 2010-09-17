package ide.operators;

import ide.value.Value;
import java.util.HashMap;

public class Enviroment {

    private HashMap<Id, Value> env = new HashMap<Id, Value>();

    public Value addToEnv(Id id, Value value) {
        return env.put(id, value);
    }

    public Value getValue(Id id) {
        for (Id i : env.keySet()) {
            if (i.getId().equals(id.getId())) {
                return env.get(i);
            }
        }
        return null;
    }

    public boolean isId(Id id) {
        if (env.containsKey(id)) {
            return true;
        } else {
            return false;
        }
    }

    public void removeId(Id id) {
        env.remove(id);
    }

    @Override
    public Enviroment clone() {
        Enviroment e = new Enviroment();
        e.env = (HashMap<Id, Value>) this.env.clone();
        return e;
    }
}
