package database.index;

import tree.Key;
import tree.TreeElement;
import dbentities.Condition;

/**
 * Ключ дерева, используемый при работе с текущей базой данных
 *
 * @author nastya
 * Date: 28.08.2009
 * Time: 14:02:10
 *
 */
public class DatabaseKey extends Key{

    Condition condition;

    public DatabaseKey(Condition condition, TreeElement link) {
        super(link);
        this.condition = condition;
    }

    public DatabaseKey(Condition condition) {
        this(condition, null);
    }


    public Key clone() {
        return new DatabaseKey(condition.clone(), link);
    }

    public int compareTo(Object o) {
        if(o == null) return 1;
        if(o.getClass() != getClass()) return 1;
        DatabaseKey k = (DatabaseKey)o;

        return condition == null ? -1 : condition.compareTo(k.condition);
    }

    @Override
    public String toString() {
        return "DatabaseKey(" + condition + ")";
    }
}
