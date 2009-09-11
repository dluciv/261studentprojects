package dbentities;

import java.io.Serializable;

/**
 * Объект, представляющий собой то самое содержание ключа, которое определяет
 * сравнение
 *
 * @author nastya
 *         Date: 30.08.2009
 *         Time: 3:20:30
 *
 * @see database.index.DatabaseKey
 */
public class Condition implements Comparable, Cloneable, Serializable{
    private String lastName;
    private String name;
    private String middleName;

    /**
     * Представляет с собой "бесконечность". То есть в случае, если это условие
     * сравнивается "снизу", толюбой элемент больше него. В случае, если "сверху",
     * то любой элемент считается больше него. Это хитрый способ не очень укладывается
     * в понятие сравниваемости
     */
    public static Condition INFINITY = new Condition("", "", "");

    public Condition(String lastName, String name, String middleName) {
        this.lastName = lastName;
        this.name = name;
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int compareTo(Object o) {
        if(o == null) return 1;
        if(! (o instanceof  Condition)) return 1;
        if(this.equals(INFINITY)) return -1;
        Condition c= (Condition) o;
        if(c.equals(INFINITY)) return -1;
        int ln = lastName == null ? -1 : lastName.compareTo(c.lastName);
        int n = name== null ? -1 : name.compareTo(c.name);
        int mn = middleName== null ? -1 : middleName.compareTo(c.middleName);
        if(ln != 0) return ln;
        if(n != 0) return n;
        return mn;
    }

    public Condition clone(){
        return new Condition(lastName, name, middleName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Condition condition = (Condition) o;

        if (lastName != null ? !lastName.equals(condition.lastName) : condition.lastName != null) return false;
        if (middleName != null ? !middleName.equals(condition.middleName) : condition.middleName != null) return false;
        if (name != null ? !name.equals(condition.name) : condition.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lastName != null ? lastName.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Condition(" + lastName + ", " + name + ", " + middleName + ")";
    }
}

