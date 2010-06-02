package name.stepa.ml.model.interpreter;

import java.util.TreeMap;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class Context {
    public TreeMap<String, Object> values;

    public Context() {
        this.values = new TreeMap<String, Object>();
    }

    public Context(TreeMap<String, Object> values) {
        this.values = values;
    }

    public Object put(String name, Object value) {
        return values.put(name, value);
    }

    public Object get(String name) {
        if (values.containsKey(name)) {
            return values.get(name);
        } else {
            return null;
        }
    }

    public void remove(String name) {
        values.remove(name);
    }

    public Context clone() {
        return new Context((TreeMap<String, Object>) this.values.clone());
    }

    public void clear() {
        this.values.clear();
    }
}
