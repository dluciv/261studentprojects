package database.index;

import tree.Key;
import tree.TreeElement;

/**
 * Created by IntelliJ IDEA.
 * User: Sergey
 * Date: 28.08.2009
 * Time: 14:02:10
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseKey extends Key {

    private String lastName;
    private String name;
    private String middleName;

    public DatabaseKey(String lastName, String name, String middleName, TreeElement link) {
        super(link);
        this.lastName = lastName;
        this.name = name;
        this.middleName = middleName;
    }


    public Key clone() {
        return new DatabaseKey(lastName, name, middleName, link);
    }

    public int compareTo(Object o) {
        if(o == null) return 1;
        if(o.getClass() != getClass()) return 1;
        DatabaseKey k = (DatabaseKey)o;

        // Сравниваем в порядке фамилия-имя-отчество
        int compareLastNames = lastName == null ? 1 : lastName.compareTo(k.lastName);
        int compareNames = name == null ? 1 : name.compareTo(k.name);
        int compareMiddleNames = middleName == null ? 1 : middleName.compareTo(k.middleName);

        if(compareLastNames != 0) return compareLastNames;
        if(compareNames != 0) return compareNames;
        return compareMiddleNames;
    }
}
